package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
