package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.CustomerRequest;
import com.bdweb.kanbanapi.dtos.CustomerResponse;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final RoleRepository roleRepository;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository){
        this.repository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public CustomerResponse save(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setUsername(request.getUsername());
        Set<Role> roles = new HashSet<>();
        Optional<Role> roleOptional = roleRepository.findByName("USER");
        roles.add(roleOptional.get());
        customer.setRoles(roles);
        customer.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        customer.setRegistrationDate(ZonedDateTime.now());
        Customer savedCustomer = repository.save(customer);
        return savedCustomer.toResponse();
    }

    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(customer -> customer.toResponse())
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(UUID id) {
        return repository.findById(id).get().toResponse();
    }

    @Transactional
    public CustomerResponse update(UUID id, CustomerRequest request) {
        Optional<Customer> customerOptional = repository.findById(id);
        Customer customer = new Customer();
        customer.setId(customerOptional.get().getId());
        customer.setRegistrationDate(customerOptional.get().getRegistrationDate());
        customer.setRoles(customerOptional.get().getRoles());
        customer.setUsername(request.getUsername());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        Customer savedCustomer = repository.save(customer);
        return savedCustomer.toResponse();
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
