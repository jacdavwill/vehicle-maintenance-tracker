package com.example.exceptions;

public class AlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public AlreadyExistsException(String message) {
        this.message = message;
    }

    public AlreadyExistsException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }

}