package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_id_number", unique = true, nullable = false)
    @Pattern(regexp = "\\d{12}", message = "Unique ID should be 12 digits")
    private String uniqueIdNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email_address", unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String emailAddress;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "First name should contain only alphabets, max 15 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "Last name should contain only alphabets, max 15 characters")
    private String lastName;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @Column(name = "state", nullable = false)
    private String state;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<SimDetails> simDetails = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public Customer() {}

    public Customer(String uniqueIdNumber, LocalDate dateOfBirth, String emailAddress,
                    String firstName, String lastName, String idType, String state) {
        this.uniqueIdNumber = uniqueIdNumber;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idType = idType;
        this.state = state;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUniqueIdNumber() { return uniqueIdNumber; }
    public void setUniqueIdNumber(String uniqueIdNumber) { this.uniqueIdNumber = uniqueIdNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public CustomerAddress getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(CustomerAddress customerAddress) { this.customerAddress = customerAddress; }

    public Set<SimDetails> getSimDetails() { return simDetails; }
    public void setSimDetails(Set<SimDetails> simDetails) { this.simDetails = simDetails; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String setState() {
        return state;
    }

}