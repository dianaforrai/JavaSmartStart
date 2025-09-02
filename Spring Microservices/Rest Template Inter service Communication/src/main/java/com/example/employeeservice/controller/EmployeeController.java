package com.example.employeeservice.controller;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public APIResponseDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
}
