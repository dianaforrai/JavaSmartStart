package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_identity")
public class CustomerIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_id_number", unique = true, nullable = false)
    @Pattern(regexp = "\\d{12}", message = "Unique ID should be 12 digits")
    private String uniqueIdNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "First name should contain only alphabets, max 15 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "Last name should contain only alphabets, max 15 characters")
    private String lastName;

    @Column(name = "email_address", nullable = false)
    @Email(message = "Email should be valid")
    private String emailAddress;

    @Column(name = "state", nullable = false)
    private String state;

    // Constructors
    public CustomerIdentity() {}

    public CustomerIdentity(String uniqueIdNumber, LocalDate dateOfBirth, String firstName,
                            String lastName, String emailAddress, String state) {
        this.uniqueIdNumber = uniqueIdNumber;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.state = state;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUniqueIdNumber() { return uniqueIdNumber; }
    public void setUniqueIdNumber(String uniqueIdNumber) { this.uniqueIdNumber = uniqueIdNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}