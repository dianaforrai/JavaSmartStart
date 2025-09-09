package com.example.servicea.controller;

import com.example.servicea.service.ExternalServiceCaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ExternalServiceCaller serviceCaller;

    public TestController(ExternalServiceCaller serviceCaller) {
        this.serviceCaller = serviceCaller;
    }

    @GetMapping("/fetch-data")
    public String fetchData() {
        return serviceCaller.callServiceB();
    }
}
