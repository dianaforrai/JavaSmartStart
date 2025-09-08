package com.example.microservicebus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {

    @Value("${message:default-bus-message}")
    private String message;

    @GetMapping("/api/config")
    public String getMessage() {
        return message;
    }
}
