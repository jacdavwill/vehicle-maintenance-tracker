package com.example.exceptions;

public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public UnauthorizedException(String message) {
        this.message = message;
    }

    public UnauthorizedException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }

}