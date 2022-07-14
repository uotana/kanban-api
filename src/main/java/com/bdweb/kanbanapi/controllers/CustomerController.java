package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.requests.CustomerRequest;
import com.bdweb.kanbanapi.dtos.responses.CustomerResponse;
import com.bdweb.kanbanapi.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService customerService){
        this.service = customerService;
    }
    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") UUID id,
                                                   @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");
    }
}
