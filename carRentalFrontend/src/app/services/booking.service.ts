import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from '../bookings/bookings.component';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  API_URL_BOOKING = 'http://localhost:8080/booking';

  constructor(
    private http: HttpClient
  ) { }

  getBookingList(){
    return this.http.get<Booking[]>(`${this.API_URL_BOOKING}/all`);
  }

  getBookingById(id){
    return this.http.get<Booking>(`${this.API_URL_BOOKING}/getBookingById/${id}`);
  }

  createBooking(booking){
    return this.http.post<Booking>(`${this.API_URL_BOOKING}/addBooking`, booking);
  }

  updateBooking(id, booking){
    return this.http.put<Booking>(`${this.API_URL_BOOKING}/update/${id}`, booking);
  }

  deleteBooking(id){
    return this.http.delete(`${this.API_URL_BOOKING}/delete/${id}`);
  }
}
