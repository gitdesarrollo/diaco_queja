import { Component, OnInit, Input, ViewChild, ElementRef,OnDestroy, AfterViewInit, HostBinding} from '@angular/core';
import {LSTFILTRO_VERIFICACIONVIGILANCIA, FrmMainParam, BaseCmbClass} from "../atencion-consumidor-const" 
import { NgModule } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { GuiaRegistrosComponent }		from '../guia-registros/guia-registros.component';
import { ResumenQuejasComponent } from '../resumen-quejas/resumen-quejas.component';
import { ConfirmarAccionComponent } from '../confirmar-accion/confirmar-accion.component';
import { ConfirmarAccionService } from '../confirmar-accion/confirmar-accion.service';
import { ReasignarReactivarComponent } from '../reasignar-reactivar/reasignar-reactivar.component';
import { TablaMainVerificacionComponent } from '../tabla-main-verificacion/tabla-main-verificacion.component';
import { BusquedaComponent } from '../busqueda/busqueda.component';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";
import { MovimientoConfVerificacionComponent } from '../movimiento-conf-verificacion/movimiento-conf-verificacion.component';
import { UsuarioDetalleComponent } from '../usuario-detalle/usuario-detalle.component';
import { ConfiguracionSistemaComponent } from '../configuracion-sistema/configuracion-sistema.component';
import { ConfUsuarioConcPreviaComponent } from '../conf-usuario-conc-previa/conf-usuario-conc-previa.component';
import { UsuarioDetalleConcPreviaComponent } from '../usuario-detalle-conc-previa/usuario-detalle-conc-previa.component';
import { AprobarConcPreviaComponent } from '../aprobar-conc-previa/aprobar-conc-previa.component';
import { AsignarColaComponent } from '../asignar-cola/asignar-cola.component';
import { ParamGeneralesComponent } from '../param-generales/param-generales.component';
import { ConfUsuarioComponent } from '../conf-usuario/conf-usuario.component';
import { CambiarPasswordComponent } from '../cambiar-password/cambiar-password.component';
import { Router } from '@angular/router';
import { CatDepartamentoComponent } from '../cat-departamento/cat-departamento.component';
import { CatDepartamentoInternoComponent } from '../cat-departamento-interno/cat-departamento-interno.component';
import {CatMunicipioComponent } from '../cat-municipio/cat-municipio.component'
import {CatSedesComponent } from '../cat-sedes/cat-sedes.component';
import { CatPaisesComponent } from '../cat-paises/cat-paises.component';
import {CatDiaInhabilComponent} from '../cat-dia-inhabil/cat-dia-inhabil.component';
import { CatMotivoQuejaComponent } from '../cat-motivo-queja/cat-motivo-queja.component';
import {CatProveedorComponent } from '../cat-proveedor/cat-proveedor.component';


@Component({
  selector: 'app-main-verificacion-vigilancia',
  templateUrl: './main-verificacion-vigilancia.component.html',
  styleUrls: ['./main-verificacion-vigilancia.component.css'],
  animations: [
		trigger('EnterLeave', [
	  state('flyIn', style({ transform: 'translateX(0)' })),
	  transition(':enter', [
		style({ transform: 'translateX(-100%)' }),
		animate('100ms 150ms ease-in')
	  ]),
	  transition(':leave', [
		animate('300ms ease-out', style({ transform: 'translateX(-100%)' }))
	  ])
	])]
})
export class MainVerificacionVigilanciaComponent implements OnInit {
  list_filtro:BaseCmbClass[];
  MtDialogGuiaRegistro: MatDialogRef<GuiaRegistrosComponent>;
  MtDialogResQuejas: MatDialogRef<ResumenQuejasComponent>;
  MtDialogConAcc: MatDialogRef<ConfirmarAccionComponent>;
  MtDialogBusqueda: MatDialogRef<BusquedaComponent>;
  MtDialogConfMov: MatDialogRef<MovimientoConfVerificacionComponent>;
  MtDialogReasignar: MatDialogRef<ReasignarReactivarComponent>;
  MtDialogParamGen: MatDialogRef<ParamGeneralesComponent>;
  MtDialogConfUsuario: MatDialogRef<ConfUsuarioComponent>;
  MtDialogUsuarioDetalle: MatDialogRef<UsuarioDetalleComponent>;
  MtDialogConfiguracion: MatDialogRef<ConfiguracionSistemaComponent>;
  MtDialogConfUsuarioPP: MatDialogRef<ConfUsuarioConcPreviaComponent>;
  MtDialogUsuarioDetallePP: MatDialogRef<UsuarioDetalleConcPreviaComponent>;
  MtDialogAsigCola: MatDialogRef<AsignarColaComponent>;
  MtDialogAprobarConcVirt: MatDialogRef<AprobarConcPreviaComponent>;
  MtDialogCambiarPW: MatDialogRef<CambiarPasswordComponent>;
  flagAccionPendiente:boolean;
  flagInfoError:boolean;
  flagAccesoPagLocal:boolean;
  flagisAdminRevisor:boolean;
  flagisAdmin:boolean;
  flagAccesoSP:boolean;
  flagAccesoAtCon:boolean;
  flagAccesoJuridico:boolean;
  flagParamGen:boolean;
  flagAdmUsuario:boolean;
  flagConfSist:boolean;
  flagAdmColas:boolean;
  flagAprobPP:boolean;
  flagAccesoConf:boolean;
  flagCatalogos:boolean;
  str_usuario;
  frmMain: FormGroup;
  selectedFiltroCtrl: FormControl;
  SelectedFDesdeCtrl: FormControl;
  SelectedFHastaCtrl: FormControl;
  selectedNoquejaCtrl: FormControl;
  MtDialogCatDepartamento: MatDialogRef<CatDepartamentoComponent>;
  MtDialogCatPaises: MatDialogRef<CatPaisesComponent>;
  MtDialogCatDepartamentoInterno: MatDialogRef<CatDepartamentoInternoComponent>;
  MtDialogCatMotivoQueja : MatDialogRef<CatMotivoQuejaComponent>;
  MtdialogCatMunicipio : MatDialogRef<CatMunicipioComponent>;
  MtDialogCatSedes : MatDialogRef<CatSedesComponent>;
  MtDialogCatDiaInhabil : MatDialogRef<CatDiaInhabilComponent>;
  MtDialogCatProveedor : MatDialogRef<CatProveedorComponent>;

	@ViewChild(TablaMainVerificacionComponent)
     private myChild: TablaMainVerificacionComponent;

   RefreshTable(){	
		this.GetList();
		this.myChild.LoadServiceInfo();
   }
   
  constructor( private dialog: MatDialog, private _confirmarAccionService: ConfirmarAccionService, private _submitFormService:SubmitFormService, private _seguridadService:SeguridadService, private router: Router) { 
		this.str_usuario=this._submitFormService.Get_username();
		this.flagAccionPendiente=false;
		this.flagInfoError=false;
		this.flagAccesoPagLocal=false;
		this.flagisAdminRevisor=false;
		this.flagisAdmin=false;
		this.flagAccesoSP=false;
		this.flagAccesoAtCon=false;
		this.flagAccesoJuridico=false;
		this.flagParamGen=false;
		this.flagAdmUsuario=false;
		this.flagConfSist=false;
		this.flagAdmColas=false;
		this.flagAprobPP=false;
		this.flagAccesoConf=false;
		this.flagCatalogos=false;
  }

  ngOnInit() {
		this.selectedFiltroCtrl = new FormControl('');
		this.SelectedFDesdeCtrl = new FormControl('');
		this.SelectedFHastaCtrl = new FormControl('');
		this.selectedNoquejaCtrl = new FormControl('');
		this.frmMain = new FormGroup({
			selectedFiltro: this.selectedFiltroCtrl,
			SelectedFDesde: this.SelectedFDesdeCtrl,
			SelectedFHasta: this.SelectedFHastaCtrl,
			selectedNoqueja: this.selectedNoquejaCtrl
		});
	    this.Acceso();
		this.Token();
		this.list_filtro=LSTFILTRO_VERIFICACIONVIGILANCIA;
		//this.tablechildflag=true;
		this.selectedFiltroCtrl.setValue(999);
		this.GetList();
  }

	Nothing(){
			//nothing
	}

	GotoAtencionConsumidor(){
		this.router.navigate(['/mainAtCon']);
	}

	GoToServiciosPublicos(){
		this.router.navigate(['/mainServPub']);
	}

	GoToJuridico(){
		console.log('Juridico');
		this.router.navigate(['/mainJuridico']);
	}	

	Acceso(){
		if(this._submitFormService.Get_Verificacion_Vigilancia()!=0)
			this.flagAccesoPagLocal=true;
		
		if(this._submitFormService.Get_Verificacion_Vigilancia()>1)
			this.flagisAdminRevisor=true;
		
		if(this._submitFormService.Get_Verificacion_Vigilancia()>2)
			this.flagisAdmin=true;
		
		if(this._submitFormService.Get_Servicios_Publicos()!=0)
			this.flagAccesoSP=true;
		
		if(this._submitFormService.Get_Atencion_Consumidor()!=0)
			this.flagAccesoAtCon=true;
		
		if(this._submitFormService.Get_Juridico()!=0)
			this.flagAccesoJuridico=true;
		
		if(this._submitFormService.Get_Conf_ParametrosGenerales()!=0)
			this.flagParamGen=true;
		if(this._submitFormService.Get_Conf_ConfUsuarios()!=0)
			this.flagAdmUsuario=true;
		if(this._submitFormService.Get_Conf_Sistema()!=0)
			this.flagConfSist=true;
		if(this._submitFormService.Get_Conf_Colas()!=0)
			this.flagAdmColas=true;
		if(this._submitFormService.Get_Conf_AprobacionPP()!=0)
			this.flagAprobPP=true;

		if(this._submitFormService.Get_Conf_Catalogos()!=0)
			this.flagCatalogos=true;

		if(this.flagParamGen || this.flagAdmUsuario || this.flagConfSist || this.flagAdmColas || this.flagAprobPP){
			this.flagAccesoConf=true;
		}
	}

	Token(){
	  let loc_token=this._submitFormService.Get_token();
	  //console.log(loc_token);
	  if(loc_token == '*'){
		  this._seguridadService.RedireccionarLogin();
	  }else{
		  this._seguridadService.VerifyToken(loc_token).subscribe((retvalue)=>{
				console.log('token check',retvalue);
				if(retvalue["reason"] != 'OK'){
					this._seguridadService.SesionVencida();
				}		
			},(error)=>{
				this._seguridadService.SesionVencida();
			});
	   }
	}
	
	CerrarSesion(){
		let loc_token=this._submitFormService.Get_token();
		console.log(loc_token);
		if(loc_token == '')
			this._seguridadService.RedireccionarLogin();
		this._seguridadService.Logout(loc_token).subscribe((retvalue)=>{
			console.log('cerrar sesion',retvalue);
			this._seguridadService.RedireccionarLogin();		
		},(error)=>{
			this._seguridadService.RedireccionarLogin();
		});
	}

	GetList(){
	  this._confirmarAccionService.getAll(this._submitFormService.Get_userid()).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.flagAccionPendiente=true;
				}else{
					this.flagAccionPendiente=false;
				}
				console.log(this.flagAccionPendiente);
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

  OpenAccionPendiente(){
	  return true;
  }

	openCambiarPW(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '700px';
		dialogConfig.width = '700px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid()};
		
		this.MtDialogCambiarPW = this.dialog.open(CambiarPasswordComponent,dialogConfig);	
	}

	openGuiaRegistro() {
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {};
		
		this.MtDialogGuiaRegistro = this.dialog.open(GuiaRegistrosComponent,dialogConfig);
	}

	openResumenQuejas(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Rol: this._submitFormService.Get_Servicios_Publicos()};
		
		this.MtDialogResQuejas = this.dialog.open(ResumenQuejasComponent,dialogConfig);	
	}
	
	openConfirmarAccion(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid()};
		
		this.MtDialogConAcc = this.dialog.open(ConfirmarAccionComponent,dialogConfig);	
		this.MtDialogConAcc.afterClosed().subscribe(result => {
			this.GetList(); 
		});
	}

	openBusqueda(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Servicios_Publicos(), Flujo: 3};
		
		this.MtDialogBusqueda = this.dialog.open(BusquedaComponent,dialogConfig);	
		this.MtDialogBusqueda.afterClosed().subscribe(result => {
			console.log(result);
			this.BusquedaLogic(result);
		});
	}
	
	openConfMovimientos(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Servicios_Publicos()};
		
		this.MtDialogConfMov = this.dialog.open(MovimientoConfVerificacionComponent,dialogConfig);	
		this.MtDialogConfMov.afterClosed().subscribe(result => {
			console.log(result);
			this.ModalLogicMovimiento(result);
		});
	}
	
	openReasignar(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Rol: this._submitFormService.Get_Atencion_Consumidor(), Flujo: 3};
		
		this.MtDialogReasignar = this.dialog.open(ReasignarReactivarComponent,dialogConfig);	
	}
	
	openConfParamGen(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogParamGen = this.dialog.open(ParamGeneralesComponent,dialogConfig);	
	}
	
	openConfUsuario(load){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor(), Load: load};
		
		this.MtDialogConfUsuario = this.dialog.open(ConfUsuarioComponent,dialogConfig);	
		this.MtDialogConfUsuario.afterClosed().subscribe(result => {
			console.log(result);
			this.ModalLogicConfUsuario(result);
		});
	}
	
	openConfUsuarioPP(load){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor(), Load: load};
		
		this.MtDialogConfUsuarioPP = this.dialog.open(ConfUsuarioConcPreviaComponent,dialogConfig);	
		this.MtDialogConfUsuarioPP.afterClosed().subscribe(result => {
			console.log(result);
			this.ModalLogicConfUsuarioPP(result);
		});
	}
	
	openUsuarioDetalle(idusuario){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1200px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor(), IDUsuario: idusuario};
		
		this.MtDialogUsuarioDetalle = this.dialog.open(UsuarioDetalleComponent,dialogConfig);	
		this.MtDialogUsuarioDetalle.afterClosed().subscribe(result => {
			console.log(result);
			this.ModalLogicUsuarioDetalle(result);
		});
	}

	openUsuarioDetallePP(idusuario, proveedor){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1200px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor(), IDUsuario: idusuario, IDProveedor: proveedor};
		
		this.MtDialogUsuarioDetallePP = this.dialog.open(UsuarioDetalleConcPreviaComponent,dialogConfig);	
		this.MtDialogUsuarioDetallePP.afterClosed().subscribe(result => {
			console.log(result);
			this.ModalLogicUsuarioDetallePP(result);
		});
	}

	openConfiguracion(idusuario){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1200px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogConfiguracion = this.dialog.open(ConfiguracionSistemaComponent,dialogConfig);	
	}
	
	openAsignaCola(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1200px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogAsigCola = this.dialog.open(AsignarColaComponent,dialogConfig);	
	}
	
	openAprobacionConcVirt(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogAprobarConcVirt = this.dialog.open(AprobarConcPreviaComponent,dialogConfig);	
	}
	
	openCatalogos(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatDepartamento= this.dialog.open(CatDepartamentoComponent,dialogConfig);	
	}


	openCatProveedor() {
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatProveedor= this.dialog.open(CatProveedorComponent,dialogConfig);	

	}

	

	openCatDiaInhabil(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatDiaInhabil= this.dialog.open(CatDiaInhabilComponent,dialogConfig);	
	}


	openCatSedes(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatSedes= this.dialog.open(CatSedesComponent,dialogConfig);	
	}
	openCatMunicipio(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtdialogCatMunicipio= this.dialog.open(CatMunicipioComponent,dialogConfig);	
	}
     openMotivoQueja(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatMotivoQueja= this.dialog.open(CatMotivoQuejaComponent,dialogConfig);	
	}

	openDepartamentoInterno(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatDepartamentoInterno= this.dialog.open(CatDepartamentoInternoComponent,dialogConfig);	
	}

	openCatPaises(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Rol: this._submitFormService.Get_Atencion_Consumidor()};
		
		this.MtDialogCatPaises= this.dialog.open(CatPaisesComponent,dialogConfig);	
	}
	
	BusquedaLogic(resp){
		if(resp.pagina != 0){
			this.selectedNoquejaCtrl.setValue(resp['quejanumero']);
			this.RefreshTable();
		}
	}

	ModalLogicMovimiento(FormResponse){
		if(FormResponse.changes){
			this.RefreshTable();
		}
	}

	ModalLogicConfUsuario(FormResponse){
		console.log(FormResponse);
		if(FormResponse.changes){
			this.openUsuarioDetalle(FormResponse.quejanumero);
		}
	}

	ModalLogicUsuarioDetalle(FormResponse){
		if(FormResponse.changes){
			this.openConfUsuario(1);
		}
	}

	ModalLogicConfUsuarioPP(FormResponse){
		console.log(FormResponse);
		if(FormResponse.changes){
			this.openUsuarioDetallePP(FormResponse.quejanumero, FormResponse.NoQueja);
		}
	}

	ModalLogicUsuarioDetallePP(FormResponse){
		if(FormResponse.changes){
			this.openConfUsuarioPP(1);
		}
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}

}
