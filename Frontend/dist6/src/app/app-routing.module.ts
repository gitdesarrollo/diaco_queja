import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
//import { MainConcVirtualComponent }      from './main-conc-virtual/main-conc-virtual.component';
//import { LoginComponent } from './login/login.component';

import { ConsumidorComponent } from './consumidor/consumidor.component';
import { ProveedorComponent } from './proveedor/proveedor.component';
import { QuejaComponent } from './queja/queja.component';

import { RevisionquejaComponent } from './revisionqueja/revisionqueja.component';
import { RegistropcvComponent } from './registropcv/registropcv.component';
import { ConfirmarProveedorComponent } from './confirmar-proveedor/confirnar-proveedor.component';
import { RegistronotprevComponent } from './registronotprev/registronotprev.component';

import { PquejainiComponent} from './pquejaini/pquejaini.component';
import { BuscaprovComponent}  from './buscaprov/buscaprov.component';

import {QuejaprComponent }  from './quejapr/quejapr.component';

const routes: Routes = [
	//{ path: '', redirectTo: '/consumidor', pathMatch: 'full' },
  //{ path: 'mainConcVirt', component: MainConcVirtualComponent },
  //{ path: 'Login', component: LoginComponent },
  //{ path: 'consumidor', component: ConsumidorComponent },
	{ path: 'proveedor', component: ProveedorComponent },
	{ path: 'queja', component: QuejaComponent }
	,{ path: 'revisionqueja', component: RevisionquejaComponent }
	,{ path: 'registropcv', component: RegistropcvComponent }
	,{ path: 'confirmar-proveedor/:clave', component: ConfirmarProveedorComponent }
	,{ path: 'registronp', component: RegistronotprevComponent }
	,{ path: 'consumidor/:dato/:id', component: ConsumidorComponent }
	,{ path: 'consumidor/:dato/:id/:queja', component: ConsumidorComponent }
	,{ path: 'pquejaini/:dato/:id', component: PquejainiComponent }
	,{ path: 'quejapr/:dato', component: QuejaprComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
