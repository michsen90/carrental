package com.app.car.rental.services;

import com.app.car.rental.model.Clients;
import com.app.car.rental.model.Users;
import com.app.car.rental.repository.ClientRepository;
import com.app.car.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    @Autowired
    private ClientRepository clientRepository;

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

    public boolean deleteClient(Long id){

        if (!isClientExists(id)) return false;
        Clients c = clientRepository.findClientByClientId(id);
        userRepository.deleteById(c.getUser().getId());
        clientRepository.deleteById(c.getId());
        return true;

    }

    public Clients addClient(Clients client){


        Clients c = clientRepository.save(client);
        userRepository.save(client.getUser());
        return c;
    }
}
