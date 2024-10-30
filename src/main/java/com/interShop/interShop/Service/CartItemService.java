package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.CartItems;
import com.interShop.interShop.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItems> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItems> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    public CartItems saveCartItem(CartItems cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
