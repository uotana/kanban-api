package com.bdweb.kanbanapi.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class BoardResponse {
    private Long id;
    private String name;
    private CustomerResponse customer;
    private ZonedDateTime registrationDate;
}
