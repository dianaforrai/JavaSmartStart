package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/api/data")
    public String getData() throws InterruptedException {
        // Simulate processing time
        Thread.sleep(1000);
        return "Response from Service B";
    }
}
