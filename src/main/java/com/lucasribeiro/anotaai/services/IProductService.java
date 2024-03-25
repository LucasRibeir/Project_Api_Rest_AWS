package com.lucasribeiro.anotaai.services;

import com.lucasribeiro.anotaai.domain.product.Product;
import com.lucasribeiro.anotaai.domain.product.ProductDto;

import java.util.List;

public interface IProductService {
    Product insert(ProductDto productDto);

    List<Product> getAll();

    Product update(String id, ProductDto productDto);

    void delete(String id);
}
