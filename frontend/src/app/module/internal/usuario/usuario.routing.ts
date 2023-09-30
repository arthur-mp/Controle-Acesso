import { ListaComponent } from './lista/lista.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetalhesComponent } from './detalhes/detalhes.component';
const routes: Routes = [
  {
    path: '', component: ListaComponent, children: [
      { path: 'create', component: DetalhesComponent },
      { path: ':id', component: DetalhesComponent }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
