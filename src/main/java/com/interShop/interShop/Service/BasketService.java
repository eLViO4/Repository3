package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.BasketProduct;
import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Repository.BasketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addProductToBasket(Long basketId, Long productId, int quantity) {
        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<BasketProduct> existingProduct = basket.getProducts().stream()
                .filter(basketProduct -> basketProduct.getProduct().equals(product))
                .findFirst();

        if (existingProduct.isPresent()) {
            BasketProduct basketProduct = existingProduct.get();
            basketProduct.setQuantity(basketProduct.getQuantity() + quantity);
        } else {
            BasketProduct newBasketProduct = new BasketProduct();
            newBasketProduct.setProduct(product);
            newBasketProduct.setQuantity(quantity);
            newBasketProduct.setBasket(basket);
            basket.getProducts().add(newBasketProduct);
        }
        basketRepository.save(basket);
    }


    @Transactional
    public void removeProductFromBasket(Long userId, Long productId) {
        Basket basket = getBasketByUserId(userId);

        Optional<BasketProduct> productToRemove = basket.getProducts().stream()
                .filter(basketProduct -> basketProduct.getProduct().getId().equals(productId))
                .findFirst();

        if (productToRemove.isPresent()) {
            basket.getProducts().remove(productToRemove.get());
            basketRepository.save(basket);
        } else {
            throw new RuntimeException("Product not found in basket");
        }
    }


    public void updateProductQuantity(Long userId, Long productId, int newQuantity) {
        Basket basket = getBasketByUserId(userId);
        basket.getProducts().stream()
                .filter(basketProduct -> basketProduct.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(basketProduct -> basketProduct.setQuantity(newQuantity));
        basketRepository.save(basket);
    }

    @Transactional
    public void clearBasket(Long userId) {
        Basket basket = getBasketByUserId(userId);
        basket.getProducts().clear();
        basketRepository.save(basket);
    }


    // Basket Summary
    public double calculateBasketTotal(Long userId) {
        Basket basket = getBasketByUserId(userId);
        return basket.getProducts().stream()
                .mapToDouble(basketProduct -> basketProduct.getProduct().getPrice() * basketProduct.getQuantity())
                .sum();
    }
}
