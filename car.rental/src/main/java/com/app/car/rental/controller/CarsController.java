package com.app.car.rental.controller;

import com.app.car.rental.model.Cars;
import com.app.car.rental.repository.CarRepository;
import com.app.car.rental.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CarsController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarsService carsService;

    @GetMapping("/all")
    public ResponseEntity<List<Cars>> getAllCars(){
        List<Cars> cars = carRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/getCarById/{carId}")
    public ResponseEntity<Cars> getCarById(@PathVariable("carId") Long carId){
        Cars c = carsService.getCarById(carId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/get/{brand}/{model}")
    public ResponseEntity<List<Cars>> getCarByBrandAndModel(@PathVariable("brand") String brand, @PathVariable("model") String model){

        List<Cars> cars = carsService.getCarsByBrandAndModel(brand, model);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/get/engine/{engine}")
    public ResponseEntity<List<Cars>> getCarsByEngine(@PathVariable("engine") String engine){
        List<Cars> cars = carsService.getCarsByEngine(engine);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cars> newCar(@RequestBody Cars car){

        Cars c = carsService.saveCar(car);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<Cars> updateCar(@PathVariable("carId") Long id, @RequestBody Cars car){
        if(!carsService.isCarExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Cars c = carsService.saveCar(car);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }

    }


}
