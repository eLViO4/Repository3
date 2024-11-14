package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    Optional<BasketProduct> findByBasket_IdAndProduct_Id(Long basketId, Long productId);
    void deleteByBasket_Id(Long basketId);
}
