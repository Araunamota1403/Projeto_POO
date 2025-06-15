package com.example.spcapi.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}