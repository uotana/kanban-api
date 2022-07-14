package com.bdweb.kanbanapi.models;

import com.bdweb.kanbanapi.dtos.CustomerResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity(name = "CUSTOMER")
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "ROLE", nullable = false)
    private Set<Role> roles;
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private ZonedDateTime registrationDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CustomerResponse toResponse() {
        CustomerResponse response = new CustomerResponse();
        response.setId(this.getId());
        response.setName(this.getName());
        response.setEmail(this.getEmail());
        response.setUsername(this.getUsername());
        response.setRegistrationDate(this.getRegistrationDate());
        response.setRoles(this.getRoles());
        return response;
    }
}
