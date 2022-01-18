import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainConcVirtualComponent } from './main-conc-virtual/main-conc-virtual.component';
import { TableConcVirtualComponent } from './table-conc-virtual/table-conc-virtual.component';
import { HttpClientModule }    from '@angular/common/http';
import {MatDialogModule, MatTreeModule, MatIconModule} from "@angular/material";
import {MatExpansionModule} from '@angular/material/expansion';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { DatePipe } from '@angular/common';
import { DataTablesModule } from 'angular-datatables';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import {MatDividerModule} from '@angular/material/divider';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { DetalleConcVirtComponent } from './detalle-conc-virt/detalle-conc-virt.component';
import { BitacoraConcVirtComponent } from './bitacora-conc-virt/bitacora-conc-virt.component';
import { ResolverConcVirtComponent } from './resolver-conc-virt/resolver-conc-virt.component';
import { FileReadUploadComponent } from './file-read-upload/file-read-upload.component';
import { LoginComponent } from './login/login.component';
import { InteraccionUsuarioComponent } from './interaccion-usuario/interaccion-usuario.component';
import { MagicLinkComponent } from './magic-link/magic-link.component';
import { PaginaConstruccionComponent } from './pagina-construccion/pagina-construccion.component';
import { CambiarPasswordComponent } from './cambiar-password/cambiar-password.component';
import { QrComponent } from './qr/qr.component';
import { ProveedorAprobadoComponent } from './proveedor-aprobado/proveedor-aprobado.component';

@NgModule({
  declarations: [
    AppComponent,
    MainConcVirtualComponent,
    TableConcVirtualComponent,
    DetalleConcVirtComponent,
    BitacoraConcVirtComponent,
    ResolverConcVirtComponent,
    FileReadUploadComponent,
    LoginComponent,
    InteraccionUsuarioComponent,
    MagicLinkComponent,
    PaginaConstruccionComponent,
    CambiarPasswordComponent,
    QrComponent,
    ProveedorAprobadoComponent
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
	MatProgressBarModule
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, DatePipe],
  bootstrap: [AppComponent],
  entryComponents: [DetalleConcVirtComponent,BitacoraConcVirtComponent,ResolverConcVirtComponent,InteraccionUsuarioComponent,CambiarPasswordComponent,QrComponent]
})
export class AppModule { }
