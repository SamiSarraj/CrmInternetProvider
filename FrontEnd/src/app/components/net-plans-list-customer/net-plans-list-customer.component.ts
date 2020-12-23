import { Component, OnInit } from '@angular/core';
import {NetPlansListUser} from '../../models/net-plans-list-user.model';
import {NetPackagesCreate} from '../../models/net-packages-create.model';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-net-plans-list-customer',
  templateUrl: './net-plans-list-customer.component.html',
  styleUrls: ['./net-plans-list-customer.component.css']
})
export class NetPlansListCustomerComponent implements OnInit {
  public allPlans = new Array<NetPlansListUser>();
  public validPlan = new NetPlansListUser();
  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.getCustomerValidPlan();
    this.getCustomerAllPastPlans();
  }
getCustomerValidPlan() {
    this.apiService.getCustomerValidPlan().subscribe(data => this.validPlan = data,
      err => console.log(err));
}
getCustomerAllPastPlans() {
  this.apiService.getCustomerAllPlans().subscribe(data => this.allPlans = data,
    err => console.log(err));
  }
}
