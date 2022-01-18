import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Pais } from '../shared/pais.model';
import { PaisesService } from '../shared/paises.service';
//import { MotivoQueja } from '../shared/motivo-queja.model';
//import { MotivosQuejaService } from '../shared/motivos-queja.service';
//import { Departamento } from '../shared/departamento.model';
//import { DepartamentosService } from '../shared/departamentos.service';
import { Etnia } from '../shared/etnia.model';
//import { SedesService } from '../shared/sedes.service';
import { EtniasService } from '../shared/etnias.service';
//import { Municipio } from '../shared/municipio.model';
import { Consumidor } from '../shared/consumidor.model';
import { Proveedor } from '../shared/proveedor.model';
//import { Sede } from '../shared/sede.model';
//import { MunicipiosService } from '../shared/municipios.service';
import { ConsumidoresService } from '../shared/consumidores.service';
import { ProveedoresService } from '../shared/proveedores.service'
import { Router } from '@angular/router';
//import { QuejasService } from '../shared/quejas.service';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";
import { MatDialog, MatDialogConfig} from '@angular/material';
import { FileUploader, FileItem } from "ng2-file-upload";
import { Subscription, Subject,Observable, of } from 'rxjs';
import { BASE_URL_REST_FILE2 } from '../conc-virt-const';

@Component({
  selector: 'app-registronotprev',
  templateUrl: './registronotprev.component.html',
  styleUrls: ['./registronotprev.component.scss']
})
export class RegistronotprevComponent implements OnInit {

  registronotprev: FormGroup;
  paises: Pais[];
  paisesSubscription: Subscription;
  //motivosQueja: MotivoQueja[];
  //motivosQuejaSubscription: Subscription;
  //departamentos: Departamento[];
  //departamentosSubscription: Subscription;
  etnias: Etnia[];
  etniasSubscription: Subscription;
  //municipios: Municipio[];
  //municipiosSubscription: Subscription;
  //sedes: Sede[];
  //sedesSubscription: Subscription;
  //consumidorOperationSubscription: Subscription;
  existingMode: boolean = false;
  existingConsumidor: Consumidor;
  existingProveedor: Proveedor;
  mjsError:string  = '';
  tipocon:string = '';
  str_usuario:string = '';
  _conprov : string = '1';
  _existe : string = '0';
  _tipodocumento : string;
  _declaracion: FileItem;
  _declaIndex : number;
  operationc = new Subject<Consumidor>();

  operationp = new Subject<Proveedor>(); 
  uploader = new FileUploader({
    url: BASE_URL_REST_FILE2,
    itemAlias: 'document'
  });
  success: boolean = false;	
  successFile : boolean = true;
	
constructor(private router: Router,
    private paisesService: PaisesService,
    //private motivosQuejaService: MotivosQuejaService,
    //private departamentosService: DepartamentosService,
    //private municipiosService: MunicipiosService,
    private etniasService: EtniasService,
	//private sedesService: SedesService,
	private dialog: MatDialog,
    private consumidoresService: ConsumidoresService,
	private proveedoresService: ProveedoresService,
    //private quejasService: QuejasService,
	private _submitFormService:SubmitFormService,
	private _seguridadService:SeguridadService) {
	   //this.str_usuario=this._submitFormService.Get_username();		
	}

  ngOnInit() {
 
    // cargar paises
    this.paisesSubscription = this.paisesService.fetchData().subscribe(
      res => {
        this.paises = res.value;
      }
    );
    /*
	// cargar motivos de queja
    this.motivosQuejaSubscription = this.motivosQuejaService.fetchData().subscribe(
      res => {
        this.motivosQueja = res.value;
      }
    );
    // cargar departamentos
    this.departamentosSubscription = this.departamentosService.fetchData().subscribe(
      res => {
        this.departamentos = res.value;
      }
    );*/
    // cargar etnias
    this.etniasSubscription = this.etniasService.fetchData().subscribe(
      res => {
        this.etnias = res.value;
      }
    );
	/*
	//cargar sedes
	this.sedesSubscription = this.sedesService.fetchData().subscribe(
      res => {
        this.sedes = res.value;
      }
    );
    // listener cuando finaliza la operacion de creacion
    this.consumidorOperationSubscription = this.consumidoresService.operation.subscribe(
      (consumidor: Consumidor) => {
        this.nextStep(consumidor);
      }
    );*/
    this.initregistronotprev(null,null);
  }

  private initregistronotprev(consumidor: Consumidor, proveedor : Proveedor) {
	  if(proveedor != null) { 		
			let correo : string;
			if (proveedor != null) {
				if (proveedor.email == '' || proveedor.email == undefined) {
					correo = proveedor.email1;
				} else {
					correo = proveedor.email;
				}
			}
			let tel : string;
			if (proveedor != null) {
				if (proveedor.telefono == '' || proveedor.telefono == undefined) {
					tel = proveedor.telefono1;
				} else {
					tel = proveedor.telefono;
				}
			}

		this.registronotprev = new FormGroup({
			// 'tipoConsumidor': new FormControl(proveedor ? proveedor.tipoConsumidor : '1', Validators.required),
			'tipoConsumidorProv': new FormControl(this._conprov),
		  'nitConsumidor': new FormControl(proveedor ? proveedor.nitProveedor : '', Validators.required),
		  'tipoConsumidor': new FormControl(proveedor ? proveedor.tipoProveedor : '6'),
		  'documentoIdentificacion': new FormControl(''),
		  'nombre1': new FormControl(proveedor ? proveedor.primerNombre : ''),
		  'nombre2': new FormControl(proveedor ? proveedor.segundoNombre : ''),
		  'nombre3': new FormControl(proveedor ? proveedor.tercerNombre : ''),
		  'apellido1': new FormControl(proveedor ? proveedor.primerApellido : ''),
		  'apellido2': new FormControl(proveedor ? proveedor.segundoApellido : ''),
		  'apellidoCasada': new FormControl(proveedor ? proveedor.apellidoCasada : ''),
		  'nombreEmpresa': new FormControl(proveedor ? proveedor.nombreEmpresa : ''),
		  'razonSocial': new FormControl(proveedor ? proveedor.razonSocial : ''),
		  'representanteLegal' : new FormControl(''),
		  'actividadEconomica': new FormControl(proveedor ? proveedor.idActividadEconomica : '', Validators.required),
		  'tipoComercio': new FormControl('', Validators.required),
		  //'telefonoCelular': new FormControl(proveedor ? tel : ''),
		  'telefonoCelular': new FormControl(proveedor ? proveedor.telefono1 : ''),
		  'telefonoDomicilio': new FormControl(proveedor ? proveedor.telefono2 : ''),
		  'telefonoReferencia': new FormControl(proveedor ? proveedor.telefono3 : ''),
		  //'email': new FormControl(proveedor ? correo : ''),
		  'email': new FormControl(proveedor ? proveedor.email : ''),
		  'emailConfirmation': new FormControl(proveedor ? proveedor.email : ''),
		  'email2': new FormControl(proveedor ? proveedor.email1 : ''),
		  'email2Confirmation': new FormControl(proveedor ? proveedor.email1 : ''),
		  
		  'codigoDepartamento': new FormControl(proveedor ? proveedor.codigoDepartamento : '', Validators.required),
		  'codigoMunicipio': new FormControl(proveedor ? proveedor.codigoMunicipio : '', Validators.required),
		  //'direccionAvenidaCalle': new FormControl(proveedor ? proveedor.direccionCalle : '', Validators.required),
		  //'tipoDireccion': new FormControl(''),
		  //'direccionNumero': new FormControl(proveedor ? proveedor.direccionAvenida : '', Validators.required),
		  //'direccionZona': new FormControl(proveedor ? proveedor.direccionZona : '', Validators.required),
		  'direccionDetalle': new FormControl(proveedor ? proveedor.direccionDetalle : '', Validators.required),
		  'proveedorComun' : new FormControl(''),
		  'sucursales' : new FormControl(''),
		  'codigoDepartamentoS': new FormControl(''),
		  'codigoMunicipioS': new FormControl(''),
		  'direccionDetalleS': new FormControl(''),
		  'nombreS' : new FormControl(''),
		  'genero': new FormControl(''),
		  'etnia': new FormControl(''),
		  
		});
	} else {
	//if (consumidor != null) {  
		this.registronotprev = new FormGroup({
		'idConsumidor' : new FormControl(consumidor ? consumidor.idConsumidor : '0'),	
		'nacionalidad': new FormControl('G'),
		'tipoConsumidorProv': new FormControl(this._conprov),  
		'tipoDocumento' : new FormControl(consumidor ? consumidor.tipoDocumento : ''),
		'tipoConsumidor': new FormControl(consumidor ? consumidor.tipoConsumidor : '6'),
		//'tipoConsumidorProv': new FormControl(consumidor ? consumidor.tipoConsumidor : '1'),
		'documentoIdentificacion': new FormControl(consumidor ? consumidor.documentoIdentificacion : ''),
		'pais': new FormControl(consumidor ? consumidor.nacionalidad : 1),
		'representanteLegal' : new FormControl(consumidor ? consumidor.representanteLegal : ''),
		'nitConsumidor': new FormControl(consumidor ? consumidor.nitConsumidor : ''),

		'nombreEmpresa': new FormControl(consumidor ? consumidor.nombreEmpresa : ''),
		'razonSocial': new FormControl(consumidor ? consumidor.razonSocial : ''),
		'nombre1': new FormControl(consumidor ? consumidor.nombre1 : ''),
		  'nombre2': new FormControl(consumidor ? consumidor.nombre2 : ''),
		  'nombre3': new FormControl(consumidor ? consumidor.nombre3 : ''),
		  'apellido1': new FormControl(consumidor ? consumidor.apellido1 : ''),
		  'apellido2': new FormControl(consumidor ? consumidor.apellido2 : ''),
		  'apellidoCasada': new FormControl(consumidor ? consumidor.apellidoCasada : ''),
		  'genero': new FormControl(consumidor ? consumidor.genero : ''),
		  'etnia': new FormControl(consumidor ? consumidor.etnia : ''),
		  'motivoQueja': new FormControl(''),

		  'codigoDepartamento': new FormControl(consumidor ? consumidor.codigoDepartamento : ''),
		  'codigoMunicipio': new FormControl(''),
		  //'direccionAvenidaCalle': new FormControl(consumidor ? consumidor.direccionCalle : ''),
		  //'tipoDireccion': new FormControl(''),
		  //'direccionNumero': new FormControl(consumidor ? consumidor.direccionAvenida : ''),
		  //'direccionZona': new FormControl(consumidor ? consumidor.direccionZona : ''),
		  'direccionDetalle': new FormControl(consumidor ? consumidor.direccionDetalle : ''),
		  'sedeDiacoCercana': new FormControl(''),

		  'telefonoCelular': new FormControl(consumidor ? consumidor.telefonoCelular : ''),
		  'telefonoDomicilio': new FormControl(consumidor ? consumidor.telefonoDomicilio : ''),
		  'telefonoReferencia': new FormControl(consumidor ? consumidor.telefonoReferencia : ''),
		  'email': new FormControl(consumidor ? consumidor.email : ''),
		  'emailConfirmation': new FormControl(consumidor ? consumidor.email : ''),
		  'email2': new FormControl(consumidor ? consumidor.email2 : ''),
		  'email2Confirmation': new FormControl(consumidor ? consumidor.email2 : ''),

			'iCheck': new FormControl(consumidor ? consumidor.iCheck : ''),
		'habilitadoNotificacionElectronica': new FormControl(consumidor ? consumidor.habilitadoNotificacionElectronica : '')
		
		});
	} 
    	
    if (consumidor) {
	  /*	
      // seleccionar departamento
      this.onDepartamentoChanged();
      this.registronotprev.patchValue({
        'codigoMunicipio': consumidor.codigoMunicipio
      });
      // seleccionar municipio
      this.onMunicipioChanged();
      this.registronotprev.patchValue({
        'codigoMunicipio': consumidor.codigoMunicipio
      });
	  */
	  //pais
	  this.registronotprev.patchValue({
        'pais': consumidor.pais
      });
	  //genero
	  this.registronotprev.patchValue({
        'genero': consumidor.genero
      });
	  //etnia
	  this.registronotprev.patchValue({
        'etnia': consumidor.etnia
      });
	  /*
	  //queja
	  this.registronotprev.patchValue({
        'motivoQueja': consumidor.motivoQueja
      });
	  //sedeDiacoCercana
	  this.registronotprev.patchValue({
        'sedeDiacoCercana': consumidor.sedeDiacoCercana
      });*/
    }
	this.registronotprev.get('nombre1').disable();
	this.registronotprev.get('nombre2').disable();
	this.registronotprev.get('nombre3').disable();
	this.registronotprev.get('apellido1').disable();
	this.registronotprev.get('apellido2').disable();
	this.registronotprev.get('apellidoCasada').disable();
	this.registronotprev.get('representanteLegal').disable();
	this.registronotprev.get('nombreEmpresa').disable();
	this.registronotprev.get('razonSocial').disable();
	this.registronotprev.get('genero').disable();
	this.registronotprev.get('etnia').disable();
	this.registronotprev.get('telefonoCelular').disable();
	this.registronotprev.get('telefonoDomicilio').disable();
	this.registronotprev.get('telefonoReferencia').disable();
	this.registronotprev.get('email').disable();
	this.registronotprev.get('emailConfirmation').disable();
	this.registronotprev.get('email2').disable();
	this.registronotprev.get('email2Confirmation').disable();

	
  }

  public onDocumentoIdentificacionChanged(tipo: string) {
	  this._tipodocumento = tipo;
  }
	
  public btnIdentificacionChanged() {	
     let tipo: string = this._tipodocumento; 
	 if (tipo == 'documento') {
		 this.tipocon = this.registronotprev.value.documentoIdentificacion;
	 } else {
		 this.tipocon = this.registronotprev.value.nitConsumidor; 
	 }
	 if (this.registronotprev.value.tipoConsumidorProv=='1') { 
		// busca datos consumidor 
		this.consumidoresService.fetchData(tipo,this.tipocon).subscribe((response) => {
			if (response.value) {
			  // inicializar formulario con valores predeterminados
			  let _nacionalidad =  (response.value.nacionalidad == 1 ? 'G' : 'E');
			  if (response.value.tipoConsumidor == this.registronotprev.value.tipoConsumidor &&
			      _nacionalidad== this.registronotprev.value.nacionalidad) {
				  this.existingMode = true;
				  this.existingConsumidor = response.value;
				  this.initregistronotprev(response.value,null);
				  this._existe = '1';
			  } else {
				  this.existingMode = false;
				  this.existingConsumidor = null;
				  this._existe = '0';
				  this.onChangeconprov();
				  alert("No existe información para el consumidor seleccionado");
			  }
			} else {
			  this.existingMode = false;
			  this.existingConsumidor = null;
			  this._existe = '0';
			  this.onChangeconprov();
			  alert("No existe información para el consumidor seleccionado");
			}
		  }
		);
	 } else {
		 //busca proveedor
		 this.proveedoresService.fetchData(this.registronotprev.value.nitConsumidor).subscribe((response) => {
			if (response.value) {
			  // inicializar formulario con valores predeterminados
			  console.log("data ",response.value.tipoProveedor,this.registronotprev.value.tipoConsumidorProv);
			  if (response.value.tipoProveedor == this.registronotprev.value.tipoConsumidor) {
			  this.existingMode = true;
			  this.existingProveedor = response.value;
			  this.initregistronotprev(null,response.value);
			  this._existe = '1';
			  } else {
				  this.existingMode = false;
				  this.existingProveedor = null;
				  this._existe = '0';	
				  this.onChangeconprov();
				  alert("No existe información para el proveedor seleccionado");
			  }
			} else {
			  this.existingMode = false;
			  this.existingProveedor = null;
			  this._existe = '0';
		      this.onChangeconprov(); 		
			  alert("No existe información para el proveedor seleccionado");
			}
		  }
		);
	 }
	 //this._declaracion = null;
  }

  public onDepartamentoChanged() {
    // cargar municipios
    /*this.municipiosSubscription = this.departamentosService.fetchMunicipios(this.registronotprev.value.codigoDepartamento).subscribe(
      res => {
        this.municipios = res.value;
      }
    );*/
  }

  public onMunicipioChanged() {
    // cargar sedes
	/*
    this.municipiosSubscription = this.municipiosService.fetchSedes(this.registronotprev.value.codigoMunicipio).subscribe(
      res => {
        this.sedes = res.value;
      }
    );
	*/
  }
  
  public valOnSummit() {

  }

  public onSubmit() {
	// verifica que esten ingresados los 2 archivos
	if (this.uploader.queue.length <= 0) {
		alert("Debe de ingresar Declaración jurada.");
		//this.showSpinner = false;
		return;
	} 
	//______________________________________________________
	let url : string = "";
	let idcp : number ; 
	
	if (this.registronotprev.value.tipoConsumidorProv == 1) { //consumidor
	    url = 'consumidores/ins_not_elect';
		idcp = this.existingConsumidor.idConsumidor;
	} else {
		url = 'proveedores/ins_not_elect';
		idcp = this.existingProveedor.idProveedor;
	}
		this.consumidoresService.saveDataCP(url, idcp, this.registronotprev.value.tipoConsumidorProv).subscribe(
			  (retvalue) => {
			if(retvalue){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					//this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					//this.vanio = tempstr.anio;
					//this.vsecanio = tempstr.secuencia;
					//this.vidqueja = tempstr.idQueja;
					//this.presencial = tempstr.conciliacion;
				//*************************************************************************************
				console.info("total datos",this.uploader.queue.length);
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
							if (removeItem == this._declaracion) {
								//console.log("cambia url ");
								//this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+2+"&correo=0&data=0";
								this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor='+idcp+"&id_categoria_imagen="+21+"&correo=0&data="+this.registronotprev.value.tipoConsumidorProv;
								//console.log(this.uploader.queue[i].url);
								//console.log(this.uploader.queue[i].url);
								//console.log(removeItem);
							} 
							//console.log("modifica url - fin");	 
						}
						
				}
				  this.uploader.setOptions({
					//url: BASE_URL_REST_FILE2 + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
					itemAlias: 'file'
					
				  });
				
				  this.uploader.onCompleteAll = () => {
					// finalizo la carga de todos los archivos
					if (this.registronotprev.value.tipoConsumidorProv==1) {
						this.operationc.next(retvalue.value);
					} else {
						this.operationp.next(retvalue.value);
					}
				  };
				  this.uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
					// finalizo la carga de un archivo
					// this.vidimagen = this.vidimagen + 1;
				  };

				  this.uploader.uploadAll();
				} else {
					if (this.registronotprev.value.tipoConsumidorProv==1) {
						this.operationc.next(retvalue.value);
					} else {
						this.operationp.next(retvalue.value);
					}
					//this.operation.next(retvalue.value);
				}
				
				//*************************************************************************************
				this.success = true;
				alert("Se ha registrado con éxito su solicitud.");
				
						}else{
							////console.log('no llego');
						}
					}else{
						////console.log('Rest service response ERROR.');
					}  
			  }
		);
	this.onChangeconprov();

  }
 
  private nextStep(consumidor: Consumidor) {
    //this.registronotprev.reset();
    // guardar el consumidor en sesion
    //this.quejasService.consumidor = consumidor;
    // ir a pantalla de proveedor
    //this.router.navigate(['/proveedor']);
  }
  
  CerrarSesion(){
	  /*
		let loc_token=this._submitFormService.Get_token();
		console.log(loc_token);
		if(loc_token == '')
			this._seguridadService.RedireccionarLogin();
		this._seguridadService.Logout(loc_token).subscribe((retvalue)=>{
			console.log('cerrar sesion',retvalue);
			this._seguridadService.RedireccionarLogin();		
		},(error)=>{
			this._seguridadService.RedireccionarLogin();
		});*/
	}

	public onChangeconprov() {
		this._conprov = this.registronotprev.value.tipoConsumidorProv;
		this.registronotprev.reset();
		this.initregistronotprev(null,null);
		this._existe = '0';
	}		
	
	// manejo de archivo
	public onFileChanged(type: string) {
		//console.info(type);	
		this._declaIndex = this.uploader.queue.length - 1;
		console.info(this._declaracion);
		if (this.uploader.queue.length > 0) {
			if (type == 'DECLA') {
			  if (this._declaracion && this._declaIndex > -1) {
				let item = this.uploader.queue[this._declaIndex];
				this.uploader.removeFromQueue(item);
			  }
				this._declaracion = this.uploader.queue[this.uploader.queue.length - 1];
			  this._declaIndex = this.uploader.queue.length - 1;
			} 
			if (this.uploader.queue.length==0) {
				this._declaIndex = -1;
			} 
			if (this.uploader.queue.length==2) {
				this.successFile = false;
			} else {
					this.successFile = true;
			}
				console.info("onFileChange ",this._declaracion);
		}
	}
}
