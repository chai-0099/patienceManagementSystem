package com.example.patientManagementSystem.patientDetails.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    //Basic entity of the patient details with id as primary key, name, gender, address, contact number, email
    @Id
    private String id;
    private String name;
    private Gender gender;
    private String contactNumber;
    private String email;
}
