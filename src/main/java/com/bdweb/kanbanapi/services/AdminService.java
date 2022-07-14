package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.responses.CustomerResponse;
import com.bdweb.kanbanapi.exception.CustomerNotFoundException;
import com.bdweb.kanbanapi.exception.RoleNotFoundException;
import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class AdminService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    public AdminService(CustomerRepository repository, RoleRepository roleRepository) {
        this.customerRepository = repository;
        this.roleRepository = roleRepository;
    }

    public CustomerResponse updateCustomerRoles(UUID customerId, Long roleId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role with id " + roleId + " not found."));
        Set<Role> roles = customer.getRoles();
        roles.add(role);
        customer.setRoles(roles);
        return customerRepository.save(customer).toResponse();
    }
}
