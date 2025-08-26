package com.glogistics.customer.repository;

import com.glogistics.customer.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    // Fetch customers whose email ends with a specific domain
    List<Customer> findByCustEmailEndingWith(String domain);

    Customer save(Customer customer);

    Object findAll();
}
