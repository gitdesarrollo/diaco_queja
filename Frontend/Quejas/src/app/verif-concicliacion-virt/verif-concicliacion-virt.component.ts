import { Component, OnInit, Input, AfterViewInit, OnDestroy } from '@angular/core';
import { FrmVerifConcP } from '../fakedata';
import { ConcvirtService } from './concvirt.service';
import { QuejaService } from '../shared/queja.service';
import { CatalogoService } from '../shared/catalogo.service';
import { ActivatedRoute } from '@angular/router';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { FrmDetalle_Queja, FrmConcVirt, CmbCatalogo, FormResponse } from '../atencion-consumidor-const';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-verif-concicliacion-virt',
  templateUrl: './verif-concicliacion-virt.component.html',
  styleUrls: ['./verif-concicliacion-virt.component.css'],
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
export class VerifConcicliacionVirtComponent implements OnInit {

  idd:number;
  div_aceptar_rechazar:boolean;
  div_continuar:boolean;
  flagInfoError:boolean;
  flagInsertInfo:boolean;
  flagNoInfo:boolean;
  flagSaveBtn:boolean;
  flagMainUpdate:boolean;
  lst_queja:FrmDetalle_Queja[];
  sing_queja:FrmDetalle_Queja;
  lst_resolucion:CmbCatalogo[];
  lst_respuesta_diaco:CmbCatalogo[];
  lst_concvirt:FrmConcVirt[];
  sing_concvirt:FrmConcVirt;
  myForm: FormGroup;
  RazonCtrl: FormControl;
  DataSaved: boolean;
  flagformvisible;
  flagEditable:boolean;
  loaderror:boolean;
  loc_pagina;
  
  constructor(private _catalogoService: CatalogoService, private _quejaService: QuejaService, private _concvirtService: ConcvirtService, private route: ActivatedRoute, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<VerifConcicliacionVirtComponent>) { 
		this.DataSaved=true;
		this.flagInfoError=false;
		this.flagInsertInfo=false;
		this.flagNoInfo=false;
		this.flagSaveBtn=true;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagMainUpdate=false;
		this.flagformvisible=0;
		this.loc_pagina=0;
  }

  ngOnInit() {
		this.RazonCtrl = new FormControl('',Validators.required);
		this.myForm = new FormGroup({
		  razon: this.RazonCtrl
		});
		
		this.idd = +this.route.snapshot.paramMap.get('id');
		this.sing_queja= new FrmDetalle_Queja();
		this.sing_concvirt= new FrmConcVirt();
		console.log(this.data);
		this.BlankFrm();
		this.BlankFrm2();
		this.GetListResolucion();
		this.GetListRespDiaco();
		this.GetQuejaList(this.data.NoQueja);
		this.GetConcVirt(this.data.NoQueja);
		this.SetSecTimerForm();
  }
  
  ButtonLogic(error){
	if(!error){
		console.log(this.sing_concvirt.resolucion);
		if(this.sing_concvirt.resolucion == 9){
			this.div_aceptar_rechazar = true;
			this.div_continuar = false;
		}
		else if(this.sing_concvirt.resolucion == 10 || this.sing_concvirt.resolucion == 8){
			this.div_aceptar_rechazar = false;
			this.div_continuar = true;
		}
		else{
			this.div_aceptar_rechazar = false;
			this.div_continuar = false;
		}
	}else{
		this.div_aceptar_rechazar = false;
		this.div_continuar = false;
	}
  }

	closeMoveDialog1() {
		this.loc_pagina=1;
		this.ngOnDestroy();
	}

	closeMoveDialog2() {
		this.loc_pagina=2;
		this.ngOnDestroy();
	}

	closeDialog() {
		this.loc_pagina=0;
		this.ngOnDestroy();
	}

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.pagina = this.loc_pagina;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}		

	GetListResolucion(){
		let tempstr='';
		this._catalogoService.getData('resolver_queja').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_resolucion=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagInfoError=false;
				}else{
					this.lst_resolucion=[];
					console.log('List Resolución vacia.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
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
	
	GetListRespDiaco(){
		let tempstr='';
		this._catalogoService.getData('respuesta_diaco_conc_virt').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_respuesta_diaco=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagInfoError=false;
				}else{
					this.lst_respuesta_diaco=[];
					console.log('List Respuesta Diaco vacia.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
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


	GetQuejaList(idqueja){
	  let tempstr='';
	  this._quejaService.getDataQueja(idqueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					//verificar estado para permitir edicion en formulario
					if(this.lst_queja[0]['id_estado_queja']>=101 && this.lst_queja[0]['id_estado_queja']<=106)
						//si es rol 3 administrador, puede editar
						if(this.data.Rol == 3)
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
					this.LoadFrmData();
				}else{
					this.BlankFrm();
					console.log('Información de queja no pudo ser obtenida.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}				
			}else{
				this.BlankFrm();
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
	
	GetConcVirt(idqueja){
	  let tempstr='';
		this._concvirtService.getData(idqueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_concvirt=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					console.log(this.lst_concvirt);
					this.flagInfoError=false;
					this.LoadFrmData2();
				}else{
					this.BlankFrm2();
					this.ButtonLogic(true);
				}
				this.flagformvisible++;
			}else{
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.ButtonLogic(true);
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.ButtonLogic(true);
			this.SetSecTimerInfoError();
		});
	}
	
	Btn_Validar_Conciliacion(){
		//this.flagSaveBtn=false;
		//this.SetSecTimerDissapButton();
		let resp=this.getRespuestaDiacoID('Validar Conciliación');
		if(resp != 0){
			this.SaveData(resp);
		}else{
			console.log('Error al buscar identificador de respuesta DIACO');
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		}
	}

	Btn_Rechazar(){
		//this.flagSaveBtn=false;
		//this.SetSecTimerDissapButton();
		let resp=this.getRespuestaDiacoID('Rechazar Solución y Continuar Trámite');
		if(resp != 0){
			this.SaveData(resp);
		}else{
			console.log('Error al buscar identificador de respuesta DIACO');
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		}
	}
	
	Btn_Continuar(){
		//this.flagSaveBtn=false;
		//this.SetSecTimerDissapButton();
		let resp=this.getRespuestaDiacoID('Continuar Trámite');
		if(resp != 0){
			this.SaveData(resp);
		}else{
			console.log('Error al buscar identificador de respuesta DIACO');
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		}
	}
	
	SaveData(respuesta_diaco){
		this.flagSaveBtn=false;
		this._concvirtService.addData(this.RazonCtrl.value, respuesta_diaco, this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue['reason'] == 'OK'){
				console.log(retvalue);
				this.flagInsertInfo=true;
				this.SetSecTimerInsert();
				this.DataSaved=true;
				this.flagNoInfo=false;
				this.flagMainUpdate=true;
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.SetSecTimerDissapButton();
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
			this.SetSecTimerDissapButton();
		});
	}
	
	LoadFrmData(){
		if(typeof this.lst_queja["0"] === 'undefined'){
			this.sing_queja.motivo='';
			this.sing_queja.detalle_queja='';
			this.sing_queja.solicita_que ='';
			this.sing_queja.fecha_queja =null;
			this.sing_queja.quejanumero ='';
		}else{
			this.sing_queja.motivo=this.lst_queja["0"].motivo;
			this.sing_queja.detalle_queja=this.lst_queja["0"].detalle_queja;
			this.sing_queja.solicita_que =this.lst_queja["0"].solicita_que;
			this.sing_queja.fecha_queja =this.lst_queja["0"].fecha_queja;
			this.sing_queja.quejanumero =this.lst_queja["0"].quejanumero;			
		}
		console.log(this.sing_queja);
	}
	
	BlankFrm(){
		this.sing_queja.id_queja=null;
		this.sing_queja.motivo=null;
		this.sing_queja.detalle_queja=null;
		this.sing_queja.solicita_que =null;
		this.sing_queja.fecha_queja =null;
	}

	LoadFrmData2(){
		if(typeof this.lst_concvirt["0"] === 'undefined'){
			this.sing_concvirt.resolucion=null;
			this.sing_concvirt.notas='';
			this.RazonCtrl.setValue('');
			this.ButtonLogic(true);
			this.sing_concvirt.respuesta_con='';
			this.sing_concvirt.resolucion_str='';
		}else{
			this.sing_concvirt.resolucion=this.lst_concvirt["0"].resolucion;
			this.sing_concvirt.resolucion_str=this.getResolucionDescripcion(+this.lst_concvirt["0"].resolucion);
			this.sing_concvirt.notas=this.lst_concvirt["0"].notas;
			this.RazonCtrl.setValue(this.lst_concvirt["0"].razon_prop_provee_rechazada);
			this.ButtonLogic(false);
			if(this.lst_concvirt["0"].razon_prop_provee_rechazada != null && this.lst_concvirt["0"].razon_prop_provee_rechazada != '')
				this.flagNoInfo=false;
			else
				this.flagNoInfo=true;
			if(this.lst_concvirt["0"].respuesta_con == '')
				this.sing_concvirt.respuesta_con ='Confirmación Pendiente';
			else
				this.sing_concvirt.respuesta_con =this.lst_concvirt["0"].respuesta_con;
		}
		console.log(this.lst_concvirt);
		console.log(this.sing_concvirt);
	}
	
	BlankFrm2(){
		this.sing_concvirt.resolucion=null;
		this.sing_concvirt.notas=null;
		this.sing_concvirt.razon_prop_provee_rechazada =null;
		this.sing_concvirt.respuesta_con='';
	}

	
	SetSecTimerInsert(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerDissapButton(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flagSaveBtn=true);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<4){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	getResolucionDescripcion(id){
	  if (id === null)
		  return null;
	  var found=null;
	  for (var me of this.lst_resolucion) {
		  if (me['id_catalogo'] === id) { 
				found=me['descripcion_catalogo'];
		  }
   	  }	  
	  if (found != null){
		  return found;
	  }else{
		  return null;
	  }
	}

	getRespuestaDiacoID(desc){
	  if (desc === null)
		  return '';
	  var found=null;
	  for (var me of this.lst_respuesta_diaco) {
		  if (me['descripcion_catalogo'] === desc) { 
				found=me['id_catalogo'];
		  }
   	  }	  
	  if (found != null){
		  return found;
	  }else{
		  return '';
	  }
	}

}
