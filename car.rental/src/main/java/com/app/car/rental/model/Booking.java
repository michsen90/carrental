package com.app.car.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Booking {





    @TableGenerator(name = "address_gen", initialValue = 99000)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "address_gen")
    private Long bookingId;

    private Date startDate;
    private Date endDate;
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

    public Booking(Date startDate, Date endDate, Cars car, Clients client) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
