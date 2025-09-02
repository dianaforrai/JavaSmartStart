package com.techlogics.parking.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public static final String LIVE_AVAILABLE_FREE_SPACE_TOPIC = "live_avl_free_space";
    public static final String LIVE_AVAILABLE_EMPTY_SPACE_WARNING_TOPIC = "live_avbl_empty_space_warning";

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic liveAvailableFreeSpaceTopic() {
        return new NewTopic(LIVE_AVAILABLE_FREE_SPACE_TOPIC, 3, (short) 1);
    }

    @Bean
    public NewTopic liveAvailableEmptySpaceWarningTopic() {
        return new NewTopic(LIVE_AVAILABLE_EMPTY_SPACE_WARNING_TOPIC, 3, (short) 1);
    }
}