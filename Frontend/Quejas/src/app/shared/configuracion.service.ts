import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { SubmitFormService } from "../shared/submit-form.service";
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionService {
	
	private baseUrl:string=BASE_URL_REST+'tipos-configuracion';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private datePipe: DatePipe, private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  
  getParamGeneral(){	
	  var localURL=this.baseUrl+'/paramgenerales/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getConfGeneral(){	
	  var localURL=this.baseUrl+'/confgeneral/'+this._submitFormService.Get_token();
	//   console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getQR(){	
		var localURL=this.baseUrl+'/qrpath/';
		// console.log(localURL);
		return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	}
  
	getColaTodoxFlujo(id_flujo){	
	  var localURL=this.baseUrl+'/asignacola/get/'+this._submitFormService.Get_token()+'/'+id_flujo;
	//   console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
	
	getVyvAsignaInicial(id_queja){	
	  var localURL=this.baseUrl+'/asignavyv/'+this._submitFormService.Get_token()+'/'+id_queja;
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getAsignaCola(idcola){	
	  var localURL=this.baseUrl+'/asignacola/'+this._submitFormService.Get_token()+'/'+idcola;
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }

  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
    
  saveParamGeneral (uma, titulo, genero, nombre): Observable<any> {
		console.log(this.ObjaJSON_ParamGenerales(uma, titulo, genero, nombre));
		var localURL=this.baseUrl+'/paramgenerales';
		return this._http.post<any>(localURL, this.ObjaJSON_ParamGenerales(uma, titulo, genero, nombre), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  saveConfUsuario (nit, usuario, nombre, dpi, estado, email, puesto, sede, telefono, glob_paramgen, valor_global_aprobarpp, valor_global_catalogos, id_usuario, valor_global_admusuarios, rolAtConCtrl, valor_atcon_admcolas, valor_atcon_presencial, rolJurCtrl, valor_jur_admcolas, rolVyVCtrl, valor_vyv_admcolas, rolSPCtrl,  valor_sp_admcolas, valor_global_configuracion, resetpassword, valor_atcon_call_center): Observable<any> {
		// console.log(this.ObjaJSON_ConfUsuario(nit, usuario, nombre, dpi, estado, email, puesto, sede, telefono, glob_paramgen,valor_global_aprobarpp, valor_global_catalogos, id_usuario, valor_global_admusuarios, rolAtConCtrl, valor_atcon_admcolas, valor_atcon_presencial, rolJurCtrl, valor_jur_admcolas, rolVyVCtrl, valor_vyv_admcolas, rolSPCtrl,  valor_sp_admcolas, valor_global_configuracion, resetpassword));
		var localURL=this.baseUrl+'/confusuario';
		return this._http.post<any>(localURL, this.ObjaJSON_ConfUsuario(nit, usuario, nombre, dpi, estado, email, puesto, sede, telefono, glob_paramgen,valor_global_aprobarpp, valor_global_catalogos, id_usuario, valor_global_admusuarios, rolAtConCtrl, valor_atcon_admcolas, valor_atcon_presencial, rolJurCtrl, valor_jur_admcolas, rolVyVCtrl, valor_vyv_admcolas, rolSPCtrl,  valor_sp_admcolas, valor_global_configuracion, resetpassword, valor_atcon_call_center), this.httpOptions).pipe(
			/*tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))*/
			map(this.extractData)
			);
			
  }
    saveConfUsuarioPP (nit, usuario, nombre, dpi, estado, email, telefono, id_usuario, rolPPCtrl, proveedor, resetpassword): Observable<any> {
		console.log('Entrando a saveConfUsuarioPP: '+this.ObjaJSON_ConfUsuarioPP(nit, usuario, nombre, dpi, estado, email, telefono, id_usuario, rolPPCtrl, proveedor, resetpassword));
		var localURL=this.baseUrl+'/confusuariopp';
		return this._http.post<any>(localURL, this.ObjaJSON_ConfUsuarioPP(nit, usuario, nombre, dpi, estado, email, telefono, id_usuario, rolPPCtrl, proveedor, resetpassword), this.httpOptions).pipe(
			/*tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))*/
			map(this.extractData)
		);
	} 
	saveConfiguracion (dias_expirar_atcon, dias_alerta_atcon, dias_link_verif_datos, dias_expirar_jur, dias_alerta_jur, dias_expirar_vyv, dias_alerta_vyv, dias_expirar_sp, dias_alerta_sp, dias_expirar_pp, dias_link_int_conc_virt, dias_link_conf_queja_fin, correo_com_perm, correo_verif_datos, correo_audiencias, correo_arch_sp, correo_confrespp): Observable<any> {
		console.log(this.ObjaJSON_Configuracion(dias_expirar_atcon, dias_alerta_atcon, dias_link_verif_datos, dias_expirar_jur, dias_alerta_jur, dias_expirar_vyv, dias_alerta_vyv, dias_expirar_sp, dias_alerta_sp, dias_expirar_pp, dias_link_int_conc_virt, dias_link_conf_queja_fin, correo_com_perm, correo_verif_datos, correo_audiencias, correo_arch_sp, correo_confrespp));
		var localURL=this.baseUrl+'/confgeneral';
		return this._http.post<any>(localURL, this.ObjaJSON_Configuracion(dias_expirar_atcon, dias_alerta_atcon, dias_link_verif_datos, dias_expirar_jur, dias_alerta_jur, dias_expirar_vyv, dias_alerta_vyv, dias_expirar_sp, dias_alerta_sp, dias_expirar_pp, dias_link_int_conc_virt, dias_link_conf_queja_fin, correo_com_perm, correo_verif_datos, correo_audiencias, correo_arch_sp, correo_confrespp), this.httpOptions).pipe(
			/*tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))*/
			map(this.extractData)
		);
	}
	//asigna cola
	saveColaAsig_EstadoUpdate (estado, id_asignacion): Observable<any> {
		console.log(this.ObjaJSON_ColaAsig_EstadoUpdate(estado, id_asignacion));
		var localURL=this.baseUrl+'/asignacola';
		return this._http.post<any>(localURL, this.ObjaJSON_ColaAsig_EstadoUpdate(estado, id_asignacion), this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	saveColaAsig_Schedule (id_asignacion, fecha): Observable<any> {
		console.log(this.ObjaJSON_ColaAsig_Schedule(id_asignacion, fecha));
		var localURL=this.baseUrl+'/asignacola';
		return this._http.post<any>(localURL, this.ObjaJSON_ColaAsig_Schedule(id_asignacion, fecha), this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	saveColaAsig_Del_Schedule (id_asignacion): Observable<any> {
		console.log(this.ObjaJSON_ColaAsig_Del_Schedule(id_asignacion));
		var localURL=this.baseUrl+'/asignacola';
		return this._http.post<any>(localURL, this.ObjaJSON_ColaAsig_Del_Schedule(id_asignacion), this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	saveColaAsigNew(id_tipo_cola, id_usuario): Observable<any> {
		console.log(this.ObjaJSON_ColaAsigNew(id_tipo_cola, id_usuario));
		var localURL=this.baseUrl+'/asignacolanew';
		return this._http.post<any>(localURL, this.ObjaJSON_ColaAsigNew(id_tipo_cola, id_usuario), this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	deleteColaAsig(id_asignacion): Observable<any> {
		console.log(this.ObjaJSON_ColaAsigDel(id_asignacion));
		var localURL=this.baseUrl+'/asignacola_del';
		return this._http.post<any>(localURL, this.ObjaJSON_ColaAsigDel(id_asignacion), this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	
	cambiaPW(nuevopassword, claveanterior): Observable<any> {
		var objjson=this.ObjaJSON_cambiaPW(nuevopassword, claveanterior);
		console.log(objjson);
		var localURL=this.baseUrl+'/cambioPW';
		return this._http.post<any>(localURL, objjson, this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	
	saveAsignaColaInicial(cola, queja){
		var JSNOjb=this.ObjaJSON_AsignaColaIn(cola, queja)
		console.log(JSNOjb);
		var localURL=this.baseUrl+'/asignavyv';
		return this._http.post<any>(localURL, JSNOjb, this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	
  ObjaJSON_ParamGenerales(uma, titulo, genero, nombre){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), UMA: uma, titulo: this.TrimandCut(titulo, 15), genero: this.TrimandCut(genero,10), nombre: this.TrimandCut(nombre, 100) };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ConfUsuario(nit, usuario, nombre, dpi, estado, email, puesto, sede, telefono, glob_paramgen, valor_global_aprobarpp, valor_global_catalogos, id_usuario, valor_global_admusuarios, rolAtConCtrl, valor_atcon_admcolas, valor_atcon_presencial, rolJurCtrl,  valor_jur_admcolas, rolVyVCtrl, valor_vyv_admcolas, rolSPCtrl,  valor_sp_admcolas, valor_global_configuracion, resetpassword, valor_atcon_call_center){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), nit: this.TrimandCut(nit, 19), usuario: this.TrimandCut(usuario, 19), nombre: this.TrimandCut(nombre, 49), dpi: this.TrimandCut(dpi, 19), estado: estado, email: this.TrimandCut(email, 29), puesto: puesto, telefono: this.TrimandCut(telefono, 29), glob_paramgen: glob_paramgen, glob_aprobarpp: valor_global_aprobarpp, glob_catalogos: valor_global_catalogos, id_usuario: id_usuario, id_sede: sede, glob_admusuarios: valor_global_admusuarios, atcon_rol: rolAtConCtrl, atcon_confsistema: 0, atcon_admcolas: valor_atcon_admcolas, atcon_presencial: valor_atcon_presencial, atcon_call_center: valor_atcon_call_center, jur_rol: rolJurCtrl, jur_confsistema: 0, jur_admcolas: valor_jur_admcolas, vyv_rol: rolVyVCtrl, vyv_confsistema: 0, vyv_admcolas: valor_vyv_admcolas, sp_rol: rolSPCtrl, sp_confsistema: 0, sp_admcolas: valor_sp_admcolas, glob_confsistema: valor_global_configuracion, resetpassword: resetpassword };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ConfUsuarioPP(nit, usuario, nombre, dpi, estado, email, telefono, id_usuario, rolPPCtrl, proveedor, resetpassword){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), nit: this.TrimandCut(nit, 19), usuario: this.TrimandCut(usuario, 19), nombre: this.TrimandCut(nombre, 49), dpi: this.TrimandCut(dpi, 19), estado: estado, email: this.TrimandCut(email, 59), telefono: this.TrimandCut(telefono, 29), id_usuario: id_usuario, pp_rol: rolPPCtrl, proveedor: proveedor, resetpassword: resetpassword };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_Configuracion(dias_expirar_atcon, dias_alerta_atcon, dias_link_verif_datos, dias_expirar_jur, dias_alerta_jur, dias_expirar_vyv, dias_alerta_vyv, dias_expirar_sp, dias_alerta_sp, dias_expirar_pp, dias_link_int_conc_virt, dias_link_conf_queja_fin, correo_com_perm, correo_verif_datos, correo_audiencias, correo_arch_sp, correo_confrespp){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), dias_expirar_atcon: dias_expirar_atcon, dias_alerta_atcon: dias_alerta_atcon, dias_link_verif_datos: dias_link_verif_datos, dias_expirar_jur: dias_expirar_jur, dias_alerta_jur: dias_alerta_jur, dias_expirar_vyv: dias_expirar_vyv, dias_alerta_vyv: dias_alerta_vyv, dias_expirar_sp: dias_expirar_sp, dias_alerta_sp: dias_alerta_sp, dias_expirar_pp: dias_expirar_pp, dias_link_conc_virt: dias_link_int_conc_virt, dias_conf_queja_fin: dias_link_conf_queja_fin, correos_com_perm: correo_com_perm, correos_verif_datos: correo_verif_datos, correos_audiencias: correo_audiencias, correos_archivar_sp: correo_arch_sp, correos_conf_resp_pp: correo_confrespp };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ColaAsig_EstadoUpdate(estado, id_asignacion){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), estado: estado, id_local: id_asignacion, operacion: 1 };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ColaAsig_Schedule(id_asignacion, fecha){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_local: id_asignacion, operacion: 2, fecha: this.dateFormat(fecha) };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ColaAsig_Del_Schedule(id_asignacion){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_local: id_asignacion, operacion: 3 };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_ColaAsigNew(id_tipo_cola, id_usuario){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_tipo_cola: id_tipo_cola, usuario_asignado :id_usuario };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_ColaAsigDel(id_asignacion){
	  var locarray = { usuario_operacion:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_local: id_asignacion };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_cambiaPW(nuevopassword, claveanterior){
	  var locarray = { usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), resp1: nuevopassword, resp2: claveanterior };
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_AsignaColaIn(cola, queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), valor: cola, id_queja: queja };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }

  dateFormat(date){
	  return this.datePipe.transform(date,"dd/MM/yyyy");
  }
  
}

