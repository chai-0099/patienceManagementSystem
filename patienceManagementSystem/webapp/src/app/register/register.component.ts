import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceService } from '../service/service.service';
import { RegisterModel } from './register-model';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

hide: boolean = true;

emailId = new FormControl('', [Validators.required, Validators.email]);

  //add constructor
  constructor(private router:Router, private service:ServiceService) { }

  regData:RegisterModel | undefined;

  registerForm = new FormGroup({
    name : new FormControl('',Validators.required),
    emailId: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });
  
  ngOnInit() {
  }



//method onSubmit that will navigate to the home page
onSubmit(formGroupDirective:FormGroupDirective) : void {

  this.regData=this.registerForm.value;

  this.service.register(this.regData).subscribe((response :any) =>{
    console.log(response);
    console.log("User Registeration is Done !!!!!");
    this.router.navigate(['/login']);
})
}

getErrorMessage() {
  if (this.emailId.hasError('required')) {
    return 'You must enter a value';
  }

  return this.emailId.hasError('emailId') ? 'Not a valid email' : '';
}
}
