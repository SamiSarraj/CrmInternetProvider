import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Customer} from '../../models/customer.model';
import {InternetMainUse} from './internet';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  public customer = new Customer();
  public error = false;
  public success = false;
  internetMainUse: InternetMainUse[];
  public password: string;
  constructor(private apiService: APIService) {
  }
  addCustomerForm = new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    email: new FormControl(),
    mobile: new FormControl(),
    country: new FormControl(),
    city: new FormControl(),
    address: new FormControl(),
    dateOfBirth: new FormControl(),
    internetMainUse: new FormControl(),

    sex: new FormControl(),
    username: new FormControl(),
    password: new FormControl()
  });
  ngOnInit() {
    this.internetMainUse = [
      {id: 1, name: 'Study'},
      {id: 2, name: 'Gaming'},
      {id: 3, name: 'Work'},
      {id: 4, name: 'Regular'},
      {id: 5, name: 'Not specified'},
    ];
  }
  reset() {
    this.addCustomerForm.reset();
  }
  addCustomer(): void {
    this.customer.username = this.addCustomerForm.controls['username'].value;
    this.customer.password = this.addCustomerForm.controls['password'].value;
    this.customer.firstName = this.addCustomerForm.controls['firstName'].value;
    this.customer.lastName = this.addCustomerForm.controls['lastName'].value;
    this.customer.email = this.addCustomerForm.controls['email'].value;
    this.customer.mobile = this.addCustomerForm.controls['mobile'].value;
    this.customer.country = this.addCustomerForm.controls['country'].value;
    this.customer.city = this.addCustomerForm.controls['city'].value;
    this.customer.address = this.addCustomerForm.controls['address'].value;
    this.customer.dateOfBirth = this.addCustomerForm.controls['dateOfBirth'].value;
    this.customer.internetMainUse = this.addCustomerForm.controls['internetMainUse'].value;
    this.customer.sex = this.addCustomerForm.controls['sex'].value;
    this.apiService.addCustomer(this.customer).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });

  }
  GeneratePassword() {
    this.password = Math.random().toString(36).substring(2);
    document.getElementById('password').textContent = '';
  }
}
