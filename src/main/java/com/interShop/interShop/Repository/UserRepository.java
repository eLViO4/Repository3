package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
