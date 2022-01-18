import { Component, OnInit, OnDestroy, ViewChild,ElementRef } from '@angular/core';
import { UsuarioService } from '../shared/usuario.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { SubmitFormService } from "../shared/submit-form.service";
import { FrmConfUsuarioBusqueda, FormResponse, BaseCmbClass, FrmSede } from "../atencion-consumidor-const";
import { ConfiguracionService } from '../shared/configuracion.service';
import { CatalogoService } from '../shared/catalogo.service';
import { SedeService } from '../shared/sede.service';

@Component({
  selector: 'app-configuracion-sistema',
  templateUrl: './configuracion-sistema.component.html',
  styleUrls: ['./configuracion-sistema.component.css'],
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
export class ConfiguracionSistemaComponent implements OnInit {
	flagInfoError:boolean;
	loaderror:boolean;
	tabledata:boolean;
	flagBoton:boolean;
	flagSaved:boolean;
	btnvisible:boolean;
	flagChange:boolean;
	flagMainUpdate:boolean;
	flagErrorLogin:boolean;
	flagErrorEmail:boolean;
	cmbpuesto:BaseCmbClass[];
	cmbsede:FrmSede[];
	myForm: FormGroup;
	atcon_expirardiasCtrl: FormControl;
	atcon_alertadiasCtrl: FormControl;
	atcon_verifdatosCtrl: FormControl;
	jur_expirardiasCtrl: FormControl;
	jur_alertadiasCtrl: FormControl;
	vyv_expirardiasCtrl: FormControl;
	vyv_alertadiasCtrl: FormControl;
	sp_expirardiasCtrl: FormControl;
	sp_alertadiasCtrl: FormControl;
	pp_expirardiasCtrl: FormControl;
	pp_concvirtintCtrl: FormControl;
	pp_concvirtconfCtrl: FormControl;
	ca_compermCtrl: FormControl;
	ca_verifdatosCtrl: FormControl;
	ca_audCtrl: FormControl;
	ca_archspCtrl: FormControl;
	ca_confresppCtrl: FormControl;
	flagformvisible; list_conf; ListUsuariosCount;
	idUsuario;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ConfiguracionSistemaComponent>, private _usuarioService: UsuarioService, private _submitFormService: SubmitFormService, private _catalogoService: CatalogoService, private _sedeservice:SedeService, private _configuracionService:ConfiguracionService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.btnvisible=true;
	this.flagChange=false;
	this.flagMainUpdate=false;
	this.flagSaved=false;
	this.flagErrorLogin=false;
	this.flagErrorEmail=false;
	this.idUsuario=0;
	this.flagformvisible=0;
	this.ListUsuariosCount=-1;
  }

  ngOnInit() {
	atcon_expirardiasCtrl: FormControl;
	atcon_alertadiasCtrl: FormControl;
	atcon_verifdatosCtrl: FormControl;
	jur_expirardiasCtrl: FormControl;
	jur_alertadiasCtrl: FormControl;
	vyv_expirardiasCtrl: FormControl;
	vyv_alertadiasCtrl: FormControl;
	sp_expirardiasCtrl: FormControl;
	sp_alertadiasCtrl: FormControl;
	pp_expirardiasCtrl: FormControl;
	pp_concvirtintCtrl: FormControl;
	pp_concvirtconfCtrl: FormControl;
	ca_compermCtrl: FormControl;
	ca_verifdatosCtrl: FormControl;
	ca_audCtrl: FormControl;
	ca_archspCtrl: FormControl;
	ca_confresppCtrl: FormControl;

	  this.atcon_expirardiasCtrl =  new FormControl('',Validators.required);
	  this.atcon_alertadiasCtrl =  new FormControl('',Validators.required);
	  this.atcon_verifdatosCtrl =  new FormControl('',Validators.required);
	  this.jur_expirardiasCtrl =  new FormControl('',Validators.required);
	  this.jur_alertadiasCtrl =  new FormControl('',Validators.required);
	  this.vyv_expirardiasCtrl =  new FormControl('',Validators.required);
	  this.vyv_alertadiasCtrl =  new FormControl('',Validators.required);
	  this.sp_expirardiasCtrl =  new FormControl('',Validators.required);
	  this.sp_alertadiasCtrl =  new FormControl('',Validators.required);
	  this.pp_expirardiasCtrl =  new FormControl('',Validators.required);
	  this.pp_concvirtintCtrl =  new FormControl('',Validators.required);
	  this.pp_concvirtconfCtrl =  new FormControl('',Validators.required);
	  this.ca_compermCtrl =  new FormControl('',Validators.required);
	  this.ca_verifdatosCtrl =  new FormControl('',Validators.required);
	  this.ca_audCtrl =  new FormControl('',Validators.required);
	  this.ca_archspCtrl =  new FormControl('',Validators.required);
	  this.ca_confresppCtrl =  new FormControl('',Validators.required);
	  
	  this.myForm = new FormGroup({
		atcon_expirardias: this.atcon_expirardiasCtrl,
		atcon_alertadias: this.atcon_alertadiasCtrl,
		atcon_verifdatos: this.atcon_verifdatosCtrl,
		jur_expirardias: this.jur_expirardiasCtrl,
		jur_alertadias: this.jur_alertadiasCtrl,
		vyv_expirardias: this.vyv_expirardiasCtrl,
		vyv_alertadias: this.vyv_alertadiasCtrl,
		sp_expirardias: this.sp_expirardiasCtrl,
		sp_alertadias: this.sp_alertadiasCtrl,
		pp_expirardias: this.pp_expirardiasCtrl,
		pp_concvirtint: this.pp_concvirtintCtrl,
		pp_concvirtconf: this.pp_concvirtconfCtrl,
		ca_comperm: this.ca_compermCtrl,
		ca_verifdatos: this.ca_verifdatosCtrl,
		ca_aud: this.ca_audCtrl,
		ca_archsp: this.ca_archspCtrl,	
		ca_confrespp: this.ca_confresppCtrl,		
	  });
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.GetList();
  }

  test(){
		console.log(this.myForm);
  }

  ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = '';
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

  FormUpdate(){
	  this.flagChange=true;
  }

  BackDialog(){
		this.flagMainUpdate=true;
		this.ngOnDestroy();
  }	  

 onSubmit(){}

  GetList(){
		this._configuracionService.getConfGeneral().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				var tempstr=retvalue['value'];
				console.log(this.list_conf);
				if(tempstr != null && tempstr != '')	{
					this.list_conf=JSON.parse('['+retvalue["value"] +']');
					this.LoadInfo(this.list_conf);
					console.log(this.list_conf);
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
  
	LoadInfo(FrmLocal){
		this.atcon_expirardiasCtrl.setValue(FrmLocal[0]['expira_atcon']);
		this.atcon_alertadiasCtrl.setValue(FrmLocal[0]['alerta_atcon']);
		this.atcon_verifdatosCtrl.setValue(FrmLocal[0]['dias_verif_datos']);
		this.jur_expirardiasCtrl.setValue(FrmLocal[0]['expira_jur']);
		this.jur_alertadiasCtrl.setValue(FrmLocal[0]['alerta_jur']);
		this.vyv_expirardiasCtrl.setValue(FrmLocal[0]['expira_vyv']);
		this.vyv_alertadiasCtrl.setValue(FrmLocal[0]['alerta_vyv']);
		this.sp_expirardiasCtrl.setValue(FrmLocal[0]['expira_sp']);
		this.sp_alertadiasCtrl.setValue(FrmLocal[0]['alerta_sp']);
		this.pp_expirardiasCtrl.setValue(FrmLocal[0]['expira_portal_proveedores']);
		this.pp_concvirtintCtrl.setValue(FrmLocal[0]['dias_conc_virt_int']);
		this.pp_concvirtconfCtrl.setValue(FrmLocal[0]['dias_conf_queja_fin']);
		this.ca_compermCtrl.setValue(FrmLocal[0]['activo_com_perm']);
		this.ca_verifdatosCtrl.setValue(FrmLocal[0]['activo_verif_datos']);
		this.ca_audCtrl.setValue(FrmLocal[0]['activo_notif_audiencia']);
		this.ca_archspCtrl.setValue(FrmLocal[0]['activo_notif_queja_sp']);
		this.ca_confresppCtrl.setValue(FrmLocal[0]['activo_conf_resp_pp']);
	}

	BtnVisibleAfterxsec(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.btnvisible=true);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	SetTimerSaved(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagSaved=false );
	}
	SetTimerErrorLogin(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.flagErrorLogin=false );
	}
	SetTimerErrorEmail(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.flagErrorEmail=false );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

	Guardar(){
		this.btnvisible=false;
		this._configuracionService.saveConfiguracion(this.atcon_expirardiasCtrl.value, this.atcon_alertadiasCtrl.value, this.atcon_verifdatosCtrl.value, this.jur_expirardiasCtrl.value, this.jur_alertadiasCtrl.value, this.vyv_expirardiasCtrl.value, this.vyv_alertadiasCtrl.value, this.sp_expirardiasCtrl.value, this.sp_alertadiasCtrl.value, this.pp_expirardiasCtrl.value, this.pp_concvirtintCtrl.value, this.pp_concvirtconfCtrl.value, this.ca_compermCtrl.value, this.ca_verifdatosCtrl.value, this.ca_audCtrl.value, this.ca_archspCtrl.value, this.ca_confresppCtrl.value ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagChange=false;
				this.flagSaved=true;	
				this.SetTimerSaved();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.BtnVisibleAfterxsec();		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
			this.BtnVisibleAfterxsec();
		});  
	}

}
