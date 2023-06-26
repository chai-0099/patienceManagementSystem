package com.example.patienceManagementSystem.patientDetails.service;

import com.example.patienceManagementSystem.patientDetails.exception.PatientAlreadyExists;
import com.example.patienceManagementSystem.patientDetails.exception.PatientNotFoundException;
import com.example.patienceManagementSystem.patientDetails.model.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    //service interface for patient with exception handling
    Patient addPatient(Patient patient) throws IOException, PatientAlreadyExists;
    Patient getPatientById(String id) throws IOException, PatientNotFoundException;
    List<Patient> getAllPatient() throws IOException, PatientNotFoundException;
    Patient updatePatient(String Id, Patient patient) throws IOException, PatientNotFoundException;
    boolean deletePatient(String id) throws IOException, PatientNotFoundException;


}
