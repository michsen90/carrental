package com.app.car.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Booking {





    @TableGenerator(name = "address_gen", initialValue = 99000)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "address_gen")
    private Long bookingId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Double finalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Cars car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "booking")
    private Clients client;

    protected Booking() {}

    public Booking(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking(Date startDate, Date endDate, double finalPrice, Cars car, Clients client) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
        this.car = car;
        this.client = client;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
