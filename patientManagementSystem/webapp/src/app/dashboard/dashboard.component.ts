import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient';
import { ServiceService } from '../service/service.service';
import { MatDialog } from '@angular/material/dialog';

import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  patient: any=[]; //patient array

  //intialize the constructor
  constructor(public dialog: MatDialog, private service:ServiceService, private router: Router) { }
  

  ngOnInit(): void {
    this.getAllPatients();
  }

  //method to get all the patient details
  getAllPatients(){
    this.service.getAllPatients().subscribe(
      (data) => {
        this.patient = data;
        console.log("Patients Data",data);
        
      }
    )
  }

  //method to get patient details by id
  getPatientById(id: string){
    this.service.getPatientById(id).subscribe(
      (data) => {
        this.patient = data;
        console.log("Patient Data",data);
        localStorage.setItem("id", id);
        this.router.navigate(['/profile']);
      }
    )
  }

  //method to delete patient details by id
  deletePatient(id: string){
    this.service.deletePatient(id).subscribe(
      (data) => {
        this.patient = data;
        console.log("Patient Data",data);
        this.getAllPatients();
      }
    )
  }

  //method to add patient details
  addPatient(patientData:any){
    this.service.addPatient(patientData).subscribe(
      (data) => {
        this.patient = data;
        console.log("Patient Data",data);
      }
    )
  }

  //method to update patient details by id
  updatePatient(id:string,patientData:any){
    this.service.updatePatient(id,patientData).subscribe(
      (data) => {
        this.patient = data;
        console.log("Patient Data",data);
        this.router.navigate(['/update-profile',id]);
      }
    )
  }

  closeDialog() {
    this.dialog.closeAll();
  }
  

}
