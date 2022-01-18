import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { SubmitFormService } from "../shared/submit-form.service";

import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class SedeService {
  private baseUrl:string=BASE_URL_REST+'tipos-sede';
  private baseUrlMuni:string=BASE_URL_REST+'tipos-muni';
  private baseUrlDepto:string=BASE_URL_REST+'tipos-dpto';
  private headers= new Headers({'content-type':'application/json; charset=iso-8859-1'});
  private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	private options=new RequestOptions({headers:this.headers});
  constructor(private _http:Http, private _submitFormService:SubmitFormService,private _httpx:HttpClient) { }
  
  getAll(){	  
	  var localURL=this.baseUrl+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  getData(id){	  
	  var localURL=this.baseUrl+'/'+id+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  getDataColaSedes(){	  
	  var localURL=this.baseUrl+'/colasedes/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
    
  getColasxUsuario(idusuario){	  
	  var localURL=this.baseUrl+'/colasxusuario/'+this._submitFormService.Get_token()+'/'+idusuario;
	  return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }	
	
  errorHandler(error:Response){
	return Observable.throw(error||"Server Error");
  }
    
  getDataDepartamentos(): Observable<any> {
    var LocalURL=this.baseUrlDepto+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.options).pipe(map(this.extractData));
  }  

  
  getDataMunicipios (idDepartamento): Observable<any> {
    var LocalURL=this.baseUrlMuni+'/'+idDepartamento+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.options).pipe(map(this.extractData));
  }  

  
  saveData (nombreSede,codigoMunicipio,direccionAvenida,direccionCalle,direccionZona,direccionDetalle,avenidaOCalle): Observable<any> {
    var LocalURL=this.baseUrl+'/save/'+nombreSede+'/'+codigoMunicipio+'/'+direccionAvenida+'/'+direccionCalle+'/'+direccionZona+'/'+direccionDetalle+'/'+avenidaOCalle+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._httpx.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  updateData (idDiacoSede,nombreSede,codigoMunicipio,direccionAvenida,direccionCalle,direccionZona,direccionDetalle,avenidaOCalle): Observable<any> {
    var LocalURL=this.baseUrl+'/update/'+idDiacoSede+'/'+nombreSede+'/'+codigoMunicipio+'/'+direccionAvenida+'/'+direccionCalle+'/'+direccionZona+'/'+direccionDetalle+'/'+avenidaOCalle+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._httpx.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
  
  deleteData (idDiacoSede): Observable<any> {
    var LocalURL=this.baseUrl+'/delete/'+idDiacoSede+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._httpx.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  

  getListSede(): Observable<any> {
    var LocalURL=this.baseUrl+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._httpx.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }
}

