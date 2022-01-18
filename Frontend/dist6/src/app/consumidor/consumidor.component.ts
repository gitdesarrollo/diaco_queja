import {Component, OnInit} from '@angular/core';
import {FormGroup, Validators, FormControl} from '@angular/forms';
import {Pais} from '../shared/pais.model';
import {PaisesService} from '../shared/paises.service';
import {Subscription} from 'rxjs';
import {MotivoQueja} from '../shared/motivo-queja.model';
import {MotivosQuejaService} from '../shared/motivos-queja.service';
import {Departamento} from '../shared/departamento.model';
import {DepartamentosService} from '../shared/departamentos.service';
import {Etnia} from '../shared/etnia.model';
import {SedesService} from '../shared/sedes.service';
import {EtniasService} from '../shared/etnias.service';
import {Municipio} from '../shared/municipio.model';
import {Consumidor} from '../shared/consumidor.model';
import {Sede} from '../shared/sede.model';
import {MunicipiosService} from '../shared/municipios.service';
import {ConsumidoresService} from '../shared/consumidores.service';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {QuejasService} from '../shared/quejas.service';
import {SubmitFormService} from '../shared/submit-form.service';
import {SeguridadService} from '../shared/seguridad.service';
import {Queja} from '../shared/queja.model';

//import { WINDOW } from "../shared/window.service";


@Component({
    selector: 'app-consumidor',
    templateUrl: './consumidor.component.html',
    styleUrls: ['./consumidor.component.scss']
})
export class ConsumidorComponent implements OnInit {
    consumidorForm: FormGroup;
    paises: Pais[];
    paisesSubscription: Subscription;
    motivosQueja: MotivoQueja[];
    motivosQuejaSubscription: Subscription;
    departamentos: Departamento[];
    departamentosSubscription: Subscription;
    etnias: Etnia[];
    etniasSubscription: Subscription;
    municipios: Municipio[];
    municipiosSubscription: Subscription;
    sedes: Sede[];
    sedesSubscription: Subscription;
    consumidorOperationSubscription: Subscription;
    existingMode: boolean = false;
    existingConsumidor: Consumidor;
    mjsError: string = '';
    tipocon: string = '';
    str_usuario: string = '';
    //datos iniciales
    dato: string = '';
    vid: string = '';
    vqueja: string = '';
    continuar: string = '1';
    existeLink: string = '1';
    str_queja: string = '';


    constructor(private router: Router,
                private paisesService: PaisesService,
                private motivosQuejaService: MotivosQuejaService,
                private departamentosService: DepartamentosService,
                private municipiosService: MunicipiosService,
                private etniasService: EtniasService,
                private sedesService: SedesService,
                private consumidoresService: ConsumidoresService,
                private quejasService: QuejasService,
                private _submitFormService: SubmitFormService,
                private _seguridadService: SeguridadService
        , private _route: ActivatedRoute
                //,@Inject(WINDOW) public window: Window
    ) {
        //this.str_usuario="Usuario: "+this._submitFormService.Get_username();
        //console.info(this._route.snapshot.paramMap.get('dato'),this._route.snapshot.paramMap.get('id'));
        //console.info("constructor");
        //console.log(window);
    }

    ngOnInit() {
        //console.info("ngOnInit");
        //if (!this._submitFormService.Get_username()) {
        //  this.router.navigate(['/Login']);
        //}

        this._route.params.subscribe(
            (params: Params) => {
                this.dato = params.dato;
                this.vid = params.id;
                this.vqueja = params.queja;
            }
        );
        //verificar parametros
        //console.info("--->>",this.dato,this.vid,this.vqueja);
        if (this.dato == 'externo' || this.dato == 'interno' || this.dato == 'presencial') {
            if (this.dato == 'externo' && this.vid != '') {
                //console.info(this.dato,this.vid,this.vqueja);
                this.quejasService.vdato = this.dato;
                this.quejasService.vid = this.vid;
                this.findByToken();
            } else {
                if ((this.dato == 'interno' || this.dato == 'presencial') && this.vid != '' && this.vqueja != '') {
                    //console.info(this.dato,this.vid,this.vqueja);
                    this.quejasService.vdato = this.dato;
                    this.quejasService.vid = this.vid;
                    this.quejasService.vqueja = this.vqueja;
                    this.findByQuejaById();
                } else {
                    alert('No puede ingresar al sistema, favor verifique.');
                    this.continuar = '0';
                    return;
                }
            }
        } else {
            alert('No puede ingresar al sistema, favor verifique.');
            this.continuar = '0';
            return;
        }
        //console.info("continuar");
        /*
        console.info("....",this.existeLink);
                if (this.existeLink == "0")  {
                    alert("Su acceso al sistema fue denegado, por favor verifique.");
                this.continuar = '0'
                return;
                }*/
        // cargar paises
        this.paisesSubscription = this.paisesService.fetchData().subscribe(
            res => {
                this.paises = res.value;
            }
        );
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
        );
        // cargar etnias
        this.etniasSubscription = this.etniasService.fetchData().subscribe(
            res => {
                this.etnias = res.value;
            }
        );
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
        );
        this.initConsumidorForm(null);


    }

    private initConsumidorForm(consumidor: Consumidor) {
        //console.info("initConsumidorForm");
        let au = false;
        if (consumidor) {
            if (consumidor.autorizoPublicar != null && consumidor.autorizoPublicar != undefined) {
                if (consumidor.autorizoPublicar == '1') {
                    au = true;
                }
            }
        }
        let nac: string = (consumidor ? (consumidor.nacionalidad == 1 ? 'G' : 'E') : 'G');
        this.consumidorForm = new FormGroup({
            'idConsumidor': new FormControl(consumidor ? consumidor.idConsumidor : '0'),
            'nacionalidad': new FormControl(nac), // , Validators.required),

            'tipoDocumento': new FormControl(consumidor ? consumidor.tipoDocumento : ''), // , Validators.required),
            'tipoConsumidor': new FormControl(consumidor ? consumidor.tipoConsumidor : '6'), // , Validators.required),
            'documentoIdentificacion': new FormControl(consumidor ? consumidor.documentoIdentificacion : ''), // , Validators.required),
            'pais': new FormControl(consumidor ? consumidor.nacionalidad : 1),
            'representanteLegal': new FormControl(consumidor ? consumidor.representanteLegal : ''), // , Validators.required),
            'nitConsumidor': new FormControl(consumidor ? consumidor.nitConsumidor : ''),

            'nombreEmpresa': new FormControl(consumidor ? consumidor.nombreEmpresa : ''), // , Validators.required),
            'razonSocial': new FormControl(consumidor ? consumidor.razonSocial : ''), // , Validators.required),
            'nombre1': new FormControl(consumidor ? consumidor.nombre1 : ''), // , Validators.required),
            'nombre2': new FormControl(consumidor ? consumidor.nombre2 : ''),
            'nombre3': new FormControl(consumidor ? consumidor.nombre3 : ''),
            'apellido1': new FormControl(consumidor ? consumidor.apellido1 : ''), // , Validators.required),
            'apellido2': new FormControl(consumidor ? consumidor.apellido2 : ''),
            'apellidoCasada': new FormControl(consumidor ? consumidor.apellidoCasada : ''),
            'genero': new FormControl(consumidor ? consumidor.genero : ''),
            'etnia': new FormControl(consumidor ? consumidor.idEtnia : ''),
            'motivoQueja': new FormControl(consumidor ? consumidor.idMotivoQueja : ''), // , Validators.required),

            'codigoDepartamento': new FormControl(consumidor ? consumidor.codigoDepartamento : ''), // , Validators.required),
            'codigoMunicipio': new FormControl(''), // , Validators.required),
            //'direccionAvenidaCalle': new FormControl(consumidor ? consumidor.direccionCalle : ''), // , Validators.required),
            //'tipoDireccion': new FormControl(''),
            //'direccionNumero': new FormControl(consumidor ? consumidor.direccionAvenida : ''), // , Validators.required),
            'direccionZona': new FormControl(consumidor ? consumidor.direccionZona : ''), // , Validators.required),
            'direccionDetalle': new FormControl(consumidor ? consumidor.direccionDetalle : ''), // , Validators.required),
            'sedeDiacoCercana': new FormControl(''), // , Validators.required),

            'telefonoCelular': new FormControl(consumidor ? consumidor.telefonoCelular : ''), // , Validators.required),
            'telefonoDomicilio': new FormControl(consumidor ? consumidor.telefonoDomicilio : ''),
            'telefonoReferencia': new FormControl(consumidor ? consumidor.telefonoReferencia : ''),
            'email': new FormControl(consumidor ? consumidor.email : ''), // , Validators.required),
            'emailConfirmation': new FormControl(consumidor ? consumidor.email : ''), // , Validators.required),
            'email2': new FormControl(consumidor ? consumidor.email2 : ''),
            'email2Confirmation': new FormControl(consumidor ? consumidor.email2 : ''),

            'iCheck': new FormControl(consumidor ? au : false),
            'habilitadoNotificacionElectronica': new FormControl(consumidor ? consumidor.habilitadoNotificacionElectronica : '')

        });
        if (consumidor) {

            // seleccionar departamento
            this.onDepartamentoChanged();
            this.consumidorForm.patchValue({
                'codigoMunicipio': consumidor.codigoMunicipio
            });
            // seleccionar municipio
            this.onMunicipioChanged();
            this.consumidorForm.patchValue({
                'codigoMunicipio': consumidor.codigoMunicipio
            });
            //pais
            this.consumidorForm.patchValue({
                'pais': consumidor.pais
            });
            //genero
            this.consumidorForm.patchValue({
                'genero': consumidor.genero
            });
            //etnia
            this.consumidorForm.patchValue({
                'etnia': consumidor.idEtnia
            });
            //queja
            this.consumidorForm.patchValue({
                'motivoQueja': (consumidor.motivoQueja ? consumidor.motivoQueja : consumidor.idMotivoQueja)
            });
            //sedeDiacoCercana
            this.consumidorForm.patchValue({
                'sedeDiacoCercana': consumidor.sedeDiacoCercana
            });
        }
    }

    public onDocumentoIdentificacionChanged(tipo: string) {
        if (tipo == 'documento') {
            this.tipocon = this.consumidorForm.value.documentoIdentificacion;
        } else {
            this.tipocon = this.consumidorForm.value.nitConsumidor;
        }
        this.consumidoresService.fetchData(tipo, this.tipocon).subscribe(
            (response) => {
                if (response.value) {
                    // inicializar formulario con valores predeterminados
                    this.existingMode = true;
                    this.existingConsumidor = response.value;
                    this.initConsumidorForm(response.value);
                } else {
                    this.existingMode = false;
                    this.existingConsumidor = null;
                }
            }
        );
    }

    public onDepartamentoChanged() {
        // cargar municipios
        this.municipiosSubscription = this.departamentosService.fetchMunicipios(this.consumidorForm.value.codigoDepartamento).subscribe(
            res => {
                this.municipios = res.value;
            }
        );
    }

    public onMunicipioChanged() {
        // cargar sedes
        /*
        this.municipiosSubscription = this.municipiosService.fetchSedes(this.consumidorForm.value.codigoMunicipio).subscribe(
          res => {
            this.sedes = res.value;
          }
        );
        */
    }

    public valOnSummit() {

    }

    public onSubmit() {
        //console.info("onSumit");
        // validar email y emailConfirmation
        let email = this.consumidorForm.value.email;
        let emailConfirmation = this.consumidorForm.value.emailConfirmation;
        let nacionalidad = this.consumidorForm.value.nacionalidad;

        let pais = this.consumidorForm.value.pais;
        let documentoIdentificacion = this.consumidorForm.value.documentoIdentificacion;
        let representanteLegal = this.consumidorForm.value.representanteLegal;
        let tipoConsumidor = this.consumidorForm.value.tipoConsumidor;
        let nombre1 = this.consumidorForm.value.nombre1;
        let apellido1 = this.consumidorForm.value.apellido1;
        let genero = this.consumidorForm.value.genero;
        let etnia = this.consumidorForm.value.etnia;
        let nombreEmpresa = this.consumidorForm.value.nombreEmpresa;
        let razonSocial = this.consumidorForm.value.razonSocial;
        let nitConsumidor = this.consumidorForm.value.nitConsumidor;
        let motivoQueja = this.consumidorForm.value.motivoQueja;
        let codigoDepartamento = this.consumidorForm.value.codigoDepartamento;
        let codigoMunicipio = this.consumidorForm.value.codigoMunicipio;
        let direccionDetalle = this.consumidorForm.value.direccionDetalle;
        let direccionZona = this.consumidorForm.value.direccionZona;
        let sedeDiacoCercana = this.consumidorForm.value.sedeDiacoCercana;
        let telefonoCelular = this.consumidorForm.value.telefonoCelular;
        let iCheck_ = this.consumidorForm.value.iCheck;

        let tipoDireccion = this.consumidorForm.value.tipoDireccion;
        let consumidor: Consumidor = new Consumidor();


        if (nacionalidad == 'E') {

            consumidor.tipoDocumento = (this.consumidorForm.value.tipoDocumento == 'G' ? 1 : 2);

            if (documentoIdentificacion == '' || documentoIdentificacion == undefined) {
                this.mjsError += '- Ingrese documento de identificacion.  ' + '\r\n';
            } else {
                consumidor.documentoIdentificacion = this.consumidorForm.value.documentoIdentificacion;
            }

            if (pais == '' || pais == undefined) {
                this.mjsError += '- Seleccione pais.  ' + '\r\n';
            } else {
                consumidor.pais = this.consumidorForm.value.pais;
                consumidor.nacionalidad = this.consumidorForm.value.pais; //(this.consumidorForm.value.nacionalidad == "G"?1:2);
            }
        } else {
            consumidor.nacionalidad = (this.consumidorForm.value.nacionalidad == 'G' ? 1 : 2);
            consumidor.tipoDocumento = (this.consumidorForm.value.tipoDocumento == 'G' ? 1 : 2);

            if (tipoConsumidor == '6') {
                consumidor.tipoConsumidor = '6';
                if (documentoIdentificacion == '' || documentoIdentificacion == undefined) {
                    this.mjsError += '- Ingrese documento de identificacion.  ' + '\r\n';
                } else {
                    consumidor.documentoIdentificacion = this.consumidorForm.value.documentoIdentificacion;
                }
            } else {
                consumidor.tipoConsumidor = '7';
                if (representanteLegal == '' || representanteLegal == undefined) {
                    this.mjsError += '- Ingrese representante legal.  ' + '\r\n';
                } else {
                    consumidor.representanteLegal = this.consumidorForm.value.representanteLegal;
                }
                if (documentoIdentificacion == '' || documentoIdentificacion == undefined) {

                } else {
                    consumidor.documentoIdentificacion = this.consumidorForm.value.documentoIdentificacion;
                }
            }
        }

        //tipoConsumidor:  7 juridico, 6 individual

        if (tipoConsumidor == '6') {
            consumidor.tipoConsumidor = '6';
            if (nombre1 == '' || nombre1 == undefined) {
                this.mjsError += '- Ingrese primer nombre.  ' + '\r\n';
            } else {
                consumidor.nombre1 = this.consumidorForm.value.nombre1;
            }

            if (apellido1 == '' || apellido1 == undefined) {
                this.mjsError += '- Ingrese primer apellido.  ' + '\r\n';
            } else {
                consumidor.apellido1 = this.consumidorForm.value.apellido1;
            }

            if (genero == '' || genero == undefined) {
                this.mjsError += '- Seleccione genero.  ' + '\r\n';
            } else {
                consumidor.genero = this.consumidorForm.value.genero;
            }

            if (etnia > 0 || etnia != undefined) {

                consumidor.etnia = this.consumidorForm.value.etnia;
            } else {
                this.mjsError += '- Seccione etnia.  ' + '\r\n';
            }
        } else {
            consumidor.tipoConsumidor = '7';
            if (nombreEmpresa == '' || nombreEmpresa == undefined) {
                this.mjsError += '- Ingrese nombre de la empresa.  ' + '\r\n';
            } else {
                consumidor.nombreEmpresa = this.consumidorForm.value.nombreEmpresa;
            }

            if (razonSocial == '' || razonSocial == undefined) {
                this.mjsError += '- Ingrese razon social.  ' + '\r\n';
            } else {
                consumidor.razonSocial = this.consumidorForm.value.razonSocial;
            }
        }

/*         if (nitConsumidor == '' || nitConsumidor == undefined) {
            this.mjsError += '- Ingrese su NIT.  ' + '\r\n';
        } else {*/
            consumidor.nitConsumidor = this.consumidorForm.value.nitConsumidor;
        

        if (motivoQueja == '' || motivoQueja == null || motivoQueja == undefined) {
            this.mjsError += '- Seleccione motivo de queja.  ' + '\r\n';
        } else {
            consumidor.motivoQueja = this.consumidorForm.value.motivoQueja;
        }
        //console.info("Departamento:  ",codigoDepartamento);
        if (codigoDepartamento == '' || codigoDepartamento == null || codigoDepartamento == undefined) {
            this.mjsError += '- Seleccione departamento.  ' + '\r\n';
        } else {
            consumidor.codigoDepartamento = this.consumidorForm.value.codigoDepartamento;
        }
        //console.info("Municipio ",codigoMunicipio);
        if (codigoMunicipio == '' || codigoMunicipio == null || codigoMunicipio == undefined) {
            this.mjsError += '- Seleccione municipio.  ' + '\r\n';
        } else {
            consumidor.codigoMunicipio = this.consumidorForm.value.codigoMunicipio;
        }

        if (direccionDetalle == '' || direccionDetalle == undefined) {
            this.mjsError += '- Ingrese su dirección.  ' + this.consumidorForm.value.direccionDetalle + '\r\n';
        } else {
            consumidor.direccionDetalle = this.consumidorForm.value.direccionDetalle;
        }

        if (direccionZona == '' || direccionZona == undefined) {
            this.mjsError += '- Ingrese su Zona.  ' + this.consumidorForm.value.direccionZona + '\r\n';
        } else {
            consumidor.direccionZona = this.consumidorForm.value.direccionZona;
        }

        //alert(sedeDiacoCercana);
        if (sedeDiacoCercana == '' || sedeDiacoCercana == null || sedeDiacoCercana == undefined) {
            this.mjsError += '- Selecciones sede.  ' + '\r\n';
        } else {
            consumidor.sedeDiacoCercana = this.consumidorForm.value.sedeDiacoCercana;
        }

        if (telefonoCelular == '' || telefonoCelular == undefined) {
            this.mjsError += '- Ingrese su numero de telefono celular.  ' + this.consumidorForm.value.telefonoCelular + '\r\n';
        } else {
            consumidor.telefonoCelular = this.consumidorForm.value.telefonoCelular;
        }

        //if (iCheck == "") {
        //	this.mjsError +=  "- Verifique su autorizacion.  " + "\r\n";
        //} else {

        if (iCheck_) {
            consumidor.autorizoPublicar = '1';
        } else {
            consumidor.autorizoPublicar = '0';
        }
        //console.info("consumidor....",iCheck_,",  ",consumidor.autorizoPublicar,"...",consumidor);
        //}


        if (email == '' || email == undefined) {
            this.mjsError += '- Ingrese su correo electronico.  ' + '\r\n';
        } else {
            if (email !== emailConfirmation) {
                // TODO: utilizar libreria para mostrar alertas
                //alert('Los emails principales no coinciden');
                this.mjsError += '- Los emails principales no coinciden ' + '\r\n';
                //return false;
            } else {
                consumidor.email = this.consumidorForm.value.email;
            }
        }
        // validar email2 y email2Confirmation
        let email2 = this.consumidorForm.value.email2;
        let email2Confirmation = this.consumidorForm.value.email2Confirmation;
        if (email2) {
            if (email2 !== email2Confirmation) {
                //alert('Los emails secundarios no coinciden');
                this.mjsError += '- Los emails secundarios no coinciden ' + '\r\n';
                //return false;
            } else {
                consumidor.email2 = this.consumidorForm.value.email2;
            }
        }

        if (this.mjsError == '') {

        } else {
            alert(this.mjsError);
            this.mjsError = '';
            return false;
        }


        //consumidor.tipoConsumidor = (this.consumidorForm.value.tipoConsumidor=="6"?"6":"7");

        if (this.consumidorForm.value.nombre2 != '') {
            consumidor.nombre2 = this.consumidorForm.value.nombre2;
        }
        if (this.consumidorForm.value.nombre3 != '') {
            consumidor.nombre3 = this.consumidorForm.value.nombre3;
        }
        if (this.consumidorForm.value.apellido2 != '') {
            consumidor.apellido2 = this.consumidorForm.value.apellido2;
        }
        if (this.consumidorForm.value.apellidoCasada != '') {
            consumidor.apellidoCasada = this.consumidorForm.value.apellidoCasada;
        }
        if (this.consumidorForm.value.telefonoDomicilio != '') {
            consumidor.telefonoDomicilio = this.consumidorForm.value.telefonoDomicilio;
        }
        if (this.consumidorForm.value.telefonoReferencia != '') {
            consumidor.telefonoReferencia = this.consumidorForm.value.telefonoReferencia;
        }
        if (this.consumidorForm.value.email2 != '') {
            consumidor.email2 = this.consumidorForm.value.email2;
        }


        /*

        */
        //console.info("consumidor....",consumidor);
        if (this.existingMode) {
            //actualiza consumidor
            //consumidor.usuarioModifico = this.str_usuario;
			console.log('valor que trae la variable consumidor 2', consumidor);
            this.consumidoresService.createConsumidor(consumidor);
            this.nextStep(this.existingConsumidor);
        } else {
            //consumidor.usuarioAdiciono = this.str_usuario;
			console.log('valor que trae la variable consumidor', consumidor);
            this.consumidoresService.createConsumidor(consumidor);
        }
    }

    private nextStep(consumidor: Consumidor) {
        this.consumidorForm.reset();
        // guardar el consumidor en sesion
        this.quejasService.consumidor = consumidor;
        // ir a pantalla de proveedor
        this.router.navigate(['/proveedor']);
    }

    CerrarSesion() {
        let loc_token = this._submitFormService.Get_token();
        //console.log(loc_token);
        if (loc_token == '') {
            this._seguridadService.RedireccionarLogin();
        }
        this._seguridadService.Logout(loc_token).subscribe((retvalue) => {
            //console.log('cerrar sesion',retvalue);
            this._seguridadService.RedireccionarLogin();
        }, (error) => {
            this._seguridadService.RedireccionarLogin();
        });
    }

    /***
     * obtiene los datos de la queja externa por token
     ****/
    public findByToken() {
        //console.info("findByToken init ",this.dato, this.vid);

        this.quejasService.fetchDataByToken(this.vid, this.dato).subscribe(
            (response) => {
                let tempstr = response.value;
                if (tempstr != null || tempstr != undefined) {

                    //console.info("existe",tempstr);
                    this.quejasService.initQuejat(tempstr);
                    if (this.quejasService.initQuejat != null) {
                        this.continuar = '1';
                        this.initConsumidorForm(null);
                        this.findByTokenConsumidor();
                    } else {
                        this.continuar = '0';
                    }
                } else {
                    //this.existingData = false;
                    //console.info("no existe",response.value);
                    this.continuar = '0';
                    alert('Su acceso al sistema fue denegado, por favor verifique.');
                }
            }
        );
        //console.info("findByToken end ",this.quejasService.vdato, this.quejasService.vid);
    }

    /***
     * obtiene los datos del consumidor
     ***/
    public findByTokenConsumidor() {

        let queja: Queja = this.quejasService.quejat;
        if (queja) {
            this.str_queja = '- Queja No. ' + queja.noQueja + '-' + queja.anio;
        }
        //console.info("findByTokenConsumidor init ",queja.idConsumidor);
        this.consumidoresService.fetchDataByTokenConsumidor(queja.idConsumidor).subscribe(
            (response) => {
                let tempstr = response.value;
                if (tempstr != null || tempstr != undefined) {
                    //console.info("existe",tempstr);
                    this.initConsumidorForm(tempstr);
                    this.initConsumidorForm = tempstr;
                    this.quejasService.vexisteConsumidor = '1';
                    this.continuar = '1';
                } else {
                    //this.existingData = false;
                    //console.info("no existe",response.value);
                    this.continuar = '0';
                    this.quejasService.vexisteConsumidor = '0';
                    this.continuar = '1';
                    alert('Su acceso al sistema fue denegado, por favor verifique.');
                }
            }
        );
        //console.info("findByTokenConsumidor end ",this.quejasService.quejat);
    }

    /***
     * obtiene los datoa de la queja
     ***/
    public findByQuejaById() {
        //console.info("findByQuejaById ini ",this.dato, this.vid, this.vqueja);
        this.quejasService.fetchDataQuejaById(this.dato, this.vid, this.vqueja).subscribe(
            (response) => {
                let tempstr = response.value;
                //console.info("findByQuejaById consumidor ",tempstr);
                if (tempstr != null || tempstr != undefined) {
                    //console.info("existe",tempstr);
                    this.quejasService.initQuejat(tempstr);
                    if (this.quejasService.initQuejat != null) {
                        this.continuar = '1';
                        this.initConsumidorForm(null);
                        this.findByTokenConsumidor();
                    } else {
                        this.continuar = '0';
                    }
                } else {
                    //this.existingData = false;
                    //console.info("no existe",response.value);
                    this.continuar = '0';
                    alert('Su acceso al sistema fue denegado, por favor verifique.');
                }
            }
        );
        let queja: Queja = this.quejasService.quejat;
        if (queja) {
            this.str_queja = '- Queja No. ' + queja.noQueja + "-" + queja.anio;
        }
        //console.info("findByQuejaById end ",this.quejasService.quejat);
    }


}
