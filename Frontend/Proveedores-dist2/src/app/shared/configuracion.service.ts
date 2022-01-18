import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { BASE_URL_REST } from '../conc-virt-const'
import { SubmitFormService } from "../shared/submit-form.service";
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionService {
	
	private baseUrl:string=BASE_URL_REST+'tipos-configuracion';
	private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
  constructor(private datePipe: DatePipe, private _http:HttpClient, private _submitFormService:SubmitFormService) { }
  	
	private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }
	
	getQR(){	
		var localURL=this.baseUrl+'/qrpath/';
		console.log(localURL);
		return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
	}
	
	cambiaPW(nuevopassword, claveanterior): Observable<any> {
		var objjson=this.ObjaJSON_cambiaPW(nuevopassword, claveanterior);
		console.log(objjson);
		var localURL=this.baseUrl+'/cambioPW';
		return this._http.post<any>(localURL, objjson, this.httpOptions).pipe(
			map(this.extractData)
		);
	}
	  
  ObjaJSON_cambiaPW(nuevopassword, claveanterior){
	  var locarray = { usuario:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), resp1: nuevopassword, resp2: claveanterior };
	  return JSON.stringify(locarray);
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }

  dateFormat(date){
	  return this.datePipe.transform(date,"dd/MM/yyyy");
  }
  
}

