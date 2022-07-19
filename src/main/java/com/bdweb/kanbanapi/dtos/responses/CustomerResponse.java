package com.bdweb.kanbanapi.dtos.responses;

import com.bdweb.kanbanapi.models.Role;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
    private String username;
    private Set<Role> roles;
    private ZonedDateTime registrationDate;
}
