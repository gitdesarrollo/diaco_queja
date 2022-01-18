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
export class MunicipioService {
  private baseUrl:string=BASE_URL_REST+'tipos-muni';
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


  
  findByIdMunicipio (idMunicipio): Observable<any> {
    var LocalURL=this.baseUrl+'/muni/'+idMunicipio+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  } 

  getData (idDepartamento): Observable<any> {
    var LocalURL=this.baseUrl+'/'+idDepartamento+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  saveData (codigoDepartamento,nombreMunicipio): Observable<any> {
    var LocalURL=this.baseUrl+'/save/'+codigoDepartamento+'/'+nombreMunicipio+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  updateData (codigoDepartamento,codigoMunicipio,nombreMunicipio): Observable<any> {
    var LocalURL=this.baseUrl+'/update/'+codigoDepartamento+'/'+codigoMunicipio+'/'+nombreMunicipio+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  deleteData (codigoMunicipio): Observable<any> {
    var LocalURL=this.baseUrl+'/delete/'+codigoMunicipio+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  private extractData(res: Response) {
    let body = res;
    return body || { };
  }
  

}
