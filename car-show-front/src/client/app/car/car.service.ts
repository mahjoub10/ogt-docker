import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Config } from './../shared/config/env.config';
import { Car } from './../shared/entities/car';
import {CarResult} from './../shared/entities/carResult' ;


@Injectable()
export class CarService {

    constructor(private http: Http) { }

    add(car: Car, files: any): Observable<any> {
        let header = new Headers({ 'Authorization': 'bearer ' + localStorage.getItem('access_token') });
        let options = new RequestOptions({ headers: header });

        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append(`media`, files[i]._file);
        }
        formData.append('car', new Blob([JSON.stringify(car)], {
            type: "application/json"
        }));

        return this.http.post('/api/car/upload', formData, options)
            .map((res: Response) => {
                return res;
            });

    }

    getRecentCars(): Observable<CarResult[]> {
        return this.http.get('/api/car/search/recent')
            .map((res: Response) => res.json())
            .catch(this.handleError);
    }

    getCarDetails(reference :string ): Observable<CarResult> {
        return this.http.get('/api/car/details/'+reference)
            .map((res: Response) => res.json())
            .catch(this.handleError);
    }

    private handleError(error: any) {
        let errMsg = (error.message) ?
            error.message : error.status ?
                `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}