import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
var FileManagerService = /** @class */ (function () {
    function FileManagerService(_http, datePipe) {
        this._http = _http;
        this.datePipe = datePipe;
        this.baseUrl = BASE_URL_REST_FILE + 'tipos-imgqueja';
        this.httpOptions = {
            headers: new HttpHeaders({
                'content-type': 'application/json; charset=iso-8859-1'
            })
        };
    }
    FileManagerService.prototype.extractData = function (res) {
        var body = res;
        return body || {};
    };
    FileManagerService.prototype.getImageList = function (idqueja, idflujo) {
        var localURL = this.baseUrl + '/' + idqueja + '/' + idflujo;
        console.log(localURL);
        return this._http.get(localURL, this.httpOptions).pipe(map(this.extractData));
    };
    FileManagerService.prototype.getImage = function (id_tipo_imagen) {
        var localURL = this.baseUrl + '/image/' + id_tipo_imagen;
        console.log(localURL);
        return this._http.get(localURL, { responseType: 'blob' }
        //,this.httpOptionsFile
        );
    };
    FileManagerService.prototype.getZip = function (idqueja, idflujo) {
        var localURL = this.baseUrl + '/zip/' + idqueja + '/' + idflujo;
        console.log(localURL);
        return this._http.get(localURL, { responseType: 'blob' }
        //,this.httpOptionsFile
        );
    };
    /*getDataQueja(id){
        var localURL=this.baseUrl+'/'+id;
        console.log(localURL);
        return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
    }
    
    getDataQuejaP(id){
        var localURL=this.baseUrl+'/'+id+'/P';
        return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
    }*/
    FileManagerService.prototype.postNewImage = function (filecontrol, idqueja, id_tipo_imagen) {
        console.log(this.baseUrl);
        var formDataloc = new FormData();
        formDataloc.append('attachment', filecontrol);
        formDataloc.append('id_queja', idqueja);
        formDataloc.append('id_tipo_imagen', id_tipo_imagen);
        formDataloc.append('id_categoria_imagen', '1');
        formDataloc.append('id_flujo', '4');
        /*return this._http.post<any>(this.baseUrl, formDataloc , {reportProgress: true} ).pipe(
            tap((item) => console.log('Added Image')),
            catchError(this.handleError<any>('Add Image'))
        );*/
        var req = new HttpRequest('POST', this.baseUrl, formDataloc, {
            reportProgress: true
        });
        return this._http.request(req);
        /*this._http.request( req ).subscribe(event => {
            console.log(event);
            if (event.type === HttpEventType.UploadProgress) {

              // calculate the progress percentage
              const percentDone = Math.round(100 * event.loaded / event.total);

              // pass the percentage into the progress-stream
              //progress.next(percentDone);
              console.log(percentDone);
            } else if (event instanceof HttpResponse) {

              // Close the progress-stream if we get an answer form the API
              // The upload is complete
              //progress.complete();
              console.log('complete');
            }
          },catchError(this.handleError<any>('Add Image'))
          );*/
    };
    FileManagerService.prototype.deleteImage = function (id) {
        var localURL = this.baseUrl + '/' + id;
        console.log(localURL);
        return this._http.delete(localURL, this.httpOptions).pipe(tap(function (item) { return console.log('Deleted Image'); }), catchError(this.handleError('Delete Image')));
    };
    FileManagerService.prototype.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            console.error(error); // log to console instead
            console.log('${operation} failed: ${error.message}');
            return of(result);
        };
    };
    FileManagerService.prototype.convertDate = function (date) {
        return this.datePipe.transform(date, "yyyy-MM-dd");
    };
    FileManagerService.prototype.FileDownLoadChoose = function (data, id_tipo_imagen) {
        if (id_tipo_imagen == 1) {
            this.downloadPDFFile(data);
        }
        else if (id_tipo_imagen == 2) {
            this.downloadPNGFile(data);
        }
        else if (id_tipo_imagen == 3) {
            this.downloadJPGFile(data);
        }
        else if (id_tipo_imagen == 4) {
            this.downloadGIFFile(data);
        }
        else if (id_tipo_imagen == 99) {
            this.downloadZIPFile(data);
        }
    };
    FileManagerService.prototype.downloadZIPFile = function (data) {
        var blob = new Blob([data], { type: 'application/zip' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
        /*var link = document.createElement("a");
        link.download = 'testname.zip';
        link.href = "data:application/zip;base64," + data;
        document.body.appendChild(link);
        link.click();
        // Cleanup the DOM
        document.body.removeChild(link);*/
    };
    FileManagerService.prototype.downloadTXTFile = function (data) {
        var blob = new Blob([data], { type: 'text/plain' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
    };
    FileManagerService.prototype.downloadPDFFile = function (data) {
        var blob = new Blob([data], { type: 'application/pdf' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
    };
    FileManagerService.prototype.downloadPNGFile = function (data) {
        var blob = new Blob([data], { type: 'image/png' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
    };
    FileManagerService.prototype.downloadJPGFile = function (data) {
        var blob = new Blob([data], { type: 'image/jpeg' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
    };
    FileManagerService.prototype.downloadGIFFile = function (data) {
        var blob = new Blob([data], { type: 'image/gif' });
        var url = window.URL.createObjectURL(blob);
        var pwa = window.open(url);
        if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
            alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
        }
    };
    FileManagerService.prototype.VerifyFileSize = function (size) {
        if (size < 40000000)
            return true;
        return false;
    };
    FileManagerService.prototype.TranslateTipoImagen = function (tipo) {
        if (tipo == 'application/pdf') {
            return 1;
        }
        else if (tipo == 'image/png') {
            return 2;
        }
        else if (tipo == 'image/jpeg') {
            return 3;
        }
        else if (tipo == 'image/gif') {
            return 4;
        }
        return null;
    };
    FileManagerService = tslib_1.__decorate([
        Injectable({
            providedIn: 'root'
        }),
        tslib_1.__metadata("design:paramtypes", [HttpClient, DatePipe])
    ], FileManagerService);
    return FileManagerService;
}());
export { FileManagerService };
//# sourceMappingURL=file-manager.service.js.map