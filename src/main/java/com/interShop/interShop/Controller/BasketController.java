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

    @PostMapping("/add")
    public Basket addProductToBasket(@RequestParam Long productId, @RequestParam Long userId, @RequestParam int quantity) {
        return basketService.addProductToBasket(productId, userId, quantity);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeProductFromBasket(@PathVariable Long productId, @RequestParam Long userId) {
        basketService.removeProductFromBasket(productId, userId);
    }

    @PutMapping("/updateQuantity/{productId}")
    public void updateQuantity(@RequestParam Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        basketService.updateProductQuantity(userId, productId, quantity);
    }

    @GetMapping("/user/{userId}")
    public Basket getBasketByUserId(@PathVariable Long userId) {
        return basketService.getBasketByUserId(userId);
    }

    @DeleteMapping("/clear")
    public void clearBasket(@RequestParam Long userId) {
        basketService.clearBasket(userId);
    }

    @GetMapping("/total")
    public double getBasketTotal(@RequestParam Long userId) {
        return basketService.calculateBasketTotal(userId);
    }

}
