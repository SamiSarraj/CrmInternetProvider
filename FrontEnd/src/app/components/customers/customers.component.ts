import { Component, OnInit } from '@angular/core';
import {APIService} from '../../services/api.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Customer} from '../../models/customer.model';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers = new Array<Customer>();
  public customer = new Customer();
  public success = false;
  public fail = false;
  constructor(private apiService: APIService) { }
  updateCustomerForm = new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    email: new FormControl(),
    mobile: new FormControl(),
    country: new FormControl(),
    city: new FormControl(),
    address: new FormControl()
  });

  ngOnInit() {
    this.getAllCustomers();
  }
  public getAllCustomers() {
    this.apiService.getAllCustomers().subscribe(data => this.customers = data,
      err => console.error(err));
  }
  updateCustomer(): void {
    this.customer.firstName = this.updateCustomerForm.controls['firstName'].value;
    this.customer.lastName = this.updateCustomerForm.controls['lastName'].value;
    this.customer.email = this.updateCustomerForm.controls['email'].value;
    this.customer.mobile = this.updateCustomerForm.controls['mobile'].value;
    this.customer.country = this.updateCustomerForm.controls['country'].value;
    this.customer.city = this.updateCustomerForm.controls['city'].value;
    this.customer.address = this.updateCustomerForm.controls['address'].value;
    /*this.apiService.updateCustomer(this.customer).subscribe(data => this.success = true, fixme: update cosutomer
      err => {
        console.log(err);
        this.fail = true;
      });*/
  }
}
