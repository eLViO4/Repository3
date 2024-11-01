package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    // Basket Management
  //  Basket getBasketByUserId(Long userId);
  //  void addProductToBasket(Long userId, Long productId, int quantity);
  //  void updateProductQuantity(Long userId, Long productId, int newQuantity);

   /* public void removeProductFromBasket(Long productId, Long userId) {
            basketRepository.deleteProductById(productId, userId);
        }*/

  //  void clearBasket(Long userId);

    public Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }



    // Basket Summary
   // double calculateBasketTotal(Long userId);
}
