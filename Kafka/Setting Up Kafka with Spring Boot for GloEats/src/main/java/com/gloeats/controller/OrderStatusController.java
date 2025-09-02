package com.gloeats.controller;

import com.gloeats.kafka.OrderStatusProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderStatusController {

    private final OrderStatusProducer producer;

    public OrderStatusController(OrderStatusProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/update/{orderId}/{status}")
    public String updateOrderStatus(@PathVariable String orderId, @PathVariable String status) {
        producer.sendOrderStatus(orderId, status);
        return "Order status update sent: OrderID=" + orderId + ", Status=" + status;
    }
}
