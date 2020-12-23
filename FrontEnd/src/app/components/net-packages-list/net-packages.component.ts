import { Component, OnInit } from '@angular/core';
import {NetPackagesCreate} from '../../models/net-packages-create.model';
import {APIService} from '../../services/api.service';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Packagetype} from '../net-packages-create/packagetype';
import {Contract} from '../net-packages-create/contract';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-net-packages',
  templateUrl: './net-packages.component.html',
  styleUrls: ['./net-packages.component.css']
})
export class NetPackagesComponent implements OnInit {
  public netPackages = new Array<NetPackagesCreate>();
  constructor(private authService: AuthService,
              private router: Router,
              private apiService: APIService,
              private http: HttpClient) { }
  public error = false;
  public success = false;
  public packageType: Packagetype[];
  public contract: Contract[];
  modifyPackageForm = new FormGroup( {
      title: new FormControl(),
      modem: new FormControl(),
      price: new FormControl(),
      type: new FormControl(),
      speed: new FormControl(),
      contract: new FormControl(),
    }
  );
  public package = new NetPackagesCreate();
  public packageId: number;


  ngOnInit() {
    this.getAllPackages();
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
  public getAllPackages() {
    this.apiService.getAllPackages().subscribe(data => this.netPackages = data,
      err => console.error(err));
  }
  customer(): boolean {
    return this.authService.isAuthenticated() && this.authService.isCustomer();
  }
  authenticated(): boolean {
    return this.authService.isAuthenticated();
  }
  modifyPackage(): void {
    this.package.title = this.modifyPackageForm.controls['title'].value;
    this.package.modem = this.modifyPackageForm.controls['modem'].value;
    this.package.price = this.modifyPackageForm.controls['price'].value;
    this.package.type = this.modifyPackageForm.controls['type'].value;
    this.package.speed = this.modifyPackageForm.controls['speed'].value;
    this.package.contract = this.modifyPackageForm.controls['contract'].value;
    this.apiService.modifyPackage(this.package, this.packageId).subscribe(data => this.success = true,
      err => { console.log(err);
        this.error = true;
      });

  }
  reset() {
    this.modifyPackageForm.reset();
  }


}
