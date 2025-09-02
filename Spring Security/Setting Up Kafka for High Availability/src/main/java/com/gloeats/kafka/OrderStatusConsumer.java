package com.gloeats.kafka;

import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class OrderStatusConsumer {
    public static void main(String[] args) {
        String topic = "OrderStatusUpdates";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9094,localhost:9096");
        props.put("group.id", "order-consumer-group");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumed: key=%s value=%s partition=%d offset=%d%n",
                            record.key(), record.value(), record.partition(), record.offset());
                }
                consumer.commitSync();
            }
        } finally {
            consumer.close();
        }
    }
}
