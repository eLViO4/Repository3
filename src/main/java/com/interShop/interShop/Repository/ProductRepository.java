package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByNameContainingIgnoreCase(String name);
}
