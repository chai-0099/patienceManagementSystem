package com.example.patientManagementSystem.patientDetails.repository;

import com.example.patientManagementSystem.patientDetails.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
