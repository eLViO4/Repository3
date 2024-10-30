package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
