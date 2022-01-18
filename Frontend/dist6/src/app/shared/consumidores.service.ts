import {Injectable} from '@angular/core';
import {BASE_URL_REST_FILE2} from '../conc-virt-const';
import {ResponseRs} from './response-rs.model';
import {Consumidor} from './consumidor.model';
import {Proveedor} from './proveedor.model';
import {Subject, Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {map, catchError, tap} from 'rxjs/operators';
//import { ConsoleReporter } from 'jasmine';

@Injectable({
    providedIn: 'root'
})
export class ConsumidoresService {
    operation = new Subject<Consumidor>();
    operationc = new Subject<Consumidor>();
    operationp = new Subject<Proveedor>();

    constructor(private http: HttpClient) {
    }

    createConsumidor(consumidor: Consumidor) {
        this.saveData(consumidor).subscribe(
            (response) => {
                this.operation.next(response.value);
            }
        );
    }

    private httpOptions = {
        headers: new HttpHeaders({
            'content-type': 'application/json; charset=iso-8859-1',
            
        })
    };

    fetchData(tipo: string, docid: string) {
        return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'consumidores/' + tipo + '?docid=' + docid);
    }

    fetchDataI(docid: string) {
        return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'consumidores?docid=' + docid);
    }

    saveData(consumidor: Consumidor) {

        //let headers = new HttpHeaders().set('Access-Control-Allow-Origin','*'); //AÑADIDO
        return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'consumidores', consumidor);   //AÑADIDO tercer parámetro, {headers: headers}

        /* return this.http.post<any>(BASE_URL_REST_FILE2 + 'consumidores', consumidor, this.httpOptions).pipe(
            tap((item) => console.log('Guardando consumidor')), catchError(this.handleError<any>('error en grabar '))
        ); */

    }

    /* EJEMPLO::::  saveData(queja: Quejaini, id: string) { 
        return this.http.post<any>(BASE_URL_REST_FILE2 + 'quejasini'+id, queja, this.httpOptions).pipe(
            tap((item) => console.log('grabada la queja prueba')),catchError(this.handleError<any>('error en grabar '))
        );
      } */

    fetchDataByTokenConsumidor(id: number): Observable<any> {
        //console.log(this.ObjaJSON_usrpass(str_username, str_password));
        var localURL = BASE_URL_REST_FILE2 + 'consumidores/consumidor?id=' + id;
        return this.http.get<any>(localURL, this.httpOptions).pipe(
            tap((item) => console.log('Added item')),
            catchError(this.handleError<any>('Add Data'))
        );
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            // TODO: send the error to remote logging infrastructure
            //console.error(error); // log to //console instead
            // TODO: better job of transforming error for user consumption
            //console.log('${operation} failed: ${error.message}');
            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    createCP(consumidor: Consumidor, proveedor: Proveedor, tipo: number) {
        if (tipo == 1) {
            this.saveDataC(consumidor).subscribe(
                (response) => {
                    this.operationc.next(response.value);
                }
            );
        } else {
            this.saveDataP(proveedor).subscribe(
                (response) => {
                    this.operationp.next(response.value);
                }
            );
        }
    }

    saveDataC(consumidor: Consumidor) {
        //    return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'consumidores/ins_not_elect', consumidor);
        //}
        return this.http.post<any>(BASE_URL_REST_FILE2 + 'consumidores/ins_not_elect', consumidor, this.httpOptions).pipe(
            tap((item) => console.log('consumidor actualizado')), catchError(this.handleError<any>('error en actualizar'))
        );
    }


    saveDataP(proveedor: Proveedor) {
        //  return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores/ins_not_elect', proveedor);
        //}
        return this.http.post<any>(BASE_URL_REST_FILE2 + 'proveedores/ins_not_elect', proveedor, this.httpOptions).pipe(
            tap((item) => console.log('proveedor actualizado')), catchError(this.handleError<any>('error en actualizar'))
        );
    }

    saveDataCP(url: string, id: number, tipo: number) {
        //  return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores/ins_not_elect', proveedor);
        //}
        return this.http.post<any>(BASE_URL_REST_FILE2 + url, this.ObjaJSON_add(tipo, id), this.httpOptions).pipe(
            tap((item) => console.log('proveedor actualizado')), catchError(this.handleError<any>('error en actualizar'))
        );
    }

    ObjaJSON_add(tipo: number, id: number) {
        if (tipo == 1) {
            var locarrayc = {idConsumidor: id};
            return JSON.stringify(locarrayc);
        } else {
            var locarrayp = {idProveedor: id};
            return JSON.stringify(locarrayp);
        }
    }

}
