import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterModel } from '../register/register-model';
import { LoginModel } from '../login/login-model';
import { Observable } from 'rxjs';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

  //method to check if the user is register
  register(registerData:RegisterModel){
    return this.http.post<any>('http://localhost:9192/api/v1/register',registerData);
}

//method to check if the user is login
login(loginData:LoginModel){
  return this.http.post<any>('http://localhost:9192/api/v1/login',loginData);
}

//method to get the patient details
getAllPatients(): Observable <any>{
  return this.http.get<any>('http://localhost:9192/api/v1/patients');
}

//method to get the patient details by id
getPatientById(id:string): Observable <any>{
  return this.http.get<any>('http://localhost:9192/api/v1/patient/'+id);
}

//method to add the patient details
addPatient(patient:Patient): Observable <Patient>{
  return this.http.post<Patient>('http://localhost:9192/api/v1/patient',patient);
}

//method to update the patient details through id
updatePatient(id:string,patientData:any) : Observable <any>{
  return this.http.put<any>('http://localhost:9192/api/v1/patient/'+id,patientData);
}

//method to delete the patient details through id
deletePatient(id:string) : Observable <any>{
  return this.http.delete<any>('http://localhost:9192/api/v1/patient/'+id);
}



}
