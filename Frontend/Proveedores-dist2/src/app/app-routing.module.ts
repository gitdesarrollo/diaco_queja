import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainConcVirtualComponent }      from './main-conc-virtual/main-conc-virtual.component';
import { LoginComponent } from './login/login.component';
import { MagicLinkComponent } from './magic-link/magic-link.component';
import { PaginaConstruccionComponent } from './pagina-construccion/pagina-construccion.component';
import { ProveedorAprobadoComponent } from './proveedor-aprobado/proveedor-aprobado.component';

const routes: Routes = [
	{ path: '', redirectTo: '/Login', pathMatch: 'full' },
  { path: 'mainConcVirt', component: MainConcVirtualComponent },
  { path: 'Login', component: LoginComponent },
    { path: 'Redirect/:id/:param1', component: MagicLinkComponent },
	{ path: 'Redirect/:id', component: MagicLinkComponent },
	{ path: 'BajoConstruccion/:token', component: PaginaConstruccionComponent },
	{ path: 'ProvAprobado/:nit', component: ProveedorAprobadoComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
