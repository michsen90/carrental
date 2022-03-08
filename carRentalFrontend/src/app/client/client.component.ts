import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../clients/clients.component';
import { ClientService } from '../services/client.service';
import { User } from '../users/users.component';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  id: number;
  client: Client;
  user: User;
  idUser: number;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute 
    ) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['idClient'];
    this.user = new User(this.idUser, '', '', 1, 'USER', '');
    
    this.client = new Client(this.id, '', '', '', '', '', '', '', this.user );

    if(this.id != -1){
      this.getClientById(this.id);
    }

    
  }

  getClientById(id){
    this.clientService.getClientById(id).subscribe(
      res => {
        this.client = res;
      }
    )
  }

  saveClient(){
    console.log(this.client);
    if(this.id == -1){
      this.clientService.createClient(this.client).subscribe(
        res => {
          //console.log('new client: ' + this.client);
          //console.log(res);
        this.router.navigate(['login']);
        }) 
    } else {
      this.clientService.updateClient(this.id, this.client).subscribe(
        res => {
          this.router.navigate(['clients']);
        }
      )
    }
  }

}
