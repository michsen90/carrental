package com.app.car.rental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MainController {


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
