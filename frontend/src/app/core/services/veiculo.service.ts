import { Config } from './../config/config';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Veiculo } from '../models/veiculo.model';


@Injectable({
    providedIn: 'root',
})
export class VeiculoService{

    _headers: HttpHeaders;
    _urlPublic: string = Config.urlBackendPublico;

    constructor(private _httpClient: HttpClient){
        this._headers = new HttpHeaders();
        this._headers.append('Content-Type', 'application/json');
    }


    // Obtem Veiculo
    obtem(id: string): Observable<Veiculo>{
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, id);

        return this._httpClient.get<Veiculo>(this._urlPublic + Config.rotaVeiculoObtem, { params: httpParams });
    }

    // Lista todos Veiculos
    lista(): Observable<Veiculo[]>{
        return this._httpClient.get<Veiculo[]>(this._urlPublic + Config.rotaVeiculoObtemTodos, { headers: this._headers});
    }

    // Cria Veiculo
    cria(veiculo: Veiculo): Observable<Veiculo>{
        return this._httpClient
            .post<Veiculo>(this._urlPublic + Config.rotaVeiculoCria, veiculo, { headers: this._headers});
    } 

    // Atualiza Veiculo
    atualiza(veiculo: Veiculo): Observable<Veiculo>{
        return this._httpClient
            .put<Veiculo>(this._urlPublic + Config.rotaVeiculoEdita, veiculo, { headers: this._headers });
    }

    // Deleta Veiculo
    deleta(veiculo: Veiculo): Observable<Veiculo>{

        const options = {
            headers: this._headers,
            body: veiculo
        }

        return this._httpClient
            .delete<Veiculo>(this._urlPublic + Config.rotaVeiculoDeleta, options);
    }


}