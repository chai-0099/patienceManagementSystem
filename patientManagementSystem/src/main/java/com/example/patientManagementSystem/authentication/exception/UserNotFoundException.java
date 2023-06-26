package com.example.patientManagementSystem.authentication.exception;

public class UserNotFoundException extends Exception{
    private String message;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message = "User Not Found";
    }
}
