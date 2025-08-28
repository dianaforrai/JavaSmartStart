package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address is required")
    @Size(max = 25, message = "Address should be maximum of 25 characters")
    private String address;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "City/State should not contain any special characters except space")
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank(message = "State is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "City/State should not contain any special characters except space")
    private String state;

    @Column(name = "pin_code", nullable = false)
    @Pattern(regexp = "\\d{6}", message = "Pin should be 6 digit number")
    private String pinCode;

    // Constructors
    public CustomerAddress() {}

    public CustomerAddress(String address, String city, String state, String pinCode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
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
}
