package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Order_Item;
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

    public List<Order_Item> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<Order_Item> findOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public Order_Item saveOrderItem(Order_Item orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
