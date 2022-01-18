import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../conc-virt-const'
import { Router } from '@angular/router';
import { SubmitFormService } from './submit-form.service'

@Injectable({
  providedIn: 'root'
})
export class SeguridadService {
	private baseUrl:string=BASE_URL_REST+'users';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private _http:HttpClient, private router: Router, private _submitFormService:SubmitFormService) { }
  
  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }

  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
    
  Login (str_username, str_password): Observable<any> {
		//console.log(this.ObjaJSON_usrpass(str_username, str_password));
		var localURL=this.baseUrl+'/login';
		return this._http.post<any>(localURL, this.ObjaJSON_usrpass(str_username, str_password), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }

  Logout (token): Observable<any> {
		this._submitFormService.Limpiar();
		//console.log(this.ObjaJSON_token(token));
		var localURL=this.baseUrl+'/logout';
		return this._http.post<any>(localURL, this.ObjaJSON_token(token), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
  
  VerifyToken (token): Observable<any> {
		//console.log(this.ObjaJSON_token(token));
		var localURL=this.baseUrl+'/token';
		return this._http.post<any>(localURL, this.ObjaJSON_token(token), this.httpOptions).pipe(
			tap((item) => console.log('Added item')),
			catchError(this.handleError<any>('Add Data'))
		);
  }
   
  EmailPassword (email): Observable<any> {
		var localURL=this.baseUrl+'/emailr/'+email;
		console.log(localURL);
		return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));  
  }
   
  ObjaJSON_usrpass(str_username, str_password){
	  var locarray = { username: this.TrimandCut(str_username,100), password: str_password };
	  return JSON.stringify(locarray);
  }

  ObjaJSON_token(token){
	  var locarray = { token: token };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
  SesionVencida(){
	  alert('Su sesión ha vencido, se va a cargar la página de ingreso.');
	  this._submitFormService.Limpiar();
	  this.router.navigate(['/Login']);
  }
  
  RedireccionarLogin(){
	  this._submitFormService.Limpiar();
	  this.router.navigate(['/Login']);
  }
  
}

