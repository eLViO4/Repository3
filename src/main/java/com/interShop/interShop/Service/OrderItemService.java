package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.OrderItems;
import com.interShop.interShop.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItems> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItems> findOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItems saveOrderItem(OrderItems orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
