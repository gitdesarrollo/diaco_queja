import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE2 } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';

@Injectable({
  providedIn: 'root'
})
export class MunicipiosService {
  constructor(private http: HttpClient) {}

  fetchSedes(codigoMunicipio: number) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'municipios/' + codigoMunicipio + '/sedes');
  }
}
