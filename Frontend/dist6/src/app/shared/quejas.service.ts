import {Injectable} from '@angular/core';
import {BASE_URL_REST_FILE2, BASE_URL_REST_EXTER, BASE_URL_DYNAMIC_ASSIGNMENT} from '../conc-virt-const';
import {ResponseRs} from './response-rs.model';
import {Consumidor} from './consumidor.model';
import {Proveedor} from './proveedor.model';
import {Queja} from './queja.model';
import {FileUploader} from 'ng2-file-upload';
import {Subject, Observable, of} from 'rxjs';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {HttpClient, HttpHeaders, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {map, catchError, tap} from 'rxjs/operators';
import {SucursalesNit} from '../shared/sucursales-nit.model';


@Injectable({
    providedIn: 'root'
})
export class QuejasService {
    consumidor: Consumidor;
    proveedor: Proveedor;
    sucursal: SucursalesNit;
    idsucursal: number;
    public quejat: Queja;
    vanio: number;
    vsecanio: number;
    vidqueja: number;
    vdato: string;  //indica si es interno, externo...
    vid: string;     // almacena token
    vqueja: string; //numero de queja enviado en consulta interna.
    vexisteConsumidor: String = '0';
    vexisteProveedor: String = '0';
    vexisteQueja: String = '0';


    private httpOptions = {
        headers: new HttpHeaders({
            'content-type': 'application/json; charset=iso-8859-1'
        })
    };

    operation = new Subject<Queja>();

    constructor(private http: HttpClient) {
    }

    initQuejat(quejap: Queja) {
        this.quejat = new Queja();
        //console.info("servicio ",quejap['idQueja']);
        //consumidor.idConsumidor = response.value.idConsumidor;
        //proveedor.idProveedor = response.value.idProveedor;
        this.quejat.idQueja = quejap.idQueja;
        this.quejat.idConsumidor = quejap.idConsumidor;
        this.quejat.idProveedor = quejap.idProveedor;
        this.quejat.idEstadoQueja = quejap.idEstadoQueja;
        //this.quejat.idSubActividadEconomica: number;
        this.quejat.facturaNumero = quejap.facturaNumero;
        this.quejat.fechaFactura = quejap.fechaFactura;
        this.quejat.detalleQueja = quejap.detalleQueja;
        this.quejat.solicitaQue = quejap.solicitaQue;
        //this.quejat.procedeQueja: string;
        //this.quejat.razonNoProcede: string;
        //this.quejat.datosVerificados: string;
        this.quejat.fechaQueja = quejap.fechaQueja;
        //this.quejat.motivo: string;
        this.quejat.idDepartamento = quejap.idDepartamento;
        this.quejat.idMunicipio = quejap.idMunicipio;
        this.quejat.usuarioAsignado = quejap.usuarioAsignado;
        this.quejat.idDiacoSede = quejap.idDiacoSede;
        this.quejat.anio = quejap.anio;
        this.quejat.noQueja = quejap.noQueja;
        this.quejat.fechaFactura_ = quejap.fechaFactura_;
        //datos no modificables
        this.quejat.idEstadoQueja = quejap.idEstadoQueja;
        this.quejat.idSubActividadEconomica = quejap.idSubActividadEconomica;
        this.quejat.procedeQueja = quejap.procedeQueja;
        this.quejat.razonNoProcede = quejap.razonNoProcede;
        this.quejat.datosVerificados = quejap.datosVerificados;
        this.quejat.fechaQueja = quejap.fechaQueja;
        this.quejat.motivo = quejap.motivo;
        this.quejat.idDepartamento = quejap.idDepartamento;
        this.quejat.idMunicipio = quejap.idMunicipio;
        this.quejat.usuarioAsignado = quejap.usuarioAsignado;
        this.quejat.idDiacoSede = quejap.idDiacoSede;
        this.quejat.fuente = quejap.fuente;
        this.quejat.idfuente = quejap.idfuente;
        this.quejat.ubicacion = quejap.ubicacion;
        this.quejat.documentado = quejap.documentado;
        this.quejat.expiradomail = quejap.expiradomail;
        this.quejat.flagalerta = quejap.flagalerta;
        //direccion  de  proveedor
        this.quejat.direccionProveedor = quejap.direccionProveedor;
        this.quejat.idDepartamentoProveedor = quejap.idDepartamentoProveedor;
        this.quejat.idMunicipioProveedor = quejap.idMunicipioProveedor;
        this.quejat.idEstablecimiento = quejap.idEstablecimiento;
        this.quejat.zonaProveedor = quejap.zonaProveedor;
        if (quejap.idQueja != null || quejap.idQueja != undefined) {
            this.vexisteQueja = '1';
        }
    }

    /*
    createQueja(queja: Queja, uploader: FileUploader) {
      this.saveData(queja).subscribe(
        (response) => {
          this.vanio = response.value.anio;
          this.vsecanio = response.value.secuencia;
          this.vidqueja = response.value.idQueja;
          this.vidqueja = response.value.idQueja;
          if (uploader.queue.length > 0) {
            uploader.setOptions({
              url: BASE_URL_REST_FILE2_ + 'quejas/upload',
              itemAlias: 'file'
            });
            uploader.onCompleteAll = () => {
              // finalizo la carga de todos los archivos
              this.operation.next(response.value);
              this.vanio = response.value.anio;
          this.vsecanio = response.value.secuencia;
          this.vidqueja = response.value.idQueja;
            };
            uploader.onCompleteItem = (item, uploadResponse, status, headers) => {
              // finalizo la carga de un archivo
            };

            uploader.uploadAll();
          } else {
            this.operation.next(response.value);
              this.vanio = response.value.anio;
              this.vsecanio = response.value.secuencia;
              this.vidqueja = response.value.idQueja;
          }
        }
      );
    }
  */

    /*
      saveData1(queja: Queja) {
        return this.http.post<ResponseRs>(BASE_URL_REST_FILE2_ + 'quejas', queja);
      }*/

    saveData(queja: Queja) {

        return this.http.post<any>(BASE_URL_REST_FILE2 + 'quejas', queja, this.httpOptions).pipe(
            tap((item) => console.log('asignacion 1')), catchError(this.handleError<any>('error en grabar '))
        );

    }

    dynamicAssignment(queja: Queja) {
        return this.http.get<any>(BASE_URL_DYNAMIC_ASSIGNMENT + queja.idQueja, this.httpOptions).pipe(
            tap((item) => console.log('Asignacion Automatica correcta', queja.idQueja, item)), catchError(this.handleError<any>('no se pudo asignar'))
        );
    }

    // this.http.post<any>(BASE_URL_DYNAMIC_ASSIGNMENT,)
	// saveData(queja: Queja) {
	// 	return this.http.post<any>(BASE_URL_REST_FILE2 + 'quejas', queja, this.httpOptions).pipe(
	// 		tap((item) => console.log('grabada la queja')), catchError(this.handleError<any>('error en grabar '))
	// 	);
	// }
    //

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            // TODO: send the error to remote logging infrastructure
            //console.error(error); // log to //console instead
            // TODO: better job of transforming error for user consumption
            //console.log('${operation} failed: ${error.message}');
            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    /*
      fetchDataByToken(id: string,dato: string) {
      return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'quejasini/quejacon/'+dato+'/'+id);
    }*/

    fetchDataByToken(id: string, dato: string): Observable<any> {
        //console.log(this.ObjaJSON_usrpass(str_username, str_password));
        var localURL = BASE_URL_REST_FILE2 + 'quejasini/quejacon/' + dato + '/' + id;
        return this.http.get<any>(localURL, this.httpOptions).pipe(
            tap((item) => console.log('Added item')),
            catchError(this.handleError<any>('Add Data'))
        );
    }

    fetchDataQuejaById(dato: string, token, idqueja: string): Observable<any> {
        //console.log(this.ObjaJSON_usrpass(str_username, str_password));
        //var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
        var localURL = BASE_URL_REST_FILE2 + 'quejasini/quejacon/' + dato + '/' + token + '?queja=' + idqueja;
        return this.http.get<any>(localURL, this.httpOptions).pipe(
            tap((item) => console.log('Added item')),
            catchError(this.handleError<any>('Add Data'))
        );
    }

    fetchSucursalProveedor(id: number): Observable<any> {
        //console.log(this.ObjaJSON_usrpass(str_username, str_password));
        //var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
        var localURL = BASE_URL_REST_FILE2 + 'proveedores/sucursales/id/' + id;
        return this.http.get<any>(localURL, this.httpOptions).pipe(
            tap((item) => console.log('Added item')),
            catchError(this.handleError<any>('Add Data'))
        );
    }

    getfilesUpload(id: number): Observable<any> {
        //console.log(this.ObjaJSON_usrpass(str_username, str_password));
        //var localURL=BASE_URL_REST_FILE2 + 'quejasini/'+id;
        var localURL = BASE_URL_REST_FILE2 + 'quejasini/archivos/' + id;
        return this.http.get<any>(localURL, this.httpOptions).pipe(
            tap((item) => console.log('Added item')),
            catchError(this.handleError<any>('Add Data'))
        );
    }

    getfile(id, categoria) {
        var localURL = BASE_URL_REST_FILE2 + 'quejasini/download/' + id + '/' + categoria;
        //console.log(localURL);
        return this.http.get(localURL, {responseType: 'blob'});
    }

    //graba datos para imprimir la queja
    add_FormQueja(idqueja, iduser, idtoken): Observable<any> {
        var LocalURL = BASE_URL_REST_EXTER + 'tipos-registros/form_queja';
        return this.http.post<any>(LocalURL, this.ObjaJSON_add(idqueja, iduser, this.vid), this.httpOptions).pipe(
            tap((item) => console.log('Registro Guardado')),
            catchError(this.handleError<any>('Registro Guardado'))
        );
    }

    ObjaJSON_add(idqueja, iduser, idtoken) {
        var locarray = {id_queja: idqueja, creado_por: iduser, token: this.vid};
        return JSON.stringify(locarray);
    }

    //servicio para obtener el pdf de la queja
    getFormularioQuejaRegistro(id, idtoken) {
        //console.log('entro al service de getFormularioQuejaRegistro ');
        var localURL = BASE_URL_REST_EXTER + 'registros-queja/form-queja/' + id + '/' + this.vid;
        console.log(localURL);
        return this.http.get(localURL, {responseType: 'blob'}
            //,this.httpOptionsFile
        );
    }

}
