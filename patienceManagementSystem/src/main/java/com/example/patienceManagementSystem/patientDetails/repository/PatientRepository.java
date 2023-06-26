package com.example.patienceManagementSystem.patientDetails.repository;

import com.example.patienceManagementSystem.patientDetails.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
