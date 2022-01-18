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
export class UsuarioService {
	private baseUrl:string=BASE_URL_REST+'tipos-usuario';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead
			// TODO: better job of transforming error for user consumption
			console.log('${operation} failed: ${error.message}');
			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
  }
    
  getData(id_flujo){	 
	  var localURL=this.baseUrl+'/usuario_xflujo/'+id_flujo+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }

  getSingle(id){	
	  var localURL=this.baseUrl+'/'+id+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  getUsuarioConf(id){	  
	  var localURL=this.baseUrl+'/usuario_conf/'+id+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  getListAsignaCola(id, id_flujo){
	  var localURL=this.baseUrl+'/asignacola/'+id+'/'+id_flujo+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
  getUsuarioConfPP(id){	  
	  var localURL=this.baseUrl+'/usuario_confpp/'+id+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
  }
  
	CustomFilter(flujo, usuario, nombre){
		console.log(this.ObjaJSON_customfilter(flujo, usuario, nombre));
		var localURL=this.baseUrl+'/customfilter';
		return this._http.post<any>(localURL, this.ObjaJSON_customfilter(flujo, usuario, nombre), this.httpOptions).pipe(
			catchError(this.handleError<any>('Add Data'))
		);
	}

	CustomFilterPP(proveedor, usuario, nombre){
		console.log(this.ObjaJSON_customfilterpp(proveedor, usuario, nombre));
		var localURL=this.baseUrl+'/customfilterconcprev';
		return this._http.post<any>(localURL, this.ObjaJSON_customfilterpp(proveedor, usuario, nombre), this.httpOptions).pipe(
			catchError(this.handleError<any>('Add Data'))
		);
	}
  
  ObjaJSON_customfilter(flujo, usuario, nombre){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), flujo: flujo, usuario: this.TrimandCut(usuario, 50), nombre: this.TrimandCut(nombre, 100) };
	  return JSON.stringify(locarray);
  }
  ObjaJSON_customfilterpp(proveedor, usuario, nombre){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), proveedor: proveedor, usuario: this.TrimandCut(usuario, 50), nombre: this.TrimandCut(nombre, 100) };
	  return JSON.stringify(locarray);
  }
  
  errorHandler(error:Response){
	return Observable.throw(error||"Server Error");
  }
    
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
}

