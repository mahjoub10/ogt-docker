import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Token } from '../../shared/entities/token';
import { AuthService } from '../../shared/services/auth.service';
import { VisibilityUtils } from '../../shared/utilities/visibility.utils';

declare var $: any;
declare var modal: any;
@Component({
  moduleId: module.id,
  selector: 'login-component',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.css']
})
export class LoginComponent {
  userName: string;
  password: string;

  formErrorMsg: string;
  userNameHasError = false;
  passwordHasError = false;

  constructor(
    public loginService: LoginService,
    public authService: AuthService,
    private visibilityUtils: VisibilityUtils) { }

  loginClick() {
    if (this.validateForm()) return;
    this.displayLoader();
    this.loginService.login(this.userName, this.password).subscribe(
      (success) => {
        let tokenObj = JSON.parse(success._body);
        this.authService.saveAuthTokens(tokenObj);
        this.hideLoader();
        $('#login-component').modal('toggle');
        this.visibilityUtils.handleLoggedUserVisibility(true);
      },
      (error) => {
        let errMsg = JSON.parse(error._body);
        this.formErrorMsg = errMsg.description;
        console.log(errMsg.description);
        this.hideLoader();
        this.visibilityUtils.handleLoggedUserVisibility(false);
      }
    );
  }

  inputChange(e: any) {
    this.validateForm();
  }

  private displayLoader() {
    $('#btn-login-form').hide();
    $('#login-loader').show();
  }

  private hideLoader() {
    $('#btn-login-form').show();
    $('#login-loader').hide();
  }

  private validateForm() {
    this.formErrorMsg = '';
    this.userNameHasError = !this.userName || this.userName === '';
    this.passwordHasError = !this.password || this.password === '';

    return this.userNameHasError ||
      this.passwordHasError;
  }
}