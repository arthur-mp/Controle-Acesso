import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExternalComponent } from './external.component';
import { ExternalRoutingModule } from './external-routing.module';
import { LoginComponent } from './login/login.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';


@NgModule({
  declarations: [
    ExternalComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    ExternalRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule
  ]
})
export class ExternalModule {
 }
