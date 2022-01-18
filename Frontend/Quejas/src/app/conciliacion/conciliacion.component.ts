import { Component, OnInit, ViewChild,ElementRef,Input } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { RegistrosService } from '../shared/registros.service';
import { FileManagerService } from '../shared/file-manager.service';
import { QuejaService } from '../shared/queja.service';
import { LSTCMB_SEDES, LSTCMB_AREAS, LSTCMB_CONCILIADORES, BaseCmbClass,FrmResArchivoConciliacion, MSG_REGISTRO_DESHABILITADO } from '../atencion-consumidor-const';
import { FormGroup, FormControl  } from '@angular/forms';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { Inject } from '@angular/core';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-conciliacion',
  templateUrl: './conciliacion.component.html',
  styleUrls: ['./conciliacion.component.css'],
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
export class ConciliacionComponent implements OnInit {
  @ViewChild('replink') replink: ElementRef;
  @ViewChild('replink_un_aud') replink_un_aud: ElementRef;
  @ViewChild('attachmentCtrl') 
  ELattachment:ElementRef;
  @ViewChild('attachmentCtrl2') 
  ELattachment2:ElementRef;
  attachmentCtrl;attachmentCtrl2;
  flagInfoError;
  progressperc:Number;progressperc2:Number;
  progressaccent:String;progressaccent2:String;
  UploadFinished:boolean;UploadFinished2:boolean;	
  flagEditable:boolean;
  loaderror:boolean;
  flagRegError:boolean;
  registrodata;linkdescription;idimagenactacon;tipoactacon;
  registrodata2;linkdescription2;idimagenactacon2;tipoactacon2;
  lst_queja;flagformvisible;
  verRegistro;
  verRegistroUnAud;
  registroResArchConc:FrmResArchivoConciliacion[];
  idRegResArchConc;
  idRegResArchUnAudConc;
  flagValidadorRegistros: boolean;
  msg_deshabilitado;

  constructor(private _registrosservice:RegistrosService, private _fileManagerService:FileManagerService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ConciliacionComponent>, private _quejaService: QuejaService, private _seguridadService:SeguridadService) { 
	this.flagInfoError=false;
	this.flagEditable=false;
	this.progressperc=0;
    this.progressaccent="primary";
    this.UploadFinished=true;
	this.linkdescription='';
	this.progressperc2=0;
    this.progressaccent2="primary";
    this.UploadFinished2=true;
	this.linkdescription2='';
	this.flagformvisible=0;
	this.loaderror=false;
	this.verRegistro=false;
	this.verRegistroUnAud=false;
	this.flagRegError=false;
	this.idRegResArchConc=0;
	this.idRegResArchUnAudConc=0;
	this.flagValidadorRegistros=false;
	this.msg_deshabilitado=MSG_REGISTRO_DESHABILITADO;
  }

  ngOnInit() {
	  this.GetLink(this.data.NoQueja);
	  this.GetLink2(this.data.NoQueja);
	  this.QuejaCheck();
	  this.GetRegistroArchConc(false);
	  this.GetRegistroArchUnAudConc(false);
      this.SetSecTimerForm();
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

	ReadFile(){
		console.log('readfile');
		//1 plantilla acta conciliacion
		this._fileManagerService.getPlantilla(1).subscribe((Data)=>{
			console.log(Data);
			//tipo 5 docx, word document
			this._fileManagerService.FileDownLoadChoose(Data,6);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }  

  downLoadActaConciliacion(){
	console.log('entro a downLoadActaConciliacion : ');
	this._registrosservice.addGet_ActaConciliacion(this.data.NoQueja).subscribe((Data)=>{
	console.log('entro a downLoadActaConciliacionII');
		this._registrosservice.FileDownLoadChoose(Data,6);
		this.flagRegError=false;
	},(error)=>{
		console.log(error);
		this.flagRegError=true;
		this.SetSecTimerRegistro();
	});
}
  ReadLink(){
		this._fileManagerService.getImageInterna(this.idimagenactacon).subscribe((Data)=>{
			console.log(this.idimagenactacon,this.tipoactacon);
			this._fileManagerService.FileDownLoadChoose(Data,this.tipoactacon);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }  
  ReadLink2(){
		this._fileManagerService.getImageInterna(this.idimagenactacon2).subscribe((Data)=>{
			console.log(this.idimagenactacon2,this.tipoactacon2);
			this._fileManagerService.FileDownLoadChoose(Data,this.tipoactacon2);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }
  GetLink(idqueja){
	  //4 acta de conciliacion
	  let idcategoria=4;
	  this._fileManagerService.getCatFile(idqueja, idcategoria).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.linkdescription=this.registrodata[0]['descripcion_imagen_otros'];
					this.idimagenactacon=this.registrodata[0]['id_imagen_interno'];
					this.tipoactacon=this.registrodata[0]['id_tipo_imagen'];
					console.log(this.registrodata);
				}else{
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
  GetLink2(idqueja){
	  //6 acta de conciliacion scan
 	  let idcategoria=6;
	  this._fileManagerService.getCatFile(idqueja, idcategoria).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.registrodata2=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.linkdescription2=this.registrodata2[0]['descripcion_imagen_otros'];
					this.idimagenactacon2=this.registrodata2[0]['id_imagen_interno'];
					this.tipoactacon2=this.registrodata2[0]['id_tipo_imagen'];
					console.log(this.registrodata2);
				}else{
					this.linkdescription2='';
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
  
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerProgressComplete(){
		const source = timer(2000);
		const subscribe = source.subscribe(val => {this.UploadFinished=true;this.progressperc=0;});
	}
	SetSecTimerProgressComplete2(){
		const source = timer(2000);
		const subscribe = source.subscribe(val => {this.UploadFinished2=true;this.progressperc2=0;});
	}
	SetSecTimerRegistro(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	DownloadProgressBar(operation){
		if(operation=='finished'){
			this.SetSecTimerProgressComplete();
		}else{
			this.UploadFinished=false;			
		}
	}
	DownloadProgressBar2(operation){
		if(operation=='finished'){
			this.SetSecTimerProgressComplete2();
		}else{
			this.UploadFinished2=false;			
		}
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
	
	UploadFile(){
		console.log(this.ELattachment);
	  if(this.ELattachment.nativeElement.files.item(0)){
		  console.log(this.ELattachment.nativeElement.files.item(0));
		  if( this._fileManagerService.VerifyFileSizeInt(this.ELattachment.nativeElement.files.item(0).size) ){
			  var tipo_imagen=this._fileManagerService.VerifyDOCX(this.ELattachment.nativeElement.files.item(0).type, this.ELattachment.nativeElement.files.item(0).name);
			  if(tipo_imagen){
				  console.log(this.ELattachment.nativeElement.files[0]);
					this.DownloadProgressBar('start');
					//4 - acta de conciliacion
					this._fileManagerService.postNewFile(this.ELattachment.nativeElement.files.item(0),this.data.NoQueja, 4,1).subscribe(event => {
						if (event.type === HttpEventType.UploadProgress) {
							this.progressperc=Math.round(100 * event.loaded / event.total);
						} else if (event instanceof HttpResponse) {
						  console.log(event);
						  this.DownloadProgressBar('finished');
						  if(event['body']['reason'] != 'OK'){
							  console.log('Falla al subir el archivo');
							  this.flagInfoError=true;
							  this.SetSecTimerInfoError();
						  }else{
								this.GetLink(this.data.NoQueja);
						  }							  
						}
					  },(error)=>{
							console.log(error);
							this.flagInfoError=true;
							this.SetSecTimerInfoError();
							this.DownloadProgressBar('finished');
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: DOCX');
			  }
		  }else{
			  alert('El tamaño máximo de archivo es de 1 Mb, por favor revise su archivo.');
		  }
	  }
	}
	UploadFile2(){
	  if(this.ELattachment2.nativeElement.files.item(0)){
		  console.log(this.ELattachment2.nativeElement.files.item(0));
		  if( this._fileManagerService.VerifyFileSize(this.ELattachment2.nativeElement.files.item(0).size) ){
			  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.ELattachment2.nativeElement.files.item(0).type, this.ELattachment2.nativeElement.files.item(0).name);
			  if(tipo_imagen){
				  console.log(this.ELattachment2.nativeElement.files[0]);
					this.DownloadProgressBar2('start');
					this._fileManagerService.postNewScan(this.ELattachment2.nativeElement.files.item(0),this.data.NoQueja,6,tipo_imagen,1).subscribe(event => {
						if (event.type === HttpEventType.UploadProgress) {
							this.progressperc2=Math.round(100 * event.loaded / event.total);
						} else if (event instanceof HttpResponse) {
						  console.log(event);
						  this.DownloadProgressBar2('finished');
						  if(event['body']['reason'] != 'OK'){
							  console.log('Falla al subir el archivo');
							  this.flagInfoError=true;
							  this.SetSecTimerInfoError();
						  }else{
							  this.GetLink2(this.data.NoQueja);
						  }
							  
						}
					  },(error)=>{
							console.log(error);
							this.flagInfoError=true;
							this.SetSecTimerInfoError();
							this.DownloadProgressBar('finished');
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: PDF, PNG, GIF y JPG.');
			  }
		  }else{
			  alert('El tamaño máximo de archivo es de 1 Mb, por favor revise su archivo.');
		  }
	  }
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

  /*openActaConciliatoriaAtCtr()
  {
    console.log('entro a openActaConciliatoriaAtCtr');
		this._registrosservice.getActaConciliatoriaAtContr(this.repLegalCtrl.value,this.repLegalDpiCtrl.value,this.calidadActuaRepLegalCtrl.value,this.acreditaRepLegalCtrl.value,this.consNombreCtrl.value, this.dpiConsCtrl.value, this.calidadActuaConsCtrl.value, this.acreditaConCtrl.value, this.nombreConciliadorCtrl.value,this.horaIniCtrl.value,this.horaFinCtrl.value, this.fechaConciliacionCtrl.value,  this.punto2Ctrl.value, this.punto3Ctrl.value,this.punto4Ctrl.value,this.punto5Ctrl.value,this.punto6Ctrl.value ).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
			
		},(error)=>{
			console.log(error);
		
		});
  }*/
  GetRegistroArchConc(click:boolean){
	this._registrosservice.read_ResArchivoConciliacion(this.data.NoQueja).subscribe((retvalue)=>{
		  if(retvalue["reason"] == 'OK'){
			  var tempstr=retvalue['value'];
			  if(tempstr != null)	{
				  //console.log(retvalue);
				  this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				  //this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
				 this.idRegResArchConc=this.registrodata[0]['id'];
				 this.verRegistro=true;
				  console.log(this.registrodata);
				  if(click)
					  this.ClickAfterHalfsec();
			  }else{
				this.idRegResArchConc='';
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

	GetRegistroArchUnAudConc(click:boolean){
		this._registrosservice.read_ResArchUnAudConciliacion(this.data.NoQueja).subscribe((retvalue)=>{
			  if(retvalue["reason"] == 'OK'){
				  var tempstr=retvalue['value'];
				  if(tempstr != null)	{
					  //console.log(retvalue);
					  this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					  //this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
					 this.idRegResArchUnAudConc=this.registrodata[0]['id'];
					 this.verRegistroUnAud=true;
					  console.log(this.registrodata);
					  if(click)
						  this.ClickResArchUnAudAfterHalfsec();
				  }else{
					this.idRegResArchUnAudConc='';
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
	


  GenerarRegistroResArchivoConciliacion(){
	var approved=false;
	if(this.linkdescription != ''){
		if(confirm("¿Está seguro que quiere actualizar este registro con la información mas reciente?")) {
			approved=true;
		}
	}else{
		approved=true;
	}
	if(approved){
		this._registrosservice.add_ResArchivoConciliacion(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				//this.registroResArchConc=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				//this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
				//this.idRegResArchConc=this.registroResArchConc[0]['id'];
				this.GetRegistroArchConc(true);
				console.log('el id del reg de resarchcon:'+this.idRegResArchConc);
				this.flagRegError=false;
				this.verRegistro=true;

			}else{
				console.log('Rest service response ERROR.');
				this.flagRegError=true;
				this.SetSecTimerRegistro();
				this.verRegistro=false;
			}				
		},(error)=>{
			console.log(error);
			this.flagRegError=true;
			this.SetSecTimerRegistro();
		});  
	}
}


GenerarRegistroResArchUnAudConciliacion(){
	var approved=false;
	if(this.linkdescription != ''){
		if(confirm("¿Está seguro que quiere actualizar este registro con la información mas reciente?")) {
			approved=true;
		}
	}else{
		approved=true;
	}
	if(approved){
		this._registrosservice.add_ResArchivoUnAudConciliacion(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				//this.registroResArchConc=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				//this.routerlink='MuestraRegistro/1/'+this.registrodata[0]['id'];
				//this.idRegResArchConc=this.registroResArchConc[0]['id'];
				this.GetRegistroArchUnAudConc(true);
				console.log('el id del reg de resarchUnAudcon:'+this.idRegResArchUnAudConc);
				this.flagRegError=false;
				this.verRegistroUnAud=true;

			}else{
				console.log('Rest service response ERROR.');
				this.flagRegError=true;
				this.SetSecTimerRegistro();
				this.verRegistroUnAud=false;
			}				
		},(error)=>{
			console.log(error);
			this.flagRegError=true;
			this.SetSecTimerRegistro();
		});  
	}
}



  openResArchivoConciliacion()
  {
    console.log('entro a openResArchivoConciliacion');
		this._registrosservice.getResArchivoConciliacion(this.idRegResArchConc).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
			
		},(error)=>{
			console.log(error);
			this.flagRegError=true;
			this.SetSecTimerRegistro();
		});
  }
  
  openResArchivoUnicaAudConciliacion()
  {
    console.log('entro a openResArchivoUnicaAudConciliacion');
		this._registrosservice.getResArchivoUnicaAudConciliacion(this.idRegResArchUnAudConc).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
			
		},(error)=>{
			console.log(error);
			this.flagRegError=true;
			this.SetSecTimerRegistro();
		});
  }

  ClickAfterHalfsec(){
	const source = timer(500);
	const subscribe = source.subscribe(val => this.replink.nativeElement.click());
}

ClickResArchUnAudAfterHalfsec(){
	const source = timer(500);
	const subscribe = source.subscribe(val => this.replink_un_aud.nativeElement.click());
}


}
