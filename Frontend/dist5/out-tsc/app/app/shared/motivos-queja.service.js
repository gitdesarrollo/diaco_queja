import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var MotivosQuejaService = /** @class */ (function () {
    function MotivosQuejaService(http) {
        this.http = http;
    }
    MotivosQuejaService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'motivos-queja');
    };
    MotivosQuejaService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], MotivosQuejaService);
    return MotivosQuejaService;
}());
export { MotivosQuejaService };
//# sourceMappingURL=motivos-queja.service.js.map