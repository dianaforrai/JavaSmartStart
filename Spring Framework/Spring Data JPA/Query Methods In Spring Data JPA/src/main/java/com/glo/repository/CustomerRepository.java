package com.glo.repository;

import com.glo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find by city
    List<Customer> findByCustCity(String custCity);

    // Find by name
    List<Customer> findByCustName(String custName);

    // Find by email
    Optional<Customer> findByCustEmail(String custEmail);

    // Find by contact number
    Optional<Customer> findByCustContactNo(String custContactNo);

    // Find by city and name
    List<Customer> findByCustCityAndCustName(String custCity, String custName);
}
