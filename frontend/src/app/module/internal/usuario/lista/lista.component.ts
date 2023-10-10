import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from 'src/app/core/models/users.model';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit{

  public listUsers: Users[] = [];

  displayedColumns: string[] = ['nome', 'matricula', 'telefone', 'email'];

  constructor(private router: Router, private usersService: UsersService){

  }

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void{
    this.usersService.lista().subscribe(
      response => {
        this.listUsers = response;
      }
    )
  }

  abrirDetalhesUsuario(usuario: Users): void{
    this.router.navigate(['internal/usuario', usuario.id]);
  }

  criarNovoUsuario(): void{
    this.router.navigate(['internal/usuario/create']);
  }
}
