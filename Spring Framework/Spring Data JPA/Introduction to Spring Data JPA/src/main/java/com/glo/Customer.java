package com.glo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @NotBlank(message = "Customer name is mandatory")
    @Size(min = 2, max = 50)
    private String custName;

    @NotBlank(message = "Contact number is mandatory")
    private String custContactNo;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String custEmail;

    @NotBlank(message = "City is mandatory")
    private String custCity;

    @NotBlank(message = "Address is mandatory")
    private String custAddress;

    // Constructors
    public Customer() {}

    public Customer(String custName, String custContactNo, String custEmail, String custCity, String custAddress) {
        this.custName = custName;
        this.custContactNo = custContactNo;
        this.custEmail = custEmail;
        this.custCity = custCity;
        this.custAddress = custAddress;
    }

    // Getters and Setters
    public Long getCustId() { return custId; }
    public void setCustId(Long custId) { this.custId = custId; }
    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }
    public String getCustContactNo() { return custContactNo; }
    public void setCustContactNo(String custContactNo) { this.custContactNo = custContactNo; }
    public String getCustEmail() { return custEmail; }
    public void setCustEmail(String custEmail) { this.custEmail = custEmail; }
    public String getCustCity() { return custCity; }
    public void setCustCity(String custCity) { this.custCity = custCity; }
    public String getCustAddress() { return custAddress; }
    public void setCustAddress(String custAddress) { this.custAddress = custAddress; }

    @Override
    public String toString() {
        return "Customer [custId=" + custId + ", custName=" + custName + ", custContactNo=" + custContactNo
                + ", custEmail=" + custEmail + ", custCity=" + custCity + ", custAddress=" + custAddress + "]";
    }
}
