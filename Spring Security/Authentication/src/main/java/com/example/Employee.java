package com.example;

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

    private String role = "ROLE_USER"; // default role

    public void setPassword(String encode) {
        this.password = encode;
    }
    public String getPassword() {
        return password;
    }

    public void setRole(String roleUser) {
        this.role = roleUser;
    }
    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}