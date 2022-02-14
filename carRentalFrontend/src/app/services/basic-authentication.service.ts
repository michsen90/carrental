import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { User } from '../users/users.component';

export class Authentication{
  constructor(public message: string) {}
}

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticatedUser'
export const ROLES = 'role'

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  user: User;

  constructor(private http: HttpClient) { }

  makeAuthentication(username, password) {
    
    let basicAuthHeaderString = 'Basic ' + btoa(username + ':' + password);
    

    console.log(this.user);
    
    let headers = new HttpHeaders({
        authorization: basicAuthHeaderString
      })

    return this.http.get<User>(`http://localhost:8080/basic/byUser/${username}`, {headers}).pipe(
      map(res => {
        this.user = res;
        sessionStorage.setItem(AUTHENTICATED_USER, username);
        sessionStorage.setItem(TOKEN, basicAuthHeaderString);
      })
    )

    /*return this.http.get<Authentication>(
      `http://localhost:8080/basic`,
      {headers}).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, basicAuthHeaderString);
            return data;
          }
        )
      );*/
    //console.log("Execute Hello World Bean Service")
  }


  getRole(){
    return sessionStorage.getItem(ROLES);
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER)
  }

  getAuthenticatedToken(){
      return sessionStorage.getItem(TOKEN);
    }
  

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER)
    sessionStorage.removeItem(TOKEN)
  }

}
