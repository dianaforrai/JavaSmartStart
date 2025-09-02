package com.techlogics.parking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlogics.parking.config.KafkaConfig;
import com.techlogics.parking.model.ParkingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ParkingDataProducer {

    private static final Logger logger = LoggerFactory.getLogger(ParkingDataProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${parking.max.capacity:50}")
    private int maxCapacity;

    private final Random random = new Random();
    private final String[] locations = {"North Block", "South Block", "East Block", "West Block", "Central Area"};

    @Scheduled(fixedRate = 3000) // Every 3 seconds
    public void generateAndPublishParkingData() {
        try {
            // Generate random parking data for different areas
            for (int i = 0; i < locations.length; i++) {
                String parkingAreaId = "AREA-" + (i + 1);
                int availableSpaces = random.nextInt(maxCapacity + 1); // 0 to maxCapacity

                ParkingData parkingData = new ParkingData(
                        parkingAreaId,
                        availableSpaces,
                        maxCapacity,
                        locations[i]
                );

                String jsonMessage = objectMapper.writeValueAsString(parkingData);

                kafkaTemplate.send(KafkaConfig.LIVE_AVAILABLE_FREE_SPACE_TOPIC, parkingAreaId, jsonMessage)
                        .addCallback(
                                result -> logger.debug("Sent parking data for area {}: {}", parkingAreaId, availableSpaces),
                                failure -> logger.error("Failed to send parking data for area {}: {}", parkingAreaId, failure.getMessage())
                        );
            }
        } catch (JsonProcessingException e) {
            logger.error("Error serializing parking data: {}", e.getMessage());
        }
    }
}