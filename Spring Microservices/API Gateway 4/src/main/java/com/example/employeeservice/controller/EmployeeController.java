package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

    private final RestTemplate restTemplate;

    public EmployeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/employees")
    public String getEmployees() {
        // Call department-service via Eureka + LoadBalancer
        String departments = restTemplate.getForObject("http://department-service/api/departments", String.class);
        return "Employees served from Employee Service (8082) + " + departments;
    }
}
