package com.example.zoutohanafansitedemo.exception;

public class InvalidPasswordResetException extends RuntimeException {
    public InvalidPasswordResetException(String message) {
        super(message);
    }
}
