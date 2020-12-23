import { Component, OnInit } from '@angular/core';
import {Packagetype} from './packagetype';
import {Contract} from './contract';
import {FormControl, FormGroup} from '@angular/forms';
import {NetPackagesCreate} from '../../models/net-packages-create.model';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-net-packages-create',
  templateUrl: './net-packages-create.component.html',
  styleUrls: ['./net-packages-create.component.css']
})
export class NetPackagesCreateComponent implements OnInit {
  public packageType: Packagetype[];
  public contract: Contract[];
  addPackageForm = new FormGroup( {
    title: new FormControl(),
    data: new FormControl(),
    modem: new FormControl(),
    price: new FormControl(),
    type: new FormControl(),
    speed: new FormControl(),
    contract: new FormControl(),
    }
  );
  public error = false;
  public success = false;
  public package = new NetPackagesCreate();
  constructor(private router: Router, private apiService: APIService) { }

  ngOnInit() {
    this.packageType = [
      {id: 1, name: '3G'},
      {id: 2, name: 'DSL Cable'},
      {id: 3, name: 'Fiber'},
      {id: 4, name: '4G'},
    ];
    this.contract = [
      {id: 1, contract: '1 year'},
      {id: 2, contract: '2 year'},
    ];
  }
  reset() {
    this.addPackageForm.reset();
  }
  addPackage(): void {
    this.package.title = this.addPackageForm.controls['title'].value;
    this.package.modem = this.addPackageForm.controls['modem'].value;
    this.package.price = this.addPackageForm.controls['price'].value;
    this.package.type = this.addPackageForm.controls['type'].value;
    this.package.speed = this.addPackageForm.controls['speed'].value;
    this.package.contract = this.addPackageForm.controls['contract'].value;
    this.apiService.addPackage(this.package).subscribe(data => this.success = true,
      err => { console.log(err);
        this.error = true;
      });

  }
}
