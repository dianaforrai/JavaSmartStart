package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/api/data")
    public String getData() throws InterruptedException {
        // Simulate random failure or delay
        if (Math.random() < 0.5) {
            Thread.sleep(5000); // Delay to simulate timeout
        }
        return "Data from Service B";
    }
}
