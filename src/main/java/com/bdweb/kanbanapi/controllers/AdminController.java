package com.bdweb.kanbanapi.controllers;

import com.bdweb.kanbanapi.dtos.responses.CustomerResponse;
import com.bdweb.kanbanapi.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PutMapping("update-customer/{customer-id}/role/{role-id}")
    public ResponseEntity<CustomerResponse> updateCustomerRoles(@PathVariable("customer-id") UUID customerId,
                                                                @PathVariable("role-id") Long roleId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCustomerRoles(customerId, roleId));

    }
}
