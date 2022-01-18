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
export class MovimExpService {
	private baseUrl:string=BASE_URL_REST+'tipos-movimexp';
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
  
  getData(idqueja){	  
	  var localURL=this.baseUrl+'/'+idqueja+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataTop5(){	  
	  var localURL=this.baseUrl+'/top5/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataTop5VyV(){	  
	  var localURL=this.baseUrl+'/top5VyV/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataTop5Jur(){	  
	  var localURL=this.baseUrl+'/top5Jur/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  addDataPre (no_queja, dpto_hacia, observaciones): Observable<any> {
	  console.log(this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones));
	  let localURL=this.baseUrl+'/pre';
		return this._http.post<any>(localURL, this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  addDataPreVyV (no_queja, dpto_hacia, observaciones): Observable<any> {
	  console.log(this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones));
	  let localURL=this.baseUrl+'/preVyV';
		return this._http.post<any>(localURL, this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  addDataPreJur (no_queja, dpto_hacia, observaciones): Observable<any> {
	  console.log(this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones));
	  let localURL=this.baseUrl+'/preJur';
		return this._http.post<any>(localURL, this.ObjaJSON_pre(no_queja, dpto_hacia, observaciones), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  addDataSP (no_queja, id_proveedor, observaciones): Observable<any> {
	  console.log(this.ObjaJSON_SP(no_queja, id_proveedor, observaciones));
	  let localURL=this.baseUrl+'/pre';
		return this._http.post<any>(localURL, this.ObjaJSON_SP(no_queja, id_proveedor, observaciones), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  addData (lista_quejas, dpto_hacia, id_flujo, dpto_interno): Observable<any> {
		console.log(this.ObjaJSON_add(lista_quejas, dpto_hacia, id_flujo, dpto_interno));
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(lista_quejas, dpto_hacia, id_flujo, dpto_interno), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
    
  addDataInd (hacia, sede, no_queja): Observable<any> {
		console.log(this.ObjaJSON_ind(hacia, sede, no_queja));
		let localURL=this.baseUrl+'/ind';
		return this._http.post<any>(localURL, this.ObjaJSON_ind(hacia, sede, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  addDataIndVyV (hacia, sede, no_queja): Observable<any> {
		console.log(this.ObjaJSON_indVyV(hacia, sede, no_queja));
		let localURL=this.baseUrl+'/indVyV';
		return this._http.post<any>(localURL, this.ObjaJSON_indVyV(hacia, sede, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  addDataIndJur (hacia, sede, no_queja): Observable<any> {
		console.log(this.ObjaJSON_indJur(hacia, sede, no_queja));
		let localURL=this.baseUrl+'/indJur';
		return this._http.post<any>(localURL, this.ObjaJSON_indJur(hacia, sede, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
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
  
  ObjaJSON_SP(no_queja, id_proveedor, observaciones){
	  var locarray = {  usuario:this._submitFormService.Get_userid(),id_queja: no_queja, id_departamento_interno:1, id_departamento_interno_hasta: 98, id_proveedor:id_proveedor , token: this._submitFormService.Get_token()};
	  if(observaciones!=''){
		  locarray['observaciones']= this.TrimandCut(observaciones,499);
	  }
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_pre(no_queja, dpto_hacia, observaciones){
	  var locarray = {  usuario:this._submitFormService.Get_userid(), id_queja: no_queja, id_departamento_interno:1, id_departamento_interno_hasta: dpto_hacia, token: this._submitFormService.Get_token(), id_proveedor:0};
	  if(observaciones!=''){
		  locarray['observaciones']= this.TrimandCut(observaciones,499);
	  }
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_add(lista_quejas, dpto_hacia, id_flujo, dpto_interno){
	  var locarray = {  usuario:this._submitFormService.Get_userid(),id_departamento_interno:dpto_interno, listado_quejas: lista_quejas.trim(), id_departamento_interno_hasta: dpto_hacia, token: this._submitFormService.Get_token(), id_proveedor:0, id_flujo: id_flujo};
	  console.log(locarray);
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_ind(hacia, sede, no_queja){
	  var locarray = {  usuario:this._submitFormService.Get_userid(),id_departamento_interno:1, id_queja: no_queja, id_departamento_interno_hasta: hacia, token: this._submitFormService.Get_token(), id_sede_hasta: sede, id_sede_diaco_operacio: 1};
	  console.log(locarray);
	  return JSON.stringify(locarray);
  }
  ObjaJSON_indVyV(hacia, sede, no_queja){
	  var locarray = {  usuario:this._submitFormService.Get_userid(),id_departamento_interno:3, id_queja: no_queja, id_departamento_interno_hasta: hacia, token: this._submitFormService.Get_token(), id_sede_hasta: sede, id_sede_diaco_operacio: 1};
	  console.log(locarray);
	  return JSON.stringify(locarray);
  }
  ObjaJSON_indJur(hacia, sede, no_queja){
	  var locarray = {  usuario:this._submitFormService.Get_userid(),id_departamento_interno:2, id_queja: no_queja, id_departamento_interno_hasta: hacia, token: this._submitFormService.Get_token(), id_sede_hasta: sede, id_sede_diaco_operacio: 1};
	  console.log(locarray);
	  return JSON.stringify(locarray);
  }
  TrimandCut(stringc, maxlen){
	  var strlocal=stringc;
	  strlocal = strlocal.trim();
	  if(strlocal.length > maxlen)
			strlocal = strlocal.substring(0,maxlen);
		
	  return strlocal;
  }
  
  
}

