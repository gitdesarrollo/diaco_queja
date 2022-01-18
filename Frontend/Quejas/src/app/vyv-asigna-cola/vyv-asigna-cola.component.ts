import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FinalizarService } from '../shared/finalizar.service';
import { ConfiguracionService } from '../shared/configuracion.service';
import { QuejaService } from '../shared/queja.service';
import { RegistrosService } from '../shared/registros.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { BaseCmbClass, CmbMotivoFinalizado, FrmRazonFin, FrmRegComCon, MSG_REGISTRO_DESHABILITADO } from "../atencion-consumidor-const";
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-vyv-asigna-cola',
  templateUrl: './vyv-asigna-cola.component.html',
  styleUrls: ['./vyv-asigna-cola.component.css'],
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
export class VyvAsignaColaComponent implements OnInit {
	@ViewChild('replink') replink: ElementRef;
	frm_data; lst_queja; list_colas;
	list_prerazon:CmbMotivoFinalizado[];
	flagformvisible;loaderror;
	flagDBError:boolean;
	flagEdit:boolean;
	flagBoton:boolean;
	flagEditable:boolean;
	flagRegError:boolean;
	flagRegErrorFicha:boolean;
	myForm: FormGroup;
	colaCtrl: FormControl;
	routerlink; linkdescription; 
	registrodata:FrmRegComCon[];
	flagValidadorRegistros: boolean;
	msg_deshabilitado;lbl_mensaje;list_inicial;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<VyvAsignaColaComponent>, private _configuracionService:ConfiguracionService, private _quejaService: QuejaService, private _registrosservice:RegistrosService, private _seguridadService:SeguridadService) { 
		this.flagEditable=false;
		this.flagformvisible=false;
		this.loaderror=false;
		this.flagEdit=true;
		this.flagDBError=false;
		this.flagBoton=true;
		this.flagValidadorRegistros=false;
		this.msg_deshabilitado=MSG_REGISTRO_DESHABILITADO;
		this.lbl_mensaje='Edición deshabilitada';
  }

	ngOnInit() {
		this.colaCtrl =  new FormControl('',Validators.required);
		
		this.myForm = new FormGroup({
			cola: this.colaCtrl,
		});
		this.LoadCmbCola();
		this.SetSecTimerForm();
		this.QuejaCheck();
		this.GetInicial();
		this.ValidadorRegistros();
	}

	ValidadorRegistros(){
		let tempstr=null;
		this._seguridadService.ValidadorRegistros(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK' || Data['reason'] == 'INVALID'){
				if(Data['reason'] == 'OK'){
					this.flagValidadorRegistros=true;
				}else{
					this.flagValidadorRegistros=false;
				}
				console.log(Data);
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

	QuejaCheck(){
		let tempstr='';
		this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					//verificar estado para permitir edicion en formulario
					if(this.lst_queja[0]['id_estado_queja']==328){
						this.flagEdit=true;
					}else{
						this.flagEdit=false;
					}
					if(this._seguridadService.EditableporFlujo(3,this.lst_queja[0]['id_estado_queja']))
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

	LoadCmbCola(){
		let tempstr='';
		this._configuracionService.getColaTodoxFlujo(3).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.list_colas=JSON.parse('['+tempstr.slice(0, -1) +']');
					//this.LoadFrmInfo();
				}else{
					this.list_colas=[];
				}
				this.flagDBError=false;
				console.log(this.list_colas);
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

	GetInicial(){
		let tempstr='';
		this._configuracionService.getVyvAsignaInicial(this.data.NoQueja).subscribe((Data)=>{
			console.log(Data);
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.list_inicial=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.lbl_mensaje='Esta queja fué asignada a cola: '+ this.GetnombreCola(this.list_inicial[0]['id_tipo_cola']);
				}else{
					this.list_inicial=[];
				}
				this.flagDBError=false;
				console.log(this.list_inicial);
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

	Save(){
   	  this.flagBoton=false;
	  //this.SetSecTimerBoton();
	  if (this.myForm.valid) {	
			this._configuracionService.saveAsignaColaInicial(this.colaCtrl.value,this.data.NoQueja).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerGetList();
					this.flagEdit=false;
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
					this.flagRegErrorFicha=true;
					this.SetSecTimerRegErrorFicha();
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
					console.log('routerlink:'+this.routerlink);
					this.linkdescription='Ficha Queja '+this.registrodata[0]['id_queja'];
					console.log(this.registrodata);
					if(click)
						this.ClickAfterxsec();
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
	
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
	}
	SetSecTimerRegError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	SetSecTimerRegErrorFicha(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegErrorFicha=false);
	}
	SetSecTimerBoton(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.flagBoton=true);
	}
	SetSecTimerGetList(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.GetInicial());
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	ClickAfterxsec(){
		const source = timer(100);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
	}
	TimerForm(){
		if(this.flagformvisible<4){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}

	GetnombreCola(idd:number){
  	  var id=+idd;
	  if (id === null)
		  return null;
	  var found=null;
	  for (var me of this.list_colas) {
		  if (me['id_tipo_cola'] === id) { 
				found=me['nombre'];
		  }
   	  }	  
	  if (found != null){
		  return found;
	  }else{
		  return null;
	  }
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
