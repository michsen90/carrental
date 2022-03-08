package com.app.car.rental.controller;

import com.app.car.rental.model.Clients;
import com.app.car.rental.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiAll")
public class MainController {

    @Autowired
    private ClientsService clientsService;

    @PostMapping("/clientAdd")
    public ResponseEntity<Clients> newClient(@RequestBody Clients client){

        /**Clients newClient = clientRepository.save(client);*/

        Clients newClient = clientsService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public String sendMessageForAll(){
        String message = "It's new message for all users! ";
        return message;
    }

    @GetMapping("/admin")
    public String sendMessageForAdmin(){
        String message = "It's new message for admin! ";
        return message;
    }

    @GetMapping("/user")
    public String sendMessageForUser(){
        String message = "It's new message for user! ";
        return message;
    }


}
