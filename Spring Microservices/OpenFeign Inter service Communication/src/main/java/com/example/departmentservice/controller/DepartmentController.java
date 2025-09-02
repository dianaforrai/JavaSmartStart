package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/{departmentCode}")
    public DepartmentDto getDepartmentByCode(@PathVariable String departmentCode){
        Department dept = departmentRepository.findByDepartmentCode(departmentCode);
        return new DepartmentDto(dept.getDepartmentId(), dept.getDepartmentName(), dept.getDepartmentCode());
    }
}
