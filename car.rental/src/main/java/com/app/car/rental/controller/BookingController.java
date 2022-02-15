package com.app.car.rental.controller;

import com.app.car.rental.model.Booking;
import com.app.car.rental.repository.BookingRepository;
import com.app.car.rental.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> allBookedCars(){
        List<Booking> listOfBookedCars = bookingRepository.findAll();
        return new ResponseEntity<>(listOfBookedCars, HttpStatus.OK);
    }

    @GetMapping("/getByClient/{clientId}")
    public ResponseEntity<List<Booking>> bookingsByClient(@PathVariable("clientId") Long clientId){

        List<Booking> listOfBookingsByClient = bookingService.findAllByClient(clientId);
        return new ResponseEntity<>(listOfBookingsByClient, HttpStatus.OK);

    }

    @GetMapping("/getByCarModel/{carId}")
    public ResponseEntity<List<Booking>> listOfBookingsByCarModel(@PathVariable("carId") String model){
        List<Booking> listBookedCarsByModel = bookingService.findAllByCarModel(model);
        return new ResponseEntity<>(listBookedCarsByModel, HttpStatus.OK);
    }

    /**@GetMapping("/getByCar/{carId}")
    public ResponseEntity<List<Booking>> bookingsByCars(@PathVariable("carId") Long carId){

        List<Booking> listOfBookingsByCar = bookingService.findAllByCar(carId);
        return new ResponseEntity<>(listOfBookingsByCar, HttpStatus.OK);
    }*/

    @PostMapping("/addBooking")
    public ResponseEntity<Booking> newCarBooking(@RequestBody Booking book){

        Booking newBooking = bookingRepository.save(book);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);

    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("bookingId") Long id, @RequestBody Booking booking){

        Booking b = bookingService.updateBooking(id, booking);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("bookingId") Long id){

        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }



}
