package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
