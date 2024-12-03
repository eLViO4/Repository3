package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.BasketProduct;
import com.interShop.interShop.Entity.Order;
import com.interShop.interShop.Entity.User;
import com.interShop.interShop.Repository.BasketRepository;
import com.interShop.interShop.Repository.OrderRepository;
import com.interShop.interShop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository repository, BasketRepository basketRepository, OrderRepository orderRepository) {
        this.userRepository = repository;
        this.basketRepository = basketRepository;
        this.orderRepository = orderRepository;
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

    public void deleteUser(Long userId) {
        List<Order> userOrders = orderRepository.findByUserId(userId);
        orderRepository.deleteAll(userOrders);
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        if (user.getBasket() != null) {
            basketRepository.delete(user.getBasket());
        }
        userRepository.deleteById(userId);
    }



    // Order & Basket
    public List<BasketProduct> getUserBasket(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь на найден"));
        Basket basket = user.getBasket();
        return basket.getProducts();
    }

}
