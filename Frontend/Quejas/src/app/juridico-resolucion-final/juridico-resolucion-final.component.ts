import { Component, OnInit, ViewChild,ElementRef,Input } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { RegistrosService } from '../shared/registros.service';
import { FileManagerService } from '../shared/file-manager.service';
import { QuejaService } from '../shared/queja.service';
import { LSTCMB_SEDES, LSTCMB_AREAS, LSTCMB_CONCILIADORES, BaseCmbClass,FrmResArchivoConciliacion, FormResponse } from '../atencion-consumidor-const';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { Inject } from '@angular/core';
import { SeguridadService } from "../shared/seguridad.service";
import { AudienciaService } from '../shared/audiencia.service';
import { CatalogoService } from '../shared/catalogo.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-juridico-resolucion-final',
  templateUrl: './juridico-resolucion-final.component.html',
  styleUrls: ['./juridico-resolucion-final.component.css'],
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
export class JuridicoResolucionFinalComponent implements OnInit {
@ViewChild('replink') replink: ElementRef;
  @ViewChild('replink_un_aud') replink_un_aud: ElementRef;
  @ViewChild('attachmentCtrl') 
  ELattachment:ElementRef;
  attachmentCtrl;
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
  listcorrelativo;cmb_resultado;
  MyForm: FormGroup;
  tipoCtrl:FormControl;
  motivoArchivoCtrl:FormControl;
  montoArchivoCtrl:FormControl;
  flagNuevo:boolean;
  flagBoton:boolean;
  flagEdit:boolean;
  flagInsertInfo:boolean;
  flagDeleteInfo:boolean;
  flagMainUpdate:boolean;
  flagValidadorRegistros:boolean;
  flagHabilitado: boolean;
  flagNoesArchivo:boolean;
  currentEditid;
  lbl_actanumero;
  listresaudiencia;
  linkgrid;loc_pagina;
  respinsertupd;lbl_tipostr;lbl_tipo;

  constructor(private _registrosservice:RegistrosService, private _fileManagerService:FileManagerService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<JuridicoResolucionFinalComponent>, private _quejaService: QuejaService, private _seguridadService:SeguridadService, private _audienciaService:AudienciaService, private _catalogoService: CatalogoService) { 
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
	this.flagNuevo=true;
	this.flagBoton=true;
	this.flagEdit=false;
	this.flagInsertInfo=false;
	this.flagDeleteInfo=false;
	this.flagMainUpdate=false;
	this.flagValidadorRegistros=false;
	this.flagHabilitado=true;
	this.flagNoesArchivo=false;
	this.currentEditid=0;
	this.lbl_tipostr='';
	this.lbl_actanumero='123456';
  }

  ngOnInit() {
		this.QuejaCheck();
		this.SetSecTimerForm();
		//this.LoadCorrelativoResol();
		this.LoadCmb();
		this.tipoCtrl =  new FormControl('',Validators.required);
		this.motivoArchivoCtrl =  new FormControl();
		this.montoArchivoCtrl =  new FormControl('0.00');
		this.MyForm = new FormGroup({
			tipo: this.tipoCtrl,
			motivo_archivo: this.motivoArchivoCtrl,
			monto: this.montoArchivoCtrl
		});
		this.ValidadorRegistros();
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
				}else{
					console.log('Informaci??n de queja no pudo ser obtenida.');
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
  GetLink(idqueja){
	  //8 acta v y v
	  let idcategoria=8;
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
  
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerProgressComplete(){
		const source = timer(2000);
		const subscribe = source.subscribe(val => {this.UploadFinished=true;this.progressperc=0;this.CancelEdit();this.SetSecTimerUpdateList(false);});
	}
	SetSecTimerRegistro(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	SetSecTimerUpdateList(param){
		const source = timer(200);
		const subscribe = source.subscribe(val => this.LoadAllResFinal(param));
	}
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerDelete(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDeleteInfo=false);
	}
	SetSecTimerCancelEdit(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.CancelEdit());
	}
	DownloadProgressBar(operation){
		if(operation=='finished'){
			this.SetSecTimerProgressComplete();
		}else{
			this.UploadFinished=false;			
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
					//20 acta resolucion final
					this._fileManagerService.postNewFileLinked(this.ELattachment.nativeElement.files.item(0),this.data.NoQueja, 20, 2,'',this.currentEditid).subscribe(event => {
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
							//this.SetSecTimerCancelEdit();
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: DOCX');
			  }
		  }else{
			  alert('El tama??o m??ximo de archivo es de 1 Mb, por favor revise su archivo.');
		  }
	  }
	}
	
	ResolucionClick(pos, id_imagen_queja){
		//si el tipo no es archivo se manda a traer el acta subida, de lo contrario se trae el registro de archivo
		if(this.listresaudiencia[pos]['tipo']!='20') {
			if(id_imagen_queja!=''){
				var id_tipo_imagen=5; //docx
				this._fileManagerService.getImageInterna(id_imagen_queja).subscribe((Data)=>{
					console.log(id_imagen_queja,id_tipo_imagen);
					this._fileManagerService.FileDownLoadChoose(Data,id_tipo_imagen);
					this.flagInfoError=false;
				},(error)=>{
					console.log(error);
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				});	
			}
		}else{
			//se llama esta funcion cuando el tipo es registro
			this.openResolucionFinal(this.listresaudiencia[pos]['id']);
		}
	}

	/*reportclick1(pos){
		if(this.listresaudiencia[pos]['tipo']=='20') {
			this.openResolucionFinal(this.listresaudiencia[pos]['id']);
		}
     }*/

	 reportclick2(ii)
	 {
		 this.openNotificacionJur('P','resolucionFinal');
	 }
	  
	 reportclick3(ii)
	 {
		 this.openNotificacionJur('C','resolucionFinal');
	 }
	 
	 openNotificacionJur(es_CP,esResultado){
		 console.log('entro a openNotificacionJur');
		 this._registrosservice.openNotificacionJur(this.data.NoQueja,es_CP,esResultado).subscribe((Data)=>{
			 this._registrosservice.FileDownLoadChoose(Data,1);
		 //	this.flagDBError=false;
		 },(error)=>{
			 console.log(error);
		 //	this.flagDBError=true;
			 this.SetSecTimerInfoError();
		 });
	 }
 



	openResolucionFinal(pIdResultAudiencia){
		console.log('entro a openResolucionFinal');
		this._registrosservice.openResolucionFinal(pIdResultAudiencia).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		//	this.flagDBError=false;
		},(error)=>{
			console.log(error);
		//	this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
	}
	
	/*LoadCorrelativoResol(){
		let tempstr='';
		this._audienciaService.getCorrelResolF().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.listcorrelativo=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
					//this.MoverHaciaCtrl.setValue(this.cmb_hacia[0].id_catalogo);
				}else{
					this.listcorrelativo=[];
				}
				console.log(this.listcorrelativo);
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
	
	LoadCmb(){
		let tempstr='';
		this._catalogoService.getData('resolucion_final').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmb_resultado=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
					//this.MoverHaciaCtrl.setValue(this.cmb_hacia[0].id_catalogo);
				}else{
					this.cmb_resultado=[];
				}
				this.LoadAllResFinal(false);
				console.log(this.cmb_resultado);
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
	
	SaveEdit(){
		if(this.flagEdit){
			this.UpdResFinal();
		}else{
			this.SaveResFinal();
		}
	}

	CancelEdit(){
		this.flagEdit=false;
		this.currentEditid=0;
		this.MyForm.reset();
	}

	SaveResFinal(){
		let tempstr='';
	  if (this.MyForm.valid) {	
			this.flagBoton=false;
			this._audienciaService.saveResFinal(this.tipoCtrl.value,this.data.NoQueja,this.motivoArchivoCtrl.value,this.montoArchivoCtrl.value).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.flagInsertInfo=true;
					this.SetSecTimerInsert();
					this.flagMainUpdate=true;
					tempstr=retvalue['value'];
					if(tempstr != null)	{
						this.respinsertupd=JSON.parse('['+tempstr.slice(0, -1) +']');
						this.VerificaNoEsArchivo(this.tipoCtrl.value);
						if(this.tipoCtrl.value!=20){
							this.flagEdit=true;
							this.lbl_tipostr=this.Getresultado(this.tipoCtrl.value);
							this.lbl_tipo=this.tipoCtrl.value;
						}
						/*this.currentEditid=this.respinsertupd[0]['id']; 
						this.lbl_actanumero=this.respinsertupd[0]['correlativo'];*/
						/*console.log(this.respinsertupd);
						console.log(this.currentEditid,this.lbl_actanumero,this.listresaudiencia);*/
					}else{
						console.log('Rest service response ERROR.');
						this.flagInfoError=true;
						this.SetSecTimerInfoError();
					}
					this.SetSecTimerUpdateList(true);
					//this.MyForm.reset();
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}	
				this.flagBoton=true;
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
				this.flagBoton=true;
			});
	  }
	}
	
	VerificaNoEsArchivo(id){
		if(id!=20){
			this.flagNoesArchivo=true;
		}else{
			this.flagNoesArchivo=false;
		}
	}
	
	UpdResFinal(){
	  if (this.MyForm.valid) {	
			this.flagBoton=false;
			this._audienciaService.updResFinal(this.tipoCtrl.value,this.data.NoQueja,this.currentEditid).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList(false);
					this.flagInsertInfo=true;
					this.SetSecTimerInsert();
					this.flagEdit=false;
					this.currentEditid=0;
					this.MyForm.reset();
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}	
				this.flagBoton=true;
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
				this.flagBoton=true;
			});
	  }
	}
	
	DelResFinal(id){
	  if (confirm('??Est?? seguro que desea eliminar este elemento?')) {	
			this._audienciaService.DelResFinal(id,this.data.NoQueja).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList(false);
					this.flagDeleteInfo=true;
					this.SetSecTimerDelete();
					this.flagMainUpdate=true;
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}	
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
			});
	  }
	}
	
	LoadAllResFinal(getlastid){
		let tempstr='';
		this._audienciaService.getallResFinal(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.listresaudiencia=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
					//this.MoverHaciaCtrl.setValue(this.cmb_hacia[0].id_catalogo);
					if(getlastid){
						this.getlastidLoadInfo();
					}
				}else{
					this.listresaudiencia=[];
				}
				this.loadTipoStr();
				console.log(this.listresaudiencia);
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
	
	closeDialog(){
		this.loc_pagina=0;
		this.ngOnDestroy();
	}

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.pagina = this.loc_pagina;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}

  ClickAfterHalfsec(){
	const source = timer(500);
	const subscribe = source.subscribe(val => this.replink.nativeElement.click());
}

ClickResArchUnAudAfterHalfsec(){
	const source = timer(500);
	const subscribe = source.subscribe(val => this.replink_un_aud.nativeElement.click());
}

loadTipoStr(){
	  var leng=this.listresaudiencia.length-1;
	  this.linkgrid=[];
	  for (var pos=0;pos<=leng;pos++) {
		this.listresaudiencia[pos]['tipo_str']=this.Getresultado(this.listresaudiencia[pos]['tipo']);  
		this.linkgrid[pos]=[];
 	  }	 
	  console.log(leng);
		if(this.listresaudiencia.length>0){
			this.flagHabilitado=false;
		}else{
			this.flagHabilitado=true;
		}
	}

	Getresultado(idd:number){
  	  var id=+idd;
	  if (id === null)
		  return null;
	  var found=null;
	  for (var me of this.cmb_resultado) {
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

	GetResAudiencia(id,pos){
		this.flagEdit=true;
		this.currentEditid=id; 
		this.tipoCtrl.setValue(this.listresaudiencia[pos]['tipo']);	
		this.lbl_actanumero=this.listresaudiencia[pos]['correlativo'];
		this.lbl_tipostr=this.Getresultado(this.listresaudiencia[pos]['tipo']);
		this.lbl_tipo=this.listresaudiencia[pos]['tipo'];
		this.VerificaNoEsArchivo(this.listresaudiencia[pos]['tipo']);
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

	reportclick(x,y){
		this.linkgrid[x][y]='1234';
	}
	
	getlastidLoadInfo(){
		var lastid=0;
		  for (var me of this.listresaudiencia) {
			  if (me['id'] > lastid) { 
					this.currentEditid=me['id'];
					this.lbl_actanumero=me['correlativo'];
			  }
		  }	  
	}

	ReadFile(){
		console.log('readfile');
		//8 sin sancion, 9 con sancion, 10 denuncia al mp
		var tipo=0;
		if(this.lbl_tipo==21){
			tipo=8;
		}else if(this.lbl_tipo==22){
			tipo=9;
		}else if(this.lbl_tipo==23){
			tipo=10;
		}
		this._fileManagerService.getPlantilla(tipo).subscribe((Data)=>{
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

}
