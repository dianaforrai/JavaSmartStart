package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "address", nullable = false)
    @Size(max = 25, message = "Address should not exceed 25 characters")
    private String address;

    @Column(name = "city", nullable = false)
    @Pattern(regexp = "[A-Za-z\\s]+", message = "City should not contain special characters except space")
    private String city;

    @Column(name = "state", nullable = false)
    @Pattern(regexp = "[A-Za-z\\s]+", message = "State should not contain special characters except space")
    private String state;

    @Column(name = "pin_code", nullable = false)
    @Pattern(regexp = "\\d{6}", message = "PIN code should be 6 digits")
    private String pinCode;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Constructors
    public CustomerAddress() {}

    public CustomerAddress(String address, String city, String state, String pinCode, Customer customer) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.customer = customer;
    }

    // Getters and Setters
    public Long getAddressId() { return addressId; }
    public void setAddressId(Long addressId) { this.addressId = addressId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}