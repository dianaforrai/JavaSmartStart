package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "sim_details")
public class SimDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simId;

    @Column(name = "service_number", unique = true, nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Service number should be 10 digits")
    private String serviceNumber;

    @Column(name = "sim_number", unique = true, nullable = false)
    @Pattern(regexp = "\\d{13}", message = "SIM number should be 13 digits")
    private String simNumber;

    @Column(name = "sim_status", nullable = false)
    private String simStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "simDetails", cascade = CascadeType.ALL)
    private Set<SimOffers> simOffers = new HashSet<>();

    // Constructors
    public SimDetails() {}

    public SimDetails(String serviceNumber, String simNumber, String simStatus, Customer customer) {
        this.serviceNumber = serviceNumber;
        this.simNumber = simNumber;
        this.simStatus = simStatus;
        this.customer = customer;
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

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Set<SimOffers> getSimOffers() { return simOffers; }
    public void setSimOffers(Set<SimOffers> simOffers) { this.simOffers = simOffers; }
}