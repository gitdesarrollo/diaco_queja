import { BrowserModule } from '@angular/platform-browser';
//import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { MainConcVirtualComponent } from './main-conc-virtual/main-conc-virtual.component';
//import { TableConcVirtualComponent } from './table-conc-virtual/table-conc-virtual.component';
import { HttpClientModule } from '@angular/common/http';
//import {MatDialogModule} from "@angular/material";
import { MatExpansionModule } from '@angular/material/expansion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { DatePipe } from '@angular/common';
import { DataTablesModule } from 'angular-datatables';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressBarModule } from '@angular/material/progress-bar';
//import { DetalleConcVirtComponent } from './detalle-conc-virt/detalle-conc-virt.component';
//import { BitacoraConcVirtComponent } from './bitacora-conc-virt/bitacora-conc-virt.component';
//import { ResolverConcVirtComponent } from './resolver-conc-virt/resolver-conc-virt.component';
//import { FileReadUploadComponent } from './file-read-upload/file-read-upload.component';
//import { LoginComponent } from './login/login.component';
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
//import { WINDOW_PROVIDERS } from "./shared/window.service";

import { SoapService } from './shared/soap.service';


import {
  MatInputModule, MatButtonModule, MatSelectModule, MatIconModule, MatTooltipModule, MatDialogModule, MatPaginatorModule, MatTableModule, MatSortModule, MatPaginatorIntl
  , MatCardModule, MatToolbarModule
} from '@angular/material';
import { RegistropcvComponent } from './registropcv/registropcv.component';
import { RevisionquejaComponent } from './revisionqueja/revisionqueja.component';
import { RegistronotprevComponent } from './registronotprev/registronotprev.component';
import { ConfirmarProveedorComponent } from './confirmar-proveedor/confirnar-proveedor.component';


//import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxSoapModule } from 'ngx-soap';
//import { InwhiteComponent } from './inwhite/inwhite.component';
import { PquejainiComponent } from './pquejaini/pquejaini.component';
//import { PconsumidorComponent } from './pconsumidor/pconsumidor.component';
//import { PproveedorComponent } from './pproveedor/pproveedor.component';
//import { PquejaComponent } from './pqueja/pqueja.component';
import { BuscaprovComponent } from './buscaprov/buscaprov.component';

import { NgxCaptchaModule } from 'ngx-captcha';
// recaptcha
import { RecaptchaModule } from 'ng-recaptcha';
import { QuejaprComponent } from './quejapr/quejapr.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

//import { QuejainiComponent } from './quejaini/quejaini.component';


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
    //MainConcVirtualComponent,
    //TableConcVirtualComponent,
    //DetalleConcVirtComponent,
    //BitacoraConcVirtComponent,
    //ResolverConcVirtComponent,
    //FileReadUploadComponent,
    //LoginComponent,
    ConsumidorComponent,
    ProveedorComponent,
    QuejaComponent,
    RegistropcvComponent,
    ConfirmarProveedorComponent,
    RevisionquejaComponent,
    RegistronotprevComponent,
    //InwhiteComponent,
    PquejainiComponent,
    //PconsumidorComponent,
    //PproveedorComponent,
    //PquejaComponent,
    BuscaprovComponent,
    QuejaprComponent
    //,QuejainiComponent
    //,FlexLayoutModule

  ],
  imports: [
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
    BsDatepickerModule.forRoot()
    , MatTooltipModule
    , MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule
    , MatTableModule
    , MatPaginatorModule
    , MatSortModule
    , NgxSoapModule
    , NgxCaptchaModule
    , MatProgressSpinnerModule
    , RecaptchaModule.forRoot()
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }, DatePipe, SoapService], //WINDOW_PROVIDERS],
  bootstrap: [AppComponent],
  //DetalleConcVirtComponent,BitacoraConcVirtComponent,ResolverConcVirtComponent,
  entryComponents: [BuscaprovComponent, QuejaprComponent]
  //,QuejainiComponent]
})
export class AppModule { }
