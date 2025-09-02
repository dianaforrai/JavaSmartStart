package com.gloeats.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public OrderStatusProducer(KafkaTemplate<String, String> kafkaTemplate,
                               @Value("${gloeats.kafka.topic}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendOrderStatus(String orderId, String status) {
        String message = "OrderID: " + orderId + ", Status: " + status;
        kafkaTemplate.send(topicName, orderId, message);
        System.out.println("Produced: " + message);
    }
}
