import { Injectable } from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { FrmMainParam, FrmMainFD, BASE_URL_REST } from "../atencion-consumidor-const";
import { DatePipe } from '@angular/common';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { SubmitFormService } from "../shared/submit-form.service";

@Injectable({
  providedIn: 'root'
})
export class MainTableService {
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
  
  dateFormat(date){
	  return this.datePipe.transform(date,"dd/MM/yyyy");
  }
  
  getUsers(Data: FrmMainParam){
	  let varfiltro:number=null;
	  let vardesde:Date=null;
	  let varhasta:Date=null;
	  let varnoqueja:string='';
	  
	  if(typeof Data.selectedFiltro!='undefined' && Data.selectedFiltro){
		  varfiltro=Data.selectedFiltro;
	  }
	  if(typeof Data.SelectedFDesde!='undefined' && Data.SelectedFDesde){
		  vardesde=Data.SelectedFDesde;
	  }
	  if(typeof Data.SelectedFHasta!='undefined' && Data.SelectedFHasta){
		  varhasta=Data.SelectedFHasta;
	  }
	  if(typeof Data.selectedNoqueja!='undefined' && Data.selectedNoqueja){
		  varnoqueja=Data.selectedNoqueja;
	  }
	  
	  var localURL = this.baseUrl+'/customfilter';
	  console.log('filtro: '+varfiltro+', desde: '+vardesde+', hasta: '+varhasta+', url: '+localURL+', queja: '+varnoqueja);
	  return this._http.put<any>(localURL, this.getStructVMQ(varfiltro,vardesde,varhasta,varnoqueja), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }

  getServPub(Data: FrmMainParam){
	  let varfiltro:number=null;
	  let vardesde:Date=null;
	  let varhasta:Date=null;
	  let varnoqueja:string='';
	  
	  if(typeof Data.selectedFiltro!='undefined' && Data.selectedFiltro){
		  varfiltro=Data.selectedFiltro;
	  }
	  if(typeof Data.SelectedFDesde!='undefined' && Data.SelectedFDesde){
		  vardesde=Data.SelectedFDesde;
	  }
	  if(typeof Data.SelectedFHasta!='undefined' && Data.SelectedFHasta){
		  varhasta=Data.SelectedFHasta;
	  }
	  if(typeof Data.selectedNoqueja!='undefined' && Data.selectedNoqueja){
		  varnoqueja=Data.selectedNoqueja;
	  }
	  
	  var localURL = this.baseUrl+'/customfilterSP';
	  console.log('filtro: '+varfiltro+', desde: '+vardesde+', hasta: '+varhasta+', url: '+localURL+', queja: '+varnoqueja);
	  return this._http.put<any>(localURL, this.getStructVMQ(varfiltro,vardesde,varhasta,varnoqueja), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }

	getVyV(Data: FrmMainParam){
	  let varfiltro:number=null;
	  let vardesde:Date=null;
	  let varhasta:Date=null;
	  let varnoqueja:string='';
	  
	  if(typeof Data.selectedFiltro!='undefined' && Data.selectedFiltro){
		  varfiltro=Data.selectedFiltro;
	  }
	  if(typeof Data.SelectedFDesde!='undefined' && Data.SelectedFDesde){
		  vardesde=Data.SelectedFDesde;
	  }
	  if(typeof Data.SelectedFHasta!='undefined' && Data.SelectedFHasta){
		  varhasta=Data.SelectedFHasta;
	  }
	  if(typeof Data.selectedNoqueja!='undefined' && Data.selectedNoqueja){
		  varnoqueja=Data.selectedNoqueja;
	  }
	  
	  var localURL = this.baseUrl+'/customfilterVyV';
	  console.log('filtro: '+varfiltro+', desde: '+vardesde+', hasta: '+varhasta+', url: '+localURL+', queja: '+varnoqueja);
	  return this._http.put<any>(localURL, this.getStructVMQ(varfiltro,vardesde,varhasta,varnoqueja), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }

	getJuridico(Data: FrmMainParam){
	  let varfiltro:number=null;
	  let vardesde:Date=null;
	  let varhasta:Date=null;
	  let varnoqueja:string='';
	  
	  if(typeof Data.selectedFiltro!='undefined' && Data.selectedFiltro){
		  varfiltro=Data.selectedFiltro;
	  }
	  if(typeof Data.SelectedFDesde!='undefined' && Data.SelectedFDesde){
		  vardesde=Data.SelectedFDesde;
	  }
	  if(typeof Data.SelectedFHasta!='undefined' && Data.SelectedFHasta){
		  varhasta=Data.SelectedFHasta;
	  }
	  if(typeof Data.selectedNoqueja!='undefined' && Data.selectedNoqueja){
		  varnoqueja=Data.selectedNoqueja;
	  }
	  
	  var localURL = this.baseUrl+'/customfilterJuridico';
	  console.log('filtro: '+varfiltro+', desde: '+vardesde+', hasta: '+varhasta+', url: '+localURL+', queja: '+varnoqueja);
	  return this._http.put<any>(localURL, this.getStructVMQ(varfiltro,vardesde,varhasta,varnoqueja), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }

  SearchBusqueda(id_flujo, id_consumidor, id_proveedor, noqueja, fdesde, fhasta, estado){
	  let vardesde:Date=null;
	  let varhasta:Date=null;	  
	  if(typeof fdesde!='undefined' && fdesde){
		  vardesde=fdesde;
	  }
	  if(typeof fhasta!='undefined' && fhasta){
		  varhasta=fhasta;
	  }
	  
	  var localURL = this.baseUrl+'/custombusqueda';
	  console.log(this.getStructVB(id_flujo, id_consumidor, id_proveedor, noqueja, vardesde, varhasta, estado));
	  return this._http.put<any>(localURL, this.getStructVB(id_flujo, id_consumidor, id_proveedor, noqueja, vardesde, varhasta, estado), this.httpOptions).pipe(
			//tap((item) => console.log('Edit item')),
			catchError(this.handleError<any>('Edit Data'))
		);
  }
  
  getSimpleQueja(){	  
	  var localURL=this.baseUrl+'/simple'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getSimpleQuejaArchivo(){	  
	  var localURL=this.baseUrl+'/simplearchivo'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getSimpleQuejaJuridico(){	  
	  var localURL=this.baseUrl+'/simplejuridico'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getSimpleQuejaVyV(){	  
	  var localURL=this.baseUrl+'/simplevyv'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getSimplePendAsig(){	  
	  var localURL=this.baseUrl+'/simple_pendasig'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getVyVSimpleQuejaArchivo(){	  
	  var localURL=this.baseUrl+'/VyVsimplearchivo'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getVyVSimpleQuejaJuridico(){	  
	  var localURL=this.baseUrl+'/VyVsimplejuridico'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getVyVSimpleQuejaAtCon(){	  
	  var localURL=this.baseUrl+'/VyVsimpleAtCon'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getVyVSimplePendAsig(){	  
	  var localURL=this.baseUrl+'/VyVsimple_pendasig'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getJurSimpleQuejaArchivo(){	  
	  var localURL=this.baseUrl+'/Jursimplearchivo'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }

  getJurSimpleQuejaVerificacion(){	  
	  var localURL=this.baseUrl+'/Jursimpleverificacion'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getJurSimpleQuejaAtCon(){	  
	  var localURL=this.baseUrl+'/JursimpleAtCon'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getJurSimplePendAsig(){	  
	  var localURL=this.baseUrl+'/Jursimple_pendasig'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  getSimpleQuejaTodo(){	  
	  var localURL=this.baseUrl+'/simpletodo'+'/'+this._submitFormService.Get_token();
	  return this._http.get(localURL,this.httpOptions).pipe(map(this.extractData));
  }
  
  private getStructVMQ(varfiltro,vardesde,varhasta,varnoqueja){
     var locarray;
        if(vardesde!=null && varhasta!=null)
            locarray= { estado:varfiltro, fecha_desde: this.dateFormat(vardesde), fecha_hasta: this.dateFormat(varhasta), usuario: this._submitFormService.Get_userid(), no_queja: varnoqueja, token: this._submitFormService.Get_token() };
        else locarray= { estado:varfiltro, usuario: this._submitFormService.Get_userid(), no_queja: varnoqueja, token: this._submitFormService.Get_token()};
        
        return JSON.stringify(locarray);
  }
  
  private getStructVB(id_flujo, id_consumidor, id_proveedor, noqueja, fdesde, fhasta, estado){
     var locarray;
        if(fdesde!=null && fhasta!=null)
            locarray= { id_proveedor:id_proveedor, id_consumidor: id_consumidor, no_queja: this.TrimandCut(noqueja, 100),  fecha_desde: this.dateFormat(fdesde), fecha_hasta: this.dateFormat(fhasta), id_usuario: this._submitFormService.Get_userid(), id_flujo:id_flujo, token: this._submitFormService.Get_token(), estado: estado };
        else locarray= { id_proveedor:id_proveedor, id_consumidor: id_consumidor, no_queja: this.TrimandCut(noqueja, 100),  id_usuario: this._submitFormService.Get_userid(), id_flujo:id_flujo, token: this._submitFormService.Get_token(), estado: estado};
        
        return JSON.stringify(locarray);
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			console.log('${operation} failed: ${error.message}');
			return of(result as T);
		};
  }
  
  TrimandCut(string, maxlen){
	  string = string.trim();
	  if(string.length > maxlen)
			string = string.substring(0,maxlen);
		
	  return string;
  }
  
  convertDate(date){
	  var temp=this.datePipe.transform(date,"yyyy-MM-dd")+'T00:00:00Z';
	  return temp;
  }
}
