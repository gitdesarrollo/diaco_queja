import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { SubmitFormService } from "../shared/submit-form.service";

@Injectable({
  providedIn: 'root'
})
export class QuejaService {
	private baseUrl:string=BASE_URL_REST+'tipos-queja';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  constructor(private _http:HttpClient, private datePipe: DatePipe, private _submitFormService:SubmitFormService) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
  
  getbyCodigo(codigoqueja){	  
	  var localURL=this.baseUrl+'/searchcodigo/'+codigoqueja+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataQueja(id){	  
	  var localURL=this.baseUrl+'/'+id+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  saveEstadoResolver(id, est_resolver){	  
	var localURL=this.baseUrl+'/est_resolver';
	console.log(localURL);
	return this._http.post(localURL,this.ObjaJSON_DocumentarEstado(id, est_resolver),this.httpOptions).pipe(map(this.extractData));
}
  
  getDataQuejaP(id){	  
	  var localURL=this.baseUrl+'/'+id+'/P/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  saveDocumentar (id_queja, valor): Observable<any> {
	  var valor_int;
	  console.log(this.ObjaJSON_Documentar(id_queja, valor));
	  var localURL=this.baseUrl+'/documentar';
	  if(valor){
			valor_int=1;
	  }else{
			valor_int=0;
	  }
		return this._http.post<any>(localURL, this.ObjaJSON_Documentar(id_queja, valor), this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
  }  
  
  saveReasignar (actualizaestado, id_queja, flujo, usuario_asignado): Observable<any> {
	  console.log(this.ObjaJSON_Reasignar(actualizaestado,id_queja, flujo, usuario_asignado));
	  var localURL=this.baseUrl+'/reasignar';
		return this._http.post<any>(localURL, this.ObjaJSON_Reasignar(actualizaestado,id_queja, flujo, usuario_asignado), this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}  
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
  
  getEstadosQueja(){	  
	  var localURL=this.baseUrl+'/estados/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  convertDate(date){
	  return this.datePipe.transform(date,"yyyy-MM-dd");
  }
  
  ObjaJSON_Documentar(id_queja, valor){
	  var locarray = { id_queja:id_queja, valor:valor, usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_DocumentarEstado(id_queja, estado_resolver){
	var locarray = { id_queja:id_queja, estado_resolver:estado_resolver, usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
	return JSON.stringify(locarray);
}  
  
  ObjaJSON_Reasignar(actualizaestado, id_queja, flujo, usuario_asignado){
	  var locarray = { id_queja:id_queja, valor:usuario_asignado, usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_flujo: flujo, valor2: actualizaestado};
	  return JSON.stringify(locarray);
  }
  
}

