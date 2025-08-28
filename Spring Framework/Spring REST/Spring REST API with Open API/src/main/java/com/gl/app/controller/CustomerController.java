package com.gl.app.controller;

import com.gl.app.payload.CustomerDto;
import com.gl.app.payload.CustomerResponse;
import com.gl.app.service.CustomerService;
import com.gl.app.utils.AppsConstant;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gl.app.payload.DeleteResponse;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // spring boot rest api for create customer
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @Valid @RequestBody CustomerDto customerDto
    ){
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    // spring boot rest api for getting all the customers
    @GetMapping
    public ResponseEntity<CustomerResponse> getCustomers(
            @RequestParam(value = "pageNo",defaultValue = AppsConstant.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppsConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppsConstant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppsConstant.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return ResponseEntity.ok(customerService.getCustomers(pageNo,pageSize,sortBy,sortDir));
    }

    // spring boot rest api for updating the customers
    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomerById(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody CustomerDto customerDto
    ){
        return ResponseEntity.ok(customerService.updateCustomer(id,customerDto));
    }

    // spring boot rest api for get customer using primary key
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable(value = "id") long id
    ){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // spring boot rest api for removing the customer from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> removeCustomer(
            @PathVariable(value = "id") long id
    ){
        customerService.removeCustomer(id);
        DeleteResponse deleteResponse = new DeleteResponse("Customer deleted successfully!");
        return ResponseEntity.ok(deleteResponse);
    }
}