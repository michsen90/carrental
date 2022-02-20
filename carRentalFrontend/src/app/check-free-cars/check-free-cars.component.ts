import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from '../bookings/bookings.component';
import { Car } from '../cars/cars.component';
import { Client } from '../clients/clients.component';
import { CarService } from '../services/car.service';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-check-free-cars',
  templateUrl: './check-free-cars.component.html',
  styleUrls: ['./check-free-cars.component.css']
})
export class CheckFreeCarsComponent implements OnInit {


  cars: Car[];
  car: Car;
  carId;
  booking: Booking;
  startDate =  new Date();
  endDate = new Date();
  startDateString;
  endDateString;

  username;
  client: Client;
  showFreeCars = false;


  constructor(
    private carService: CarService,
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {

    this.username = sessionStorage.getItem('AUTHENTICATED_USER');
    console.log(this.username);
    this.getClient(this.username);


    console.log('start date: ' + this.startDate);
    
    this.startDateString = this.datePipe.transform(this.startDate, 'yyyy-MM-dd');
    this.endDateString = this.datePipe.transform(this.endDate, 'yyyy-MM-dd');
    this.booking = new Booking(0, this.startDateString, this.endDateString, this.client, this.car);

  }

  getClient(username){
    this.clientService.getClientByUsername(username).subscribe(
      res => {
        this.client = res;
      }
    )
  }

  findCars(){

    console.log('date: ' + this.startDate);
    this.carService.getFreeCars(this.startDateString, this.endDateString).subscribe(
      res => {
        
        
        this.cars = res;
        if(this.cars != null){
          this.showFreeCars = true;

        }else{

        }
      }
    )
  }

}
