import { Component, DoCheck } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogSelectUserComponent } from 'src/app/components/dialog-select-user/dialog-select-user.component';
import { ParametroConfiguracao } from 'src/app/core/models/parametroConfiguracao.model';
import { TagsTemporary } from 'src/app/core/models/tagsTemporary.model';
import { ParametroConfiguracaoService } from 'src/app/core/services/parametroConfiguracao.service';
import { TagsTemporaryService } from 'src/app/core/services/tagsTemporary.service';

@Component({
  templateUrl: './tag-temporary.component.html',
  styleUrls: ['./tag-temporary.component.scss']
})
export class TagTemporaryComponent{

  public listTags: TagsTemporary[] = [];

  displayedColumns: string[] = ['codigo'];

  public parametroConfiguracaoCadastrarTag: ParametroConfiguracao;

  constructor(private router: Router, private tagsTemporaryService: TagsTemporaryService, public dialog: MatDialog, 
    private _formBuilder: FormBuilder, private parametroConfiguracaoService: ParametroConfiguracaoService ){
      this.parametroConfiguracaoCadastrarTag = {
        id: "",
        nome: "",
        valor: false
      }
  }

  ngOnInit(): void {
    this.carregarTags();
    this.carregarParametroConfiguracao();

  }

  atualizaParametroConfiguracao(): void{
    this.parametroConfiguracaoService.atualiza(this.parametroConfiguracaoCadastrarTag).subscribe(
      response => {}
    )
  }


  carregarTags(): void{
    this.tagsTemporaryService.lista().subscribe(
      response => {
        this.listTags = response;
      }
    )
  }

  carregarParametroConfiguracao(): void{
    this.parametroConfiguracaoService.obtem("cadastrarTag").subscribe(
      response => {
        this.parametroConfiguracaoCadastrarTag = response;
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
