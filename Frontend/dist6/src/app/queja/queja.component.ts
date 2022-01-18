import {Component, OnInit, ViewChild, ElementRef, Injectable, NgModule} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {QuejasService} from '../shared/quejas.service';
import {Queja} from '../shared/queja.model';
import {FileUploader, FileItem} from 'ng2-file-upload';
import {HttpClient} from '@angular/common/http';
import {BASE_URL_REST_FILE2} from '../conc-virt-const';
import {ResponseRs} from '../shared/response-rs.model';
import {Subject} from 'rxjs';
import {SubmitFormService} from '../shared/submit-form.service';
import {SeguridadService} from '../shared/seguridad.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {BsLocaleService} from 'ngx-bootstrap/datepicker';
import {listLocales} from 'ngx-bootstrap/chronos';
import {formatDate} from '@angular/common';
import {Adjuntos} from '../shared/adjuntos.model';


@Component({
    selector: 'app-queja',
    templateUrl: './queja.component.html',
    styleUrls: ['./queja.component.scss']
})
export class QuejaComponent implements OnInit {
    quejaForm: FormGroup;
    quejaOperationSubscription: Subscription;
    @ViewChild('filDpi')
    filDpi: ElementRef;
    uploader = new FileUploader({
        itemAlias: 'document'
    });
    otro: FileItem;
    factura: FileItem;
    facturaIndex: number;
    dpi: FileItem;
    dpiIndex: number;
    success: boolean = false;
    completado: boolean = false;
    mjsError: string = '';
    vanio: number;
    vsecuencia: number;
    vidimagen: number = 3;
    vidqueja: number;
    operation = new Subject<Queja>();
    str_usuario: string = '';
    locale = 'es';
    locales = listLocales();
    str_queja: string = '';
    cerrar_: string = 'no';
    presencial: string = '';
    _existenUploads: string = '0';
    documentosAdjuntos: Adjuntos[];
    documentosSubscription: Subscription;

    constructor(private router: Router,
                private quejasService: QuejasService,
                private _submitFormService: SubmitFormService,
                private _seguridadService: SeguridadService
        , private localeService: BsLocaleService) {
        //,public dialogRef: MatDialogRef<QuejaComponent>) {
        //this.str_usuario="Usuario: "+this._submitFormService.Get_username();
        this.localeService.use(this.locale);
    }

    applyLocale(pop: any) {
        this.localeService.use(this.locale);
        pop.hide();
        pop.show();
    }


    ngOnInit() {
        // validar que haya un consumidor en sesion
        if (!this.quejasService.consumidor || this.quejasService.vexisteConsumidor == '0') {
            this.router.navigate(['/consumidor']);
        }
        // validar que haya un proveedor en sesion
        if (!this.quejasService.proveedor || this.quejasService.vexisteProveedor == '0') {
            this.router.navigate(['/proveedor']);
        }

        // listener cuando finaliza la operacion de creacion
        this.quejaOperationSubscription = this.quejasService.operation.subscribe(
            (queja: Queja) => {
                // limpia sesion
                this.quejasService.consumidor = null;
                this.quejasService.proveedor = null;
                this.quejaForm.reset();
                this.success = true;
            }
        );

        this.initQuejaForm(null);
        this.localeService.use(this.locale);
        let _queja: Queja = this.quejasService.quejat;
        if (_queja) {
            this.initQuejaForm(_queja);
            this.str_queja = '- Queja No. ' + _queja.noQueja + '-' + _queja.anio;
        }
        this.presencial = this.quejasService.vdato;
    }

    initQuejaForm(_queja: Queja) {
        let fecha_;
        if (_queja) {
            //console.info('fecha fechaFactura ',_queja.fechaFactura);
            //console.info('fecha fechaFactura_',_queja.fechaFactura_);
            if (_queja.fechaFactura_ != undefined || _queja.fechaFactura_ != '') {
                fecha_ = new Date(_queja.fechaFactura_);
            } else {
                fecha_ = _queja.fechaFactura;
            }
        }
        this.quejaForm = new FormGroup({
            'facturaNumero': new FormControl(_queja ? _queja.facturaNumero : ''),
            'fechaFactura': new FormControl(_queja ? _queja.fechaFactura : '', Validators.required),
            'detalleQueja': new FormControl(_queja ? _queja.detalleQueja : '', Validators.required),
            'solicitaQue': new FormControl(_queja ? _queja.solicitaQue : '', Validators.required),
            'documento': new FormControl('')
        });
        console.info('existe', _queja);
        if (_queja) {
            //busca archivos adjuntos
            this.quejasService.getfilesUpload(_queja.idQueja).subscribe(
                (response) => {
                    let tempstr = response.value;
                    if (tempstr != null || tempstr != undefined) {
                        this._existenUploads = '1';

                        console.info('existe', tempstr);
                        this.documentosAdjuntos = tempstr;
                        //this.dpi = tempstr;
                    } else {
                        //this.quejasService.vexisteProveedor = "0";
                        //this.existingData = false;
                        //console.info("no existe",response.value);
                        //this.continuar = "0";
                        //alert("Su acceso al sistema fue denegado, por favor verifique.");
                    }
                }
            );
        }

    }

    onFileChanged(type: string) {
        if (type == 'FAC') {
            if (this.factura) {
                let facturaItem = this.uploader.queue[this.facturaIndex];
                this.uploader.removeFromQueue(facturaItem);
            }
            this.factura = this.uploader.queue[this.uploader.queue.length - 1];
            this.facturaIndex = this.uploader.queue.length - 1;
        } else if (type == 'DPI') {
            if (this.dpi) {
                let dpiItem = this.uploader.queue[this.dpiIndex];
                this.uploader.removeFromQueue(dpiItem);
            }
            this.dpi = this.uploader.queue[this.uploader.queue.length - 1];
            this.dpiIndex = this.uploader.queue.length - 1;
        }

        /*	if (this.uploader.queue.length > 0) {
            console.info(this.factura);
            console.info(this.dpi);
        }*/

    }

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
    }

    public onSubmit() {
        let queja: Queja = new Queja();
        if (this.quejaForm.value.facturaNumero == '' || this.quejaForm.value.facturaNumero == undefined) {
            //this.mjsError += '- Ingrese numero de factura o documento.  ' + '\r\n';
            queja.facturaNumero = this.quejaForm.value.facturaNumero;
        } else {
            queja.facturaNumero = this.quejaForm.value.facturaNumero;
        }
        if (this.quejaForm.value.fechaFactura == '' || this.quejaForm.value.fechaFactura == undefined) {
            this.mjsError += '- Ingrese fecha del documento.  ' + '\r\n';
        } else {
            queja.fechaFactura = this.quejaForm.value.fechaFactura;
        }
        if (this.quejaForm.value.detalleQueja == '' || this.quejaForm.value.detalleQueja == undefined) {
            this.mjsError += '- Ingrese detalle de la queja.  ' + '\r\n';
        } else {
            queja.detalleQueja = this.quejaForm.value.detalleQueja;
        }
        if (this.quejaForm.value.solicitaQue == '' || this.quejaForm.value.solicitaQue == undefined) {
            this.mjsError += '- Ingrese su solicitud de queja.  ' + '\r\n';
        } else {
            queja.solicitaQue = this.quejaForm.value.solicitaQue;
        }
        queja.idConsumidor = this.quejasService.consumidor.idConsumidor;
        queja.idProveedor = this.quejasService.proveedor.idProveedor;

        if (this.mjsError == '') {

        } else {
            alert(this.mjsError);
            this.mjsError = '';
            return false;
        }

        /*
        if (this.factura == null || this.dpi == null) {
            alert("Debe de ingresar  copia de factura y dpi.");
            return;
        }*/

        /*
        this.quejasService.createQueja(queja, this.uploader);
        this.vanio = this.quejasService.vsecanio;
        this.vsecuencia = this.quejasService.vsecanio;
        */
        let _queja: Queja = this.quejasService.quejat;
        let _proveedor = this.quejasService.proveedor;

        if (_queja) {
            queja.idQueja = _queja.idQueja;
            queja.noQueja = _queja.noQueja;
            queja.anio = _queja.anio;
            queja.direccionProveedor = _proveedor.direccionDetalle;
            queja.idDepartamentoProveedor = _proveedor.codigoDepartamento;
            queja.idMunicipioProveedor = _proveedor.codigoMunicipio;
            queja.idDepartamento = _proveedor.codigoDepartamento;
            queja.idMunicipio = _proveedor.codigoMunicipio;
            queja.zonaProveedor = _proveedor.direccionZona;
            //datos no modificables
            queja.idEstadoQueja = _queja.idEstadoQueja;
            queja.idSubActividadEconomica = _queja.idSubActividadEconomica;
            queja.procedeQueja = _queja.procedeQueja;
            queja.razonNoProcede = _queja.razonNoProcede;
            queja.datosVerificados = _queja.datosVerificados;
            queja.fechaQueja = _queja.fechaQueja;
            queja.motivo = _queja.motivo;

            queja.usuarioAsignado = _queja.usuarioAsignado;
            queja.idDiacoSede = _queja.idDiacoSede;
            queja.fuente = _queja.fuente;
            queja.idfuente = _queja.idfuente;
            queja.ubicacion = _queja.ubicacion;
            queja.documentado = _queja.documentado;
            queja.expiradomail = _queja.expiradomail;
            queja.flagalerta = _queja.flagalerta;
            queja.idEstablecimiento = _queja.idEstablecimiento;
        }

        let _sucursal = this.quejasService.sucursal;
        let _idsucursal = this.quejasService.idsucursal;
        console.info('queja', queja);
        if (_sucursal != null && _sucursal != undefined) {
            queja.idEstablecimiento = _sucursal.idEstablecimiento;
            console.info('queja_sucursal', _sucursal, _idsucursal);
            queja.direccionProveedor = _sucursal.direccion;
            queja.idDepartamentoProveedor = _sucursal.idDepartamento;
            queja.idMunicipioProveedor = _sucursal.idMunicipio;
            queja.zonaProveedor = _sucursal.zona;
        } else {
            if (_idsucursal != null && _idsucursal != undefined) {
                queja.idEstablecimiento = _idsucursal;

                //busca la sucursal
                this.quejasService.fetchSucursalProveedor(_idsucursal).subscribe(
                    (response) => {
                        let tempstr = response.value;
                        if (tempstr != null || tempstr != undefined) {
                            queja.direccionProveedor = tempstr.direccion;
                            queja.idDepartamentoProveedor = tempstr.idDepartamento;
                            queja.idMunicipioProveedor = tempstr.idMunicipio;
                            queja.zonaProveedor = tempstr.zona;
                        }
                    }
                );
                console.info('queja_id queja', queja);
            }
        }

        //queja = this.quejasService.createQueja(queja, this.uploader).susbribe;
        this.quejasService.saveData(queja).subscribe(
            (retvalue) => {
                if (retvalue) {
                    var tempstr = retvalue['value'];
                    if (tempstr != null && tempstr != '') {
                        //this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
                        this.vanio = tempstr.anio;
                        this.vsecuencia = tempstr.noQueja;
                        this.vidqueja = tempstr.idQueja;
                        this.completado = true;
                        this.success = true;
                    } else {
                        ////console.log('no llego');
                    }
                } else {
                    ////console.log('Rest service response ERROR.');
                }

                this.quejasService.dynamicAssignment(queja).subscribe(
                    (response) => {
                        console.log('respuesta de asignacion: ', response);
                    }
                );
            }
        );
        this.quejasService.consumidor = null;
        this.quejasService.proveedor = null;
        this.quejasService.quejat = null;
        this.quejasService.sucursal = null;
        this.quejasService.idsucursal = null;
    }

    onDismissClicked() {
        this.success = false;
        this.otro = null;
        this.factura = null;
        this.dpi = null;
        this.quejaForm.reset();
    }

    onHomeClicked() {
        this.CerrarSesion();
        //this.router.navigate(['/Login']);
        this.cerrar_ = 'si';
        //let localresp: QuejaComponent= new QuejaComponent();
        //	this.dialogRef.close(this);
    }

    CerrarSesion() {
        /*
          let loc_token=this._submitFormService.Get_token();
          //console.log(loc_token);
          if(loc_token == '')
              this._seguridadService.RedireccionarLogin();
          this._seguridadService.Logout(loc_token).subscribe((retvalue)=>{
              //console.log('cerrar sesion',retvalue);
              this._seguridadService.RedireccionarLogin();
          },(error)=>{
              this._seguridadService.RedireccionarLogin();
          });
          */
    }

    //graba datos para imprimir la queja
    onPrintQueja() {
        this.quejasService.add_FormQueja(this.vidqueja, '1', 'this.vid').subscribe(
            (response) => {
                let tempstr = response;
                //console.info("findByQuejaById consumidor ",tempstr);
                if (tempstr != null || tempstr != undefined) {
                    //console.info("existe",tempstr);
                    if (tempstr.reason = 'OK') {
                        //console.log('entro a getFormularioQuejaRegistro');
                        let tempresponse = tempstr['value'];
                        let registrodata = JSON.parse('[' + response['value'].slice(0, -1) + ']');

                        //console.log('entro a getFormularioQuejaRegistro',registrodata[0]['id'],tempresponse,tempstr.value["id"],tempresponse["id"],tempresponse['id'], tempresponse.id,tempstr['value']['id']);
                        //this.quejasService.getFormularioQuejaRegistro(registrodata[0]['id'],this.vid).subscribe((Data)=>{
                        this.quejasService.getFormularioQuejaRegistro(this.vidqueja, 'this.vid').subscribe((Data) => {
                            console.log('entro a openRegisto');
                            //this._registrosservice.FileDownLoadChoose(Data,1);
                            const blob = new Blob([Data], {type: 'application/pdf'});
                            const url = window.URL.createObjectURL(blob);
                            var pwa = window.open(url);
                            if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
                                alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
                            }
                            //this.flagInfoError=false;
                        }, (error) => {
                            console.log(error);
                            //this.flagInfoError=true;
                            //this.SetSecTimerInfoError();
                        });
                    }


                } else {
                    //this.existingData = false;
                    //console.info("no existe",response.value);
                    alert('Error no se logró imprimir su solicitud por favor ingrese al sistema para reimpresión, gracias.');
                }
            }
        );

    }
}
