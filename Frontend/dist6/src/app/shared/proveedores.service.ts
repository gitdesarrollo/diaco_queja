import { Injectable } from '@angular/core';
import { BASE_URL_REST_FILE2} from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';
import { Proveedor } from './proveedor.model';
import { Proveedorpcv } from '../shared/proveedorpcv.model';
import { Subject,Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse ,HttpParams} from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { SucursalesNit } from '../shared/sucursales-nit.model';
import { Confirmacion } from './confirmacion';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {
  operation = new Subject<Proveedor>();
  operations = new Subject<SucursalesNit>();

  constructor(private http: HttpClient) {}

  createProveedor(proveedor: Proveedor) {
    this.saveData(proveedor).subscribe(
      (response) => {
        this.operation.next(response.value);
      }
    );
  }

   private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  fetchData(nit: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores?nit=' + nit);
  }
  
  fetchDataId(id: string) {
	  return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores/id?id=' + id);
  }

  saveData(proveedor: Proveedor) {
    return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores', proveedor);
  }
 
   saveDataPCV(proveedor: Proveedorpcv) {
    return this.http.post<any>(BASE_URL_REST_FILE2 + 'proveedores/aprobar', proveedor, this.httpOptions).pipe(
		tap((item) => console.log('grabada la proveedor')),catchError(this.handleError<any>('error en grabar '))
	);
   }

   confirmarCorreoProveedor(confirmacion: Confirmacion) {
    return this.http.post<any>(BASE_URL_REST_FILE2 + 'proveedores/confirmarproveedor', confirmacion, this.httpOptions).pipe(
		tap((item) => console.log('confirmando el proveedor')),catchError(this.handleError<any>('error en confirmacion '))
	);
   }
   
   createSucursal(sucursal: SucursalesNit) {
    this.createSucursal2(sucursal).subscribe(
      (response) => {
        this.operations.next(response.value);
      }
    );
  }
   createSucursal2(sucursal: SucursalesNit) {
	   
    return this.http.post<ResponseRs>(BASE_URL_REST_FILE2 + 'proveedores/sucursales', sucursal);
 
    //return this.http.post<any>(BASE_URL_REST_FILE2 + 'proveedores/sucursales', sucursal, this.httpOptions).pipe(
	//	tap((item) => console.log('grabada la sucursales')),catchError(this.handleError<any>('error en grabar '))
	//);
   }
   
   fetchDataByWsProveedor (pnit: string): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));		
		
		var localURL=BASE_URL_REST_FILE2 + 'proveedores/aprobar?nit='+pnit;
		
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
	
    fetchDataByTokenProveedor (id: number): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		var localURL=BASE_URL_REST_FILE2 + 'proveedores/proveedor?id='+id;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
	
	private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			// TODO: send the error to remote logging infrastructure
			//console.error(error); // log to //console instead
			// TODO: better job of transforming error for user consumption
			//console.log('${operation} failed: ${error.message}');
			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}
	
	fetchDataSucursal (pnit: string): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		var localURL=BASE_URL_REST_FILE2 + 'proveedores/sucursales/'+pnit;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
	
		fetchSucursalProveedor (id: number): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		//var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
		var localURL=BASE_URL_REST_FILE2 + 'proveedores/sucursales/id/'+id;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
}
