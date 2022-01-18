import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var PaisesService = /** @class */ (function () {
    function PaisesService(http) {
        this.http = http;
    }
    PaisesService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'paises');
    };
    PaisesService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], PaisesService);
    return PaisesService;
}());
export { PaisesService };
//# sourceMappingURL=paises.service.js.map