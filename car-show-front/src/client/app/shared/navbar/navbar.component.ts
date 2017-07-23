import { Component, AfterViewInit, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { VisibilityUtils } from '../utilities/visibility.utils';

declare var $: any;
@Component({
  moduleId: module.id,
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css'],
})

export class NavbarComponent {
  constructor(
    public authService: AuthService,
    private visibilityUtils: VisibilityUtils) { }

  logoutClick() {
    this.authService.removeAuthTokens();
    this.visibilityUtils.handleLoggedUserVisibility(false);
  }
}