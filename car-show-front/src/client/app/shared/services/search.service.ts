import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Config } from '../config/env.config';
import { SearchParam } from '../entities/searchParam';
import { CarResult } from '../entities/carResult';


@Injectable()
export class SearchService {

    constructor(private http: Http) { }

    search(params: SearchParam): Observable<CarResult[]> {
        let header = new Headers({ 'Content-Type': "application/json" });
        let options = new RequestOptions({ headers: header });
        return this.http.post('/api/car/search', JSON.stringify(params), options)
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