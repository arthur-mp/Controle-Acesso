import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from 'src/app/core/models/users.model';
import { Veiculo } from 'src/app/core/models/veiculo.model';
import { UsersService } from 'src/app/core/services/users.service';
import { VeiculoService } from 'src/app/core/services/veiculo.service';

@Component({
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent {

  public listVeiculos: Veiculo[] = [];

  displayedColumns: string[] = ['modelo', 'marca', 'placa', 'cor'];

  constructor(private router: Router, private usersService: UsersService, private veiculoService: VeiculoService){

  }

  ngOnInit(): void {
    this.carregarVeiculos();
  }

  carregarVeiculos(): void{
    this.veiculoService.lista().subscribe(
      response => {
        this.listVeiculos = response;
      }
    )
  }

  abrirDetalhesVeiculo(veiculo: Veiculo): void{
    this.router.navigate(['internal/usuario', veiculo.usuarioId]);
  }

}
