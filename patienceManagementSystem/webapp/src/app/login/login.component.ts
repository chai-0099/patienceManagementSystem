import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent implements OnInit {

hide: boolean = true;
emailId = new FormControl('', [Validators.required, Validators.email]);


  //add constructor
  constructor(private router:Router,private service:ServiceService) { }

  loginForm = new FormGroup({
    emailId: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });
  

  ngOnInit() {
  }

//method onSubmit that will navigate to the home page
  onSubmit() {

    this.service.login(this.loginForm.value).subscribe((response :any) =>{
    console.log("Login successful");
    this.router.navigate(['/home']);
  })
  }


  getErrorMessage() {
    if (this.emailId.hasError('required')) {
      return 'You must enter a value';
    }

    return this.emailId.hasError('email') ? 'Not a valid email' : '';
  }
}
