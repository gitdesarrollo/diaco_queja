import { Component, OnInit, OnDestroy } from '@angular/core';
import { QuejaService } from '../shared/queja.service';
import { ConsumidorService } from '../shared/consumidor.service';
import { ProveedorService } from '../shared/proveedor.service';
import { MainTableService } from '../tabla-main-at-con/main-table.service';
import { UsuarioService } from '../shared/usuario.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FormResponse, FrmBusqueda_Con } from '../atencion-consumidor-const';
import { SubmitFormService } from "../shared/submit-form.service";
import { Inject } from '@angular/core';

@Component({
  selector: 'app-reasignar-reactivar',
  templateUrl: './reasignar-reactivar.component.html',
  styleUrls: ['./reasignar-reactivar.component.css'],
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
export class ReasignarReactivarComponent implements OnInit {
  flagformvisible;
  flagEditable:boolean;
  flagInfoError:boolean;
  loaderror:boolean;
  flagUpdate:boolean;
  frmMain: FormGroup;
  NoQuejaCtrl: FormControl;
  AsignarACtrl: FormControl;
  loc_queja; loc_pagina;lst_queja;QuejaResult;lst_visitapor;
  
  constructor(public dialogRef: MatDialogRef<ReasignarReactivarComponent>, private _quejaService: QuejaService, private _consumidorService: ConsumidorService, private _proveedorService: ProveedorService, private _mainTableService: MainTableService, private _submitFormService:SubmitFormService, @Inject(MAT_DIALOG_DATA) public data: any, private _usuarioService:UsuarioService) { 
		this.loaderror=false;
		this.flagInfoError=false;
		this.flagEditable=false;
		this.flagUpdate=false;
		this.flagformvisible=0;
		this.QuejaResult=-1;
		this.loc_queja=0;
		this.loc_pagina=0;
  }

  ngOnInit() {
	  this.NoQuejaCtrl = new FormControl('');
	  this.AsignarACtrl = new FormControl('');
	  this.frmMain = new FormGroup({
		  noqueja: this.NoQuejaCtrl,
		  asignar_a: this.AsignarACtrl,
	  });
	  //this.SetSecTimerForm();
  }
  
	SearchQueja(){
		let tempstr='';
		this._quejaService.getbyCodigo(this.NoQuejaCtrl.value).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.QuejaResult=this.lst_queja.length;
					this.flagUpdate=false;
					console.log(this.lst_queja);
					this.flagformvisible++;
					this.GetHechaPorList();
					//si es rol 3 administrador, puede editar
					if(this.data.Rol == 3)
						this.flagEditable=true;
				}else{
					console.log('Información de queja no pudo ser obtenida.');
					this.QuejaResult=0;
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
  
	closeDialog() {
		this.loc_pagina=0;
		this.ngOnDestroy();
	}
	
	GetHechaPorList(){
	  this._usuarioService.getData(this.lst_queja[0]['id_flujo']).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				this.lst_visitapor=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				this.AsignarACtrl.setValue(this.lst_visitapor[0]['id_usuario']);
				console.log(this.lst_visitapor);
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
	
	ReasignarQ(){
		if ( confirm("¿Esta seguro que desea reasignar esta queja a otro usuario?") ) {
			this.SaveReasignarFrm(2);
		}
	}
	
	ReactivarQ(){
		if ( confirm("¿Esta seguro que desea reactivar esta queja que ya se encuentra archivada?") ) {
			this.SaveReasignarFrm(1);
		}
	}
	
	SaveReasignarFrm(actualizaestado){
		var flujoasignado=0;
		if(this.lst_queja[0]['id_flujo']==6){
			flujoasignado=6;
		}else{
			flujoasignado=this.data.Flujo;
		}
		this._quejaService.saveReasignar(actualizaestado, this.lst_queja[0]['id_queja'], flujoasignado, this.AsignarACtrl.value).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				this.flagUpdate=true;
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