import {Injectable} from '@angular/core';
import {AuthStorage} from './auth-storage.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from '../models/user.model';
import {TokenResponse} from '../models/token-response.model';

@Injectable()
export class AuthService {

  private userUrl = '/crm';
  private plainTextResponse = {responseType: 'text' as 'text'};

  constructor(private http: HttpClient, private authStorage: AuthStorage, private router: Router) {
  }

  public attemptAuth(credentials) {
    const encodedCredentials = {'username': credentials.username, 'password': btoa(credentials.password)};
    return this.http.post<TokenResponse>(this.userUrl + '/token/generate-token', encodedCredentials);
  }

  public logout(withRedirect) {
    this.http.get(this.userUrl + '/token/invalidate', this.plainTextResponse).subscribe(
      response => {
        this.authStorage.signOut();
        if (withRedirect) {
          this.router.navigate(['/login']);
        }
      },
      err => console.log(err)
    );
  }

  public refresh() {
    this.http.get(this.userUrl + '/token/check', this.plainTextResponse).subscribe(
      response => {
        if (response === 'valid') {
          this.authStorage.setAuthenticated();
        } else {
          this.authStorage.signOut();
          this.router.navigate(['/login']);
        }
      },
      err => {
        console.log(err);
        this.authStorage.signOut();
        this.router.navigate(['/login']);
      }
    );
  }

  public updateUserData() {
    this.http.get<User>(this.userUrl + '/user/current').subscribe(
      response => {
        this.authStorage.saveUsername(response.username);
        this.authStorage.setRole(response.role);
      },
      err => console.log(err)
    );
  }

  public isAuthenticated() {
    return this.authStorage.getIsAuthenticated();
  }

  public isAdmin() {
    return this.authStorage.getIsAdmin();
  }
  public isEmpolyee() {
    return this.authStorage.getIsEmployee();
  }
  public isCustomer() {
    return this.authStorage.getIsCustomer();
  }

  public getUsername() {
    return this.authStorage.getUsername();
  }

  saveToken(token) {
    this.authStorage.saveToken(token);
    this.updateUserData();
  }
}
