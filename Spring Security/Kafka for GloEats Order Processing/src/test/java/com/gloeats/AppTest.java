package com.gloeats;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AppTest {

    @Test
    void testOrderUpdatesProducer() {
        // Mock KafkaProducer
        KafkaProducer<String, String> mockProducer = Mockito.mock(KafkaProducer.class);

        // Test data
        String topicName = "OrderUpdates";
        String key = "Restaurant1";
        String value = "OrderID: 101, Status: Order Placed";

        // Create a ProducerRecord
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

        // Simulate sending the record
        mockProducer.send(record);

        // Verify that the producer's send method was called with the correct record
        verify(mockProducer, times(1)).send(record);
    }

    @Test
    void testOrderUpdatesConsumer() {
        // Mock KafkaConsumer
        KafkaConsumer<String, String> mockConsumer = Mockito.mock(KafkaConsumer.class);

        // Test data
        String topicName = "OrderUpdates";
        ConsumerRecord<String, String> mockRecord = new ConsumerRecord<>(topicName, 0, 0, "Restaurant1", "OrderID: 101, Status: Order Placed");

        // Simulate polling records
        when(mockConsumer.poll(any())).thenReturn(new org.apache.kafka.clients.consumer.ConsumerRecords<>(Map.of(
                new org.apache.kafka.common.TopicPartition(topicName, 0), List.of(mockRecord)
        )));

        // Poll and verify the record
        org.apache.kafka.clients.consumer.ConsumerRecords<String, String> records = mockConsumer.poll(java.time.Duration.ofMillis(500));
        for (ConsumerRecord<String, String> record : records) {
            assertEquals("Restaurant1", record.key());
            assertEquals("OrderID: 101, Status: Order Placed", record.value());
        }

        // Verify that the consumer's poll method was called
        verify(mockConsumer, times(1)).poll(any());
    }
}