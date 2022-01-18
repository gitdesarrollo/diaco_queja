import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var EtniasService = /** @class */ (function () {
    function EtniasService(http) {
        this.http = http;
    }
    EtniasService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'etnias');
    };
    EtniasService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], EtniasService);
    return EtniasService;
}());
export { EtniasService };
//# sourceMappingURL=etnias.service.js.map