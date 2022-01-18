import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { LSTCMB_AREAS, LSTCMB_SEDES, BaseCmbClass, FrmDetalle_Queja, CmbDptoInterno, FrmSede, LstQuejaSimple, FrmMovimExp, FormResponse , FrmMoviExpReg} from '../atencion-consumidor-const';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { CatalogoService } from '../shared/catalogo.service'
import { SedeService } from '../shared/sede.service'
import { QuejaService } from '../shared/queja.service'
import { RegistrosService } from '../shared/registros.service'
import { MovimExpService } from '../movimiento-expediente/movim-exp.service'
import { CompermanenteService } from '../comunicacion-permanente/compermanente.service';
import { MainTableService } from '../tabla-main-at-con/main-table.service'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';

@Component({
  selector: 'app-movimiento-conf-verificacion',
  templateUrl: './movimiento-conf-verificacion.component.html',
  styleUrls: ['./movimiento-conf-verificacion.component.css'],
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
export class MovimientoConfVerificacionComponent implements OnInit {
lst_mhacia: BaseCmbClass[];
	lst_queja:FrmDetalle_Queja[];sing_queja;
	lst_sede:FrmSede[];
	lst_mov:FrmMovimExp[];
	//lst_quejasimple:LstQuejaSimple[];
	registroMvm:FrmMoviExpReg[];
	cmb_hacia:CmbDptoInterno[];
	flagInfoError:boolean;
	flagInfoError2:boolean;
	flagsaved:boolean;
	btnvisible:boolean;
	btnvisible2:boolean;
	flagListPendiente: boolean;
	flagUpdate:boolean;
	flagNinguno:boolean;
	flagMainUpdate:boolean;
	htmlQuejaNo;flagemtpyList;
	htmlLugarActual;htmlidflujo;
	myForm: FormGroup;
	MoverHaciaCtrl: FormControl;
	Form2: FormGroup;
	SedeCtrl: FormControl;
	HaciaIndCtrl: FormControl;
	lst_quejasimple=[];lst_pendassig;
	flagformvisible;
	flagEditable:boolean;
	loaderror:boolean;
	id_registro_mvm;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _sedeservice:SedeService, private _catalogoservice:CatalogoService, public dialogRef: MatDialogRef<MovimientoConfVerificacionComponent>, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _mainTableService:MainTableService, private _movimExpService:MovimExpService, private _compermservice:CompermanenteService) { 
		this.htmlQuejaNo='';
		this.htmlLugarActual='';
		this.htmlidflujo=0;
		this.flagemtpyList=false;
		this.flagInfoError=false;
		this.flagInfoError2=false;
		this.flagsaved=false;
		this.btnvisible=true;
		this.btnvisible2=true;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagUpdate=false;
		this.flagNinguno=false;
		this.flagMainUpdate=false;
		this.flagListPendiente=true;
		this.flagformvisible=0;
		this.id_registro_mvm=0;
  }

  ngOnInit() {
		this.MoverHaciaCtrl =  new FormControl('',Validators.required);
		this.myForm = new FormGroup({
			moverhacia: this.MoverHaciaCtrl,
			lst_quejasimple: new FormArray([])
		});
		this.SedeCtrl =  new FormControl('',Validators.required);
		this.HaciaIndCtrl =  new FormControl('',Validators.required);
		this.Form2 = new FormGroup({
			sede: this.SedeCtrl,
			haciaInd: this.HaciaIndCtrl
		});
		this.HaciaIndCtrl.setValue(1);
		this.LoadCmbHacia();
		this.GetQuejaListSimpleAtCon();
		this.SetSecTimerForm();
		this.LoadCmbSede();
		this.GetListaMovimientos();
  }

	PendienteClick(idqueja){
		//buscar detalles de la queja y cargar a var
		this.QuejaCheck(idqueja);
	}
	
	VolverListado(){
		this.flagListPendiente=true;
	}

  private addCheckboxes() {
		console.log(this.myForm.controls.lst_quejasimple);
		this.myForm.get('lst_quejasimple')['controls']=[];
		this.lst_quejasimple.map((o, i) => {
			const control = new FormControl(i === 0); // if first item set to true, else false
			(this.myForm.controls.lst_quejasimple as FormArray).push(control);
		});
  }

  QuejaCheck(queja){
		let tempstr='';
		this._quejaService.getDataQueja(queja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null && tempstr!='')	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.sing_queja=this.lst_queja[0];
					console.log(this.lst_queja);
					this.flagListPendiente=false;
				}else{
					this.sing_queja=[];
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

  LoadCmbHacia(){
		let tempstr='';
		this._catalogoservice.getDptoInternoMovHaciaVyV().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmb_hacia=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.MoverHaciaCtrl.setValue(this.cmb_hacia[0].id_departamento_interno);
					this.flagformvisible++;
				}else{
					this.cmb_hacia=[];
				}
				console.log(this.cmb_hacia);
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

	GetQuejaListSimpleJuridico(){
		let tempstr='';
		this._mainTableService.getVyVSimpleQuejaJuridico().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				this.lst_quejasimple=JSON.parse('['+tempstr.slice(0, -1) +']');
				if(tempstr != null && tempstr!='')	{
					this.flagemtpyList=false;
				}else{
					this.flagemtpyList=true;
				}
				this.addCheckboxes();
				console.log(this.lst_quejasimple);
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

	GetQuejaListSimpleAtCon(){
		let tempstr='';
		this._mainTableService.getVyVSimpleQuejaAtCon().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				this.lst_quejasimple=JSON.parse('['+tempstr.slice(0, -1) +']');
				if(tempstr != null && tempstr!='')	{
					this.flagemtpyList=false;
				}else{
					this.flagemtpyList=true;
				}
				this.addCheckboxes();
				this.flagformvisible++;
				console.log(this.lst_quejasimple);
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

	GetQuejaListSimpleArchivo(){
		let tempstr='';
		this._mainTableService.getVyVSimpleQuejaArchivo().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				console.log(tempstr);
				this.lst_quejasimple=JSON.parse('['+tempstr.slice(0, -1) +']');
				if(tempstr != null && tempstr!='')	{
					this.flagemtpyList=false;
				}else{
					this.flagemtpyList=true;
				}
				this.addCheckboxes();
				console.log(this.lst_quejasimple);
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

	GetQuejaListPendienteAsignacion(){
		let tempstr='';
		this._mainTableService.getVyVSimplePendAsig().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				console.log(tempstr);
				this.lst_pendassig=JSON.parse('['+tempstr.slice(0, -1) +']');
				this.addCheckboxes();
				console.log(this.lst_pendassig);
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


  MoverHaciaUpdate(){
		if(this.MoverHaciaCtrl.value == 4)
			this.GetQuejaListSimpleArchivo();
		else if(this.MoverHaciaCtrl.value == 1)
			this.GetQuejaListSimpleAtCon();
		else if(this.MoverHaciaCtrl.value == 2)
			this.GetQuejaListSimpleJuridico();
		else if(this.MoverHaciaCtrl.value==9)
			this.GetQuejaListPendienteAsignacion();
		console.log(this.flagemtpyList);
  }

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerInfoError2(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError2=false);
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
	
	closeDialog(){
		this.ngOnDestroy();
	}

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

	GuardarMovimiento(){
		let loc_str=this.GenerarListQueja();
		console.log(loc_str.length);
		if(loc_str.length >0){
			this.btnvisible=false;
			this._movimExpService.addData(loc_str, this.MoverHaciaCtrl.value, 3, 3 ).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){

					var tempstr=retvalue['value'];
					if(tempstr != null)	{
						//console.log(retvalue);
						this.registroMvm=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.id_registro_mvm=this.registroMvm[0]['id'];
					
					}
					this.flagInfoError=false;
					this.flagsaved=true;
					this.flagUpdate=true;
					this.LabelUpdateView();
					this.MoverHaciaUpdate();
					this.UpdateAfter1sec();
					this.flagMainUpdate=true;
				}else{
					this.id_registro_mvm='';
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
		}else{
			console.log('no hay elementos seleccionados');
			this.flagNinguno=true;
			this.NingunElementoTimer();
		}
	}
	
	GuardarIndividual(){
		this.btnvisible2=false;
		this._movimExpService.addDataIndVyV(this.HaciaIndCtrl.value, this.SedeCtrl.value, this.sing_queja['id_queja'] ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					//console.log(retvalue);
					this.registroMvm=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.id_registro_mvm=this.registroMvm[0]['id'];				
				}
				this.flagInfoError2=false;
				this.flagsaved=true;
				this.flagUpdate=true;
				this.LabelUpdateView();
				this.MoverHaciaUpdate();
				this.flagMainUpdate=true;
				this.GetQuejaListPendienteAsignacion();
				this.flagListPendiente=true;
				this.UpdateAfter1sec();
			}else{
				this.id_registro_mvm='';
				console.log('Rest service response ERROR.');
				this.flagInfoError2=true;
				this.SetSecTimerInfoError2();
			}				
			this.BtnVisibleAfterxsec2();
		},(error)=>{
			console.log(error);
			this.flagInfoError2=true;
			this.SetSecTimerInfoError2();
			this.BtnVisibleAfterxsec2();
		});  
	}
	
	GenerarListQueja(){
		let str_queja_list='';
		let i=0;
		for (let thisitem of this.myForm.get('lst_quejasimple')['controls']){
			//console.log(thisitem.value);
			if(thisitem.value){
				if(str_queja_list.length==0)
					str_queja_list=''+this.lst_quejasimple[i].id_queja;
				else
					str_queja_list=str_queja_list+', '+this.lst_quejasimple[i].id_queja;
			}
			i++;
		}
		console.log(str_queja_list.length);
		console.log(this.myForm.get('lst_quejasimple')['controls']);
		return str_queja_list;
	}

	LoadCmbSede(){
		let tempstr='';
		this._sedeservice.getDataColaSedes().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.lst_sede=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.SedeCtrl.setValue(this.lst_sede[0].id_sede);
					this.flagformvisible++;
				}else{
					this.lst_sede=[];
				}
				console.log(this.lst_sede);
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

	openMovimientoExpRegistro(id){
		console.log('entro a openMovimientoExpRegistro');
		this._registrosservice.getMovExpedienteReg(id).subscribe((Data)=>{
		console.log('entro a openRegisto');
			this._registrosservice.FileDownLoadChoose(Data,1);
			},(error)=>{
			console.log(error);
			this.SetSecTimerInfoError();
		});
	}
		
	GetListaMovimientos(){
		this._movimExpService.getDataTop5VyV().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_mov=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_mov);
					this.LoadDisplay();
				}else{
					this.lst_mov=[];
				}
				console.log(retvalue);
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
	
	LoadDisplay(){
		for(let i=0; i<this.lst_mov.length; i++){
			if(this.lst_mov[i].nombre_proveedor != ''){
				this.lst_mov[i].display='Movimiento a Proveedor: '+this.lst_mov[i].nombre_proveedor;
			}else{
				if(this.lst_mov[i].sede_desde != '' && this.lst_mov[i].sede_hasta != ''){
					this.lst_mov[i].display='Movimiento Sede: '+this.lst_mov[i].sede_desde + ' -> '+this.lst_mov[i].sede_hasta;
				}else{
					this.lst_mov[i].display='Movimiento Interno: ' +this.lst_mov[i].dpto_interno_desde + ' -> ' + this.lst_mov[i].dpto_interno_hasta;
				}
			}
		}  
	}	
	UpdateAfter1sec(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.GetListaMovimientos());
	}	
	BtnVisibleAfterxsec(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.btnvisible=true);
	}
	BtnVisibleAfterxsec2(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.btnvisible2=true);
	}
	LabelUpdateView(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdate=false);
	}
	NingunElementoTimer(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagNinguno=false);
	}
	CheckVar(){
		console.log(this.myForm.get('lst_quejasimple')['controls']);
		console.log(this.lst_quejasimple);	
	}
}
