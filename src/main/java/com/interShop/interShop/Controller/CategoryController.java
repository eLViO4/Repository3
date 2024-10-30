package com.interShop.interShop.Controller;

import com.interShop.interShop.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.interShop.interShop.Entity.Categories;
import com.interShop.interShop.Service.CategoryService;
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
    public List<Categories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Categories> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Categories createCategory(@RequestBody Categories category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping
    public Categories updateCategory(@RequestBody Categories category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}