import {Component, OnInit} from '@angular/core';
import {APIService} from '../../services/api.service';
import {CountNew} from '../chart-js/count-new';
import {DashboardTopWorkers} from '../../models/dashboard-top-workers.model';
import {Router} from '@angular/router';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public amount = new CountNew();
  public amountTickets  = new CountNew();
  public topWorkers = new Array<DashboardTopWorkers>();
  constructor(private apiService: APIService, private router: Router) { }
  ngOnInit() {
    this.getAllDashboardUserNumbers();
    this.getAllTicketsNumbers();
    this.getTopWorkers();
  }
  public getAllDashboardUserNumbers() {
    this.apiService.getAllDashboardUserNumbers().subscribe(data => this.amount = data,
      err => console.error(err));
  }
  public getAllTicketsNumbers() {
    this.apiService.getAllTicketsNumbers().subscribe(data => this.amountTickets = data,
      err => console.error(err));
  }
  public getTopWorkers() {
    this.apiService.getTopWorkers().subscribe(data => this.topWorkers = data,
      err => console.error(err));
  }
public goToEmployee(employeeId) {
  this.router.navigate(['getProfile/', {id: employeeId}]);
}
}
