import {Component, OnInit} from '@angular/core';
import {APIService} from './services/api.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AuthService} from './core/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public showNav: false;

  constructor(  private authService: AuthService,
                private router: Router,
                private apiService: APIService,
                private http: HttpClient
  ) {
  }
  ngOnInit() {
    this.authService.refresh();
  }

}
