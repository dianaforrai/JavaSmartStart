package com.gloeats;

import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class OrderUpdatesConsumer {

    public static void main(String[] args) {
        String topicName = "OrderUpdates";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "order-consumer-group");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Restaurant: %s, Update: %s, Partition: %d, Offset: %d%n",
                            record.key(), record.value(), record.partition(), record.offset());
                }
                consumer.commitSync();
            }
        } finally {
            consumer.close();
        }
    }
}
