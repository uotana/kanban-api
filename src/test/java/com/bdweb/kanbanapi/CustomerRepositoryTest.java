package com.bdweb.kanbanapi;

import com.bdweb.kanbanapi.models.Customer;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.CustomerRepository;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findCustomerByUsernameTest() {
        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Customer customer = new Customer();
        customer.setUsername("ana");
        customer.setEmail("ana@email.com");
        customer.setName("ana");
        customer.setPassword(new BCryptPasswordEncoder().encode("abcde"));
        customer.setRoles(roles);
        customer.setRegistrationDate(ZonedDateTime.now());
        customerRepository.save(customer);
        Customer customerReturned = customerRepository.findByUsername("ana").get();
        System.out.println(customerReturned);
        assertEquals(customer.getUsername(), customerReturned.getUsername());
    }
}
