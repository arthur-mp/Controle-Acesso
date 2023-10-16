import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InternalRoutingModule } from './internal-routing.module';
import { InternalComponent } from './internal.component';
import { ComponentsModule } from 'src/app/components/components.module';
import { HomeComponent } from './home/home.component';
import {MatCardModule} from '@angular/material/card';
import { RelatorioComponent } from './relatorio/relatorio.component';
import { TagTemporaryComponent } from './tag-temporary/tag-temporary.component';
import { MatTableModule } from '@angular/material/table';
import { MatDividerModule } from '@angular/material/divider';

@NgModule({
  declarations: [
    InternalComponent,
    HomeComponent,
    RelatorioComponent,
    TagTemporaryComponent
  ],
  imports: [
    CommonModule,
    InternalRoutingModule,
    ComponentsModule,
    MatCardModule,
    MatTableModule,
    MatDividerModule
  ]
})
export class InternalModule { }
