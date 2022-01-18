import { Component, OnInit, OnDestroy } from '@angular/core';
import { QuejaService } from '../shared/queja.service';
import { ConsumidorService } from '../shared/consumidor.service';
import { ProveedorService } from '../shared/proveedor.service';
import { MainTableService } from '../tabla-main-at-con/main-table.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FormResponse, FrmBusqueda_Con } from '../atencion-consumidor-const';
import { SubmitFormService } from "../shared/submit-form.service";
import { Inject } from '@angular/core';

@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.css']
})
export class BusquedaComponent implements OnInit {
  flagformvisible;
  flagEditable:boolean;
  flagInfoError:boolean;
  loaderror:boolean;
  loc_pagina;loc_queja;
  frmConsumidor: FormGroup;
  con_nombreCtrl: FormControl;
  con_apellidoCtrl: FormControl;
  con_dpiCtrl: FormControl;
  con_nitCtrl: FormControl;
  frmProveedor: FormGroup;
  pro_nombreCtrl: FormControl;
  pro_nitCtrl: FormControl;
  frmMain: FormGroup;
  NoQuejaCtrl: FormControl;
  FechaDesdeCtrl: FormControl;
  FechaHastaCtrl: FormControl;
  EstadoCtrl: FormControl;
  lst_consumidor;ConsumidorResult;ConsumidorActivo;
  lst_proveedor;ProveedorResult;ProveedorActivo;
  FrmMainList;QuejaResult;
  
  constructor(public dialogRef: MatDialogRef<BusquedaComponent>, private _quejaService: QuejaService, private _consumidorService: ConsumidorService, private _proveedorService: ProveedorService, private _mainTableService: MainTableService, private _submitFormService:SubmitFormService, @Inject(MAT_DIALOG_DATA) public data: any) { 
		this.loaderror=false;
		this.flagInfoError=false;
		this.flagformvisible=0;
		this.loc_pagina=0;
		this.loc_queja=0;
		this.ConsumidorResult=-1;
		this.ConsumidorActivo=0;
		this.ProveedorResult=-1;
		this.ProveedorActivo=0;
		this.QuejaResult=-1;
  }

  ngOnInit() {
	  this.con_nombreCtrl = new FormControl('');
	  this.con_apellidoCtrl = new FormControl('');
	  this.con_dpiCtrl = new FormControl('');
	  this.con_nitCtrl = new FormControl('');
	  this.frmConsumidor = new FormGroup({
		  con_nombre: this.con_nombreCtrl,
		  con_apellido: this.con_apellidoCtrl,
		  con_dpi: this.con_dpiCtrl,
		  con_nit: this.con_nitCtrl
	  });
	  this.pro_nombreCtrl = new FormControl('');
	  this.pro_nitCtrl = new FormControl('');
	  this.frmProveedor = new FormGroup({
		  pro_nombre: this.pro_nombreCtrl,
		  pro_nit: this.pro_nitCtrl
	  });
	  this.NoQuejaCtrl = new FormControl('');
	  this.FechaDesdeCtrl = new FormControl('');
	  this.FechaHastaCtrl = new FormControl('');
	  this.EstadoCtrl = new FormControl('');
	  this.frmMain = new FormGroup({
		  noqueja: this.NoQuejaCtrl,
		  fechadesde: this.FechaDesdeCtrl,
		  fechahasta: this.FechaHastaCtrl,
		  estadoFiltro: this.EstadoCtrl,
	  });
	  this.ResetConsumidor();
	  this.ResetProveedor();
	  this.SetSecTimerForm();
	  this.EstadoCtrl.setValue(1);
  }

	FilterConsumidor(){
		let tempstr='';
		this._consumidorService.CustomFilter(this.con_nombreCtrl.value, this.con_apellidoCtrl.value, this.con_dpiCtrl.value, this.con_nitCtrl.value).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_consumidor=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.ConsumidorResult=this.lst_consumidor.length;
					console.log(this.lst_consumidor);
					this.ConsumidorLogic();
				}else{
					this.lst_consumidor==null;				
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
  
	FilterProveedor(){
		let tempstr='';
		this._proveedorService.CustomFilter(this.pro_nombreCtrl.value, this.pro_nitCtrl.value).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_proveedor=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.ProveedorResult=this.lst_proveedor.length;
					console.log(this.lst_proveedor);
					this.ProveedorLogic();
				}else{
					this.lst_proveedor==null;				
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
  
	SearchQuejas(){
		let tempstr='';
		this._mainTableService.SearchBusqueda(this.data.Flujo, this.ConsumidorActivo, this.ProveedorActivo, this.NoQuejaCtrl.value, this.FechaDesdeCtrl.value, this.FechaHastaCtrl.value, this.EstadoCtrl.value).subscribe((Data)=>{
			if(Data["reason"] == 'OK'){
				tempstr=Data['value'];
				console.log(Data);
				//console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.FrmMainList=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.QuejaResult=this.FrmMainList.length;
				}else{
					this.FrmMainList=[];
					this.QuejaResult=0;
				}
				/*this.rows=this.FrmMainList;
				this.data = this.rows;
				this.filteredData = this.rows;*/
				console.log(Data);			
				this.flagInfoError=false;
			}else{
				console.log('Rest Service Error');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
		//this.InfotoArray();
	}
  
    ConsumidorLogic(){
		if(this.ConsumidorResult==1)
			this.ConsumidorActivo=this.lst_consumidor[0]['id_consumidor'];
		else
			this.ConsumidorActivo=0;
	}
  
	ProveedorLogic(){
		if(this.ProveedorResult==1)
			this.ProveedorActivo=this.lst_proveedor[0]['id_proveedor'];
		else
			this.ProveedorActivo=0;
	}
  
	ConsumidorClick(pos){
		console.log(pos);
		let temp_lst=[];
		temp_lst[0]=this.lst_consumidor[pos];
		this.lst_consumidor=temp_lst;
		this.ConsumidorResult=this.lst_consumidor.length;
		this.ConsumidorLogic();
		console.log(temp_lst, this.lst_consumidor);
	}

	ProveedorClick(pos){
		console.log(pos);
		let temp_lst=[];
		temp_lst[0]=this.lst_proveedor[pos];
		this.lst_proveedor=temp_lst;
		this.ProveedorResult=this.lst_proveedor.length;
		this.ProveedorLogic();
		console.log(temp_lst, this.lst_proveedor);
	}

  
	ResetConsumidor(){
		this.con_nombreCtrl.setValue('');
		this.con_apellidoCtrl.setValue('');
		this.con_dpiCtrl.setValue('');
		this.con_nitCtrl.setValue('');
		this.lst_consumidor=[];
		this.ConsumidorResult=-1;
		this.ConsumidorLogic();
	}
  
	ResetProveedor(){
		this.pro_nombreCtrl.setValue('');
		this.pro_nitCtrl.setValue('');
		this.lst_proveedor=[];
		this.ProveedorResult=-1;
		this.ProveedorLogic();
	}  
  
  closeDialog() {
		this.loc_pagina=0;
		this.ngOnDestroy();
	}
	
	AsignarQuejayCerrar(numeroqueja){
		this.loc_queja=numeroqueja;
		this.loc_pagina=1;
		this.ngOnDestroy();
	}
	
	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.quejanumero = this.loc_queja;
		localresp.pagina = this.loc_pagina;
		this.dialogRef.close(localresp);
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<0){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
}
