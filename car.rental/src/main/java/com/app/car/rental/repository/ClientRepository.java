package com.app.car.rental.repository;

import com.app.car.rental.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {
    Clients findByEmail(String email);
    Clients findClientByClientId(Long id);

}
