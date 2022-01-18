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
export class ConsumidorService {
	private baseUrl:string=BASE_URL_REST+'tipos-consumidor';
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
  
  CustomFilter (nombre, apellido, nit, dpi): Observable<any> {
		console.log(this.ObjaJSON_add(nombre, apellido, nit, dpi));
		var localURL=this.baseUrl+'/customf';
		return this._http.post<any>(localURL, this.ObjaJSON_add(nombre, apellido, nit, dpi), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
   
   ObjaJSON_add(nombre, apellido, nit, dpi){
	  var locarray = { con_nombre: this.TrimandCut(nombre,100), con_apellido: this.TrimandCut(apellido,100), con_nit: this.TrimandCut(nit,100), con_dpi: this.TrimandCut(dpi,100), token: this._submitFormService.Get_token() };
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


  ObjaJSON2_add(nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
	codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
	apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
	telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
	nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar){
	var locarray = { nacionalidad: nacionalidad, nit_consumidor : nit_consumidor,
	documento_identificacion: documento_identificacion, genero :genero, direccion: direccion,
	direccion_zona: direccion_zona,
	codigo_municipio :codigo_municipio, codigo_departamento: codigo_departamento,
	sede_diaco_cercana: sede_diaco_cercana,habilitado_notificacion_electronica:habilitado_notificcion_electronica,
	nombre1: nombre1, nombre2: nombre2, nombre3: nombre3 ,
	apellido1: apellido1,apellido2: apellido2, apellido_casada: apellido_casada,
	tipo_consumidor: tipo_consumidor,tipo_documento : tipo_documento,id_pais :id_pais,id_etnia : id_etnia,
	telefono : telefono,domicilio : domicilio,referencia: referencia,
	correo_electronico1 :correo_electronico1,correo_electronico2 : correo_electronico2,
	representante_legal:representante_legal,
	nombre_empresa: nombre_empresa,razon_social : razon_social,id_motivo_queja :id_motivo_queja,
	usuario_modifico : this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token(), autorizoPublicar: autorizoPublicar };
	return JSON.stringify(locarray);
}

ObjaJSON_update( id_consumidor,nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
	codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
	apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
	telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
	nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar){
	var locarray = { id_consumidor : id_consumidor ,nacionalidad: nacionalidad, nit_consumidor : nit_consumidor,
	documento_identificacion: documento_identificacion, genero :genero, direccion: direccion,
	direccion_zona: direccion_zona,
	codigo_municipio :codigo_municipio, codigo_departamento: codigo_departamento,
	sede_diaco_cercana: sede_diaco_cercana,habilitado_notificacion_electronica:habilitado_notificcion_electronica,
	nombre1: nombre1, nombre2: nombre2, nombre3: nombre3 ,
	apellido1: apellido1,apellido2: apellido2, apellido_casada: apellido_casada,
	tipo_consumidor: tipo_consumidor,tipo_documento : tipo_documento,id_pais :id_pais,id_etnia : id_etnia,
	telefono : telefono,domicilio : domicilio,referencia: referencia,
	correo_electronico1 :correo_electronico1,correo_electronico2 : correo_electronico2,
	representante_legal:representante_legal,
	nombre_empresa: nombre_empresa,razon_social : razon_social,id_motivo_queja :id_motivo_queja,
	usuario_modifico : this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token(),
	autorizoPublicar : autorizoPublicar  };
	return JSON.stringify(locarray);
}

saveData (nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
	codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
	apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
	telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
	nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar): Observable<any> {
	console.log(this.ObjaJSON2_add(nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
		codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
		apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
		telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
		nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar));
	return this._http.post<any>(this.baseUrl+'/save', this.ObjaJSON2_add(nacionalidad,nit_consumidor,
		documento_identificacion,genero,direccion,direccion_zona,
		codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
		apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
		telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
		nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar), this.httpOptions).pipe(
		tap((item) => console.log('Added item')),
		catchError(this.handleError<any>('Add Data'))
	);
}

updateData (id_consumidor,nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
	codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
	apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
	telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
	nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar): Observable<any> {
	console.log(this.ObjaJSON_update(id_consumidor,nacionalidad,nit_consumidor,documento_identificacion,genero,direccion,direccion_zona,
		codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
		apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
		telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
		nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar));
	return this._http.post<any>(this.baseUrl+'/update', this.ObjaJSON_update(id_consumidor,nacionalidad,nit_consumidor,
		documento_identificacion,genero,direccion,direccion_zona,
		codigo_municipio, codigo_departamento,sede_diaco_cercana,habilitado_notificcion_electronica,nombre1,nombre2,nombre3,
		apellido1,apellido2, apellido_casada,tipo_consumidor,tipo_documento,id_pais,id_etnia,
		telefono,domicilio,referencia,correo_electronico1,correo_electronico2,representante_legal,
		nombre_empresa,razon_social,id_motivo_queja,autorizoPublicar), this.httpOptions).pipe(
		tap((item) => console.log('Updated item')),
		catchError(this.handleError<any>('Update Data'))
	);
}


findByNit(nit): Observable<any> {
    var LocalURL=this.baseUrl+'/find/'+nit+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }

  findByDpi(dpi): Observable<any> {
    var LocalURL=this.baseUrl+'/findDpi/'+dpi+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }

  
	deleteData (id): Observable<any> {
		var LocalURL=this.baseUrl+'/delete/'+id+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
	  }  
 

  
}

