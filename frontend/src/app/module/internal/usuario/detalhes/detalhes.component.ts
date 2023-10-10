import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Users } from 'src/app/core/models/users.model';
import { Veiculo } from 'src/app/core/models/veiculo.model';
import { UsersService } from 'src/app/core/services/users.service';
import { VeiculoService } from 'src/app/core/services/veiculo.service';

@Component({
  templateUrl: './detalhes.component.html',
  styleUrls: ['./detalhes.component.scss']
})
export class DetalhesComponent implements OnInit{

  public isCreateUser: boolean = false;
  public usuarioForm: FormGroup;
  public user: Users;

  private idUser: string = "";

  constructor(private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder, 
    private userService: UsersService, private veiculoService: VeiculoService, private router: Router){
      this.usuarioForm = this.formBuilder.group({
        id: [''], 
        nome: ['', Validators.required], 
        sobrenome: [''],
        telefone: ['', Validators.required],
        matricula: [''],
        email: ['', [Validators.required, Validators.email]], 
        senha: [''],
        codigoTag: [''],
        veiculos: this.formBuilder.array([])
      });


      this.user = {
        id: '',
        nome: '',
        sobrenome: '',
        telefone: '',
        matricula: '',
        email: '',
        senha: '',
        codigoTag: '',
        veiculosId: [],
      };
  }

  ngOnInit(): void {
    this.activatedRoute.url.subscribe(url => {
      if(url[0].path == 'create') this.isCreateUser = true
      else{
          this.idUser = url[0].path;
          this.carregaUsuario(this.idUser);
      } 
    });
  }

  carregaUsuario(idUser: string): void{
    this.userService.obtem(idUser).subscribe(
      user => {
        this.user = user;
        this.usuarioForm.patchValue(this.user);

        for (let index = 0; index < user.veiculosId.length; index++) {
          this.veiculoService.obtem(user.veiculosId[index]).subscribe(veiculoDto => {
            const veiculo: FormGroup = this.formBuilder.group({
              id: [veiculoDto.id],
              usuarioId: [veiculoDto.usuarioId],
              modelo: [veiculoDto.modelo, Validators.required],
              marca: [veiculoDto.marca, Validators.required],
              placa: [veiculoDto.placa, Validators.required],
              cor: [veiculoDto.cor, Validators.required],
            });
        
            const listaVeiculos = this.usuarioForm.get("veiculos") as FormArray;
            listaVeiculos.push(veiculo);
          })
        }
      }
    )
  }

  getVeiculosForm(): FormGroup[]{
    return (this.usuarioForm.get("veiculos") as FormArray).controls as FormGroup[];
  }

  getVeiculoControl(novoVeiculo: FormGroup, nome: string): FormControl{
    return (novoVeiculo.get(nome) as FormControl);
  }

  adicionarVeiculo(): void{
    const veiculo: FormGroup = this.formBuilder.group({
      usuarioId: [''],
      modelo: ["", Validators.required],
      marca: ["", Validators.required],
      placa: ["", Validators.required],
      cor: ["", Validators.required],
    });
 
    const listaVeiculos = this.usuarioForm.get("veiculos") as FormArray;
    listaVeiculos.push(veiculo);
  }


  deleteVeiculo(index: number): void{
    const listaVeiculos = this.usuarioForm.get("veiculos") as FormArray;

    if(!this.isCreateUser){
      const veiculo = listaVeiculos.at(index) as FormGroup;
      const veiculoDTO: Veiculo = {
        id: veiculo.get("id")?.value,
        modelo: veiculo.get("modelo")?.value,
        marca: veiculo.get("marca")?.value,
        placa: veiculo.get("placa")?.value,
        cor: veiculo.get("cor")?.value,
        usuarioId: veiculo.get("usuarioId")?.value
      }
      this.veiculoService.deleta(veiculoDTO).subscribe((veiculo) => {
        listaVeiculos.removeAt(index);
      });
    }else{
      listaVeiculos.removeAt(index);
    }
  }

  salvar(): void{
    const usuario: Users = {
      id: this.usuarioForm.get("id")?.value,
      nome: this.usuarioForm.get("nome")?.value,
      sobrenome: this.usuarioForm.get("sobrenome")?.value,
      telefone: this.usuarioForm.get("telefone")?.value,
      matricula: this.usuarioForm.get("matricula")?.value,
      email: this.usuarioForm.get("email")?.value,
      senha: this.usuarioForm.get("senha")?.value,
      codigoTag: this.usuarioForm.get("codigoTag")?.value,
      veiculosId: []
    }
    this.userService.cria(usuario).subscribe(usuario => {
      const listaVeiculos = this.usuarioForm.get("veiculos") as FormArray;

      const listaVeiculosDTO: Veiculo[] = [];

      for (let i = 0; i < listaVeiculos.length; i++) {
        const veiculoControl = listaVeiculos.at(i) as FormGroup;
        veiculoControl.get("usuarioId")?.setValue(usuario.id);

        listaVeiculosDTO.push(
          {
            id: veiculoControl.get("id")?.value,
            modelo: veiculoControl.get("modelo")?.value,
            marca: veiculoControl.get("marca")?.value,
            placa: veiculoControl.get("placa")?.value,
            cor: veiculoControl.get("cor")?.value,
            usuarioId: veiculoControl.get("usuarioId")?.value
          }
        )
      }

      this.usuarioForm.get("id")?.setValue(usuario.id);

      this.veiculoService.criaAll(listaVeiculosDTO).subscribe(listaVeiculos => {
        listaVeiculos.forEach(veiculo => {
          usuario.veiculosId.push(veiculo.id);
        })

        this.userService.atualiza(usuario).subscribe(usuario => {
          this.router.navigate(['internal/usuario']);
        })
      })
      
    })
  }

  deletar(): void{
    const listaVeiculos = this.usuarioForm.get("veiculos") as FormArray;

    for (let index = 0; index < listaVeiculos.length; index++) {
      this.deleteVeiculo(index);
    }
    this.userService.deleta(this.user).subscribe(usuario => {
      this.router.navigate(['internal/usuario']);
    })
  }

}
