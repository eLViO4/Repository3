package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Authentication

    public void registerUser(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
        repository.save(user);
    }

    public User login(String email, String password) {
        User user1 = repository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email not found"));
        if (!user1.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return user1;
    }
    //void logout(Long userId);


    // Profile management

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User saveUser(User user) {
        if (user.getId() != null && repository.findById(user.getId()).isPresent()) {
            return repository.save(user);
        } else if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
        return repository.save(user);
    }


    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

     // Order & Basket
    public Basket getUserBasket(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getBasket();
    }

}
