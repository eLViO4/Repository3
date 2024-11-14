package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    // @Query("SELECT b FROM Basket b WHERE b.user.id = :userId")
   Basket findByUser_Id(Long userId);

}

