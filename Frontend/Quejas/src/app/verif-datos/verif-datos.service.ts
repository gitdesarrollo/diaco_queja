import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { SubmitFormService } from "../shared/submit-form.service";

@Injectable({
  providedIn: 'root'
})
export class VerifDatosService {
	private baseUrl:string=BASE_URL_REST+'tipos-verifdatos';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  
  getInteracciones(idqueja){	
	  var localURL=this.baseUrl+'/getInt/'+idqueja+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  updFlujoGuia(idqueja, id_flujo){	
	  var localURL=this.baseUrl+'/FlujoGuia/'+idqueja+'/'+id_flujo+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

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
    
  addDataInt (no_queja): Observable<any> {
		console.log(this.ObjaJSON_add(no_queja));
		var localURL=this.baseUrl+'/saveInt';
		return this._http.post<any>(localURL, this.ObjaJSON_add(no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }

  ObjaJSON_add(no_queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(), id_queja: no_queja, id_departamento_interno:1, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }

  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
}

