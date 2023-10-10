import { Config } from '../config/config';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TagsTemporary } from '../models/tagsTemporary.model';


@Injectable({
    providedIn: 'root',
})
export class TagsTemporaryService{

    _headers: HttpHeaders;
    _urlPublic: string = Config.urlBackendPublico;

    constructor(private _httpClient: HttpClient){
        this._headers = new HttpHeaders();
        this._headers.append('Content-Type', 'application/json');
    }


    // Obtem TagsTemporary
    obtem(id: string): Observable<TagsTemporary>{
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, id);

        return this._httpClient.get<TagsTemporary>(this._urlPublic + Config.rotaTagsTemporaryObtem, { params: httpParams });
    }

    // Lista todos TagsTemporary
    lista(): Observable<TagsTemporary[]>{
        return this._httpClient.get<TagsTemporary[]>(this._urlPublic + Config.rotaTagsTemporaryObtemTodos, { headers: this._headers});
    }

    // Cria TagsTemporary
    cria(tagsTemporary: TagsTemporary): Observable<TagsTemporary>{
        return this._httpClient
            .post<TagsTemporary>(this._urlPublic + Config.rotaTagsTemporaryCria, tagsTemporary, { headers: this._headers});
    } 

    // Atualiza TagsTemporary
    atualiza(tagsTemporary: TagsTemporary): Observable<TagsTemporary>{
        return this._httpClient
            .put<TagsTemporary>(this._urlPublic + Config.rotaTagsTemporaryEdita, tagsTemporary, { headers: this._headers });
    }

    // Deleta TagsTemporary
    deleta(tagsTemporary: TagsTemporary): Observable<TagsTemporary>{
        
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, tagsTemporary.id);

        return this._httpClient.delete<TagsTemporary>(this._urlPublic + Config.rotaTagsTemporaryDeleta, { params: httpParams });
    }


}