import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login/login.component';
import {SubscribeComponent} from './login/subscribe.component';
import { LoginService } from './login/login.service';
import { LoaderComponent } from '../shared/components/loader/loader.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule, FormsModule, SharedModule
  ],
  declarations: [
    LoginComponent,
    SubscribeComponent
  ],
  exports: [
    LoginComponent,
    SubscribeComponent
  ]
})
export class LoginModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: LoginModule,
      providers: [
        LoginService
      ]
    };
  }
}