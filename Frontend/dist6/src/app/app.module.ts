import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MatExpansionModule } from '@angular/material/expansion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { DatePipe } from '@angular/common';
import { DataTablesModule } from 'angular-datatables';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { ConsumidorComponent } from './consumidor/consumidor.component';
import { ProveedorComponent } from './proveedor/proveedor.component';
import { QuejaComponent } from './queja/queja.component';
import { FileUploadModule } from 'ng2-file-upload';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { defineLocale, LocaleData } from 'ngx-bootstrap/chronos';
import {
  arLocale, bgLocale, csLocale, daLocale, deLocale, enGbLocale, esDoLocale, esLocale, esUsLocale, frLocale, heLocale,
  hiLocale, fiLocale, glLocale, huLocale, idLocale, itLocale, jaLocale, koLocale, ltLocale, mnLocale, nbLocale,
  nlBeLocale, nlLocale, plLocale, ptBrLocale, ruLocale, roLocale, skLocale, slLocale, svLocale, thLocale, trLocale,
  zhCnLocale
} from 'ngx-bootstrap/locale';

import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';

import { SoapService } from './shared/soap.service';


import {
  MatInputModule, MatButtonModule, MatSelectModule, MatIconModule, MatTooltipModule, MatDialogModule, MatPaginatorModule, MatTableModule, MatSortModule, MatPaginatorIntl,MatDatepickerModule, MatNativeDateModule  
  , MatCardModule, MatToolbarModule
} from '@angular/material';
import { RegistropcvComponent } from './registropcv/registropcv.component';
import { RevisionquejaComponent } from './revisionqueja/revisionqueja.component';
import { RegistronotprevComponent } from './registronotprev/registronotprev.component';
import { ConfirmarProveedorComponent } from './confirmar-proveedor/confirnar-proveedor.component';


import { NgxSoapModule } from 'ngx-soap';
import { PquejainiComponent } from './pquejaini/pquejaini.component';
import { BuscaprovComponent } from './buscaprov/buscaprov.component';

import { NgxCaptchaModule } from 'ngx-captcha';
import { RecaptchaModule } from 'ng-recaptcha';
import { QuejaprComponent } from './quejapr/quejapr.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';

const locales = [
  arLocale, bgLocale, csLocale, daLocale, deLocale, enGbLocale, esDoLocale, esLocale, esUsLocale, frLocale, heLocale,
  hiLocale, fiLocale, glLocale, huLocale, idLocale, itLocale, jaLocale, koLocale, ltLocale, mnLocale, nbLocale,
  nlBeLocale, nlLocale, plLocale, ptBrLocale, ruLocale, roLocale, skLocale, slLocale, svLocale, thLocale, trLocale,
  zhCnLocale
];

locales.forEach((locale: LocaleData) => {
  if (!locale.abbr) {
    return;
  }

  defineLocale(locale.abbr, locale);
});

@NgModule({
  declarations: [
    AppComponent,
    ConsumidorComponent,
    ProveedorComponent,
    QuejaComponent,
    RegistropcvComponent,
    ConfirmarProveedorComponent,
    RevisionquejaComponent,
    RegistronotprevComponent,
    PquejainiComponent,
    BuscaprovComponent,
    QuejaprComponent,
    ProviderRegisterComponent
  ],
  imports: [
    MatDatepickerModule, MatNativeDateModule ,MatCardModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatExpansionModule,
    BrowserAnimationsModule,
    DataTablesModule,
    NgxDatatableModule,
    MatDividerModule,
    MatProgressBarModule,
    FileUploadModule,
    BsDatepickerModule.forRoot(),
    MatTooltipModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    NgxSoapModule,
    NgxCaptchaModule,
    MatProgressSpinnerModule,
    RecaptchaModule.forRoot()
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }, DatePipe, SoapService],
  bootstrap: [AppComponent],
  entryComponents: [BuscaprovComponent, QuejaprComponent]
})
export class AppModule { }
