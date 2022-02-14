import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Prices } from '../prices/prices.component';
import { CarService } from '../services/car.service';

export class Car{
  constructor(
    public idCar: number,
    public brand: string,
    public model: string,
    public productionYear: Date,
    public body: string,
    public airConditioning: string,
    public numberPassengers: string,
    public doors: number,
    public gps: string,
    public engine: string,
    public gearbox: string,
    public color: string,
    public price: Prices
  ){}
}

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  cars: Car[];
  idCar: number;
  priceAfter3Days: number;
  priceAfterOneWeek: number
  constructor(
    private carService: CarService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.retriveAllCars();

  }

  retriveAllCars(){
    this.carService.getAllCars().subscribe(
      res => {
        
        this.cars = res;
        //console.log(this.cars);
        //console.log('production year 1 cars: ' + this.cars[1].productionYear);
      }
    )
  }

  saveCar(){
    this.router.navigate(['car', -1]);
  }

  updateCar(car){
    console.log('id car: ' + car.id);
    this.router.navigate(['car', car.id]);
  }

}
