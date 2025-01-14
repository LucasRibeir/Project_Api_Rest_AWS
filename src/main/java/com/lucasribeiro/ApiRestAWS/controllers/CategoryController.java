package com.lucasribeiro.ApiRestAWS.controllers;

import com.lucasribeiro.ApiRestAWS.domain.category.Category;
import com.lucasribeiro.ApiRestAWS.domain.category.CategoryDto;
import com.lucasribeiro.ApiRestAWS.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDto categoryDto) {
        var category = categoryService.insert(categoryDto);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(@RequestBody CategoryDto categoryDto) {
        var categories = categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDto categoryDto) {
        var updateCategory = categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();

    }
}