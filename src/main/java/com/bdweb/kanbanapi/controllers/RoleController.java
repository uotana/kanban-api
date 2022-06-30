package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.RoleRequest;
import com.bdweb.kanbanapi.models.Role;
import com.bdweb.kanbanapi.services.RoleService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService roleService) {
        this.service = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody RoleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<Role>> findAllRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id,
                                           @RequestBody RoleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully.");
    }
}
