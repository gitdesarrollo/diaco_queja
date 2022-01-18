//import { Component, OnInit } from '@angular/core';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
//import { ormGroup,  FormControl } from '@angular/forms';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Subscription, Subject, Observable, of } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';
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
//import { MyCustomDialogService } from '../my-custom-dialog/my-custom-dialog.service';

import { MatDialog, MatDialogConfig } from '@angular/material';
import { BuscaprovComponent } from '../buscaprov/buscaprov.component';

import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { ResponseRs } from '../shared/response-rs.model';
import { isUndefined } from 'util';

import Swal from 'sweetalert2';

@Component({
	selector: 'app-quejaext',
	templateUrl: './quejaext.component.html',
	styleUrls: ['./quejaext.component.scss']
})


export class QuejaextComponent implements OnInit {
	public nproveedor: string = "";
	public idproveedor: string = "";
	public conciliacion: string = "";
	public vanio: number = 0
	public vsecanio: number = 0;
	public vsecuencia2: string = "";
	public vidqueja: number = 0;
	public vidimagen: number = 3;

	public vdpi: number = -1;
	public vfac: number = -1;

	public vact: number = -1;

	//public vid: string = "";
	private contador = 0
	private contador1 = 0

	customDialogForm: FormGroup;

	quejaForm: FormGroup;
	quejaOperationSubscription: Subscription;
	existingConsumidor: Consumidor;
	existingProveedor: Proveedor;
	existingModeConsumidor: boolean = false;
	existingModeProveedor: boolean = false;
	existingSecuencia: Secuencia;
	vsecuencia: string = "";

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
	successFile: boolean = true;
	showSpinner: boolean = false;


	operation = new Subject<Quejaini>();

	factRepetida: boolean = false;
	factNoRepetida: boolean =false;
	conteoRepetido: number =0;
	serieNumeroFactura: string = "";
	mostrarCamposProveedor: boolean = false;

	//constructor() { }
	constructor(private router: Router,
		private dialog: MatDialog,
		private consumidoresService: ConsumidoresService,
		private proveedoresService: ProveedoresService,
		private quejasService: QuejasIniService
		//,private customDialog: MyCustomDialogService
		//,private fb: FormBuilder

	) {
		//console.info(this._route.snapshot.paramMap.get('id'));

	}

	onKey(event){
		this.contador = event.target.value.length
	}
	onKey1(event){
		this.contador1 = event.target.value.length
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
				this.conciliacion = "";
			}
		);
		/*
		this.customDialogForm = fb.group({
			dialogTitle: ['Title'],
			dialogMsg: [''],
			dialogType: ['alert'],
			okBtnColor: [''],
			okBtnLabel: [''],
			cancelBtnColor: [''],
			cancelBtnLabel: ['']
		  });
		  */
		//this._route.params.subscribe(
		//  (params: Params) => {
		//    this.vid = params.id;
		//  }
		//);

		this.initQuejaForm();
		//this.initConsumidorForm(null);
		//this.vid = this._route.snapshot.paramMap.get('id')
		//console.info(this.vid);
	}

	initQuejaForm() {
		this.quejaForm = new FormGroup({
			'nombre': new FormControl('', Validators.required),
			'apellido': new FormControl('', Validators.required),
			'dpiPasaporte': new FormControl('', Validators.required),
			'telefono': new FormControl('', Validators.required),
			'correo': new FormControl('', Validators.required),
			'detalleQueja': new FormControl('', Validators.required),
			'solicitaQue': new FormControl('', Validators.required),
			'serieNumeroFactura': new FormControl('', Validators.required),
			'nitProveedor': new FormControl(''),
			'ubicacion': new FormControl('')
		});
		this.nproveedor = "";
		this.conciliacion = "";
		this.dpiIndex = -1;
		this.facturaIndex = -1;
		////console.log("inicializa");
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
					this.dpiIndex = this.dpiIndex - 1;
				}



			} else if (type == 'DPI') {
				if (this.dpi && this.dpiIndex > -1) {
					let dpiItem = this.uploader.queue[this.dpiIndex];
					this.uploader.removeFromQueue(dpiItem);



				}
				this.dpi = this.uploader.queue[this.uploader.queue.length - 1];
				this.dpiIndex = this.uploader.queue.length - 1;
				if (this.dpiIndex == this.facturaIndex) {
					this.facturaIndex = this.facturaIndex - 1;
				}


			}
			if (this.uploader.queue.length == 0) {
				this.dpiIndex = -1;
				this.facturaIndex = -1;
			}

			//console.info(this.factura);
			//console.info(this.dpi);
			//console.info(this.uploader.queue.length);
			//console.info(this.facturaIndex);
			//console.info(this.dpiIndex);
			if (this.uploader.queue.length == 2) {
				this.successFile = false;
			} else {
				this.successFile = true;
			}
		}
	}


	/*  onRemoveClicked(index: number) {
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
		this.showSpinner = true;
		let queja: Quejaini = new Quejaini();
		queja.nombre = this.quejaForm.value.nombre;
		queja.apellido = this.quejaForm.value.apellido;
		queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
		queja.telefono = this.quejaForm.value.telefono;
		queja.correo = this.quejaForm.value.correo;
		queja.detalleQueja = this.quejaForm.value.detalleQueja;
		queja.solicitaQue = this.quejaForm.value.solicitaQue;
		queja.nitProveedor = this.quejaForm.value.nitProveedor;
		queja.ubicacion = this.quejaForm.value.ubicacion;
		queja.anio = 0;
		queja.secuencia = 0;
		queja.idFuente = "Web user";
		queja.facturaNumero = this.quejaForm.value.serieNumeroFactura;
		if (this.existingModeConsumidor) {
			queja.idConsumidor = this.existingConsumidor.idConsumidor;
		} else {
			queja.idConsumidor = 0;
		}
		if (this.existingModeProveedor) {
			queja.idProveedor = this.existingProveedor.idProveedor;
		} else {
			queja.idProveedor = 0;
		}



		// verifica que esten ingresados los 2 archivos
		if (this.uploader.queue.length != 2) {
			alert("Debe de ingresar Datos adjuntos requeridos.");
			this.showSpinner = false;
			return;
		}

		//coloca los archivos en orden.
		if (this.uploader.queue.length >= 2) {
			let removeItem = this.uploader.queue[1];
			this.uploader.removeFromQueue(removeItem);
			removeItem = this.uploader.queue[0];
			this.uploader.removeFromQueue(removeItem);
			this.uploader.queue.push(this.factura);
			this.uploader.queue.push(this.dpi);
			console.info(this.uploader.queue.length);
		}


		//queja = this.quejasService.createQueja(queja, this.uploader).susbribe;
		this.quejasService.saveData(queja, '').subscribe(
			(retvalue) => {
				this.showSpinner = true;

				if (retvalue) {
					var tempstr = retvalue['value'];
					if (tempstr != null && tempstr != '') {
						//this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.vanio = tempstr.anio;
						this.vsecanio = tempstr.secuencia;
						this.vidqueja = tempstr.idQueja;
						Swal.fire('Registro exitoso...', `Su número de Queja es: <h1 class="display-1">${this.vsecanio}-${this.vanio}</h1>`, 'success');



						if (this.uploader.queue.length > 0) {
							//console.log(this.vidqueja);

							this.uploader.onBeforeUploadItem = (removeItem) => {
								//removeItem.withCredentials = false;
								//removeItem.url = BASE_URL_REST_FILE + 'quejas/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen;
								//BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
								for (let i = 0; i < this.uploader.queue.length; i++) {
									console.log("modifica url - inicio");
									console.log(i);
									let removeItem = this.uploader.queue[i];
									if (removeItem == this.dpi) {
										console.log("cambia url dpi");
										this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + this.vidqueja + "&id_categoria_imagen=" + 2 + "&correo=0&data=0";
										console.log(this.uploader.queue[i].url);
										console.log(removeItem);
									} else {
										if (removeItem == this.factura) {
											console.log("cambia url factura");
											this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + this.vidqueja + "&id_categoria_imagen=" + 3 + "&correo=0&data=0";
											console.log(this.uploader.queue[i].url);
											console.log(removeItem);
										} else {
											console.log("cambia url otro");
											this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + this.vidqueja + "&id_categoria_imagen=" + 1 + "&correo=0&data=0";
											console.log(this.uploader.queue[i].url);
											console.log(removeItem);
										}
									}
									console.log("modifica url - fin");
								}

							}


							this.uploader.setOptions({
								//url: BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
								itemAlias: 'file'

							});

							this.uploader.onCompleteAll = () => {
								// finalizo la carga de todos los archivos
								this.operation.next(retvalue.value);
								this.showSpinner = false;
								//console.info(this.uploader.nativeElement.value);
							};
							this.uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
								// finalizo la carga de un archivo
								this.vidimagen = this.vidimagen + 1;
								//console.info(this.uploader.nativeElement.value);
							};

							this.uploader.uploadAll();
						} else {
							this.operation.next(retvalue.value);
						}

						this.success = true;
					} else {
						////console.log('no llego');
					}
				} else {
					////console.log('Rest service response ERROR.');
				}


			}


		);

		this.showSpinner = false;
		

		this.onDismissClicked();

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
		this.successFile = false;
		this.initQuejaForm();

		////console.info("onDismm");

	}


	private initConsumidor(consumidor: Consumidor, tipo: number) {
		if (tipo == 0) {
			this.quejaForm = new FormGroup({
				'nombre': new FormControl(consumidor ? consumidor.nombre1 +
					(consumidor ? ' ' + (consumidor.nombre2 == null ? '' : consumidor.nombre2) +
						(consumidor != null ? ' ' + (consumidor.nombre3 == null ? '' : consumidor.nombre3) : '') : '') : '', Validators.required),
				'apellido': new FormControl(consumidor ? consumidor.apellido1 + (consumidor ? ' ' + (consumidor.apellido2 == null ? '' : consumidor.apellido2) : '') : '', Validators.required),
				//'apellidoCasada': new FormControl(consumidor ? consumidor.apellidoCasada : ''),
				'telefono': new FormControl(consumidor ? consumidor.telefono : '', Validators.required),
				//'telefonoDomicilio': new FormControl(consumidor ? consumidor.domicilio : ''),
				'correo': new FormControl(consumidor ? consumidor.correoElectronico1 : '', Validators.email),
				'dpiPasaporte': new FormControl(consumidor ? consumidor.documentoIdentificacion : '', Validators.required),
				'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
				'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
				'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor),
				'serieNumeroFactura': new FormControl(this.quejaForm.value.serieNumeroFactura, Validators.required),
				'ubicacion': new FormControl(this.quejaForm.value.ubicacion)

			});
		} else {
			this.quejaForm = new FormGroup({
				'nombre': new FormControl(consumidor ? consumidor.nombre1 +
					(consumidor ? ' ' + (consumidor.nombre2 == null ? '' : consumidor.nombre2) +
						(consumidor != null ? ' ' + (consumidor.nombre3 == null ? '' : consumidor.nombre3) : '') : '') : '', Validators.required),
				'apellido': new FormControl(consumidor ? consumidor.apellido1 + (consumidor ? ' ' + (consumidor.apellido2 == null ? '' : consumidor.apellido2) : '') : '', Validators.required),
				//'apellidoCasada': new FormControl(consumidor ? consumidor.apellidoCasada : ''),
				'telefono': new FormControl(consumidor ? consumidor.telefono : '', Validators.required),
				//'telefonoDomicilio': new FormControl(consumidor ? consumidor.domicilio : ''),
				'correo': new FormControl(consumidor ? consumidor.correoElectronico1 : '', Validators.email),
				'dpiPasaporte': new FormControl(this.quejaForm.value.dpiPasaporte, Validators.required),
				'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
				'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
				'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor),
				'serieNumeroFactura': new FormControl(this.quejaForm.value.serieNumeroFactura, Validators.required),
				'ubicacion': new FormControl(this.quejaForm.value.ubicacion)

			});
		}
		this.findBySecuencia();
		//////console.log("inicializa consumidor");
	}

	private initProveedor(nit: string) {
		this.quejaForm = new FormGroup({
			'nombre': new FormControl(this.quejaForm.value.nombre, Validators.required),
			'apellido': new FormControl(this.quejaForm.value.apellido, Validators.required),
			'telefono': new FormControl(this.quejaForm.value.telefono, Validators.required),
			'correo': new FormControl(this.quejaForm.value.correo, Validators.email),
			'dpiPasaporte': new FormControl(this.quejaForm.value.dpiPasaporte, Validators.required),
			'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
			'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
			'nitProveedor': new FormControl(nit),
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
			'serieNumeroFactura': new FormControl(this.quejaForm.value.serieNumeroFactura, Validators.required),
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
					this.initConsumidor(response.value, 0);
					//queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
				} else {
					this.existingModeConsumidor = false;
					this.existingConsumidor = null;
					this.initConsumidor(null, 1);
				}
			}
		);
		//////console.log("ingreso al onDocumentoIdentificacionChanged");
	}



	public onProveedorChanged() {



		const dialogConfig = new MatDialogConfig();
		this.mostrarCamposProveedor = true;

		dialogConfig.disableClose = false;
		dialogConfig.autoFocus = true;

		const dialogRef = this.dialog.open(BuscaprovComponent, dialogConfig);

		dialogRef.afterClosed().subscribe(
			data => {
				if (!isUndefined(data)) {
					console.info(data);
					// inicializar formulario con valores predeterminados
					this.existingModeProveedor = true;
					this.existingProveedor = data;
					this.initProveedor(data.nitProveedor);
					this.nproveedor = data.nombre;
					this.idproveedor = data.idProveedor;
					this.conciliacion = data.habilitadoConciliacionPrevia;

				} else {
					this.existingModeProveedor = false;
					this.existingProveedor = null;
					this.nproveedor = "";
					this.conciliacion = "";
				}



			}, error => console.info('Undefined data. No will insert 2')); //this.logService.print(error, LogService.ERROR_MSG));

		/*
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
	  */


	}

	private initNoQueja(queja: Queja) {
		this.vanio = (queja ? queja.anio : 0);
		this.vsecanio = (queja ? queja.noQueja : 0);
		////console.info('initNoQueja|');
		////console.info(this.vanio);
		////console.info(this.vsecanio);
	}

	public onFacturaChanged(){
		console.log('Factura Changed');
		this.conteoRepetido=0;
		this.factRepetida=false;
		this.quejasService.fetchDataFact(this.idproveedor).subscribe(
			(response) => {
				if(response["reason"] == 'OK'){
					var tempstr=response['value'];
					if(tempstr != null && tempstr != '')	{
						
						console.log('Cantidad de Objetos en Array: '+ tempstr.length);

						for (let i=0; i < tempstr.length; i++){
							console.log('Valor de respuesta: '+ tempstr[i].idQueja);
							console.log('Valor de serieNumeroFactura: '+ this.quejaForm.value.serieNumeroFactura);
							
							if (tempstr[i].facturaNumero == this.quejaForm.value.serieNumeroFactura) {
								console.log('Es igual');
								this.conteoRepetido++;
							} else {
								console.log('No es igual');
							}

							if (this.conteoRepetido>0) {
								/* this.factRepetida=true; */
								this.factNoRepetida=false;
								Swal.fire({
									title: 'Factura ó Contrato existente', 
									text: `La serie y número de Factura ó contrato: ${this.quejaForm.value.serieNumeroFactura} ya se encuentra en base de datos.`, 
									icon: 'error',
									confirmButtonText: `Regresar`,
									showConfirmButton: true,
									
								})/* .then((result) => {
									
									if (result.isConfirmed) {
									  Swal.fire('Saved!', '', 'success')
									}
								}) */
								
								this.serieNumeroFactura=""

								
							}else{
								this.factNoRepetida=true;
							}
						}

						/* this.confacc_list=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.addCheckboxes();
						console.log(this.confacc_list);
						this.btnvisible=true; */
					}else{
						/* this.confacc_list='';
						this.tabledata=false; */
					}
					/* this.flagformvisible++; */
				}else{
					console.log('Rest service response ERROR.');
					/* this.flagInfoError=true;
					this.SetSecTimerInfoError(); */
				}	
			}
		);
	}

	public findSecuenciaId() {
		if (this.vsecuencia = "") {
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


	public openCustomDialog() {
		console.log("this.customDialogForm.value");
		//this.customDialog.openAlertDialog(this.customDialogForm.value);
		this.success = false;
	}


}
