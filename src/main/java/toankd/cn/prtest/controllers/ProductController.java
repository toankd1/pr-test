package toankd.cn.prtest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toankd.cn.prtest.entities.Product;
import toankd.cn.prtest.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Object> getProduct(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}", query);
        List<Product> products = this.productService.processSearch(query);
        log.info("products {}", products);
        return ResponseEntity.ok(products);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> addCustomer(@RequestBody Product product) {
        Product created = this.productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
