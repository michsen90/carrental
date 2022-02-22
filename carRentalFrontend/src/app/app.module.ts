import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorComponent } from './error/error.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { LogoutComponent } from './logout/logout.component';
import { UsersComponent } from './users/users.component';
import { ClientsComponent } from './clients/clients.component';
import { BookingsComponent } from './bookings/bookings.component';
import { HttpIntercepterBasicAuthService } from './service/http-intercepter-basic-auth.service';
import { ClientComponent } from './client/client.component';
import { CarsComponent } from './cars/cars.component';
import { PricesComponent } from './prices/prices.component';
import { CarComponent } from './car/car.component';
import { CheckFreeCarsComponent } from './check-free-cars/check-free-cars.component';
import { DatePipe } from '@angular/common';
import { MakeBookingCarComponent } from './make-booking-car/make-booking-car.component';

 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    ErrorComponent,
    MenuComponent,
    FooterComponent,
    LogoutComponent,
    UsersComponent,
    ClientsComponent,
    BookingsComponent,
    ClientComponent,
    CarsComponent,
    PricesComponent,
    CarComponent,
    CheckFreeCarsComponent,
    MakeBookingCarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterBasicAuthService, multi: true},
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
