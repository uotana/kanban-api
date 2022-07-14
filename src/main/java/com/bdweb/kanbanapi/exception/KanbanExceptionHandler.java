package com.bdweb.kanbanapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class KanbanExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DefaultError> userNotFound(CustomerNotFoundException exception) {
        DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
