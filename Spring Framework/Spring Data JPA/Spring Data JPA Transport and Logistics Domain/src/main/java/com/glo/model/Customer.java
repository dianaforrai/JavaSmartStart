package com.glo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "cust_name", nullable = false)
    private String custName;

    @Column(name = "cust_contact", nullable = false)
    private String custContact;

    @Column(name = "cust_add", nullable = false)
    private String custAdd;

    // Constructors
    public Customer() {}

    public Customer(String custName, String custContact, String custAdd) {
        this.custName = custName;
        this.custContact = custContact;
        this.custAdd = custAdd;
    }

    // Getters and Setters
    public Integer getCustId() { return custId; }
    public void setCustId(Integer custId) { this.custId = custId; }

    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }

    public String getCustContact() { return custContact; }
    public void setCustContact(String custContact) { this.custContact = custContact; }

    public String getCustAdd() { return custAdd; }
    public void setCustAdd(String custAdd) { this.custAdd = custAdd; }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custContact='" + custContact + '\'' +
                ", custAdd='" + custAdd + '\'' +
                '}';
    }
}