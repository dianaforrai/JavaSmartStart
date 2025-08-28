package com.gl.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "freight")
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "origin",nullable = false)
    private String origin;

    @Column(name = "destination",nullable = false)
    private String destination;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
}
