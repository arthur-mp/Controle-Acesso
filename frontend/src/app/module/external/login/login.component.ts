import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/core/models/login.model';
import { UsersService } from 'src/app/core/services/users.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  public signInForm: FormGroup;
  
  constructor(private router: Router, private _formBuilder: FormBuilder, private usersService: UsersService){
    this.signInForm = new FormGroup({});
  }
  
  ngOnInit(): void {
    this.signInForm = this._formBuilder.group({
      email     : ['admin@gmail.com', [Validators.required, Validators.email]],
      password  : ['admin', Validators.required]
    });
  }

  signIn(){
    if (this.signInForm.valid) { 
      const login = {
        email: this.signInForm.value.email,
        senha: this.signInForm.value.password
      };

      this.usersService.login(login).subscribe(
        response => {
          this.router.navigate(['/internal']);
        }
      );
    }
  }
}
