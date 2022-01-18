import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';
import { LSTCMB_AREAS, LSTCMB_SEDES, BaseCmbClass, FrmDetalle_Queja, CmbDptoInterno, FrmSede, LstQuejaSimple, FrmMovimExp, FormResponse } from '../atencion-consumidor-const';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { CatalogoService } from '../shared/catalogo.service'
import { SedeService } from '../shared/sede.service'
import { QuejaService } from '../shared/queja.service'
import { RegistrosService } from '../shared/registros.service'
import { MovimExpService } from '../movimiento-expediente/movim-exp.service'
import { FinalizarService } from '../shared/finalizar.service'
import { CompermanenteService } from '../comunicacion-permanente/compermanente.service';
import { MainTableService } from '../tabla-main-at-con/main-table.service'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-movimiento-verificacion',
  templateUrl: './movimiento-verificacion.component.html',
  styleUrls: ['./movimiento-verificacion.component.css'],
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
export class MovimientoVerificacionComponent implements OnInit {
	lst_mhacia: BaseCmbClass[];
	lst_queja:FrmDetalle_Queja[];
	lst_sede:FrmSede[];
	lst_mov:FrmMovimExp[];
	//lst_quejasimple:LstQuejaSimple[];
	cmb_hacia:CmbDptoInterno[];
	cmb_hacia2:CmbDptoInterno[];
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
	flagValidaArchivo:boolean;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _sedeservice:SedeService, private _catalogoservice:CatalogoService, public dialogRef: MatDialogRef<MovimientoVerificacionComponent>, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _mainTableService:MainTableService, private _movimExpService:MovimExpService, private _compermservice:CompermanenteService, private _seguridadService:SeguridadService, private _finalizarService:FinalizarService) { 
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
		this.flagValidaArchivo=false;
  }

  ValidadorArchivo(){
		let tempstr='';
		this._finalizarService.getValidadorArchivar(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				this.flagValidaArchivo=true;
			}else{
				this.flagValidaArchivo=false;
			}
			this.flagformvisible++;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }

  ngOnInit() {
		this.MoverHaciaCtrl =  new FormControl('',Validators.required);
 	    this.SedeCtrl =  new FormControl('',Validators.required);
		this.observacionesCtrl =  new FormControl('');  
		  
		this.myForm = new FormGroup({
			moverhacia: this.MoverHaciaCtrl,
			sede: this.SedeCtrl,
			observaciones: this.observacionesCtrl,
			lst_quejasimple: new FormArray([])
		});
		//this.LoadCmbHacia2();
		this.GetQuejaList();
		this.GetListaMovimientos();
		this.SetSecTimerForm();
		this.MoverHaciaCtrl.setValue(91);
		this.ValidadorArchivo();
  }

  private addCheckboxes() {
		console.log(this.myForm.controls.lst_quejasimple);
		this.myForm.get('lst_quejasimple')['controls']=[];
		this.lst_quejasimple.map((o, i) => {
			const control = new FormControl(i === 0); // if first item set to true, else false
			(this.myForm.controls.lst_quejasimple as FormArray).push(control);
		});
  }

  /*LoadCmbHacia2(){
		let tempstr='';
		this._catalogoservice.getDptoInternoMovHaciaSinJefe().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmb_hacia2=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.MoverHaciaCtrl.setValue(this.cmb_hacia2[0].id_departamento_interno);
					this.flagformvisible++;
				}else{
					this.cmb_hacia2=[];
				}
				console.log(this.cmb_hacia2);
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
  }*/
  
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
					
					if(this.lst_queja[0]['id_estado_queja']>=329 && this.lst_queja[0]['id_estado_queja']<=333)
						this.Flagestadoqueja=true;
					
					//verificar estado para permitir edicion en formulario
					if( this._seguridadService.EditableporFlujo(3,this.lst_queja[0]['id_estado_queja']) )
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
			//this.LoadCmbSede(this.lst_queja[0].id_diaco_sede);
		}
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
		if(confirm("¿Está seguro que desea mover esta queja?")) {
			this.btnvisible=false;
			this._movimExpService.addDataPreVyV(this.data.NoQueja, this.MoverHaciaCtrl.value, this.observacionesCtrl.value ).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					this.flagInfoError=false;
					this.flagsaved=true;
					this.btnvisible=false;
					this.UpdateAfter3sec();
					this.flagMainUpdate=true;
					this.flagUpdate=true;
					this.LabelUpdateView();
					this.GetQuejaList();
					this.BtnVisibleAfterxsec();
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
					this.BtnVisibleAfterxsec();
				}				
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.BtnVisibleAfterxsec();
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
	
	UpdateAfter3sec(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.GetListaMovimientos());
	}
	BtnVisibleAfterxsec(){
		const source = timer(1000);
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
