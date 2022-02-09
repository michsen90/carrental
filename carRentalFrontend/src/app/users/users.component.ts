import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

export class User{
  constructor(
    public id: number,
    public username: string,
    public password: string,
    public active: number,
    public roles: string,
    public permissions: string
  ){

  }
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {


  users: User[];
  

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.userService.getUsers().subscribe(
      data => {
        console.log(data);        
        this.users = data;
        console.log(this.users);
      }
    )
  }

  



}
