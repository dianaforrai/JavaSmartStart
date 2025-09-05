package com.example.departmentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @GetMapping("/departments")
    public String getDepartments() {
        return "List of Departments from port " + System.getProperty("server.port");
    }
}
