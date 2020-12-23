import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {User} from '../../models/user.model';
import {APIService} from '../../services/api.service';
import {Topics} from '../ticket-create/topics';


declare function  GeneratePassword(): any;
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  public user = new User();
  public error = false;
  public success = false;
  public topic: Topics[];
  addUserForm = new FormGroup( {
  firstName: new FormControl(),
  lastName: new FormControl(),
  email: new FormControl(),
  mobile: new FormControl(),
  country: new FormControl(),
  city: new FormControl(),
  address: new FormControl(),
  dateOfBirth: new FormControl(),
  sex: new FormControl(),
  username: new FormControl(),
  password: new FormControl(),
  role: new FormControl(),
  employeeRole: new FormControl()
  });
  constructor(private apiService: APIService) {
  }

  ngOnInit() {
    this.topic = [
      {id: 1, name: 'Internet service'},
      {id: 2, name: 'Internet Packages'},
      {id: 3, name: 'Payment'},
      {id: 4, name: 'Crm Website'},
      {id: 5, name: 'Customer Service'},
    ];
  }
  reset() {
    this.addUserForm.reset();
  }
  addUser(): void {
    this.user.firstName = this.addUserForm.controls['firstName'].value;
    this.user.lastName = this.addUserForm.controls['lastName'].value;
    this.user.email = this.addUserForm.controls['email'].value;
    this.user.mobile = this.addUserForm.controls['mobile'].value;
    this.user.country = this.addUserForm.controls['country'].value;
    this.user.city = this.addUserForm.controls['city'].value;
    this.user.address = this.addUserForm.controls['address'].value;
    this.user.dateOfBirth = this.addUserForm.controls['dateOfBirth'].value;
    this.user.sex = this.addUserForm.controls['sex'].value;
    this.user.username = this.addUserForm.controls['username'].value;
    this.user.password = this.addUserForm.controls['password'].value;
    this.user.role = this.addUserForm.controls['role'].value;
    this.user.employeeRole = this.addUserForm.controls['employeeRole'].value;

    this.apiService.addUser(this.user).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }

  GeneratePassword(): String {
    const password =  Math.random().toString(36).substring(2);
    document.getElementById('password').textContent = '';
    return password;
  }
}
