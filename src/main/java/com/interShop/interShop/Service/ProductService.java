package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Products;
import com.interShop.interShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Products> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

}
