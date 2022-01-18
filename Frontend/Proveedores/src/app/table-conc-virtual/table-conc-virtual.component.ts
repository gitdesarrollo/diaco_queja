import { Component, OnInit, Input, ViewChild, ElementRef,OnDestroy, AfterViewInit} from '@angular/core';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { ViewEncapsulation } from '@angular/core';
import { Subject } from 'rxjs';
declare var $: any;
import { trigger, state, style, animate, transition } from '@angular/animations';
import { timer } from 'rxjs';
import { FrmMainConcVirt, FrmQueja, FormResponse } from '../conc-virt-const'
import { DetalleConcVirtComponent } from '../detalle-conc-virt/detalle-conc-virt.component';
import { BitacoraConcVirtComponent } from '../bitacora-conc-virt/bitacora-conc-virt.component';
import { ResolverConcVirtComponent } from '../resolver-conc-virt/resolver-conc-virt.component';
import { InteraccionUsuarioComponent } from '../interaccion-usuario/interaccion-usuario.component';
import { ViewMainQuejaService } from './view-main-queja.service';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-table-conc-virtual',
  templateUrl: './table-conc-virtual.component.html',
  styleUrls: ['./table-conc-virtual.component.css'],
    encapsulation: ViewEncapsulation.None,
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
	])
  ]

})
export class TableConcVirtualComponent implements OnInit {
  @Input() frmmain:FrmMainConcVirt; str_rol;
  flagDBError:boolean;
  listquejas:FrmQueja[];
  rows=[];
  columns=[];
  data = [];
  filteredData = [];  
  selected = [];	
  flagInfoError: boolean;
  flaglimitError:boolean;
  MtDialogRefDetalle: MatDialogRef<DetalleConcVirtComponent>;
  MtDialogRefBit: MatDialogRef<BitacoraConcVirtComponent>;
  MtDialogRefRes: MatDialogRef<ResolverConcVirtComponent>;
  MtDialogRefInt: MatDialogRef<InteraccionUsuarioComponent>;

  constructor(private dialog: MatDialog, private _vmqService:ViewMainQuejaService, private _submitFormService:SubmitFormService, private _seguridadService:SeguridadService) { 
		this.str_rol=this._submitFormService.Get_Conciliacion_Virtual();
	  this.columns = [
		{ prop: 'quejanumero', width: 100 },
		{ prop: 'fecha_queja', width: 100 },
		{ prop: 'descripcion', width: 100 },
		{ prop: 'solicito', width: 100 }
	  ];
		this.flaglimitError=false;
  }

  Token(){
	  let loc_token=this._submitFormService.Get_token();
	  //console.log(loc_token);
	  if(loc_token == '')
		  this._seguridadService.RedireccionarLogin();
	  this._seguridadService.VerifyToken(loc_token).subscribe((retvalue)=>{
			console.log('token check',retvalue);
			if(retvalue["reason"] != 'OK'){
				this._seguridadService.SesionVencida();
			}		
		},(error)=>{
			this._seguridadService.SesionVencida();
		});
	}

  ngOnInit() {
	  this.flagInfoError=false;
	  //aqui debo mandar el id del proveedor, debo sacarlo del usuario logueado, por el momento es 1
	  this.GetQuejaList();
  }

	onSelect({ selected }) {
		console.log('Select Event', selected, this.selected);
	}

	singleSelectCheck = (row:any) => {
		return this.selected.indexOf(row) === -1;
	}
	
	clickStopper(event) {
		event.stopPropagation();
		return;
    }

	GetQuejaList(){
	  this._vmqService.getData(this.frmmain).subscribe((retvalue)=>{
			let tempstr=retvalue['value'];
			if(tempstr != null)	{
				this.listquejas=JSON.parse('['+retvalue['value'].slice(0, -1) +']');
				if(this.listquejas.length>=200){
					this.flaglimitError=true;
					this.SetSecTimerLimitError();
				}
			}else{
				this.listquejas=[];
			}			
			this.rows=this.listquejas;
			this.data = this.rows;
			this.filteredData = this.rows;
			console.log(this.listquejas);
			this.flagInfoError=false;
		},(error)=>{
			this.listquejas=[];
			this.rows=this.listquejas;
			this.data = this.rows;
			this.filteredData = this.rows;
			console.log(error);
			this.flagInfoError=true;
		});
  }

// filters results
	filterDatatable(event){
		this.Token();
	  let val = event.target.value.toLowerCase();
	  console.log(this.data);
	  let keys = Object.keys(this.data[0]);
	  let colsAmt = keys.length;
	  let validkeys = [1,3,4,9];
	  console.log(val, colsAmt, keys);
	  this.rows = this.filteredData.filter(function(item){
		for (let i=0; i<colsAmt; i++){
			if(validkeys.indexOf(i) != -1 ){
				  if (item[keys[i]].toString().toLowerCase().indexOf(val) !== -1 || !val){
					return true;
				  }
			}
		}
	  });
	  console.log(this.rows);
	  // whenever the filter changes, always go back to the first page
	  //this.table.offset = 0;
	}
	
	openDetalle(NoQueja, strQueja) {
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = false;
		dialogConfig.height = '1000px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja};
		
    this.MtDialogRefDetalle = this.dialog.open(DetalleConcVirtComponent,dialogConfig);
	this.MtDialogRefDetalle.afterClosed().subscribe(result => {
		this.ModalLogicInteraccion(result); 
	});
  
  }

	openBitacora(NoQueja, strQueja) {
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '1000px';
		dialogConfig.width = '1200px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja};
		
    this.MtDialogRefBit = this.dialog.open(BitacoraConcVirtComponent,dialogConfig);
  
  }

	openResolver(NoQueja, strQueja) {
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, rol: this._submitFormService.Get_Conciliacion_Virtual()};
		
		this.MtDialogRefRes = this.dialog.open(ResolverConcVirtComponent,dialogConfig);
		this.MtDialogRefRes.afterClosed().subscribe(result => {
			console.log(result);
			if(result){
				this.GetQuejaList();
			}
		});
	}

	openInteraccion(NoQueja, strQueja) {
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja};
		
		this.MtDialogRefInt = this.dialog.open(InteraccionUsuarioComponent,dialogConfig);
	}

	ModalLogicInteraccion(FormResponse){
		console.log(FormResponse);
		console.log(typeof FormResponse);
		if(typeof FormResponse !== "undefined"){
			this.openInteraccion(+FormResponse.NoQueja, FormResponse.quejanumero);
		}
	}
	
	SetSecTimerLimitError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flaglimitError=false);
	}
}
