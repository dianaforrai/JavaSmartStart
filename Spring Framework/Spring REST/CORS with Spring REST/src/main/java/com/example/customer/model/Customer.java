package com.example.customer.model;

public class Customer {

    private Long id;
    private String name;
    private int age;
    private String email;
    private String phoneNo;

    // Constructors
    public Customer() {}
    public Customer(Long id, String name, int age, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
}
