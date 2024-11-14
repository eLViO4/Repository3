package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.*;
import com.interShop.interShop.Repository.BasketRepository;
import com.interShop.interShop.Repository.OrderItemRepository;
import com.interShop.interShop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;


    @Autowired
    public OrderService(OrderRepository orderRepository, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order createOrder(Long userId) {
        Basket basket = basketService.getBasketByUserId(userId);

        Order order = new Order();
        order.setUser(basket.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Ожидание");

        double totalPrice = basketService.calculateBasketTotal(userId);
        order.setPrice(totalPrice);

        Order_Item orderItem = new Order_Item();
        orderItem.setOrder(order);
      //  orderItem.setProduct(basket.getProduct());
      //  orderItem.setQuantity(basket.getQuantity());

        order.getOrderItems().add(orderItem);

        orderRepository.save(order);
        basketService.clearBasket(userId);

        return order;
    }

    public Order getOrderByUserId(Long userId) {
        Order order = orderRepository.findByUser_Id(userId);
        if (order == null) {
            throw new RuntimeException("Заказ не найден для пользователя с ID: " + userId);
        }
        return order;
    }

    public String getOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ не найден с ID: " + orderId));
        return order.getStatus();
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Заказ не найден с ID: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

}

