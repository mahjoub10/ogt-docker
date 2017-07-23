import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';


import { AuthService } from './services/auth.service';
import {SearchService} from './services/search.service';
import { VisibilityUtils } from './utilities/visibility.utils';
import { FormValuesUtils } from './utilities/form-values.utils';
import { UserService } from './services/user.service';
import { LoaderComponent } from './components/loader/loader.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    LoaderComponent
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    LoaderComponent,
    CommonModule,
    FormsModule,
    RouterModule
  ]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedModule,
      providers: [
        AuthService,
        VisibilityUtils,
        UserService,
        FormValuesUtils,
        SearchService
        
      ]
    };
  }
}
