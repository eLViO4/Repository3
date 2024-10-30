package com.interShop.interShop.Controller;

import com.interShop.interShop.Entity.Orders;
import com.interShop.interShop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Orders> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.saveOrder(order);
    }

    @PutMapping
    public Orders updateOrder(@RequestBody Orders order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

}
