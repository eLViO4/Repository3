package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Product CRUD

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    // Search and Filtering

    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }


    // Inventory Management

    public boolean isProductAvailable(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product!=null && product.isAvailable();
    }


}
