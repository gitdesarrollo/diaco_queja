import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './usuario';
import { Observable } from 'rxjs';
import { Proveedor } from '../shared/proveedor.model';
import { BASE_URL_REST_FILE } from '../conc-virt-const';

@Injectable({
  providedIn: 'root'
})
export class ApiUsuariosService {

  constructor(private http:HttpClient) { }

   public getUsuarios():Observable<Usuario[]> {
    return  this.http.get<Usuario[]>('http://apiuser.azurewebsites.net/api/usuarios');
  }
  
   public fetchDataProv(nit: string, nombre: string):Observable<Proveedor[]> {
	   
    return this.http.get<Proveedor[]>(BASE_URL_REST_FILE + 'proveedores/busca?nit=' + nit + "&nombre=" +nombre);
  }



}
