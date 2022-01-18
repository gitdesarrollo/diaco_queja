import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/Rx';
import {Dpto} from './dpto';
import 'rxjs/add/operator/map';
import { HttpClientModule } from '@angular/common/http'; 
import { HttpModule } from '@angular/http';
/*import { map } from 'rxjs/operators';
import { Observable, of, Subject } from 'rxjs';*/


@Injectable({
  providedIn: 'root'
})
export class DepartamentoService {
  //http://desagestorquejas.diaco.gob.gt
  //private baseUrl:string='http://localhost:8080/diaco-int-web/rs/tipos-departamentos/';
	private baseUrl:string='http://desagestorquejas.diaco.gob.gt/diaco-int-web/rs/tipos-departamentos/';
	private headers= new Headers({'Content.Type':'application/json'});
	private options=new RequestOptions({headers:this.headers});
  constructor(private _http:Http) { }
  
  getUsers(){
	  return this._http.get(this.baseUrl,this.options).map((response:Response)=>response.json()).catch(this.errorHandler);
	  
  }
  
  errorHandler(error:Response){
	return Observable.throw(error||"Server Error");
  }
}
