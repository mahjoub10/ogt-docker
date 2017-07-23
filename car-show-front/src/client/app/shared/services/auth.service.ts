import { Injectable } from '@angular/core';
import { Token } from '../entities/token';
import { Config } from './../config/env.config';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { UserService } from './user.service';
import { VisibilityUtils } from '../utilities/visibility.utils';

@Injectable()
export class AuthService {

    constructor(
        private http: Http, 
        private userService: UserService,
        private visibilityUtils:VisibilityUtils) { }

    isUserAuthenticated(): void {
        this.userService.get().subscribe(
            (success) => {
                this.visibilityUtils.handleLoggedUserVisibility(true);
            },
            (error) => {
                this.removeAuthTokens();
                this.visibilityUtils.handleLoggedUserVisibility(false);
            });
    }


    saveAuthTokens(tokenObj: Token) {
        localStorage.setItem('access_token', tokenObj.access_token);
        localStorage.setItem('refresh_token', tokenObj.refresh_token);
    }

    removeAuthTokens() {
        localStorage.removeItem('access_token');
        localStorage.removeItem('refresh_token');
    }
}