import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BasicAuthenticationService } from '../services/basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor{

  constructor(private basicAuthenticationService: BasicAuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
      let basicAuthHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
      let username = this.basicAuthenticationService.getAuthenticatedUser();
      console.log(basicAuthHeaderString);
      console.log(username);

      if(basicAuthHeaderString && username){
        req = req.clone({
          setHeaders: {
            //Authorization: basicAuthHeaderString
            Authorization: basicAuthHeaderString
          }
        })
      }
      return next.handle(req);
  }
}
