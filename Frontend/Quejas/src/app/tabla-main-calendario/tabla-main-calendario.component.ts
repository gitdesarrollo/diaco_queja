import { Component, OnInit, Input, ViewChild, ElementRef,OnDestroy, AfterViewInit} from '@angular/core';
import { FrmMainParam, FrmMainFD } from "../atencion-consumidor-const";
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { ComunicacionPermanenteComponent } from '../comunicacion-permanente/comunicacion-permanente.component';
import { VerifConcicliacionVirtComponent } from '../verif-concicliacion-virt/verif-concicliacion-virt.component';
//import { FinalizarQuejaComponent }		from '../finalizar-queja/finalizar-queja.component';
import { BitacoraACComponent }		from '../bitacora-ac/bitacora-ac.component';
import { PrimeraAudienciaComponent }		from '../primera-audiencia/primera-audiencia.component';
import { SegundaAudienciaComponent }		from '../segunda-audiencia/segunda-audiencia.component';
import { ConciliacionComponent }		from '../conciliacion/conciliacion.component';
import { IncomparecenciaComponent }		from '../incomparecencia/incomparecencia.component';
import { VisitaCampoComponent }		from '../visita-campo/visita-campo.component';
import { ArchivarQuejaComponent }		from '../archivar-queja/archivar-queja.component';
import { MovimientoExpedienteComponent }		from '../movimiento-expediente/movimiento-expediente.component';
import { VerifDatosComponent }		from '../verif-datos/verif-datos.component';
import { ViewEncapsulation } from '@angular/core';
import { MainTableService } from './main-calendario.service';
import { Subject } from 'rxjs';
//import { DataTableDirective } from 'angular-datatables';
declare var $: any;
import 'datatables.net';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { timer } from 'rxjs';
import { SubmitFormService } from "../shared/submit-form.service";
import { SeguridadService } from "../shared/seguridad.service";

declare function myclick(thisobject): any;

@Component({
  selector: 'app-tabla-main-calendario',
  templateUrl: './tabla-main-calendario.component.html',
  styleUrls: ['./tabla-main-calendario.component.css'],
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
export class TablaMainCalendarioComponent implements OnInit {
  @Input() inputfrmmain;
  //frmmain: FrmMainParam;
  FrmMainListFD: FrmMainFD[];
  MtDialogRefCompPerm: MatDialogRef<ComunicacionPermanenteComponent>;
  MtDialogRefVCV: MatDialogRef<VerifConcicliacionVirtComponent>;
  //MtDialogRefFin: MatDialogRef<FinalizarQuejaComponent>;
  MtDialogRefBit: MatDialogRef<BitacoraACComponent>;
  MtDialogRefPrimAud: MatDialogRef<PrimeraAudienciaComponent>;
  MtDialogRefSegAud: MatDialogRef<SegundaAudienciaComponent>;
  MtDialogRefConc: MatDialogRef<ConciliacionComponent>;
  MtDialogRefInc: MatDialogRef<IncomparecenciaComponent>;
  MtDialogRefVisita: MatDialogRef<VisitaCampoComponent>;
  MtDialogRefArchivarQ: MatDialogRef<ArchivarQuejaComponent>;
  MtDialogRefMovExp: MatDialogRef<MovimientoExpedienteComponent>;
  MtDialogRefVerifDatos: MatDialogRef<VerifDatosComponent>;
  flagDBError:boolean;
  flagdesdegthasta:boolean;
  flagmissingdatep:boolean;
  flaglimitError:boolean;
  flagAtConVerificador:boolean;
  rows=[];
  columns=[];
  data = [];
  filteredData = [];  
  selected = [];	  
	  
	  
  //dtOptions: DataTables.Settings = {};
  //dtTrigger: Subject<any> = new Subject();
  //jsonoriginal;
  //titleArray=[];
  
  constructor(private dialog: MatDialog, private _maintableservice:MainTableService, private _submitFormService:SubmitFormService, private _seguridadService:SeguridadService){
  
	  this.columns = [
		{ prop: 'lunes', width: 100 },
		{ prop: 'martes', width: 100 },
		{ prop: 'miercoles', width: 100 },
		{ prop: 'jueves', width: 100 },
		{ prop: 'viernes', width: 100 },
		{ prop: 'sabado', width: 100 },
		{ prop: 'domingo', width: 100 }
	  ];
		this.flaglimitError=false;
		this.flagAtConVerificador=false;
  }

  ngOnInit() {
	//this.LoadInputInfo();
	//if( this.ValidateRefresh() ){	
		this.LoadServiceInfo();
		//this.setupDT();
		this.flagDBError=false;
		this.flagdesdegthasta=false;
	//}
	
	// populate datatable rows
	this.data = this.rows;
	// copy over dataset to empty object
	this.filteredData = this.rows;
	this.VerificarRol();
	  
  }
  
  VerificarRol(){
	  if(this._submitFormService.Get_Atcon_Verificador() == 1){
		  this.flagAtConVerificador=true;
	  }
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
  
  LoadInputInfo(){
	let frmmain = new FrmMainParam();    
	if (typeof this.inputfrmmain['controls'] === "undefined") {
		frmmain['selectedFiltro']=null;
		frmmain['SelectedFDesde']=null;
		frmmain['SelectedFHasta']=null;
		frmmain['selectedNoqueja']='';
	}
	else{
		console.log(this.inputfrmmain);
		frmmain['selectedFiltro']=this.inputfrmmain['controls']['selectedFiltro'].value;
		frmmain['SelectedFDesde']=this.inputfrmmain['controls']['SelectedFDesde'].value;
		frmmain['SelectedFHasta']=this.inputfrmmain['controls']['SelectedFHasta'].value;
		frmmain['selectedNoqueja']=this.inputfrmmain['controls']['selectedNoqueja'].value;		
	}
	return frmmain;
  }
  
  ngAfterViewInit(){
	//this.setupDT();
	//this.dtTrigger.next();
	//console.log('ngAfterViewInit');
  }
  
  /*SetEdicionxEstadoQueja(estado){
	  if(estado <101 || estado >999)
		  return null;
	  if(estado >=101 && estado <=106)
		  return true;
	  else
		  return false;
  }*/

  openVerifVirtual(NoQueja, strQueja) {
	  this.Token();
	  const dialogConfig = new MatDialogConfig();
	    dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '100%';
		dialogConfig.width = '1200px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefVCV = this.dialog.open(VerifConcicliacionVirtComponent,dialogConfig);
  
  	this.MtDialogRefVCV.afterClosed().subscribe(result => {
		this.ModalLogicVerifVirtual(result); 
	});
  
  }
  
  openComPermanente(NoQueja, strQueja, flujo) {
	  this.Token();
	  const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '500px';
		dialogConfig.width = '1200px';
		dialogConfig.data = 
		{NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid(), Flujo: flujo};
		
    this.MtDialogRefCompPerm = this.dialog.open(ComunicacionPermanenteComponent,dialogConfig);
	this.MtDialogRefCompPerm.afterClosed().subscribe(result => {
		this.ModalLogicComPerm(result); 
	});
  }
  
	/*openFinalizarQueja(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();
	    dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '400px';
		dialogConfig.width = '1200px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefFin = this.dialog.open(FinalizarQuejaComponent,dialogConfig);
		
  }*/
  
  openBitacora(NoQueja, strQueja, flujo) {
	  this.Token();
	  const dialogConfig = new MatDialogConfig();
	    dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '1000px';
		dialogConfig.width = '1200px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid(), Flujo: flujo};
		
    this.MtDialogRefBit = this.dialog.open(BitacoraACComponent,dialogConfig);
		
  }

	openPrimeraAudiencia(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '80%';
		dialogConfig.width = '90%';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefPrimAud = this.dialog.open(PrimeraAudienciaComponent,dialogConfig);
	this.MtDialogRefPrimAud.afterClosed().subscribe(result => {
		this.ModalLogicAudiencia(result); 
	});	
  }

	openSegundaAudiencia(NoQueja, strQueja, flujo) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();
	    dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '700px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid(), Flujo: flujo};
		
    this.MtDialogRefSegAud = this.dialog.open(SegundaAudienciaComponent,dialogConfig);
	this.MtDialogRefSegAud.afterClosed().subscribe(result => {
		this.ModalLogicAudiencia(result); 
	});	
  }

	openConciliacion(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '600px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefConc = this.dialog.open(ConciliacionComponent,dialogConfig);
		
  }

	openIncomparecencia(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '600px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefInc = this.dialog.open(IncomparecenciaComponent,dialogConfig);
		
  }

	openVisitaCampo(NoQueja, strQueja, flujo) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();
	    dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '900px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid(), Flujo: flujo};
		
    this.MtDialogRefVisita = this.dialog.open(VisitaCampoComponent,dialogConfig);
		
  }

	openArchivarQueja(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1100px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
    this.MtDialogRefArchivarQ = this.dialog.open(ArchivarQuejaComponent,dialogConfig);
		
  }

	openMovimientoExpediente(NoQueja, strQueja) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '800px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid()};
		
		this.MtDialogRefMovExp = this.dialog.open(MovimientoExpedienteComponent,dialogConfig);
		this.MtDialogRefMovExp.afterClosed().subscribe(result => {
			this.ModalLogicMovimiento(result); 
		});	
  }

	openVerifDatos(NoQueja, strQueja, flujo) {
		this.Token();
	  const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
		dialogConfig.height = '600px';
		dialogConfig.width = '1000px';
		dialogConfig.data = {NoQueja: NoQueja, quejanumero: strQueja, Rol: this._submitFormService.Get_Atencion_Consumidor(), Usuario: this._submitFormService.Get_userid(), Flujo: flujo};
		
		this.MtDialogRefVerifDatos = this.dialog.open(VerifDatosComponent,dialogConfig);
		this.MtDialogRefVerifDatos.afterClosed().subscribe(result => {
			this.ModalLogicVerifDatos(result); 
		});
  }


	ModalLogicVerifVirtual(FormResponse){
		if(FormResponse.changes){
			this.LoadServiceInfo();
		}
		console.log(FormResponse);
		if(FormResponse.pagina == 1){
			this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);
		}else if(FormResponse.pagina == 2){
			this.openVerifDatos(+FormResponse.NoQueja, FormResponse.quejanumero, 1);			
		}
	}
	
	ModalLogicComPerm(FormResponse){
		if(FormResponse.changes){
			this.LoadServiceInfo();
		}
		console.log(FormResponse);
		if(FormResponse.pagina == 1){
			this.openPrimeraAudiencia(+FormResponse.NoQueja, FormResponse.quejanumero);			
		}
	}

	ModalLogicMovimiento(FormResponse){
		if(FormResponse.changes){
			this.LoadServiceInfo();
		}
	}

	ModalLogicVerifDatos(FormResponse){
		console.log(FormResponse.changes);
		if(FormResponse.changes){
			this.LoadServiceInfo();
		}
	}
	
	ModalLogicAudiencia(FormResponse){
		if(FormResponse.changes){
			this.LoadServiceInfo();
		}
		console.log(FormResponse);
		if(FormResponse.pagina == 1){
			this.openMovimientoExpediente(+FormResponse.NoQueja, FormResponse.quejanumero);
		}else if(FormResponse.pagina == 2){
			this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);			
		}else if(FormResponse.pagina == 3){
			this.openConciliacion(+FormResponse.NoQueja, FormResponse.quejanumero);			
		}else if(FormResponse.pagina == 4){
			this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);			
		}else if(FormResponse.pagina == 5){
			this.openIncomparecencia(+FormResponse.NoQueja, FormResponse.quejanumero);			
		}
	}

   local_myclick(obje){
		myclick(obje);
   }

	LoadServiceInfo(){
		this.Token();
		let frmmainloc=this.LoadInputInfo();
		let tempstr='';
		this._maintableservice.getUsers(frmmainloc).subscribe((Data)=>{
			if(Data["reason"] == 'OK'){
				tempstr=Data['value'];
				console.log(Data);
				//console.log(tempstr);
				if(tempstr != null && tempstr != '')	{
					this.FrmMainListFD=JSON.parse('['+tempstr.slice(0, -1) +']');
					if(this.FrmMainListFD.length>=2000){
						this.flaglimitError=true;
						this.SetSecTimerLimitError();
					}
				}else{
					this.FrmMainListFD=[];
				}
				this.rows=this.FrmMainListFD;
				this.data = this.rows;
				this.filteredData = this.rows;
				console.log(Data);			
				this.flagDBError=false;
			}else{
				console.log('Rest Service Error');
				this.flagDBError=true;
				this.SetSecTimerDBError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerDBError();
		});
		//this.InfotoArray();
	}

	ValidateRefresh(): boolean {
	  /*let hasfiltro:boolean=false;
	  let hasdesde:boolean=false;
	  let hashasta:boolean=false;
	  let locresult:boolean=false;
	  
	  if(typeof this.frmmain.selectedFiltro!='undefined' && this.frmmain.selectedFiltro){
		  hasfiltro=true;
	  }
	  if(typeof this.frmmain.SelectedFDesde!='undefined' && this.frmmain.SelectedFDesde){
		  hasdesde=true;
	  }
	  if(typeof this.frmmain.SelectedFHasta!='undefined' && this.frmmain.SelectedFHasta){
		  hashasta=true;
	  }
	  locresult=this.frmmain.SelectedFDesde > this.frmmain.SelectedFHasta;
	  //console.log('hey '+locresult);
	  
	  if( (hasdesde && !hashasta) || (!hasdesde && hashasta) ){
		  this.flagmissingdatep=true;
		  this.SetSecTimermissingdatep();
		  return false;
	  }else{
		  this.flagmissingdatep=false;
	  }
	  
	  if(hasdesde && hashasta && (this.frmmain.SelectedFDesde > this.frmmain.SelectedFHasta) ){
		  this.flagdesdegthasta=true;
		  this.SetSecTimerdesdegthasta();
		  return false;
	  }else{
		  this.flagdesdegthasta=false;
	  }*/
	  
	  return true;
	}

	SetSecTimerdesdegthasta(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagdesdegthasta=false);
	}

	SetSecTimermissingdatep(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagmissingdatep=false);
	}

	// filters results
	filterDatatable(event){
	  // get the value of the key pressed and make it lowercase
	  let val = event.target.value.toLowerCase();
	  // get the amount of columns in the table
	  //let colsAmt = this.columns.length;
	  console.log(this.data[0]);
	  // get the key names of each column in the dataset
	  let keys = Object.keys(this.data[0]);
	  let colsAmt = keys.length;
	  //let validkeys = [1,3,4,9,12];
	  let validkeys = [0,1,3,4,7,10,12];
	  console.log(val, colsAmt, keys);
	  // assign filtered matches to the active datatable
	  this.rows = this.filteredData.filter(function(item){
		// iterate through each row's column data
		for (let i=0; i<colsAmt; i++){
			if(validkeys.indexOf(i) != -1 ){
				  // check for a match
				  if (item[keys[i]].toString().toLowerCase().indexOf(val) !== -1 || !val){
					// found match, return true to add to result set
					return true;
				  }
			}
		}
	  });
	  console.log(this.rows);
	  // whenever the filter changes, always go back to the first page
	  //this.table.offset = 0;
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

	SetSecTimerDBError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
	}
	SetSecTimerLimitError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flaglimitError=false);
	}

	SetBtnClass(enabled:boolean, image){
		if(enabled)
			return 'btn btn-apgreen btn-image '+image;
		else
			return 'btn btn-primary btn-image '+image;
	}

}

