import { Component } from '@angular/core';
import { Usages } from 'src/app/core/models/usages.model';
import { Users } from 'src/app/core/models/users.model';
import { UsagesService } from 'src/app/core/services/usages.service';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.scss']
})
export class RelatorioComponent {

  public listUsagesModify: any[] = [];

  public listUsers: Users[] = [];

  displayedColumns: string[] = ['nome', 'tagUser', 'dateUsageInput', 'timeUsageInput', 'dateUsageOutput', 'timeUsageOutput'];

  constructor(private usagesService: UsagesService, private userService: UsersService){

  }

  ngOnInit(): void {
    this.carregarUsages();
  }

  carregarUsages(): void{
    this.usagesService.lista().subscribe(
      response => {
        this.listUsagesModify = response;
        this.carregaUsuarios();
      }
    )
  }

  carregaUsuarios(): void{
    this.listUsagesModify.forEach(usage => {
      this.userService.obtem(usage.idUser).subscribe(user => {
        usage.nome = user.nome + " " + user.sobrenome;
      })
    })
  }
}
