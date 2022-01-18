import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../conc-virt-const'

@Injectable({
  providedIn: 'root'
})
export class QuejaService {
	private baseUrl:string=BASE_URL_REST+'tipos-queja';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  constructor(private _http:HttpClient, private datePipe: DatePipe) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
  
  getDataQueja(id){	  
	  var localURL=this.baseUrl+'/'+id;
	  console.log(localURL);
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getDataQuejaP(id){	  
	  var localURL=this.baseUrl+'/'+id+'/P';
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
  
  convertDate(date){
	  return this.datePipe.transform(date,"yyyy-MM-dd");
  }
  
  
}

