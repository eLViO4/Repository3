package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.*;
import com.interShop.interShop.Repository.BasketRepository;
import com.interShop.interShop.Repository.OrderItemRepository;
import com.interShop.interShop.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final OrderItemRepository orderItemRepository;
    private final BasketRepository basketRepository;

    public OrderService(OrderRepository orderRepository, BasketService basketService, OrderItemRepository orderItemRepository, BasketRepository basketRepository) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
        this.orderItemRepository = orderItemRepository;
        this.basketRepository = basketRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order createOrder(Long userId) {
        System.out.println("Fetching basket for userId: " + userId);
        Basket basket = basketService.getBasketByUserId(userId);
        if (basket == null) {
            throw new RuntimeException("Корзина не найдена для userId: " + userId);
        }
        if (basket.getProducts().isEmpty()) {
            throw new RuntimeException("Корзина пуста. Невозможно создать заказ.");
        }

        Order order = new Order();
        order.setUser(basket.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Ожидание");

        double totalPrice = basketService.calculateBasketTotal(userId);
        order.setPrice(totalPrice);

        for (BasketProduct basketProduct : basket.getProducts()) {
            Order_Item orderItem = new Order_Item();
            orderItem.setOrder(order);
            orderItem.setProduct(basketProduct.getProduct());
            orderItem.setQuantity(basketProduct.getQuantity());
            order.getOrderItems().add(orderItem);
        }
        orderRepository.save(order);
        basketService.clearBasket(userId);
        return order;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
    }

    public String getOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
        return order.getStatus();
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Order_Item> getAllOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
        return order.getOrderItems();
    }

    public double getOrderPrice(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
        return order.getPrice();
    }

    public LocalDateTime getOrderDate(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ с ID: " + orderId + " не найден"));
        return order.getOrderDate();
    }


}

