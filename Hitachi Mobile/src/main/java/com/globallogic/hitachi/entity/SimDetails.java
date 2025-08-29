package com.globallogic.hitachi.entity;

import jakarta.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "sim_details")
public class SimDetails {

    @Id
    @Column(name = "sim_id")
    private Integer simId;

    @Column(name = "service_number", nullable = false)
    private BigInteger serviceNumber;

    @Column(name = "sim_number", nullable = false)
    private BigInteger simNumber;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    // Default constructor
    public SimDetails() {}

    // Parameterized constructor
    public SimDetails(Integer simId, BigInteger serviceNumber, BigInteger simNumber, String status) {
        this.simId = simId;
        this.serviceNumber = serviceNumber;
        this.simNumber = simNumber;
        this.status = status;
    }

    // Getters and Setters
    public Integer getSimId() { return simId; }
    public void setSimId(Integer simId) { this.simId = simId; }

    public BigInteger getServiceNumber() { return serviceNumber; }
    public void setServiceNumber(BigInteger serviceNumber) { this.serviceNumber = serviceNumber; }

    public BigInteger getSimNumber() { return simNumber; }
    public void setSimNumber(BigInteger simNumber) { this.simNumber = simNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "SimDetails{" +
                "simId=" + simId +
                ", serviceNumber=" + serviceNumber +
                ", simNumber=" + simNumber +
                ", status='" + status + '\'' +
                '}';
    }
}