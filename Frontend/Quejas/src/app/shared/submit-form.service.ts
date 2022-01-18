import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { FrmConfUsuarioBusqueda } from "../atencion-consumidor-const";

@Injectable({
  providedIn: 'root'
})
export class SubmitFormService {
	srv_userid;
	srv_username;
	srv_token;
	srv_atencion_consumidor;
	srv_verificacion_vigilancia;
	srv_juridico;
	srv_conciliacion_virtual;
	srv_servicios_publicos;
	conf_usuario_bsq_flujo; conf_usuario_bsq_usuario; conf_usuario_bsq_nombre;
	conf_usuario_pp_proveedor; conf_usuario_pp_usuario; conf_usuario_pp_nombre;
	aconf; aconf_paramgen; aconf_usuarios; aconf_sistema; aconf_colas; aconf_aprobacionpp; aconf_catalogos; aconf_presencial; aconf_callcenter;
	atcon_verificador;

  constructor(private router: Router) { 
		this.Limpiar();
  }
  
  Limpiar(){
		this.srv_username='';
		this.srv_token='*';
		this.srv_userid=0;
		this.srv_atencion_consumidor=0;
		this.srv_verificacion_vigilancia=0;
		this.srv_juridico=0;
		this.srv_conciliacion_virtual=0;	 
		this.srv_servicios_publicos=0;
		this.conf_usuario_bsq_flujo=0;
		this.conf_usuario_bsq_usuario='';
		this.conf_usuario_bsq_nombre='';
		this.conf_usuario_pp_proveedor=0;
		this.conf_usuario_pp_usuario='';
		this.conf_usuario_pp_nombre='';
		this.aconf=0;
		this.aconf_paramgen=0;
		this.aconf_usuarios=0;
		this.aconf_sistema=0;
		this.aconf_colas=0;
		this.aconf_aprobacionpp=0;
		this.aconf_catalogos=0;
		this.aconf_presencial=0;
		this.aconf_callcenter=0;
		this.atcon_verificador=0;
  }
  
  SetInitial(formsubmit){
		console.log(formsubmit);
		this.Limpiar();
		this.srv_username = formsubmit['username'];
		this.srv_token = formsubmit['token'];
		this.LoadAtCon(formsubmit);
		this.LoadServPub(formsubmit);
		this.LoadVyV(formsubmit);
		this.LoadJuridico(formsubmit);
		this.LoadUserid(formsubmit);
		this.LoadConf(formsubmit);
		//redirect
		if(this.srv_atencion_consumidor !=0){
			this.router.navigate(['/mainAtCon']);
			return true;
		}
		else if(this.srv_servicios_publicos !=0){
			this.router.navigate(['/mainServPub']);
			return true;
		}
		else if(this.srv_verificacion_vigilancia !=0){
			this.router.navigate(['/mainVyV']);
			return true;
		}else if(this.srv_juridico !=0){
			this.router.navigate(['/mainJuridico']);
			return true;
		}else if(this.aconf != 0 || this.aconf_catalogos != 0 || this.aconf_presencial != 0 || this.aconf_callcenter != 0){
			this.router.navigate(['/mainAtCon']);
			return true;
		}else{
			this.Limpiar();
			return false;
		}
  }
  
  Set_ConfUsuarioBusqueda(flujo, usuario, nombre){
	  this.conf_usuario_bsq_flujo = flujo;
	  this.conf_usuario_bsq_usuario = usuario;
	  this.conf_usuario_bsq_nombre = nombre;
  }
  
  Get_ConfUsuarioBusqueda(){
	  let frmdata:FrmConfUsuarioBusqueda= new FrmConfUsuarioBusqueda();
	  frmdata.flujo=this.conf_usuario_bsq_flujo;
	  frmdata.usuario=this.conf_usuario_bsq_usuario;
	  frmdata.nombre=this.conf_usuario_bsq_nombre;
	  return frmdata;
  }
  Set_ConfUsuarioBusquedaPP(proveedor, usuario, nombre){
	  this.conf_usuario_pp_proveedor = proveedor;
	  this.conf_usuario_pp_usuario = usuario;
	  this.conf_usuario_pp_nombre = nombre;
  }
  Get_ConfUsuarioBusquedaPP(){
	  let frmdata:FrmConfUsuarioBusqueda= new FrmConfUsuarioBusqueda();
	  frmdata.proveedor=this.conf_usuario_pp_proveedor;
	  frmdata.usuario=this.conf_usuario_pp_usuario;
	  frmdata.nombre=this.conf_usuario_pp_nombre;
	  return frmdata;
  }
  
  Get_userid(){
		return this.srv_userid;
  }
  
  Get_username(){
		return this.srv_username;
  }

  Get_token(){
		return this.srv_token;
  }
  
  Get_Atencion_Consumidor(){
		return this.srv_atencion_consumidor;
  }
  
  Get_Servicios_Publicos(){
		return this.srv_servicios_publicos;
  }
  
  Get_Verificacion_Vigilancia(){
		return this.srv_verificacion_vigilancia;
  }
  
  Get_Juridico(){
		return this.srv_juridico;
  }
  
  Get_Conf_ParametrosGenerales(){
		return this.aconf_paramgen;
  }
  Get_Conf_ConfUsuarios(){
		return this.aconf_usuarios;
  }
  Get_Conf_Sistema(){
		return this.aconf_sistema;
  }
  Get_Conf_Colas(){
		return this.aconf_colas;
  }
  Get_Conf_AprobacionPP(){
		return this.aconf_aprobacionpp;
  }
  Get_Conf_Catalogos(){
	  
		return this.aconf_catalogos;
  }
  Get_Conf_Presencial(){
	console.log('Rol: ', this.aconf_callcenter)
		return this.aconf_presencial;
  }

  Get_Conf_CallCenter(){
		return this.aconf_callcenter;
  }
  Get_Atcon_Verificador(){
		return this.atcon_verificador;
  }
  
  LoadUserid(formsubmit){
	  this.srv_userid=parseInt(formsubmit['userid'], 10);
	  console.log('userid: ',this.srv_userid);
  }
  
  LoadAtCon(formsubmit){
	  /*0-ninguno  1-normal  2-revisor  3-administrador*/
	let str_queja_list='';
	let i=0;
	let rol=0;
	for (let thisitem of formsubmit['roles']){
		if(thisitem['id_flujo']==1){
			rol=0;
			if(thisitem['perfil']=='Normal'){
				rol=1;
			}else if(thisitem['perfil']=='Revisor'){
				rol=2;
			}else if(thisitem['perfil']=='Administrador'){
				rol=3;
			}else if(thisitem['perfil']=='Verificador'){
				rol=2;
				this.atcon_verificador=1;
			}
			if(this.srv_atencion_consumidor < rol)
				this.srv_atencion_consumidor=rol;
		}
	}
	console.log('rol at con: ',this.srv_atencion_consumidor);
  }
  
  LoadServPub(formsubmit){
	  /*0-ninguno  1-normal  2-revisor  3-administrador*/
	let str_queja_list='';
	let i=0;
	let rol=0;
	for (let thisitem of formsubmit['roles']){
		if(thisitem['id_flujo']==6){
			rol=0;
			if(thisitem['perfil']=='Normal'){
				rol=1;
			}else if(thisitem['perfil']=='Revisor'){
				rol=2;
			}else if(thisitem['perfil']=='Administrador'){
				rol=3;
			}
			if(this.srv_servicios_publicos < rol)
				this.srv_servicios_publicos=rol;
		}
	}
	console.log('rol serv pub: ',this.srv_servicios_publicos);
  }
  
  LoadVyV(formsubmit){
	  /*0-ninguno  1-normal  2-revisor  3-administrador*/
	let str_queja_list='';
	let i=0;
	let rol=0;
	for (let thisitem of formsubmit['roles']){
		if(thisitem['id_flujo']==3){
			rol=0;
			if(thisitem['perfil']=='Normal'){
				rol=1;
			}else if(thisitem['perfil']=='Revisor'){
				rol=2;
			}else if(thisitem['perfil']=='Administrador'){
				rol=3;
			}
			if(this.srv_verificacion_vigilancia < rol)
				this.srv_verificacion_vigilancia=rol;
		}
	}
	console.log('rol v y v: ',this.srv_verificacion_vigilancia);
  }
  
  LoadJuridico(formsubmit){
	  /*0-ninguno  1-normal  2-revisor  3-administrador*/
	let str_queja_list='';
	let i=0;
	let rol=0;
	for (let thisitem of formsubmit['roles']){
		if(thisitem['id_flujo']==2){
			rol=0;
			if(thisitem['perfil']=='Normal'){
				rol=1;
			}else if(thisitem['perfil']=='Revisor'){
				rol=2;
			}else if(thisitem['perfil']=='Administrador'){
				rol=3;
			}
			if(this.srv_juridico < rol)
				this.srv_juridico=rol;
		}
	}
	console.log('roljuridico: ',this.srv_juridico);
  }
  LoadConf(formsubmit){
	  /*0-ninguno  1-normal  2-revisor  3-administrador*/
	let str_queja_list='';
	let i=0;
	let rol=0;
	let cola_atcon=0;
	let cola_jur=0;
	let cola_vyv=0;
	let cola_sp=0;
	for (let thisitem of formsubmit['roles']){
		switch(thisitem['perfil']){
			case 'Adm_Usuarios':
				this.aconf_usuarios=1;
				break;
			case 'Parametros_Generales':
				this.aconf_paramgen=1;
				break;
			case 'Configuracion':
				this.aconf_sistema=1;
				break;
			case 'Aprobacion_PP':
				this.aconf_aprobacionpp=1;
				break;
			case 'Catalogos':
				this.aconf_catalogos=1;
				break;
			case 'Adm_Colas_atcon':
				cola_atcon=1;
				break;
			case 'Adm_Colas_jur':
				cola_jur=1;
				break;
			case 'Adm_Colas_vyv':
				cola_vyv=1;
				break;
			case 'Adm_Colas_sp':
				cola_sp=1;
				break;	
			case 'Presencial':
				this.aconf_presencial=1;
				break;
			case 'CallCenter':
				this.aconf_callcenter=1;
				break;
		}
		if(cola_atcon==1 || cola_jur==1 || cola_vyv==1 || cola_sp==1){
			this.aconf_colas=1;
		}else{
			this.aconf_colas=0;
		}
	}
	console.log('Catalogos: ',this.aconf_catalogos);
  }
}
