package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUser_Id(Long userId);
}
