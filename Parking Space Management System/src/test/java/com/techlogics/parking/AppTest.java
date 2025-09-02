package com.techlogics.parking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlogics.parking.model.ParkingData;
import com.techlogics.parking.service.ParkingDataProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" },
        topics = { "live_avl_free_space", "live_avbl_empty_space_warning" })
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.streams.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
class ParkingManagementSystemApplicationTests {

    @Autowired
    private ParkingDataProducer parkingDataProducer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertNotNull(parkingDataProducer);
        assertNotNull(objectMapper);
    }

    @Test
    void testParkingDataSerialization() throws Exception {
        ParkingData parkingData = new ParkingData("TEST-AREA", 25, 50, "Test Location");
        String json = objectMapper.writeValueAsString(parkingData);

        assertNotNull(json);

        ParkingData deserializedData = objectMapper.readValue(json, ParkingData.class);
        assertNotNull(deserializedData);
    }
}