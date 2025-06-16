package com.example.spcapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(InvalidSerialException.class)
    public ResponseEntity<String> handleInvalidSerial(InvalidSerialException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Serial: " + e.getMessage());
    }
    
    @ExceptionHandler(MissingTokenException.class)
    public ResponseEntity<String> handleMissingToken(MissingTokenException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing Token: " + e.getMessage());
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API Error: " + e.getMessage());
    }
}
