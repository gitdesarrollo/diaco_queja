import { Injectable } from '@angular/core';

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
	srv_idproveedor;

  constructor() { 
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
		this.srv_idproveedor=0;
  }
  
  SetInitial(formsubmit){
	  console.log(formsubmit);
	  this.Limpiar();
	this.srv_username = formsubmit['username'];
	this.srv_token = formsubmit['token'];
	this.LoadConVir(formsubmit);
	this.LoadUserid(formsubmit);
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
  
  Get_Conciliacion_Virtual(){
		return this.srv_conciliacion_virtual;
  }
  
  Get_proveedor(){
		return this.srv_idproveedor;
  }
  
  LoadUserid(formsubmit){
	  //this.srv_userid=parseInt(formsubmit['userid'], 10);
	  this.srv_userid=formsubmit['userid'];
	  if(formsubmit['id_proveedor'])
		  this.srv_idproveedor=+formsubmit['id_proveedor'];
	  console.log('userid, proveedor ',this.srv_userid, this.srv_idproveedor);
  }
  
  LoadConVir(formsubmit){
	  /*0-ninguno  1-normal  2-aprovador*/
	let i=0;
	let rol=0;
	for (let thisitem of formsubmit['roles']){
		if(thisitem['id_flujo']==4){
			rol=0;
			if(thisitem['perfil']=='Normal'){
				rol=1;
			}else if(thisitem['perfil']=='Revisor'){
				rol=2;
			}
			if(this.srv_conciliacion_virtual < rol)
				this.srv_conciliacion_virtual=rol;
		}
	}
	console.log('x rol con vir: ',this.srv_conciliacion_virtual);
  }
  
}
