import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { FrmMainParam, FrmMainFD } from "../atencion-consumidor-const";
import { BASE_URL_REST } from '../atencion-consumidor-const'
import { BASE_URL_REST_CALENDARIO } from '../atencion-consumidor-const'
import { SubmitFormService } from "../shared/submit-form.service";
import { jsonpCallbackContext } from '@angular/common/http/src/module';

@Injectable({
	providedIn: 'root'
})
export class AudienciaService {
	private baseUrl: string = BASE_URL_REST + 'tipos-audiencia';
	private baseUrlCalendario: string = BASE_URL_REST_CALENDARIO;

	private httpOptions = {
		headers: new HttpHeaders({
			'content-type': 'application/json; charset=iso-8859-1'
		})
	};
	constructor(private _http: HttpClient, private datePipe: DatePipe, private _submitFormService: SubmitFormService) { }

	private extractData(res: Response) {
		let body = res;
		return body || {};
	}

	getData(idqueja, no_audiencia): Observable<any> {
		var LocalURL = this.baseUrl + '/' + idqueja + '/' + no_audiencia + '/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	getCorrelResolF(): Observable<any> {
		var LocalURL = this.baseUrl + '/resol_final/getcorrelativo/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	getallResAudiencia(idqueja): Observable<any> {
		var LocalURL = this.baseUrl + '/res_audiencia/getall/' + idqueja + '/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	getResAudiencia(id): Observable<any> {
		var LocalURL = this.baseUrl + '/res_audiencia/get/' + id + '/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	getallResFinal(idqueja): Observable<any> {
		var LocalURL = this.baseUrl + '/res_final/getall/' + idqueja + '/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	getResFinal(id): Observable<any> {
		var LocalURL = this.baseUrl + '/res_final/get/' + id + '/' + this._submitFormService.Get_token();
		console.log(LocalURL);
		return this._http.get(LocalURL, this.httpOptions).pipe(map(this.extractData));
	}

	addData(frmData): Observable<any> {
		return this._http.post<any>(this.baseUrl, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}


	saveResAudiencia(tipo, fecha_notif, ccitacion, no_queja) {
		var LocalURL = this.baseUrl + '/res_audiencia/add';
		console.log(LocalURL);
		var frmData = this.ObjaJSON_ResAudiencia(tipo, fecha_notif, ccitacion, no_queja);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	updResAudiencia(tipo, fecha_notif, ccitacion, no_queja, id) {
		var LocalURL = this.baseUrl + '/res_audiencia/upd';
		console.log(LocalURL);
		var frmData = this.ObjaJSONUpd_ResAudiencia(tipo, fecha_notif, ccitacion, no_queja, id);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	DelResAudiencia(id, no_queja) {
		var LocalURL = this.baseUrl + '/res_audiencia/del';
		console.log(LocalURL);
		var frmData = this.ObjaJSONDel_ResAudiencia(id, no_queja);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Eliminado')),
			catchError(this.handleError<any>('Registro Eliminado'))
		);
	}

	saveResFinal(tipo, no_queja, motivo, monto) {
		var LocalURL = this.baseUrl + '/res_final/add';
		console.log(LocalURL);
		var frmData = this.ObjaJSON_ResFinal(tipo, no_queja, motivo, monto);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	updResFinal(tipo, no_queja, id) {
		var LocalURL = this.baseUrl + '/res_final/upd';
		console.log(LocalURL);
		var frmData = this.ObjaJSONUpd_ResFinal(tipo, no_queja, id);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Guardado')),
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}

	DelResFinal(id, no_queja) {
		var LocalURL = this.baseUrl + '/res_final/del';
		console.log(LocalURL);
		var frmData = this.ObjaJSONDel_ResFinal(id, no_queja);
		console.log(frmData);
		return this._http.post<any>(LocalURL, frmData, this.httpOptions).pipe(
			tap((item) => console.log('Registro Eliminado')),
			catchError(this.handleError<any>('Registro Eliminado'))
		);
	}


	ProgramarAudiencia(fecha, hora, idqueja, no_audiencia, flujo) {
		var frmData = this.ObjaJSON_ProgAudiencia(fecha, hora, idqueja, no_audiencia, flujo);
		return this.addData(frmData);
	}

	Audiencia(id_resultado, idqueja, no_audiencia, flujo) {
		var frmData = this.ObjaJSON_Audiencia(id_resultado, idqueja, no_audiencia, flujo);
		return this.addData(frmData);
	}

	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead
			// TODO: better job of transforming error for user consumption
			console.log('${operation} failed: ${error.message}');
			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}

	convertDate(date, time) {
		var hour, min;
		if (time.length == 5) {
			hour = time.substring(0, 2);
			min = time.substring(3, 5);
		} else {
			hour = "00";
			min = "00";
		}
		var temp = this.datePipe.transform(date, "yyyy-MM-dd") + 'T' + hour + ":" + min + ":00";
		//'T00:00:00Z';
		return temp;
	}

	ObjaJSON_ProgAudiencia(fecha, hora, no_queja, no_audiencia, flujo) {
		var locarray = { es_primera_seg_audiencia: no_audiencia, fecha_programada: this.dateFormat(fecha), hora_programada: hora, id_queja: no_queja, id_sede_diaco_operacio: 1, id_departamento_interno: 1, usuario: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_flujo: flujo };
		return JSON.stringify(locarray);
	}


	ObjaJSON_Audiencia(id_resultado, no_queja, no_audiencia, flujo) {
		var locarray = { es_primera_seg_audiencia: no_audiencia, id_resultado_audiencia: id_resultado, id_queja: no_queja, id_sede_diaco_operacio: 1, id_departamento_interno: 1, usuario: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token(), id_flujo: flujo };
		return JSON.stringify(locarray);
	}

	ObjaJSON_ResAudiencia(tipo, fecha_notif, ccitacion, no_queja) {
		//var locarray = { tipo: tipo, fecha: this.dateFormat(fecha), hora: hora, id_queja: no_queja,  usuario_modifico:this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		var locarray = { tipo: tipo, id_queja: no_queja, usuario_modifico: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		if (fecha_notif != '') {
			locarray['fechanotificacion'] = this.dateFormat(fecha_notif);
		}
		if (ccitacion != '') {
			locarray['ccitacion'] = ccitacion;
		}

		return JSON.stringify(locarray);
	}

	ObjaJSONUpd_ResAudiencia(tipo, fecha_notif, ccitacion, no_queja, id) {
		var locarray = { id: id, tipo: tipo, id_queja: no_queja, usuario_modifico: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		if (fecha_notif != '') {
			locarray['fechanotificacion'] = this.dateFormat(fecha_notif);
		}
		if (ccitacion != '') {
			locarray['ccitacion'] = ccitacion;
		}

		return JSON.stringify(locarray);
	}

	ObjaJSONDel_ResAudiencia(id, no_queja) {
		var locarray = { id: id, id_queja: no_queja, usuario_modifico: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		return JSON.stringify(locarray);
	}

	ObjaJSON_ResFinal(tipo, no_queja, motivo, monto) {
		var locarray = { valor: tipo, id_queja: no_queja, valor2: motivo, valor3: monto, usuario: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		return JSON.stringify(locarray);
	}

	ObjaJSONUpd_ResFinal(tipo, no_queja, id) {
		var locarray = { valor2: id, valor: tipo, id_queja: no_queja, usuario: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		return JSON.stringify(locarray);
	}

	ObjaJSONDel_ResFinal(id, no_queja) {
		var locarray = { valor2: id, id_queja: no_queja, usuario: this._submitFormService.Get_userid(), token: this._submitFormService.Get_token() };
		return JSON.stringify(locarray);
	}

	TrimandCut(string, maxlen) {
		string = string.trim();
		if (string.length > maxlen)
			string = string.substring(0, maxlen);

		return string;
	}

	dateFormat(date) {
		return this.datePipe.transform(date, "dd/MM/yyyy");
	}

	dateFormatCalendario(date) {
		return this.datePipe.transform(date, "yyyy/MM/dd");
	}

	


	//24.04.2020 alruanoe
	//conversion a json de envio de validacion de calendario
	ObjaJSON_ValidaCalendario(usuarioOperacion, idQueja, fechaInicio, fechaFin) {

		var locarray = {
			usuarioOperacion: usuarioOperacion,
			idQueja: idQueja,
			fechaInicio: this.dateFormatCalendario(fechaInicio),
			fechaFinal: this.dateFormatCalendario(fechaFin)
		};
		console.log("locarray "+JSON.stringify(locarray));

		return JSON.stringify(locarray);
	}

	//se obtiene el calendario de la fecha actual a 60 dias mas
	ValidarCalendario(usuarioOperacion, idQueja, fechaInicio, fechaFin) {

		var frmData = this.ObjaJSON_ValidaCalendario(usuarioOperacion, idQueja, fechaInicio, fechaFin);
		return this.ObtenerCalendario(frmData);


	}

	//se obtiene el calendario
	ObtenerCalendario(frmData): Observable<any> {
		console.log('json enviado '+frmData);
		return this._http.post<any>(this.baseUrlCalendario, frmData, this.httpOptions).pipe(
			tap((item) => {
				console.log('Calendario obtenido ');
				console.log(JSON.stringify(item));

				//.pipe(map(this.extractData));
			})
			,
			catchError(this.handleError<any>('Registro Guardado'))
		);
	}


	//26.04.2020 alruanoe
	//conversion a json de envio de validacion de fecha calendario
	ObjaJSON_ValidaFecha(usuarioOperacion, idQueja, fechaInicio, fechaFin) {

		var locarray = {
			usuarioOperacion: usuarioOperacion,
			idQueja: idQueja,
			fechaInicio: fechaInicio,
			fechaFinal: fechaFin
		};
		console.log("locarray "+JSON.stringify(locarray));

		return JSON.stringify(locarray);
	}

	//se obtiene el calendario de la fecha actual a 60 dias mas
	ValidarFecha(usuarioOperacion, idQueja, fechaInicio, fechaFin) {

		var frmData = this.ObjaJSON_ValidaFecha(usuarioOperacion, idQueja, fechaInicio, fechaFin);
		return this.ObtenerCalendario(frmData);


	}




}

