import { Component, OnInit, OnDestroy } from '@angular/core';
import { UsuarioService } from '../shared/usuario.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { SubmitFormService } from "../shared/submit-form.service";
import { ProveedorService } from "../shared/proveedor.service";
import { FrmConfUsuarioBusqueda, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-conf-usuario-conc-previa',
  templateUrl: './conf-usuario-conc-previa.component.html',
  styleUrls: ['./conf-usuario-conc-previa.component.css'],
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
export class ConfUsuarioConcPreviaComponent implements OnInit {
	flagInfoError:boolean;
	loaderror:boolean;
	tabledata:boolean;
	flagBoton:boolean;
	flagSaved:boolean;
	btnvisible:boolean;
	flagChange:boolean;
	flagMainUpdate:boolean;
	myForm: FormGroup;
	proveedorCtrl: FormControl;
	usuarioCtrl: FormControl;
	nombreCtrl: FormControl;
	flagformvisible; list_conf; ListUsuariosCount; cmb_prov;
	idUsuario;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ConfUsuarioConcPreviaComponent>, private _usuarioService: UsuarioService, private _submitFormService: SubmitFormService, private _proveedorService: ProveedorService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.flagSaved=false;
	this.btnvisible=true;
	this.flagChange=false;
	this.flagMainUpdate=false;
	this.idUsuario=0;
	this.flagformvisible=0;
	this.ListUsuariosCount=-1;
  }

  ngOnInit() {
	  this.proveedorCtrl =  new FormControl('',Validators.required);
	  this.usuarioCtrl =  new FormControl('');
	  this.nombreCtrl =  new FormControl('');
	  
	  this.myForm = new FormGroup({
		proveedor: this.proveedorCtrl,
		usuario: this.usuarioCtrl,
		nombre: this.nombreCtrl,
	  });
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.loadParams();
	  this.GetcmbProveedores();
	  //this.generoCtrl.setValue('Masculino');
  }

  ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.proveedorCtrl.value;
		localresp.quejanumero = this.idUsuario;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

  loadParams(){
	  let frmdata:FrmConfUsuarioBusqueda=this._submitFormService.Get_ConfUsuarioBusquedaPP();
	  console.log(frmdata);
	  this.proveedorCtrl.setValue(frmdata.proveedor);
	  this.usuarioCtrl.setValue(frmdata.usuario);
	  this.nombreCtrl.setValue(frmdata.nombre);
	  if(this.data.Load == 1){
			this.GetList();
	  }
  }

  FormUpdate(){
	  this.flagChange=true;
  }

  OpenDetalle(usuario){
		this.flagMainUpdate=true;
		this.idUsuario=usuario;
		this.ngOnDestroy();
  }	  

 onSubmit(){}

  GetList(){
	  this._submitFormService.Set_ConfUsuarioBusquedaPP(this.proveedorCtrl.value,this.usuarioCtrl.value,this.nombreCtrl.value);
	  this._usuarioService.CustomFilterPP(this.proveedorCtrl.value,this.usuarioCtrl.value,this.nombreCtrl.value).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(retvalue);
				if(tempstr != null && tempstr != '')	{
					this.list_conf=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.list_conf);
					this.ListUsuariosCount=this.list_conf.length;
					console.log(this.ListUsuariosCount);
				}else{
					this.ListUsuariosCount=0;
					this.list_conf=[];
				}
				console.log(this.ListUsuariosCount);
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

	GetcmbProveedores(){
	  this._proveedorService.getProveedoresCPoSP().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(this.cmb_prov);
				if(tempstr != null && tempstr != '')	{
					this.cmb_prov=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
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
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	txtTrim(pos){
		switch(pos){
			case 1:
				let varusuario=this.usuarioCtrl.value;
				this.usuarioCtrl.setValue(varusuario.substring(0, 14));
				break;
			case 2:
				let varnombre=this.nombreCtrl.value;
				this.nombreCtrl.setValue(varnombre.substring(0, 99));
				break;
		}
		this.FormUpdate();
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

}
