package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "unique_id_number", nullable = false)
    @Pattern(regexp = "\\d{16}", message = "Id should be 16 digits")
    private String uniqueIdNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "email_address", nullable = false)
    @Email(message = "Invalid email format")
    private String emailAddress;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    @Size(max = 15, message = "First name/Last name should be a maximum of 15 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name and last names should accept only alphabets")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    @Size(max = 15, message = "First name/Last name should be a maximum of 15 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name and last names should accept only alphabets")
    private String lastName;

    @Column(name = "id_type", nullable = false)
    @NotBlank(message = "ID type is required")
    private String idType;

    @Column(name = "customer_address_id", nullable = false)
    private Long customerAddressId;

    @Column(name = "sim_id", nullable = false)
    private Long simId;

    @Column(name = "state", nullable = false)
    @NotBlank(message = "State is required")
    private String state;

    // Constructors
    public Customer() {}

    public Customer(String uniqueIdNumber, LocalDate dateOfBirth, String emailAddress,
                    String firstName, String lastName, String idType,
                    Long customerAddressId, Long simId, String state) {
        this.uniqueIdNumber = uniqueIdNumber;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idType = idType;
        this.customerAddressId = customerAddressId;
        this.simId = simId;
        this.state = state;
    }

    // Getters and Setters
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

    public Long getCustomerAddressId() { return customerAddressId; }
    public void setCustomerAddressId(Long customerAddressId) { this.customerAddressId = customerAddressId; }

    public Long getSimId() { return simId; }
    public void setSimId(Long simId) { this.simId = simId; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}
