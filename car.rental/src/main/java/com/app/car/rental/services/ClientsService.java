package com.app.car.rental.services;

import com.app.car.rental.model.Clients;
import com.app.car.rental.model.Users;
import com.app.car.rental.repository.ClientRepository;
import com.app.car.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Clients getClientById(Long id){

        return clientRepository.findClientByClientId(id);
    }

    public boolean isClientExists(Long id){
        Clients c = clientRepository.findClientByClientId(id);
        if(c != null) return true;
        return false;

    }

    public Clients addClient(Clients client){

        System.out.println("Client: " + client);
        Clients c = clientRepository.save(client);
        String password = client.getUser().getPassword();
        client.getUser().setPassword(passwordEncoder.encode(password));
        Users u = client.getUser();
        userRepository.save(u);
        userRepository.save(client.getUser());
        return c;
    }

    public void deleteClient(Long id){
        Clients c = clientRepository.findClientByClientId(id);
        Users u = c.getUser();
        userRepository.delete(u);
        clientRepository.delete(c);
    }

    public Clients getClientByUsername(String username){
        Clients c = clientRepository.findByUserUsername(username);
        return c;
    }
}
