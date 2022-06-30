package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.RoleRequest;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Role save(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setRegistrationDate(ZonedDateTime.now());
        return repository.save(role);
    }


    public List<Role> findAll() {
        return repository.findAll();
    }


    public Role findById(Long id) {
        return repository.findById(id).get();
    }


    public Role update(Long id, RoleRequest request) {
        Optional<Role> roleOptional = repository.findById(id);
        Role role = new Role();
        role.setId(roleOptional.get().getId());
        role.setRegistrationDate(roleOptional.get().getRegistrationDate());
        role.setName(request.getName());
        return repository.save(role);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
