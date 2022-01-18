import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpRequest,HttpEvent,HttpEventType,HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST_FILE } from '../conc-virt-const'
import { SubmitFormService } from '../shared/submit-form.service'

@Injectable({
  providedIn: 'root'
})
export class FileManagerService {
	private baseUrl:string=BASE_URL_REST_FILE+'tipos-imgqueja';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	//'content-type':'multipart/form-data' 
	//'Content-Type':'application/octet-stream'
    //observe: 'response'
	private filectrl;
	
  constructor(private _http:HttpClient, private datePipe: DatePipe, private _submitFormService:SubmitFormService) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
  
  getObligatorios(idqueja){
	  var localURL=this.baseUrl+'/obligatorios/'+idqueja+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getImageList(idqueja,idflujo){
	  var localURL=this.baseUrl+'/'+idqueja+'/'+idflujo+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));  
  }
  
  getImage(id_tipo_imagen){
	  var localURL=this.baseUrl+'/image/'+id_tipo_imagen+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL, {responseType: 'blob'}
	  //,this.httpOptionsFile
	  );  
  
  }

  getZip(idqueja, idflujo){
	  var localURL=this.baseUrl+'/zip/'+idqueja+'/'+idflujo+'/'+this._submitFormService.Get_token();
	  console.log(localURL);
	  return this._http.get(localURL, {responseType: 'blob'}
	  //,this.httpOptionsFile
	  );  
  
  }
  
  /*getDataQueja(id){	  
	  var localURL=this.baseUrl+'/'+id;
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataQuejaP(id){	  
	  var localURL=this.baseUrl+'/'+id+'/P';
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }*/
  
  postNewImage(filecontrol, idqueja, id_tipo_imagen, id_categoria_imagen){
		var localURL=this.baseUrl+'/privado';
		console.log(localURL);
		let formDataloc = new FormData();
        formDataloc.append('attachment', filecontrol );
		formDataloc.append('id_queja', idqueja );
		formDataloc.append('id_tipo_imagen', id_tipo_imagen );
		formDataloc.append('id_categoria_imagen', id_categoria_imagen );
		formDataloc.append('id_flujo', '4' );
		formDataloc.append('token', this._submitFormService.Get_token() );
        /*return this._http.post<any>(this.baseUrl, formDataloc , {reportProgress: true} ).pipe(
			tap((item) => console.log('Added Image')),
			catchError(this.handleError<any>('Add Image'))
		);*/
		const req =  new HttpRequest<any>('POST', localURL, formDataloc, {
			reportProgress: true
		});
		return this._http.request( req );
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
  }

  deleteImage(id){
		var localURL=this.baseUrl+'/'+id+'/'+this._submitFormService.Get_token();
		console.log(localURL);
        return this._http.delete<any>(localURL, this.httpOptions).pipe(
			tap((item) => console.log('Deleted Image')),
			catchError(this.handleError<any>('Delete Image'))
		);
  }

  
  handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
  
  convertDate(date){
	  return this.datePipe.transform(date,"yyyy-MM-dd");
  }
  
    FileDownLoadChoose(data,id_tipo_imagen){
	  if(id_tipo_imagen == 1){
		  this.downloadPDFFile(data);
	  }else if(id_tipo_imagen == 2){
		  this.downloadPNGFile(data);
	  }else if(id_tipo_imagen == 3){
		  this.downloadJPGFile(data);
	  }else if(id_tipo_imagen == 4){
		  this.downloadGIFFile(data);
	  }else if(id_tipo_imagen == 99){
		  this.downloadZIPFile(data);
	  }

  }

  downloadZIPFile(data) {
	  const blob = new Blob([data], { type: 'application/zip' });
	  const url= window.URL.createObjectURL(blob);
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
	}

  
  downloadTXTFile(data) {
	  const blob = new Blob([data], { type: 'text/plain' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}

  downloadPDFFile(data) {
	  const blob = new Blob([data], { type: 'application/pdf' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}

  downloadPNGFile(data) {
	  const blob = new Blob([data], { type: 'image/png' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}
	
	downloadJPGFile(data) {
	  const blob = new Blob([data], { type: 'image/jpeg' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}
	
	downloadGIFFile(data) {
	  const blob = new Blob([data], { type: 'image/gif' });
	  const url= window.URL.createObjectURL(blob);
	  var pwa = window.open(url);
	  if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
		  alert('Por favor deshabilite su bloqueador de Pop-ups e intente de nuevo.');
	  }
	}
  
    VerifyFileSize(size){
		if(size < 40000000) return true;
		return false;
	}
  
	/*TranslateTipoImagen(tipo){
	  if(tipo == 'application/pdf'){
		  return 1;
	  }else if(tipo == 'image/png'){
		  return 2;
	  }else if(tipo == 'image/jpeg'){
		  return 3;
	  }else if(tipo == 'image/gif'){
		  return 4;
	  }		
	  return null;
	}*/
	
	TranslateTipoImagen(tipo, nombre){
	  if(tipo == 'application/pdf'){
		  return 1;
	  }else if(tipo == 'image/png'){
		  return 2;
	  }else if(tipo == 'image/jpeg'){
		  return 3;
	  }else if(tipo == 'image/gif'){
		  return 4;
	  }else if(tipo == ''){
			if(nombre.length < 4){
				return null;
			}
			//revisar tipo en base al nombre porque el tipo vienen en blanco
			var last4 = nombre.substr(nombre.length - 4);
			var last3 = nombre.substr(nombre.length - 3);
			if(last3.toLowerCase() == 'pdf'){
				return 1;
			}else if(last3.toLowerCase() == 'png'){
				return 2;
			}else if(last4.toLowerCase() == 'jpeg'){
				return 3;	
			}else if(last3.toLowerCase() == 'jpg'){
				return 3;	
			}else if(last3.toLowerCase() == 'gif'){
				return 4;
			}
	  }		  
	  return null;
	}
  
}

