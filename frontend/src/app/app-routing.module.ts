import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
        import("./module/external/external.module").then(
            (m) => m.ExternalModule
        )
  },
  {
    path: "internal",
    loadChildren: () =>
        import("./module/internal/internal.module").then(
            (m) => m.InternalModule
        )
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
