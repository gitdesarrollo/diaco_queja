import { Injectable } from '@angular/core';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';
import { Subject,Observable, of } from 'rxjs';
import { Quejaini } from './quejaini.model';
import { FileUploader } from 'ng2-file-upload';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse ,HttpParams} from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class QuejasIniService {
  
  private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  operation = new Subject<Quejaini>();

  constructor(private http: HttpClient) {}

  createQueja(queja: Quejaini, uploader: FileUploader) {
    return this.saveData(queja,'').subscribe(
      (retvalue) => {
		  //console.info('-------------------');
		  //console.info(retvalue);
		  //console.info('-------------------');
		if(retvalue){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					//console.log('llego');
				}else{
					//console.log('no llego');
				}
			}else{
				//console.log('Rest service response ERROR.');
			}  
		  
        if (uploader.queue.length > 	0) {
          uploader.setOptions({
            url: BASE_URL_REST_FILE + 'quejasini/upload',
            itemAlias: 'file'
          });
          uploader.onCompleteAll = () => {
            // finalizo la carga de todos los archivos
           // this.operation.next(response.value);
          };
          uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
            // finalizo la carga de un archivo
          };

          uploader.uploadAll();
        } else {
          //this.operation.next(response.value);
        }
      }
	  
    );
	//this.fetchDataSecId(queja.idFlujo);
  }

  saveData(queja: Quejaini, id: string) {
    return this.http.post<any>(BASE_URL_REST_FILE + 'quejasini'+id, queja, this.httpOptions).pipe(
		tap((item) => console.log('grabada la queja')),catchError(this.handleError<any>('error en grabar '))
	);
  }
  

  fetchDataSecId(id: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'quejasini/secuencia/'+id);
  }
  
  fetchDataSecuencia() {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'quejasini/secuencia');
  }

  fetchDataByToken(id: string,dato: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'quejasini/quejacon/'+dato+'/'+id);
  }

  fetchDataFact(proveedor: string) {
		return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'quejasini?proveedor='+ proveedor);
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
  
}
