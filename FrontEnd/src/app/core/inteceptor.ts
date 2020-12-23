import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthStorage} from './auth-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private authStorage: AuthStorage, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    if (this.authStorage.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.authStorage.getToken())});
    }
    return next.handle(authReq);
  }

}
