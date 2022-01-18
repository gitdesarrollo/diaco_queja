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
import { ProveedorService } from "../shared/proveedor.service";

@Component({
  selector: 'app-usuario-detalle-conc-previa',
  templateUrl: './usuario-detalle-conc-previa.component.html',
  styleUrls: ['./usuario-detalle-conc-previa.component.css'],
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
export class UsuarioDetalleConcPreviaComponent implements OnInit {
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
	myForm: FormGroup;
	nitCtrl: FormControl;
	usuarioCtrl: FormControl;
	nombreCtrl: FormControl;
	dpiCtrl: FormControl;
	estadoCtrl: FormControl;
	emailCtrl: FormControl;
	telefonoCtrl: FormControl;
	rolPPCtrl: FormControl;
	proveedorCtrl: FormControl;
	flagformvisible; list_conf; ListUsuariosCount;
	idUsuario;cmb_prov;lbl_newuser;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<UsuarioDetalleConcPreviaComponent>, private _usuarioService: UsuarioService, private _submitFormService: SubmitFormService, private _catalogoService: CatalogoService, private _sedeservice:SedeService, private _configuracionService:ConfiguracionService, private _proveedorService: ProveedorService) { 
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
	  this.telefonoCtrl =  new FormControl('',Validators.required);
	  this.rolPPCtrl =  new FormControl('',Validators.required);
	  this.proveedorCtrl =  new FormControl('',Validators.required);
	  
	  this.myForm = new FormGroup({
		nit: this.nitCtrl,
		usuario: this.usuarioCtrl,
		nombre: this.nombreCtrl,
		dpi: this.dpiCtrl,
		estado: this.estadoCtrl,
		email: this.emailCtrl,
		telefono: this.telefonoCtrl,
		rolPP: this.rolPPCtrl,
		proveedor: this.proveedorCtrl,
	  });
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.LoadDefaults();
	  this.GetcmbProveedores();
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

  LoadDefaults(){
	  this.estadoCtrl.setValue('1');
	  this.rolPPCtrl.setValue('0');
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

  GetList(){
		if(this.data.IDUsuario != 0){
			this._usuarioService.getUsuarioConfPP(this.data.IDUsuario).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					var tempstr=retvalue['value'];
					console.log(this.list_conf);
					if(tempstr != null && tempstr != '')	{
						this.list_conf=JSON.parse('['+retvalue["value"] +']');
						this.LoadInfo(this.list_conf);
						console.log(this.list_conf);
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
		console.log(FrmLocal);
		this.nitCtrl.setValue(FrmLocal[0]['nit']);
		this.usuarioCtrl.setValue(FrmLocal[0]['login']);
		this.nombreCtrl.setValue(FrmLocal[0]['nombre']);
		this.dpiCtrl.setValue(FrmLocal[0]['dpi']);
		this.estadoCtrl.setValue(FrmLocal[0]['habilitado']);
		this.emailCtrl.setValue(FrmLocal[0]['email']);
		this.telefonoCtrl.setValue(FrmLocal[0]['telefono']);
		this.rolPPCtrl.setValue(FrmLocal[0]['pp_rol']);	
		this.proveedorCtrl.setValue(FrmLocal[0]['proveedor']);
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
		if(this.flagformvisible<1){
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
				console.log('Valor de EMAIL: '+varemail);
				this.emailCtrl.setValue(varemail);
				console.log('Valor de emailCtrl: '+this.emailCtrl.value);
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
		console.log('Valor de emailCtrl en método GuardarUsuario: '+this.emailCtrl.value);
		this.btnvisible=false;
		this._configuracionService.saveConfUsuarioPP(this.nitCtrl.value, this.usuarioCtrl.value, this.nombreCtrl.value, this.dpiCtrl.value, this.estadoCtrl.value, this.emailCtrl.value, this.telefonoCtrl.value, this.data.IDUsuario, this.rolPPCtrl.value, this.proveedorCtrl.value, valor_resetpassword ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				var tempstr=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				console.log(tempstr);
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
					console.log(this.data.IDUsuario);
				}
				if(this.data.IDUsuario!=0){
					this.EL_resetpassword.nativeElement.checked=false;
				}
			}else if(retvalue["reason"] == 'ERROR_USUARIO'){
				this.flagErrorLogin=true;
				this.SetTimerErrorLogin();
			}else if(retvalue["reason"] == 'ERROR_EMAIL'){
				this.flagErrorEmail=true;
				this.SetTimerErrorEmail();
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

	GetcmbProveedores(){
	  this._proveedorService.getProveedoresCPoSP().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.cmb_prov=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//default proveedor, leer si form anterior envia algo
					if(this.data.IDProveedor != 0)
						this.proveedorCtrl.setValue(this.data.IDProveedor);
					else
						this.proveedorCtrl.setValue(this.cmb_prov[0]['id_proveedor']);
					console.log(this.cmb_prov);
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

}
