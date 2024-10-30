package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.Product;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Service.BasketService;
import com.interShop.interShop.Service.ProductService;
import com.interShop.interShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Basket> getAllBaskets() {
        return basketService.findAllBaskets();
    }

    @GetMapping("/{id}")
    public Optional<Basket> getBasketById(@PathVariable Long id) {
        return basketService.findBasketById(id);
    }

    @PostMapping
    public Basket createBasket(@RequestBody Basket basket) {
        return basketService.saveBasket(basket);
    }

    @PostMapping("/add")
    public Basket addToBasket(@RequestParam Long productId, @RequestParam Long userId, @RequestParam int quantity) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Basket basket = new Basket(null, quantity, user, product);
        return basketService.saveBasket(basket);
    }

    @PutMapping
    public Basket updateBasket(@RequestBody Basket basket) {
        return basketService.saveBasket(basket);
    }

    @DeleteMapping("/{id}")
    public void deleteBasket(@PathVariable Long id) {
        basketService.deleteBasketById(id);
    }

}
