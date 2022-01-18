import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { Subject, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
var QuejasIniService = /** @class */ (function () {
    function QuejasIniService(http) {
        this.http = http;
        this.httpOptions = {
            headers: new HttpHeaders({
                'content-type': 'application/json; charset=iso-8859-1'
            })
        };
        this.operation = new Subject();
    }
    QuejasIniService.prototype.createQueja = function (queja, uploader) {
        return this.saveData(queja, '').subscribe(function (retvalue) {
            //console.info('-------------------');
            //console.info(retvalue);
            //console.info('-------------------');
            if (retvalue) {
                var tempstr = retvalue['value'];
                if (tempstr != null && tempstr != '') {
                    //console.log('llego');
                }
                else {
                    //console.log('no llego');
                }
            }
            else {
                //console.log('Rest service response ERROR.');
            }
            if (uploader.queue.length > 0) {
                uploader.setOptions({
                    url: BASE_URL_REST_FILE + 'quejasini/upload',
                    itemAlias: 'file'
                });
                uploader.onCompleteAll = function () {
                    // finalizo la carga de todos los archivos
                    // this.operation.next(response.value);
                };
                uploader.onCompleteItem = function (item, uploadResponse, status, headers) {
                    // finalizo la carga de un archivo
                };
                uploader.uploadAll();
            }
            else {
                //this.operation.next(response.value);
            }
        });
        //this.fetchDataSecId(queja.idFlujo);
    };
    QuejasIniService.prototype.saveData = function (queja, id) {
        return this.http.post(BASE_URL_REST_FILE + 'quejasini' + id, queja, this.httpOptions).pipe(tap(function (item) { return console.log('grabada la queja'); }), catchError(this.handleError('error en grabar ')));
    };
    QuejasIniService.prototype.fetchDataSecId = function (id) {
        return this.http.get(BASE_URL_REST_FILE + 'quejasini/secuencia/' + id);
    };
    QuejasIniService.prototype.fetchDataSecuencia = function () {
        return this.http.get(BASE_URL_REST_FILE + 'quejasini/secuencia');
    };
    QuejasIniService.prototype.fetchDataByToken = function (id, dato) {
        return this.http.get(BASE_URL_REST_FILE + 'quejasini/quejacon/' + dato + '/' + id);
    };
    QuejasIniService.prototype.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            // TODO: send the error to remote logging infrastructure
            //console.error(error); // log to //console instead
            // TODO: better job of transforming error for user consumption
            //console.log('${operation} failed: ${error.message}');
            // Let the app keep running by returning an empty result.
            return of(result);
        };
    };
    QuejasIniService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient])
    ], QuejasIniService);
    return QuejasIniService;
}());
export { QuejasIniService };
//# sourceMappingURL=quejasini.service.js.map