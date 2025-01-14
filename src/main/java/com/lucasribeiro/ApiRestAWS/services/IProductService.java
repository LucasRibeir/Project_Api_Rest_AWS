package com.lucasribeiro.ApiRestAWS.services;

import com.lucasribeiro.ApiRestAWS.domain.product.Product;
import com.lucasribeiro.ApiRestAWS.domain.product.ProductDto;

import java.util.List;

public interface IProductService {
    Product insert(ProductDto productDto);

    List<Product> getAll();

    Product update(String id, ProductDto productDto);

    void delete(String id);
}
