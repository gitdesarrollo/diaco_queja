import { Component, OnInit, ViewChild,ElementRef,Input } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { RegistrosService } from '../shared/registros.service';
import { FileManagerService } from '../shared/file-manager.service';
import { QuejaService } from '../shared/queja.service';
import { BaseCmbClass, FormResponse } from '../atencion-consumidor-const';
import { FormGroup, FormControl  } from '@angular/forms';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { Inject } from '@angular/core';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-documentar',
  templateUrl: './documentar.component.html',
  styleUrls: ['./documentar.component.css'],
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
export class DocumentarComponent implements OnInit {
@ViewChild('chkbox') 
  ELattachment:ElementRef;
  flagInfoError;
  flagEditable:boolean;
  loaderror:boolean;
  flagUpdate:boolean;
  flagMainUpdate:boolean;
  flagcheck:boolean;
  lst_queja;flagformvisible;

  constructor(private _registrosservice:RegistrosService, private _fileManagerService:FileManagerService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<DocumentarComponent>, private _quejaService: QuejaService, private _seguridadService:SeguridadService) { 
	this.flagInfoError=false;
	this.flagEditable=false;
	this.flagformvisible=0;
	this.loaderror=false;
	this.flagUpdate=false;
	this.flagMainUpdate=false;
	this.flagcheck=false;
  }

  ngOnInit() {
	  this.QuejaCheck();
	  this.SetSecTimerForm();
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
					
					if(this.flagEditable){
						//asignar valor si es editable
						if(this.lst_queja[0].documentado != ''){
							if(this.lst_queja[0].documentado == '1'){
								//no se puede editar porque la queja ya fue confirmada
								//this.SetSecTimerChkBox();
								this.flagEditable=false;
							}
						}
					}
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

	CheckChange(){
		if(this.ELattachment.nativeElement.checked){
			this.flagcheck=true;
		}else{
			this.flagcheck=false;
		}
	}

	SaveDocumentar(bitacora, consumidor, no_queja){
		var valor_int;
		if(this.ELattachment.nativeElement.checked){
			valor_int=1;
		}else{
			valor_int=0;
		}
	  this._quejaService.saveDocumentar(this.data.NoQueja,valor_int).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				this.flagUpdate=true;
				this.SetSecTimerUpdate();
				this.flagMainUpdate=true;
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

	ReadCaratula(){
		console.log('readfile');
		//5 caratula juridico
		this._fileManagerService.getPlantilla(5).subscribe((Data)=>{
			console.log(Data);
			//tipo 5 docx, word document
			this._fileManagerService.FileDownLoadChoose(Data,5);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }  
    
  openCaratula()
  {
    console.log('entro a openCaratula');
		this._registrosservice.getCaratula(this.data.NoQueja).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
			
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }


	ReadListadoChequeo(){
		console.log('readfile2');
		//6 listado chequeo
		this._fileManagerService.getPlantilla(6).subscribe((Data)=>{
			console.log(Data);
			//tipo 5 docx, word document
			this._fileManagerService.FileDownLoadChoose(Data,5);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }  

	SetSecTimerChkBox(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.ELattachment.nativeElement.checked=true);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerUpdate(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdate=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
		
	closeDialog() {
		this.ngOnDestroy();
	}

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}



}
