import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainAtencionConsumidorComponent }      from './main-atencion-consumidor/main-atencion-consumidor.component';
import { MainServiciosPublicosComponent }      from './main-servicios-publicos/main-servicios-publicos.component';
import { MainVerificacionVigilanciaComponent }      from './main-verificacion-vigilancia/main-verificacion-vigilancia.component';
import { MainJuridicoComponent } from './main-juridico/main-juridico.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/Login', pathMatch: 'full' },
  { path: 'mainAtCon', component: MainAtencionConsumidorComponent },
  { path: 'mainServPub', component: MainServiciosPublicosComponent },
  { path: 'mainVyV', component: MainVerificacionVigilanciaComponent },
  { path: 'mainJuridico', component: MainJuridicoComponent },
  { path: 'Login', component: LoginComponent }/*,
  { path: 'VerifConcVirt/:id', component: VerifConcicliacionVirtComponent },
  { path: 'ComPermanente/:id', component: ComunicacionPermanenteComponent },
  { path: 'FinalizarQueja/:id', component: FinalizarQuejaComponent },
  { path: 'BitacoraACComponent/:id', component: FinalizarQuejaComponent },
  { path: 'DepartamentoTest/:id', component: DepartamentotestComponent },
  { path: 'MuestraRegistro/:id/:reg', component: MuestraRegistroComponent },
  { path: 'GuiaRegistros', component: GuiaRegistrosComponent }*/
];

@NgModule({
	imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
