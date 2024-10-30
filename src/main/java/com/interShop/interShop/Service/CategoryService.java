package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Categories;
import com.interShop.interShop.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
