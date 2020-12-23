import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
public sucess = false;
public error = false;
public credentials = {username: '', password: ''};


/*showNav = true;*/
loginForm = new FormGroup({
  username: new FormControl(),
  password: new FormControl()
});

  constructor(private authService: AuthService, private router: Router) { }
  login(): void {
    this.credentials.username = this.loginForm.controls['username'].value;
    this.credentials.password = this.loginForm.controls['password'].value;
    this.error = false;
    this.authService.attemptAuth(this.credentials).subscribe(data => {
        this.authService.saveToken(data.token);
        this.router.navigate(['/home']);
        /* ignore jslint start */
        /* ignore jslint end */
      },
      err => {
        console.log(err);
        this.error = true;
      }
    );
  }
}
