import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FinalizarService } from '../shared/finalizar.service';
import { QuejaFinalizadaComponent } from '../queja-finalizada/queja-finalizada.component';
import { QuejaService } from '../shared/queja.service';
import { RegistrosService } from '../shared/registros.service';
import { ConcvirtService } from '../verif-concicliacion-virt/concvirt.service';
import { CatalogoService } from '../shared/catalogo.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { BaseCmbClass, CmbMotivoFinalizado, FrmRazonFin, FrmRegComCon } from "../atencion-consumidor-const"
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-archivar-serv-pub',
  templateUrl: './archivar-serv-pub.component.html',
  styleUrls: ['./archivar-serv-pub.component.css'],
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
export class ArchivarServPubComponent implements OnInit {
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
	flagDataProv:boolean;
	myForm: FormGroup;
	detallesCtrl: FormControl;
	lst_concvirt;lbl_respuesta;lst_resolucion;lbl_detalle;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ArchivarServPubComponent>, private _finalizarService:FinalizarService, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _concvirtService: ConcvirtService, private _seguridadService:SeguridadService, private _catalogoService: CatalogoService) { 
		this.flagEditable=false;
		this.flagformvisible=false;
		this.loaderror=false;
		this.flagEdit=true;
		this.flagFinalizada=false;
		this.flagDBError=false;
		this.flagInsertInfo=false;
		this.flagBoton=true;
		this.flagDataProv=false;
		this.lbl_numqueja='';
		this.lst_concvirt='';
		this.lst_resolucion='';
  }

	ngOnInit() {
		this.detallesCtrl =  new FormControl('',Validators.required);		
		this.myForm = new FormGroup({
			detalles: this.detallesCtrl,
		});
		this.LoadCmbRazon();
		this.SetSecTimerForm();
		this.QuejaCheck();
		this.GetListResolucion();
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
					this.LoadFrmInfo(this.lst_queja[0]['quejanumero']);
					this.lbl_numqueja=this.lst_queja[0]['quejanumero'];
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

	LoadCmbRazon(){
		let tempstr='';
		this._finalizarService.getMotivoFinalizado('S').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.list_razon=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
				}else{
					this.list_razon=[];
				}
				this.flagDBError=false;
				console.log(this.list_razon);
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

	GetConcVirt(){
	  let tempstr='';
		this._concvirtService.getData(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null && tempstr!='')	{
					this.lst_concvirt=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					this.lbl_respuesta=this.getResolucionDescripcion(+this.lst_concvirt[0]['resolucion']);
					this.lbl_detalle=this.lst_concvirt[0]['notas'];
					this.flagDBError=false;
					this.flagDataProv=true;
				}
				this.flagformvisible++;
				console.log(this.lst_concvirt, this.lbl_respuesta);
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

	GetListResolucion(){
		let tempstr='';
		this._catalogoService.getData('resolver_queja').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_resolucion=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagDBError=false;
					this.GetConcVirt();
				}else{
					this.lst_resolucion=[];
					console.log('List Resolución vacia.');
					this.flagDBError=true;
					this.SetSecTimerInfoError();
				}
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

	SaveFinalizar(){
   	  this.flagBoton=false;
	  if (this.myForm.valid) {	
			this._finalizarService.addDataSP(this.detallesCtrl.value,this.data.NoQueja).subscribe((retvalue)=>{
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

	closeDialog(){
		this.dialogRef.close();
	}
	
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
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
	TimerForm(){
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	SetSecTimerUpdate(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.QuejaCheck() );
	}
	
}
