package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Products;
import com.interShop.interShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intershop/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Products> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> getProductsById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Products addProduct(@RequestBody Products products) {
        return productService.saveProduct(products);
    }

    @PutMapping
    public Products updateProduct(@RequestBody Products products) {
        return productService.saveProduct(products);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
