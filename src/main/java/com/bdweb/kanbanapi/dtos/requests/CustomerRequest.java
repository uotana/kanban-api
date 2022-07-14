package com.bdweb.kanbanapi.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
