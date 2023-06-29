package com.example.patientManagementSystem.patientTests.controller;

import com.example.patientManagementSystem.patientDetails.controller.PatientController;
import com.example.patientManagementSystem.patientDetails.exception.PatientAlreadyExists;
import com.example.patientManagementSystem.patientDetails.exception.PatientNotFoundException;
import com.example.patientManagementSystem.patientDetails.model.Patient;
import com.example.patientManagementSystem.patientDetails.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.patientManagementSystem.patientDetails.model.Gender.Male;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatient() throws IOException, PatientAlreadyExists {
        // Arrange
        Patient patient = new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientService.addPatient(patient)).thenReturn(patient);

        // Act
        ResponseEntity<?> response = patientController.addPatient(patient);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(patientService, times(1)).addPatient(patient);
    }

    @Test
    void testGetPatientById() throws IOException, PatientNotFoundException {
        // Arrange
        String patientId = "1";
        Patient expectedPatient = new Patient(patientId, "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientService.getPatientById(patientId)).thenReturn(expectedPatient);

        // Act
        ResponseEntity<?> response = patientController.getPatientById(patientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPatient, response.getBody());
        verify(patientService, times(2)).getPatientById(patientId);
    }

    @Test
    void testGetAllPatient() throws IOException, PatientNotFoundException {
        // Arrange
        List<Patient> expectedPatients = new ArrayList<>();
        expectedPatients.add(new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history"));
        expectedPatients.add(new Patient("1", "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history"));
        when(patientService.getAllPatient()).thenReturn(expectedPatients);

        // Act
        ResponseEntity<?> response = patientController.getAllPatient();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPatients, response.getBody());
        verify(patientService, times(2)).getAllPatient();
    }

    @Test
    void testUpdatePatient() throws IOException, PatientNotFoundException {
        // Arrange
        String patientId = "1";
        Patient patient = new Patient(patientId, "John Doe", Male, 12, "A+","45688996", "john@gmail.com","New Jersey, USA", "Patient has no medical history");
        when(patientService.updatePatient(String.valueOf(patientId), patient)).thenReturn(patient);

        // Act
        ResponseEntity<?> response = patientController.updatePatient(String.valueOf(patientId), patient);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(patientService, times(2)).updatePatient(String.valueOf(patientId), patient);
    }

    @Test
    void testDeletePatient() throws IOException, PatientNotFoundException {
        // Arrange
        long patientId = 1;
        when(patientService.deletePatient(String.valueOf(patientId))).thenReturn(true);

        // Act
        ResponseEntity<?> response = patientController.deletePatient(String.valueOf(patientId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(patientService, times(1)).deletePatient(String.valueOf(patientId));

    }
}
