package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/api/departments")
    public String getDepartments() {
        return "Departments served from port: " + port;
    }
}
