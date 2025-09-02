package com.example.departmentservice.controller;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{departmentCode}")
    public Department getDepartmentByCode(@PathVariable String departmentCode) {
        return departmentService.getDepartmentByCode(departmentCode);
    }
}