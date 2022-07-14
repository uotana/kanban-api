package com.bdweb.kanbanapi.dtos.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {
    private String token;
}
