package com.app.car.rental.controller;

import com.app.car.rental.model.Booking;
import com.app.car.rental.pdf.EmailService;
import com.app.car.rental.pdf.Mail;
import com.app.car.rental.pdf.PDFBooking;
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


    String EMAIL_SEND = "michalsenkowicz23@gmail.com";

    @Autowired
    private EmailService emailService;

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

    @GetMapping("/getBookingById/{bookingId}")
    public ResponseEntity<Booking> getBookingsById(@PathVariable("bookingId") Long id){

        Booking b = bookingService.getBookingById(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    /**@GetMapping("/getByCar/{carId}")
    public ResponseEntity<List<Booking>> bookingsByCars(@PathVariable("carId") Long carId){

        List<Booking> listOfBookingsByCar = bookingService.findAllByCar(carId);
        return new ResponseEntity<>(listOfBookingsByCar, HttpStatus.OK);
    }*/

    @PostMapping("/addBooking")
    public ResponseEntity<Booking> newCarBooking(@RequestBody Booking book){

        Booking newBooking = bookingService.saveBooking(book);
        PDFBooking pdfBooking = new PDFBooking();
        pdfBooking.createPdfBooking(newBooking);
        String fileName = pdfBooking.getFileName(String.valueOf(newBooking.getBookingId()));
        prepareEmailToSend(newBooking, fileName);
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


    public void prepareEmailToSend(Booking booking, String filename){
        Mail mail = new Mail();
        mail.setMailFrom(EMAIL_SEND);
        mail.setMailTo(booking.getClient().getEmail());
        mail.setMailSubject("Potwierdzenie z??o??enia zam??wienia numer: " + booking.getBookingId() + " !");
        mail.setMailContent("Witaj " + booking.getClient().getFirstname() + " " + booking.getClient().getFirstname() +" ! \n\n" +
                "   W za????czeniu przesy??amy potwierdzenie dokonania rezerwacji na samoch??d: " + booking.getCar().getBrand() + " " + booking.getCar().getModel() + "\n" +
                "Samoch??d jest do odbioru w naszej siedzibie przy Lotnisku od godziny 9:00 do 17:00, przekroczenie tego okresu, b??dzie wi??za??o si?? z naliczeniem koszt??w, \n" +
                "zwi??zanych z przetrzymanie pojazdu, tj. " + booking.getCar().getPrice().getPricePerDay() + " PLN brutto za dobr??. " +
                "\n\n" +
                "Je??li masz jakie?? pytania, skontaktuj si?? z nami telefonicznie lub mailowo na: \n" +
                "Email: " + EMAIL_SEND);
        emailService.sendEmail(mail, filename);
    }

}
