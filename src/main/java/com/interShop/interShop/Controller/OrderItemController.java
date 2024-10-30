package com.interShop.interShop.Controller;

import com.interShop.interShop.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.interShop.interShop.Entity.Order_Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intershop/order_item")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<Order_Item> getAllOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public Optional<Order_Item> getOrderItemById(@PathVariable Long id) {
        return orderItemService.findOrderItemById(id);
    }

    @PostMapping
    public Order_Item createOrderItem(@RequestBody Order_Item orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @PutMapping
    public Order_Item updateOrderItem(@RequestBody Order_Item orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
    }

}
