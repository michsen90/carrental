import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Car } from '../cars/cars.component';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(
    private http: HttpClient
  ) { }

    API_URL_CARS = 'http://localhost:8080/cars';

    getAllCars(){
      return this.http.get<Car[]>(`${this.API_URL_CARS}/all`);
    }

    getCarById(carId){
      return this.http.get<Car>(`${this.API_URL_CARS}/getCarById/${carId}`)
    }

    createCar(car){
      return this.http.post<Car>(`${this.API_URL_CARS}/add`, car);
    }

    updateCar(carId, car){
      return this.http.put<Car>(`${this.API_URL_CARS}/update/${carId}`, car);
    }
}
