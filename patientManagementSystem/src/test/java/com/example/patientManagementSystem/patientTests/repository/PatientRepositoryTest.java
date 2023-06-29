package com.example.patientManagementSystem.patientTests.repository;

import com.example.patientManagementSystem.patientDetails.model.Patient;
import com.example.patientManagementSystem.patientDetails.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class PatientRepositoryTest {


    private PatientRepository patientRepository;

    @Autowired
    public PatientRepositoryTest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }



    @Test
    public void testSavePatient() {
        // Create a new Patient object
        Patient patient = new Patient();
        patient.setName("John Doe");


        // Save the Patient in the repository
        Patient savedPatient = patientRepository.save(patient);

        // Retrieve the saved Patient from the repository
        Patient retrievedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);

        // Assert that the retrieved Patient matches the original Patient
        Assertions.assertEquals(patient, retrievedPatient);
    }

    @Test
    public void testFindPatientById() {
        // Create a new Patient object
        Patient patient = new Patient();
        patient.setName("Jane Smith");


        // Save the Patient in the repository
        Patient savedPatient = patientRepository.save(patient);

        // Retrieve the Patient by ID
        Patient retrievedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);

        // Assert that the retrieved Patient matches the original Patient
        Assertions.assertEquals(patient, retrievedPatient);
    }

    @Test
    public void testUpdatePatient() {
        // Create a new Patient object
        Patient patient = new Patient();
        patient.setName("John Doe");


        // Save the Patient in the repository
        Patient savedPatient = patientRepository.save(patient);

        // Update the Patient's name
        savedPatient.setName("John Smith");

        // Save the updated Patient in the repository
        patientRepository.save(savedPatient);

        // Retrieve the updated Patient from the repository
        Patient retrievedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);

        // Assert that the retrieved Patient reflects the changes made during the update
        Assertions.assertEquals("John Smith", retrievedPatient.getName());
    }

    @Test
    public void testDeletePatient() {
        // Create a new Patient object
        Patient patient = new Patient();
        patient.setName("John Doe");


        // Save the Patient in the repository
        Patient savedPatient = patientRepository.save(patient);

        // Delete the Patient from the repository
        patientRepository.deleteById(savedPatient.getId());

        // Attempt to retrieve the deleted Patient from the repository
        Patient deletedPatient = patientRepository.findById(savedPatient.getId()).orElse(null);

        // Assert that the deleted Patient is null
        Assertions.assertNull(deletedPatient);
    }
    @Test
    public void testFGetAllPatients() {
        // Create multiple Patient objects
        Patient patient1 = new Patient();
        patient1.setName("John Doe");


        Patient patient2 = new Patient();
        patient2.setName("Jane Smith");


        // Save the Patients in the repository
        patientRepository.save(patient1);
        patientRepository.save(patient2);

        // Retrieve all Patients from the repository
        Iterable<Patient> patients = patientRepository.findAll();


        // Assert that the count matches the number of saved Patients
        Assertions.assertEquals(2 , patients.spliterator().getExactSizeIfKnown());

    }

}
