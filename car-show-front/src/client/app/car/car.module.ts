import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CarComponent } from './car.component';
import { CarDetailsComponent } from './car.details.component';
import { CarService } from './car.service';
import { LoaderComponent } from '../shared/components/loader/loader.component';
import { SharedModule } from '../shared/shared.module';
import { FileSelectDirective } from 'ng2-file-upload/ng2-file-upload';
import { Slide } from '../shared/components/caousel/slide.component'
import { Carousel } from '../shared/components/caousel/carousel.component';

@NgModule({
  imports: [
    CommonModule, FormsModule, SharedModule
  ],
  declarations: [
    CarComponent,
    FileSelectDirective,
    CarDetailsComponent,
    Carousel,
    Slide
  ],
  exports: [
    CarComponent,
  ]
})
export class CarModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CarModule,
      providers: [
        CarService
      ]
    };
  }
}