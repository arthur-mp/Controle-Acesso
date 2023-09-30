import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioRoutingModule } from './usuario.routing';
import { ListaComponent } from './lista/lista.component';
import { DetalhesComponent } from './detalhes/detalhes.component';


@NgModule({
    declarations: [ 
    ListaComponent, DetalhesComponent
  ],
    imports: [
        CommonModule,
        UsuarioRoutingModule
    ]
})
export class UsuarioModule {}

