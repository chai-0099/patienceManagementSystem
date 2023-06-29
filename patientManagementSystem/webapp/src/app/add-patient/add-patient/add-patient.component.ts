import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/model/patient';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {

    patient: Patient = {
      name: '',
      age: 0,
      gender: '',
      bloodGroup: '',
      contactNumber: '',
      email: '',
      address: '',
      description: ''
    };
  

  constructor(private patientService: ServiceService, private router:Router) {}
  ngOnInit(): void {
    this.addPatient();
 }

  //add a method to add patient details
  addPatient(){
    this.patientService.addPatient(this.patient).subscribe(
      (data) => {
        this.patient = data;
        console.log("Patient Data",data);
        this.router.navigate(['/dashboard']);
      }
     
    )
  }
 
}


