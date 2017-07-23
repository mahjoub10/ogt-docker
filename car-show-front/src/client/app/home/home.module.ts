import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from '../shared/shared.module';
// import { DoctorService } from './../shared/services/doctor.service';
import { LoginModule } from '../login/login.module';
import { LoginService } from '../login/login/login.service';
import { CarModule } from '../car/car.module'
import { CarService } from '../car/car.service';

@NgModule({
  imports: [
    HomeRoutingModule,
    SharedModule,
    LoginModule,
    FormsModule,
    CarModule
  ],
  declarations: [
    HomeComponent
    ],
  exports: [HomeComponent],
  providers: [
    // DoctorService,
    LoginService,
    CarService]
})
export class HomeModule { }
