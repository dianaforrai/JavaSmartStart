package com.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void listen(Order order) {
        System.out.println("Received order: " + order.getOrderId());
        System.out.println("Generating notification for customer: " + order.getCustomerName());
        // Simulate notification logic
        System.out.println("Notification: Order " + order.getOrderId() + " is being processed.");
    }
}
