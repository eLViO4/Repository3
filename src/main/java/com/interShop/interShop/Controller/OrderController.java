package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Entity.Order;
import com.interShop.interShop.Entity.Order_Item;
import com.interShop.interShop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intershop/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/allProducts/{orderId}")
    public List<Order_Item> getOrderProducts(@PathVariable Long orderId) {
        return orderService.getAllOrderItems(orderId);
    }

    @GetMapping("/status/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId) {
        return orderService.getOrderStatus(orderId);
    }

    @PutMapping("/updateStatus/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @GetMapping("/date/{orderId}")
    public LocalDateTime getOrderDate(@PathVariable Long orderId) {
        return orderService.getOrderDate(orderId);
    }

    @GetMapping("/total/{orderId}")
    public double getOrderPrice(@PathVariable Long orderId) {
        return orderService.getOrderPrice(orderId);
    }




}
