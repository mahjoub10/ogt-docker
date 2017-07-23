import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Config } from '../../shared/config/env.config';
import { User } from '../../shared/entities/user';

@Injectable()
export class LoginService {

    constructor(private http: Http) { }

    login(userName: string, password: string): Observable<any> {
        let url = "/oauth/token?client_secret=QzNmrG57pQpt5B9azA01&client_id=mMKBdsIgpC9prxBwgd6V&grant_type=password&scope=read&username=" + userName + "&password=" + password;
        return this.http.post(url, {})
            .map((res: Response) => {
                console.log("The response is " + res);
                return res;
            });
    }


    subscribe(user: User) {
        let header = new Headers({ 'Content-Type': "application/json" });
        let options = new RequestOptions({ headers: header });
        return this.http.post('/api/user/register', JSON.stringify(user),options)
            .map((res: Response) => {
                return res;
            });

    }

}