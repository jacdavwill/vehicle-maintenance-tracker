  
package com.example.exceptions;

public class InternalServiceException extends Exception {
    private static final long serialVersionUID = 1L;
    private String message;
    
    public InternalServiceException(String message) {
        this.message = message;
    }

    public InternalServiceException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }

}