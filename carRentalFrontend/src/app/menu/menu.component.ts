import { Component, OnInit } from '@angular/core';
import { BasicAuthenticationService } from '../services/basic-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private basicAuthenticationService: BasicAuthenticationService) { }

  role;
  admin = 'ADMIN';

  ngOnInit(): void {
    this.role = this.basicAuthenticationService.getRole();
    
  }

  isAuthenticatedUser(){
    return this.basicAuthenticationService.isUserLoggedIn();
  }

  isAdmin(){

    //console.log('role: ' + this.role);
    if(this.role == this.admin){
      return true;
    }
    else{
      return false;
    }
  }

}
