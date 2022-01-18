import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { LSTCMB_AREAS, LSTCMB_SEDES, BaseCmbClass, FrmDetalle_Queja, CmbDptoInterno, FrmSede, LstQuejaSimple, FrmMovimExp, FormResponse } from '../atencion-consumidor-const';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { CatalogoService } from '../shared/catalogo.service'
import { ProveedorService } from '../shared/proveedor.service'
import { QuejaService } from '../shared/queja.service'
import { RegistrosService } from '../shared/registros.service'
import { MovimExpService } from '../movimiento-expediente/movim-exp.service'
import { CompermanenteService } from '../comunicacion-permanente/compermanente.service';
import { MainTableService } from '../tabla-main-at-con/main-table.service'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-movimiento-serv-pub',
  templateUrl: './movimiento-serv-pub.component.html',
  styleUrls: ['./movimiento-serv-pub.component.css'],
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
export class MovimientoServPubComponent implements OnInit {
	lst_mhacia: BaseCmbClass[];
	lst_queja:FrmDetalle_Queja[];
	lst_sede:FrmSede[];
	lst_mov:FrmMovimExp[];
	//lst_quejasimple:LstQuejaSimple[];
	cmb_hacia:CmbDptoInterno[];
	flagInfoError:boolean;
	flagsaved:boolean;
	flagMainUpdate: boolean;
	flagUpdate: boolean;
	btnvisible:boolean;
	htmlQuejaNo;
	htmlLugarActual;htmlidflujo;
	myForm: FormGroup;
	MoverHaciaCtrl: FormControl;
	SedeCtrl: FormControl;
	observacionesCtrl: FormControl;
	lst_quejasimple=[];
	flagformvisible;
	flagEditable:boolean;
	loaderror:boolean;
	Flagestadoqueja:boolean;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _catalogoservice:CatalogoService, public dialogRef: MatDialogRef<MovimientoServPubComponent>, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _mainTableService:MainTableService, private _movimExpService:MovimExpService, private _compermservice:CompermanenteService, private _proveedorService:ProveedorService, private _seguridadService:SeguridadService) { 
		this.htmlQuejaNo='';
		this.Flagestadoqueja=false;
		this.htmlLugarActual='';
		this.htmlidflujo=0;
		this.flagInfoError=false;
		this.flagsaved=false;
		this.btnvisible=true;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagMainUpdate=false;
		this.flagUpdate=false;
		this.flagformvisible=0;
  }

  ngOnInit() {
		this.MoverHaciaCtrl =  new FormControl('',Validators.required);
 	    this.SedeCtrl =  new FormControl('',Validators.required);
		this.observacionesCtrl = new FormControl('');
		
		this.myForm = new FormGroup({
			moverhacia: this.MoverHaciaCtrl,
			sede: this.SedeCtrl,
			observaciones: this.observacionesCtrl,
			lst_quejasimple: new FormArray([])
		});
		this.LoadCmbHacia();
		this.GetQuejaList();
		this.GetListaMovimientos();
		this.SetSecTimerForm();
  }

  private addCheckboxes() {
		console.log(this.myForm.controls.lst_quejasimple);
		this.myForm.get('lst_quejasimple')['controls']=[];
		this.lst_quejasimple.map((o, i) => {
			const control = new FormControl(i === 0); // if first item set to true, else false
			(this.myForm.controls.lst_quejasimple as FormArray).push(control);
		});
  }

  LoadCmbHacia(){
		let tempstr='';
		this._proveedorService.getProveedoresSP().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmb_hacia=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.MoverHaciaCtrl.setValue(this.cmb_hacia[0]['id_proveedor']);
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
  
  ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

	GetQuejaList(){
	  let tempstr='';
	  this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.LoadFrmData(false);
					this.flagformvisible++;
					//verificar estado para permitir edicion en formulario
					if( this._seguridadService.EditableporFlujo(6,this.lst_queja[0]['id_estado_queja']) )
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
				}else{
					console.log('Información de queja no pudo ser obtenida.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
					this.LoadFrmData(true);
				}				
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.LoadFrmData(true);
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

	LoadFrmData(empty:boolean){
		this.htmlQuejaNo=this.data.NoQueja;
		if(!empty){
			if(typeof this.lst_queja["0"] === 'undefined')
				empty=true;
		}
		if(empty){
			this.htmlLugarActual='';
			this.htmlidflujo=0;
		}else{
			this.htmlLugarActual=this.lst_queja[0].nombre_flujo;
			this.htmlidflujo=this.lst_queja[0].id_flujo;
		}
		//console.log('idfl: ',this.htmlidflujo);
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
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	closeDialog(){
		this.ngOnDestroy();
	}

	GuardarMovimiento(){
		if(confirm("¿Está seguro que desea enviar esta queja al proveedor?")) {
			this._movimExpService.addDataSP(this.data.NoQueja, this.MoverHaciaCtrl.value, this.observacionesCtrl.value ).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					this.flagInfoError=false;
					this.flagsaved=true;
					this.btnvisible=false;
					this.UpdateAfter3sec();
					this.BtnVisibleAfter5sec();
					this.flagMainUpdate=true;
					this.flagUpdate=true;
					this.LabelUpdateView();
					this.GetQuejaList();
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
		
	GetListaMovimientos(){
		this._movimExpService.getData(this.data.NoQueja).subscribe((retvalue)=>{
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
	
	UpdateAfter3sec(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.GetListaMovimientos());
	}
	BtnVisibleAfter5sec(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.btnvisible=true);
	}
	LabelUpdateView(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdate=false);
	}
	CheckVar(){
		console.log(this.myForm.get('lst_quejasimple')['controls']);
		console.log(this.lst_quejasimple);	
	}
}
