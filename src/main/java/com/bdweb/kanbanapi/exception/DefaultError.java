package com.bdweb.kanbanapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefaultError {
    private Integer status;
    private String message;
    private Long timestamp;
}
