package com.example.crm.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column(nullable = false)
    private String custName;

    @Column(nullable = false)
    private String custContactNo;

    @Column(nullable = false, unique = true)
    private String custEmail;

    @Column(nullable = false)
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

    public void setCustId(Long id) {
        this.custId = id;
    }
}
