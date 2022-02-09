import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { BasicAuthenticationService } from './basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{

  constructor(
    private router: Router,
    private basicAuthenticationService: BasicAuthenticationService
  ) { }
  
  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    
    if(this.basicAuthenticationService.isUserLoggedIn())
      return true;
    
    this.router.navigate(['login']);
    return false;
  }
}
