package com.gloeats;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class OrderUpdatesProducer {

    public static void main(String[] args) {
        String topicName = "OrderUpdates";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Sample orders
        String[] restaurants = {"Restaurant1", "Restaurant2"};
        String[] statuses = {"Order Placed", "Order Confirmed", "Order Delivered"};

        int orderId = 101;

        for (String restaurant : restaurants) {
            for (String status : statuses) {
                String key = restaurant;
                String value = "OrderID: " + orderId + ", Status: " + status;

                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

                producer.send(record, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.printf("Produced record: key=%s value=%s partition=%d offset=%d%n",
                                key, value, metadata.partition(), metadata.offset());
                    } else {
                        exception.printStackTrace();
                    }
                });

                orderId++;
            }
        }

        producer.close();
    }
}
