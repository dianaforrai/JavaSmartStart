package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}