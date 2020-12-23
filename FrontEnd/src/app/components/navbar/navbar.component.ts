import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(  private authService: AuthService,
                private router: Router,
                private apiService: APIService,
                private http: HttpClient) { }
  public logout() {
    this.authService.logout(true);
  }
  admin(): boolean {
    return this.authService.isAuthenticated() && this.authService.isAdmin();
  }
  employee(): boolean {
    return this.authService.isAuthenticated() && this.authService.isEmpolyee();
  }
  customer(): boolean {
    return this.authService.isAuthenticated() && this.authService.isCustomer();
  }
  authenticated(): boolean {
    return this.authService.isAuthenticated();
  }
  ngOnInit() {
    this.employee();
  }

}
