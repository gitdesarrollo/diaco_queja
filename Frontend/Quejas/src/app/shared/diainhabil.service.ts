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
export class DiainhabilService {
  
  private baseUrl:string=BASE_URL_REST+'tipos-dia-inhabil';
  private baseUrlDepto:string=BASE_URL_REST+'tipos-dpto';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};

 
	constructor(private _http:HttpClient, private datePipe: DatePipe, private _submitFormService:SubmitFormService) { }

  getDataDepartamentos(): Observable<any> {
    var LocalURL=this.baseUrlDepto+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  

  
  getListDepartamentos(): Observable<any> {
    var LocalURL=this.baseUrl+'/list-depto/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  

  getData (idDepartamento): Observable<any> {
    var LocalURL=this.baseUrl+'/'+idDepartamento+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  saveData (descripcion,mes,dia,id_departamento): Observable<any> {
    var LocalURL=this.baseUrl+'/save/'+descripcion+'/'+mes+'/'+dia+'/'+id_departamento+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  updateData (id_dia_inhabil,descripcion,mes,dia,id_departamento): Observable<any> {
    var LocalURL=this.baseUrl+'/update/'+id_dia_inhabil+'/'+descripcion+'/'+mes+'/'+dia+'/'+id_departamento+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  deleteData (id_dia_inhabil): Observable<any> {
    var LocalURL=this.baseUrl+'/delete/'+id_dia_inhabil+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  private extractData(res: Response) {
    let body = res;
    return body || { };
  }
  


}
