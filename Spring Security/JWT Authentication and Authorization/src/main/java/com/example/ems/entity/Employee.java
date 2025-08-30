package com.example.ems.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private String department;
    private Double salary;

    @Column(unique = true)
    private String username;

    private String password;
    private String role = "ROLE_USER";

    // Getters and Setters
}
