import { DateTimeFormatService } from './../utils/DateTimeFormatService';
import { Config } from '../config/config';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usages } from '../models/usages.model';


@Injectable({
    providedIn: 'root',
})
export class UsagesService{

    _headers: HttpHeaders;
    _urlPublic: string = Config.urlBackendPublico;

    constructor(private _httpClient: HttpClient){
        this._headers = new HttpHeaders();
        this._headers.append('Content-Type', 'application/json');
    }


    // Obtem Usages
    public obtem(id: string): Observable<Usages>{
        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, id);

        return this._httpClient.get<Usages>(this._urlPublic + Config.rotaUsagesObtem, { params: httpParams });
    }

    // Lista todos Usages
    public lista(): Observable<Usages[]>{
        return this._httpClient.get<Usages[]>(this._urlPublic + Config.rotaUsagesObtemTodos, { headers: this._headers});
    }

    // Cria Usages
    public cria(usages: Usages): Observable<Usages>{

        //usages = this.setDateAndTimeUsages(usages);

        return this._httpClient
            .post<Usages>(this._urlPublic + Config.rotaUsagesCria, usages, { headers: this._headers});
    } 

    // Atualiza Usages
    public atualiza(usages: Usages): Observable<Usages>{

        //usages = this.setDateAndTimeUsages(usages);

        return this._httpClient
            .put<Usages>(this._urlPublic + Config.rotaUsagesEdita, usages, { headers: this._headers });
    }

    // Deleta Users
    public deleta(usages: Usages): Observable<Usages>{

        let httpParams = new HttpParams();
        httpParams = httpParams.append(Config.parametroId, usages.id);

        return this._httpClient.delete<Usages>(this._urlPublic + Config.rotaUsagesDeleta, { params: httpParams });

            
    }

    // private setDateAndTimeUsages(usages: Usages): Usages{
    //     const currentDateAndTime = new Date();

    //     const currentDate: string = this.dateTimeFormatService.formatToDateString(currentDateAndTime);
    //     const currentTime: string = this.dateTimeFormatService.formatToTimeString(currentDateAndTime);

    //     usages.dateUsageInput = currentDate;
    //     usages.timeUsageInput = currentTime;

    //     return usages;
    // }


}