import { Config } from '../config/config';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ParametroConfiguracao } from '../models/parametroConfiguracao.model';


@Injectable({
    providedIn: 'root',
})
export class ParametroConfiguracaoService{

    _headers: HttpHeaders;
    _urlPublic: string = Config.urlBackendPublico;

    constructor(private _httpClient: HttpClient){
        this._headers = new HttpHeaders();
        this._headers.append('Content-Type', 'application/json');
    }


    // Obtem ParametroConfiguracao
    obtem(nome: string): Observable<ParametroConfiguracao>{
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.nomeParametro, nome);

        return this._httpClient.get<ParametroConfiguracao>(this._urlPublic + Config.rotaParametroConfiguracaoObtem, { params: httpParams });
    }

    // Lista todos ParametroConfiguracao
    lista(): Observable<ParametroConfiguracao[]>{
        return this._httpClient.get<ParametroConfiguracao[]>(this._urlPublic + Config.rotaParametroConfiguracaoObtemTodos, { headers: this._headers});
    }

    // Cria ParametroConfiguracao
    cria(parametroConfiguracao: ParametroConfiguracao): Observable<ParametroConfiguracao>{
        return this._httpClient
            .post<ParametroConfiguracao>(this._urlPublic + Config.rotaParametroConfiguracaoCria, parametroConfiguracao, { headers: this._headers});
    } 

    // Atualiza ParametroConfiguracao
    atualiza(parametroConfiguracao: ParametroConfiguracao): Observable<ParametroConfiguracao>{
        return this._httpClient
            .put<ParametroConfiguracao>(this._urlPublic + Config.rotaParametroConfiguracaoEdita, parametroConfiguracao, { headers: this._headers });
    }

    // Deleta ParametroConfiguracao
    deleta(parametroConfiguracao: ParametroConfiguracao): Observable<ParametroConfiguracao>{
        
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, parametroConfiguracao.id);

        return this._httpClient.delete<ParametroConfiguracao>(this._urlPublic + Config.rotaParametroConfiguracaoDeleta, { params: httpParams });
    }


}