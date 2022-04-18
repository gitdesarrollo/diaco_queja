import { Injectable } from '@angular/core';
import { SubmitFormService } from './shared/submit-form.service';
import { BASE_URL_REST } from './atencion-consumidor-const'
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IniciarchatService {
  private baseUrl:string=BASE_URL_REST+'tipos-iniciarchat';
  private httpOptions = {
    headers: new HttpHeaders({
    'content-type':'application/json; charset=iso-8859-1'
    })
  };

  constructor(    private _submitFormService:SubmitFormService,
    private _http:HttpClient,) { 

  }

  private extractData(res: Response) {
	  let body = res;
	  return body || { };
  }

  getAll(id_usuario){	
	  let localUrl=this.baseUrl+'/'+id_usuario+'/'+this._submitFormService.Get_token();
	//   console.log(localUrl);
	  return this._http.get(localUrl,this.httpOptions).pipe(map(this.extractData));
  }
}
