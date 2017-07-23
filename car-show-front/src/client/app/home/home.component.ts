import { Component, OnInit, AfterViewInit, NgZone } from '@angular/core';

import { CarService } from './../car/car.service';
import { FormValuesUtils } from './../shared/utilities/form-values.utils';
import { Config } from '../shared/config/env.config';
import { AuthService } from '../shared/services/auth.service';
import { VisibilityUtils } from '../shared/utilities/visibility.utils';
import { CarResult } from '../shared/entities/carResult';
import { SearchParam } from '../shared/entities/searchParam';
import { Router } from '@angular/router';
import { SearchService } from '../shared/services/search.service';


declare var $: any;
declare var Bloodhound: any;
declare var Login: any;
declare var modal: any;


@Component({
    moduleId: module.id,
    selector: 'app-home',
    templateUrl: 'home.component.html',
    styleUrls: ['home.component.css'],
})

export class HomeComponent implements OnInit, AfterViewInit {
    isUserAuthenticated: boolean;
    errorMessage: string;

    cars: CarResult[];
    selectedCar: CarResult;
    brands: string[];
    models: string[];
    years: number[];
    miles: number[];
    selectedBrand: string;
    params: SearchParam = new SearchParam();

    constructor(
        private zone: NgZone,
        private router: Router,
        public carService: CarService,
        public authService: AuthService,
        public searchService: SearchService,
        private visibilityUtils: VisibilityUtils,
        private formValuesUtils: FormValuesUtils) {
        this.authService.isUserAuthenticated();
    }

    ngOnInit() {
        this.getCars();
        this.brands = this.getBrands();
        this.years = this.getYears();
        this.miles = this.getMiles();
    }

    ngAfterViewInit() {
        this.zone.runOutsideAngular(() => {
            setTimeout(() => {
                this.bindSearchControl();
            }, 3000);
        });
    }


    bindSearchControl() {
        let self = this;
        var partners = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: '/api/car/search/name?name=',
                replace: function (url: string, query: string) {
                    return url + query;
                }
            }
        });

        $('#car .typeahead').typeahead({
            hint: true,
            highlight: true,
            minLength: 1,
        }, {
                displayKey: 'name',
                source: partners.ttAdapter(),
                templates: {
                    empty: 'not found', //optional
                    suggestion: function (el: any) {
                        return '<div class="autocomplete-result col-md-12">' +
                            ' <div class="autocomplete-img col-md-4 no-padding">' +
                            '<img class="" src="' + el.thumb + '" />' +
                            '</div>' +
                            '<div class="autocomplete-text col-md-8"> ' +
                            '<span class="autocomplete-name">' + el.name + ' </span>' +
                            '<span class="autocomplete-address"> ' + el.year + '</span>' +
                            '</div>' +
                            '</div>';
                    }
                }
            }).bind('typeahead:selected', function (obj: Object, datum: CarResult) {
                self.zone.run(() => self.goToCarDetails(datum.reference));
            });
    }
    search() {
        this.searchService.search(this.params).subscribe(result => {
            this.cars = result;
        });
    }


    goToCarDetails(carRef: string) {
        this.router.navigate(['/car-details', carRef]);

    }

    getCars() {
        this.carService.getRecentCars().subscribe(result => {
            this.cars = result;
        });
    }

    getBrands() {
        return this.formValuesUtils.getBrands();
    }

    getModels(brand: string) {
        return this.formValuesUtils.getModels(brand);
    }

    getYears() {
        return this.formValuesUtils.getYears();
    }

    getMiles() {
        return this.formValuesUtils.getMiles();
    }

    onChange(brand) {
        console.log(brand);
        this.selectedBrand = brand;
        this.models = this.getModels(brand);
    }
}
