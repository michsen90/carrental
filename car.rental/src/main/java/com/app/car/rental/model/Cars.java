package com.app.car.rental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String brand;
    private String model;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productionYear;
    private String body;
    private String airConditioning;
    private String numberPassengers;
    private int doors;
    private String gps;
    private String engine;
    private String gearbox;
    private String color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Prices price;

    @OneToMany(mappedBy = "car")
    @JsonManagedReference
    private List<Booking> booking;

    protected Cars() {}

    public Cars(String brand, String model, Date productionYear, String body, String airConditioning,
                String numberPassengers, int doors, String gps, String engine, String gearbox, String color) {

        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.body = body;
        this.airConditioning = airConditioning;
        this.numberPassengers = numberPassengers;
        this.doors = doors;
        this.gps = gps;
        this.engine = engine;
        this.gearbox = gearbox;
        this.color = color;
    }

    public Long getId() {
        return carId;
    }

    public void setId(Long id) {
        this.carId = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Date productionYear) {
        this.productionYear = productionYear;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(String airConditioning) {
        this.airConditioning = airConditioning;
    }

    public String getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(String numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Prices getPrice() {
        return price;
    }

    public void setPrice(Prices price) {
        this.price = price;
    }
}
