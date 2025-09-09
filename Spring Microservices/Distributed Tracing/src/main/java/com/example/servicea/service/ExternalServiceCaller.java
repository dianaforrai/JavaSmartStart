package com.example.servicea.service;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceCaller {

    private final RestTemplate restTemplate;

    public ExternalServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Observed(name = "call-service-b") // Micrometer observation
    public String callServiceB() {
        return restTemplate.getForObject("http://localhost:8081/api/data", String.class);
    }
}
