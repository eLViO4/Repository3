package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Service.BasketService;
import com.interShop.interShop.Service.ProductService;
import com.interShop.interShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intershop/basket")
public class BasketController {
    private final BasketService basketService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService, UserService userService) {
        this.basketService = basketService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping
    public Basket createBasket(@RequestBody Basket basket) {
        return basketService.saveBasket(basket);
    }

    @PostMapping("/add/{userId}")
    public void addProductToBasket(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        basketService.addProductToBasket(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{userId}")
    public void removeProductFromBasket(@PathVariable Long userId, @RequestParam Long productId) {
        basketService.removeProductFromBasket(userId, productId);
    }

    @PutMapping("/updateQuantity/{userId}")
    public void updateProductQuantity(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int newQuantity) {
        basketService.updateProductQuantity(userId, productId, newQuantity);
    }

    @GetMapping("/user/{userId}")
    public Basket getBasketByUserId(@PathVariable Long userId) {
        return basketService.getBasketByUserId(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearBasket(@PathVariable Long userId) {
        basketService.clearBasket(userId);
    }

    @GetMapping("/total/{userId}")
    public double calculateBasketTotal(@PathVariable Long userId) {
        return basketService.calculateBasketTotal(userId);
    }


}
