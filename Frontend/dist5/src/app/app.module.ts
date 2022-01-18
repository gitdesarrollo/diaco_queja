import { CommonModule } from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
//import { MainConcVirtualComponent } from './main-conc-virtual/main-conc-virtual.component';
//import { TableConcVirtualComponent } from './table-conc-virtual/table-conc-virtual.component';
//import { HttpClientModule }    from '@angular/common/http';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
//import {MatDialogModule} from "@angular/material";
import { MatInputModule, MatButtonModule, MatSelectModule, MatIconModule, MatTooltipModule, MatDialogModule, MatPaginatorModule, MatTableModule, MatSortModule, MatPaginatorIntl   } from '@angular/material';
import {MatExpansionModule} from '@angular/material/expansion';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { DatePipe } from '@angular/common';
import { DataTablesModule } from 'angular-datatables';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import {MatDividerModule} from '@angular/material/divider';
import {MatProgressBarModule} from '@angular/material/progress-bar';
//import { DetalleConcVirtComponent } from './detalle-conc-virt/detalle-conc-virt.component';
//import { BitacoraConcVirtComponent } from './bitacora-conc-virt/bitacora-conc-virt.component';
//import { ResolverConcVirtComponent } from './resolver-conc-virt/resolver-conc-virt.component';
//import { FileReadUploadComponent } from './file-read-upload/file-read-upload.component';
//import { LoginComponent } from './login/login.component';
//import { ConsumidorComponent } from './consumidor/consumidor.component';
//import { ProveedorComponent } from './proveedor/proveedor.component';
//import { QuejaComponent } from './queja/queja.component';
import { FileUploadModule } from 'ng2-file-upload';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
//import { MatTooltipModule  } from '@angular/material';
import { QuejainiComponent } from './quejaini/quejaini.component';
import { QuejaextComponent } from './quejaext/quejaext.component';
import { QuejaconComponent } from './quejacon/quejacon.component';
import { MyAlertDialogComponent } from './my-alert-dialog/my-alert-dialog.component';

//import { MyCustomDialogService } from './my-custom-dialog/my-custom-dialog.service';
//import { MyCustomDialogModule } from './my-custom-dialog/my-custom-dialog.module';
//import { MyCustomDialogComponent } from './my-custom-dialog/my-custom-dialog.component';
import { BuscaprovComponent } from './buscaprov/buscaprov.component';
import { PaginatorEspañol } from './buscaprov/paginator-español';
//import { QuejaiuComponent } from './quejaiu/quejaiu.component';
//import { LoadingScreenComponent } from './loading-screen/loading-screen.component';
//import { LoadingScreenInterceptor } from "./loading-screen/loading.interceptor";


/*
import { QuejainiComponentCon } from './quejacon/quejacon.component';
import { QuejainiComponentExt } from './quejaext/quejaext.component';
*/
export class MaterialModule { }

@NgModule({
  declarations: [
    AppComponent,
//    MainConcVirtualComponent,
//    TableConcVirtualComponent,
//    DetalleConcVirtComponent,
//    BitacoraConcVirtComponent,
//    ResolverConcVirtComponent,
//    FileReadUploadComponent
//    LoginComponent,
//    ConsumidorComponent,
//    ProveedorComponent,
//    QuejaComponent,
      QuejainiComponent,
      QuejaextComponent,
      QuejaconComponent,
      MyAlertDialogComponent,  BuscaprovComponent
	//, QuejaiuComponent
	//, LoadingScreenComponent
	//,QuejainiComponentCon
	//,QuejainiComponentExt
	//,MyCustomDialogComponent
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
    BsDatepickerModule.forRoot(),
	 MatTooltipModule
    ,MatInputModule, 
    MatButtonModule,
    MatSelectModule,
    MatIconModule
	//,MyCustomDialogModule
	,MatTableModule
    ,MatPaginatorModule
    ,MatSortModule
	,CommonModule,
  ],
  //exports: [MyCustomDialogComponent],

  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, { provide: MatPaginatorIntl, useClass: PaginatorEspañol}, DatePipe], //{provide: MyCustomDialogService}],
  //,{      provide: HTTP_INTERCEPTORS,      useClass: LoadingScreenInterceptor,      multi: true    }
  bootstrap: [AppComponent],
//  entryComponents: [DetalleConcVirtComponent,BitacoraConcVirtComponent,ResolverConcVirtComponent,MyCustomDialogComponent]
  entryComponents: [QuejainiComponent, QuejaextComponent, QuejaconComponent, MyAlertDialogComponent,  BuscaprovComponent	 ], //, QuejainiComponentCon, QuejainiComponentExt ]
  
})
export class AppModule { }

