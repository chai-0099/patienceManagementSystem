import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceService } from '../service/service.service';
import { Patient } from '../model/patient';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  id: any;
 
  patient!: Patient;

  constructor(
    private service: ServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) { }
 
  ngOnInit(): void {
    this.patient = new Patient('', '', 0, '', '', '', '', '');
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);
    this.service.getPatientById(this.id).subscribe(data => {
      console.log(data);
      this.patient = data;
    });
  }

  updatePatient() {
    this.service.updatePatient(this.id, this.patient).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/dashboard']);
      },
      error => {
        console.log(error);
      }
    );
  }
}
