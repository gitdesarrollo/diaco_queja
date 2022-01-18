import * as tslib_1 from "tslib";
import { Component, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { ApiUsuariosService } from './api-usuarios.service';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { Proveedor } from '../shared/proveedor.model';
var BuscaprovComponent = /** @class */ (function () {
    function BuscaprovComponent(dataService, dialogRef) {
        this.dataService = dataService;
        this.dialogRef = dialogRef;
        this.displayedColumns = ['Id', 'Documento', 'Nombre', 'Seleccionar'];
        //this.logService = new LogService(this.LOG_TAG);
    }
    BuscaprovComponent.prototype.ngOnInit = function () {
        this.initProveedorForm();
    };
    BuscaprovComponent.prototype.initProveedorForm = function () {
        this.proveedorForm = new FormGroup({
            'nombrep': new FormControl(''),
            'nitProveedor': new FormControl('')
        });
        this.proveedorToInsert = new Proveedor();
        this.proveedorToInsert.idProveedor = 0;
        this.proveedorToInsert.nombre = "";
        this.proveedorToInsert.nitProveedor = "";
    };
    BuscaprovComponent.prototype.RenderDataTable = function () {
        var _this = this;
        this.dataService.getUsuarios()
            .subscribe(function (res) {
            _this.dataSource = new MatTableDataSource();
            _this.dataSource.data = res;
            //console.log(this.dataSource.data);
            _this.dataSource.sort = _this.sort;
            _this.dataSource.paginator = _this.paginator;
        }, function (error) {
            console.log('Se produjo un error mientras intentaba recuperar Usuarios!' + error);
        });
    };
    BuscaprovComponent.prototype.RenderDataTableProveedor = function () {
        var _this = this;
        if (this.proveedorForm.value.nitProveedor == "" && this.proveedorForm.value.nombrep == "") {
            this.dataSource = null;
        }
        else {
            this.dataService.fetchDataProv(this.proveedorForm.value.nitProveedor, this.proveedorForm.value.nombrep)
                .subscribe(function (res) {
                _this.dataSource = new MatTableDataSource();
                _this.dataSource.data = res['value'];
                //console.log(this.dataSource.data);
                _this.dataSource.sort = _this.sort;
                _this.dataSource.paginator = _this.paginator;
            }, function (error) {
                console.log('Se produjo un error mientras intentaba recuperar Usuarios!' + error);
            });
        }
    };
    BuscaprovComponent.prototype.applyFilter = function (filterValue) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.dataSource.filter = filterValue;
    };
    BuscaprovComponent.prototype.seleccionar = function (valor, nombre, nit) {
        this.proveedorToInsert.idProveedor = valor;
        this.proveedorToInsert.nombre = nombre;
        this.proveedorToInsert.nitProveedor = nit;
        //this.initProveedorForm();
        //this.dataSource = null;	
        console.info(this.proveedorToInsert.idProveedor);
        console.info(this.proveedorToInsert.nombre);
    };
    BuscaprovComponent.prototype.courseToInsert = function () {
    };
    tslib_1.__decorate([
        ViewChild(MatPaginator),
        tslib_1.__metadata("design:type", MatPaginator)
    ], BuscaprovComponent.prototype, "paginator", void 0);
    tslib_1.__decorate([
        ViewChild(MatSort),
        tslib_1.__metadata("design:type", MatSort)
    ], BuscaprovComponent.prototype, "sort", void 0);
    BuscaprovComponent = tslib_1.__decorate([
        Component({
            selector: 'app-buscaprov',
            templateUrl: './buscaprov.component.html',
            styleUrls: ['./buscaprov.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [ApiUsuariosService, MatDialogRef])
    ], BuscaprovComponent);
    return BuscaprovComponent;
}());
export { BuscaprovComponent };
//# sourceMappingURL=buscaprov.component.js.map