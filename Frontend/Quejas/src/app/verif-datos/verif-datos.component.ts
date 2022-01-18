import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CompermanenteService } from '../comunicacion-permanente/compermanente.service';
import { BASE_VERIFICACION_DATOS_LINK, FormResponse, MSG_REGISTRO_DESHABILITADO } from "../atencion-consumidor-const";
import { RegistrosService } from '../shared/registros.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { VerifDatosService } from '../verif-datos/verif-datos.service';
import { QuejaService } from '../shared/queja.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-verif-datos',
  templateUrl: './verif-datos.component.html',
  styleUrls: ['./verif-datos.component.css'],
  animations: [
		trigger('EnterLeave', [
	  state('flyIn', style({ transform: 'translateX(0)' })),
	  transition(':enter', [
		style({ transform: 'translateX(-100%)' }),
		animate('100ms 150ms ease-in')
	  ]),
	  transition(':leave', [
		animate('300ms ease-out', style({ transform: 'translateX(-100%)' }))
	  ])
	])]
})
export class VerifDatosComponent implements OnInit {
  @ViewChild('replink') replink: ElementRef;
  lst_int;lbl_numqueja;lst_queja;flagformvisible;linkdescription;routerlink;registrodata;linkregistro;
  flagUpdateInfo: boolean;
  flagIntUsuario: boolean;
  flagIntList: boolean;
  flagInfoError: boolean;
  flagEmailSent: boolean;
  flagEmailError: boolean;
  loaderror: boolean;
  flagEditable: boolean;
  flaglink: boolean;
  flagBoton: boolean;
  flagMainUpdate: boolean;
  flagValidadorRegistros: boolean;
  msg_deshabilitado;

  constructor( @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<VerifDatosComponent>, private _compermservice:CompermanenteService, private _verifDatosService:VerifDatosService, private _quejaService:QuejaService,private _registrosservice:RegistrosService, private _submitFormService:SubmitFormService, private _seguridadService:SeguridadService ) { 
		this.flaglink=false;
		this.flagIntUsuario = false;
		this.flagUpdateInfo = false;
		this.flagIntList=false;
		this.flagInfoError=false;
		this.flagEmailSent=false;
		this.flagEmailError=false;
		this.loaderror=false;
		this.flagEditable=false;
		this.flagBoton=true;
		this.flagMainUpdate=false;
		this.flagValidadorRegistros=false;
		this.flagformvisible=0;
		this.linkdescription='Ver Detalles';
		this.msg_deshabilitado=MSG_REGISTRO_DESHABILITADO;
  }

  ngOnInit() {
		this.QuejaCheck();
		this.LoadFrmInfo();
		this.SetSecTimerForm();
		this.GetRegistro(false);
		this.ValidadorRegistros();
  }

  ValidadorRegistros(){
		let tempstr=null;
		this._seguridadService.ValidadorRegistros(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK' || Data['reason'] == 'INVALID'){
				if(Data['reason'] == 'OK'){
					this.flagValidadorRegistros=true;
				}else{
					this.flagValidadorRegistros=false;
				}
				this.flagformvisible++;
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

  AgregarInteraccion(){
		var r = confirm("¿Esta seguro que desea enviar un correo solicitando al consumidor ingresar información adicional?");
		if (r){
				this.flagBoton=false;
				//this.SetSecTimerBoton();
				this._verifDatosService.addDataInt(this.data.NoQueja).subscribe((retvalue)=>{
					console.log(retvalue);
					if(retvalue["reason"] == 'OK' || retvalue["reason"] == 'EMAILFAIL'){
						console.log(retvalue);
						if(retvalue["reason"] == 'OK'){
							this.flagEmailSent=true;
							this.SetSecTimerMailOk();
						}else{
							this.flagEmailError=true;
							this.SetSecTimerMailError();
						}
						this.LoadFrmInfo();
					}else{
						console.log('Rest service response ERROR.');
						this.flagInfoError=true;
						this.SetSecTimerInfoError();
					}
					this.SetSecTimerBoton();	
				},(error)=>{
					console.log(error);
					this.flagInfoError=true;
					this.SetSecTimerBoton();
				});
		}		
		//saveinteraccion
  }

  VericarDatos(){
	    this.FlugoGuia();
		//window.location.href = BASE_VERIFICACION_DATOS_LINK+this._submitFormService.Get_token();
		//window.open( BASE_VERIFICACION_DATOS_LINK+this._submitFormService.Get_token()+'/'+this.data.NoQueja, '_blank', 'height=' + screen.height + ',width=' + screen.width + ',resizable=yes,scrollbars=yes,toolbar=yes,menubar=yes,location=yes');//'width=100%, height=100%' )
		window.open(BASE_VERIFICACION_DATOS_LINK+this._submitFormService.Get_token()+'/'+this.data.NoQueja, '_blank');
  }

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

  DetallesInteraccion(){
	  if(this.flagIntList){
		  this.flagIntList=false;
		  this.linkdescription='Ver Detalles';
	  }else{
		  this.flagIntList=true;
		  this.linkdescription='Ocultar Detalles';
	  }
  }
  	
	QuejaCheck(){
		let tempstr='';
		this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					this.lbl_numqueja=this.lst_queja[0]['quejanumero'];
					//ver si es verificador, en ese caso siempre puede editar
					var rolverificador=this._submitFormService.Get_Atcon_Verificador();
					//this.flaglink=true;
					//verificar estado para permitir edicion en formulario, se agrega validacion para rol verificador de poder editar quejas en estado pendiente de confirmación.
					if( this.lst_queja[0]['id_estado_queja'] == 130 && rolverificador == 1){
						this.flagEditable=true;
					}else{ 
						if( this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']) )
							//si es rol 3 administrador, puede editar
							if(this.data.Rol == 3 || rolverificador == 1)
								this.flagEditable=true;
							else{
								//si es otro rol, revisar si la queja esta asignada a este usuario
								if(this.lst_queja[0]['usuario_asignado'] == this.data.Usuario)
									this.flagEditable=true;
								else
									this.flagEditable=false;
							}
						else
							this.flagEditable=false;
					}
				}else{
					console.log('Información de queja no pudo ser obtenida.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}				
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});	
	}

	LoadFrmInfo(){
		let tempstr=null;
		this._verifDatosService.getInteracciones(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null && tempstr!='')	{
					this.lst_int=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagIntUsuario=true;
				}else{
					this.lst_int=null;
					this.flagIntUsuario=false;
				}
				this.flagInfoError=false;
				console.log('*'+tempstr+'*');
				console.log(this.lst_int);
				this.flagformvisible++;
				this.SetSecTimerVarLoad();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}
	
	FlugoGuia(){
		let tempstr=null;
		this._verifDatosService.updFlujoGuia(this.data.NoQueja, this.data.Flujo).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				this.flagMainUpdate=true;
				console.log('Flujo actualizado');
			}else{
				console.log('Rest service response ERROR Flujo.');
			}
		},(error)=>{
			console.log(error);
		});
	}
	
	openFormularioQuejaRegistro(){
		console.log('entro a getFormularioQuejaRegistro');
	 	this._registrosservice.getFormularioQuejaRegistro(this.data.NoQueja).subscribe((Data)=>{
		console.log('entro a openRegisto');
			this._registrosservice.FileDownLoadChoose(Data,1);
				this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			//this.SetSecTimerInfoError();
		});
	} 

	GenerarRegistro(){
		var approved=false;
		if(this.linkdescription != ''){
			if(confirm("¿Está seguro que quiere actualizar este registro con la información mas reciente?")) {
				approved=true;
			}
		}else{
			approved=true;
		}
		if(approved){
			this._registrosservice.add_FormQueja(this.data.NoQueja).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					//this.SetSecTimerUpdateList();
					this.flagInfoError=false;
					this.GetRegistro(true);
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}				
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			});  
		}
	}
	
	  GetRegistro(click:boolean){
	  this._registrosservice.read_FormQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					//console.log(retvalue);
					this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
					this.routerlink=this.registrodata[0]['id'];
					this.linkregistro=this.registrodata[0]['codigo'];
					console.log(this.registrodata);
					if(click)
						this.ClickAfter3sec();
				}else{
					this.routerlink='';
					this.linkregistro='';
				}
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }

	closeDialog(){
		this.ngOnDestroy();
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerMailOk(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagEmailSent=false);
	}
	SetSecTimerMailError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagEmailError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		console.log(this.flagformvisible);
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	SetSecTimerVarLoad(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flaglink=true);
	}
	SetSecTimerBoton(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flagBoton=true);
	}
	ClickAfter3sec(){
		const source = timer(1);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
	}
}
