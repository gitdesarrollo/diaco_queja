import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var ApiUsuariosService = /** @class */ (function () {
    function ApiUsuariosService(http) {
        this.http = http;
    }
    ApiUsuariosService.prototype.getUsuarios = function () {
        return this.http.get('http://apiuser.azurewebsites.net/api/usuarios');
    };
    ApiUsuariosService.prototype.fetchDataProv = function (nit, nombre) {
        return this.http.get(BASE_URL_REST_FILE + 'proveedores/busca?nit=' + nit + "&nombre=" + nombre);
    };
    ApiUsuariosService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], ApiUsuariosService);
    return ApiUsuariosService;
}());
export { ApiUsuariosService };
//# sourceMappingURL=api-usuarios.service.js.map