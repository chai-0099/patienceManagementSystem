package com.example.patientManagementSystem.patientDetails.service;

import com.example.patientManagementSystem.patientDetails.exception.PatientAlreadyExists;
import com.example.patientManagementSystem.patientDetails.exception.PatientNotFoundException;
import com.example.patientManagementSystem.patientDetails.model.Patient;
import com.example.patientManagementSystem.patientDetails.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    //initialise the repository
    private PatientRepository patientRepository;

    //Create the auto-wired constructor
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //Implements the method of addPatient from PatientService
    @Override
    public Patient addPatient(Patient patient) throws PatientAlreadyExists {
        if(patientRepository.existsById(patient.getId())){
            throw new PatientAlreadyExists("Patient Already Exists");
        }
        Patient savedPatient = patientRepository.save(patient);
        return savedPatient;
    }

    //Implements the method of updatePatient from PatientService with proper exception handling
    @Override
    public Patient updatePatient(String Id,Patient patient) throws IOException, PatientNotFoundException {
        if (patientRepository.existsById(Id)) {
            Patient savedPatient = patientRepository.save(patient);
            return savedPatient;
        }
        throw new PatientNotFoundException("Patient Not Found");
    }
    //Implements the method of deletePatient from PatientService with proper exception handling
    @Override
    public boolean deletePatient(String id) throws IOException, PatientNotFoundException {
        if(patientRepository.existsById(id)){
            Patient patient =  patientRepository.findById(id).get();
            patientRepository.deleteById(id);
            return true;
        }
        throw new PatientNotFoundException("Patient Not Found");
    }

    //Implements the method of getPatientById from PatientService with proper exception handling
    @Override
    public Patient getPatientById(String id) throws IOException, PatientNotFoundException {
        if(patientRepository.existsById(id)){
              return patientRepository.findById(id).get();
        }
       throw new PatientNotFoundException("Patient Not Found");
    }

    //Implements the method of getAllPatient from PatientService with proper exception handling
    @Override
    public List<Patient> getAllPatient() throws IOException, PatientNotFoundException {
        if (patientRepository.findAll().isEmpty()) {
            throw new PatientNotFoundException("Patient Not Found");
        }
        return patientRepository.findAll();
    }


}
