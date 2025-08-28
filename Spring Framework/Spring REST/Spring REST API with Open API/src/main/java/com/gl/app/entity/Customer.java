package com.gl.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerName;

    @Column(unique = true)
    private String customerEmail;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Freight> freights = new HashSet<>();
}
