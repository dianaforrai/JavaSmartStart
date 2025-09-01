package com.example.ems.repo;

import com.example.ems.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        // Seed some fake employees
        employees.add(new Employee(1L, "Alice Johnson", "Manager", "alice@example.com"));
        employees.add(new Employee(2L, "Bob Smith", "Developer", "bob@example.com"));
        employees.add(new Employee(3L, "Carol Lee", "QA Engineer", "carol@example.com"));
    }

    public List<Employee> findAll() {
        return employees;
    }
}
