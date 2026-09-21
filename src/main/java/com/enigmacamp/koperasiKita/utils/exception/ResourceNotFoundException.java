package com.enigmacamp.koperasiKita.utils.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' Is Not Found",  resourceName, fieldName, fieldValue));
    }
}
