package com.lucasribeiro.anotaai.repositories;

import com.lucasribeiro.anotaai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
