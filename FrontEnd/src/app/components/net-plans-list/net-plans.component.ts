import { Component, OnInit } from '@angular/core';
import {NetPlansListUser} from '../../models/net-plans-list-user.model';
import {APIService} from '../../services/api.service';
import {NetPackagesCreate} from '../../models/net-packages-create.model';

@Component({
  selector: 'app-net-plans',
  templateUrl: './net-plans.component.html',
  styleUrls: ['./net-plans.component.css']
})
export class NetPlansComponent implements OnInit {
  public netPlans = new Array<NetPlansListUser>();
  public package = new NetPackagesCreate();
  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.getAllNetPlans();
  }
getAllNetPlans() {
    this.apiService.getAllNetPlans().subscribe(data => this.netPlans = data,
      err => console.error(err));
}
getInfoAboutThePackage(id) {
    this.apiService.getOnePackageById(id).subscribe(data => this.package = data,
      err => console.error(err));

}
}
