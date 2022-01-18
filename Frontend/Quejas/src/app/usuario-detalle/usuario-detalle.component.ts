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
  selector: 'app-usuario-detalle',
  templateUrl: './usuario-detalle.component.html',
  styleUrls: ['./usuario-detalle.component.css'],
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
export class UsuarioDetalleComponent implements OnInit {
	@ViewChild('chbox_global_paramgen') EL_global_paramgen:ElementRef;
	@ViewChild('chkbox_global_admusuarios') EL_global_admusuarios:ElementRef;
	@ViewChild('chkbox_global_aprobarpp') EL_global_aprobarpp:ElementRef;
	@ViewChild('chkbox_global_catalogos') EL_global_catalogos:ElementRef;
	//@ViewChild('chkbox_atcon_confsist') EL_atcon_confsist:ElementRef;
	@ViewChild('chkbox_atcon_admcolas') EL_atcon_admcolas:ElementRef;
	@ViewChild('chkbox_atcon_presencial') EL_atcon_presencial:ElementRef;
  @ViewChild('chkbox_atencion_call') El_atencion_call:ElementRef;
	// @ViewChild('chkbox_atcon_call_center') EL_atcon_call_center:ElementRef;
	//@ViewChild('chkbox_jur_confsist') EL_jur_confsist:ElementRef;
	@ViewChild('chkbox_jur_admcolas') EL_jur_admcolas:ElementRef;
	//@ViewChild('chkbox_vyv_confsist') EL_vyv_confsist:ElementRef;
	@ViewChild('chkbox_vyv_admcolas') EL_vyv_admcolas:ElementRef;
	//@ViewChild('chkbox_sp_confsist') EL_sp_confsist:ElementRef;
	@ViewChild('chkbox_sp_admcolas') EL_sp_admcolas:ElementRef;
	@ViewChild('chkbox_global_confsist') EL_global_confsist:ElementRef;
	@ViewChild('chkbox_resetpassword') EL_resetpassword:ElementRef;
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
	flagNewUserSaved:boolean;
	cmbpuesto:BaseCmbClass[];
	cmbsede:FrmSede[];
	myForm: FormGroup;
	nitCtrl: FormControl;
	usuarioCtrl: FormControl;
	nombreCtrl: FormControl;
	dpiCtrl: FormControl;
	estadoCtrl: FormControl;
	emailCtrl: FormControl;
	sedeCtrl: FormControl;
	puestoCtrl: FormControl;
	telefonoCtrl: FormControl;
	rolAtConCtrl: FormControl;
	rolJurCtrl: FormControl;
	rolVyVCtrl: FormControl;
	rolSPCtrl: FormControl;
	flagformvisible; list_conf; ListUsuariosCount;
	idUsuario;lbl_newuser;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<UsuarioDetalleComponent>, private _usuarioService: UsuarioService, private _submitFormService: SubmitFormService, private _catalogoService: CatalogoService, private _sedeservice:SedeService, private _configuracionService:ConfiguracionService) {
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
	this.flagNewUserSaved=false;
	this.idUsuario=0;
	this.flagformvisible=0;
	this.ListUsuariosCount=-1;
  }

  ngOnInit() {
	  this.nitCtrl =  new FormControl('');
	  this.usuarioCtrl =  new FormControl('',Validators.required);
	  this.nombreCtrl =  new FormControl('',Validators.required);
	  this.dpiCtrl =  new FormControl('');
	  this.estadoCtrl =  new FormControl('',Validators.required);
	  this.emailCtrl =  new FormControl('',Validators.required);
	  this.sedeCtrl =  new FormControl('',Validators.required);
	  this.puestoCtrl =  new FormControl('',Validators.required);
	  this.telefonoCtrl =  new FormControl('',Validators.required);
	  this.rolAtConCtrl =  new FormControl('',Validators.required);
	  this.rolJurCtrl =  new FormControl('',Validators.required);
	  this.rolVyVCtrl =  new FormControl('',Validators.required);
	  this.rolSPCtrl =  new FormControl('',Validators.required);

	  this.myForm = new FormGroup({
		nit: this.nitCtrl,
		usuario: this.usuarioCtrl,
		nombre: this.nombreCtrl,
		dpi: this.dpiCtrl,
		estado: this.estadoCtrl,
		email: this.emailCtrl,
		sede: this.sedeCtrl,
		puesto: this.puestoCtrl,
		telefono: this.telefonoCtrl,
		rolAtCon: this.rolAtConCtrl,
		rolJur: this.rolJurCtrl,
		rolVyV: this.rolVyVCtrl,
		rolSP: this.rolSPCtrl,
	  });
	  console.log('Data Response2:',this.data);
	  this.SetSecTimerForm();
	  this.LoadCmbPuestos();
	  this.LoadDefaults();
	  this.LoadCmbSede();
	  this.GetList();
	  //this.generoCtrl.setValue('Masculino');
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

  LoadCmbSede(){
		let tempstr='';
		this._sedeservice.getAll().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.cmbsede=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.sedeCtrl.setValue(this.cmbsede[0].id_diaco_sede);
					this.flagformvisible++;
				}else{
					this.cmbsede=[];
				}
				console.log(this.cmbsede);
				this.flagInfoError=false;
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

  LoadDefaults(){
	  this.puestoCtrl.setValue('5');
	  this.estadoCtrl.setValue('1');
	  this.rolAtConCtrl.setValue('0');
	  this.rolJurCtrl.setValue('0');
	  this.rolVyVCtrl.setValue('0');
	  this.rolSPCtrl.setValue('0');
  }

  FormUpdate(){
	  if(this.data.IDUsuario != 0){
		  this.flagChange=true;
	  }
  }

  BackDialog(){
		this.flagMainUpdate=true;
		this.ngOnDestroy();
  }

 onSubmit(){}

	LoadCmbPuestos(){
		let tempstr='';
		this._catalogoService.getPuestos().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmbpuesto=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
				}else{
					this.cmbpuesto=[];
				}
				this.flagInfoError=false;
				console.log(tempstr);
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

  GetList(){
		if(this.data.IDUsuario != 0){
			this._usuarioService.getUsuarioConf(this.data.IDUsuario).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					// console.log(retvalue);
					var tempstr=retvalue['value'];
					// console.log(this.list_conf);
					if(tempstr != null && tempstr != '')	{
						//this.list_conf=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.list_conf=JSON.parse('['+retvalue["value"] +']');
						this.LoadInfo(this.list_conf);
						console.log('Get Lista Data3',this.list_conf);
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
  }

	LoadInfo(FrmLocal){
		this.nitCtrl.setValue(FrmLocal[0]['nit']);
		this.usuarioCtrl.setValue(FrmLocal[0]['login']);
		this.nombreCtrl.setValue(FrmLocal[0]['nombre']);
		this.dpiCtrl.setValue(FrmLocal[0]['dpi']);
		this.estadoCtrl.setValue(FrmLocal[0]['habilitado']);
		this.emailCtrl.setValue(FrmLocal[0]['email']);
		this.sedeCtrl.setValue(FrmLocal[0]['id_sede_diaco']);
		this.puestoCtrl.setValue(FrmLocal[0]['id_puesto']);
		this.telefonoCtrl.setValue(FrmLocal[0]['telefono']);
		this.rolAtConCtrl.setValue(FrmLocal[0]['atcon_rol']);
		this.rolJurCtrl.setValue(FrmLocal[0]['jur_rol']);
		this.rolVyVCtrl.setValue(FrmLocal[0]['vyv_rol']);
		this.rolSPCtrl.setValue(FrmLocal[0]['sp_rol']);
		if(FrmLocal[0]['general_Parametros']==1)
			this.SetSecTimerChk_global_paramgen();
		if(FrmLocal[0]['general_AdmUsuarios']==1)
			this.SetSecTimerChk_global_admusuarios();
		if(FrmLocal[0]['general_aprobarpp']==1)
			this.SetSecTimerChk_global_aprobarpp();
		if(FrmLocal[0]['general_catalogos']==1)
			this.SetSecTimerChk_global_catalogos();
		//if(FrmLocal[0]['atcon_conf']==1)
		//	this.SetSecTimerChk_atcon_confsist();
		if(FrmLocal[0]['atcon_colas']==1)
			this.SetSecTimerChk_atcon_admcolas();
		if(FrmLocal[0]['atcon_presencial']==1)
			this.SetSecTimerChk_atcon_presencial();
		if(FrmLocal[0]['atencion_call']==1)
			this.SetSecTimerChk_atencion_call();
		//if(FrmLocal[0]['jur_conf']==1)
		//	this.SetSecTimerChk_jur_confsist();
		if(FrmLocal[0]['jur_colas']==1)
			this.SetSecTimerChk_jur_admcolas();
		//if(FrmLocal[0]['vyv_conf']==1)
		//	this.SetSecTimerChk_vyv_confsist();
		if(FrmLocal[0]['vyv_colas']==1)
			this.SetSecTimerChk_vyv_admcolas();
		//if(FrmLocal[0]['sp_conf']==1)
		//	this.SetSecTimerChk_sp_confsist();
		if(FrmLocal[0]['sp_colas']==1)
			this.SetSecTimerChk_sp_admcolas();
		if(FrmLocal[0]['general_Configuracion']==1)
			this.SetSecTimerChk_global_confsist();

	}

	SetSecTimerChk_global_paramgen(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_global_paramgen.nativeElement.checked=true);
	}
	SetSecTimerChk_global_admusuarios(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_global_admusuarios.nativeElement.checked=true);
	}
	SetSecTimerChk_global_confsist(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_global_confsist.nativeElement.checked=true);
	}
	SetSecTimerChk_global_aprobarpp(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_global_aprobarpp.nativeElement.checked=true);
	}
	SetSecTimerChk_global_catalogos(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_global_catalogos.nativeElement.checked=true);
	}
	/*SetSecTimerChk_atcon_confsist(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_atcon_confsist.nativeElement.checked=true);
	}*/
	SetSecTimerChk_atcon_admcolas(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_atcon_admcolas.nativeElement.checked=true);
	}
	SetSecTimerChk_atcon_presencial(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_atcon_presencial.nativeElement.checked=true);
	}

	SetSecTimerChk_atencion_call(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.El_atencion_call.nativeElement.checked=true);
	}
	/*SetSecTimerChk_jur_confsist(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_jur_confsist.nativeElement.checked=true);
	}*/
	SetSecTimerChk_jur_admcolas(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_jur_admcolas.nativeElement.checked=true);
	}
	/*SetSecTimerChk_vyv_confsist(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_vyv_confsist.nativeElement.checked=true);
	}*/
	SetSecTimerChk_vyv_admcolas(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_vyv_admcolas.nativeElement.checked=true);
	}
	/*SetSecTimerChk_sp_confsist(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_sp_confsist.nativeElement.checked=true);
	}*/
	SetSecTimerChk_sp_admcolas(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.EL_sp_admcolas.nativeElement.checked=true);
	}
	SetTimerFlagChange(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.flagSaved=true);
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
	SetTimerNewUser(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.flagNewUserSaved=false );
	}
	TimerForm(){
		if(this.flagformvisible<2){
			this.loaderror=true;
			this.flagformvisible=-1;
		}
	}

	txtTrim(pos){
		switch(pos){
			case 1:
				let varnit=this.nitCtrl.value;
				this.nitCtrl.setValue(varnit.substring(0, 19));
				break;
			case 2:
				let varusuario=this.usuarioCtrl.value;
				this.usuarioCtrl.setValue(varusuario.substring(0, 19));
				break;
			case 3:
				let varnombre=this.nombreCtrl.value;
				this.nombreCtrl.setValue(varnombre.substring(0, 49));
				break;
			case 4:
				let vardpi=this.dpiCtrl.value;
				this.dpiCtrl.setValue(vardpi.substring(0, 19));
				break;
			case 5:
				let varemail=this.emailCtrl.value;
				this.emailCtrl.setValue(varemail.substring(0, 29));
				break;
			case 7:
				let vartelefono=this.telefonoCtrl.value;
				this.telefonoCtrl.setValue(vartelefono.substring(0, 29));
				break;
		}
		this.FormUpdate();
	}

	closeDialog() {
		this.dialogRef.close();
	}

	GuardarUsuario(){
		this.btnvisible=false;
		let valor_global_paramgen;
		if(this.EL_global_paramgen.nativeElement.checked){
			valor_global_paramgen=1;
		}else{
			valor_global_paramgen=0;
		}
		let valor_global_admusuarios;
		if(this.EL_global_admusuarios.nativeElement.checked){
			valor_global_admusuarios=1;
		}else{
			valor_global_admusuarios=0;
		}
		let valor_global_confsist;
		if(this.EL_global_confsist.nativeElement.checked){
			valor_global_confsist=1;
		}else{
			valor_global_confsist=0;
		}
		let valor_global_aprobarpp;
		if(this.EL_global_aprobarpp.nativeElement.checked){
			valor_global_aprobarpp=1;
		}else{
			valor_global_aprobarpp=0;
		}
		let valor_global_catalogos;
		if(this.EL_global_catalogos.nativeElement.checked){
			valor_global_catalogos=1;
		}else{
			valor_global_catalogos=0;
		}
		/*let valor_atcon_confsist;
		if(this.EL_atcon_confsist.nativeElement.checked){
			valor_atcon_confsist=1;
		}else{
			valor_atcon_confsist=0;
		}*/
		let valor_atcon_admcolas;
		if(this.EL_atcon_admcolas.nativeElement.checked){
			valor_atcon_admcolas=1;
		}else{
			valor_atcon_admcolas=0;
		}
		let valor_atcon_presencial;
		if(this.EL_atcon_presencial.nativeElement.checked){

			valor_atcon_presencial=1;
		}else{
			valor_atcon_presencial=0;
		}

		// queja call center

		let valor_atcon_call_center;
		if(this.El_atencion_call.nativeElement.checked){
			valor_atcon_call_center=1;
		}else{
			valor_atcon_call_center=0;
		}
		/********************** */

		/*let valor_jur_confsist;
		if(this.EL_jur_confsist.nativeElement.checked){
			valor_jur_confsist=1;
		}else{
			valor_jur_confsist=0;
		}*/
		let valor_jur_admcolas;
		if(this.EL_jur_admcolas.nativeElement.checked){
			valor_jur_admcolas=1;
		}else{
			valor_jur_admcolas=0;
		}
		/*let valor_vyv_confsist;
		if(this.EL_vyv_confsist.nativeElement.checked){
			valor_vyv_confsist=1;
		}else{
			valor_vyv_confsist=0;
		}*/
		let valor_vyv_admcolas;
		if(this.EL_vyv_admcolas.nativeElement.checked){
			valor_vyv_admcolas=1;
		}else{
			valor_vyv_admcolas=0;
		}
		/*let valor_sp_confsist;
		if(this.EL_sp_confsist.nativeElement.checked){
			valor_sp_confsist=1;
		}else{
			valor_sp_confsist=0;
		}*/
		let valor_sp_admcolas;
		if(this.EL_sp_admcolas.nativeElement.checked){
			valor_sp_admcolas=1;
		}else{
			valor_sp_admcolas=0;
		}
		let valor_resetpassword;
		if(this.data.IDUsuario==0){
			valor_resetpassword=1;
		}else{
			if(this.EL_resetpassword.nativeElement.checked){
				valor_resetpassword=1;
			}else{
				valor_resetpassword=0;
			}
		}
		this._configuracionService.saveConfUsuario(this.nitCtrl.value, this.usuarioCtrl.value, this.nombreCtrl.value, this.dpiCtrl.value, this.estadoCtrl.value, this.emailCtrl.value, this.puestoCtrl.value, this.sedeCtrl.value, this.telefonoCtrl.value, valor_global_paramgen, valor_global_aprobarpp, valor_global_catalogos, this.data.IDUsuario, valor_global_admusuarios, this.rolAtConCtrl.value, valor_atcon_admcolas, valor_atcon_presencial, this.rolJurCtrl.value, valor_jur_admcolas, this.rolVyVCtrl.value, valor_vyv_admcolas, this.rolSPCtrl.value, valor_sp_admcolas, valor_global_confsist, valor_resetpassword, valor_atcon_call_center ).subscribe((retvalue)=>{
			console.log(retvalue)
			if(retvalue["reason"] == 'OK'){
				var tempstr=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				// console.log(tempstr);
				this.flagChange=false;
				this.flagSaved=true;
				this.SetTimerSaved();
				this.flagErrorLogin=false;
				this.flagErrorEmail=false;
				if(this.data.IDUsuario==0){
					this.lbl_newuser=this.usuarioCtrl.value;
					this.flagNewUserSaved=true;
					this.SetTimerNewUser();
					this.data.IDUsuario=tempstr[0]['id_usuario'];
					// console.log(this.data.IDUsuario);
				}
				/*if(this.data.IDUsuario!=0){
					this.EL_resetpassword.nativeElement.checked=false;
				}*/
			}else if(retvalue["reason"] == 'ERROR_USUARIO'){
				this.flagErrorLogin=true;
				this.SetTimerErrorLogin();
			}else if(retvalue["reason"] == 'ERROR_EMAIL'){
				this.flagErrorEmail=true;
				this.SetTimerErrorEmail();
			}else{
				console.log('Rest service2 response ERROR.');
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
