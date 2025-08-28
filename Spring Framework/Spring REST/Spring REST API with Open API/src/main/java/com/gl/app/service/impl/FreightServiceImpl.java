package com.gl.app.service.impl;

import com.gl.app.entity.Customer;
import com.gl.app.entity.Freight;
import com.gl.app.exception.FreightAPIException;
import com.gl.app.exception.ResourceNotFoundException;
import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.FreightDto;
import com.gl.app.repository.CustomerRepository;
import com.gl.app.repository.FreightRepository;
import com.gl.app.service.CustomerService;
import com.gl.app.service.FreightService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreightServiceImpl implements FreightService {

    private final FreightRepository freightRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public FreightServiceImpl(FreightRepository freightRepository,
                              CustomerRepository customerRepository,
                              ModelMapper modelMapper) {
        this.freightRepository = freightRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    // map to dto
    private FreightDto mapToDto(Freight freight){
        return modelMapper.map(freight,FreightDto.class);
    }

    // map to entity
    private Freight mapToEntity(FreightDto freightDto){
        return modelMapper.map(freightDto,Freight.class);
    }

    // map customerDTO to customer entity
    private Customer mapToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto,Customer.class);
    }

    // map customer entity to customerDTO
    private CustomerDto mapToDto(Customer customer){
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public FreightDto createFreight(CustomerDto customerDto, FreightDto freightDto) {
        Freight freight = mapToEntity(freightDto);
        Customer customer = mapToEntity(customerDto);
        freight.setCustomer(customer);
        Freight created = freightRepository.save(freight);
        return mapToDto(created);
    }

    @Override
    public List<FreightDto> getFreightByCustomerId(long customerId) {
        if(!freightRepository.existsByCustomerId(customerId)){
            throw new FreightAPIException(HttpStatus.BAD_REQUEST, "Customer Id: "+customerId+" is invalid please try again!");
        }
        List<Freight> freightList = freightRepository.findByCustomerId(customerId);
        return freightList.stream().map(c->mapToDto(c)).collect(Collectors.toList());
    }

    @Override
    public FreightDto getFreightById(CustomerDto customerDto, long orderId) {
        Customer customer = mapToEntity(customerDto);
        Freight freight = freightRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Freight", "orderId", orderId)
        );
        if(freight.getCustomer().getId()!=customer.getId()){
            throw new FreightAPIException(HttpStatus.BAD_REQUEST,"Freight doesn't belong to the customer");
        }
        return mapToDto(freight);
    }

    @Override
    public FreightDto updateFreightById(CustomerDto customerDto, long orderId, FreightDto freightDto) {
        Customer customer = mapToEntity(customerDto);
        Freight freight = freightRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Freight", "orderId", orderId)
        );
        if(freight.getCustomer().getId()!=customer.getId()){
            throw new FreightAPIException(HttpStatus.BAD_REQUEST,"Freight doesn't belong to the customer");
        }
        freight.setOrigin(freightDto.getOrigin());
        freight.setDestination(freightDto.getDestination());
        Freight updated = freightRepository.save(freight);
        return mapToDto(updated);
    }

    @Override
    public void removeFreight(CustomerDto customerDto, long orderId) {
        Customer customer = mapToEntity(customerDto);
        Freight freight = freightRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Freight", "orderId", orderId)
        );
        if(freight.getCustomer().getId()!=customer.getId()){
            throw new FreightAPIException(HttpStatus.BAD_REQUEST,"Freight doesn't belong to the customer");
        }
        freightRepository.delete(freight);
    }
}
