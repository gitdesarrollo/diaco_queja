import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../shared/usuario.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { ConfiguracionService } from "../shared/configuracion.service";
import { SedeService } from "../shared/sede.service";
import { FrmConfUsuarioBusqueda, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-asignar-cola',
  templateUrl: './asignar-cola.component.html',
  styleUrls: ['./asignar-cola.component.css'],
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
export class AsignarColaComponent implements OnInit {
	flagInfoError:boolean;
	loaderror:boolean;
	tabledata:boolean;
	flagBoton:boolean;
	flagSaved:boolean;
	btnvisible:boolean;
	flagChange:boolean;
	flagMainUpdate:boolean;
	flagDetalle:boolean;
	flagScheduled:boolean;
	flagformvisible; list_conf; list_detalle; ListUsuariosCount; ListDetalleCount; lst_usuarios;
	idUsuario; nom_lista; flagEditDetalle; ScheduledDate; idDetalle; usuarioDetalle; fecha_programada; idLista; idFlujo;
	myForm: FormGroup;
	estadoCtrl: FormControl;
	fechaCtrl: FormControl;
	usuarioCtrl: FormControl;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<AsignarColaComponent>, private _usuarioService: UsuarioService, private _configuracionService: ConfiguracionService, private _sedeService: SedeService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.flagSaved=false;
	this.btnvisible=true;
	this.flagChange=false;
	this.flagMainUpdate=false;
	this.flagDetalle=false;
	this.flagEditDetalle=false;
	this.flagScheduled=false;
	this.idUsuario=0;
	this.flagformvisible=0;
	this.ListUsuariosCount=-1;
	this.nom_lista='';
	this.ListDetalleCount=-1;
	this.idDetalle=0;
	this.idLista=0;
	this.idFlujo=0;
	this.usuarioDetalle='';
  }

  ngOnInit() {
	  estadoCtrl: FormControl;
	  fechaCtrl: FormControl;
	  usuarioCtrl: FormControl;
	  this.estadoCtrl =  new FormControl('',Validators.required);
	  this.fechaCtrl =  new FormControl('',Validators.required);
	  this.usuarioCtrl =  new FormControl('',Validators.required);
	  this.myForm = new FormGroup({
		estado: this.estadoCtrl,
		fecha: this.fechaCtrl,
		usuario: this.usuarioCtrl,
	  });
	  
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.GetList();
	  //this.generoCtrl.setValue('Masculino');
  }

  GetUsuarioList(id, id_flujo){
	  this._usuarioService.getListAsignaCola(id, id_flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				this.lst_usuarios=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				this.usuarioCtrl.setValue(this.lst_usuarios[0]['id_usuario']);
				console.log(this.lst_usuarios);
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

  ListClick(id, nombre, id_flujo){
	  this.nom_lista=nombre;
	  this.idLista=id;
	  this.idFlujo=id_flujo;
	  this.GetDetalle(id);
	  this.GetUsuarioList(id, id_flujo);
	  this.flagDetalle=true;
	  //get date with id
  }

  DetailsClick(singleElement){
	  console.log(singleElement);
	  this.idDetalle=singleElement['id'];
	  this.usuarioDetalle=singleElement['login'];
	  this.fecha_programada=singleElement['fecha_programado'];
	  if(singleElement['activo'] == true){
		this.estadoCtrl.setValue(1);
	  }else{
		this.estadoCtrl.setValue(0);  
	  }
	  if(singleElement['flag_programado'] == 1){
		this.flagScheduled=true;
		this.ScheduledDate=singleElement['fecha_programado'];		
	  }else{
		this.flagScheduled=false;  
	  }
	  this.flagEditDetalle=true;
	  //this.getDetalleSingle(id);
  }

  CancelEstado(){
	  this.flagEditDetalle=false;	
  }

  DeleteSchedule(){
		this.flagEditDetalle=false;	
  }
  
  SetSchedule(){
	  this.flagEditDetalle=false;
  }

 onSubmit(){}

	BackDialog(){
		this.flagDetalle=false;
	}

  GetDetalle(id){
	  this._configuracionService.getAsignaCola(id).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(this.list_detalle);
				if(tempstr != null && tempstr != '')	{
					this.list_detalle=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.list_detalle);
					this.ListDetalleCount=this.list_detalle.length;
					console.log(this.ListDetalleCount);
				}else{
					this.ListDetalleCount=0;
					this.list_detalle=[];
				}
				console.log(this.ListDetalleCount);
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

  GetList(){
	  this._sedeService.getColasxUsuario(this.data.Usuario).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(this.list_conf);
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
	
	closeDialog() {
		this.dialogRef.close();
	}

	saveEstado(){
		this.btnvisible=false;
		this._configuracionService.saveColaAsig_EstadoUpdate( this.estadoCtrl.value, this.idDetalle ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagEditDetalle=false;
				this.GetDetalle(this.idLista);
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

	saveColaAsig_Schedule(){
		this.btnvisible=false;
		this._configuracionService.saveColaAsig_Schedule( this.idDetalle, this.fechaCtrl.value ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagEditDetalle=false;
				this.GetDetalle(this.idLista);
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

	deleteSchedule(){
		this.btnvisible=false;
		this._configuracionService.saveColaAsig_Del_Schedule( this.idDetalle ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagEditDetalle=false;
				this.GetDetalle(this.idLista);
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
	
	newUser(){
		this.btnvisible=false;
		this._configuracionService.saveColaAsigNew( this.idLista, this.usuarioCtrl.value ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagEditDetalle=false;
				this.GetDetalle(this.idLista);
				this.GetUsuarioList(this.idLista, this.idFlujo);
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
	
	deleteUser(singleElement){
		if(confirm("¿Está seguro que desea quitar este usuario del listado?")) {
			this.btnvisible=false;
			this._configuracionService.deleteColaAsig( singleElement['id'] ).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					this.flagEditDetalle=false;
					this.GetDetalle(this.idLista);
					this.GetUsuarioList(this.idLista, this.idFlujo);
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
	
}
