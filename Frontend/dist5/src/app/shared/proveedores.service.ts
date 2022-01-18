import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';
import { Proveedor } from './proveedor.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {
  operation = new Subject<Proveedor>();

  constructor(private http: HttpClient) {}

  createProveedor(proveedor: Proveedor) {
    this.saveData(proveedor).subscribe(
      (response) => {
        this.operation.next(response.value);
      }
    );
  }

  fetchData(nit: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'proveedores?nit=' + nit);
  }

  fetchDataProv(nit: string, nombre: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'proveedores/busca?nit=' + nit + "&nombre=" +nombre);
  }
  
  saveData(proveedor: Proveedor) {
    return this.http.post<ResponseRs>(BASE_URL_REST_FILE + 'proveedores', proveedor);
  }
}
