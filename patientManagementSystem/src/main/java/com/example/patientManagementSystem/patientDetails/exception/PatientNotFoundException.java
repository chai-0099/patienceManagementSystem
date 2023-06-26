package com.example.patienceManagementSystem.patientDetails.exception;

public class PatientNotFoundException extends Exception{
    private String message;

    public PatientNotFoundException() {
    }

    public PatientNotFoundException(String message) {
        super(message);
        this.message = "Patient Not Found";
    }
}

