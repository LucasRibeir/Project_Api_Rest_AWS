package com.lucasribeiro.anotaai.services;

import com.lucasribeiro.anotaai.domain.category.Category;
import com.lucasribeiro.anotaai.domain.category.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
     Category insert(CategoryDto categoryDto);

     List<Category> getAll();

     Optional<Category> getById(String id);

     Category update(String id, CategoryDto categoryDto);

     void delete(String id);
}
