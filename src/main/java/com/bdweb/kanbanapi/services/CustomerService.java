package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.CustomerRequest;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private CustomerRepository repository;
    public CustomerService(CustomerRepository customerRepository){
        this.repository = customerRepository;
    }

    @Transactional
    public Customer save(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setRegistrationDate(ZonedDateTime.now());
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(UUID id) {
        return repository.findById(id).get();
    }

    public Customer update(UUID id, CustomerRequest request) {
        Optional<Customer> customerOptional = repository.findById(id);
        Customer customer = new Customer();
        customer.setId(customerOptional.get().getId());
        customer.setRegistrationDate(customerOptional.get().getRegistrationDate());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        return repository.save(customer);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
