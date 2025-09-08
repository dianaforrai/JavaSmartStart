package com.example.servicea.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceCaller {

    private final RestTemplate restTemplate;

    public ExternalServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "externalServiceCB", fallbackMethod = "fallbackForExternalService")
    public String callExternalService() {
        return restTemplate.getForObject("http://localhost:8081/api/data", String.class);
    }

    public String fallbackForExternalService(Throwable t) {
        return "Fallback response: External service unavailable!";
    }
}
