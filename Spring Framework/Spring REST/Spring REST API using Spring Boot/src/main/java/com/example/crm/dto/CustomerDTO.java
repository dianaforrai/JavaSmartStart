package com.example.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotBlank(message = "{customer.name.notblank}")
    private String custName;

    @NotBlank(message = "{customer.contact.notblank}")
    private String custContactNo;

    @NotBlank(message = "{customer.email.notblank}")
    @Email(message = "{customer.email.invalid}")
    private String custEmail;

    @NotBlank(message = "{customer.city.notblank}")
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
