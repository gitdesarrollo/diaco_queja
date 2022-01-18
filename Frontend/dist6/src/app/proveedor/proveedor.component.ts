import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Pais } from '../shared/pais.model';
import { Subscription } from 'rxjs';
import { Departamento } from '../shared/departamento.model';
import { PaisesService } from '../shared/paises.service';
import { DepartamentosService } from '../shared/departamentos.service';
import { ProveedoresComunesService } from '../shared/proveedores-comunes.service';
import { ProveedorComun } from '../shared/proveedor-comun.model';
import { SucursalesNit } from '../shared/sucursales-nit.model';
import { ActividadEconomica } from '../shared/actividad-economica.model';
import { ActividadesEconomicasService } from '../shared/actividades-economicas.service';
import { TipoComercio } from '../shared/tipo-comercio.model';
import { TiposComercioService } from '../shared/tipos-comercio.service';
import { MunicipiosService } from '../shared/municipios.service';
import { Municipio } from '../shared/municipio.model';
import { Sede } from '../shared/sede.model';
import { Proveedor } from '../shared/proveedor.model';
import { ProveedoresService } from '../shared/proveedores.service';
import { QuejasService } from '../shared/quejas.service';
import { Router } from '@angular/router';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";
import { Queja } from '../shared/queja.model';

@Component({
  selector: 'app-proveedor',
  templateUrl: './proveedor.component.html',
  styleUrls: ['./proveedor.component.scss']
})
export class ProveedorComponent implements OnInit {
  proveedorForm: FormGroup;
  proveedoresComunes: ProveedorComun[];
  proveedoresComunesSubscription: Subscription;
  sucursalesNit : SucursalesNit[];
  sucursalesNitSubscription : Subscription;
  
  departamentos: Departamento[];
  departamentosSubscription: Subscription;
  municipios: Municipio[];
  municipiossuc: Municipio[];
  municipiosSubscription: Subscription;
  sedes: Sede[];
  sedesSubscription: Subscription;
  actividadesEconomicas: ActividadEconomica[];
  actividadesEconomicasSubscription: Subscription;
  tiposComercio: TipoComercio[];
  tiposComercioSubscription: Subscription;
  proveedorOperationSubscription: Subscription;
  sucursalOperationSubscription: Subscription;
  existingMode: boolean = false;
  existingProveedor: Proveedor;
  mjsError:string  = '';
  str_usuario:string = '';
  str_queja: string = "";
  continuarp: string = '1';
  nuevasucursal : string = '0';
  nitProveedorSeleccionado : string = '';

  //addsucursal : SucursalesNit; 

  constructor(private router: Router,
    private proveedoresComunesService: ProveedoresComunesService,
    private departamentosService: DepartamentosService,
    private municipiosService: MunicipiosService,
    private actividadesEconomicasService: ActividadesEconomicasService,
    private proveedoresService: ProveedoresService,
    private quejasService: QuejasService,
	private _submitFormService:SubmitFormService,
	private _seguridadService:SeguridadService) {
	//this.str_usuario= "Usuario: " + this._submitFormService.Get_username();		
	}

  ngOnInit() {
    // validar que haya un consumidor en sesion
	//////console.info(this.quejasService.quejat);
	if ( this.quejasService.quejat == undefined) {
		//////console.info("quejat",this.quejasService.quejat);
		this.continuarp = "0"
		this.router.navigate(['/consumidor/./.']);
		//return;
	}
	//////console.info(this.quejasService.consumidor, this.quejasService.vexisteConsumidor);
    if (!this.quejasService.consumidor || !this.quejasService.quejat) {
		//continuar: string = '0';
		//////console.info("consumidor",this.quejasService.quejat);
		this.router.navigate(['/consumidor/./.']);
    }
    // cargar proveedores comunes
    this.proveedoresComunesSubscription = this.proveedoresComunesService.fetchData().subscribe(
      res => {
        this.proveedoresComunes = res.value;
      }
    );
    // cargar departamentos
    this.departamentosSubscription = this.departamentosService.fetchData().subscribe(
      res => {
        this.departamentos = res.value;
      }
    );
    // cargar actividades economicas
    this.actividadesEconomicasSubscription = this.actividadesEconomicasService.fetchData().subscribe(
      res => {
        this.actividadesEconomicas = res.value;
      }
    );
    // listener cuando finaliza la operacion de creacion
	this.proveedorOperationSubscription = this.proveedoresService.operation.subscribe(
      (proveedor: Proveedor) => {
        this.nextStep(proveedor);
      }
    );
	
	// listener cuando finaliza la operacion de creacion
	this.sucursalOperationSubscription = this.proveedoresService.operations.subscribe(
      (sucursal: SucursalesNit) => {
        this.nextSteps(sucursal);
      }
    );
	
	this.initProveedorForm(null);
	this.findByTokenProveedor();
  }

  private initProveedorForm(proveedor: Proveedor) {
	  //////console.info(proveedor);
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
	
    this.proveedorForm = new FormGroup({
		// 'tipoConsumidor': new FormControl(proveedor ? proveedor.tipoConsumidor : '1', Validators.required),
      'nitProveedor': new FormControl(proveedor ? proveedor.nitProveedor : ''),
      'tipoProveedor': new FormControl(proveedor ? proveedor.tipoProveedor : '6'),
      'primerNombre': new FormControl(proveedor ? proveedor.primerNombre : ''),
      'segundoNombre': new FormControl(proveedor ? proveedor.segundoNombre : ''),
      'tercerNombre': new FormControl(proveedor ? proveedor.tercerNombre : ''),
      'primerApellido': new FormControl(proveedor ? proveedor.primerApellido : ''),
      'segundoApellido': new FormControl(proveedor ? proveedor.segundoApellido : ''),
      'apellidoCasada': new FormControl(proveedor ? proveedor.apellidoCasada : ''),
      'nombreEmpresa': new FormControl(proveedor ? proveedor.nombreEmpresa : ''),
      'razonSocial': new FormControl(proveedor ? proveedor.razonSocial : ''),
      'actividadEconomica': new FormControl(proveedor ? proveedor.idActividadEconomica : '', Validators.required),
      'tipoComercio': new FormControl('', Validators.required),
      'telefono': new FormControl(proveedor ? tel : ''),
      'email': new FormControl(proveedor ? correo : ''),
      'codigoDepartamento': new FormControl(proveedor ? proveedor.codigoDepartamento : '', Validators.required),
      'codigoMunicipio': new FormControl(proveedor ? proveedor.codigoMunicipio : '', Validators.required),
      //'direccionAvenidaCalle': new FormControl(proveedor ? proveedor.direccionCalle : '', Validators.required),
      //'tipoDireccion': new FormControl(''),
      //'direccionNumero': new FormControl(proveedor ? proveedor.direccionAvenida : '', Validators.required),
      'direccionZona': new FormControl(proveedor ? proveedor.direccionZona : '', Validators.required),
      'direccionDetalle': new FormControl(proveedor ? proveedor.direccionDetalle : '', Validators.required),
	  'proveedorComun' : new FormControl(''),
	  'sucursales' : new FormControl(''),
	  'codigoDepartamentoS': new FormControl(''),
      'codigoMunicipioS': new FormControl(''),
	  'direccionDetalleS': new FormControl(''),
	  'nombreS' : new FormControl(''),
	  'zonaS' : new FormControl('')
    });
	
    if (proveedor) {
      // seleccionar departamento
      this.onDepartamentoChanged(1);
	  this.proveedorForm.patchValue({
        'codigoDepartamento': proveedor.codigoDepartamento
      });
	  
	  // seleccionar municipio
      this.onMunicipioChanged();
      this.proveedorForm.patchValue({
        'codigoMunicipio': proveedor.codigoMunicipio
      });
      
      // seleccionar actividad economica
     // this.onActividadEconomicaChanged();
      this.proveedorForm.patchValue({
        'tipoComercio': 'proveedor.idTipoComercio'
      });
	  //actividad economica
	  this.proveedorForm.patchValue({
        'actividadEconomica': proveedor.idActividadEconomica
      });
	  //tipo comercio
	  this.onActividadEconomicaChanged();
	  this.proveedorForm.patchValue({
        'tipoComercio': proveedor.idTipoComercio
      });
	  
	  // cargar sucursales
      this.sucursalesNitSubscription = this.proveedoresService.fetchDataSucursal(proveedor.nitProveedor).subscribe(
      res => {
		  ////console.log('existen sucursales ',res);
		  //console.log(res);
        this.sucursalesNit = res.value;
      }
    );
	  
    }
  }

  public onBuscarProveedorClicked() {
	  //////console.info(this.proveedorForm.value.nitProveedor);
    this.proveedoresService.fetchData(this.proveedorForm.value.nitProveedor).subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
          this.existingMode = true;
          this.existingProveedor = response.value;
          this.initProveedorForm(response.value);
        } else {
          this.existingMode = false;
          this.existingProveedor = null;

        }
      }
    );
	
  }

	public onProveedoresComunesChanged() {
		//alert(this.proveedorForm.value.proveedorComun+'1');
		this.proveedoresService.fetchDataId(this.proveedorForm.value.proveedorComun).subscribe(
      (response) => {
        if (response.value) {
          // inicializar formulario con valores predeterminados
          this.existingMode = true;
          this.existingProveedor = response.value;
          this.initProveedorForm(response.value);
        } else {
          this.existingMode = false;
          this.existingProveedor = null;
        }
      }
    );
	}
  
  public onActividadEconomicaChanged() {
    // cargar tipos de comercio
    this.tiposComercioSubscription = this.actividadesEconomicasService.fetchTiposComercio(this.proveedorForm.value.actividadEconomica).subscribe(
      res => {
        this.tiposComercio = res.value;
      }
    );
  }

  public onDepartamentoChanged(tipo : number) {
	let depto : number = (tipo==1? this.proveedorForm.value.codigoDepartamento : this.proveedorForm.value.codigoDepartamentoS); 
    // cargar municipios
    this.municipiosSubscription = this.departamentosService.fetchMunicipios(depto).subscribe(
      res => {
		  if (tipo==1) {
			  this.municipios = res.value;
		  } else {
			  this.municipiossuc = res.value;
		  }
      }
    );
  }

  public onMunicipioChanged() {
    // cargar sedes
    this.municipiosSubscription = this.municipiosService.fetchSedes(this.proveedorForm.value.codigoMunicipio).subscribe(
      res => {
        this.sedes = res.value;
      }
    );
  }

  public onSubmit() {
    //let tipoDireccion = this.proveedorForm.value.tipoDireccion;
    let proveedor: Proveedor = new Proveedor();
	let addsucursal: SucursalesNit = new SucursalesNit();
  	//proveedor.nombre = this.proveedorForm.value.nombre;
	if (this.proveedorForm.value.nitProveedor=="" || this.proveedorForm.value.nitProveedor == undefined) {
		/* this.mjsError +=  "- Ingrese NIT.  " + "\r\n"; */
		proveedor.nitProveedor = this.proveedorForm.value.nitProveedor;//+ POR JJ
	} else {	
		proveedor.nitProveedor = this.proveedorForm.value.nitProveedor;
	}
	
  	if (this.proveedorForm.value.tipoProveedor==6 ) {
		proveedor.tipoProveedor = 6;
		if (this.proveedorForm.value.primerNombre=="" || this.proveedorForm.value.primerNombre==undefined) {
			this.mjsError +=  "- Ingrese primer nombre.  " + "\r\n";
		} else {
			proveedor.primerNombre = this.proveedorForm.value.primerNombre;
		}
		proveedor.segundoNombre = this.proveedorForm.value.segundoNombre;
		proveedor.tercerNombre = this.proveedorForm.value.tercerNombre;
		if (this.proveedorForm.value.primerApellido=="" || this.proveedorForm.value.primerApellido==undefined) {
				this.mjsError +=  "- Ingrese primer apellido.  " + "\r\n";
		} else {
			proveedor.primerApellido = this.proveedorForm.value.primerApellido;
		}
		proveedor.segundoApellido = this.proveedorForm.value.segundoApellido;
		proveedor.apellidoCasada = this.proveedorForm.value.apellidoCasada;
	} else {
		//juridico
			proveedor.tipoProveedor = 7;
			if (this.proveedorForm.value.razonSocial=="" || this.proveedorForm.value.razonSocial==undefined) {
				this.mjsError +=  "- Ingrese razon social.  " + "\r\n";
			} else {	
				proveedor.razonSocial = this.proveedorForm.value.razonSocial;
			}
	}
    
	if (this.proveedorForm.value.nombreEmpresa=="" || this.proveedorForm.value.nombreEmpresa==undefined) {
		this.mjsError +=  "- Ingrese nombre de la empresa.  " + "\r\n";
	} else {
		proveedor.nombreEmpresa = this.proveedorForm.value.nombreEmpresa;
	}
	
	if (this.proveedorForm.value.actividadEconomica > 0 ) {
		proveedor.idActividadEconomica = this.proveedorForm.value.actividadEconomica;
	} else {
		this.mjsError +=  "- Seleccione actividad economica.  " + "\r\n";
	}
	if (this.proveedorForm.value.tipoComercio > 0) {
		proveedor.idTipoComercio = this.proveedorForm.value.tipoComercio;
	} else {
		this.mjsError +=  "- Seleccione tipo de comercio.  " + "\r\n";
	}
	if (this.proveedorForm.value.telefono=="" || this.proveedorForm.value.telefono==undefined) {
		this.mjsError +=  "- Ingrese telefono.  " + "\r\n";
	} else {
		proveedor.telefono = this.proveedorForm.value.telefono;
	}
	if (this.proveedorForm.value.email=="" || this.proveedorForm.value.email==undefined) {
		this.mjsError +=  "- Ingrese correo electronico.  " + "\r\n";
	} else {
		proveedor.email = this.proveedorForm.value.email;
	}
	if (this.proveedorForm.value.codigoDepartamento > 0) {
		proveedor.codigoDepartamento = this.proveedorForm.value.codigoDepartamento;
	} else {
		this.mjsError +=  "- Seleccione departamento.  " + "\r\n";
	}
	if (this.proveedorForm.value.codigoMunicipio > 0) {
		proveedor.codigoMunicipio = this.proveedorForm.value.codigoMunicipio;
	} else {
		this.mjsError +=  "- Seleccione municipio.  " + "\r\n";
	}
	if (this.proveedorForm.value.direccionDetalle=="" || this.proveedorForm.value.direccionDetalle==undefined) {
		this.mjsError +=  "- Ingrese direccion.  " + "\r\n";
	} else {
		proveedor.direccionDetalle = this.proveedorForm.value.direccionDetalle;
	}
	if (this.proveedorForm.value.direccionZona=="" || this.proveedorForm.value.direccionZona==undefined) {
		this.mjsError +=  "- Ingrese Zona.  " + "\r\n";
	} else {
		proveedor.direccionZona = this.proveedorForm.value.direccionZona;
	}
  	//proveedor.direccionCalle = (tipoDireccion == 'C' ? this.proveedorForm.value.direccionAvenidaCalle : this.proveedorForm.value.direccionNumero);
  	//proveedor.direccionAvenida = (tipoDireccion == 'A' ? this.proveedorForm.value.direccionAvenidaCalle : this.proveedorForm.value.direccionNumero);
  	//proveedor.direccionZona = this.proveedorForm.value.direccionZona;
  	
	//6 individual, 2 juridico
    //proveedor.tipoProveedor = this.proveedorForm.value.tipoProveedor;
    //proveedor.idActividadEconomica = this.proveedorForm.value.actividadEconomica;
    //proveedor.idTipoComercio = this.proveedorForm.value.tipoComercio;
    
    // TODO: obtener usuario de sesion
	if (this.existingProveedor) {
		proveedor.direccion = this.existingProveedor.direccion;
		proveedor.habilitadoConciliacionPrevia = this.existingProveedor.habilitadoConciliacionPrevia;
		proveedor.habilitadoNotificacionElectronica = this.existingProveedor.habilitadoNotificacionElectronica;
		proveedor.habilitado = "1";
		//proveedor.fechaRegistroConciliacionPrevia = this.existingProveedor.fechaRegistroConciliacionPrevia;
		//proveedor.fechaAdicion = this.existingProveedor.fechaAdicion;
		//proveedor.fechaModificacion = this.existingProveedor.fechaModificacion;
		proveedor.usuarioAdiciono = this.existingProveedor.usuarioAdiciono;
		proveedor.usuarioModifico = this.existingProveedor.usuarioModifico;
		proveedor.idProveedor = this.existingProveedor.idProveedor; 	
		}

	//hay una nueva sucursal, hay que grabarla
	if (this.nuevasucursal == '1') {
		if (this.proveedorForm.value.codigoDepartamentoS > 0) {
			addsucursal.idDepartamento = this.proveedorForm.value.codigoDepartamentoS;
		} else {
			this.mjsError +=  "- Seleccione departamento de la sucursal.  " + "\r\n";
		}
		if (this.proveedorForm.value.codigoMunicipioS > 0) {
			addsucursal.idMunicipio = this.proveedorForm.value.codigoMunicipioS;
		} else {
			this.mjsError +=  "- Seleccione municipio de la sucursal.  " + "\r\n";
		}
		if (this.proveedorForm.value.direccionDetalleS=="" || this.proveedorForm.value.direccionDetalleS==undefined) {
			this.mjsError +=  "- Ingrese direccion de la sucursal.  " + "\r\n";
		} else {
			addsucursal.direccion = this.proveedorForm.value.direccionDetalleS;
		}
		if (this.proveedorForm.value.nombreS=="" || this.proveedorForm.value.nombreS==undefined) {
			this.mjsError +=  "- Ingrese nombre de la sucursal.  " + "\r\n";
		} else {
			addsucursal.nombreComercial = this.proveedorForm.value.nombreS;
		}
		if (this.proveedorForm.value.zonaS=="" || this.proveedorForm.value.zonaS==undefined) {
			this.mjsError +=  "- Ingrese Zona de sucursal.  " + "\r\n";
		} else {
			addsucursal.zona = this.proveedorForm.value.zonaS;
		}
		addsucursal.nit = this.proveedorForm.value.nitProveedor;
		addsucursal.habilitado = '1';
	}
    
	if (this.mjsError=='') {
	
	} else {
		alert(this.mjsError);
		this.mjsError = "";
		return false;
	}
	
	if (this.proveedorForm.value.sucursales > 0) {
			this.quejasService.idsucursal = this.proveedorForm.value.sucursales;
			//console.info("onaummir sucursal seleccionada ",this.proveedorForm.value.sucursales,this.quejasService.idsucursal);
			//busca la sucursal 
			this.proveedoresService.fetchSucursalProveedor(this.proveedorForm.value.sucursales).subscribe(
			  (response) => {
				let tempstr=response.value;
				if (tempstr != null || tempstr != undefined) {
				   addsucursal.direccion = tempstr.direccion;
					addsucursal.idDepartamento = tempstr.idDepartamento;
					addsucursal.idMunicipio = tempstr.idMunicipio;
					addsucursal.zona = tempstr.zona;
					addsucursal.idEstablecimiento = tempstr.idEstablecimiento;
					addsucursal.nit = tempstr.nit;
					//console.info("addsucursal ",addsucursal);
					this.nextSteps(addsucursal);
				  } 
			  }
			);
		}
		
	if (this.nuevasucursal == '1'){
		this.proveedoresService.createSucursal(addsucursal);
		this.nextSteps(addsucursal);
		//this.quejasService.sucursal = addsucursal;
	} else {
		
	}
	
	if (this.existingMode) {
		//actualiza proveedor
		proveedor.usuarioModifico = this.str_usuario;
		this.proveedoresService.createProveedor(proveedor);
      this.nextStep(this.existingProveedor);
    } else {
		proveedor.usuarioAdiciono = this.str_usuario;
      this.proveedoresService.createProveedor(proveedor);
    }
  }

  private nextStep(proveedor: Proveedor) {
	  //////console.info("nextStep");
    this.proveedorForm.reset();
    // guardar el proveedor en sesion
    this.quejasService.proveedor = proveedor;
    // ir a pantalla de queja
    this.router.navigate(['/queja']);
  }
  
    private nextSteps(addsucursal: SucursalesNit) {
	  //////console.info("nextStep");
    //this.proveedorForm.reset();
    // guardar el proveedor en sesion
    this.quejasService.sucursal = addsucursal;
	if (this.proveedorForm.value.sucursales > 0) {
			this.quejasService.idsucursal = this.proveedorForm.value.sucursales;
			//console.info("sucursal seleccionada ",this.proveedorForm.value.sucursales,this.quejasService.idsucursal);
		}
    // ir a pantalla de queja
    //this.router.navigate(['/queja']);
  }
  
  public CerrarSesion(){
		let loc_token=this._submitFormService.Get_token();
		//////console.log(loc_token);
		if(loc_token == '')
			this._seguridadService.RedireccionarLogin();
		this._seguridadService.Logout(loc_token).subscribe((retvalue)=>{
			//////console.log('cerrar sesion',retvalue);
			this._seguridadService.RedireccionarLogin();		
		},(error)=>{
			this._seguridadService.RedireccionarLogin();
		});
	}
	
 public findByTokenProveedor() {
	  
	 let queja : Queja = this.quejasService.quejat;
	 if (queja) {
		 this.str_queja = "- Queja No. "+queja.noQueja+"-"+queja.anio;
	 }
	 //////console.info("findByTokenProveedor consumidor ",queja);
	 this.proveedoresService.fetchDataByTokenProveedor(queja.idProveedor).subscribe(
      (response) => {
		let tempstr=response.value;
        if (tempstr != null || tempstr != undefined) {

		   //////console.info("existe",tempstr);
		   this.initProveedorForm(tempstr);
		   this.existingProveedor = tempstr;
		   this.quejasService.vexisteProveedor = "1";
		   /*this.quejasService.initQuejat(tempstr);
		   if (this.quejasService.initQuejat != null) {
				this.continuar = "1";
				this.initConsumidorForm(null);
		   } else
				this.continuar = "0";
			*/
		  } else {
			  this.quejasService.vexisteProveedor = "0";
			//this.existingData = false;
			//////console.info("no existe",response.value);
			//this.continuar = "0";
			//alert("Su acceso al sistema fue denegado, por favor verifique.");	
		  }
      }
    );	
	//////console.info("findByTokenProveedor end ",this.quejasService.quejat);
  }	
  
  public onSucursalChanged() {
	  ////console.info("sucursal seleccionada ",this.proveedorForm.value.sucursales);
  }
  
  //nueva sucursal
  onSucursalClicked(tipo : string) {
	  this.nuevasucursal = tipo;
	  if (tipo == "0") {
			this.proveedorForm.value.nombreS = "";
			this.proveedorForm.value.direccionDetalleS = "";
	  }
  }
  
}
