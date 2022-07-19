package com.bdweb.kanbanapi.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardResponse {
    private Long id;
    private String name;
    private CustomerResponse customer;
    private ZonedDateTime registrationDate;
}
