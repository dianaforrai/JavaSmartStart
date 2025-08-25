package com.glo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    private String custName;
    private String custContactNo;
    private String custEmail;
    private String custCity;
    private String custAddress;

    // Constructors
    public Customer() { }

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
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custContactNo='" + custContactNo + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custCity='" + custCity + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }

}
