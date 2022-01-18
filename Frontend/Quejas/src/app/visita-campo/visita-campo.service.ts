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
export class VisitaCampoService {
	private baseUrl:string=BASE_URL_REST+'tipos-visitacampo';
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
  
  getData(idqueja, flujo){	  
	  var localURL=this.baseUrl+'/'+idqueja+'/'+flujo+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  addData (hecha_por, fecha_visita, observaciones, no_queja, flujo): Observable<any> {
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(hecha_por, fecha_visita, observaciones, no_queja, flujo), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  deleteData (id,no_queja, flujo): Observable<any> {
		var localURL=this.baseUrl+'/del';
		return this._http.put<any>(localURL, this.ObjaJSON_del(id,no_queja, flujo), this.httpOptions).pipe(
			tap((item) => console.log('Deleted item')),
			catchError(this.handleError<any>('Delete Data'))
		);
  }
  
  editData (id, hecha_por, fecha_visita, observaciones, no_queja, flujo): Observable<any> {
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_edit(id, hecha_por, fecha_visita, observaciones, no_queja, flujo), this.httpOptions).pipe(
			tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);

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
    
  convertDate(date){
	return this.datePipe.transform(date,"yyyy-MM-dd")+'T00:00:00Z';
  }
  
  dateFormat(date){
	  return this.datePipe.transform(date,"dd/MM/yyyy");
  }
  
  ObjaJSON_add(hecha_por, fecha_visita, observaciones, no_queja, flujo){
	  var locarray = {  usuario_operacion:this._submitFormService.Get_userid(),fecha_visita: this.dateFormat(fecha_visita),hecha_por: hecha_por, observaciones: this.TrimandCut(observaciones,499), id_queja: no_queja, id_departamento_interno:1, edit:false, token: this._submitFormService.Get_token(), id_flujo: flujo };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_del(id,no_queja, flujo){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), id_visita_campo:id, id_departamento_interno:1, id_queja:no_queja, token: this._submitFormService.Get_token(), id_flujo: flujo };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_edit(id, hecha_por, fecha_visita, observaciones, no_queja, flujo){
	  var locarray = {  id_visita_campo: id, usuario_operacion:this._submitFormService.Get_userid(),fecha_visita: this.dateFormat(fecha_visita),hecha_por: hecha_por, observaciones: this.TrimandCut(observaciones,499), id_queja: no_queja, id_departamento_interno:1, edit:true, token: this._submitFormService.Get_token(), id_flujo: flujo };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
  
}

