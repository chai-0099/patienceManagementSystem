package com.example.patienceManagementSystem.patientDetails.controller;


import com.example.patienceManagementSystem.patientDetails.exception.PatientAlreadyExists;
import com.example.patienceManagementSystem.patientDetails.model.Patient;
import com.example.patienceManagementSystem.patientDetails.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//Add all the required annotations before the controller class
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PatientController {
    //inject the service class object here

    private PatientService patientService;
    ResponseEntity responseEntity;

    //Add the constructor here with @Autowired annotation
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Add Post Mapping to addPatients the required http methods with proper mapping and exception handling

    @PostMapping("/patient")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) throws IOException, PatientAlreadyExists {
        try {
            patientService.addPatient(patient);
            responseEntity = ResponseEntity.ok(patient);
        } catch (PatientAlreadyExists patientAlreadyExists) {
            responseEntity = ResponseEntity.badRequest().body(patientAlreadyExists.getMessage());
        }
        return responseEntity;
    }

    //Add Get Mapping to getPatients the required http methods with proper mapping and exception handling
    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable String id) throws IOException {
        try {
            patientService.getPatientById(id);
            responseEntity = ResponseEntity.ok(patientService.getPatientById(id));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }

    //Add Get Mapping to getAllPatients the required http methods with proper mapping and exception handling
    @GetMapping("/patients")
   public ResponseEntity<?> getAllPatient() throws IOException {
        try {
            patientService.getAllPatient();
            responseEntity = ResponseEntity.ok(patientService.getAllPatient());
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }
    //Add Put Mapping to updatePatients the required http methods with proper mapping and exception handling

    @PutMapping("/patient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable String id, @RequestBody Patient patient) throws IOException {
        try {
            patientService.updatePatient(id, patient);
            responseEntity = ResponseEntity.ok(patientService.updatePatient(id, patient));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }

    //Add Delete Mapping to deletePatients the required http methods with proper mapping and exception handling
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable String id) throws IOException {
        try {
            ResponseEntity responseEntity = ResponseEntity.ok(patientService.deletePatient(id));
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }
        return responseEntity;
    }

}
