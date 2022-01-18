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
export class FinalizarService {
	private baseUrl:string=BASE_URL_REST+'tipos-finalizado';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  
  getValidadorArchivar(queja){	
	  var localURL=this.baseUrl+'/checkarchivo/'+queja+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getMotivoFinalizado(tipo){	
	  var localURL=this.baseUrl+'/motivofinalizado/'+tipo+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getMotivoFinMotivo(padre){	
	  var localURL=this.baseUrl+'/quejafinmotivo/'+padre+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getMotivoPOA(){	
	  var localURL=this.baseUrl+'/poa/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getFinalizado(idqueja){	  //'F'
	  var localURL=this.baseUrl+'/quejafinalizada/'+idqueja+'/'+this._submitFormService.Get_token();
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
    
  addData (razon, detalle, no_queja): Observable<any> {
		console.log(this.ObjaJSON_add(razon, detalle, no_queja));
		var localURL=this.baseUrl+'/quejafinalizada/';
		return this._http.post<any>(localURL, this.ObjaJSON_add(razon, detalle, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }

  addDataSP (detalle, no_queja): Observable<any> {
		console.log(this.ObjaJSON_addSP(detalle, no_queja));
		var localURL=this.baseUrl+'/quejafinalizadaSP/';
		return this._http.post<any>(localURL, this.ObjaJSON_addSP(detalle, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }	

  addDataVyV (prerazon, motivo_poa, monto, detalle, no_queja): Observable<any> {	
	 if(prerazon==8){
		console.log(this.ObjaJSON_addArchivoVyV(motivo_poa, monto, detalle, no_queja));
		var localURL=this.baseUrl+'/quejafinalizadaVyV/';
		return this._http.post<any>(localURL, this.ObjaJSON_addArchivoVyV(motivo_poa, monto, detalle, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);			
	 }else{
		console.log(this.ObjaJSON_add(prerazon, detalle, no_queja));
		var localURL=this.baseUrl+'/quejafinalizadaVyV/';
		return this._http.post<any>(localURL, this.ObjaJSON_add(prerazon, detalle, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);	
	 }
  
  }
  

  addDataArchivo (prerazon, motivo_poa, razon, categoria, monto, conclusion, comentario, no_queja): Observable<any> {	
	 if(prerazon==8){
		console.log(this.ObjaJSON_addArchivo(motivo_poa, razon, categoria, monto, conclusion, comentario, no_queja));
		var localURL=this.baseUrl+'/quejafinalizada/';
		return this._http.post<any>(localURL, this.ObjaJSON_addArchivo(motivo_poa, razon, categoria, monto, conclusion, comentario, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);			
	 }else{
		console.log(this.ObjaJSON_addAtCon(prerazon, conclusion, comentario, no_queja));
		var localURL=this.baseUrl+'/quejafinalizada/';
		return this._http.post<any>(localURL, this.ObjaJSON_addAtCon(prerazon, conclusion, comentario, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);	
	 }
 }
  
  ObjaJSON_addAtCon(razon, conclusion, comentario, no_queja){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),conclusion: this.TrimandCut(conclusion,499),comentario: this.TrimandCut(comentario,499) , razon: razon, id_queja: no_queja, id_sede_diaco_operacio:1, id_departamento_interno:1, archivo:false, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_add(razon, detalle, no_queja){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),detalles: this.TrimandCut(detalle,99), razon: razon, id_queja: no_queja, id_sede_diaco_operacio:1, id_departamento_interno:1, archivo:false, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_addSP(detalle, no_queja){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),detalles: this.TrimandCut(detalle,99), id_queja: no_queja, id_sede_diaco_operacio:1, id_departamento_interno:1, archivo:false, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }	

  ObjaJSON_addArchivo(motivo_poa, razon, categoria, monto, conclusion, comentario, no_queja){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),conclusion: this.TrimandCut(conclusion,499),comentario: this.TrimandCut(comentario,499), razon: razon, motivo_poa: motivo_poa, categoria: categoria, monto: monto, id_queja: no_queja, id_sede_diaco_operacio:1, id_departamento_interno:1, archivo:true, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }
 
 ObjaJSON_addArchivoVyV(motivo_poa, monto, detalle, no_queja){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(),detalles: this.TrimandCut(detalle,99), motivo_poa: motivo_poa, monto: monto, id_queja: no_queja, id_sede_diaco_operacio:1, id_departamento_interno:1, archivo:true, token: this._submitFormService.Get_token() };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
}

