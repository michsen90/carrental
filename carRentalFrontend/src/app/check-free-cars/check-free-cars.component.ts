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
  isChoosen = false;
  price;
  finalPrice: number; 
  qtyDays: number;


  constructor(
    private carService: CarService,
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {

    this.username = sessionStorage.getItem('authenticatedUser');
    

    this.getClient(this.username);
    
    this.startDateString = this.datePipe.transform(this.startDate, 'yyyy-MM-dd');
    this.endDateString = this.datePipe.transform(this.endDate, 'yyyy-MM-dd');
    this.booking = new Booking(0, this.startDateString, this.endDateString, this.client, this.car);
    
  }

  getCar(carId){
    return this.carService.getCarById(carId).subscribe(
      res => {
        
        this.car = res;
        
      }
    )
  }

  getClient(username: string){
    this.clientService.getClientByUsername(username).subscribe(
      res => {
        this.client = res;
      }
    )
  }

  findCars(){

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

  takeCar(car){

    this.car = car;

    
    //console.log(this.car);
    this.booking.car = this.car;
  
    this.setFinalPrice(this.car);
    //console.log(this.finalPrice);

  }

  bookingThisCar(car){

    this.booking.car = car;
    this.booking.client = this.client; 
    console.log(this.booking.startDate);
    console.log(this.client);

    console.log('booking' + this.booking);
  }

  setFinalPrice(c){
    console.log(c);
    this.isChoosen = true;
    
    this.qtyDays = this.diffDates(this.booking.startDate, this.booking.endDate);
    console.log('qtyDays: ' + this.qtyDays);

    switch(true){
      case (this.qtyDays<3):
        this.finalPrice = c.price.pricePerDay * this.qtyDays;
        break;
      case(this.qtyDays>=3 && this.qtyDays<=7):
        this.finalPrice = (c.price.pricePerDay * c.price.discountAfterThreeDays) * this.qtyDays;
        break;
      case(this.qtyDays > 7 && this.qtyDays < 28):
        this.finalPrice = (c.price.pricePerDay * c.price.discountAfterOneWeek) * this.qtyDays;
        break;
      case(this.qtyDays > 28):
        if(this.booking.endDate.getMonth() - this.booking.startDate.getMonth() == 1){
          this.finalPrice = c.price.pricePerMonth
        }else{
          this.finalPrice = c.price.pricePerMonth * (this.booking.endDate.getMonth() - this.booking.startDate.getMonth());
        }
    }

    console.log('final price: ' +  this.finalPrice);
    
  }

  changeCar(){
    this.isChoosen = false;
  }

  diffDates(startDateString, endDateString){
    
    var date1 = new Date(startDateString);
    var date2 = new Date(endDateString);
    var Difference_In_Time = date2.getTime() - date1.getTime(); 
    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
    return Difference_In_Days;
  }

  

}
