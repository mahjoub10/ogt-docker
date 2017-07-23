import { Injectable } from '@angular/core';
import { Token } from '../entities/token';
import { Config } from './../config/env.config';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../entities/user';

@Injectable()
export class UserService {

    constructor(private http: Http) { }

    get(): Observable<User> {
        let url = "/api/user/account";
        let header = new Headers({ 'Authorization': 'bearer ' + localStorage.getItem('access_token') });
        let options = new RequestOptions({ headers: header });
        return this.http
            .get(url, options)
            .map((res: Response) => {
                return res.json();
            });
    }
}