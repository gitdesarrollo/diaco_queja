import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var TiposComercioService = /** @class */ (function () {
    function TiposComercioService(http) {
        this.http = http;
    }
    TiposComercioService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'tipos-comercio');
    };
    TiposComercioService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], TiposComercioService);
    return TiposComercioService;
}());
export { TiposComercioService };
//# sourceMappingURL=tipos-comercio.service.js.map