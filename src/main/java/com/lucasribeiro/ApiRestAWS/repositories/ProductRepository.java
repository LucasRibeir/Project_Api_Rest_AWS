package com.lucasribeiro.ApiRestAWS.repositories;

import com.lucasribeiro.ApiRestAWS.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
