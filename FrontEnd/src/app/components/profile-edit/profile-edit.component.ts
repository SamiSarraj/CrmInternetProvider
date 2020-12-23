import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {User} from '../../models/user.model';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {
  public user = new User();
  public error = false;
  public success = false;
  modifyUserForm = new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    email: new FormControl(),
    mobile: new FormControl(),
    country: new FormControl(),
    city: new FormControl(),
    address: new FormControl(),
    password: new FormControl()
  });

  constructor(private apiService: APIService) {
  }

  ngOnInit() {
  }

  reset() {
    this.modifyUserForm.reset();
  }

  modifyUser(): void {
    this.user.firstName = this.modifyUserForm.controls['firstName'].value;
    this.user.lastName = this.modifyUserForm.controls['lastName'].value;
    this.user.email = this.modifyUserForm.controls['email'].value;
    this.user.mobile = this.modifyUserForm.controls['mobile'].value;
    this.user.country = this.modifyUserForm.controls['country'].value;
    this.user.city = this.modifyUserForm.controls['city'].value;
    this.user.address = this.modifyUserForm.controls['address'].value;
    this.user.password = this.modifyUserForm.controls['password'].value;
    this.apiService.modifyUser(this.user).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
}
