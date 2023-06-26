import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterModel } from '../register/register-model';
import { LoginModel } from '../login/login-model';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

  //method to check if the user is register
  register(registerData:RegisterModel){
    return this.http.post<any>('http://localhost:8181/api/v1/register',registerData);
}

//method to check if the user is login
login(loginData:LoginModel){
  return this.http.post<any>('http://localhost:8181/api/v1/login',loginData);
}

}
