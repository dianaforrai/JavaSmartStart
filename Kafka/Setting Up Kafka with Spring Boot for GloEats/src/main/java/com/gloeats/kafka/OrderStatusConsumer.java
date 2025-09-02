package com.gloeats.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusConsumer {

    @KafkaListener(topics = "${gloeats.kafka.topic}", groupId = "order-status-group")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
        // Optional: Persist to database here
    }
}
