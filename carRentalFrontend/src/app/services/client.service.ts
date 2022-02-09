import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { Client } from '../clients/clients.component';
import { User } from '../users/users.component';
import { Booking } from '../bookings/bookings.component';



@Injectable({
  providedIn: 'root'
})
export class ClientService {


  constructor(private http: HttpClient) { }

  API_URL = 'http://localhost:8080/clients';

  getClients(){
    return this.http.get<Client[]>(`${this.API_URL}/all`);
  }

  getClientById(id){
    return this.http.get<Client>(`${this.API_URL}/getById/${id}`);
  }

  updateClient(id, client){
    return this.http.put<User>(`${this.API_URL}/update/${id}`, client);
  }
}
