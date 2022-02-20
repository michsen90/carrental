package com.app.car.rental.services;

import com.app.car.rental.model.Cars;
import com.app.car.rental.model.Prices;
import com.app.car.rental.repository.CarRepository;
import com.app.car.rental.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CarsService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PriceRepository priceRepository;

    //FIND CAR BY ID
    public Cars getCarById(Long id){
        return carRepository.findCarByCarId(id);
    }

    //DATE FORMATTER
    public Date getDateFromString(String dateString) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        return date;
    }

    public boolean isCarExists(Long id){
        Cars c = carRepository.findCarByCarId(id);
        if(c == null){
            return false;
        }
        return true;
    }

    //GET CARS METHOD BY ENGINE
    public List<Cars> getCarsByEngine(String engine){

        List<Cars> allCars = carRepository.findAll();
        List<Cars> searchingCarsList = new ArrayList<Cars>();

        for(Cars car: allCars){
            if(findInString(car.getEngine(), engine)){
                searchingCarsList.add(car);
            }
        }
        return searchingCarsList;
    }

    //GET CARS METHOD BY BRAND AND MODEL
    public List<Cars> getCarsByBrandAndModel(String brand, String model){

        List<Cars> allCars = carRepository.findAll();
        List<Cars> searchingCarList = new ArrayList<Cars>();
        for(Cars car: allCars){
            if(findModelAndBrand(car.getModel(), model, car.getBrand(), brand)){
                searchingCarList.add(car);
            }
        }
        return searchingCarList;
    }

    //GET CARS METHOD BY MODEL
    public List<Cars> carsListByModel(String model) {
        List<Cars> allCars = carRepository.findAll();
        List<Cars> carListByModel = new ArrayList<Cars>();

        for(Cars car: allCars){
            if(findInString(car.getModel(), model)){
                carListByModel.add(car);
            }
        }
        return carListByModel;
    }

    public Cars saveCar(Cars car){
        Cars c = carRepository.save(car);
        Prices p = car.getPrice();
        priceRepository.save(p);

        return car;
    }

    public void deleteCar(Long id){
        Cars c = carRepository.findCarByCarId(id);
        Prices p = c.getPrice();
        priceRepository.delete(p);
        carRepository.delete(c);

    }


    public boolean findInString(String text, String word){
        return text.toLowerCase().indexOf(word.toLowerCase()) > -1;
    }

    public boolean findModelAndBrand(String textModel, String wordModel, String textBrand, String wordBrand){
        return textModel.toLowerCase().indexOf(wordModel.toLowerCase()) > -1 && textBrand.toLowerCase().indexOf(wordBrand.toLowerCase()) > -1;
    }


}
