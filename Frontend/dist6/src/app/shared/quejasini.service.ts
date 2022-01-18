import { Injectable } from '@angular/core';
import { BASE_URL_REST_FILE2, BASE_URL_REST_EXTER } from '../conc-virt-const';
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
            url: BASE_URL_REST_FILE2 + 'quejasini/upload',
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
	
    return this.http.post<any>(BASE_URL_REST_FILE2 + 'quejasini'+id, queja, this.httpOptions).pipe(
		tap((item) => console.log('grabada la queja prueba')),catchError(this.handleError<any>('error en grabar '))
	);
  }
  

  fetchDataSecId(id: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'quejasini/secuencia/'+id);
  }
  
  fetchDataSecuencia() {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'quejasini/secuencia');
  }
/*
  fetchDataByToken(id: string,dato: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2_ + 'quejasini/quejacon/'+dato+'/'+id);
  }
*/
	fetchDataFact(proveedor: string) {
		return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'quejasini?proveedor='+ proveedor);
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
  
      fetchDataByToken (id: string,dato: string): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		var localURL=BASE_URL_REST_FILE2 + 'quejasini/quejacon/'+dato+'/'+id;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}

    fetchDataQuejaById (dato: string, token, idqueja: string): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		//var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
		var localURL=BASE_URL_REST_FILE2 + 'quejasini/quejacon/'+dato+'/'+token+'?queja='+idqueja;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
	
	fetchQuejaByLinkMagic (dato: string, token, idqueja: string): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		//var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
		var localURL=BASE_URL_REST_FILE2 + 'quejasini/quejacon/'+dato+'/'+token+'?queja='+idqueja;
		return this.http.get<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
	}
	
	//graba datos para imprimir la queja
	add_FormQueja(idqueja, iduser, idtoken): Observable<any> {
		var LocalURL = BASE_URL_REST_EXTER + 'tipos-registros/form_queja';
		return this.http.post<any>(LocalURL, this.ObjaJSON_add(idqueja, iduser, idtoken), this.httpOptions).pipe(
		tap((item) => console.log('Registro Guardado')),
		catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	ObjaJSON_add(idqueja, iduser, idtoken) {
		var locarray = { id_queja: idqueja, creado_por: iduser, token: idtoken };
		return JSON.stringify(locarray);
	}
	
	//servicio para obtener el pdf de la queja
	getFormularioQuejaRegistro(id,idtoken) {
		//console.log('entro al service de getFormularioQuejaRegistro ');
		var localURL = BASE_URL_REST_EXTER + 'registros-queja/form-queja/' + id + '/' + idtoken;
		console.log(localURL);
		return this.http.get(localURL, { responseType: 'blob' }
		//,this.httpOptionsFile
		);
	}
	
	//graba datos para actualizar la queja
	add_FormQuejaUpdate(_idQueja, _resp, _xqueja, _xsolicitud, _xubicacion, _varchivos, _vdatos,_vdato): Observable<any> {
		var LocalURL = BASE_URL_REST_FILE2 + 'quejasini/updatequejacon';
		return this.http.post<any>(LocalURL, this.ObjaJSON_update(_idQueja, _resp, _xqueja, _xsolicitud, _xubicacion, _varchivos, _vdatos, _vdato), this.httpOptions).pipe(
		tap((item) => console.log('Registro Guardado')),
		catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	ObjaJSON_update(_idQueja, _resp, _xqueja, _xsolicitud, _xubicacion, _varchivos, _vdatos, _vdato) {
		var locarray = { idQueja: _idQueja, resp: _resp, xqueja: _xqueja, xsolicitud: _xsolicitud, xubicacion: _xubicacion, varchivos: _varchivos, vdatos: _vdatos, token:_vdato };
		return JSON.stringify(locarray);
	}
}
