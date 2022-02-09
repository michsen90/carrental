import { Component, OnInit } from '@angular/core';

export class Booking{
  constructor(
    public idBooking: number,
    public startDate: Date,
    public endDate: Date
  ){}
}

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
