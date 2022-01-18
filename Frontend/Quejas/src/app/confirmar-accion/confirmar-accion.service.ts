import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { SubmitFormService } from '../shared/submit-form.service';

@Injectable({
  providedIn: 'root'
})
export class ConfirmarAccionService {
	private baseUrl:string=BASE_URL_REST+'tipos-confaccion';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  
  getAll(id_usuario){	
	  let localUrl=this.baseUrl+'/'+id_usuario+'/'+this._submitFormService.Get_token();
	  console.log(localUrl);
	  return this._http.get(localUrl,this.httpOptions).pipe(map(this.extractData));
  }

  getFechaAceptacion(id_usuario, id_queja){	
	let localUrl=this.baseUrl+'/ultima_aceptacion';
	console.log(localUrl);
	return this._http.post(localUrl,this.ObjaJSON_add2(id_usuario, id_queja),this.httpOptions).pipe(map(this.extractData));
}

  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }

  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
    
  addData (id_usuario, queja_list): Observable<any> {
		console.log(this.ObjaJSON_add(id_usuario, queja_list));
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(id_usuario, queja_list), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }

  ObjaJSON_add(id_usuario, queja_list){
	  var locarray = { listado_quejas:queja_list, usuario: id_usuario, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_add2(id_usuario, id_queja){
	var locarray = { creado_por:id_usuario, id_queja: id_queja, token: this._submitFormService.Get_token() };
	return JSON.stringify(locarray);
}

  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
}

