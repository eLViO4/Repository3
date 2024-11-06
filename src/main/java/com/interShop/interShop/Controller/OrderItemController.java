package com.interShop.interShop.Controller;

import com.interShop.interShop.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.interShop.interShop.Entity.Order_Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/intershop/order_item")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public Order_Item createOrderItem(@RequestBody Order_Item orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @GetMapping("/order/{orderId}")
    public List<Order_Item> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
    }

    @PutMapping("/updateQuantity/order/{orderId}/product/{productId}")
    public void updateQuantity(@PathVariable Long orderId, @PathVariable Long productId, @RequestParam int quantity) {
        orderItemService.updateQuantity(orderId, productId, quantity);
    }
}
