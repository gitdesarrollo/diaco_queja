import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';

@Injectable({
  providedIn: 'root'
})
export class MotivosQuejaService {
  constructor(private http: HttpClient) {}

  fetchData() {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'motivos-queja');
  }
}
