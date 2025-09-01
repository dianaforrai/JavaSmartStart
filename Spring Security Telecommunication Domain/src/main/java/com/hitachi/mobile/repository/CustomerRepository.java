package com.hitachi.mobile.repository;

import com.hitachi.mobile.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUniqueIdNumber(String uniqueIdNumber);
    Optional<Customer> findByEmailAddress(String emailAddress);
    boolean existsByUniqueIdNumber(String uniqueIdNumber);
    boolean existsByEmailAddress(String emailAddress);
}