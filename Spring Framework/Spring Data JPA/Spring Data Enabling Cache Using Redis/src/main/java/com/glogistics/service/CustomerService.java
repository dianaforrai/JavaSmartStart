package com.glogistics.service;

import com.glogistics.entity.Customer;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer getCustomer(Long custId);
}
