package com.example.kafka;

import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class CustomProducer {
    public static void main(String[] args) {
        String topicName = "custom-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "com.example.kafka.CustomPartitioner");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 1; i <= 10; i++) {
            String key = (i % 2 == 0) ? "A-Key-" + i : "B-Key-" + i;
            String value = "Message-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Produced record: key=%s value=%s partition=%d offset=%d%n",
                            key, value, metadata.partition(), metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });
        }

        producer.close();
    }
}
