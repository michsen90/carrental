import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './car/car.component';
import { CarsComponent } from './cars/cars.component';
import { ClientComponent } from './client/client.component';
import { ClientsComponent } from './clients/clients.component';
import { ErrorComponent } from './error/error.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RouteGuardService } from './services/route-guard.service';
import { UsersComponent } from './users/users.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService]},
  { path: 'welcome', component: WelcomeComponent, canActivate: [RouteGuardService]},
  { path: 'users', component: UsersComponent, canActivate: [RouteGuardService]},
  { path: 'clients', component: ClientsComponent, canActivate: [RouteGuardService]},
  { path: 'client/:idClient', component: ClientComponent, canActivate: [RouteGuardService]},
  { path: 'cars', component: CarsComponent, canActivate: [RouteGuardService]},
  { path: 'car/:idCar', component: CarComponent, canActivate: [RouteGuardService]},



  { path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
