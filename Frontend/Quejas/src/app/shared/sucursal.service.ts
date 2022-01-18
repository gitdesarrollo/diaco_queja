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
export class SucursalService {
	private baseUrl:string=BASE_URL_REST+'tipos-sucursal';
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
  
   
  
  errorHandler(error:Response){
	return Observable.throw(error||"Server Error");
  }
    
	TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }


  ObjaJSON2_add(id_proveedor, codigo_departamento,codigo_municipio, direccion,
    zona ,telefono){
	var locarray = { 
    id_proveedor : id_proveedor, codigo_departamento : codigo_departamento,
    codigo_municipio : codigo_municipio, direccion : direccion,
    zona : zona ,telefono : telefono,  
	usuario : this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token() };
	return JSON.stringify(locarray);
}

ObjaJSON_update( id_sucursal,id_proveedor, codigo_departamento,codigo_municipio, direccion,
  zona ,telefono){
	var locarray = { id_sucursal,id_proveedor, codigo_departamento,codigo_municipio, direccion,
    zona ,telefono,
	usuario: this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token()
  };
	return JSON.stringify(locarray);
}

saveData (
  id_proveedor, codigo_departamento,codigo_municipio, direccion,
  zona ,telefono
): Observable<any> {
	console.log(this.ObjaJSON2_add(id_proveedor, codigo_departamento,codigo_municipio, direccion,
    zona ,telefono));
	return this._http.post<any>(this.baseUrl+'/save', this.ObjaJSON2_add(id_proveedor, codigo_departamento,codigo_municipio, direccion,
    zona ,telefono), this.httpOptions).pipe(
		tap((item) => console.log('Added item')),
		catchError(this.handleError<any>('Add Data'))
	);
}

updateData (id_sucursal,id_proveedor, codigo_departamento,codigo_municipio, direccion,
  zona ,telefono): Observable<any> {
	console.log(this.ObjaJSON_update(id_sucursal,id_proveedor, codigo_departamento,codigo_municipio, direccion,
    zona ,telefono));
	return this._http.post<any>(this.baseUrl+'/update', this.ObjaJSON_update(
    id_sucursal,id_proveedor, codigo_departamento,codigo_municipio, direccion,
  zona ,telefono), this.httpOptions).pipe(
		tap((item) => console.log('Updated item')),
		catchError(this.handleError<any>('Update Data'))
	);
}


find(id_proveedor): Observable<any> {
    var LocalURL=this.baseUrl+'/find/'+id_proveedor+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  

  
	deleteData (id): Observable<any> {
		var LocalURL=this.baseUrl+'/delete/'+id+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
	  }  
 

  
}

