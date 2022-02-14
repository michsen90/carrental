package com.app.car.rental.controller;


import com.app.car.rental.model.Clients;
import com.app.car.rental.repository.ClientRepository;
import com.app.car.rental.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/clients")
public class ClientsController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientsService clientsService;

    @GetMapping("/all")
    public ResponseEntity<List<Clients>> findClients(){
        List<Clients> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable Long id){
        Clients client = clientRepository.findClientByClientId(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Clients> getClientByEmail(@PathVariable String email){
        Clients client = clientRepository.findByEmail(email);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Clients> newClient(@RequestBody Clients client){

        /**Clients newClient = clientRepository.save(client);*/

        Clients newClient = clientsService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Clients> updateClient(@RequestBody Clients client, @PathVariable Long id){
        Clients c = clientRepository.findClientByClientId(id);

        if(!clientsService.isClientExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Clients updatedClient = clientRepository.save(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){

        if(!clientsService.deleteClient(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
