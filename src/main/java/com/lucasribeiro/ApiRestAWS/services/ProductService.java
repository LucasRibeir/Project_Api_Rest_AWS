package com.lucasribeiro.ApiRestAWS.services;

import com.lucasribeiro.ApiRestAWS.domain.category.exceptions.CategoryNotFoundException;
import com.lucasribeiro.ApiRestAWS.domain.product.Product;
import com.lucasribeiro.ApiRestAWS.domain.product.ProductDto;
import com.lucasribeiro.ApiRestAWS.domain.product.exceptions.ProductNotFoundException;
import com.lucasribeiro.ApiRestAWS.repositories.ProductRepository;
import com.lucasribeiro.ApiRestAWS.services.aws.AwsSnsService;
import com.lucasribeiro.ApiRestAWS.services.aws.MessageDto;
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
