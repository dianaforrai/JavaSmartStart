package com.glo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long id;

    @Column(nullable = false)
    private String custName;

    @Column(nullable = false, unique = true)
    private String custContact;

    @Column(nullable = false)
    private String custAdd;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    // Constructors
    public Customer() {}

    public Customer(String custName, String custContact, String custAdd) {
        this.custName = custName;
        this.custContact = custContact;
        this.custAdd = custAdd;
    }

    // Getters and Setters
    public Long getCustId() { return id; }
    public void setCustId(Long custId) { this.id = custId; }

    public String getCustName() { return custName; }
    public void setCustName(String custName) { this.custName = custName; }

    public String getCustContact() { return custContact; }
    public void setCustContact(String custContact) { this.custContact = custContact; }

    public String getCustAdd() { return custAdd; }
    public void setCustAdd(String custAdd) { this.custAdd = custAdd; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        return String.format("Customer{id=%d, name='%s', contact='%s', address='%s'}",
                id, custName, custContact, custAdd);
    }
}