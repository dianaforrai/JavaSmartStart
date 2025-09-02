package com.techlogics.parking.service;

import com.techlogics.parking.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class ParkingDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ParkingDataConsumer.class);

    @KafkaListener(topics = KafkaConfig.LIVE_AVAILABLE_FREE_SPACE_TOPIC, groupId = "parking-metadata-group")
    public void consumeParkingDataWithMetadata(
            ConsumerRecord<String, String> record,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        LocalDateTime messageTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
        );

        logger.info("=== PARKING DATA METADATA ===");
        logger.info("Timestamp: {}", messageTime);
        logger.info("Partition: {}", partition);
        logger.info("Offset: {}", offset);
        logger.info("Key: {}", record.key());
        logger.info("Value: {}", record.value());
        logger.info("===============================");
    }

    @KafkaListener(topics = KafkaConfig.LIVE_AVAILABLE_EMPTY_SPACE_WARNING_TOPIC, groupId = "parking-warning-group")
    public void consumeWarningMessages(ConsumerRecord<String, String> record) {
        logger.warn(" LOW PARKING SPACE WARNING ");
        logger.warn("Area: {}", record.key());
        logger.warn("Warning Data: {}", record.value());
        logger.warn("Immediate attention required for parking area: {}", record.key());

        // Here you could add additional logic like:
        // - Send email notifications
        // - Update dashboard alerts
        // - Trigger automated responses
    }
}