import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from '../bookings/bookings.component';
import { TOKEN } from '../services/basic-authentication.service';
import { ClientService } from '../services/client.service';
import { User } from '../users/users.component';

export class Client{
  constructor(
    public idClient: number,
    public firstname: string,
    public lastname: string,
    public email: string,
    public city: string,
    public street: string,
    public number: string,
    public phone: string,
    public user: User,
    public bookings: Booking[]
  ){}
}

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clients: Client[];
  res: any[];  
  client: Client;
  id;

  constructor(
    private clientService: ClientService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getClientsList();
  }

  getClientsList(){

    console.log(sessionStorage.getItem(TOKEN))
    this.clientService.getClients().subscribe(
      data => {
       
        this.clients = data;
        console.log(this.clients);
      }
    )
  }

  getClientById(){
    this.clientService.getClientById(1).subscribe(data => {
      this.client = data;
      console.log(this.client);
    })
  }

  
  updateClient(id){
    console.log(id);
    this.router.navigate(['client', id]);
  }

}
