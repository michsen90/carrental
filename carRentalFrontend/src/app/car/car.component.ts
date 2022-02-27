import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../cars/cars.component';
import { Prices } from '../prices/prices.component';
import { CarService } from '../services/car.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  car: Car;
  id: number;
  price: Prices;
  idPrice: number
  productionYear;

  tak = 'TAK';
  nie = 'NIE';

  dwa = 2;
  trzy = 3;
  cztery = 4;
  piec = 5;
  siedem = 7;

  
  constructor(
    private carService: CarService,
    private router: Router,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['idCar'];

    this.price = new Prices(this.idPrice, 0, 0.8, 0.7, 0);

    this.productionYear = this.datePipe.transform(new Date(), 'yyyy-MM-dd');

    this.car = new Car(this.id, '', '', this.productionYear, '', '', '', 0, '', '', '', '', this.price);

    console.log('id: ' + this.id);
    console.log('car' + this.car)

    if(this.id != -1){
      this.getCarById(this.id);
    }

  }

  getCarById(id){
    console.log('id from getCarById: '+id)
    this.carService.getCarById(id).subscribe(
      res => {
        this.car = res;
        
      }
    )
  }



   saveCar(){
     if(this.id == -1){

       
      this.carService.createCar(this.car).subscribe(
        res => {
          console.log(this.car);
          
          this.router.navigate(['cars']);
        }
      )
     }else{
      this.carService.updateCar(this.id, this.car).subscribe(
        res => {
          console.log(this.car);
          console.log(res);
          this.router.navigate(['cars']);
        }
      )
     }
   }
   

}
