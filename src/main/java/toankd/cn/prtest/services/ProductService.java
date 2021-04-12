package toankd.cn.prtest.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toankd.cn.prtest.entities.Product;
import toankd.cn.prtest.repositories.ProductRepository;

import java.util.*;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> processSearch(String q) {
        return productRepository.findAll();
    }
}
