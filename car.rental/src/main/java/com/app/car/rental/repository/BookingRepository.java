package com.app.car.rental.repository;

import com.app.car.rental.model.Booking;
import com.app.car.rental.model.Cars;
import com.app.car.rental.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findAllByClient(Clients client);
    List<Booking> findAllByCar(Cars car);
    Booking getBookingsByBookingId(Long id);

}
