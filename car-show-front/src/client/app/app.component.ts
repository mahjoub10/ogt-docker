import { Component, OnInit, AfterViewInit, NgZone } from '@angular/core';
import { Config } from './shared/config/env.config';
import { AuthService } from './shared/services/auth.service';
import './operators';

declare var $: any;
declare var Layout: any;

@Component({
  moduleId: module.id,
  selector: 'car-app',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
})

export class AppComponent implements OnInit, AfterViewInit {

  constructor(private zone: NgZone, private authService: AuthService) {
    console.log('Environment config', Config);
  }

  ngOnInit() {
    $.fn.scriptLoad({
      source: [
        'assets/ressources/js/myscript.js',
        'assets/ressources/js/main.js',
        'assets/ressources/step/js/scripts.js'
      ]
    });
  }

  ngAfterViewInit() {
    this.zone.runOutsideAngular(() => {
      setTimeout(() => {
        Layout.init();
      }, 200);
    });
  }

}
