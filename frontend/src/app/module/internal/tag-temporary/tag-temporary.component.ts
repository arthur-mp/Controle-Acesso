import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogSelectUserComponent } from 'src/app/components/dialog-select-user/dialog-select-user.component';
import { TagsTemporary } from 'src/app/core/models/tagsTemporary.model';
import { TagsTemporaryService } from 'src/app/core/services/tagsTemporary.service';

@Component({
  templateUrl: './tag-temporary.component.html',
  styleUrls: ['./tag-temporary.component.scss']
})
export class TagTemporaryComponent {

  public listTags: TagsTemporary[] = [];

  displayedColumns: string[] = ['codigo'];

  constructor(private router: Router, private tagsTemporaryService: TagsTemporaryService, public dialog: MatDialog ){

  }

  ngOnInit(): void {
    this.carregarTags();
  }

  carregarTags(): void{
    this.tagsTemporaryService.lista().subscribe(
      response => {
        this.listTags = response;
      }
    )
  }

  abrirDetalhesTag(tags: TagsTemporary): void{
    const dialogRef = this.dialog.open(DialogSelectUserComponent, {
      data: tags,
    });

    dialogRef.afterClosed().subscribe(result => {
      this.carregarTags();
    });
  }

}
