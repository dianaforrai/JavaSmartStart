package com.gl.app.repository;

import com.gl.app.entity.Customer;
import com.gl.app.entity.Freight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}