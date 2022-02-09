package com.app.car.rental.controller;

import com.app.car.rental.model.Clients;
import com.app.car.rental.model.Users;
import com.app.car.rental.repository.ClientRepository;
import com.app.car.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/public")
public class PublicRestApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/test1")
    public String test1(){ return "API TEST 1"; }

    @GetMapping("/test2")
    public String test2(){ return "API TEST 2"; }

    @GetMapping("users")
    public List<Users> users(){
        return userRepository.findAll();
    }

    @GetMapping("clients")
    public List<Clients> clients() { return clientRepository.findAll(); }

}
