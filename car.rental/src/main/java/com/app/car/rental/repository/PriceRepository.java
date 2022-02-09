package com.app.car.rental.repository;

import com.app.car.rental.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Prices, Long> {



}
