package com.glo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customers by city
    List<Customer> findByCustCity(String custCity);

    // Optional: Find customers by email
    Customer findByCustEmail(String custEmail);
}
