import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { VisitaCampoService } from './visita-campo.service';
import { UsuarioService } from '../shared/usuario.service';
import { RegistrosService } from '../shared/registros.service';
import { QuejaService } from '../shared/queja.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import { Inject } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { LSTCMB_VISITAPOR, BaseCmbClass, FrmVisitaCampo, FrmRegComCon, MSG_REGISTRO_DESHABILITADO } from '../atencion-consumidor-const';
import { DatePipe } from '@angular/common';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-visita-campo',
  templateUrl: './visita-campo.component.html',
  styleUrls: ['./visita-campo.component.css'],
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
export class VisitaCampoComponent implements OnInit {
  ListVisitaCampo:FrmVisitaCampo[];
  //ListBitacora=LSTFKBITACORA;
  @ViewChild('replink') replink: ElementRef;
  myForm: FormGroup;
  Hecha_porCtrl: FormControl;
  FechaCtrl: FormControl;
  ObservacionesCtrl: FormControl;
  flagInsertInfo: boolean;
  flagUpdateInfo: boolean;
  flagDeleteInfo: boolean;
  flagEdit: boolean;
  flagInfoError: boolean;
  flagCopyConsumidor: boolean;
  flagRegError:boolean;
  currentEditid;
  lst_visitapor;
  routerlink;
  linkdescription;
  registrodata:FrmRegComCon[];
  flagformvisible;lst_queja;
  flagEditable:boolean;
  loaderror:boolean;
  flagValidadorRegistros: boolean;
  msg_deshabilitado;
  
  constructor(private _visitaCampoService:VisitaCampoService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<VisitaCampoComponent>, private _usuarioService:UsuarioService, private datePipe: DatePipe, private _registrosService:RegistrosService, private _quejaService: QuejaService, private _seguridadService:SeguridadService) { 
		this.flagInsertInfo = false;
		this.flagUpdateInfo = false;
		this.flagDeleteInfo = false;
		this.flagInfoError = false;
		this.flagEdit = false;
		this.currentEditid = 0;
		this.flagCopyConsumidor=true;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagRegError=false;
		this.flagformvisible=0;
		this.flagValidadorRegistros=false;
		this.msg_deshabilitado=MSG_REGISTRO_DESHABILITADO;
  }
	  
	  /*ngAfterViewInit(){
		console.log('length: '+this.ListBitacora );
	  }*/

  ngOnInit() {	  
	  this.Hecha_porCtrl =  new FormControl('',Validators.required);
	  this.FechaCtrl =  new FormControl('',Validators.required);
	  this.ObservacionesCtrl =  new FormControl('',Validators.required);
	  this.myForm = new FormGroup({
		hecha_por: this.Hecha_porCtrl,
		fecha: this.FechaCtrl,
		observaciones: this.ObservacionesCtrl,
	  });
	  
	  //console.log(this.data);
	  //this.lst_visitapor=LSTCMB_VISITAPOR;
	  console.log(this.data);
	  this.GetHechaPorList();
	  this.GetRegistro(false);
	  this.QuejaCheck();
	  this.SetSecTimerForm();
	  this.ValidadorRegistros();
	  this.GetVisitaCampoList(this.data.NoQueja);
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
					if( this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']) )
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
  
  openResArchivoVisitaCampo()
  {
    console.log('entro a openCedulaNotiProv');
		if(this.data.Flujo==1)
		{
		this._registrosService.getResArchivoVisitaCampo(this.routerlink,'a').subscribe((Data)=>{
			this._registrosService.FileDownLoadChoose(Data,1);
			//this.flagDBError=false;
		},(error)=>{
			console.log(error);
		//	this.flagDBError=true;
		//	this.SetSecTimerInfoError();
		});}
		else {
			this._registrosService.getResArchivoVisitaCampoVerif(this.routerlink).subscribe((Data)=>{
				this._registrosService.FileDownLoadChoose(Data,1);
				//this.flagDBError=false;
			},(error)=>{
				console.log(error);
			//	this.flagDBError=true;
			//	this.SetSecTimerInfoError();
			});
		  }
  }
  
  onSubmit() {
	  if (this.myForm.valid && !this.flagEdit) {
		//console.log(this.myForm.value);
		this.SaveVisitaCampo();
	  }
  }

  ConsumidorTouched(){
	this.flagCopyConsumidor=false;
  }	  

  GetHechaPorList(){
	  this._usuarioService.getData(this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				this.lst_visitapor=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				this.Hecha_porCtrl.setValue(this.lst_visitapor[0]['id_usuario']);
				console.log(this.lst_visitapor);
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
  
  GetVisitaCampoList(idqueja){
	  this._visitaCampoService.getData(idqueja, this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				let tempvalue=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				if(tempvalue!=null){
					this.ListVisitaCampo=tempvalue;
					console.log(this.ListVisitaCampo);
					this.ResetID();
				}else{
					this.ListVisitaCampo=[];
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
  
  SaveVisitaCampo(){
	  this._visitaCampoService.addData(this.Hecha_porCtrl.value, this.FechaCtrl.value, this.ObservacionesCtrl.value, this.data.NoQueja, this.data.Flujo ).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				this.SetSecTimerUpdateList();
				this.flagInsertInfo=true;
				this.SetSecTimerInsert();
				this.myForm.reset();
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
  
  EnableEdit(id, fecha, hecha_por, observaciones){
	  //console.log('length: '+this.ListBitacora.length );
	  this.flagEdit=true;
	  this.currentEditid=id;
	  this.FechaCtrl.setValue(this.convertDate(fecha));	
  	  this.Hecha_porCtrl.setValue(hecha_por);
	  this.ObservacionesCtrl.setValue(observaciones);		  
  }
  
  convertDate(date){
	return this.datePipe.transform(date,"yyyy-MM-dd");//+'T00:00:00Z';
  }
  
	CancelEdit(){
		this.flagEdit=false;
		this.currentEditid = 0;
		this.CleanForm();
	}
	
  DeleteVisitaCampo(id){
		if(confirm("¿Está seguro que quiere eliminar este elemento?")) {
			this._visitaCampoService.deleteData(id,this.data.NoQueja, this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList();
					this.flagDeleteInfo=true;
					this.SetSecTimerDelete();
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

  UpdateVisitaCampo(){
	  if (this.myForm.valid) {	
			console.log('id actual update: '+this.currentEditid);
			this._visitaCampoService.editData(this.currentEditid, this.Hecha_porCtrl.value, this.FechaCtrl.value, this.ObservacionesCtrl.value, this.data.NoQueja, this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList();
					this.flagUpdateInfo=true;
					this.SetSecTimerUpdate();
					this.CancelEdit();
					this.myForm.reset();
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
			this._registrosService.add_resvisitacampo(this.data.NoQueja,this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					//this.SetSecTimerUpdateList();
					this.flagRegError=false;
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
	
	  GetRegistro(click:boolean){
		  console.log('valor flujo '+this.data.Flujo);
	  this._registrosService.read_resvisitacampo(this.data.NoQueja,this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null)	{
					//console.log(retvalue);
					this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
					this.routerlink=this.registrodata[0]['id'];
					this.linkdescription=this.registrodata[0]['codigo'];
					console.log(this.registrodata);
					if(click)
						this.ClickAfter1sec();
				}else{
					this.routerlink='';
					this.linkdescription='';
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

  ResetID(){
		for(let i=0; i<this.ListVisitaCampo.length; i++){
			this.ListVisitaCampo[i].correlativo = i+1; //use i instead of 0
		}  
  }

	SetSecTimerUpdateList(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.GetVisitaCampoList(this.data.NoQueja));
	}
	
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerUpdate(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdateInfo=false);
	}
	SetSecTimerDelete(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDeleteInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerRegistro(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<5){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	ClickAfter1sec(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.replink.nativeElement.click());
	}
	
	CleanForm(){
		this.Hecha_porCtrl.setValue("");	
		this.FechaCtrl.setValue("");	
		this.ObservacionesCtrl.setValue("");	
	}
	
	closeDialog() {
		this.dialogRef.close();
	}
	
  
}
