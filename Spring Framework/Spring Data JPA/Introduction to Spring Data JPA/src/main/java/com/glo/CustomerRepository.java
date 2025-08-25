package com.glo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // JpaRepository already provides CRUD methods:
    // save(), findById(), findAll(), deleteById(), etc.
}
