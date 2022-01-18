import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { FrmMainConcVirt, BASE_URL_REST } from "../conc-virt-const";
import { DatePipe } from '@angular/common';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { SubmitFormService } from '../shared/submit-form.service'

@Injectable({
  providedIn: 'root'
})
export class ViewMainQuejaService {
	private baseUrl:string=BASE_URL_REST+'tipos-viewmainqueja';
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
  
  getData(Data:FrmMainConcVirt ){
	  let varfiltro:number=null;
	  let vardesde:Date=null;
	  let varhasta:Date=null;
	  let vardpto:number=null;
	  let varmuni:number=null;
	  
	  if(typeof Data.estado!='undefined' && Data.estado){
		  varfiltro=Data.estado;
	  }
	  if(typeof Data.SelectedFDesde!='undefined' && Data.SelectedFDesde){
		  vardesde=Data.SelectedFDesde;
	  }
	  if(typeof Data.SelectedFHasta!='undefined' && Data.SelectedFHasta){
		  varhasta=Data.SelectedFHasta;
	  }
	  if(typeof Data.departamento!='undefined' && Data.departamento){
		  if(Data.departamento!=0)
			vardpto=Data.departamento;
	  }
	  if(typeof Data.municipio!='undefined' && Data.municipio){
		  if(Data.municipio!=0)
			varmuni=Data.municipio;
	  }
	  
	  var localURL = this.baseUrl+'/customfilter2';
	  console.log('filtro: '+varfiltro+', desde: '+vardesde+', hasta: '+varhasta+', url: '+localURL);
	  return this._http.put<any>(localURL, this.getStructVMQ(varfiltro,vardesde,varhasta,vardpto,varmuni,Data.id_proveedor), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }
  
  private getStructVMQ(varfiltro,vardesde,varhasta,vardpto,varmuni,proveedor){
	   var locarray;
      if(vardesde!=null && varhasta!=null)
           locarray = { estado:varfiltro, fecha_desde: this.dateFormat(vardesde), fecha_hasta: this.dateFormat(varhasta), id_departamento: vardpto, id_municipio: varmuni, token: this._submitFormService.Get_token(), id_proveedor: this._submitFormService.Get_proveedor() };
      else locarray = { estado:varfiltro, id_departamento: vardpto, id_municipio: varmuni, token: this._submitFormService.Get_token(), id_proveedor: this._submitFormService.Get_proveedor() };
	  
	  return JSON.stringify(locarray);	  
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
  
  dateFormat(date){
	  return this.datePipe.transform(date,"dd/MM/yyyy");
  }
  
  convertDate(date){
	  var temp=this.datePipe.transform(date,"yyyy-MM-dd")+'T00:00:00Z';
	  return temp;
  }
}
