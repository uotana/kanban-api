package com.bdweb.kanbanapi.exception;

public class TaskGroupNotFoundException extends RuntimeException{
    public TaskGroupNotFoundException(String message) {
        super(message);
    }
}