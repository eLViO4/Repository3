package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}

