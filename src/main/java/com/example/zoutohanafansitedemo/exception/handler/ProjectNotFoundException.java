package com.example.zoutohanafansitedemo.exception.handler;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
