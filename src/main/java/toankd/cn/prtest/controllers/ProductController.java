package toankd.cn.prtest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toankd.cn.prtest.elastic.services.ProductSearch;
import toankd.cn.prtest.entities.Product;
import toankd.cn.prtest.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSearch processSearch;

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        List<Product> products = this.productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") String id) {
        int _id = Integer.parseInt(id);
        Product product = this.productService.getProductById(_id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/products/name")
    public ResponseEntity<Object> getProductByName(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}", query);
        List<Product> products = this.productService.getProductByName(query);
        log.info("products {}", products);
        return ResponseEntity.ok(products);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        Product created = this.productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/products")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        Product updated = this.productService.update(product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("id") String id) {
        int _id = Integer.parseInt(id);
        this.productService.delete(_id);
        return ResponseEntity.ok().build();
    }

    // import db to elasticsearch
    @PostMapping(value = "/product/reimport")
    public ResponseEntity<Object> reimportElastic() {
        this.productService.reimportElastic();
        return ResponseEntity.ok().build();
    }

    // fuzzy search
    @GetMapping(value = "/products/search")
    public ResponseEntity<Object> fetchByName(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}", query);
        List<Product> products = processSearch.processSearch(query);
        log.info("products {}", products);
        return ResponseEntity.ok(products);
    }

    // suggestion
    @GetMapping(value = "/products/suggestion")
    public ResponseEntity<Object> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        log.info("fetch suggests {}", query);
        List<String> suggests = processSearch.fetchSuggestions(query);
        log.info("suggests {}", suggests);
        return ResponseEntity.ok(suggests);
    }
}
