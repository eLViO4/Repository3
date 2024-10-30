package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Order_Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order_Item, Long> {
}
