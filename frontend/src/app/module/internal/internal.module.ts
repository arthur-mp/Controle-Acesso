import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InternalRoutingModule } from './internal-routing.module';
import { InternalComponent } from './internal.component';
import { ComponentsModule } from 'src/app/components/components.module';
import { HomeComponent } from './home/home.component';
import {MatCardModule} from '@angular/material/card';
import { RelatorioComponent } from './relatorio/relatorio.component';

@NgModule({
  declarations: [
    InternalComponent,
    HomeComponent,
    RelatorioComponent
  ],
  imports: [
    CommonModule,
    InternalRoutingModule,
    ComponentsModule,
    MatCardModule
  ]
})
export class InternalModule { }
