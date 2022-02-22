import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from '../bookings/bookings.component';
import { Car } from '../cars/cars.component';
import { Client } from '../clients/clients.component';
import { BookingService } from '../services/booking.service';
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
  finalPrice; 
  qtyDays: number;

  errorMessageWrongDates = 'Data zakończenia najmu musi następować przynajmniej 1 dzień później niż data rozpoczęcią!';
  invalidDates = false;


  constructor(
    private carService: CarService,
    private clientService: ClientService,
    private bookingService: BookingService,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {

    this.username = sessionStorage.getItem('authenticatedUser');

    this.getClient(this.username);
    
    this.startDateString = this.datePipe.transform(this.startDate, 'yyyy-MM-dd');
    this.endDateString = this.datePipe.transform(this.endDate, 'yyyy-MM-dd');
    this.booking = new Booking(0, this.startDateString, this.endDateString, this.finalPrice, this.client, this.car);
    
    
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
        this.booking.client = res;
        console.log(this.booking.client);
        console.log(this.booking);
      }
    )
  }

  findCars(){
    
    if(this.booking.endDate <= this.booking.startDate){
      this.invalidDates = true;
    }else{
      this.invalidDates = false;
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

  takeCar(car){

    this.car = car;
    this.carId = car.id;
    this.car.idCar = this.carId;

    this.carService.getCarById(this.carId).subscribe(
      res => {
        this.booking.car = res;
      }
    )
    
  
    this.setFinalPrice(this.car);
    //console.log(this.finalPrice);

  }

  bookingThisCar(){

    console.log(this.booking);
    this.bookingService.createBooking(this.booking).subscribe(
      
    )
    
  }

  setFinalPrice(c){
    //console.log(c);
    this.isChoosen = true;
    
    this.qtyDays = this.diffDates(this.booking.startDate, this.booking.endDate);
    //console.log('qtyDays: ' + this.qtyDays);

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
    this.booking.finalPrice = this.finalPrice;

    //console.log('final price: ' +  this.finalPrice);
    
  }

  changeCar(){
    this.isChoosen = false;
  }

  diffDates(startDateString, endDateString){
    
    var date1 = new Date(startDateString);
    var date2 = new Date(endDateString);
    var difference_In_Time = date2.getTime() - date1.getTime(); 
    var difference_In_Days = difference_In_Time / (1000 * 3600 * 24);
    return difference_In_Days;
  }



  

}
