package com.app.car.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    private Double pricePerDay;
    private Double discountAfterThreeDays;
    private Double discountAfterOneWeek;
    private Double pricePerMonth;

    @JsonIgnore()
    @OneToOne(mappedBy = "price")
    private Cars car;

    protected Prices () {}

    /*public Prices(Double pricePerDay, Double discountAfterThreeDays, Double discountAfterOneWeek, Double pricePerMonth) {

        this.pricePerDay = pricePerDay;
        this.discountAfterThreeDays = discountAfterThreeDays;
        this.discountAfterOneWeek = discountAfterOneWeek;
        this.pricePerMonth = pricePerMonth;
    }*/

    public Prices(Double pricePerDay, Double pricePerMonth) {
        this.pricePerDay = pricePerDay;
        this.pricePerMonth = pricePerMonth;
        this.discountAfterThreeDays = 0.90;
        this.discountAfterOneWeek = 0.70;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getDiscountAfterThreeDays() {
        return discountAfterThreeDays;
    }

    public void setDiscountAfterThreeDays(Double discountAfterThreeDays) {
        this.discountAfterThreeDays = discountAfterThreeDays;
    }

    public Double getDiscountAfterOneWeek() {
        return discountAfterOneWeek;
    }

    public void setDiscountAfterOneWeek(Double discountAfterOneWeek) {
        this.discountAfterOneWeek = discountAfterOneWeek;
    }

    public Double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(Double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }
}
