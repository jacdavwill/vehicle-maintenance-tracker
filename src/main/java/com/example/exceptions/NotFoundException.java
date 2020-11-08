package com.example.exceptions;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public NotFoundException(String message) {
        this.message = message;
    }

    public NotFoundException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }

}