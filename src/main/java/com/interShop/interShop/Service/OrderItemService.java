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


    public List<Order_Item> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrder_Id(orderId);
    }

    public Order_Item saveOrderItem(Order_Item orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }

    public void updateQuantity(Long orderId, Long productId, Integer quantity) {
        Order_Item orderItem = orderItemRepository.findByOrder_IdAndProduct_Id(orderId, productId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found for this order and product"));
        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);
    }
}
