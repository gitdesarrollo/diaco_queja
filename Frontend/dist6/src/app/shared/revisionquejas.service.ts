import { Injectable } from '@angular/core';
import { BASE_URL_REST_FILE2 } from '../conc-virt-const';
import { ResponseRs } from './response-rs.model';
import { RevisionQueja } from './revisionqueja.model';
import { Subject,Observable, of } from 'rxjs';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { HttpClient, HttpHeaders, HttpErrorResponse ,HttpParams} from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RevisionQuejasService {

  private httpOptions = {
	  headers: new HttpHeaders({
		'content-type':'application/json; charset=iso-8859-1'
	  })
	};
	
  operation = new Subject<RevisionQueja>();
  constructor(private http: HttpClient) {}

  fetchData(noqueja: string) {
    return this.http.get<ResponseRs>(BASE_URL_REST_FILE2 + 'quejas/' + noqueja);
  }
  
}
