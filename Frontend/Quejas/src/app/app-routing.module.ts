import { NgModule }             from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { MainAtencionConsumidorComponent }      from './main-atencion-consumidor/main-atencion-consumidor.component';
import { MainServiciosPublicosComponent }      from './main-servicios-publicos/main-servicios-publicos.component';
import { MainVerificacionVigilanciaComponent }      from './main-verificacion-vigilancia/main-verificacion-vigilancia.component';
import { MainJuridicoComponent } from './main-juridico/main-juridico.component';
import { LoginComponent } from './login/login.component';
import { Authguard } from './shared/auth.guard'

import { ChatComponent } from './chat/chat.component';
import { routerNgProbeToken } from '@angular/router/src/router_module';

const routes: Routes = [
  { path: '', redirectTo: '/Login', pathMatch: 'full' },
  { path: 'mainAtCon', component: MainAtencionConsumidorComponent, canActivate: [Authguard]  },
  { path: 'mainServPub', component: MainServiciosPublicosComponent, canActivate: [Authguard]  },
  { path: 'mainVyV', component: MainVerificacionVigilanciaComponent, canActivate: [Authguard]  },
  { path: 'mainJuridico', component: MainJuridicoComponent, canActivate: [Authguard]  },
  { path: 'Login', component: LoginComponent},/*,
  { path: 'VerifConcVirt/:id', component: VerifConcicliacionVirtComponent },
  { path: 'ComPermanente/:id', component: ComunicacionPermanenteComponent },
  { path: 'FinalizarQueja/:id', component: FinalizarQuejaComponent },
  { path: 'BitacoraACComponent/:id', component: FinalizarQuejaComponent },
  { path: 'DepartamentoTest/:id', component: DepartamentotestComponent },
  { path: 'MuestraRegistro/:id/:reg', component: MuestraRegistroComponent },
  { path: 'GuiaRegistros', component: GuiaRegistrosComponent }*/

  { path: 'Chat/:idAudiencia', component: ChatComponent }
];


@NgModule({
	imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
