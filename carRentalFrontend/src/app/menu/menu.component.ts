import { Component, OnInit } from '@angular/core';
import { BasicAuthenticationService } from '../services/basic-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit(): void {
  }

  isAuthenticatedUser(){
    return this.basicAuthenticationService.isUserLoggedIn();
  }

}
