package com.example.patientManagementSystem.patientTests.service;

import com.example.patientManagementSystem.patientDetails.exception.PatientAlreadyExists;
import com.example.patientManagementSystem.patientDetails.exception.PatientNotFoundException;
import com.example.patientManagementSystem.patientDetails.model.Patient;
import com.example.patientManagementSystem.patientDetails.repository.PatientRepository;
import com.example.patientManagementSystem.patientDetails.service.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.patientManagementSystem.patientDetails.model.Gender.Male;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient() throws PatientAlreadyExists {
        // Arrange
        Patient patient = new Patient("1","John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(anyString())).thenReturn(false);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Act
        Patient savedPatient = patientService.addPatient(patient);

        // Assert
        verify(patientRepository, times(1)).existsById(anyString());
        verify(patientRepository, times(1)).save(any(Patient.class));
        Assertions.assertEquals(patient, savedPatient);
    }

    @Test
    void testAddPatient_ThrowsPatientAlreadyExists() {
        // Arrange
        Patient patient = new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(anyString())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(PatientAlreadyExists.class, () -> patientService.addPatient(patient));
        verify(patientRepository, times(1)).existsById(anyString());
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void testUpdatePatient() throws IOException, PatientNotFoundException {
        // Arrange
        String patientId = "1";
        Patient updatedPatient = new Patient(patientId,"John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(patientId)).thenReturn(true);
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        // Act
        Patient savedPatient = patientService.updatePatient(patientId, updatedPatient);

        // Assert
        verify(patientRepository, times(1)).existsById(patientId);
        verify(patientRepository, times(1)).save(any(Patient.class));
        Assertions.assertEquals(updatedPatient, savedPatient);
    }

    @Test
    void testUpdatePatient_ThrowsPatientNotFoundException() {
        // Arrange
       String patientId = "1";
        Patient updatedPatient = new Patient(patientId, "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(String.valueOf(patientId))).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(PatientNotFoundException.class, () -> patientService.updatePatient(String.valueOf(patientId), updatedPatient));
        verify(patientRepository, times(1)).existsById(String.valueOf(patientId));
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void testDeletePatient() throws IOException, PatientNotFoundException {
        // Arrange
    String patientId = "1";
        Patient patient = new Patient(patientId, "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(String.valueOf(patientId))).thenReturn(true);
        when(patientRepository.findById(String.valueOf(patientId))).thenReturn(Optional.of(patient));

        // Act
        boolean isDeleted = patientService.deletePatient(String.valueOf(patientId));

        // Assert
        verify(patientRepository, times(1)).existsById(String.valueOf(patientId));
        verify(patientRepository, times(1)).findById(String.valueOf(patientId));
        verify(patientRepository, times(1)).deleteById(String.valueOf(patientId));
        Assertions.assertTrue(isDeleted);
    }

    @Test
    void testDeletePatient_ThrowsPatientNotFoundException() {
        // Arrange
        String patientId = "1";
        when(patientRepository.existsById(String.valueOf(patientId))).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(PatientNotFoundException.class, () -> patientService.deletePatient(String.valueOf(patientId)));
        verify(patientRepository, times(1)).existsById(String.valueOf(patientId));
        verify(patientRepository, never()).findById(anyString());
        verify(patientRepository, never()).deleteById(anyString());
    }

    @Test
    void testGetPatientById() throws IOException, PatientNotFoundException {
        // Arrange
        String  patientId = "1";
        Patient patient = new Patient(patientId, "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientRepository.existsById(String.valueOf(patientId))).thenReturn(true);
        when(patientRepository.findById(String.valueOf(patientId))).thenReturn(Optional.of(patient));

        // Act
        Patient retrievedPatient = patientService.getPatientById(String.valueOf(patientId));

        // Assert
        verify(patientRepository, times(1)).existsById(String.valueOf(patientId));
        verify(patientRepository, times(1)).findById(String.valueOf(patientId));
        Assertions.assertEquals(patient, retrievedPatient);
    }

    @Test
    void testGetPatientById_ThrowsPatientNotFoundException() {
        // Arrange
     long patientId = 1;
        when(patientRepository.existsById(String.valueOf(patientId))).thenReturn(false);

        // Act & Assert
        Assertions.assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(String.valueOf(patientId)));
        verify(patientRepository, times(1)).existsById(String.valueOf(patientId));
        verify(patientRepository, never()).findById(anyString());
    }

    @Test
    void testGetAllPatient() throws IOException, PatientNotFoundException {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history"));
        patients.add(new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history"));
        when(patientRepository.findAll()).thenReturn(patients);

        // Act
        List<Patient> retrievedPatients = patientService.getAllPatient();

        // Assert
        verify(patientRepository, times(2)).findAll();
        Assertions.assertEquals(patients, retrievedPatients);
    }

    @Test
    void testGetAllPatient_ThrowsPatientNotFoundException() {
        // Arrange
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(PatientNotFoundException.class, () -> patientService.getAllPatient());
        verify(patientRepository, times(1)).findAll();
    }
}
