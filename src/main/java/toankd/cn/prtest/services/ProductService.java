package toankd.cn.prtest.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import toankd.cn.prtest.entities.Product;
import toankd.cn.prtest.repositories.ProductRepository;

import java.util.*;

@Service
@Slf4j
@CacheConfig(cacheNames = "productCache")
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(cacheNames = "products")
    public List<Product> processSearch(String q) {
        waitSomeTime();
        return this.productRepository.findByName(q);
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product add(Product product) {
        return this.productRepository.save(product);
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
}
