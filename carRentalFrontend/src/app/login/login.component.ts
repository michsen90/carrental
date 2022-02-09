import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../services/basic-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  errorMessage = 'Invalid Credential!';
  invalidLogin = false;


  constructor(
    private basicAuthenticationService: BasicAuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  handleLogin() {
       
    this.basicAuthenticationService.makeAuthentication(this.username, this.password)
        .subscribe({
         next:  data => {
            console.log(data);
            this.router.navigate(['welcome']);
            this.invalidLogin = false;
            
          }, 
          error: error => {
            this.invalidLogin = true;
          }        
        })
  }

}
