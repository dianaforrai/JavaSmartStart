package com.glogistics.customer.repository;

import com.glogistics.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustCity(String city);
    Customer findByCustEmail(String email);
}
