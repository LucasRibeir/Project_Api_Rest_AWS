package com.lucasribeiro.ApiRestAWS.services;

import com.lucasribeiro.ApiRestAWS.domain.category.Category;
import com.lucasribeiro.ApiRestAWS.domain.category.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
     Category insert(CategoryDto categoryDto);

     List<Category> getAll();

     Optional<Category> getById(String id);

     Category update(String id, CategoryDto categoryDto);

     void delete(String id);
}
