package com.interShop.interShop.Repository;

import com.interShop.interShop.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
