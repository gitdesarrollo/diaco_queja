import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST_FILE, BASE_URL_REST} from "../atencion-consumidor-const";
import { SubmitFormService } from '../shared/submit-form.service';

@Injectable({
  providedIn: 'root'
})
export class CompermanenteService {
	private baseUrl:string=BASE_URL_REST+'tipos-comconsumidor';
//	private baseUrlRegistro:string=BASE_URL_REST_FILE+'registros-queja';
private baseUrlRegistro:string=BASE_URL_REST+'registros-queja';
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
  
  getData(noqueja, flujo){	  
	  var localURL=this.baseUrl+'/'+noqueja+'/'+this._submitFormService.Get_token()+'/'+flujo;
	  console.log(localURL);
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
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
    
  addData (estatus, consumidor, via, no_queja, flujo): Observable<any> {
		console.log(this.ObjaJSON_add(estatus, consumidor, via, no_queja, flujo));
		//return this._http.post(this.baseUrl, this.ObjaJSON_add(estatus, consumidor, via, no_queja), this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(estatus, consumidor, via, no_queja, flujo), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
    
  ObjaJSON_add(estatus, observaciones, via, no_queja, flujo){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),estatus: this.TrimandCut(estatus,399), observaciones: this.TrimandCut(observaciones,399), via:via, id_queja: no_queja, id_sede_diaco_operacio:1,
  id_departamento_interno:1, token: this._submitFormService.Get_token(), id_flujo: flujo };
	  //console.log(bitacora+'-'+consumidor+'-'+no_queja);
	  //console.log(locarray);
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
	}
	
	downloadPDFFile(data) {
	  const blob = new Blob([data], { type: 'application/pdf' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}
  
}

