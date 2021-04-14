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
}
