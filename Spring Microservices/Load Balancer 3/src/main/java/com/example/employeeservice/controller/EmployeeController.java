package com.example.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/employees-with-departments")
    public String getEmployeesWithDepartments() {
        // Use service name, not hardcoded URL
        String deptResponse = restTemplate.getForObject(
                "http://department-service/departments",
                String.class
        );

        return "Employee Service -> " + deptResponse;
    }
}
