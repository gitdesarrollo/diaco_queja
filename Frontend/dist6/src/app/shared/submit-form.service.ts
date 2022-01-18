import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

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
	srv_proveedor;

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
		this.srv_proveedor=0;
  }
  
  SetInitial(formsubmit){
		console.log(formsubmit);
		this.Limpiar();
		this.srv_username = formsubmit['username'];
		this.srv_token = formsubmit['token'];
		this.srv_proveedor = formsubmit['id_proveedor'];
		this.LoadAtCon(formsubmit);
		this.LoadServPub(formsubmit);
		this.LoadVyV(formsubmit);
		this.LoadUserid(formsubmit);
		//redirect
		if (this.srv_proveedor != 0) {
			this.router.navigate(['/consumidor']);
		   return true;
		}
		/*
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
			  
		}*/
		else{
			  this.Limpiar();
			  return false;
		}
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
  
}
