package com.app.car.rental.services;

import com.app.car.rental.model.Booking;
import com.app.car.rental.model.Cars;
import com.app.car.rental.model.Clients;
import com.app.car.rental.repository.BookingRepository;
import com.app.car.rental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private CarsService carsService;

    public Booking getBookingById(Long id){

        Booking b = bookingRepository.getBookingsByBookingId(id);
        return b;
    }

    public List<Booking> findAllByClient(Long clientId){

        Clients client = clientsService.getClientById(clientId);
        List<Booking> listBookingsByClient = bookingRepository.findAllByClient(client);

        return listBookingsByClient;
    }

    public List<Booking> findAllByCarModel(String model){

        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> bookingListModel = new ArrayList<Booking>();

        for(Booking book: allBookings){
            if(findInString(book.getCar().getModel(), model)){
                bookingListModel.add(book);
            }
        }

        return bookingListModel;
    }

    public List<Booking> findAllByCar(Long carId){

        Cars car = carsService.getCarById(carId);
        List<Booking> listOfBookingsByCars = bookingRepository.findAllByCar(car);
        return listOfBookingsByCars;

    }

    public Booking updateBooking(Long id, Booking booking){
        Booking b = bookingRepository.getById(id);
        if(b == null){
            return null;
        }
        bookingRepository.save(booking);
        return booking;
    }

    public void deleteBooking(Long id){
        Booking b = bookingRepository.getById(id);
        bookingRepository.delete(b);

    }

    public boolean findInString(String text, String word){
        return text.toLowerCase().indexOf(word.toLowerCase()) > -1;
    }
}
