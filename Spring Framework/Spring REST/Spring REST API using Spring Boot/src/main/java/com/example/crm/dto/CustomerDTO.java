package com.example.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 50)
    private String custName;

    @NotBlank(message = "Contact number is required")
    @Size(min = 10, max = 15)
    private String custContactNo;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String custEmail;

    @NotBlank(message = "City is required")
    private String custCity;

    public Object getCustName() {
        return custName;
    }
    public void setCustName(Object custName) {
        this.custName = (String) custName;
    }
    public Object getCustContactNo() {
        return custContactNo;
    }
    public void setCustContactNo(Object custContactNo) {
        this.custContactNo = (String) custContactNo;
    }
    public Object getCustEmail() {
        return custEmail;
    }
    public void setCustEmail(Object custEmail) {
        this.custEmail = (String) custEmail;
    }
    public Object getCustCity() {
        return custCity;
    }
    public void setCustCity(Object custCity) {
        this.custCity = (String) custCity;
    }

    // getters and setters
}
