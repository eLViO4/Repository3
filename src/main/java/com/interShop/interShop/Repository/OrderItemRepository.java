package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Order_Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<Order_Item, Long> {
    List<Order_Item> findByOrder_Id(Long orderId);
}
