import { Component, OnInit } from '@angular/core';

export class Prices{
  constructor(
    public priceId: number,
    public pricePerDay: number,
    public discountAfterThreeDays: number,
    public discountAfterOneWeek: number,
    public pricePerMonth: number
  ){}
}

@Component({
  selector: 'app-prices',
  templateUrl: './prices.component.html',
  styleUrls: ['./prices.component.css']
})
export class PricesComponent implements OnInit {

  prices: Prices[];
  id: number;

  constructor() { }

  ngOnInit(): void {
  }

  getAllPrices(){

  }

}
