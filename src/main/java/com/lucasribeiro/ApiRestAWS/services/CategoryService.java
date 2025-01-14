package com.lucasribeiro.ApiRestAWS.services;

import com.lucasribeiro.ApiRestAWS.domain.category.Category;
import com.lucasribeiro.ApiRestAWS.domain.category.CategoryDto;
import com.lucasribeiro.ApiRestAWS.domain.category.exceptions.CategoryNotFoundException;
import com.lucasribeiro.ApiRestAWS.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category insert(CategoryDto categoryDto) {
        var category = new Category(categoryDto);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(String id, CategoryDto categoryDto) {
        var category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (!category.getTitle().isEmpty()) category.setTitle(categoryDto.title());

        if (!category.getDescription().isEmpty()) category.setDescription(categoryDto.description());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public void delete(String id) {
        var category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}