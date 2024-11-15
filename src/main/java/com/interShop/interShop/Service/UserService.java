package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    // Authentication

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Этот email уже зарегистрирован");
        }
        Basket newBasket = new Basket();
        newBasket.setUser(user);
        user.setBasket(newBasket);
        userRepository.save(user);
    }

    public User login(String email, String password) {
        User user1 = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Email не найден"));
        if (!user1.getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный пароль");
        }
        return user1;
    }

    //void logout(Long userId);


    // Profile management

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            return userRepository.save(user);
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Этот email уже зарегистрирован");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    // Order & Basket
    public Basket getUserBasket(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь на найден"));
        return user.getBasket();
    }

}
