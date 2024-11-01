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
        Basket basket = new Basket();
        basket.setProduct(product);
        basket.setUser(user);
        basket.setQuantity(quantity);
        return basketService.saveBasket(basket);
    }

    /*@DeleteMapping("/removeProduct")
    public String removeProductFromBasket(@RequestParam Long productId, @RequestParam Long userId) {
        basketService.removeProductFromBasket(productId, userId);
        return "Product removed from basket successfully.";
    }
*/

    @PutMapping
    public Basket updateBasket(@RequestBody Basket basket) {
        return basketService.saveBasket(basket);
    }


}
