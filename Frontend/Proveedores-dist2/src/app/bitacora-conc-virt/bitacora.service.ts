import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../conc-virt-const';
import { SubmitFormService } from '../shared/submit-form.service'

@Injectable({
  providedIn: 'root'
})
export class BitacoraService {
	private baseUrl:string=BASE_URL_REST+'tipos-bitacora';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  constructor(private _http:HttpClient, private datePipe: DatePipe, private _submitFormService:SubmitFormService) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
  
  getData(idqueja){	  
	  var localURL=this.baseUrl+'/'+idqueja+'/4/'+this._submitFormService.Get_token();
	  //return this._http.get(localURL,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  addData (bitacora, consumidor, no_queja): Observable<any> {
		//return this._http.post(this.baseUrl, this.ObjaJSON_add(bitacora, consumidor, no_queja), this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
		return this._http.post<any>(this.baseUrl, this.ObjaJSON_add(bitacora, consumidor, no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  deleteData (id,no_queja): Observable<any> {
		var localURL=this.baseUrl+'/del';
		//return this._http.put(localURL, this.ObjaJSON_del(id,no_queja), this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
		return this._http.put<any>(localURL, this.ObjaJSON_del(id,no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Deleted item')),
			catchError(this.handleError<any>('Delete Data'))
		);
  }
  
  editData (id, bitacora, consumidor,no_queja): Observable<any> {
		//return this._http.put(this.baseUrl, this.ObjaJSON_edit(id, bitacora, consumidor,no_queja), this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
		return this._http.put<any>(this.baseUrl, this.ObjaJSON_edit(id, bitacora, consumidor,no_queja), this.httpOptions).pipe(
			tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead
			// TODO: better job of transforming error for user consumption
			console.log('${operation} failed: ${error.message}');
			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
  }
  
/*  errorHandler(error:Response){
	return Observable.throw(error||"Server Error");
  }*/
    
  convertDate(date){
	  return this.datePipe.transform(date,"yyyy-MM-dd");
  }
  
  ObjaJSON_add(bitacora, consumidor, no_queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(),bitacora: this.TrimandCut(bitacora,999), consumidor: this.TrimandCut(consumidor,999), id_queja: no_queja, id_sede_diaco_operacio: 1, id_departamento_interno:1, id_flujo: 4, token: this._submitFormService.Get_token() };
	  //console.log(bitacora+'-'+consumidor+'-'+no_queja);
	  //console.log(locarray);
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_del(id,no_queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(), id:id, id_sede_diaco_operacio: 1, id_departamento_interno:1, id_queja:no_queja,id_flujo:4, token: this._submitFormService.Get_token() };
	  //console.log(bitacora+'-'+consumidor+'-'+no_queja);
	  //console.log(locarray);
	  return JSON.stringify(locarray);
  }
  
  ObjaJSON_edit(id, bitacora, consumidor,no_queja){
	  var locarray = { usuario:this._submitFormService.Get_userid(),bitacora: bitacora, consumidor: consumidor, id:id, id_sede_diaco_operacio: 1, id_departamento_interno:1, id_queja:no_queja, id_flujo:4, token: this._submitFormService.Get_token() };
	  //console.log(bitacora+'-'+consumidor+'-'+no_queja);
	  //console.log(locarray);
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
  /*WhiteListChars(input) {

    var NumbersAlphabetAndWhiteSpace = /[A-Za-z áéíóúÁÉÍÓÚÑñ1234567890-_+*]/g;   
    // Retrieving the key from the char code passed in event.which
    // For more info on even.which, look here: http://stackoverflow.com/q/3050984/114029
    var key = String.fromCharCode(event.which);
    
    //alert(event.keyCode);
    
    // For the keyCodes, look here: http://stackoverflow.com/a/3781360/114029
    // keyCode == 8  is backspace
    // keyCode == 37 is left arrow
    // keyCode == 39 is right arrow
    // englishAlphabetAndWhiteSpace.test(key) does the matching, that is, test the key just typed against the regex pattern
    if (event.keyCode == 8 || event.keyCode == 37 || event.keyCode == 39 || englishAlphabetAndWhiteSpace.test(key)) {
        return true;
    }

    // If we got this far, just return false because a disallowed key was typed.
    return false;
	});*/

  
}

