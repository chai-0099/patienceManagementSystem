package com.example.patientManagementSystem.patientDetails.exception;

public class PatientAlreadyExists extends Exception{
    private String message;

    public PatientAlreadyExists() {
    }

    public PatientAlreadyExists(String message) {
        super(message);
        this.message = "Patient Already Exists";
    }
}
