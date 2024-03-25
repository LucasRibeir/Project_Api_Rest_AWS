package com.lucasribeiro.anotaai.controllers;

import com.lucasribeiro.anotaai.domain.product.Product;
import com.lucasribeiro.anotaai.domain.product.ProductDto;
import com.lucasribeiro.anotaai.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDto productDto) {
        var product = productService.insert(productDto);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestBody ProductDto productDto) {
        var categories = productService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        var updateProduct = productService.update(id, productDto);
        return ResponseEntity.ok().body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}