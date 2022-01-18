import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FinalizarService } from '../shared/finalizar.service';
import { QuejaFinalizadaComponent } from '../queja-finalizada/queja-finalizada.component';
import { QuejaService } from '../shared/queja.service';
import { RegistrosService } from '../shared/registros.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { BaseCmbClass, CmbMotivoFinalizado, FrmRazonFin, FrmRegComCon } from "../atencion-consumidor-const";
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-archivar-juridico',
  templateUrl: './archivar-juridico.component.html',
  styleUrls: ['./archivar-juridico.component.css'],
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
export class ArchivarJuridicoComponent implements OnInit {
@ViewChild('replink') replink: ElementRef;
	list_razon;	list_poa;list_categoriafin; lst_categoria; elementinfo; frm_data; lbl_numqueja; lst_queja; 
	list_prerazon:CmbMotivoFinalizado[];
	flagformvisible;loaderror;
	flagDBError:boolean;
	flagEdit:boolean;
	flagInsertInfo:boolean;
	flagBoton:boolean;
	flagEditable:boolean;
	flagFinalizada:boolean;
	flagRegError:boolean;
	myForm: FormGroup;
	motivo_poaCtrl: FormControl;
	montoCtrl: FormControl;
	detallesCtrl: FormControl;
	RazonCtrl: FormControl;
	routerlink; linkdescription; 
	registrodata:FrmRegComCon[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ArchivarJuridicoComponent>, private _finalizarService:FinalizarService, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _seguridadService:SeguridadService) { 
		this.flagEditable=false;
		this.flagformvisible=false;
		this.loaderror=false;
		this.flagEdit=true;
		this.flagFinalizada=false;
		this.flagDBError=false;
		this.flagInsertInfo=false;
		this.flagBoton=true;
		this.lbl_numqueja='';
  }

	ngOnInit() {
		this.motivo_poaCtrl =  new FormControl('',Validators.required);
		this.montoCtrl =  new FormControl('',Validators.required);
		this.detallesCtrl =  new FormControl('',Validators.required);
		this.RazonCtrl =  new FormControl('',Validators.required);
		
		this.myForm = new FormGroup({
			motivo_poa: this.motivo_poaCtrl,
			monto: this.montoCtrl,
			detalles: this.detallesCtrl,
			razon: this.RazonCtrl,
		});
		this.montoCtrl.setValue('0.00');
		this.LoadCmbPOA();
		this.SetSecTimerForm();
		this.QuejaCheck();
		this.LoadCmbPreRazon();
	}

	QuejaCheck(){
		let tempstr='';
		this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					this.lbl_numqueja=this.lst_queja[0]['quejanumero'];
					this.LoadFrmInfo(this.lst_queja[0]['quejanumero']);
					//verificar estado para permitir edicion en formulario
					if( this._seguridadService.EditableporFlujo(2,this.lst_queja[0]['id_estado_queja']) )
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

	LoadFrmInfo(quejanumero){
		let tempstr='';
		this._finalizarService.getFinalizado(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.frm_data=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.frm_data[0]['numeroqueja']=quejanumero;
					this.elementinfo=this.frm_data[0];
					this.flagEdit=false;
				}else{
					this.frm_data=null;
					this.elementinfo=null;
				}
				this.flagFinalizada=true;
				this.flagDBError=false;
				console.log(this.frm_data);
				this.flagformvisible++;
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

	LoadCmbPOA(){
		let tempstr='';
		this._finalizarService.getMotivoPOA().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.list_poa=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.motivo_poaCtrl.setValue(this.list_poa[0]['id_motivo_queja_finalizada_poa']);
					//this.LoadFrmInfo();
				}else{
					this.list_poa=[];
				}
				this.flagDBError=false;
				console.log(this.list_poa);
				this.flagformvisible++;
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

	LoadCmbPreRazon(){
		let tempstr='';
		this._finalizarService.getMotivoFinalizado('F').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.list_prerazon=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.RazonCtrl.setValue(this.list_prerazon[0]['id_motivo_queja_finalizada']);
					this.flagformvisible++;
				}else{
					this.list_prerazon=[];
				}
				this.flagDBError=false;
				console.log(this.list_prerazon);
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

	SaveFinalizar(){
   	  this.flagBoton=false;
	  //this.SetSecTimerBoton();
	  if (this.myForm.valid) {	
			this._finalizarService.addDataVyV(this.RazonCtrl.value,this.motivo_poaCtrl.value,this.montoCtrl.value,this.detallesCtrl.value,this.data.NoQueja).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.flagInsertInfo=true;
					this.SetSecTimerInsert();
					this.LoadFrmInfo(this.lbl_numqueja);
					this.SetSecTimerUpdate();
				}else{
					console.log('Rest service response ERROR.');
					this.flagDBError=true;
					this.SetSecTimerInfoError();
				}
				this.SetSecTimerBoton();				
			},(error)=>{
				console.log(error);
				this.flagDBError=true;
				this.SetSecTimerBoton();
			});
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
			this._registrosservice.add_FichaQueja(this.data.NoQueja).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					//this.SetSecTimerUpdateList();
					this.flagRegError=false;
					this.GetRegistro(true);
				}else{
					console.log('Rest service response ERROR.');
					this.flagRegError=true;
					this.SetSecTimerRegError();
				}				
			},(error)=>{
				console.log(error);
				this.flagRegError=true;
				this.SetSecTimerRegError();
			});  
		}
	}
	
	GetRegistro(click:boolean){
	  this._registrosservice.read_FichaQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					//console.log(retvalue);
					this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//this.routerlink='MuestraRegistro/2/'+this.registrodata[0]['id'];
					this.routerlink=this.registrodata[0]['id'];
					this.linkdescription='Ficha Queja '+this.registrodata[0]['id_queja'];
					console.log(this.registrodata);
					if(click)
						this.ClickAfter3sec();
				}else{
					this.routerlink='';
					this.linkdescription='';
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

	closeDialog(){
		this.dialogRef.close();
	}
	
	FormatMonto(){
		this.montoCtrl.setValue(this.decimals(this.montoCtrl.value));
	}

	decimals(cadena) {
		if(cadena ==null || cadena ==''){
			return '0.00';
		}else{
			let conv=Math.round(cadena * 100) / 100;
			return conv.toFixed(2);
		}
	}
	
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
	}
	SetSecTimerRegError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerBoton(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flagBoton=true);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	ClickAfter3sec(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
	}
	TimerForm(){
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	SetSecTimerUpdate(){
		const source = timer(2000);
		const subscribe = source.subscribe(val => this.flagEditable=false );
	}

	openFichaQueja(){
		console.log('entro a openFichaQueja');
			this._registrosservice.getFichaQueja(this.routerlink,' ',' ').subscribe((Data)=>{
				this._registrosservice.FileDownLoadChoose(Data,1);
			},(error)=>{
				console.log(error);
			
			});
	}
	
}
