import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InternalComponent } from './internal.component';
import { HomeComponent } from './home/home.component';
import { RelatorioComponent } from './relatorio/relatorio.component';
const routes: Routes = [
  {
    path: '', component: InternalComponent, children: [
      { path: '', component: HomeComponent },
      { path: 'relatorio', component: RelatorioComponent },
      {
        path: "usuario",
        loadChildren: () =>
            import("./usuario/usuario.module").then(
                (m) => m.UsuarioModule
            )
      },
      {
        path: "veiculo",
        loadChildren: () =>
            import("./veiculo/veiculo.module").then(
                (m) => m.VeiculoModule
            )
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InternalRoutingModule { }
