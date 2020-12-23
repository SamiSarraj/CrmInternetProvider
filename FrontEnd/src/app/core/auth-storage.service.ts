import {Injectable} from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const AUTH_KEY = 'AuthStatus';
const ADMIN_KEY = 'AuthAdmin';
const EMPLOYEE_KEY = 'AuthEmployee';
const CUSTOMER_KEY = 'AuthCustomer';
const USERNAME_KEY = 'AuthName';

@Injectable()
export class AuthStorage {

  constructor() {
  }

  signOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.removeItem(AUTH_KEY);
    window.sessionStorage.clear();
  }

  public setAuthenticated() {
    window.sessionStorage.removeItem(AUTH_KEY);
    window.sessionStorage.setItem(AUTH_KEY, 'true');
  }

  public getIsAuthenticated(): boolean {
    return sessionStorage.getItem(AUTH_KEY) === 'true';
  }
  public setRole(value) {
    if (value === 'ADMIN') {
      window.sessionStorage.removeItem(ADMIN_KEY);
      window.sessionStorage.setItem(ADMIN_KEY, 'true');
    } else if (value === 'EMPLOYEE') {
      window.sessionStorage.removeItem(EMPLOYEE_KEY);
      window.sessionStorage.setItem(EMPLOYEE_KEY, 'true');
    } else if (value === 'CUSTOMER') {
      window.sessionStorage.removeItem(CUSTOMER_KEY);
      window.sessionStorage.setItem(CUSTOMER_KEY, 'true');
    }
  }
  /**public setAdmin(value) {
    window.sessionStorage.removeItem(ADMIN_KEY);
    window.sessionStorage.setItem(ADMIN_KEY, value);
  }*/

  public getIsAdmin(): boolean {
    return sessionStorage.getItem(ADMIN_KEY) === 'true';
  }
  public getIsEmployee (): boolean {
    return sessionStorage.getItem(EMPLOYEE_KEY) === 'true';
  }
  public getIsCustomer (): boolean {
    return sessionStorage.getItem(CUSTOMER_KEY) === 'true';
  }
  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    this.setAuthenticated();
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }
}
