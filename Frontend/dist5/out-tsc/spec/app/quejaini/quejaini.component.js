import * as tslib_1 from "tslib";
//import { Component, OnInit } from '@angular/core';
import { Component, ViewChild, ElementRef } from '@angular/core';
//import { ormGroup,  FormControl } from '@angular/forms';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { QuejasIniService } from '../shared/quejasini.service';
import { Quejaini } from '../shared/quejaini.model';
import { FileUploader } from "ng2-file-upload";
import { BASE_URL_FILE } from '../conc-virt-const';
import { ConsumidoresService } from '../shared/consumidores.service';
import { ProveedoresService } from '../shared/proveedores.service';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
//import { MyCustomDialogService } from '../my-custom-dialog/my-custom-dialog.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { BuscaprovComponent } from '../buscaprov/buscaprov.component';
import { isUndefined } from 'util';
var QuejainiComponent = /** @class */ (function () {
    //constructor() { }
    function QuejainiComponent(router, dialog, consumidoresService, proveedoresService, quejasService
    //,private customDialog: MyCustomDialogService
    //,private fb: FormBuilder
    ) {
        //console.info(this._route.snapshot.paramMap.get('id'));
        this.router = router;
        this.dialog = dialog;
        this.consumidoresService = consumidoresService;
        this.proveedoresService = proveedoresService;
        this.quejasService = quejasService;
        this.nproveedor = "";
        this.vanio = 0;
        this.vsecanio = 0;
        this.vsecuencia2 = "";
        this.vidqueja = 0;
        this.vidimagen = 2;
        this.vdpi = -1;
        this.vfac = -1;
        this.vact = -1;
        this.existingModeConsumidor = false;
        this.existingModeProveedor = false;
        this.vsecuencia = "";
        this.uploader = new FileUploader({
            url: BASE_URL_FILE,
            itemAlias: 'document'
        });
        this.success = false;
        this.successFile = true;
        this.showSpinner = false;
        this.operation = new Subject();
    }
    QuejainiComponent.prototype.ngOnDestroy = function () {
        ////console.log("finaliza");
    };
    QuejainiComponent.prototype.ngOnInit = function () {
        var _this = this;
        // listener cuando finaliza la operacion de creacion
        this.quejaOperationSubscription = this.quejasService.operation.subscribe(function (queja) {
            // limpia sesion
            _this.quejaForm.reset();
            _this.success = true;
            _this.successFile = true;
            _this.nproveedor = "";
        });
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
    };
    QuejainiComponent.prototype.initQuejaForm = function () {
        this.quejaForm = new FormGroup({
            'nombre': new FormControl('', Validators.required),
            'apellido': new FormControl('', Validators.required),
            'dpiPasaporte': new FormControl('', Validators.required),
            'telefono': new FormControl('', Validators.required),
            'correo': new FormControl('', Validators.required),
            'detalleQueja': new FormControl('', Validators.required),
            'solicitaQue': new FormControl('', Validators.required),
            'nitProveedor': new FormControl('', Validators.required),
            'ubicacion': new FormControl('', Validators.required)
        });
        this.nproveedor = "";
        this.dpiIndex = -1;
        this.facturaIndex = -1;
        ////console.log("inicializa");
    };
    QuejainiComponent.prototype.onFileChanged = function (type) {
        console.info(type);
        this.vact = this.uploader.queue.length - 1;
        console.info(this.factura);
        if (this.uploader.queue.length > 0) {
            if (type == 'FAC') {
                if (this.factura && this.facturaIndex > -1) {
                    var facturaItem = this.uploader.queue[this.facturaIndex];
                    this.uploader.removeFromQueue(facturaItem);
                }
                this.factura = this.uploader.queue[this.uploader.queue.length - 1];
                this.facturaIndex = this.uploader.queue.length - 1;
                if (this.dpiIndex == this.facturaIndex) {
                    this.dpiIndex = this.dpiIndex - 1;
                }
            }
            else if (type == 'DPI') {
                if (this.dpi && this.dpiIndex > -1) {
                    var dpiItem = this.uploader.queue[this.dpiIndex];
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
            }
            else {
                this.successFile = true;
            }
            //console.info(this.uploader.queue.length);	
            //console.info(this.uploader);
        }
    };
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
    QuejainiComponent.prototype.onSubmit = function () {
        var _this = this;
        this.showSpinner = true;
        var queja = new Quejaini();
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
        queja.idFuente = 'Call Center';
        if (this.existingModeConsumidor) {
            queja.idConsumidor = this.existingConsumidor.idConsumidor;
        }
        else {
            queja.idConsumidor = 0;
        }
        if (this.existingModeProveedor) {
            queja.idProveedor = this.existingProveedor.idProveedor;
        }
        else {
            queja.idProveedor = 0;
        }
        //queja = this.quejasService.createQueja(queja, this.uploader).susbribe;
        this.quejasService.saveData(queja, '/noupload').subscribe(function (retvalue) {
            if (retvalue) {
                var tempstr = retvalue['value'];
                if (tempstr != null && tempstr != '') {
                    //this.registrodata=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
                    _this.vanio = tempstr.anio;
                    _this.vsecanio = tempstr.secuencia;
                    _this.vidqueja = tempstr.idQueja;
                    if (_this.uploader.queue.length > 0) {
                        //console.log(this.vidqueja);
                        _this.uploader.setOptions({
                            url: BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + _this.vidqueja + "&id_categoria_imagen=" + _this.vidimagen,
                            itemAlias: 'file'
                        });
                        _this.uploader.onCompleteAll = function () {
                            // finalizo la carga de todos los archivos
                            _this.operation.next(retvalue.value);
                        };
                        _this.uploader.onCompleteItem = function (item, uploadResponse, status, headers) {
                            // finalizo la carga de un archivo
                            _this.vidimagen = _this.vidimagen + 1;
                        };
                        _this.uploader.uploadAll();
                    }
                    else {
                        _this.operation.next(retvalue.value);
                    }
                    _this.success = true;
                }
                else {
                    ////console.log('no llego');
                }
            }
            else {
                ////console.log('Rest service response ERROR.');
            }
        });
        this.onDismissClicked();
        this.showSpinner = false;
        //this.findSecuenciaId();
        //////console.info("onsSumiT");
        //this.vanio = queja.anio;
        //  this.vsecanio = queja.noQueja;
        ////console.info('-------------------');
        ////console.info(queja);
        ////console.info('-------------------');
    };
    QuejainiComponent.prototype.onDismissClicked = function () {
        this.success = false;
        this.initQuejaForm();
        ////console.info("onDismm");
    };
    QuejainiComponent.prototype.initConsumidor = function (consumidor, tipo) {
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
                'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor, Validators.required),
                'ubicacion': new FormControl(this.quejaForm.value.ubicacion, Validators.required)
            });
        }
        else {
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
                'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor, Validators.required),
                'ubicacion': new FormControl(this.quejaForm.value.ubicacion, Validators.required)
            });
        }
        this.findBySecuencia();
        //////console.log("inicializa consumidor");
    };
    QuejainiComponent.prototype.initProveedor = function (nit) {
        this.quejaForm = new FormGroup({
            'nombre': new FormControl(this.quejaForm.value.nombre, Validators.required),
            'apellido': new FormControl(this.quejaForm.value.apellido, Validators.required),
            'telefono': new FormControl(this.quejaForm.value.telefono, Validators.required),
            'correo': new FormControl(this.quejaForm.value.correo, Validators.email),
            'dpiPasaporte': new FormControl(this.quejaForm.value.dpiPasaporte, Validators.required),
            'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
            'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
            'nitProveedor': new FormControl(nit, Validators.required),
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
            'ubicacion': new FormControl(this.quejaForm.value.ubicacion, Validators.required)
        });
        //////console.log("inicializa consumidor");
    };
    QuejainiComponent.prototype.onDocumentoIdentificacionChanged = function () {
        var _this = this;
        //////console.log(this.quejaForm.value.dpiPasaporte);
        this.consumidoresService.fetchData(this.quejaForm.value.dpiPasaporte).subscribe(function (response) {
            if (response.value) {
                // inicializar formulario con valores predeterminados
                _this.existingModeConsumidor = true;
                _this.existingConsumidor = response.value;
                _this.initConsumidor(response.value, 0);
                //queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
            }
            else {
                _this.existingModeConsumidor = false;
                _this.existingConsumidor = null;
                _this.initConsumidor(null, 1);
            }
        });
        //////console.log("ingreso al onDocumentoIdentificacionChanged");
    };
    QuejainiComponent.prototype.onProveedorChanged = function () {
        var _this = this;
        var dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
        var dialogRef = this.dialog.open(BuscaprovComponent, dialogConfig);
        dialogRef.afterClosed().subscribe(function (data) {
            if (!isUndefined(data)) {
                console.info(data);
                // inicializar formulario con valores predeterminados
                _this.existingModeProveedor = true;
                _this.existingProveedor = data;
                _this.initProveedor(data.nitProveedor);
                _this.nproveedor = data.nombre;
            }
            else {
                _this.existingModeProveedor = false;
                _this.existingProveedor = null;
                _this.nproveedor = "";
            }
        }, function (error) { return console.info('Undefined data. No will insert 2'); }); //this.logService.print(error, LogService.ERROR_MSG));
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
    };
    QuejainiComponent.prototype.initNoQueja = function (queja) {
        this.vanio = (queja ? queja.anio : 0);
        this.vsecanio = (queja ? queja.noQueja : 0);
        ////console.info('initNoQueja|');
        ////console.info(this.vanio);
        ////console.info(this.vsecanio);
    };
    QuejainiComponent.prototype.findSecuenciaId = function () {
        var _this = this;
        if (this.vsecuencia = "") {
        }
        else {
            this.quejasService.fetchDataSecId(this.vsecuencia).subscribe(function (response) {
                if (response.value) {
                    // inicializar formulario con valores predeterminados
                    ////console.info("findSecuenc");
                    _this.initNoQueja(response.value);
                }
            });
        }
    };
    QuejainiComponent.prototype.findBySecuencia = function () {
        var _this = this;
        this.quejasService.fetchDataSecuencia().subscribe(function (response) {
            if (response.value) {
                // inicializar formulario con valores predeterminados
                //
                _this.vsecuencia2 = response.value;
                _this.vsecuencia = response.value;
            }
        });
    };
    QuejainiComponent.prototype.openCustomDialog = function () {
        console.log("this.customDialogForm.value");
        //this.customDialog.openAlertDialog(this.customDialogForm.value);
        this.success = false;
    };
    tslib_1.__decorate([
        ViewChild('filDpi'),
        tslib_1.__metadata("design:type", ElementRef)
    ], QuejainiComponent.prototype, "filDpi", void 0);
    QuejainiComponent = tslib_1.__decorate([
        Component({
            selector: 'app-quejaini',
            templateUrl: './quejaini.component.html',
            styleUrls: ['./quejaini.component.scss']
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            MatDialog,
            ConsumidoresService,
            ProveedoresService,
            QuejasIniService
            //,private customDialog: MyCustomDialogService
            //,private fb: FormBuilder
        ])
    ], QuejainiComponent);
    return QuejainiComponent;
}());
export { QuejainiComponent };
//# sourceMappingURL=quejaini.component.js.map