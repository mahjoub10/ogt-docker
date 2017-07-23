import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarService } from './car.service';
import { CarResult } from '../shared/entities/carResult';
import { Slide } from '../shared/components/caousel/slide.component'
import { Carousel } from '../shared/components/caousel/carousel.component';

declare var $: any;
declare var modal: any;
@Component({
    moduleId: module.id,
    selector: 'car-details-component',
    templateUrl: 'car.details.component.html',
    styleUrls: ['car.component.css']
})
export class CarDetailsComponent implements OnInit {

    selectedCar: CarResult;
    reference: string;
    mapMarkers: any[] = new Array();
    NextPhotoInterval: number = 5000;
    noLoopSlides: boolean = true;

    constructor(
        private route: ActivatedRoute,
        private carService: CarService) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.reference = params['reference'];
            this.getCarDetails();

        });
    }


    getCarDetails() {
        this.carService.getCarDetails(this.reference).subscribe(result => {
            this.selectedCar = result;
        });
    }


}