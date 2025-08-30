package com.example.ems.service;

import com.example.ems.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee registerEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
}
