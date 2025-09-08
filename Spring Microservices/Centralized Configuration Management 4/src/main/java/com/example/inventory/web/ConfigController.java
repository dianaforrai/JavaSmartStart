package com.example.inventory.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${message:default-inventory-message}")
    private String message;

    @Value("${inventory.threshold:0}")
    private int threshold;

    @GetMapping("/api/config")
    public Map<String, Object> config() {
        return Map.of(
                "service", "inventory-service",
                "message", message,
                "inventory.threshold", threshold
        );
    }
}
