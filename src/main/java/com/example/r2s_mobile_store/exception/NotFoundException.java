package com.example.r2s_mobile_store.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Not found with id: " + id);
    }
}
