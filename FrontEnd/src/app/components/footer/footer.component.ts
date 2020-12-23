import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(  private authService: AuthService,
                   private router: Router,
                   private apiService: APIService,
                   private http: HttpClient) { }
  authenticated(): boolean {
    return this.authService.isAuthenticated();
  }
  ngOnInit() {
  }

}
