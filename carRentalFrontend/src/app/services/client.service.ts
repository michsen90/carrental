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

  API_URL_ADD = 'http://localhost:8080/apiAll/clientAdd';

  getClients(){
    return this.http.get<Client[]>(`${this.API_URL}/all`);
  }

  getClientByUsername(username){
    return this.http.get<Client>(`${this.API_URL}/getByUsername/${username}`);
  }

  getClientById(id){
    return this.http.get<Client>(`${this.API_URL}/getById/${id}`);
  }

  updateClient(id, client){
    return this.http.put<User>(`${this.API_URL}/update/${id}`, client);
  }

  /*createClient(client){
    return this.http.post<User>(`${this.API_URL}/add`, client);
  }*/
  createClient(client){
    return this.http.post<User>(`${this.API_URL_ADD}`, client);
  }

  deleteClient(id){
    return this.http.delete(`${this.API_URL}/delete/${id}`);
  }
}
