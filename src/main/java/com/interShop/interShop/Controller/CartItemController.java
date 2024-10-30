package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.CartItems;
import com.interShop.interShop.Entity.Products;
import com.interShop.interShop.Entity.Users;
import com.interShop.interShop.Service.CartItemService;
import com.interShop.interShop.Service.ProductService;
import com.interShop.interShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intershop/cart")
public class CartItemController {
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartItemController(CartItemService cartItemService, ProductService productService, UserService userService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public List<CartItems> getAllCartItems() {
        return cartItemService.findAllCartItems();
    }

    @GetMapping("/{id}")
    public Optional<CartItems> getCartItemById(@PathVariable Long id) {
        return cartItemService.findCartItemById(id);
    }

    @PostMapping
    public CartItems createCartItem(@RequestBody CartItems cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @PostMapping("/add")
    public CartItems addToCart(@RequestParam Long productId, @RequestParam Long userId, @RequestParam int quantity) {
        Products product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        Users user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        CartItems cartItem = new CartItems(null, quantity, user, product);
        return cartItemService.saveCartItem(cartItem);
    }


    @PutMapping
    public CartItems updateCartItem(@RequestBody CartItems cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItemById(id);
    }

}
