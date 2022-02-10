package com.app.car.rental.authentication;

import com.app.car.rental.model.Users;
import com.app.car.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/basic")
    public Authentication getAuthentication(){

        return new Authentication("You are authenticated !!!");
    }

    @GetMapping("/basic/byUser/{username}")
    public Users getAuthenticatedUser(@PathVariable("username") String username){
        Users u = userRepository.findByUsername(username);
        return u;

    }

    @GetMapping("/basic/getUser/{username}")
    public Users getUserByUsername(@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }


}
