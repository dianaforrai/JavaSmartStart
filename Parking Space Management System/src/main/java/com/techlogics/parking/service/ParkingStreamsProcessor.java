package com.techlogics.parking.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlogics.parking.config.KafkaConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class ParkingStreamsProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ParkingStreamsProcessor.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${parking.warning.threshold:10}")
    private int warningThreshold;

    public static final String PARKING_STATE_STORE = "parking-state-store";

    @Bean
    public KStream<String, String> processParkingStream(StreamsBuilder streamsBuilder) {

        // Create state store for parking data
        StoreBuilder<KeyValueStore<String, String>> storeBuilder = Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore(PARKING_STATE_STORE),
                Serdes.String(),
                Serdes.String()
        );
        streamsBuilder.addStateStore(storeBuilder);

        // Create stream from the main parking topic
        KStream<String, String> parkingStream = streamsBuilder
                .stream(KafkaConfig.LIVE_AVAILABLE_FREE_SPACE_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        // Store all parking data in state store
        parkingStream.process(() -> new ParkingStateStoreProcessor(PARKING_STATE_STORE), PARKING_STATE_STORE);
        KStream<String, String> warningStream = parkingStream
                .filter((key, value) -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(value);
                        int availableSpaces = jsonNode.get("availableSpaces").asInt();
                        boolean isLowAvailability = availableSpaces < warningThreshold;

                        if (isLowAvailability) {
                            logger.debug("Low parking availability detected for area {}: {} spaces", key, availableSpaces);
                        }

                        return isLowAvailability;
                    } catch (Exception e) {
                        logger.error("Error processing parking data: {}", e.getMessage());
                        return false;
                    }
                })
                .mapValues((key, value) -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(value);
                        // Add warning information to the message
                        ((com.fasterxml.jackson.databind.node.ObjectNode) jsonNode).put("warningType", "LOW_AVAILABILITY");
                        ((com.fasterxml.jackson.databind.node.ObjectNode) jsonNode).put("threshold", warningThreshold);
                        return objectMapper.writeValueAsString(jsonNode);
                    } catch (Exception e) {
                        logger.error("Error adding warning information: {}", e.getMessage());
                        return value;
                    }
                });

        // Publish warning messages to warning topic
        warningStream.to(KafkaConfig.LIVE_AVAILABLE_EMPTY_SPACE_WARNING_TOPIC,
                Produced.with(Serdes.String(), Serdes.String()));

        // Create a KTable for aggregated parking data (optional for advanced queries)
        KTable<String, String> parkingTable = streamsBuilder
                .table(KafkaConfig.LIVE_AVAILABLE_FREE_SPACE_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        // Log stream processing
        parkingStream.foreach((key, value) ->
                logger.debug("Processing parking data for area: {} -> {}", key, value));

        return parkingStream;
    }
}