import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FrmComPermanente } from '../fakedata';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const"
import { CatalogoService } from '../shared/catalogo.service'
import { RegistrosService } from '../shared/registros.service'
import { CompermanenteService } from './compermanente.service'
import { QuejaService } from '../shared/queja.service'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-comunicacion-permanente',
  templateUrl: './comunicacion-permanente.component.html',
  styleUrls: ['./comunicacion-permanente.component.css'],
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
export class ComunicacionPermanenteComponent implements OnInit {

  @ViewChild('replink') replink: ElementRef;
  cmbvia:CmbCatalogo[];
  LocalForm;
  myForm: FormGroup;
  estatusCtrl: FormControl;
  observacionesCtrl: FormControl;
  viaCtrl: FormControl;
  flagDBError: boolean;
  flagUpdateInfo: boolean;
  ReadOnlyVia;
  routerlink;
  linkdescription;
  flagReportDisponible: boolean;
  registrodata:FrmRegComCon[];
  flagformvisible;lst_queja;
  flagEditable:boolean;
  loaderror:boolean;
  flagMainUpdate:boolean;
  flagRegError:boolean;
  btnvisible:boolean;
  loc_pagina;
  showSpinner: boolean = false;  

  constructor( @Inject(MAT_DIALOG_DATA) public data: any, private _catalogoservice:CatalogoService, private _compermservice:CompermanenteService, public dialogRef: MatDialogRef<ComunicacionPermanenteComponent>, private _registrosservice:RegistrosService, private _quejaService: QuejaService, private _seguridadService:SeguridadService ) { 
		this.flagDBError = false;
		this.flagUpdateInfo = false;
		this.flagReportDisponible=false;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagformvisible=0;
		this.flagMainUpdate=false;
		this.flagRegError=false;
		this.btnvisible=true;
		this.loc_pagina=0;
		this.showSpinner=false;
  }

  ngOnInit() {
	this.estatusCtrl =  new FormControl('',Validators.required);
	this.observacionesCtrl =  new FormControl('');
	this.viaCtrl = new FormControl('',Validators.required);

	this.myForm = new FormGroup({
      estatus: this.estatusCtrl,
      observaciones: this.observacionesCtrl,
	  via: this.viaCtrl
    });
	this.LoadCmbVia();
	console.log('Valor de data'+this.data.NoQueja);	
	 this.LocalForm=[];
	 this.routerlink='MuestraRegistro/1/1';
	 this.GetRegistro(false);
	 this.GetQuejaList(this.data.NoQueja);
	 this.SetSecTimerForm();
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
					if( this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']) )
						//si es rol 3 administrador, puede editar
						//if(this.data.Rol == 3)  ORIGINAL
						if(this.data.Rol == 3 || this.data.Rol == 2)    //EDITADO JJ   SI ES ADMIN 3 Ó SUPERVISOR 2
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
					
					console.log(this.flagEditable,this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']),this.lst_queja[0]['usuario_asignado'],this.data.Usuario);
				}else{
					console.log('Información de queja no pudo ser obtenida.');
					this.flagDBError=true;
					this.SetSecTimerInfoError();
				}				
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
	}
   
   ngAfterViewInit(){
		this.GetFormData(this.data.NoQueja);		
   }
   
  onSubmit() {
	  if (this.myForm.valid) {
		console.log(this.myForm.value);
		this.SaveData();
	  }
  }

  LoadFrmData(isempty:boolean){
		//this.myForm.get('estatus').setValue(this.FormData.estatus);
		//this.myForm.controls['observaciones'].setValue(this.FormData.observaciones);
		//this.myForm.controls['via'].setValue(this.FormData.via);
	console.log(this.LocalForm);
	//if(typeof this.LocalForm["0"] === 'undefined'){
	if(isempty){
		this.estatusCtrl.setValue('');
		this.observacionesCtrl.setValue('');
		this.ReadOnlyVia='';
		this.flagReportDisponible=false;
	}else{
		/* this.estatusCtrl.setValue(this.LocalForm["0"].estatus);
		this.observacionesCtrl.setValue(this.LocalForm["0"].observaciones);
		this.viaCtrl.setValue(this.LocalForm["0"].via);
		this.ReadOnlyVia= this.Getdescripcionvia(this.LocalForm["0"].via); */
		this.flagReportDisponible=true;
	}
	
	//if("0" in this.LocalForm){
		
  }

  LoadCmbVia(){//MANDA A TRAER LAS COMUNICACIONES EN TABLA DIACO_COMUNICACION_PERMANENTE
		let tempstr='';
		this._catalogoservice.getData('diaco_comunicacion_consumidor').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmbvia=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
				}else{
					this.cmbvia=[];
				}
				this.flagDBError=false;
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
  }
  
  GetFormData(noqueja){
		let tempstr='';
		this._compermservice.getData(noqueja, this.data.Flujo).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.LocalForm=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					console.log(this.LocalForm);
					this.LoadFrmData(false);
				}else{
					this.LocalForm=[];
					this.LoadFrmData(true);
				}
				this.flagformvisible++;
				this.flagDBError=false;
			}else{
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
  }
  
  SaveData(){
	  this.btnvisible=false;
	  this._compermservice.addData(this.estatusCtrl.value,this.observacionesCtrl.value,this.viaCtrl.value, this.data.NoQueja, this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue['reason'] == 'OK'){
				console.log(retvalue);
				this.flagUpdateInfo=true;
				this.flagReportDisponible=true;
				this.SetSecTimerUpdate();
				this.flagMainUpdate=true;
				this.GetFormData(this.data.NoQueja);
				this.BorrarInput();
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}
			this.btnvisible=true;
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.btnvisible=true;
			this.SetSecTimerInfoError();
		});
  }
  /*
  openFichaRegistro(){
		console.log('entro a openFichaRegistro');
		this._compermservice.getFichaRegistro().subscribe((Data)=>{
		console.log('entro a openRegisto');
			this._compermservice.FileDownLoadChoose(Data,1);
			this.flagDBError=false;
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
	}*/ 
	openComunicacionPerm(){
		console.log('entro a openComunicacionPerm con id reporte: '+this.routerlink);
		this._registrosservice.getComPermanente(this.routerlink).subscribe((Data)=>{
		console.log('entro a openRegisto');
			this._registrosservice.FileDownLoadChoose(Data,1);
			this.flagRegError=false;
			this.showSpinner=false;
		},(error)=>{
			console.log(error);
			this.flagRegError=true;
			this.SetSecTimerRegistro();
		});
	}
  
  closeDialog() {
		this.loc_pagina=0;
		this.ngOnDestroy();
	}
	
  SubmitandGo(){
	  //console.log('we got here');
	  if (this.myForm.valid) {
		console.log(this.myForm.value);
		this.SaveData();
		this.loc_pagina=1;
		this.ngOnDestroy();
	  }
  }
  
  ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.pagina = this.loc_pagina;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}	

	BorrarInput(){
		this.estatusCtrl.setValue('');
		this.observacionesCtrl.setValue('');
		this.viaCtrl.setValue('');
		this.estatusCtrl.errors.required=false;	
		this.viaCtrl.errors.required=false;
	}
  
	SetSecTimerUpdate(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdateInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
		this.showSpinner=false;
	}
	ClickAfter3sec(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
	}
	ClickAfterHalfsec(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
		
	}
	SetSecTimerRegistro(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
		this.showSpinner=false;
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
	
  	Getdescripcionvia(idd:number){
  	  var id=+idd;
	  if (id === null)
		  return null;
	  var found=null;
	  for (var me of this.cmbvia) {
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
			this._registrosservice.add_ComCon(this.data.NoQueja).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue + ' jj ');
					//this.SetSecTimerUpdateList();
					this.flagDBError=false;
					this.GetRegistro(true);
				}else{
					console.log('Rest service response ERROR.');
					this.flagRegError=true;
					this.SetSecTimerRegistro();
				}				
			},(error)=>{
				console.log(error);
				this.flagRegError=true;
				this.SetSecTimerRegistro();
			});  
		}
	}

	GenerarRegistroIndividual(id_comunicacion_consumidor){
		this.showSpinner=true;
		var approved=false;
		if(this.linkdescription != ''){
			approved=true;
		}else{
			approved=true;
		}
		if(approved){
			this._registrosservice.add_ComCon1(this.data.NoQueja,id_comunicacion_consumidor).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue + ' jj ');
					//this.SetSecTimerUpdateList();
					this.flagDBError=false;
					this.GetRegistro(true);
				}else{
					console.log('Rest service response ERROR.');
					this.flagRegError=true;
					this.SetSecTimerRegistro();					
				}			
			}
			,(error)=>{
				console.log(error);
				this.flagRegError=true;
				this.SetSecTimerRegistro();
			});
		}
	}
	
	  GetRegistro(click:boolean){
		  console.log('noQueja es '+this.data.NoQueja);
	  this._registrosservice.read_ComCon(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					//console.log(retvalue);
					this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
					this.routerlink=this.registrodata[0]['id'];
					this.linkdescription=this.registrodata[0]['codigo'];
					console.log('registrodata ',this.registrodata);
					if(click){
						this.ClickAfterHalfsec();
					}
				}else{
					this.routerlink='';
					this.linkdescription='';
					this.showSpinner=false;
				}
				this.flagformvisible++;
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
				this.showSpinner=false;
			}		
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
			this.showSpinner=false;
		});
  }

	
  }