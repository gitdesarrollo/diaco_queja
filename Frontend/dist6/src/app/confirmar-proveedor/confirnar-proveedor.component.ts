import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Router, Params, ActivatedRoute } from '@angular/router';
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
import { Confirmacion } from '../shared/confirmacion';
//import { ReCaptchaV3Service } from 'ngx-captcha';

@Component({
	selector: 'app-confirmar-proveedor',
	templateUrl: './confirmar-proveedor.component.html',
	styleUrls: ['./confirmar-proveedor.component.scss'],
})
export class ConfirmarProveedorComponent implements OnInit {

	registropcvForm: FormGroup;
	@ViewChild('filDpi')
	filDpi: ElementRef;
	continua: string = "0";
	paso: number;
	completado: boolean = false;
	vClave: string = "";
	success: boolean = false;
	mjsError: string = '';


	constructor(private router: Router
		, private dialog: MatDialog
		, private departamentosService: DepartamentosService
		, private municipiosService: MunicipiosService
		, private soap: NgxSoapService
		, private http: HttpClient
		, private proveedoresService: ProveedoresService
		//,private reCaptchaV3Service: ReCaptchaV3Service
		, private formBuilder: FormBuilder
		, private _route: ActivatedRoute
	) {

	}

	ngOnInit() {

		this._route.params.subscribe(
			(params: Params) => {
				this.vClave = params.clave;
				console.log("clave " + this.vClave);
			}
		);

	}



	public onSubmit() {


	}

	onDismissClicked() {
		this.success = false;
		this.continua = "0";
		this.paso = 0;
		this.completado = false;
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


	public confirmarCorreo() {
		console.log('confirmarCorreo ');
		let confirmacion:Confirmacion = new Confirmacion();;
		confirmacion.llave=this.vClave;
		this.proveedoresService.confirmarCorreoProveedor(confirmacion).subscribe(
			(retvalue) => {
				console.log(retvalue);

				if(retvalue["reason"] == 'OK'){
					  console.log('Confirmación correcta');
					  this.success=true;
					  
					 
				  }else{
					this.success=false;
					  console.log('Rest service response ERROR.');
				  }  
				
			
			}
		   
		  );

		this.completado = true;

	}


	/***
	  boton continuar
	***/
	public continuar() {
		this.paso = this.paso + 1;

		this.continua = this.paso.toString();


		console.info("continuar ", this.continua);
	}

	/***
	  boton regresar
	***/
	public regresar() {
		this.paso = this.paso - 1;

		this.continua = this.paso.toString();


		console.info("continuar ", this.continua);
	}

	//captcha
	public readonly siteKey = '6LcvoUgUAAAAAJJbhcXvLn3KgG-pyULLusaU4mL1';


}
