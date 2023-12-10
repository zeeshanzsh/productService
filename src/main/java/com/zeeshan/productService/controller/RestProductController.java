package com.zeeshan.productService.controller;

import com.zeeshan.productService.exception.InvalidCategoryException;
import com.zeeshan.productService.exception.ProductNotFoundException;
import com.zeeshan.productService.exception.ProductNotSavedException;
import com.zeeshan.productService.redis.entity.Product;
import com.zeeshan.productService.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zeeshan
 */

@RestController
@RequestMapping("/product")
@Slf4j
public class RestProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) throws ProductNotSavedException {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getByProductId(@PathVariable int id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findByProductId(id));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam(name = "category", required = false) String category)
            throws InvalidCategoryException {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductUnderPrice(@RequestParam(name = "price", required = false) Long price)
            throws ProductNotFoundException {
        return ResponseEntity.ok(productService.filterByUnderPrice(price));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProductById(id);

    }
}
