import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var ActividadesEconomicasService = /** @class */ (function () {
    function ActividadesEconomicasService(http) {
        this.http = http;
    }
    ActividadesEconomicasService.prototype.fetchData = function () {
        return this.http.get(BASE_URL_REST_FILE + 'actividades-economicas');
    };
    ActividadesEconomicasService.prototype.fetchTiposComercio = function (idActividadEconomica) {
        return this.http.get(BASE_URL_REST_FILE + 'actividades-economicas/' + idActividadEconomica + '/tipos-comercio');
    };
    ActividadesEconomicasService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], ActividadesEconomicasService);
    return ActividadesEconomicasService;
}());
export { ActividadesEconomicasService };
//# sourceMappingURL=actividades-economicas.service.js.map