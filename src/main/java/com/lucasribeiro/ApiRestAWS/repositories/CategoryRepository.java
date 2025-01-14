package com.lucasribeiro.ApiRestAWS.repositories;

import com.lucasribeiro.ApiRestAWS.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}