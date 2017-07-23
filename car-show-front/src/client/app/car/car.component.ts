import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from './car.service';
import { Token } from './../shared/entities/token';
import { FormValuesUtils } from './../shared/utilities/form-values.utils';
import { AuthService } from './../shared/services/auth.service';
import { VisibilityUtils } from './../shared/utilities/visibility.utils';
import { FileSelectDirective, FileDropDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload';
import { Car } from './../shared/entities/car';


declare var $: any;
declare var modal: any;
@Component({
  moduleId: module.id,
  selector: 'car-component',
  templateUrl: 'car.component.html',
  styleUrls: ['car.component.css']
})
export class CarComponent implements OnInit {
  car: Car = new Car;
  brands: string[];
  models: string[];
  years: number[];
  miles: number[];
  selectedBrand: string;
  formErrorMsg: string;
  userNameHasError = false;
  passwordHasError = false;

  public uploader: FileUploader = new FileUploader({ url: "" });

  constructor(
    public carService: CarService,
    public authService: AuthService,
    private formValuesUtils: FormValuesUtils,
    private router: Router,
    private visibilityUtils: VisibilityUtils) { }

  ngOnInit() {
    this.brands = this.getBrands();
    this.years = this.getYears();
    this.miles = this.getMiles();
  }

  loginClick() {

    console.log("the car to add is  " + JSON.stringify(this.car));
    this.displayLoader() ;
    let files = this.uploader.queue;
    this.carService.add(this.car, files).subscribe(
      (success) => {
        this.router.navigate(['/']);
      },
      (error) => {
        let errMsg = JSON.parse(error._body);
        this.formErrorMsg = errMsg.description;
        console.log(errMsg.description);
        this.hideLoader();
      }
    );

  }

  inputChange(e: any) {
    this.validateForm();
  }

  private displayLoader() {
    $('#btn-add-form').hide();
    $('#add-loader').show();
  }

  private hideLoader() {
    $('#btn-login-form').show();
    $('#login-loader').hide();
  }

  private validateForm() {
    this.formErrorMsg = '';
    this.userNameHasError = !this.car.name || this.car.name === '';
    this.passwordHasError = !this.car.brand || this.car.brand === '';

    return this.userNameHasError ||
      this.passwordHasError;
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