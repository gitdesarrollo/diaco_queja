import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { FrmMainParam, FrmMainFD, BASE_URL_REST } from "../atencion-consumidor-const";
import { SubmitFormService } from "../shared/submit-form.service";

@Injectable({
  providedIn: 'root'
})
export class CatPuestoService {
  private baseUrl:string=BASE_URL_REST+'tipos-puesto';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};

	constructor(private _http:HttpClient, private datePipe: DatePipe, private _submitFormService:SubmitFormService) { }

  

  getData (): Observable<any> {
    var LocalURL=this.baseUrl+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  
  saveData (nombrePuesto): Observable<any> {
    var LocalURL=this.baseUrl+'/save/'+nombrePuesto+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  updateData (idPuesto,nombrePuesto): Observable<any> {
    var LocalURL=this.baseUrl+'/update/'+idPuesto+'/'+nombrePuesto+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  deleteData (idPuesto): Observable<any> {
    var LocalURL=this.baseUrl+'/delete/'+idPuesto+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  private extractData(res: Response) {
    let body = res;
    return body || { };
  }
  

}
