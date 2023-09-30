import { NgModule } from "@angular/core";
import { HeaderComponent } from "./header/header.component";
import { NavComponent } from "./nav/nav.component";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterModule } from "@angular/router";


@NgModule({
    declarations: [
        HeaderComponent,
        NavComponent
    ],
    imports: [
        RouterModule,
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatSidenavModule,
        MatListModule,
    ],
    exports: [
        HeaderComponent,
        NavComponent
    ]
})
export class ComponentsModule{}