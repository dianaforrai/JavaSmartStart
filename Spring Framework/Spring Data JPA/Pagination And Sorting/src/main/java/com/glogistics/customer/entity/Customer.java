package com.glogistics.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    private String custName;
    private String custContactNo;
    private String custEmail;
    private String custCity;
    private String custAddress;

    public String getCustEmail() {
        return custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }
}
