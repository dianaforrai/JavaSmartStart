package com.gl.app.service;

import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.CustomerResponse;
import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(long id,CustomerDto customerDto);
    CustomerResponse getCustomers(int pageNo, int pageSize, String sortBy, String sortDir);
    CustomerDto getCustomerById(long id);
    void removeCustomer(long id);
}
