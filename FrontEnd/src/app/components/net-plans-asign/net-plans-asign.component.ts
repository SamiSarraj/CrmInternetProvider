import { Component, OnInit } from '@angular/core';
import {TicketAsign} from '../../models/ticket-asign.model';
import {TicketAsignList} from '../../models/ticket-asign-list.model';
import {TicketEmployee} from '../../models/ticket-employee.model';
import {Importance} from '../ticket-assign/importance';
import {Topics} from '../ticket-create/topics';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {NetPlanAsign} from '../../models/net-plan-asign';
import {NetPlanCustomers} from '../../models/net-plan-customers';
import {NetPackagesCreate} from '../../models/net-packages-create.model';

@Component({
  selector: 'app-net-plans-asign',
  templateUrl: './net-plans-asign.component.html',
  styleUrls: ['./net-plans-asign.component.css']
})
export class NetPlansAsignComponent implements OnInit {

  public netPlanAsign = new NetPlanAsign();
  public customers = new Array<NetPlanCustomers>();
  public packages = new Array<NetPackagesCreate>();
  public error = false;
  public success = false;
  asignPlanForm = new FormGroup( {
    customerUsername : new FormControl(),
    idPackage : new FormControl()
  });
  constructor(private router: Router, private apiService: APIService) { }

  ngOnInit() {
    this.getAllCustomers();
    this.getAllPackages();
  }
  public getAllCustomers() {
    this.apiService.getAllCustomersNetPlans().subscribe(
      data => this.customers = data,
      err => console.error(err)
    );
  }
  public getAllPackages() {
    this.apiService.getAllPackages().subscribe(
      data => this.packages = data,
      err => console.log(err)
    );
  }
  asignNetPlan(): void {
    this.netPlanAsign.customerUsername = this.asignPlanForm.controls['customerUsername'].value;
    this.netPlanAsign.idPackage = this.asignPlanForm.controls['idPackage'].value;
    this.apiService.asignNetPlan(this.netPlanAsign).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }

}
