package com.globallogic.hitachi.entity;

import jakarta.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "unique_id_number")
    private BigInteger uniqueIdNumber;

    @Column(name = "date_of_birth", nullable = false, length = 20)
    private String dateOfBirth;

    @Column(name = "email_address", nullable = false, length = 50)
    private String emailAddress;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "id_type", nullable = false, length = 20)
    private String idType;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(BigInteger uniqueIdNumber, String dateOfBirth, String emailAddress,
                    String firstName, String lastName, String idType, String city, String state) {
        this.uniqueIdNumber = uniqueIdNumber;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idType = idType;
        this.city = city;
        this.state = state;
    }

    // Getters and Setters
    public BigInteger getUniqueIdNumber() { return uniqueIdNumber; }
    public void setUniqueIdNumber(BigInteger uniqueIdNumber) { this.uniqueIdNumber = uniqueIdNumber; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "Customer{" +
                "uniqueIdNumber=" + uniqueIdNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}