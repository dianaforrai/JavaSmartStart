package com.globallogic.hitachi.repository;

import com.globallogic.hitachi.entity.Customer;
import com.globallogic.hitachi.exception.CustomerDoesNotExistException;
import com.globallogic.hitachi.exception.CustomerTableEmptyException;
import java.math.BigInteger;
import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll() throws CustomerTableEmptyException;
    List<Customer> findByCity(String city);
    Customer findById(BigInteger id) throws CustomerDoesNotExistException;
    void updateCustomerAddress(BigInteger customerId, String newCity) throws CustomerDoesNotExistException;
}