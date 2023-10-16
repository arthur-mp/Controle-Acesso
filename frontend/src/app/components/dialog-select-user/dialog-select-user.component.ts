import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TagsTemporary } from 'src/app/core/models/tagsTemporary.model';
import { Users } from 'src/app/core/models/users.model';
import { TagsTemporaryService } from 'src/app/core/services/tagsTemporary.service';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
  selector: 'app-dialog-select-user',
  templateUrl: './dialog-select-user.component.html',
  styleUrls: ['./dialog-select-user.component.scss']
})
export class DialogSelectUserComponent {

  public selectedUser: Users;

  public tag: TagsTemporary;

  public listUsers: Users[] = [];

  
  constructor(private userService: UsersService, private tagsService: TagsTemporaryService, public dialogRef: MatDialogRef<DialogSelectUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.selectedUser = {
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

    this.obtemUsers();
    this.tag = data;
  }

  obtemUsers(){
    this.userService.lista().subscribe(lista => {
      this.listUsers = lista.filter(user => user.nome !== "Admin");
    });
    
  }

  salvar(){
    this.selectedUser.codigoTag = this.tag.codeTag;
    this.userService.atualiza(this.selectedUser).subscribe(user => {
      this.tagsService.deleta(this.tag).subscribe(tag =>  this.dialogRef.close )
    })
  }
}
