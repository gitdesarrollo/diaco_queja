import { Component, OnInit,ViewChild,ElementRef,Input  } from '@angular/core';
import { LSTFILTRO_RESOLUCION, BaseCmbClass, List_Images } from '../atencion-consumidor-const';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FileManagerService } from '../shared/file-manager.service'
import { MAT_DIALOG_DATA } from '@angular/material';
import { Inject } from '@angular/core';
import { timer, Observable } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-file-read-upload',
  templateUrl: './file-read-upload.component.html',
  styleUrls: ['./file-read-upload.component.css'],
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
export class FileReadUploadComponent implements OnInit {
  @Input() id_queja;
  @Input() id_flujo;
  @Input() delete_button:boolean;
  @Input() upload_button:boolean;
  @ViewChild('attachmentCtrl') ELattachment:ElementRef;	
  @ViewChild('filedpi') filedpiCtrl:ElementRef;
  @ViewChild('filefactura') filefacCtrl:ElementRef;
  @ViewChild('replinkdpi') RepLinkdpiCtrl:ElementRef;	
  @ViewChild('replinkfac') RepLinkfacCtrl:ElementRef;	
  flagDBError:boolean;
  flagInsertInfo:boolean;
  flagDeleteInfo:boolean;
  attachmentCtrl; list_conf; ImageList;
  ListImages:List_Images[];
  progressperc:Number;
  progressaccent:String;
  UploadFinished:boolean;
  progressperc_dpi:Number;
  progressaccent_dpi:String;
  UploadFinished_dpi:boolean;
  progressperc_fac:Number;
  progressaccent_fac:String;
  UploadFinished_fac:boolean;
  flagObligatorios:boolean;
  
  constructor(private _fileManagerService:FileManagerService) { }

  ngOnInit() {
	  this.flagObligatorios=false;
  	  this.flagInsertInfo=false;
	  this.flagDeleteInfo=false;
	  this.flagDBError=false;
	  this.ReadImageList();
	  this.progressperc=0;
	  this.progressaccent="primary";
	  this.UploadFinished=true;
	  this.progressperc_dpi=0;
	  this.progressaccent_dpi="primary";
	  this.UploadFinished_dpi=true;
	  this.progressperc_fac=0;
	  this.progressaccent_fac="primary";
	  this.UploadFinished_fac=true;
	  this.GetListObligatorios();
  }

  ReadFile(id_imagen_queja,id_tipo_imagen){
		this._fileManagerService.getImage(id_imagen_queja).subscribe((Data)=>{
			console.log(id_imagen_queja,id_tipo_imagen);
			this._fileManagerService.FileDownLoadChoose(Data,id_tipo_imagen);
			this.flagDBError=false;
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
  }  

	DeleteFile(id){
		if(confirm("¿Está seguro que quiere eliminar este elemento?")) {
			this._fileManagerService.deleteImage(id).subscribe((retvalue)=>{
				console.log(retvalue);
				this.flagDBError=false;
				this.flagDeleteInfo=true;
				this.SetSecTimerUpdateList();
				this.SetSecTimerDelete();
			},(error)=>{
				console.log(error);
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			});
		}
	}

  UploadFile(){
	  if(this.ELattachment.nativeElement.files.item(0)){
		  console.log(this.ELattachment.nativeElement.files.item(0));
		  if( this._fileManagerService.VerifyFileSize(this.ELattachment.nativeElement.files.item(0).size) ){
			  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.ELattachment.nativeElement.files.item(0).type, this.ELattachment.nativeElement.files.item(0).name);
			  if(tipo_imagen){
				  console.log(this.ELattachment.nativeElement.files[0]);
					this.DownloadProgressBar('start',1);
					this._fileManagerService.postNewImage(this.ELattachment.nativeElement.files.item(0),this.id_queja,tipo_imagen,this.id_flujo,1).subscribe(event => {
						if (event.type === HttpEventType.UploadProgress) {

						  // calculate the progress percentage
						  //const percentDone = Math.round(100 * event.loaded / event.total);
							this.progressperc=Math.round(100 * event.loaded / event.total);
						  // pass the percentage into the progress-stream
						  //progress.next(percentDone);
						  //console.log(percentDone);
						} else if (event instanceof HttpResponse) {

						  // Close the progress-stream if we get an answer form the API
						  // The upload is complete
						  //progress.complete();
						  console.log(event);
						  this.DownloadProgressBar('finished',1);
						  if(event['body']['reason'] == 'OK')
							  this.SetSecTimerUpdateList();
						  else{
							  console.log('Falla al subir el archivo');
							  this.flagDBError=true;
							  this.SetSecTimerInfoError();
						  }
							  
						}
					  },(error)=>{
							console.log(error);
							this.flagDBError=true;
							this.SetSecTimerInfoError();
							this.DownloadProgressBar('finished',1);
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: PDF, PNG, GIF y JPG.');
			  }
		  }else{
			  alert('El tamaño máximo de archivo es de 10 Mb, por favor revise su archivo.');
		  }
	  }
  }
  UploadFile_dpi(){
	  if(this.filedpiCtrl.nativeElement.files.item(0)){
		  console.log(this.filedpiCtrl.nativeElement.files.item(0));
		  if( this._fileManagerService.VerifyFileSize(this.filedpiCtrl.nativeElement.files.item(0).size) ){
			  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.filedpiCtrl.nativeElement.files.item(0).type, this.filedpiCtrl.nativeElement.files.item(0).name);
			  if(tipo_imagen){
				  console.log(this.filedpiCtrl.nativeElement.files[0]);
					this.DownloadProgressBar('start',2);
					this._fileManagerService.postNewImage(this.filedpiCtrl.nativeElement.files.item(0),this.id_queja,tipo_imagen,this.id_flujo,2).subscribe(event => {
						if (event.type === HttpEventType.UploadProgress) {

						  // calculate the progress percentage
						  //const percentDone = Math.round(100 * event.loaded / event.total);
							this.progressperc_dpi=Math.round(100 * event.loaded / event.total);
						  // pass the percentage into the progress-stream
						  //progress.next(percentDone);
						  //console.log(percentDone);
						} else if (event instanceof HttpResponse) {

						  // Close the progress-stream if we get an answer form the API
						  // The upload is complete
						  //progress.complete();
						  console.log(event);
						  this.DownloadProgressBar('finished',2);
						  if(event['body']['reason'] == 'OK')
							  this.SetSecTimerGetLinks();
						  else{
							  console.log('Falla al subir el archivo');
							  this.flagDBError=true;
							  this.SetSecTimerInfoError();
						  }
							  
						}
					  },(error)=>{
							console.log(error);
							this.flagDBError=true;
							this.SetSecTimerInfoError();
							this.DownloadProgressBar('finished',2);
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: PDF, PNG, GIF y JPG.');
			  }
		  }else{
			  alert('El tamaño máximo de archivo es de 10 Mb, por favor revise su archivo.');
		  }
	  }
	}
	UploadFile_fac(){
	  if(this.filefacCtrl.nativeElement.files.item(0)){
		  console.log(this.filefacCtrl.nativeElement.files.item(0));
		  if( this._fileManagerService.VerifyFileSize(this.filefacCtrl.nativeElement.files.item(0).size) ){
			  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.filefacCtrl.nativeElement.files.item(0).type, this.filefacCtrl.nativeElement.files.item(0).name);
			  if(tipo_imagen){
				  console.log(this.filefacCtrl.nativeElement.files[0]);
					this.DownloadProgressBar('start',3);
					this._fileManagerService.postNewImage(this.filefacCtrl.nativeElement.files.item(0),this.id_queja,tipo_imagen,this.id_flujo,3).subscribe(event => {
						if (event.type === HttpEventType.UploadProgress) {

						  // calculate the progress percentage
						  //const percentDone = Math.round(100 * event.loaded / event.total);
							this.progressperc_fac=Math.round(100 * event.loaded / event.total);
						  // pass the percentage into the progress-stream
						  //progress.next(percentDone);
						  //console.log(percentDone);
						} else if (event instanceof HttpResponse) {

						  // Close the progress-stream if we get an answer form the API
						  // The upload is complete
						  //progress.complete();
						  console.log(event);
						  this.DownloadProgressBar('finished',3);
						  if(event['body']['reason'] == 'OK')
							  this.SetSecTimerGetLinks();
						  else{
							  console.log('Falla al subir el archivo');
							  this.flagDBError=true;
							  this.SetSecTimerInfoError();
						  }
							  
						}
					  },(error)=>{
							console.log(error);
							this.flagDBError=true;
							this.SetSecTimerInfoError();
							this.DownloadProgressBar('finished',3);
					  });
			  }else{
				  alert('Solo se permite subir archivos de tipo: PDF, PNG, GIF y JPG.');
			  }
		  }else{
			  alert('El tamaño máximo de archivo es de 10 Mb, por favor revise su archivo.');
		  }
	  }
	}
  
  LocalErrorHandler(error){
	  console.log('caught error during upload');
	  this.DownloadProgressBar('finished',1);
	  return this._fileManagerService.handleError<any>(error);
  }
  
  ReadImageList(){
	  let tempstr='';
		this._fileManagerService.getImageList(this.id_queja,this.id_flujo).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.ListImages=JSON.parse('['+tempstr.slice(0, -1) +']');
				}else{
					this.ListImages=[];
				}
				this.flagDBError=false;
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

	SetSecTimerUpdateList(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.ReadImageList());
	}	
	SetSecTimerInsert(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerDelete(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.flagDeleteInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
	}
	SetSecTimerProgressComplete(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => {this.UploadFinished=true;this.progressperc=0;});
	}
	SetSecTimerProgressComplete_dpi(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => {this.UploadFinished_dpi=true;this.progressperc_dpi=0;});
	}
	SetSecTimerProgressComplete_fac(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => {this.UploadFinished_fac=true;this.progressperc_fac=0;});
	}
	SetSecTimerGetLinks(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.GetListObligatorios());
	}

	/*DownloadProgressBar(operation){
		if(operation=='finished'){
			this.SetSecTimerProgressComplete();
		}else{
			this.UploadFinished=false;			
		}
	}*/

	DownloadProgressBar(operation, number){
		switch(number){
			case 1:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete();
				}else{
					this.UploadFinished=false;			
				}
				break;
			case 2:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete_dpi();
				}else{
					this.UploadFinished_dpi=false;			
				}
				break;
			case 3:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete_fac();
				}else{
					this.UploadFinished_fac=false;			
				}
				break;
		}				
	}

/*	ColorSelect(){
		if(this.progressperc >= 100)
			return "accent"
		else
			return "primary"
	}*/

	/*
	el parametro "async" obliga a que una instruccion detenga el flujo del codigo con la palabra clave "await"
	era necesario para guardar en la variable zip cada archivo antes de generalo como .zip
	*/
	DownloadZip() {
		this._fileManagerService.getZip(this.id_queja,1).subscribe((Data)=>{
			this._fileManagerService.FileDownLoadChoose(Data,99);
			this.flagDBError=false;
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
	}

	GetListObligatorios(){
	  this._fileManagerService.getObligatorios(this.id_queja).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.list_conf=JSON.parse('['+retvalue["value"] +']');
					console.log(this.list_conf);
					this.ImageList=this.list_conf[0];
					this.flagObligatorios=true;
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

	/*DownloadZip(){
		 var jszip2;
		 const Fname=this.id_queja;
		  //jszip.file('Hello.txt', 'Hello World\n');

		 jszip.generateAsync({ type: 'blob' }).then(function(content) {
			// see FileSaver.js
			saveAs(content, 'example.zip');
		  });
		  
			var promise = null;
			if (JSZip.support.uint8array) {
			  promise = jszip.generateAsync({type : "uint8array"});
			} else {
			  promise = jszip.generateAsync({type : "string"});
			}
		  var promise = jszip.generateAsync({type:"blob"});
		  const blob = new Blob([promise], { type: 'application/zip' });
		  const url= window.URL.createObjectURL(blob);
		  var pwa = window.open(url);
		  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
			  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
		  }
		//var img = zip.folder(this.id_queja);  
		this.ReadAllFiles().subscribe((Data)=>{
			console.log(Data);
			jszip2=Data;
			if(jszip2 != null){				
				console.log('started file creation process');
				jszip2.generateAsync({type:"base64"}).then(function (base64) {
					//window.location.href = "data:application/zip;base64," + base64;
					// Construct the <a> element
					var link = document.createElement("a");
					link.download = Fname+'.zip';
					link.href = "data:application/zip;base64," + base64;;
					document.body.appendChild(link);
					link.click();
					// Cleanup the DOM
					document.body.removeChild(link);
				},(error)=>{
					console.log(error);
					this.flagDBError=true;
					this.SetSecTimerInfoError();
					return null;
				});
			}
		}, function (err) {
			console.log(err);
		});
		return 1;
	}*/
	
	ImageLink(id_imagen,id_tipo_imagen){
		this._fileManagerService.getImage(id_imagen).subscribe((Data)=>{
			console.log(id_imagen,id_tipo_imagen);
			this._fileManagerService.FileDownLoadChoose(Data,id_tipo_imagen);
			this.flagDBError=false;
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
	} 
	
}
