import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../conc-virt-const'
import { SubmitFormService } from '../shared/submit-form.service'

@Injectable({
  providedIn: 'root'
})
export class InteraccionService {
	private baseUrl:string=BASE_URL_REST+'tipos-concvirtint';
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
  
  getData(noqueja){	  
	  var localURL=this.baseUrl+'/'+noqueja+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
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
    
  addData (pregunta, cambiar_archivos, cambiar_datos, id_queja): Observable<any> {
		console.log(this.ObjaJSON_add(pregunta, cambiar_archivos, cambiar_datos, id_queja));
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(pregunta, cambiar_archivos, cambiar_datos, id_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
    
  ObjaJSON_add(pregunta, cambiar_archivos, cambiar_datos, id_queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(),pregunta: this.TrimandCut(pregunta,199), cambiar_archivos: cambiar_archivos, cambiar_datos: cambiar_datos, id_queja: id_queja, id_departamento_interno:1, id_flujo:4, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
}

