import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaComponent } from './lista/lista.component';
import { DetalhesComponent } from './detalhes/detalhes.component';
import { VeiculoRoutingModule } from './veiculo.routing';


@NgModule({
    declarations: [ 
  
    ListaComponent, DetalhesComponent
  ],
    imports: [
        CommonModule,
        VeiculoRoutingModule
    ]
})
export class VeiculoModule {}

