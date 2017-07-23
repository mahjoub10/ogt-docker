import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Token } from '../../shared/entities/token';
import { User } from '../../shared/entities/user';
import { AuthService } from '../../shared/services/auth.service';
import { VisibilityUtils } from '../../shared/utilities/visibility.utils';

declare var $: any;
declare var modal: any;
@Component({
  moduleId: module.id,
  selector: 'subscribe-component',
  templateUrl: 'subscribe.component.html',
  styleUrls: ['login.component.css']
})
export class SubscribeComponent implements OnInit {
  user: User = new User();

  formErrorMsg: string;
  userNameHasError = false;
  passwordHasError = false;

  constructor(
    public loginService: LoginService,
    public authService: AuthService,
    private visibilityUtils: VisibilityUtils) {
  }

  public ngOnInit() {
    // $('#login-component').modal('toggle');
  }

  subscribe() {
    // if (this.validateForm()) return;
    this.displayLoader();
    this.loginService.subscribe(this.user).subscribe(
      (success) => {

        $('#login-component').modal('toggle');
        $('#subscribe-component').modal('toggle');
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
    // this.validateForm();
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