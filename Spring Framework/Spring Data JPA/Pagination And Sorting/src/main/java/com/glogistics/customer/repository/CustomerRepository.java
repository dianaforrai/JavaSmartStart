package com.glogistics.customer.repository;

import com.glogistics.customer.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    // Fetch customers with gmail.com emails
    @Query("SELECT c FROM Customer c WHERE c.custEmail LIKE %:domain%")
    List<Customer> findCustomersByEmailDomain(@Param("domain") String domain);

    // Optional: Spring Data JPA Query Method for sorting/pagination
    List<Customer> findByCustCity(String custCity);

    Customer save(Customer customer);
}
