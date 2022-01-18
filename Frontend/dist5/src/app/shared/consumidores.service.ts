import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL_REST_FILE } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';
import { Consumidor } from './consumidor.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsumidoresService {
  operation = new Subject<Consumidor>();

  constructor(private http: HttpClient) {}

  createConsumidor(consumidor: Consumidor) {
    this.saveData(consumidor).subscribe(
      (response) => {
        this.operation.next(response.value);
      }
    );
  }

  fetchData(docid: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE + 'consumidores?docid=' + docid);
  }

  saveData(consumidor: Consumidor) {
    return this.http.post<ResponseRs>(BASE_URL_REST_FILE + 'consumidores', consumidor);
  }
}
