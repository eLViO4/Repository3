package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}