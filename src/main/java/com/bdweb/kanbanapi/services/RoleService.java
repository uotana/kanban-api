package com.bdweb.kanbanapi.services;

import com.bdweb.kanbanapi.dtos.requests.RoleRequest;
import com.bdweb.kanbanapi.exception.RoleNotFoundException;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

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
        return repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " not found."));
    }

    @Transactional
    public Role update(Long id, RoleRequest request) {
        Role role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " not found."));
        role.setId(role.getId());
        role.setRegistrationDate(role.getRegistrationDate());
        role.setName(request.getName());
        return repository.save(role);
    }

    @Transactional
    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " not found."));
        repository.deleteById(id);
    }
}
