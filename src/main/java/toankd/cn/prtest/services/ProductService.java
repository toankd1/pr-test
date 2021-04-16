package toankd.cn.prtest.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import toankd.cn.prtest.elastic.index.ProductIndex;
import toankd.cn.prtest.elastic.repositories.ProductIndexRepo;
import toankd.cn.prtest.entities.Product;
import toankd.cn.prtest.repositories.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIndexRepo productIndexRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(cacheNames = "products")
    public List<Product> getAll() {
        // test
        waitSomeTime();
        return this.productRepository.findAll();
    }

    @Cacheable(cacheNames = "product", key = "#id", unless = "#result == null")
    public Product getProductById(int id) {
        waitSomeTime();
        return this.productRepository.findById(id).orElse(null);
    }

    // not cache
    public List<Product> getProductByName(String q) {
        return this.productRepository.findByName(q);
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product add(Product product) {
        return this.productRepository.save(product);
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product update(Product product) {
        Optional<Product> optProduct = this.productRepository.findById(product.getId());
        if (optProduct.isEmpty())
            return null;
        Product repProduct = optProduct.get();
        repProduct.setName(product.getName());
        repProduct.setPrice(product.getPrice());
        repProduct.setQuantity(product.getQuantity());
        repProduct.setCategory(product.getCategory());
        repProduct.setDescription(product.getDescription());
        repProduct.setManufacturer(product.getManufacturer());
        return this.productRepository.save(repProduct);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "product", key = "#id"),
            @CacheEvict(cacheNames = "products", allEntries = true)})
    public void delete(int id) {
        this.productRepository.deleteById(id);
    }

    private void waitSomeTime() {
        System.out.println("Long Wait Begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Long Wait End");
    }

    public void reimportElastic() {
        // 1. Query list product from db
        List<Product> productList = this.productRepository.findAll();

        // 2. Convert to index
        List<ProductIndex> productDataSet = productList.stream()
                .map(this::productToProductIndexMapper).collect(Collectors.toList());

        // 3. Import to Elastic
        productIndexRepo.deleteAll();
        productIndexRepo.saveAll(productDataSet);
    }

    private ProductIndex productToProductIndexMapper(final Product product) {
        ProductIndex productIndex = modelMapper.map(product, ProductIndex.class);
        return productIndex;
    }
}
