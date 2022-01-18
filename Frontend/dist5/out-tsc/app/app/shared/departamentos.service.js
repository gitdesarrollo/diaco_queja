import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var DepartamentosService = /** @class */ (function () {
    function DepartamentosService(http) {
        this.http = http;
    }
    DepartamentosService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'departamentos');
    };
    DepartamentosService.prototype.fetchMunicipios = function (codigoDepartamento) {
        return this.http.get(BASE_URL_REST_FILE + 'departamentos/' + codigoDepartamento + '/municipios');
    };
    DepartamentosService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], DepartamentosService);
    return DepartamentosService;
}());
export { DepartamentosService };
//# sourceMappingURL=departamentos.service.js.map