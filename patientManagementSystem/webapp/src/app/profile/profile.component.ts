import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service/service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  patient: any=[];
  constructor(private service: ServiceService){}

//Create the onint method that will retrieve the data of patient with id in dialog box

  ngOnInit(): void {
    this.service.getPatientById(localStorage.getItem("id") as any).subscribe((data: any)=>{
      this.patient = data;
      console.log("Patient Data",data);
    }
    )
  }

}
