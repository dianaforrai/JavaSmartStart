package com.techlogics.parking.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlogics.parking.service.ParkingStreamsProcessor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    private StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/availability")
    public ResponseEntity<Map<String, Object>> getAllParkingAvailability() {
        try {
            KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();

            if (kafkaStreams == null || !kafkaStreams.state().isRunningOrRebalancing()) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of("error", "Kafka Streams is not ready"));
            }

            ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                    StoreQueryParameters.fromNameAndType(
                            ParkingStreamsProcessor.PARKING_STATE_STORE,
                            QueryableStoreTypes.keyValueStore()
                    )
            );

            Map<String, Object> result = new HashMap<>();
            result.put("timestamp", java.time.LocalDateTime.now().toString());
            result.put("parkingAreas", new HashMap<String, Object>());

            @SuppressWarnings("unchecked")
            Map<String, Object> parkingAreas = (Map<String, Object>) result.get("parkingAreas");

            store.all().forEachRemaining(keyValue -> {
                try {
                    JsonNode parkingData = objectMapper.readTree(keyValue.value);
                    Map<String, Object> areaInfo = new HashMap<>();
                    areaInfo.put("availableSpaces", parkingData.get("availableSpaces").asInt());
                    areaInfo.put("totalSpaces", parkingData.get("totalSpaces").asInt());
                    areaInfo.put("location", parkingData.get("location").asText());
                    areaInfo.put("lastUpdated", parkingData.get("timestamp").asText());

                    double occupancyRate = (double)(parkingData.get("totalSpaces").asInt() - parkingData.get("availableSpaces").asInt())
                            / parkingData.get("totalSpaces").asInt() * 100;
                    areaInfo.put("occupancyRate", Math.round(occupancyRate * 100.0) / 100.0);

                    parkingAreas.put(keyValue.key, areaInfo);
                } catch (Exception e) {
                    logger.error("Error processing parking data for key {}: {}", keyValue.key, e.getMessage());
                }
            });

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            logger.error("Error retrieving parking availability: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unable to retrieve parking data", "message", e.getMessage()));
        }
    }

    @GetMapping("/availability/{areaId}")
    public ResponseEntity<Map<String, Object>> getParkingAvailabilityByArea(@PathVariable String areaId) {
        try {
            KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();

            if (kafkaStreams == null || !kafkaStreams.state().isRunningOrRebalancing()) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of("error", "Kafka Streams is not ready"));
            }

            ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                    StoreQueryParameters.fromNameAndType(
                            ParkingStreamsProcessor.PARKING_STATE_STORE,
                            QueryableStoreTypes.keyValueStore()
                    )
            );

            String parkingDataJson = store.get(areaId);

            if (parkingDataJson == null) {
                return ResponseEntity.notFound().build();
            }

            JsonNode parkingData = objectMapper.readTree(parkingDataJson);
            Map<String, Object> result = new HashMap<>();
            result.put("areaId", areaId);
            result.put("availableSpaces", parkingData.get("availableSpaces").asInt());
            result.put("totalSpaces", parkingData.get("totalSpaces").asInt());
            result.put("location", parkingData.get("location").asText());
            result.put("lastUpdated", parkingData.get("timestamp").asText());

            double occupancyRate = (double)(parkingData.get("totalSpaces").asInt() - parkingData.get("availableSpaces").asInt())
                    / parkingData.get("totalSpaces").asInt() * 100;
            result.put("occupancyRate", Math.round(occupancyRate * 100.0) / 100.0);
            result.put("timestamp", java.time.LocalDateTime.now().toString());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error retrieving parking availability for area {}: {}", areaId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unable to retrieve parking data", "message", e.getMessage()));
        }
    }
}