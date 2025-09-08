package com.example.servicea.controller;

import com.example.servicea.service.ExternalServiceCaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ExternalServiceCaller externalServiceCaller;

    public TestController(ExternalServiceCaller externalServiceCaller) {
        this.externalServiceCaller = externalServiceCaller;
    }

    @GetMapping("/fetch-data")
    public String fetchData() {
        return externalServiceCaller.callExternalService();
    }
}
