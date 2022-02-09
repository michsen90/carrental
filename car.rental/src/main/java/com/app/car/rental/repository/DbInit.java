package com.app.car.rental.repository;

import com.app.car.rental.model.*;
import com.app.car.rental.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DbInit implements   CommandLineRunner {


    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private CarRepository carRepository;
    private CarsService carsService;
    private BookingRepository bookingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, ClientRepository clientRepository, CarRepository carRepository, CarsService carsService, BookingRepository bookingRepository){
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.carsService = carsService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        /**
        Clients cmichal = new Clients("Michal", "Senkowicz", "michalsenkowicz23@gmail.com", "Kraków", "Lubostroń", "33/81", "793100200");
        Clients cadmin = new Clients("Admin", "Admin", "admin@gmail.com", "Kraków", "Pawia", "5", "600254121");
        Users michal = new Users("michal", passwordEncoder.encode("michal123"), "USER", "TEST_1");
        Users admin = new Users("admin", passwordEncoder.encode("admin123"), "ADMIN", "TEST_1, TEST_2");

        cmichal.setUser(michal);
        cadmin.setUser(admin);


        List<Clients> clients = Arrays.asList(cmichal, cadmin);
        this.clientRepository.saveAll(clients);

        List<Users> users = Arrays.asList(michal, admin);
        this.userRepository.saveAll(users);

        Prices p1 = new Prices(180.00, 4000.00);
        Prices p2 = new Prices(150.00, 3000.00);
        Prices p3 = new Prices(190.00, 3800.00);
        Prices p4 = new Prices(110.00, 2800.00);
        Prices p5 = new Prices(140.00, 3200.00);

        Date d1 = carsService.getDateFromString("2017-05-01");
        Date d2 = carsService.getDateFromString("2021-12-01");
        Date d3 = carsService.getDateFromString("2019-01-05");
        Date d4 = carsService.getDateFromString("2020-10-29");
        Date d5 = carsService.getDateFromString("2021-09-03");

       Cars car1 = new Cars("Volkswagen", "Passat", d1, "combi", "YES", "5", 5,
               "YES", "Diesel 2.0", "Manual", "Black metalic");
       Cars car2 = new Cars("Ford", "Focus", d2, "sedan", "YES", "4", 4,
               "YES", "Diesel 1.5", "Automatic", "Red metalic");
       Cars car3 = new Cars("Volkswagen", "Passat", d3, "combi", "YES", "5",
               4, "YES", "Gasoline 2.0 ", "Automatic", "White perl");
       Cars car4 = new Cars("Skoda", "Citi", d4, "hatchback", "YES", "4", 3,
               "NO", "Electric", "Automatic", "Green");
       Cars car5 = new Cars("Dacia", "Duster", d5, "Crossover", "YES", "5", 5,
               "NO", "Gasoline + LPG 1.0", "Manual", "Grey");

        car1.setPrice(p1);
        car2.setPrice(p2);
        car3.setPrice(p3);
        car4.setPrice(p4);
        car5.setPrice(p5);


       List<Cars> cars = Arrays.asList(car1, car2, car3, car4, car5);

       this.carRepository.saveAll(cars);

        Booking b1 = new Booking(new Date(), new Date(), car1, cmichal);

        this.bookingRepository.save(b1);

        */
    }

}
