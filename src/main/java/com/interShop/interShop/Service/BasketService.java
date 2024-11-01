package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public BasketService(BasketRepository basketRepository, ProductService productService, UserService userService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
        this.userService = userService;
    }

    // Basket

    public Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    // Basket Management

    public Basket getBasketByUserId(Long userId) {
        return basketRepository.findByUser_Id(userId);
    }

    public Basket addProductToBasket(Long productId, Long userId, int quantity) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Basket basket = new Basket();
        basket.setProduct(product);
        basket.setUser(user);
        basket.setQuantity(quantity);
        return basketRepository.save(basket);
    }

    public void removeProductFromBasket(Long productId, Long userId) {
        basketRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public void updateProductQuantity(Long userId, Long productId, int newQuantity) {
        Basket basket = getBasketByUserId(userId);
        if (!basket.getProduct().getId().equals(productId)) {
            throw new RuntimeException("Product not found in the basket for this user : " + userId);
        }
        basket.setQuantity(newQuantity);
        basketRepository.save(basket);
    }

    public void clearBasket(Long userId) {
        Basket basket = getBasketByUserId(userId);
        if (basket.getQuantity() > 0) {
            basket.setQuantity(0);
            basketRepository.save(basket);
        }
    }

    // Basket Summary

    public double calculateBasketTotal(Long userId) {
        Basket basket = basketRepository.findByUser_Id(userId);
        if (basket == null || basket.getQuantity() == 0) {
            return 0;
        }
        return basket.getQuantity() * basket.getProduct().getPrice();
    }
}
