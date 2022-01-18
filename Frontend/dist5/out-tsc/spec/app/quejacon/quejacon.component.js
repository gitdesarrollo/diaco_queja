import * as tslib_1 from "tslib";
//import { Component, OnInit } from '@angular/core';
import { Component, ViewChild, ElementRef } from '@angular/core';
//import { ormGroup,  FormControl } from '@angular/forms';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Subject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { QuejasIniService } from '../shared/quejasini.service';
import { Quejaini } from '../shared/quejaini.model';
import { FileUploader } from "ng2-file-upload";
import { ConsumidoresService } from '../shared/consumidores.service';
import { ProveedoresService } from '../shared/proveedores.service';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { MyAlertDialogComponent } from '../my-alert-dialog/my-alert-dialog.component';
import { MatDialog } from '@angular/material';
var QuejaconComponent = /** @class */ (function () {
    //constructor() { }
    function QuejaconComponent(router, dialog, _route, consumidoresService, proveedoresService, quejasService) {
        this.router = router;
        this.dialog = dialog;
        this._route = _route;
        this.consumidoresService = consumidoresService;
        this.proveedoresService = proveedoresService;
        this.quejasService = quejasService;
        this.nproveedor = "";
        this.vanio = 0;
        this.vsecanio = 0;
        this.vsecuencia2 = "";
        this.vidqueja = 0;
        this.vidimagen = 3;
        this.vdpi = -1;
        this.vfac = -1;
        this.vact = -1;
        this.dato = "";
        this.vid = "";
        //variables a mostrar
        this._vDpiPasaporte = "";
        this._vNombre = "";
        this._vTelefono = "";
        this._vCorreo = "";
        this._vDetalleQueja = "";
        this._vSolicitaQue = "";
        this._vNitProveedor = "";
        this._vUbicacion = "";
        this._url = "";
        this.existingData = false;
        this.existingModeConsumidor = false;
        this.existingModeProveedor = false;
        this.existingModeToken = false;
        this.vsecuencia = "";
        this.uploader = new FileUploader({
            //url: BASE_URL_FILE,
            itemAlias: 'document'
        });
        this.success = false;
        this.successFile = true;
        this.operation = new Subject();
        //console.info(this._route.snapshot.paramMap.get('id'));
    }
    QuejaconComponent.prototype.ngOnDestroy = function () {
        ////console.log("finaliza");
    };
    QuejaconComponent.prototype.ngOnInit = function () {
        var _this = this;
        // listener cuando finaliza la operacion de creacion
        this.quejaOperationSubscription = this.quejasService.operation.subscribe(function (queja) {
            // limpia sesion
            _this.quejaForm.reset();
            _this.success = true;
            _this.successFile = true;
            _this.nproveedor = "";
        });
        this._route.params.subscribe(function (params) {
            _this.dato = params.dato;
            _this.vid = params.id;
        });
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
    };
    QuejaconComponent.prototype.initQuejaForm = function () {
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
    };
    QuejaconComponent.prototype.unregister = function () {
        var dialogRef = this.dialog.open(MyAlertDialogComponent);
        dialogRef.afterClosed().subscribe(function (result) {
            // NOTE: The result can also be nothing if the user presses the `esc` key or clicks outside the dialog
            if (result == 'confirm') {
                console.log('Unregistered');
            }
        });
    };
    QuejaconComponent.prototype.onFileChanged = function (type) {
        //console.info(type);	
        this.vact = this.uploader.queue.length - 1;
        //console.info(this.factura);
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
            //console.info(this.uploader.queue.length);	
            if (this.uploader.queue.length == 2 && this._vDpiPasaporte != undefined) {
                this.successFile = false;
            }
            else {
                this.successFile = true;
            }
            console.info(this._vDpiPasaporte, this.uploader.queue.length == 2);
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
    QuejaconComponent.prototype.onSubmit = function () {
        var _this = this;
        var queja = new Quejaini();
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
        // verifica que esten ingresados los 2 archivos
        if (this.uploader.queue.length != 2) {
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
            var removeItem = this.uploader.queue[1];
            this.uploader.removeFromQueue(removeItem);
            removeItem = this.uploader.queue[0];
            this.uploader.removeFromQueue(removeItem);
            this.uploader.queue.push(this.factura);
            this.uploader.queue.push(this.dpi);
            //console.info(this.uploader.queue.length);
        }
        if (this.uploader.queue.length > 0) {
            //console.log(this.vidqueja);
            this.uploader.onBeforeUploadItem = function (removeItem) {
                //removeItem.withCredentials = false;
                //removeItem.url = BASE_URL_REST_FILE + 'quejas/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen;
                //BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
                for (var i = 0; i < _this.uploader.queue.length; i++) {
                    //console.log("modifica url - inicio");
                    //console.log(i);
                    var removeItem_1 = _this.uploader.queue[i];
                    if (removeItem_1 == _this.dpi) {
                        //console.log("cambia url dpi");
                        _this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + _this.vidqueja + "&id_categoria_imagen=" + 2 + "&correo=1&data=" + _this.vid;
                        //console.log(this.uploader.queue[i].url);
                        //console.log(removeItem);
                    }
                    else {
                        if (removeItem_1 == _this.factura) {
                            //console.log("cambia url factura");
                            _this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + _this.vidqueja + "&id_categoria_imagen=" + 3 + "&correo=0&data=" + _this.vid;
                            //console.log(this.uploader.queue[i].url);	
                            //console.log(removeItem);
                        }
                        else {
                            //console.log("cambia url otro");
                            _this.uploader.queue[i].url = BASE_URL_REST_FILE + 'quejasini/upload?id_queja=' + _this.vidqueja + "&id_categoria_imagen=" + 1 + "&correo=0&data=" + _this.vid;
                            //console.log(this.uploader.queue[i].url);
                            //console.log(removeItem);
                        }
                    }
                    //console.log("modifica url - fin");	
                }
            };
            this.uploader.setOptions({
                //url: BASE_URL_REST_FILE + 'quejasini/upload?id_queja='+this.vidqueja+"&id_categoria_imagen="+this.vidimagen,
                itemAlias: 'file'
            });
            this.uploader.onCompleteAll = function () {
                // finalizo la carga de todos los archivos
                // this.operation.next(retvalue.value);
                //console.log("completo inicia limpieza");
                _this._vDpiPasaporte = '';
                _this._vNombre = '';
                _this._vTelefono = '';
                _this._vCorreo = '';
                _this._vDetalleQueja = '';
                _this._vSolicitaQue = '';
                _this._vNitProveedor = '';
                _this._vUbicacion = '';
                _this.findByToken("1");
                _this.successFile = true;
                _this.success = true;
                /*
          //
            //this.vanio = 0;
            //this.vsecanio =  0;
            //this.vidqueja =  0;
            //this.uploader = null;
            */
            };
            this.uploader.onCompleteItem = function (item, uploadResponse, status, headers) {
                // finalizo la carga de un archivo
                // this.vidimagen = this.vidimagen + 1;
                console.log('ImageUpload:uploaded:', item, status, headers);
            };
            this.uploader.uploadAll();
        }
        else {
            //this.operation.next(retvalue.value);
        }
        //this.findSecuenciaId();
        //////console.info("onsSumiT");
        //this.vanio = queja.anio;
        //  this.vsecanio = queja.noQueja;
        ////console.info('-------------------');
        ////console.info(queja);
        ////console.info('-------------------');
    };
    QuejaconComponent.prototype.onDismissClicked = function () {
        this.success = false;
        this.initQuejaForm();
        console.info("onDismm");
    };
    QuejaconComponent.prototype.initQuejaParam = function (queja) {
        this._vDpiPasaporte = (queja ? queja.dpiPasaporte : '');
        this._vNombre = (queja ? queja.nombre : '');
        this._vTelefono = (queja ? queja.telefono : '');
        this._vCorreo = (queja ? queja.correo : '');
        this._vDetalleQueja = (queja ? queja.detalleQueja : '');
        this._vSolicitaQue = (queja ? queja.solicitaQue : '');
        this._vNitProveedor = (queja ? queja.nitProveedor : '');
        this.vanio = (queja ? queja.anio : 0);
        this.vsecanio = (queja ? queja.secuencia : 0);
        this._vUbicacion = (queja ? queja.ubicacion : '');
        this.vidqueja = (queja ? queja.idQueja : 0);
    };
    QuejaconComponent.prototype.initConsumidor = function (consumidor) {
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
            'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
            'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
            'nitProveedor': new FormControl(this.quejaForm.value.nitProveedor, Validators.required),
            'ubicacion': new FormControl(this.quejaForm.value.ubicacion)
        });
        this.findBySecuencia();
        //////console.log("inicializa consumidor");
    };
    QuejaconComponent.prototype.initProveedor = function (proveedor) {
        this.quejaForm = new FormGroup({
            'nombre': new FormControl(this.quejaForm.value.nombre, Validators.required),
            'telefono': new FormControl(this.quejaForm.value.telefono, Validators.required),
            'correo': new FormControl(this.quejaForm.value.correo, Validators.email),
            'dpiPasaporte': new FormControl(this.quejaForm.value.dpiPasaporte, Validators.required),
            'detalleQueja': new FormControl(this.quejaForm.value.detalleQueja, Validators.required),
            'solicitaQue': new FormControl(this.quejaForm.value.solicitaQue, Validators.required),
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
    };
    QuejaconComponent.prototype.onDocumentoIdentificacionChanged = function () {
        var _this = this;
        //////console.log(this.quejaForm.value.dpiPasaporte);
        this.consumidoresService.fetchData(this.quejaForm.value.dpiPasaporte).subscribe(function (response) {
            if (response.value) {
                // inicializar formulario con valores predeterminados
                _this.existingModeConsumidor = true;
                _this.existingConsumidor = response.value;
                _this.initConsumidor(response.value);
                //queja.dpiPasaporte = this.quejaForm.value.dpiPasaporte;
            }
            else {
                _this.initConsumidor(null);
                _this.existingModeConsumidor = false;
                _this.existingConsumidor = null;
            }
        });
        //////console.log("ingreso al onDocumentoIdentificacionChanged");
    };
    QuejaconComponent.prototype.onProveedorChanged = function () {
        var _this = this;
        //////console.log(this.quejaForm.value.nitProveedor);
        this.proveedoresService.fetchData(this.quejaForm.value.nitProveedor).subscribe(function (response) {
            if (response.value) {
                // inicializar formulario con valores predeterminados
                _this.existingModeProveedor = true;
                _this.existingProveedor = response.value;
                _this.initProveedor(response.value);
                _this.nproveedor = response.value.nombre;
            }
            else {
                _this.existingModeProveedor = false;
                _this.existingProveedor = null;
                _this.nproveedor = "";
            }
        });
        //////console.log("ingreso al onProveedorChanged");
    };
    QuejaconComponent.prototype.initNoQueja = function (queja) {
        this.vanio = (queja ? queja.anio : 0);
        this.vsecanio = (queja ? queja.noQueja : 0);
        ////console.info('initNoQueja|');
        ////console.info(this.vanio);
        ////console.info(this.vsecanio);
    };
    QuejaconComponent.prototype.findSecuenciaId = function () {
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
    QuejaconComponent.prototype.findBySecuencia = function () {
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
    QuejaconComponent.prototype.findByToken = function (dato) {
        var _this = this;
        this.quejasService.fetchDataByToken(this.vid, dato).subscribe(function (response) {
            if (response.value) {
                // inicializar formulario con valores predeterminados
                //
                _this.existingQueja = response.value;
                _this.existingModeToken = true;
                _this.initQuejaParam(response.value);
            }
            else {
                _this.existingData = false;
            }
        });
        console.info(this._vDpiPasaporte);
    };
    tslib_1.__decorate([
        ViewChild('filDpi'),
        tslib_1.__metadata("design:type", ElementRef)
    ], QuejaconComponent.prototype, "filDpi", void 0);
    QuejaconComponent = tslib_1.__decorate([
        Component({
            selector: 'app-quejacon',
            templateUrl: './quejacon.component.html',
            styleUrls: ['./quejacon.component.scss']
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            MatDialog,
            ActivatedRoute,
            ConsumidoresService,
            ProveedoresService,
            QuejasIniService])
    ], QuejaconComponent);
    return QuejaconComponent;
}());
export { QuejaconComponent };
//# sourceMappingURL=quejacon.component.js.map