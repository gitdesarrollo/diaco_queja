//import { Component, OnInit } from '@angular/core';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
//import { ormGroup,  FormControl } from '@angular/forms';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Subscription, Subject,Observable, of } from 'rxjs';
import { Router, ActivatedRoute, Params  } from '@angular/router';
import { QuejasIniService } from '../shared/quejasini.service';
import { Quejaini } from '../shared/quejaini.model';
import { Secuencia } from '../shared/secuencia.model';
import { Queja } from '../shared/queja.model';
import { FileUploader, FileItem } from "ng2-file-upload";
import { BASE_URL_FILE } from '../conc-virt-const';
import { Consumidor } from '../shared/consumidor.model';
import { Proveedor } from '../shared/proveedor.model';
import { ConsumidoresService } from '../shared/consumidores.service';
import { ProveedoresService } from '../shared/proveedores.service';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { MyAlertDialogComponent } from '../my-alert-dialog/my-alert-dialog.component';
import { MatDialog } from '@angular/material';


import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { ResponseRs } from '../shared/response-rs.model';

@Component({
  selector: 'app-quejacon',
  templateUrl: './quejacon.component.html',
  styleUrls: ['./quejacon.component.scss']
})
export class QuejaconComponent implements OnInit {
	public nproveedor:string = "";
		public vanio:number = 0
			public vsecanio:number = 0;
				public vsecuencia2:string = "";
				public vidqueja: number = 0;
				public vidimagen: number = 3;
				
				public vdpi: number = -1;
				public vfac: number = -1;
				
				public vact: number = -1;
	public dato: string = "";
	public vid: string = "";
	
	//variables a mostrar
	public _vDpiPasaporte : string = "";
	public _vNombre : string = "";
	  public _vTelefono : string = "";
	  public _vCorreo : string = "";
	  public _vDetalleQueja : string = "";
	  public _vSolicitaQue : string = "";
	  public _vNitProveedor : string = "";
	  public _vUbicacion : string = "";
	  public _url : string = "";
	  existingData: boolean = false;
  quejaForm: FormGroup;
  quejaOperationSubscription: Subscription;
  	existingConsumidor: Consumidor;
	existingProveedor: Proveedor;
	existingQueja: Quejaini;
	existingModeConsumidor: boolean = false;
	existingModeProveedor: boolean = false;
	existingModeToken: boolean = false;
	existingSecuencia: Secuencia;
	vsecuencia:string = "";
	
  @ViewChild('filDpi')
  filDpi: ElementRef;
  uploader = new FileUploader({
    //url: BASE_URL_FILE,
    itemAlias: 'document'
  });
  factura: FileItem;
  facturaIndex: number;
  dpi: FileItem;
  dpiIndex: number;
  success: boolean = false;
  successFile : boolean = true;
  
  
  

operation = new Subject<Quejaini>();

  //constructor() { }
   constructor(private router: Router,
   private dialog: MatDialog,
   private _route: ActivatedRoute,
   private consumidoresService: ConsumidoresService,
   private proveedoresService: ProveedoresService,
    private quejasService: QuejasIniService) { 
	//console.info(this._route.snapshot.paramMap.get('id'));
	}

ngOnDestroy() {
	////console.log("finaliza");
}

  ngOnInit() {
	      // listener cuando finaliza la operacion de creacion
    this.quejaOperationSubscription = this.quejasService.operation.subscribe(
      (queja: Quejaini) => {
        // limpia sesion
        this.quejaForm.reset();
        this.success = true;
		this.successFile = true;
		this.nproveedor = "";
      }
    );
	
	this._route.params.subscribe(
      (params: Params) => {
		this.dato = params.dato;  
        this.vid = params.id;
      }
    );
	
    this.initQuejaForm();
	 //this.initConsumidorForm(null);
	 //this.vid = this._route.snapshot.paramMap.get('id')
	 //console.info(this.vid);
	 if (this.vid != "") {
		 this.findByToken("0");
		 
		 //console.info(this.vid);
		 
		 //if (this.vid == "") {
		 this.unregister();
	 //}
	 }
	 
  }
  
  
  
  initQuejaForm() {
    this.quejaForm = new FormGroup({
      'nombre': new FormControl(''),
      'dpiPasaporte': new FormControl(''),
	  'telefono': new FormControl(''),
	  'correo': new FormControl(''),
      'detalleQueja': new FormControl(''),
      'solicitaQue': new FormControl(''),
	  'nitProveedor': new FormControl(''),
	  'ubicacion': new FormControl('')
    });
	this.nproveedor = "";
	this.dpiIndex = -1;
	this.facturaIndex = -1;
	////console.log("inicializa");
  }
  
  unregister() {
    let dialogRef = this.dialog.open(MyAlertDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      // NOTE: The result can also be nothing if the user presses the `esc` key or clicks outside the dialog
      if (result == 'confirm') {
        console.log('Unregistered');
      }
    })
  }
  
    onFileChanged(type: string) {
		//console.info(type);	
		
			this.vact = this.uploader.queue.length - 1;
		//console.info(this.factura);
		if (this.uploader.queue.length > 0) {
			if (type == 'FAC') {
			  if (this.factura && this.facturaIndex > -1) {
				let facturaItem = this.uploader.queue[this.facturaIndex];
				this.uploader.removeFromQueue(facturaItem);
				
			  }
				this.factura = this.uploader.queue[this.uploader.queue.length - 1];
			  this.facturaIndex = this.uploader.queue.length - 1;

			  if (this.dpiIndex == this.facturaIndex) {
				  this.dpiIndex = this.dpiIndex -1;
			  }
			  
		
			
			} else if (type == 'DPI' ) {
				  if (this.dpi && this.dpiIndex > -1) {
					let dpiItem = this.uploader.queue[this.dpiIndex];
					this.uploader.removeFromQueue(dpiItem);
					
					
				
				  }
				  this.dpi = this.uploader.queue[this.uploader.queue.length - 1];
				  this.dpiIndex = this.uploader.queue.length - 1;
			if (this.dpiIndex == this.facturaIndex) {
				  this.facturaIndex = this.facturaIndex -1;
			  }				
				
			
				}
			if (this.uploader.queue.length==0) {
				this.dpiIndex = -1;
				this.facturaIndex = -1;
			} 
				
		//console.info(this.factura);
		//console.info(this.dpi);
		//console.info(this.uploader.queue.length);
	//console.info(this.facturaIndex);
	//console.info(this.dpiIndex);
//console.info(this.uploader.queue.length);	

	
	if (this.uploader.queue.length==2 && this._vDpiPasaporte!=undefined) {
		this.successFile = false;
		} else {
			this.successFile = true;
		}
		console.info(this._vDpiPasaporte,this.uploader.queue.length==2 );
  }
}
/*
  onRemoveClicked(index: number) {
    let removeItem = this.uploader.queue[index];
    this.uploader.removeFromQueue(removeItem);
    if (index == this.facturaIndex) {
      this.facturaIndex = null;
      this.factura = null;
    } else if (index == this.dpiIndex) {
      this.dpiIndex = null;
      this.dpi = null;
    }
  }*/
  

	
  
    public onSubmit() {
		
    let queja: Quejaini = new Quejaini();
    queja.nombre = this.quejaForm.value.nombre;
    queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
	queja.telefono = this.quejaForm.value.telefono;
    queja.correo = this.quejaForm.value.correo;
    queja.detalleQueja = this.quejaForm.value.detalleQueja;
    queja.solicitaQue = this.quejaForm.value.solicitaQue;
	queja.nitProveedor = this.quejaForm.value.nitProveedor;
	
	queja.anio = 0;
	queja.secuencia = 0;
	queja.idFuente = this.vsecuencia;
	if (this.existingModeConsumidor) {
		queja.idConsumidor = this.existingConsumidor.idConsumidor;
	} else {
		queja.idConsumidor = 0;
	}
	if (this.existingModeProveedor) {
		queja.idProveedor = this.existingProveedor.idProveedor;
	} else {
		queja.idProveedor =0;
	}
	
	// verifica que esten ingresados los 2 archivos
	if (this.uploader.queue.length!=2) {
		alert("Debe de ingresar Datos adjuntos requeridos.");
		return;
	} 
	//queja = this.quejasService.createQueja(queja, this.uploader).susbribe;
	/*
	this.quejasService.saveData(queja).subscribe(
      (retvalue) => {
		  
		if(retvalue){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					//this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.vanio = tempstr.anio;
					this.vsecanio = tempstr.secuencia;
					this.vidqueja = tempstr.idQueja;
	*/
	
		//coloca los archivos en orden.
	 
	 if (this.uploader.queue.length >= 2) {
		let removeItem = this.uploader.queue[1];
        this.uploader.removeFromQueue(removeItem);
		removeItem = this.uploader.queue[0];
        this.uploader.removeFromQueue(removeItem);
		this.uploader.queue.push(this.factura);
        this.uploader.queue.push(this.dpi);
		//console.info(this.uploader.queue.length);
		}
	
	if (this.uploader.queue.length > 	0) {
		//console.log(this.vidqueja);
		
		this.uploader.onBeforeUploadItem = (removeItem) => {
				//removeItem.withCredentials = false;
				//removeItem.url = BASE_URL_REST_FILE + 'quejas/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen;
				//BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
					  	for (let i = 0; i < this.uploader.queue.length; i++) {
							//console.log("modifica url - inicio");
							//console.log(i);
							let removeItem = this.uploader.queue[i];
							if (removeItem == this.dpi) {
								//console.log("cambia url dpi");
								this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+2+"&correo=1&data="+this.vid;
								//console.log(this.uploader.queue[i].url);
								//console.log(removeItem);
							} else {
								if (removeItem == this.factura) {
									//console.log("cambia url factura");
									this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+3+"&correo=0&data="+this.vid;
									//console.log(this.uploader.queue[i].url);	
									//console.log(removeItem);
								} else {
									//console.log("cambia url otro");
									this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+1+"&correo=0&data="+this.vid;
									//console.log(this.uploader.queue[i].url);
									//console.log(removeItem);
								}
							}
							//console.log("modifica url - fin");	
						}
						
				}
				
          this.uploader.setOptions({
            //url: BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
            itemAlias: 'file'
			
          });
		
          this.uploader.onCompleteAll = () => {
            // finalizo la carga de todos los archivos
            // this.operation.next(retvalue.value);
			//console.log("completo inicia limpieza");
				
				this._vDpiPasaporte = '';
	  this._vNombre = '';
	  this._vTelefono = '';
	  this._vCorreo = '';
	  this._vDetalleQueja = '';
	  this._vSolicitaQue = '';
	  this._vNitProveedor = '';
	  this._vUbicacion =  '';
	  this.findByToken("1");
	  this.successFile = true;
	  this.success = true;

		  /*
	//
	  //this.vanio = 0;
	  //this.vsecanio =  0;
	  //this.vidqueja =  0;
	  //this.uploader = null;
	  */
          };
          this.uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
            // finalizo la carga de un archivo
			// this.vidimagen = this.vidimagen + 1;
			console.log('ImageUpload:uploaded:', item, status, headers);
          };

          this.uploader.uploadAll();
        } else {
          //this.operation.next(retvalue.value);
        }
	

	
	//this.findSecuenciaId();
	//////console.info("onsSumiT");
	//this.vanio = queja.anio;
	//  this.vsecanio = queja.noQueja;
	////console.info('-------------------');
		  ////console.info(queja);
		  ////console.info('-------------------');
  }

  onDismissClicked() {
    this.success = false;
	this.initQuejaForm();
	console.info("onDismm");
  }

  private initQuejaParam(queja: Quejaini) {
	  
	  this._vDpiPasaporte = ( queja ? queja.dpiPasaporte: '');
	  this._vNombre = ( queja ? queja.nombre: '');
	  this._vTelefono = ( queja ? queja.telefono: '');
	  this._vCorreo = ( queja ? queja.correo: '');
	  this._vDetalleQueja = ( queja ? queja.detalleQueja: '');
	  this._vSolicitaQue = ( queja ? queja.solicitaQue: '');
	  this._vNitProveedor = ( queja ? queja.nitProveedor: '');
	  this.vanio = (queja ? queja.anio : 0);
	  this.vsecanio = (queja ? queja.secuencia : 0);
	  this._vUbicacion = (queja ? queja.ubicacion : '');
	  this.vidqueja = (queja ? queja.idQueja : 0);
	  

  }
 
  private initConsumidor(consumidor: Consumidor) {
    this.quejaForm = new FormGroup({
      'nombre': new FormControl(consumidor ? consumidor.nombre1 : '', Validators.required),
      //'nombre2': new FormControl(consumidor ? consumidor.nombre2 : ''),
      //'nombre3': new FormControl(consumidor ? consumidor.nombre3 : ''),
      //'apellido1': new FormControl(consumidor ? consumidor.apellido1 : '', Validators.required),
      //'apellido2': new FormControl(consumidor ? consumidor.apellido2 : ''),
      //'apellidoCasada': new FormControl(consumidor ? consumidor.apellidoCasada : ''),
      'telefono': new FormControl(consumidor ? consumidor.telefono : '', Validators.required),
      //'telefonoDomicilio': new FormControl(consumidor ? consumidor.domicilio : ''),
	  'correo': new FormControl(consumidor ? consumidor.correoElectronico1 : '', Validators.email),
      'dpiPasaporte': new FormControl(consumidor ? consumidor.documentoIdentificacion : '', Validators.required),
      'detalleQueja': new FormControl( this.quejaForm.value.detalleQueja, Validators.required),
      'solicitaQue': new FormControl( this.quejaForm.value.solicitaQue, Validators.required),
	  'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor, Validators.required),
	  'ubicacion': new FormControl(this.quejaForm.value.ubicacion)

    });
	this.findBySecuencia();
//////console.log("inicializa consumidor");
  }
  
  private initProveedor(proveedor: Proveedor) {
    this.quejaForm = new FormGroup({
      'nombre': new FormControl(this.quejaForm.value.nombre, Validators.required),
      'telefono': new FormControl(this.quejaForm.value.telefono, Validators.required),
	  'correo': new FormControl(this.quejaForm.value.correo, Validators.email),
      'dpiPasaporte': new FormControl(this.quejaForm.value.dpiPasaporte, Validators.required),
      'detalleQueja': new FormControl( this.quejaForm.value.detalleQueja, Validators.required),
      'solicitaQue': new FormControl( this.quejaForm.value.solicitaQue, Validators.required),
	  'nitProveedor': new FormControl(proveedor ? proveedor.nitProveedor : '', Validators.required),
	  /*
	  'primerNombre': new FormControl(proveedor ? proveedor.primerNombre : ''),
      'segundoNombre': new FormControl(proveedor ? proveedor.segundoNombre : ''),
      'tercerNombre': new FormControl(proveedor ? proveedor.tercerNombre : ''),
      'primerApellido': new FormControl(proveedor ? proveedor.primerApellido : ''),
      'segundoApellido': new FormControl(proveedor ? proveedor.segundoApellido : ''),
      'apellidoCasada': new FormControl(proveedor ? proveedor.apellidoCasada : ''),
      'nombreEmpresa': new FormControl(proveedor ? proveedor.nombreEmpresa : ''),
      'razonSocial': new FormControl(proveedor ? proveedor.razonSocial : ''),
	  */
	  'ubicacion': new FormControl(this.quejaForm.value.ubicacion)
    });
//////console.log("inicializa consumidor");
  }
  
	public onDocumentoIdentificacionChanged() {
		//////console.log(this.quejaForm.value.dpiPasaporte);
    this.consumidoresService.fetchData(this.quejaForm.value.dpiPasaporte).subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
          this.existingModeConsumidor = true;
          this.existingConsumidor = response.value;
          this.initConsumidor(response.value);
			//queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
        } else {
		  this.initConsumidor(null);
          this.existingModeConsumidor = false;
          this.existingConsumidor = null;
        }
      }
    );
	 //////console.log("ingreso al onDocumentoIdentificacionChanged");
  }
  
  public onProveedorChanged() {
	  //////console.log(this.quejaForm.value.nitProveedor);
    this.proveedoresService.fetchData(this.quejaForm.value.nitProveedor).subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
          this.existingModeProveedor = true;
          this.existingProveedor = response.value;
          this.initProveedor(response.value);
		  this.nproveedor = response.value.nombre;
        } else {
          this.existingModeProveedor = false;
          this.existingProveedor = null;
		  this.nproveedor = "";
        }
      }
    );
	//////console.log("ingreso al onProveedorChanged");
  }
  
  private initNoQueja(queja: Queja) {
	  this.vanio = (queja ? queja.anio : 0);
	  this.vsecanio = (queja ? queja.noQueja : 0);
	////console.info('initNoQueja|');
	  ////console.info(this.vanio);
	  ////console.info(this.vsecanio);
	}
	
  public findSecuenciaId() {
	  if (this.vsecuencia="") {
	  } else {
    this.quejasService.fetchDataSecId(this.vsecuencia).subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
		  ////console.info("findSecuenc");
          this.initNoQueja(response.value);
		          } 
      }
    );
	}
  }
  
  public findBySecuencia() {
	 this.quejasService.fetchDataSecuencia().subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
          //
		   this.vsecuencia2 = response.value;
		   this.vsecuencia = response.value;
		          } 
      }
    );
  }
  
  public findByToken(dato: string) {
	 this.quejasService.fetchDataByToken(this.vid,dato).subscribe(
      (response) => {
		  
        if (response.value) {
          // inicializar formulario con valores predeterminados
          //
		  this.existingQueja = response.value;
		  this.existingModeToken = true;
		   this.initQuejaParam(response.value);
		   
	
		          } else {
					this.existingData = false;
	  
				  }
      }
    );
console.info(this._vDpiPasaporte); 
 }
  
}
