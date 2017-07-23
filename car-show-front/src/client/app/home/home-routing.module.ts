import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { CarDetailsComponent } from '../car/car.details.component';
import { CarComponent } from '../car/car.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: '', component: HomeComponent },
      { path: 'new', component: CarComponent },
      { path: 'car-details/:reference', component: CarDetailsComponent }
    ])
  ],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
