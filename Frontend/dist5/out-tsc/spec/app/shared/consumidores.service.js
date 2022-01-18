import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { Subject } from 'rxjs';
var ConsumidoresService = /** @class */ (function () {
    function ConsumidoresService(http) {
        this.http = http;
        this.operation = new Subject();
    }
    ConsumidoresService.prototype.createConsumidor = function (consumidor) {
        var _this = this;
        this.saveData(consumidor).subscribe(function (response) {
            _this.operation.next(response.value);
        });
    };
    ConsumidoresService.prototype.fetchData = function (docid) {
        return this.http.get(BASE_URL_REST_FILE + 'consumidores?docid=' + docid);
    };
    ConsumidoresService.prototype.saveData = function (consumidor) {
        return this.http.post(BASE_URL_REST_FILE + 'consumidores', consumidor);
    };
    ConsumidoresService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], ConsumidoresService);
    return ConsumidoresService;
}());
export { ConsumidoresService };
//# sourceMappingURL=consumidores.service.js.map