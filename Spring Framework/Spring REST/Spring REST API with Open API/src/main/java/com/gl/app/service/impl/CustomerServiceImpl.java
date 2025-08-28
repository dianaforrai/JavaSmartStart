package com.gl.app.service.impl;

import com.gl.app.entity.Customer;
import com.gl.app.exception.ResourceNotFoundException;
import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.CustomerResponse;
import com.gl.app.repository.CustomerRepository;
import com.gl.app.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            ModelMapper modelMapper
    ) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    // map to dto
    private CustomerDto mapToDto(Customer customer){
        return modelMapper.map(customer,CustomerDto.class);
    }

    // map to entity
    private Customer mapToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto,Customer.class);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer created = customerRepository.save(customer);
        return mapToDto(created);
    }

    @Override
    public CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        Customer updated = customerRepository.save(customer);
        return mapToDto(updated);
    }

    @Override
    public CustomerResponse getCustomers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Customer> all = customerRepository.findAll(pageable);
        Set<CustomerDto> contents = all.getContent().stream().map(c -> mapToDto(c)).collect(Collectors.toSet());
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setContent(contents);
        customerResponse.setPageNo(all.getNumber());
        customerResponse.setPageSize(all.getSize());
        customerResponse.setTotalPages(all.getTotalPages());
        customerResponse.setTotalElements(all.getTotalElements());
        customerResponse.setLast(all.isLast());
        return customerResponse;
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
        return mapToDto(customer);
    }

    @Override
    public void removeCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
        customerRepository.delete(customer);
    }
}
