package toankd.cn.prtest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public List<Product> getProduct(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}",query);
        List<Product> products = productService.processSearch(query) ;
        log.info("products {}",products);
        return products;
    }
}
