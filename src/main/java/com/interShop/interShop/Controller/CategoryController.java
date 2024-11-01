package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.interShop.interShop.Entity.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/intershop/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }
}
