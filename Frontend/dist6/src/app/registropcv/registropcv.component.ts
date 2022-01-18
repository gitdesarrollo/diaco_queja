import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { FileUploader, FileItem } from "ng2-file-upload";
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BASE_URL_REST_FILE2 } from '../conc-virt-const';
import { ResponseRs } from '../shared/response-rs.model';
import { Subject } from 'rxjs';
import { SubmitFormService } from "../shared/submit-form.service";
import { Departamento } from '../shared/departamento.model';
import { DepartamentosService } from '../shared/departamentos.service';
import { Municipio } from '../shared/municipio.model';
import { MunicipiosService } from '../shared/municipios.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { NgxSoapService, Client, ISoapMethodResponse } from 'ngx-soap';
import { ProveedoresService } from '../shared/proveedores.service';
import { Proveedorpcv } from '../shared/proveedorpcv.model';
import swal from 'sweetalert2'
//import * as jsPDF from 'jspdf';
//import * as html2canvas from 'html2canvas';
//import { ReCaptchaV3Service } from 'ngx-captcha';

@Component({
	selector: 'app-registropcv',
	templateUrl: './registropcv.component.html',
	styleUrls: ['./registropcv.component.scss']
})
export class RegistropcvComponent implements OnInit {

	registropcvForm: FormGroup;
	@ViewChild('filDpi')
	filDpi: ElementRef;
	departamentos: Departamento[];
	departamentosSubscription: Subscription;
	municipios: Municipio[];
	municipiosSubscription: Subscription;
	existingProveedor: Proveedorpcv;
	nombre_: string;
	telefono_: string;
	correo_: string;
	continua: string = "0";
	paso: number;
	completado: boolean = false;

	uploader = new FileUploader({
		itemAlias: 'document',
		allowedMimeType: ['image/jpg', 'application/pdf','image/jpeg'] ,
		maxFileSize: 5*1024*1024 // 5 MB
	});

	otro: FileItem;

	dpi: FileItem;
	dpiIndex: number;
	formulario: FileItem;
	formularioIndex: number;
	nombramiento: FileItem;
	nombramientoIndex: number;
	patente: FileItem;
	patenteIndex: number;
	rtu: FileItem;
	rtuIndex: number;
	success: boolean = false;
	mjsError: string = '';
	str_usuario: string = '';
	client: Client;
	intA: string;
	intB: string;
	vidproveedor: number;
	$valorCaptcha: String;
	//variable que contiene la validación del captcha
	isValid: boolean = true;
	isValidTmp: boolean = false;
	//variable que guarda el cui del representate legal o propietario
	cui: string;
	//variable de validacion de correo en SAT
	errorCorreo: boolean = false;
	//variable de validacion de telefono en SAT
	errorTelefono: boolean = false;

	//variable de validacion de telefono en SAT
	cargando: boolean = false;


	operation = new Subject<Proveedorpcv>();


	constructor(private router: Router
		, private dialog: MatDialog
		, private departamentosService: DepartamentosService
		, private municipiosService: MunicipiosService
		, private soap: NgxSoapService
		, private http: HttpClient
		, private proveedoresService: ProveedoresService
		//,private reCaptchaV3Service: ReCaptchaV3Service
		, private formBuilder: FormBuilder
	) {

	}

	/**
	 * alruanoe
	 * Metodo que valida que se ingrese el captcha
	 * @param captchaResponse resultado del captcha
	 */
	resolved(captchaResponse: string) {
		console.log(`Resolved captcha with response ${captchaResponse}:`);

		this.$valorCaptcha = captchaResponse;
		if (captchaResponse) {
			this.isValid = true;
			this.isValidTmp = true;
		} else {
			this.isValid = false;
		}
		console.log(`isValid ${this.isValid}:`);
	}

	ngOnInit() {
		// cargar departamentos
		this.departamentosSubscription = this.departamentosService.fetchData().subscribe(
			res => {
				this.departamentos = res.value;
			}
		)
		this.initregistropcvForm(null);
	}



	initregistropcvForm(proveedor: Proveedorpcv) {
		console.log("cui initRegistro " + this.cui);
		if (proveedor) {
			this.nombre_ = proveedor.nombre;
			this.telefono_ = proveedor.telefono;
			if	(proveedor.correo) {
/* 				var caracteres = proveedor.correo.split('',2);
				for (let index = 0; index < caracteres.length; index++) {
					const element = caracteres[index];
					console.log(element);
				} */
				if (proveedor.correo.includes('F#')) {
					proveedor.correo = proveedor.correo.replace('F#','');
							
				}
			};

			this.correo_ = proveedor.correo;
			this.cui = proveedor.cui;
		}
		this.registropcvForm = new FormGroup({
			'nit': new FormControl(proveedor ? proveedor.nit : '', Validators.required)
			, 'telefono': new FormControl(proveedor ? this.telefono_ : '', Validators.required)
			, 'email': new FormControl(proveedor ? this.correo_ : '', Validators.required)
			, 'codigoDepartamento': new FormControl(proveedor ? proveedor.idDepartamento : '', Validators.required)
			, 'codigoMunicipio': new FormControl(proveedor ? proveedor.idMunicipio : '', Validators.required)
			, 'direccionDetalle': new FormControl(proveedor ? proveedor.direccion : '', Validators.required)
			//,'nombre' : new FormControl(proveedor ? proveedor.nombre : '', Validators.required)
			//,'razonSocial' : new FormControl(proveedor ? proveedor.razonSocial : '', Validators.required)
			, 'cui': new FormControl(proveedor ? this.cui : '', Validators.required)
			, 'tipoProveedor': new FormControl(1, Validators.required)
			, 'recaptcha': new FormControl('', Validators.required)
		});
		if (proveedor) {
			// seleccionar departamento
			this.onDepartamentoChanged(proveedor.idDepartamento != undefined ? proveedor.idDepartamento : 0);
			this.registropcvForm.patchValue({
				'codigoDepartamento': proveedor.idDepartamento
			});

			// seleccionar municipio
			this.onMunicipioChanged();
			this.registropcvForm.patchValue({
				'codigoMunicipio': proveedor.idMunicipio
			});
		}
	}

	onFileChanged(type: string) {
		if (type == 'FORM') {
			console.log('this.uploader.queue.length '+this.uploader.queue.length);
			if (this.formulario) {
				let formularioItem = this.uploader.queue[this.formularioIndex];
				this.uploader.removeFromQueue(formularioItem);
			}
			this.formulario = this.uploader.queue[this.uploader.queue.length - 1];
			this.formularioIndex = this.uploader.queue.length - 1;
		} else if (type == 'DPI') {
			if (this.dpi) {
				let dpiItem = this.uploader.queue[this.dpiIndex];
				this.uploader.removeFromQueue(dpiItem);
			}
			this.dpi = this.uploader.queue[this.uploader.queue.length - 1];
			this.dpiIndex = this.uploader.queue.length - 1;
		} else if (type == 'NOMB') {
			if (this.nombramiento) {
				let nombramientoItem = this.uploader.queue[this.nombramientoIndex];
				this.uploader.removeFromQueue(nombramientoItem);
			}
			this.nombramiento = this.uploader.queue[this.uploader.queue.length - 1];
			this.nombramientoIndex = this.uploader.queue.length - 1;
		} else if (type == 'PATE') {
			if (this.patente) {
				let patenteItem = this.uploader.queue[this.patenteIndex];
				this.uploader.removeFromQueue(patenteItem);
			}
			this.patente = this.uploader.queue[this.uploader.queue.length - 1];
			this.patenteIndex = this.uploader.queue.length - 1;
		} else if (type == 'RTU') {
			if (this.rtu) {
				
				let rtuItem = this.uploader.queue[this.rtuIndex];
				this.uploader.removeFromQueue(rtuItem);
			}
			this.rtu = this.uploader.queue[this.uploader.queue.length - 1];
			this.rtuIndex = this.uploader.queue.length - 1;
		}



	}

	onRemoveClicked(index: number) {
		let removeItem = this.uploader.queue[index];
		this.uploader.removeFromQueue(removeItem);
		if (index == this.formularioIndex) {
			this.formularioIndex = null;
			this.formulario = null;
		} else if (index == this.dpiIndex) {
			this.dpiIndex = null;
			this.dpi = null;
		} else if (index == this.nombramientoIndex) {
			this.nombramientoIndex = null;
			this.nombramiento = null;
		} else if (index == this.patenteIndex) {
			this.patenteIndex = null;
			this.patente = null;
		} else if (index == this.rtuIndex) {
			this.rtuIndex = null;
			this.rtu = null;
		}
	}

	public onSubmit() {
		console.log("submit " + this.cui)

		let proveedor: Proveedorpcv = new Proveedorpcv();
		if (this.registropcvForm.value.nit == "" || this.registropcvForm.value.nit == undefined) {
			this.mjsError += "- Ingrese NIT.  " + "\r\n";
		} else {
			proveedor.nit = this.registropcvForm.value.nit;
		}
		if (this.registropcvForm.value.telefono == "" || this.registropcvForm.value.telefono == undefined) {
			this.mjsError += "- Ingrese numero de telefono.  " + "\r\n";
		} else {
			proveedor.telefonoNotif = this.telefono_;
		}
		if (this.registropcvForm.value.email == "" || this.registropcvForm.value.email == undefined) {
			this.mjsError += "- Ingrese correo.  " + "\r\n";
		} else {
			/* proveedor.correoNotif = this.correo_; */
			proveedor.correoNotif = this.registropcvForm.value.email;
		}

		if (this.cui == "" || this.cui == undefined) {
			this.mjsError += "- Ingrese CUI del dueño o Representante legal.  " + "\r\n";
		} else {
			console.log("this.cui " + this.cui);
			console.log("this.existingProveedor.cui " + this.existingProveedor.cui);
			if (this.existingProveedor.cui != undefined || this.existingProveedor.cui != null) {
				proveedor.cui = this.existingProveedor.cui;
			} else {
				proveedor.cui = this.cui;
			}
		}

		if (this.registropcvForm.value.direccionDetalle == "" || this.registropcvForm.value.direccionDetalle == undefined) {
			this.mjsError += "- Ingrese su direccion.  " + "\r\n";
		} else {
			proveedor.direccion = this.registropcvForm.value.direccionDetalle;
		}
		if (this.registropcvForm.value.codigoDepartamento > 0) {
			proveedor.idDepartamento = this.registropcvForm.value.codigoDepartamento;
		} else {
			this.mjsError += "- Seleccione departamento.  " + "\r\n";
		}
		if (this.registropcvForm.value.codigoMunicipio > 0) {
			proveedor.idMunicipio = this.registropcvForm.value.codigoMunicipio;
		} else {
			this.mjsError += "- Seleccione municipio.  " + "\r\n";
		}
		if (this.registropcvForm.value.tipoProveedor == '1') {
			if (this.dpi != null) {
			} else {
				this.mjsError += "- Ingrese DPI dueño o Representante legal.  " + "\r\n";
			}
			if (this.rtu != null) {
			} else {
				this.mjsError += "- Ingrese Constancia de Registro de Inscripción Tributaria (RTU).  " + "\r\n";
			}
		}
		if (this.registropcvForm.value.tipoProveedor == '2') {
			if (this.dpi != null) {
			} else {
				this.mjsError += "- Ingrese DPI dueño o Representante legal.  " + "\r\n";
			}
			if (this.rtu != null) {
			} else {
				this.mjsError += "- Ingrese Constancia de Registro de Inscripción Tributaria (RTU).  " + "\r\n";
			}
			if (this.patente != null) {
			} else {
				this.mjsError += "- Ingrese Patente de sociedad y empresa.  " + "\r\n";
			}
		}
		if (this.registropcvForm.value.tipoProveedor == '3') {
			if (this.formulario != null) {
			} else {
				this.mjsError += "- Ingrese Formulario de registro.  " + "\r\n";
			}
			if (this.nombramiento != null) {
			} else {
				this.mjsError += "- Ingrese Nombramiento de representante legal.  " + "\r\n";
			}
			if (this.dpi != null) {
			} else {
				this.mjsError += "- Ingrese DPI dueño o Representante legal.  " + "\r\n";
			}
			if (this.patente != null) {
			} else {
				this.mjsError += "- Ingrese Patente de sociedad y empresa.  " + "\r\n";
			}
			if (this.rtu != null) {
			} else {
				this.mjsError += "- Ingrese Constancia de Registro de Inscripción Tributaria (RTU).  " + "\r\n";
			}
		}

		if (this.mjsError == '') {

		} else {
			//alert(this.mjsError);
			//aalruanoe - 16.02.2020
			//se cambia el manejo de mensajes de error
			this.Alerta("Advertencia", this.mjsError);
			this.mjsError = "";
			return false;
		}

		proveedor.nombre = this.existingProveedor.nombre;
		proveedor.id = this.existingProveedor.id;
		proveedor.razonSocial = this.existingProveedor.razonSocial;
		proveedor.telefono = this.existingProveedor.telefono;
		/* proveedor.correo = this.existingProveedor.correo; */
		proveedor.correo = this.registropcvForm.value.email;
		proveedor.notaRechazo = this.existingProveedor.notaRechazo;
		proveedor.estado = (this.existingProveedor.estado ? this.existingProveedor.estado : 'P');
		proveedor.tipo_proveedor = this.registropcvForm.value.tipoProveedor;
		console.log('a grabar proveedor ')
		console.log(proveedor);		
		this.cargando=true;
		this.proveedoresService.saveDataPCV(proveedor).subscribe(			
			(retvalue) => {

				if (retvalue) {
					var tempstr = retvalue['value'];

					if (tempstr != null && tempstr != '') {
						//this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						//this.vanio = tempstr.anio;
						//this.vsecuencia = tempstr.noQueja;
						this.vidproveedor = tempstr.id;
						if (this.uploader.queue.length > 0) {
							let estado: boolean = true;
							// this.success = true;
							this.uploader.onBeforeUploadItem = (removeItem) => {
								for (let i = 0; i < this.uploader.queue.length; i++) {
									//console.log("modifica url - inicio");
									//console.log(i);
									let removeItem = this.uploader.queue[i];
									if (removeItem == this.dpi) {
										//console.log("cambia url dpi");
										this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 16 + "&correo=0&data=0";
										//console.log(this.uploader.queue[i].url);
										//console.log(removeItem);
									} else {
										if (removeItem == this.formulario) {
											//console.log("cambia url formulario");
											this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 14 + "&correo=0&data=0";
											//console.log(this.uploader.queue[i].url);	
											//console.log(removeItem);
										} else {
											if (removeItem == this.nombramiento) {
												//console.log("cambia url nombramiento");
												this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 15 + "&correo=0&data=0";
												//console.log(this.uploader.queue[i].url);	
												//console.log(removeItem);
											} else {
												if (removeItem == this.patente) {
													//console.log("cambia url patente");
													this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 17 + "&correo=0&data=0";
													//console.log(this.uploader.queue[i].url);	
													//console.log(removeItem);
												} else {
													if (removeItem == this.rtu) {
														//console.log("cambia url patente");
														this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 22 + "&correo=0&data=0";
														//console.log(this.uploader.queue[i].url);	
														//console.log(removeItem);
													} else {
														//console.log("cambia url otro");
														this.uploader.queue[i].url = BASE_URL_REST_FILE2 + 'proveedores/upload?id_proveedor=' + this.vidproveedor + "&id_categoria_imagen=" + 1 + "&correo=0&data=0";
														//console.log(this.uploader.queue[i].url);
														//console.log(removeItem);
													}
												} // remove patente
											} // remove nombramiento
										} // remove formulario
									} //remove dpi
									//console.log("modifica url - fin");	
								}

							}
							//console.info("ingreso files");
							this.uploader.setOptions({
								itemAlias: 'file'
							});

							this.uploader.onCompleteAll = () => {
								// finalizo la carga de todos los archivos
								///this.vidimagen = this.vidimagen - 1;
								this.operation.next(retvalue.value);


								console.info("ingreso files onclomplete", estado);
								if (estado) {
									this.success = true;
									this.completado = false;
									this.initregistropcvForm(null);
									while (this.uploader.queue.length) {
										this.uploader.queue[0].remove();
									}

									this.Alerta("exito", null);
								} else {
									//this.success = false;									
									//alert("Ocurrio un error al cargar sus archivos, por favor verifique.  Gracias.");
									//aalruanoe 18.02.2020
									//se cambia la forma de presentar los mensajes de error
									this.cargando=false;
									this.Alerta("Error", "Ocurrio un error al cargar sus archivos, por favor verifique.  Gracias.");
								}
							};
							this.uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
								// finalizo la carga de un archivo
								//this.vidimagen = this.vidimagen - 1;
								//console.info("ingreso files oncompleteitem",status);
								if (status == 0) {
									//this.success = false;
									estado = false;
								} else {
									//this.success = true;
								}
							};

							this.uploader.uploadAll();
							//console.info("ingreso files uploadall");
						} else {
							this.operation.next(retvalue.value);
						}
					} else {
						////console.log('no llego');
						this.cargando=false;
					}
				} else {
					this.cargando=false;
					console.log('Rest service response ERROR.');
				}


			}

		);
		//console.info(this.vsecuencia);
		//console.info(this.vanio);

	}
	/**
	 * alruanoe
	 * 03.02.2020
	 * Metodo para refrescar la página
	 */
	onReload() {
		window.location.reload();
	}
	onDismissClicked() {
		this.success = false;
		this.continua = "0";
		this.paso = 0;
		this.nombre_ = "";
		this.telefono_ = "";
		this.correo_ = "";
		this.dpi = null;
		this.otro = null;
		this.formulario = null;
		this.nombramiento = null;
		this.patente = null;
		this.completado = false;
		this.errorCorreo = false;
		this.errorTelefono = false;
		this.cargando=false;
		//variable que valida el captcha
		this.isValid = false;
		//variable que guarda el cui del representate legal o propietario
		this.cui = "";
		//this.uploader.queue = null;

		if (this.uploader.queue.length > 0) {
			let pItem = this.uploader.queue[0];
			this.uploader.removeFromQueue(pItem);
		}
		this.registropcvForm.reset();

	}

	onHomeClicked() {
		this.CerrarSesion();
		this.router.navigate(['/Login']);
	}

	CerrarSesion() {
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


	public onDepartamentoChanged(_departamento: number) {
		// cargar municipios
		//console.log("depto ",_departamento);
		this.municipiosSubscription = this.departamentosService.fetchMunicipios(_departamento != undefined ? _departamento : this.registropcvForm.value.codigoDepartamento).subscribe(
			res => {
				this.municipios = res.value;
			}
		);
	}

	public onMunicipioChanged() {
		// cargar sedes
		/*
		this.municipiosSubscription = this.municipiosService.fetchSedes(this.registropcvForm.value.idMunicipio).subscribe(
		  res => {
			this.sedes = res.value;
		  }
		);
		*/
	}

	/**Obtener pdf instructivo */
/*	generarPdf(seccion: string) {    
   
		var data = document.getElementById(seccion);  
		html2canvas(data).then(canvas => {  
		  // Few necessary setting options  
		  var imgWidth = 208;   
		  var pageHeight = 295;    
		  var imgHeight = canvas.height * imgWidth / canvas.width;  
		  var heightLeft = imgHeight;    
		  const contentDataURL = canvas.toDataURL('image/png')  
		  let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
		  var position = 0;  
		  pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)  
		  pdf.save('instructivo_'+seccion+'.pdf'); // Generated PDF   
		});  
	
	  }*/

	/***
	  busca nit en servicio de SAT
	***/
	public findByNITtoSAT() {
		console.log('findByNITtoSAT ');
		let pnit = this.registropcvForm.value.nit;
		
		this.completado = true;
		pnit=pnit.replace('-','').toUpperCase();

		console.log('nit modificado '+pnit);
		this.proveedoresService.fetchDataByWsProveedor(pnit).subscribe(
			(response) => {

				console.log("response ");
				console.log(response)
				let tempstr = response['value'];

				console.log("tempstr ");
				console.log(tempstr);
				if (tempstr != null || tempstr != undefined) {

					console.info("existe", tempstr);
					this.initregistropcvForm(tempstr);
					this.existingProveedor = tempstr;
					if (this.existingProveedor.telefono == null || this.existingProveedor.telefono == undefined || this.existingProveedor.telefono == " ") {
						this.errorTelefono = true;
						this.Alerta("ErrorTelefono", null);

					} else {
						if (this.existingProveedor.correo == null || this.existingProveedor.correo == undefined || this.existingProveedor.correo == " ") {
							this.errorCorreo = true;
							this.Alerta("ErrorCorreo", null);
						} else {
							this.continua = "1";
							console.log('this.continua ' + this.continua);
							this.paso = 1;
							this.completado = true;
						}
					}

				} else {

					//aalruanoe 18.02.2020
					//se cambia la forma de presentar el error.
					this.Alerta("Error", "No existe información para el NIT ingresado, por favor verifique. Gracias");
					this.registropcvForm.reset();
					this.completado = false;
				}
			}
		);
		//console.info("findByTokenProveedor end ",this.quejasService.quejat);
	}


	/***
	 * alruanoe
	 * boton continuar
	***/
	public continuar() {
		this.paso = this.paso + 1;

		this.continua = this.paso.toString();


		console.info("continuar ", this.continua);
	}

	/***
	 * aalruanoe 
	 * boton regresar
	***/
	public regresar() {
		this.paso = this.paso - 1;
		this.continua = this.paso.toString();
		console.info("continuar ", this.continua);
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
					title: 'Su solicitud ha sido registrada exitosamente.',
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



}
