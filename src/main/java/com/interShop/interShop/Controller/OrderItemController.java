package com.interShop.interShop.Controller;

import com.interShop.interShop.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.interShop.interShop.Entity.OrderItems;
import com.interShop.interShop.Service.OrderItemService;
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
    public List<OrderItems> getAllOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public Optional<OrderItems> getOrderItemById(@PathVariable Long id) {
        return orderItemService.findOrderItemById(id);
    }

    @PostMapping
    public OrderItems createOrderItem(@RequestBody OrderItems orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @PutMapping
    public OrderItems updateOrderItem(@RequestBody OrderItems orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
    }

}
