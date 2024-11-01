package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Category;
import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Repository.CategoryRepository;
import com.interShop.interShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // Category CRUD

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Additional
    public List<Product> getProductsByCategory(Long categoryId){
         return productRepository.findByCategoryId(categoryId);
     }
}
