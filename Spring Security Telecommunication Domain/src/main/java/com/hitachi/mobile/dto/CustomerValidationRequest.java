package com.hitachi.mobile.dto;

import jakarta.validation.constraints.*;

public class CustomerValidationRequest {
    @NotBlank(message = "Aadhaar number is required")
    @Pattern(regexp = "\\d{12}", message = "Aadhaar number should be 12 digits")
    private String aadhaarNumber;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "First name should contain only alphabets, max 15 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "[A-Za-z]{1,15}", message = "Last name should contain only alphabets, max 15 characters")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    // Constructors
    public CustomerValidationRequest() {}

    // Getters and setters
    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}