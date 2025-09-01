package com.hitachi.mobile.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "sim_offers")
public class SimOffers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @Column(name = "call_qty", nullable = false)
    private Integer callQty;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "data_qty", nullable = false)
    private Integer dataQty;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "offer_name", nullable = false)
    private String offerName;

    @ManyToOne
    @JoinColumn(name = "sim_id", nullable = false)
    private SimDetails simDetails;

    // Constructors
    public SimOffers() {}

    public SimOffers(Integer callQty, Double cost, Integer dataQty, Integer duration,
                     String offerName, SimDetails simDetails) {
        this.callQty = callQty;
        this.cost = cost;
        this.dataQty = dataQty;
        this.duration = duration;
        this.offerName = offerName;
        this.simDetails = simDetails;
    }

    // Getters and Setters
    public Long getOfferId() { return offerId; }
    public void setOfferId(Long offerId) { this.offerId = offerId; }

    public Integer getCallQty() { return callQty; }
    public void setCallQty(Integer callQty) { this.callQty = callQty; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Integer getDataQty() { return dataQty; }
    public void setDataQty(Integer dataQty) { this.dataQty = dataQty; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getOfferName() { return offerName; }
    public void setOfferName(String offerName) { this.offerName = offerName; }

    public SimDetails getSimDetails() { return simDetails; }
    public void setSimDetails(SimDetails simDetails) { this.simDetails = simDetails; }
}