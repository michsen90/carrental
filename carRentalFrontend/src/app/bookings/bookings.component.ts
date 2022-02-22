import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../cars/cars.component';
import { Client } from '../clients/clients.component';
import { BookingService } from '../services/booking.service';

export class Booking{
  constructor(
    public bookingId: number,
    public startDate: Date,
    public endDate: Date,
    public finalPrice: number,
    public client?: Client,
    public car?: Car
  ){}
}

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  bookings: Booking[];
  idBooking: number;

  constructor(
    private bookingService: BookingService,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.retriveBookingList();
  }

  retriveBookingList(){
    this.bookingService.getBookingList().subscribe(
      res => {
        this.bookings = res;
        console.log(this.bookings);
      }
    )
  }

  newBooking(){
    this.router.navigate(['check-free-cars', -1]);
  }

  updateBooking(b){
    this.router.navigate(['check-free-cars', b.bookingId]);
  }

  deleteBooking(b){
    this.bookingService.deleteBooking(b.bookingId).subscribe(
      res => {
        this.retriveBookingList();
      }
    )
  }

}
