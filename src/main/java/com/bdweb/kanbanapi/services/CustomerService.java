package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.CustomerRequest;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final RoleRepository roleRepository;

    public CustomerService(CustomerRepository customerRepository, RoleRepository roleRepository){
        this.repository = customerRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Customer save(CustomerRequest request) {
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
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(UUID id) {
        return repository.findById(id).get();
    }

    @Transactional
    public Customer update(UUID id, CustomerRequest request) {
        Optional<Customer> customerOptional = repository.findById(id);
        Customer customer = new Customer();
        customer.setId(customerOptional.get().getId());
        customer.setRegistrationDate(customerOptional.get().getRegistrationDate());
        customer.setRoles(customerOptional.get().getRoles());
        customer.setUsername(request.getUsername());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        return repository.save(customer);
    }
    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
