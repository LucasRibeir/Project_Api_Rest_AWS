package com.lucasribeiro.anotaai.services;

import com.lucasribeiro.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.lucasribeiro.anotaai.domain.product.Product;
import com.lucasribeiro.anotaai.domain.product.ProductDto;
import com.lucasribeiro.anotaai.domain.product.exceptions.ProductNotFoundException;
import com.lucasribeiro.anotaai.repositories.ProductRepository;
import com.lucasribeiro.anotaai.services.aws.AwsSnsService;
import com.lucasribeiro.anotaai.services.aws.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AwsSnsService awsSnsService;


    public Product insert(ProductDto productDto) {
        var category = categoryService.getById(productDto.categoryId()).orElseThrow(CategoryNotFoundException::new);
        var product = new Product(productDto);
        product.setCategory(category);
        awsSnsService.pusblishMessage(new MessageDto(product.getOwnerId()));
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product update(String id, ProductDto productDto) {
        var product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (productDto.categoryId() != null)
            categoryService.getById(productDto.categoryId()).ifPresent(product::setCategory);

        if (!product.getTitle().isEmpty()) product.setTitle(productDto.title());
        if (!product.getDescription().isEmpty()) product.setDescription(productDto.description());
        if (!(product.getPrice() == null)) product.setPrice(productDto.price());

        productRepository.save(product);
        awsSnsService.pusblishMessage(new MessageDto(product.getOwnerId()));
        return product;
    }

    public void delete(String id) {
        var product = productRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        productRepository.delete(product);
    }
}
