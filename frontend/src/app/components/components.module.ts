import { NgModule } from "@angular/core";
import { HeaderComponent } from "./header/header.component";
import { NavComponent } from "./nav/nav.component";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import { RouterModule } from "@angular/router";
import { DialogSelectUserComponent } from './dialog-select-user/dialog-select-user.component';
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormsModule } from "@angular/forms";
import { MatInputModule } from "@angular/material/input";
import {NgIf} from '@angular/common';
import { NgFor } from "@angular/common";


@NgModule({
    declarations: [
        HeaderComponent,
        NavComponent,
        DialogSelectUserComponent
    ],
    imports: [
        RouterModule,
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatSidenavModule,
        MatListModule,
        MatDialogModule,
        MatSelectModule,
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
        NgFor,
        NgIf
    ],
    exports: [
        HeaderComponent,
        NavComponent
    ]
})
export class ComponentsModule{}