import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var ProveedoresComunesService = /** @class */ (function () {
    function ProveedoresComunesService(http) {
        this.http = http;
    }
    ProveedoresComunesService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'proveedores-comunes');
    };
    ProveedoresComunesService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], ProveedoresComunesService);
    return ProveedoresComunesService;
}());
export { ProveedoresComunesService };
//# sourceMappingURL=proveedores-comunes.service.js.map