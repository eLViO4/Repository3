package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Order;
import com.interShop.interShop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/intershop/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public Order createOrder(@RequestParam Long userId) {
        return orderService.createOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public Order getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    @GetMapping("/status/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId) {
        return orderService.getOrderStatus(orderId);
    }

    @PutMapping("/updateStatus/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

}
