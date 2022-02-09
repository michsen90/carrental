package com.app.car.rental.repository;

import com.app.car.rental.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {

    List<Cars> findByBrandAndModel(String brand, String model);
    List<Cars> findByEngine(String engine);
    Cars findCarByCarId(Long id);
    List<Cars> findByModel(String model);

}
