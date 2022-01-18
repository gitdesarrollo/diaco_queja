import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { BASE_URL_REST } from '../conc-virt-const';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
var CatalogoService = /** @class */ (function () {
    function CatalogoService(_http) {
        this._http = _http;
        this.baseUrl = BASE_URL_REST + 'tipos-catalogo';
        this.httpOptions = {
            headers: new HttpHeaders({
                'content-type': 'application/json; charset=iso-8859-1'
            })
        };
    }
    CatalogoService.prototype.extractData = function (res) {
        var body = res;
        return body || {};
    };
    CatalogoService.prototype.getData = function (table) {
        var localURL = this.baseUrl + '/' + table;
        //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
        return this._http.get(localURL, this.httpOptions).pipe(map(this.extractData));
    };
    CatalogoService.prototype.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            console.log('${operation} failed: ${error.message}');
            // Let the app keep running by returning an empty result.
            return of(result);
        };
    };
    CatalogoService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], CatalogoService);
    return CatalogoService;
}());
export { CatalogoService };
//# sourceMappingURL=catalogo.service.js.map