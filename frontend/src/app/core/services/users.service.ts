import { Config } from '../config/config';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Users } from '../models/users.model';
import { Login } from '../models/login.model';


@Injectable({
    providedIn: 'root',
})
export class UsersService{

    _headers: HttpHeaders;
    _urlPublic: string = Config.urlBackendPublico;

    constructor(private _httpClient: HttpClient){
        this._headers = new HttpHeaders();
        this._headers.append('Content-Type', 'application/json');
    }

    // Login User
    login(login: Login): Observable<Users>{
        return this._httpClient
            .post<Users>(this._urlPublic + Config.rotaUserLogin, login, { headers: this._headers});
    }


    // Obtem Users
    obtem(id: string): Observable<Users>{
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, id);

        return this._httpClient.get<Users>(this._urlPublic + Config.rotaUserObtem, { params: httpParams });
    }

    // Lista todos Users
    lista(): Observable<Users[]>{
        return this._httpClient.get<Users[]>(this._urlPublic + Config.rotaUserObtemTodos, { headers: this._headers});
    }

    // Cria Users
    cria(users: Users): Observable<Users>{
        return this._httpClient
            .post<Users>(this._urlPublic + Config.rotaUserCria, users, { headers: this._headers});
    } 

    // Atualiza Users
    atualiza(users: Users): Observable<Users>{
        return this._httpClient
            .put<Users>(this._urlPublic + Config.rotaUserEdita, users, { headers: this._headers });
    }

    // Deleta Users
    deleta(users: Users): Observable<Users>{

        const options = {
            headers: this._headers,
            body: users
        }

        return this._httpClient
            .delete<Users>(this._urlPublic + Config.rotaUserDeleta, options);
    }


}