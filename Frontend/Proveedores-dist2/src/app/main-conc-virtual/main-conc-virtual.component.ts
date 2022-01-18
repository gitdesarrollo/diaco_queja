import { Component, OnInit, ViewChild, HostBinding } from '@angular/core';
import { LSTFILTRO_ESTADOCONCVIRT, BaseCmbClass, FrmMainConcVirt, FrmDepartamento, FrmMunicipio, FrmProveedor } from '../conc-virt-const';
import { NgModule } from '@angular/core';
import { DepartamentoService } from './departamento.service';
import { MunicipioService } from './municipio.service';
import { ProveedorService } from './proveedor.service';
import { CambiarPasswordComponent } from '../cambiar-password/cambiar-password.component';
import { QrComponent } from '../qr/qr.component';
import { TableConcVirtualComponent } from '../table-conc-virtual/table-conc-virtual.component'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-main-conc-virtual',
  templateUrl: './main-conc-virtual.component.html',
  styleUrls: ['./main-conc-virtual.component.css'],
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
export class MainConcVirtualComponent implements OnInit {
  list_filtro:BaseCmbClass[];
  frmmain:FrmMainConcVirt;
  lstcmb_departamento:FrmDepartamento[];
  lstcmb_municipio:FrmMunicipio[];
  lst_proveedor:FrmProveedor[];
  nombre_proveedor;str_usuario;
  flagInfoError:boolean;
  flagAccesoConVir:boolean;  
  @ViewChild(TableConcVirtualComponent)
   private myChild: TableConcVirtualComponent;
   MtDialogCambiarPW: MatDialogRef<CambiarPasswordComponent>;
   MtDialogQR: MatDialogRef<QrComponent>;
	 
  constructor(private dialog: MatDialog, private _departamentoService:DepartamentoService, private _municipioService:MunicipioService, private _proveedorService:ProveedorService, private _submitFormService:SubmitFormService, private _seguridadService:SeguridadService) {
		this.flagAccesoConVir=false;	  
		this.str_usuario=this._submitFormService.Get_username();
  }

  ngOnInit() {
		this.frmmain = new FrmMainConcVirt();
		this.Token();
		this.Acceso();
		this.frmmain.departamento=0;
		this.frmmain.municipio=0;
		this.frmmain.estado=401;
		this.getDepartamentos();
		this.lstcmb_municipio=[];
		this.list_filtro = LSTFILTRO_ESTADOCONCVIRT;
		this.getProveedor(this._submitFormService.Get_proveedor());
  }
  
  Acceso(){
		if(this._submitFormService.Get_Conciliacion_Virtual()!=0 && this._submitFormService.Get_proveedor() !=0)
			this.flagAccesoConVir=true;
	}

	Token(){
	  let loc_token=this._submitFormService.Get_token();
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
  
  getProveedor(id){
	  if(id!=0){
		  this._proveedorService.getData(id).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					this.lst_proveedor=JSON.parse('['+retvalue['value'].slice(0, -1) +']');
					console.log(this.lst_proveedor);
					this.flagInfoError=false;
					this.LoadNameProveedor();
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}
			},(error)=>{
				this.lst_proveedor=[];
				console.log(error);
				this.flagInfoError=true;
			});
	  }
  }
  
  UpdateMunicipio(){
		if( this.frmmain.departamento == 0 ){
			this.lstcmb_municipio=[];
			this.frmmain.municipio=0;
		}else{
			this.getMunicipios(this.frmmain.departamento);
			this.frmmain.municipio=0;
		}
  }
  
  getDepartamentos(){
	  this._departamentoService.getData().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				this.lstcmb_departamento=JSON.parse('['+retvalue['value'].slice(0, -1) +']');
				console.log(this.lstcmb_departamento);
				this.flagInfoError=false;
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			this.lstcmb_departamento=[];
			console.log(error);
			this.flagInfoError=true;
		});
  }
  
  getMunicipios(id_departamento){
	  this._municipioService.getData(id_departamento).subscribe((retvalue)=>{
			
			if(retvalue["reason"] == 'OK'){
				this.lstcmb_municipio=JSON.parse('['+retvalue['value'].slice(0, -1) +']');
				console.log(this.lstcmb_municipio);
				this.flagInfoError=false;
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			this.lstcmb_municipio=[];
			console.log(error);
			this.flagInfoError=true;
		});
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

	openQR(){
		this.Token();
		const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '400px';
		dialogConfig.width = '400px';
		dialogConfig.data = {Usuario: this._submitFormService.Get_userid(), Nit: this.lst_proveedor[0]['nit']};
		
		this.MtDialogQR = this.dialog.open(QrComponent,dialogConfig);	
	}

    LoadNameProveedor(){
		if(typeof this.lst_proveedor["0"] === 'undefined'){
			this.nombre_proveedor='';
		}else{
			this.nombre_proveedor=this.lst_proveedor["0"].nombre;
		}
	}


	RefreshTable(){	
		this.myChild.GetQuejaList();
	}
	
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	
}
