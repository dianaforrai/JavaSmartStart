package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Customer {

    @Id
    private Long custId;

    @NotEmpty(message = "Customer name must not be empty")
    private String custName;

    @NotEmpty(message = "Contact number must not be empty")
    private String custContactNo;

    public Customer() {}

    public Customer(Long custId, String custName, String custContactNo) {
        this.custId = custId;
        this.custName = custName;
        this.custContactNo = custContactNo;
    }

    // Getters and setters
    public Long getCustId() { return custId; }
    public void setCustId(Long custId) { this.custId = custId; }
    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }
    public String getCustContactNo() { return custContactNo; }
    public void setCustContactNo(String custContactNo) { this.custContactNo = custContactNo; }
}
