package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
   /* void deleteProductById(Long productId, Long userId);*/
}

