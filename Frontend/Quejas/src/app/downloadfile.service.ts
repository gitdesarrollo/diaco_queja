import { Injectable } from '@angular/core';
import { Http, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import { HttpClientModule } from '@angular/common/http'; 

@Injectable({
  providedIn: 'root'
})
export class DownloadfileService {

	
  constructor(private _http:Http) { }
  
  downloadPDF(url): any {
    const options = { responseType: ResponseContentType.Blob  };
    return this._http.get(url, options).map(
    (res) => {
        return new Blob([res.blob()], { type: 'application/pdf' });
    });
  }

}
