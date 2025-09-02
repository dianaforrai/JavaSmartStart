package com.gloeats.kafka;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.Random;

public class OrderStatusProducer {
    public static void main(String[] args) {
        String topic = "OrderStatusUpdates";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9094,localhost:9096");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        String[] statuses = {"Order Placed", "Order Confirmed", "Order Preparing", "Order Out for Delivery", "Order Delivered"};
        String[] restaurants = {"Restaurant1", "Restaurant2", "Restaurant3"};

        Random rand = new Random();
        int orderId = 1001;

        for (int i = 0; i < 15; i++) {
            String restaurant = restaurants[rand.nextInt(restaurants.length)];
            String status = statuses[rand.nextInt(statuses.length)];
            String key = restaurant;
            String value = "OrderID:" + orderId + ", Status:" + status;

            producer.send(new ProducerRecord<>(topic, key, value), (metadata, e) -> {
                if (e == null) {
                    System.out.printf("Produced: key=%s value=%s partition=%d offset=%d%n",
                            key, value, metadata.partition(), metadata.offset());
                } else {
                    e.printStackTrace();
                }
            });
            orderId++;
        }

        producer.close();
    }
}
