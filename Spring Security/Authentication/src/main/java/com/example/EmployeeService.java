package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee) {
        // Hash password before saving
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole("ROLE_USER");
        return employeeRepository.save(employee);
    }
}