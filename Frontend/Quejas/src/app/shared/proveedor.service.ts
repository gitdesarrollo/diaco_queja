import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { FrmMainParam, FrmMainFD, BASE_URL_REST } from "../atencion-consumidor-const";
import { SubmitFormService } from "../shared/submit-form.service";
import { Proveedor } from "../models/proveedor";

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {
	private baseUrl:string=BASE_URL_REST+'tipos-proveedor';
	private baseUrlComun:string=BASE_URL_REST+'tipos-proveedor-comun';
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
  
  getProveedoresSP(){	  
	  var localURL=this.baseUrl+'/serv_pub/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getProveedoresnoneSP(){	  
	  var localURL=this.baseUrl+'/none_serv_pub/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getProveedoresCPoSP(){	  
	  var localURL=this.baseUrl+'/cp_sp/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getProveedorAprobar(estado){	  
	  //http://localhost/diaco-int-web/rs/tipos-proveedor/prov_aprobar2/  
		console.log('getProveedorAprobar');
	  console.log(this.baseUrl+'/prov_aprobar/');
	  var localURL=this.baseUrl+'/prov_aprobar/'+this._submitFormService.Get_token()+'/'+estado;
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  saveProveedorAprobar(estado, id){	
	  //http://localhost/diaco-int-web/rs/tipos-proveedor/prov_aprobar2/  
	  console.log('saveProveedorAprobar');
	  console.log(this.baseUrl+'/prov_aprobar2/');
	  var localURL=this.baseUrl+'/prov_aprobar2/'+this._submitFormService.Get_token()+'/'+estado+'/'+id+'/'+this._submitFormService.Get_userid();
	  console.log('localURL '+localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  rechazoProveedorAprobar(razon, id){	  
	  var localURL=this.baseUrl+'/prov_aprobar_rechazo/'+this._submitFormService.Get_token()+'/'+razon+'/'+id+'/'+this._submitFormService.Get_userid();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  CustomFilter (nombre, nit): Observable<any> {
		console.log(this.ObjaJSON_add(nombre, nit));
		var localURL=this.baseUrl+'/customf';
		return this._http.post<any>(localURL, this.ObjaJSON_add(nombre, nit), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
   
   ObjaJSON_add(nombre, nit){
	  var locarray = { pro_nombre: this.TrimandCut(nombre,100), pro_nit: this.TrimandCut(nit,100), token: this._submitFormService.Get_token() };
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




  ObjaJSON2_add(nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion){
	var locarray = { 
		nombre: nombre,nit_proveedor : nit, codigo_departamento : codigoDepartamento,codigo_municipio :codigoMunicipio,
		primer_nombre : primerNombre,segundo_nombre :segundoNombre,tercer_nombre:tercerNombre,
		primer_apellido:primerApellido,segundo_apellido:segundoApellido,
	apellido_casada:apellidoCasada,direccion:direccion,direccion_zona:direccionZona,
	nombre_empresa:nombreEmpresa,razon_social:razonSocial,email:email,tipo_proveedor:tipoProveedor,
	telefono:telefono,id_actividad_economica:idActividadEconomica,
	id_tipo_comercio:idTipoComercio,habilitado_notificacion_electronica:habilitarNotificacion,
	servicio_publico:servicioPublico,
	habilitado_conciliacion_previa:conciliacion,permite_calificacion:calificacion,
	usuario : this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token() };
	return JSON.stringify(locarray);
}

ObjaJSON_update( idProveedor,nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,
	segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,
	servicioPublico,conciliacion,calificacion){
	var locarray = { 
		id_proveedor : idProveedor,nombre:nombre,nit_proveedor:nit,
		codigo_departamento:codigoDepartamento,
		codigo_municipio:codigoMunicipio,primer_nombre:primerNombre,
		segundo_nombre:segundoNombre,tercer_nombre:tercerNombre,primer_apellido:primerApellido,
		segundo_apellido:segundoApellido,
		apellido_casada:apellidoCasada,direccion:direccion,
		direccion_zona:direccionZona,nombre_empresa:nombreEmpresa,razon_social:razonSocial,
		email:email,tipo_proveedor:tipoProveedor,
		telefono:telefono,id_actividad_economica:idActividadEconomica,
		id_tipo_comercio:idTipoComercio,
		habilitado_notificacion_electronica:habilitarNotificacion,
		servicio_publico:servicioPublico,
		habilitado_conciliacion_previa:conciliacion,permite_calificacion:calificacion,		
		usuario : this._submitFormService.Get_username(),
		token: this._submitFormService.Get_token()};
	return JSON.stringify(locarray);
}

saveData (nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion
): Observable<any> {
	console.log(this.ObjaJSON2_add(
		nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
		apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
		telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion	
	));
	return this._http.post<any>(this.baseUrl+'/save', this.ObjaJSON2_add(nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
		apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
		telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion), this.httpOptions).pipe(
		tap((item) => console.log('Added item')),
		catchError(this.handleError<any>('Add Data'))
	);
}

/*
  saveData (nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion): Observable<any> {
	var LocalURL=this.baseUrl+'/save/'+nombre+'/'+nit+'/'+codigoDepartamento+'/'+
	codigoMunicipio+'/'+primerNombre+'/'+segundoNombre+'/'+tercerNombre+'/'+primerApellido+'/'+segundoApellido+
	'/'+apellidoCasada+'/'+direccion+'/'+direccionZona+'/'+nombreEmpresa+'/'+
	razonSocial+'/'+email+'/'+tipoProveedor+'/'+telefono+'/'+this._submitFormService.Get_username()+
	'/'+idActividadEconomica+'/'+idTipoComercio+'/'+habilitarNotificacion+'/'+servicioPublico+'/'+conciliacion+'/'+calificacion+'/'+this._submitFormService.Get_token();
    console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
*/

updateData (idProveedor,nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,
	segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion): Observable<any> {
	console.log(this.ObjaJSON_update(idProveedor,nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,
		segundoNombre,tercerNombre,primerApellido,segundoApellido,
		apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
		telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion));
	return this._http.post<any>(this.baseUrl+'/update', this.ObjaJSON_update(idProveedor,nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,
		segundoNombre,tercerNombre,primerApellido,segundoApellido,
		apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoProveedor,
		telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion), this.httpOptions).pipe(
		tap((item) => console.log('Updated item')),
		catchError(this.handleError<any>('Update Data'))
	);
}
/*
  updateData (idProveedor,nombre,nit,codigoDepartamento,codigoMunicipio,primerNombre,segundoNombre,tercerNombre,primerApellido,segundoApellido,
	apellidoCasada,direccion,direccionZona,nombreEmpresa,razonSocial,email,tipoComercio,
	telefono,idActividadEconomica,idTipoComercio,habilitarNotificacion,servicioPublico,conciliacion,calificacion): Observable<any> {
	var LocalURL=this.baseUrl+'/update/'+idProveedor+'/'+nombre+'/'+nit+'/'+codigoDepartamento+'/'+
	codigoMunicipio+'/'+primerNombre+'/'+segundoNombre+'/'+tercerNombre+'/'+primerApellido+'/'+segundoApellido+
	'/'+apellidoCasada+'/'+direccion+'/'+direccionZona+'/'+nombreEmpresa+'/'+
	razonSocial+'/'+email+'/'+tipoComercio+'/'+telefono+'/'+this._submitFormService.Get_username()+
	'/'+idActividadEconomica+'/'+idTipoComercio+'/'+habilitarNotificacion+'/'+servicioPublico+'/'+conciliacion+'/'+calificacion+'/'+this._submitFormService.Get_token();    
	console.log(LocalURL);
    return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
  }  
*/

	deleteData (idProveedor): Observable<any> {
		var LocalURL=this.baseUrl+'/delete/'+idProveedor+'/'+this._submitFormService.Get_username()+'/'+this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.post(LocalURL,this.httpOptions).pipe(map(this.extractData));
	  }  
  
	  findByNitProveedor (nitProveedor): Observable<any> {
		var LocalURL=this.baseUrl+'/find/'+nitProveedor+'/'+this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
	  } 

	  findById (idProveedor): Observable<any> {
		var LocalURL=this.baseUrlComun+'/findById/'+idProveedor+'/'+this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
	  } 

   getListProveedorComun() {
	var LocalURL=this.baseUrlComun+'/'+this._submitFormService.Get_token();
	console.log(LocalURL);
	return this._http.get(LocalURL,this.httpOptions).pipe(map(this.extractData));
  
   }	  

   getList(){	  
	var localURL=this.baseUrl+'/all/'+this._submitFormService.Get_token();
	return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
}

  /*
  /save/{nombre}/{nit}/{codigoDepartamento}/{codigoMunicipio}/{primerNombre}/"
  + "{segundoNombre}/{tercerNombre}/"
  + "{primerApellido}/{segundoApellido}/{apellidoCasada}/{direccionCalle}/"
  + "{direccionAvenida}/{direccionZona}/{direccionDetalle}/{nombreEmpresa}/"
  + "{razonSocial}/{email}/{tipoProveedor}/"
  + "{telefono}/{usuario}/{idActividadEconomica}/{token}
*/
}

