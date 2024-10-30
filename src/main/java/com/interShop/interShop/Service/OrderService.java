package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Orders;
import com.interShop.interShop.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}

