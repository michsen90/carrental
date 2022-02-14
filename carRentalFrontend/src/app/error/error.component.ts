import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  constructor() { }

  errorMessage = 'Something went wrong! Please contact Support! ';
  
  ngOnInit(): void {
  }

  errorClientNotFound(){

  }

  errorClientIdIsUndefined(id){
    return 'The id ' + id + ' is undefined or null';
  }

}
