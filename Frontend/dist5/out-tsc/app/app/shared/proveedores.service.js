import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { Subject } from 'rxjs';
var ProveedoresService = /** @class */ (function () {
    function ProveedoresService(http) {
        this.http = http;
        this.operation = new Subject();
    }
    ProveedoresService.prototype.createProveedor = function (proveedor) {
        var _this = this;
        this.saveData(proveedor).subscribe(function (response) {
            _this.operation.next(response.value);
        });
    };
    ProveedoresService.prototype.fetchData = function (nit) {
        return this.http.get(BASE_URL_REST_FILE + 'proveedores?nit=' + nit);
    };
    ProveedoresService.prototype.fetchDataProv = function (nit, nombre) {
        return this.http.get(BASE_URL_REST_FILE + 'proveedores/busca?nit=' + nit + "&nombre=" + nombre);
    };
    ProveedoresService.prototype.saveData = function (proveedor) {
        return this.http.post(BASE_URL_REST_FILE + 'proveedores', proveedor);
    };
    ProveedoresService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], ProveedoresService);
    return ProveedoresService;
}());
export { ProveedoresService };
//# sourceMappingURL=proveedores.service.js.map