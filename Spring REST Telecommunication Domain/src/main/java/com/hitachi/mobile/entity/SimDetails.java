package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "sim_details")
public class SimDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simId;

    @Column(name = "service_number", nullable = false, unique = true)
    @Pattern(regexp = "\\d{10}", message = "Service number must be 10 digits")
    private String serviceNumber;

    @Column(name = "sim_number", nullable = false)
    @NotBlank(message = "SIM number is required")
    private String simNumber;

    @Column(name = "sim_status", nullable = false)
    @Pattern(regexp = "active|inactive", message = "Status must be active or inactive")
    private String simStatus = "inactive";

    // Constructors
    public SimDetails() {}

    public SimDetails(String serviceNumber, String simNumber, String simStatus) {
        this.serviceNumber = serviceNumber;
        this.simNumber = simNumber;
        this.simStatus = simStatus;
    }

    // Getters and Setters
    public Long getSimId() { return simId; }
    public void setSimId(Long simId) { this.simId = simId; }

    public String getServiceNumber() { return serviceNumber; }
    public void setServiceNumber(String serviceNumber) { this.serviceNumber = serviceNumber; }

    public String getSimNumber() { return simNumber; }
    public void setSimNumber(String simNumber) { this.simNumber = simNumber; }

    public String getSimStatus() { return simStatus; }
    public void setSimStatus(String simStatus) { this.simStatus = simStatus; }
}
