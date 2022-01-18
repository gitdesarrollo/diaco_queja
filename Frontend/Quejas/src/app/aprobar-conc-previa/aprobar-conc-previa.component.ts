import { Component, OnInit, ViewChild,ElementRef } from '@angular/core';
import { UsuarioService } from '../shared/usuario.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { ConfiguracionService } from "../shared/configuracion.service";
import { ProveedorService } from "../shared/proveedor.service";
import { FrmConfUsuarioBusqueda, FormResponse } from "../atencion-consumidor-const";
import { FileManagerService } from '../shared/file-manager.service';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import swal from 'sweetalert2'

@Component({
  selector: 'app-aprobar-conc-previa',
  templateUrl: './aprobar-conc-previa.component.html',
  styleUrls: ['./aprobar-conc-previa.component.css'],
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
export class AprobarConcPreviaComponent implements OnInit {
	flagInfoError:boolean;
	loaderror:boolean;
	tabledata:boolean;
	flagBoton:boolean;
	flagSaved:boolean;
	btnvisible:boolean;
	flagChange:boolean;
	flagMainUpdate:boolean;
	flagDetalle:boolean;
	flagScheduled:boolean;
	flagformvisible; list_conf; list_detalle; ListUsuariosCount; ListDetalleCount; lst_usuarios;
	idUsuario; nom_lista; flagEditDetalle; ScheduledDate; idDetalle; usuarioDetalle; fecha_programada; idLista; SingleElem; ImageList; lst_images;
	myForm: FormGroup;
	estadoCtrl: FormControl;
	FrmRazon: FormGroup;
	razonCtrl: FormControl;
	progressperc1:Number;
	progressaccent1:String;
	UploadFinished1:boolean;
	progressperc2:Number;
	progressaccent2:String;
	UploadFinished2:boolean;
	progressperc3:Number;
	progressaccent3:String;
	UploadFinished3:boolean;
	progressperc4:Number;
	progressaccent4:String;
	UploadFinished4:boolean;
	@ViewChild('formregistroVC') formregistroCtrl:ElementRef;
	@ViewChild('nombreplegalVC') nombreplegalCtrl:ElementRef;
	@ViewChild('dpiVC') dpiCtrl:ElementRef;
	@ViewChild('patenteVC') patenteCtrl:ElementRef;	
	@ViewChild('replink1') RepLink1Ctrl:ElementRef;	
	@ViewChild('replink2') RepLink2Ctrl:ElementRef;	
	@ViewChild('replink3') RepLink3Ctrl:ElementRef;	
	@ViewChild('replink4') RepLink4Ctrl:ElementRef;	
	str_qrpath; lst_path;

  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<AprobarConcPreviaComponent>, private _usuarioService: UsuarioService, private _configuracionService: ConfiguracionService, private _proveedorService: ProveedorService, private _fileManagerService:FileManagerService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.flagSaved=false;
	this.btnvisible=true;
	this.flagChange=false;
	this.flagMainUpdate=false;
	this.flagDetalle=false;
	this.flagEditDetalle=false;
	this.flagScheduled=false;
	this.idUsuario=0;
	this.flagformvisible=0;
	this.ListUsuariosCount=-1;
	this.nom_lista='';
	this.ListDetalleCount=-1;
	this.idDetalle=0;
	this.idLista=0;
	this.usuarioDetalle='';
	this.progressperc1=0;
	this.progressaccent1="primary";
	this.UploadFinished1=true;
	this.progressperc2=0;
	this.progressaccent2="primary";
	this.UploadFinished2=true;
	this.progressperc3=0;
	this.progressaccent3="primary";
	this.UploadFinished3=true;
	this.progressperc4=0;
	this.progressaccent4="primary";
	this.UploadFinished4=true;
	this.str_qrpath='';
  }

  ngOnInit() {
	  estadoCtrl: FormControl;
	  this.estadoCtrl =  new FormControl('',Validators.required);
	  this.myForm = new FormGroup({
		estado: this.estadoCtrl,
	  });
	  razonCtrl: FormControl;
	  this.razonCtrl =  new FormControl('',Validators.required);
	  this.FrmRazon = new FormGroup({
		razon: this.razonCtrl,
	  });
	  
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.estadoCtrl.setValue('P');
	  this.GetList();
	  this.GetQrpath();
	  //this.generoCtrl.setValue('Masculino');
	  
  }

  ListClick(singleElement){
	  this.idLista=singleElement['id'];
	  this.SingleElem=singleElement;
	  console.log(this.SingleElem);
	  this.GetProveedorImagenes(singleElement['id']);
	  if(singleElement['estado'] == 'P'){
		  this.SingleElem['estado_string']='Pendiente';
	  }else if(singleElement['estado'] == 'A'){
		  this.SingleElem['estado_string']='Aprobado';
	  }else if(singleElement['estado'] == 'R'){
		  this.SingleElem['estado_string']='Rechazado';
	  }		  
	  //get date with id
  }

  DetailsClick(singleElement){
	  console.log(singleElement);
	  this.idDetalle=singleElement['id'];
	  this.usuarioDetalle=singleElement['login'];
	  this.fecha_programada=singleElement['fecha_programado'];
	  if(singleElement['activo'] == true){
		this.estadoCtrl.setValue(1);
	  }else{
		this.estadoCtrl.setValue(0);  
	  }
	  if(singleElement['flag_programado'] == 1){
		this.flagScheduled=true;
		this.ScheduledDate=singleElement['fecha_programado'];		
	  }else{
		this.flagScheduled=false;  
	  }
	  this.flagEditDetalle=true;
	  //this.getDetalleSingle(id);
  }

  Aprobar(){
		this.saveEstado('A');
  }

  Rechazar(){
		this.rechazoProvAprobar();
  }
  
 onSubmit(){}

	BackDialog(){
		this.flagDetalle=false;
	}

  GetList(){
	  this._proveedorService.getProveedorAprobar(this.estadoCtrl.value).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				console.log(this.list_conf);
				if(tempstr != null && tempstr != '')	{
					this.list_conf=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.list_conf);
					this.ListUsuariosCount=this.list_conf.length;
					console.log(this.ListUsuariosCount);
				}else{
					this.ListUsuariosCount=0;
					this.list_conf=[];
				}
				console.log(this.ListUsuariosCount);
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

	GetProveedorImagenes(id){
		this._fileManagerService.getProveedorAll(id).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.lst_images=JSON.parse('['+retvalue["value"] +']');
					this.ImageList=this.lst_images[0];
					console.log(this.ImageList);
					console.log(this.ImageList['formulario_registro']);
				}
				console.log(this.ListUsuariosCount);
				this.flagformvisible++;
				this.flagDetalle=true;
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

	BtnVisibleAfterxsec(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.btnvisible=true);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	SetTimerSaved(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagSaved=false );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

	saveEstado(estado){
		this.btnvisible=false;
		console.log('a grabar proveedor aprobar');
		this._proveedorService.saveProveedorAprobar( estado, this.idLista  ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagDetalle=false;
				this.Alerta("exito", null);
				this.GetList();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.BtnVisibleAfterxsec();		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
			this.BtnVisibleAfterxsec();
		});  
	}
	
	rechazoProvAprobar(){
		this.btnvisible=false;
		this._proveedorService.rechazoProveedorAprobar( this.TrimandCut(this.razonCtrl.value,99), this.idLista  ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagDetalle=false;
				this.GetList();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.BtnVisibleAfterxsec();		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
			this.BtnVisibleAfterxsec();
		});  
	}
	
	/**
	 * alruanoe
	 * Metodo de mostrar alertas */
	Alerta(tipo, mensaje) {
		if (mensaje == undefined || mensaje == null) {
			if (tipo == 'ErrorCorreo') {
				swal.fire({
					icon: 'error',
					title: 'Error datos obtenidos.',
					text: 'No tiene configurado su correo electrónico en SAT, por favor actualice sus datos en Agencia Virtual de SAT. Gracias.',
				});

			}

			if (tipo == 'ErrorTelefono') {
				swal.fire({
					icon: 'error',
					title: 'Error datos obtenidos.',
					text: 'No tiene configurado su número telefónico en SAT, por favor actualice sus datos en Agencia Virtual de SAT. Gracias.',
				});

			}

			if (tipo == 'exito') {
				swal.fire({					
					icon: 'success',
					title: 'Proveedor registrado exitosamente.',
					showConfirmButton: false,
					timer: 1500
				})
			}

		} else {

			if (tipo == 'Error') {
				swal.fire({
					icon: 'error',
					title: 'Error.',
					text: mensaje,
				});

			}

			if (tipo == 'Advertencia') {
				swal.fire({
					icon: 'warning',
					title: 'Advertencia.',
					text: mensaje,

				});

			}
		}
	}
	TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
	}

	  UploadFile1(){// categoria 14 formulario registro
		  if(this.formregistroCtrl.nativeElement.files.item(0)){
			  console.log(this.formregistroCtrl.nativeElement.files.item(0));
			  if( this._fileManagerService.VerifyFileSize(this.formregistroCtrl.nativeElement.files.item(0).size) ){
				  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.formregistroCtrl.nativeElement.files.item(0).type, this.formregistroCtrl.nativeElement.files.item(0).name);
				  if(tipo_imagen){
					  console.log(this.formregistroCtrl.nativeElement.files[0]);
						this.DownloadProgressBar('start',1);
						this._fileManagerService.postNewProveedor(this.formregistroCtrl.nativeElement.files.item(0),14,this.idLista,tipo_imagen).subscribe(event => {
							if (event.type === HttpEventType.UploadProgress) {
								this.progressperc1=Math.round(100 * event.loaded / event.total);
							} else if (event instanceof HttpResponse) {
							  console.log(event);
							  this.DownloadProgressBar('finished',1);
							  if(event['body']['reason'] == 'OK')
								  this.SetSecTimerGetLinks();
							  else{
								  console.log('Falla al subir el archivo');
								  this.flagInfoError=true;
								  this.SetSecTimerInfoError();
							  }								  
							}
						  },(error)=>{
								console.log(error);
								this.flagInfoError=true;
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
	UploadFile2(){// categoria 15 nombramiento rep legal
		  if(this.nombreplegalCtrl.nativeElement.files.item(0)){
			  console.log(this.nombreplegalCtrl.nativeElement.files.item(0));
			  if( this._fileManagerService.VerifyFileSize(this.nombreplegalCtrl.nativeElement.files.item(0).size) ){
				  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.nombreplegalCtrl.nativeElement.files.item(0).type, this.nombreplegalCtrl.nativeElement.files.item(0).name);
				  if(tipo_imagen){
					  console.log(this.nombreplegalCtrl.nativeElement.files[0]);
						this.DownloadProgressBar('start',2);
						this._fileManagerService.postNewProveedor(this.nombreplegalCtrl.nativeElement.files.item(0),15,this.idLista,tipo_imagen).subscribe(event => {
							if (event.type === HttpEventType.UploadProgress) {
								this.progressperc2=Math.round(100 * event.loaded / event.total);
							} else if (event instanceof HttpResponse) {
							  console.log(event);
							  this.DownloadProgressBar('finished',2);
							  if(event['body']['reason'] == 'OK')
								  this.SetSecTimerGetLinks();
							  else{
								  console.log('Falla al subir el archivo');
								  this.flagInfoError=true;
								  this.SetSecTimerInfoError();
							  }								  
							}
						  },(error)=>{
								console.log(error);
								this.flagInfoError=true;
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
	UploadFile3(){// categoria 16 dpi dueño o representante legal
		  if(this.dpiCtrl.nativeElement.files.item(0)){
			  console.log(this.dpiCtrl.nativeElement.files.item(0));
			  if( this._fileManagerService.VerifyFileSize(this.dpiCtrl.nativeElement.files.item(0).size) ){
				  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.dpiCtrl.nativeElement.files.item(0).type, this.dpiCtrl.nativeElement.files.item(0).name);
				  if(tipo_imagen){
					  console.log(this.dpiCtrl.nativeElement.files[0]);
						this.DownloadProgressBar('start',3);
						this._fileManagerService.postNewProveedor(this.dpiCtrl.nativeElement.files.item(0),16,this.idLista,tipo_imagen).subscribe(event => {
							if (event.type === HttpEventType.UploadProgress) {
								this.progressperc3=Math.round(100 * event.loaded / event.total);
							} else if (event instanceof HttpResponse) {
							  console.log(event);
							  this.DownloadProgressBar('finished',3);
							  if(event['body']['reason'] == 'OK')
								  this.SetSecTimerGetLinks();
							  else{
								  console.log('Falla al subir el archivo');
								  this.flagInfoError=true;
								  this.SetSecTimerInfoError();
							  }								  
							}
						  },(error)=>{
								console.log(error);
								this.flagInfoError=true;
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
	UploadFile4(){// categoria 17 patente de sociedad y empresa
		  if(this.patenteCtrl.nativeElement.files.item(0)){
			  console.log(this.patenteCtrl.nativeElement.files.item(0));
			  if( this._fileManagerService.VerifyFileSize(this.patenteCtrl.nativeElement.files.item(0).size) ){
				  var tipo_imagen=this._fileManagerService.TranslateTipoImagen(this.patenteCtrl.nativeElement.files.item(0).type, this.patenteCtrl.nativeElement.files.item(0).name);
				  if(tipo_imagen){
					  console.log(this.patenteCtrl.nativeElement.files[0]);
						this.DownloadProgressBar('start',4);
						this._fileManagerService.postNewProveedor(this.patenteCtrl.nativeElement.files.item(0),17,this.idLista,tipo_imagen).subscribe(event => {
							if (event.type === HttpEventType.UploadProgress) {
								this.progressperc4=Math.round(100 * event.loaded / event.total);
							} else if (event instanceof HttpResponse) {
							  console.log(event);
							  this.DownloadProgressBar('finished',4);
							  if(event['body']['reason'] == 'OK')
								  this.SetSecTimerGetLinks();
							  else{
								  console.log('Falla al subir el archivo');
								  this.flagInfoError=true;
								  this.SetSecTimerInfoError();
							  }								  
							}
						  },(error)=>{
								console.log(error);
								this.flagInfoError=true;
								this.SetSecTimerInfoError();
								this.DownloadProgressBar('finished',4);
						  });
				  }else{
					  alert('Solo se permite subir archivos de tipo: PDF, PNG, GIF y JPG.');
				  }
			  }else{
				  alert('El tamaño máximo de archivo es de 10 Mb, por favor revise su archivo.');
			  }
		  }
	}
	DownloadProgressBar(operation, number){
		switch(number){
			case 1:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete1();
				}else{
					this.UploadFinished1=false;			
				}
				break;
			case 2:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete2();
				}else{
					this.UploadFinished2=false;			
				}
				break;
			case 3:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete3();
				}else{
					this.UploadFinished3=false;			
				}
				break;
			case 4:
				if(operation=='finished'){
					this.SetSecTimerProgressComplete4();
				}else{
					this.UploadFinished4=false;			
				}
				break;
		}				
	}
	
	ImageLink(id_imagen,id_tipo_imagen){
		this._fileManagerService.getImageProveedor(id_imagen).subscribe((Data)=>{
			console.log(id_imagen,id_tipo_imagen);
			this._fileManagerService.FileDownLoadChoose(Data,id_tipo_imagen);
			this.flagInfoError=false;
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}  
	
	SetSecTimerGetLinks(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.GetProveedorImagenes(this.idLista));
	}
	SetSecTimerProgressComplete1(){
		const source = timer(500);
		const subscribe = source.subscribe(val => {this.UploadFinished1=true;this.progressperc1=0;});
	}
	SetSecTimerProgressComplete2(){
		const source = timer(500);
		const subscribe = source.subscribe(val => {this.UploadFinished2=true;this.progressperc2=0;});
	}
	SetSecTimerProgressComplete3(){
		const source = timer(500);
		const subscribe = source.subscribe(val => {this.UploadFinished3=true;this.progressperc3=0;});
	}
	SetSecTimerProgressComplete4(){
		const source = timer(500);
		const subscribe = source.subscribe(val => {this.UploadFinished4=true;this.progressperc4=0;});
	}	
	
	GetQrpath(){
		this._configuracionService.getQR().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.lst_path=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.str_qrpath=this.lst_path[0]['path']+this.data['Nit'];
					console.log(this.lst_path, this.str_qrpath);			
				}
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
		});
	}
	
}
