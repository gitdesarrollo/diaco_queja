import {Component, OnInit, Input, ViewChild, ElementRef, OnDestroy, AfterViewInit} from '@angular/core';
import {FrmMainParam, FrmMainFD} from '../atencion-consumidor-const';
import {MatDialog, MatDialogRef, MatDialogConfig} from '@angular/material';
import {ComunicacionPermanenteComponent} from '../comunicacion-permanente/comunicacion-permanente.component';
import {VerifConcicliacionVirtComponent} from '../verif-concicliacion-virt/verif-concicliacion-virt.component';
//import { FinalizarQuejaComponent }		from '../finalizar-queja/finalizar-queja.component';
import {BitacoraACComponent} from '../bitacora-ac/bitacora-ac.component';
import {PrimeraAudienciaComponent} from '../primera-audiencia/primera-audiencia.component';
import {SegundaAudienciaComponent} from '../segunda-audiencia/segunda-audiencia.component';
import {ConciliacionJuridicoComponent} from '../conciliacion-juridico/conciliacion-juridico.component';
import {IncomparecenciaComponent} from '../incomparecencia/incomparecencia.component';
import {VisitaCampoComponent} from '../visita-campo/visita-campo.component';
import {ArchivarJuridicoComponent} from '../archivar-juridico/archivar-juridico.component';
import {MovimientoJuridicoComponent} from '../movimiento-juridico/movimiento-juridico.component';
import {DocumentarComponent} from '../documentar/documentar.component';
import {VerifDatosComponent} from '../verif-datos/verif-datos.component';
import {SubirArchivosComponent} from '../subir-archivos/subir-archivos.component';
import {JuridicoResultadoAudienciaComponent} from '../juridico-resultado-audiencia/juridico-resultado-audiencia.component';
import {JuridicoResolucionFinalComponent} from '../juridico-resolucion-final/juridico-resolucion-final.component';
import {ViewEncapsulation} from '@angular/core';
import {MainTableService} from '../tabla-main-at-con/main-table.service';
import {Subject} from 'rxjs';
//import { DataTableDirective } from 'angular-datatables';
declare var $: any;
import 'datatables.net';
import {trigger, state, style, animate, transition} from '@angular/animations';
import {timer} from 'rxjs';
import {SubmitFormService} from '../shared/submit-form.service';
import {SeguridadService} from '../shared/seguridad.service';

@Component({
  selector: 'app-tabla-main-juridico',
  templateUrl: './tabla-main-juridico.component.html',
  styleUrls: ['./tabla-main-juridico.component.css'],
  encapsulation: ViewEncapsulation.None,
  animations: [
    trigger('EnterLeave', [
      state('flyIn', style({transform: 'translateX(0)'})),
      transition(':enter', [
        style({transform: 'translateX(-100%)'}),
        animate('100ms 150ms ease-in')
      ]),
      transition(':leave', [
        animate('300ms ease-out', style({transform: 'translateX(-100%)'}))
      ])
    ])
  ]
})
export class TablaMainJuridicoComponent implements OnInit {
  @Input() inputfrmmain;
  //frmmain: FrmMainParam;
  FrmMainListFD: FrmMainFD[];
  MtDialogRefVCV: MatDialogRef<VerifConcicliacionVirtComponent>;
  //MtDialogRefFin: MatDialogRef<FinalizarQuejaComponent>;
  MtDialogRefBit: MatDialogRef<BitacoraACComponent>;
  MtDialogRefDocumentar: MatDialogRef<DocumentarComponent>;
  MtDialogRefSegAud: MatDialogRef<SegundaAudienciaComponent>;
  MtDialogRefConc: MatDialogRef<ConciliacionJuridicoComponent>;
  MtDialogRefInc: MatDialogRef<IncomparecenciaComponent>;
  MtDialogRefVisita: MatDialogRef<VisitaCampoComponent>;
  MtDialogRefArchivarQ: MatDialogRef<ArchivarJuridicoComponent>;
  MtDialogRefMovExp: MatDialogRef<MovimientoJuridicoComponent>;
  MtDialogRefVerifDatos: MatDialogRef<VerifDatosComponent>;
  MtDialogRefSubArch: MatDialogRef<SubirArchivosComponent>;
  MtDialogRefCompPerm: MatDialogRef<ComunicacionPermanenteComponent>;
  MtDialogRefJurResAud: MatDialogRef<JuridicoResultadoAudienciaComponent>;
  MtDialogRefJurResFin: MatDialogRef<JuridicoResolucionFinalComponent>;
  flagDBError: boolean;
  flagdesdegthasta: boolean;
  flagmissingdatep: boolean;
  flaglimitError: boolean;
  rows = [];
  columns = [];
  data = [];
  filteredData = [];
  selected = [];


  //dtOptions: DataTables.Settings = {};
  //dtTrigger: Subject<any> = new Subject();
  //jsonoriginal;
  //titleArray=[];

  constructor(private dialog: MatDialog, private _maintableservice: MainTableService, private _submitFormService: SubmitFormService, private _seguridadService: SeguridadService) {

    this.columns = [
      {prop: 'quejanumero', width: 100},
      {prop: 'fecha_queja', width: 100},
      {prop: 'nombre_consumidor', width: 100},
      {prop: 'nombre_proveedor', width: 100},
      {prop: 'asignado_nombre', width: 100},
      {prop: 'completo_dato', width: 100}
    ];
    this.flaglimitError = false;
  }

  ngOnInit() {
    //this.LoadInputInfo();
    //if( this.ValidateRefresh() ){
    this.LoadServiceInfo();
    //this.setupDT();
    this.flagDBError = false;
    this.flagdesdegthasta = false;
    //}

    // populate datatable rows
    this.data = this.rows;
    // copy over dataset to empty object
    this.filteredData = this.rows;

  }

  Token() {
    let loc_token = this._submitFormService.Get_token();
    //console.log(loc_token);
    if (loc_token == '') {
      this._seguridadService.RedireccionarLogin();
    }
    this._seguridadService.VerifyToken(loc_token).subscribe((retvalue) => {
      console.log('token check', retvalue);
      if (retvalue['reason'] != 'OK') {
        this._seguridadService.SesionVencida();
      }
    }, (error) => {
      this._seguridadService.SesionVencida();
    });
  }

  LoadInputInfo() {
    let frmmain = new FrmMainParam();
    if (typeof this.inputfrmmain['controls'] === 'undefined') {
      frmmain['selectedFiltro'] = null;
      frmmain['SelectedFDesde'] = null;
      frmmain['SelectedFHasta'] = null;
      frmmain['selectedNoqueja'] = '';
    } else {
      console.log(this.inputfrmmain);
      frmmain['selectedFiltro'] = this.inputfrmmain['controls']['selectedFiltro'].value;
      frmmain['SelectedFDesde'] = this.inputfrmmain['controls']['SelectedFDesde'].value;
      frmmain['SelectedFHasta'] = this.inputfrmmain['controls']['SelectedFHasta'].value;
      frmmain['selectedNoqueja'] = this.inputfrmmain['controls']['selectedNoqueja'].value;
    }
    return frmmain;
  }

  ngAfterViewInit() {
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
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefVCV = this.dialog.open(VerifConcicliacionVirtComponent, dialogConfig);

    this.MtDialogRefVCV.afterClosed().subscribe(result => {
      this.ModalLogicVerifVirtual(result);
    });

  }

  openBitacora(NoQueja, strQueja, flujo) {
    this.Token();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '1000px';
    dialogConfig.width = '1200px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefBit = this.dialog.open(BitacoraACComponent, dialogConfig);

  }

  openSegundaAudiencia(NoQueja, strQueja, flujo) {
    this.Token();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '80%';
    dialogConfig.width = '95%';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefSegAud = this.dialog.open(SegundaAudienciaComponent, dialogConfig);
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
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefConc = this.dialog.open(ConciliacionJuridicoComponent, dialogConfig);

  }

  openIncomparecencia(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '600px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefInc = this.dialog.open(IncomparecenciaComponent, dialogConfig);

  }

  openVisitaCampo(NoQueja, strQueja, flujo) {
    this.Token();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '1000px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefVisita = this.dialog.open(VisitaCampoComponent, dialogConfig);

  }

  openArchivarQueja(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '800px';
    dialogConfig.width = '1100px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefArchivarQ = this.dialog.open(ArchivarJuridicoComponent, dialogConfig);

  }

  openMovimientoExpediente(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '800px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefMovExp = this.dialog.open(MovimientoJuridicoComponent, dialogConfig);
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
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefVerifDatos = this.dialog.open(VerifDatosComponent, dialogConfig);
    this.MtDialogRefVerifDatos.afterClosed().subscribe(result => {
      this.ModalLogicVerifDatos(result);
    });
  }

  openJuridicoResolucionFinal(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '800px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefJurResFin = this.dialog.open(JuridicoResolucionFinalComponent, dialogConfig);
    this.MtDialogRefJurResFin.afterClosed().subscribe(result => {
      this.ModalLogicMovimiento(result);
    });
  }

  openJuridicoResultadoAudiencia(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '800px';
    dialogConfig.width = '1200px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefJurResAud = this.dialog.open(JuridicoResultadoAudienciaComponent, dialogConfig);
    this.MtDialogRefJurResAud.afterClosed().subscribe(result => {
      this.ModalLogicMovimiento(result);
    });
  }

  ModalLogicVerifVirtual(FormResponse) {
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
    console.log(FormResponse);
    if (FormResponse.pagina == 1) {
      this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);
    } else if (FormResponse.pagina == 2) {
      this.openVerifDatos(+FormResponse.NoQueja, FormResponse.quejanumero, 1);
    }
  }

  ModalLogicMovimiento(FormResponse) {
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
  }

  ModalLogicVerifDatos(FormResponse) {
    console.log(FormResponse.changes);
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
  }

  ModalLogicAudiencia(FormResponse) {
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
    console.log(FormResponse);
    if (FormResponse.pagina == 1) {
      this.openMovimientoExpediente(+FormResponse.NoQueja, FormResponse.quejanumero);
    } else if (FormResponse.pagina == 2) {
      this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);
    } else if (FormResponse.pagina == 3) {
      this.openConciliacion(+FormResponse.NoQueja, FormResponse.quejanumero);
    } else if (FormResponse.pagina == 4) {
      this.openArchivarQueja(+FormResponse.NoQueja, FormResponse.quejanumero);
    } else if (FormResponse.pagina == 5) {
      this.openIncomparecencia(+FormResponse.NoQueja, FormResponse.quejanumero);
    }
  }

  ModalLogicComPerm(FormResponse) {
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
    console.log(FormResponse);
  }

  openListadoArchivos(NoQueja, strQueja, flujo) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '800px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Atencion_Consumidor(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefSubArch = this.dialog.open(SubirArchivosComponent, dialogConfig);
  }

  openDocumentar(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '600px';
    dialogConfig.width = '800px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefDocumentar = this.dialog.open(DocumentarComponent, dialogConfig);
    this.MtDialogRefDocumentar.afterClosed().subscribe(result => {
      this.ModalLogicVerifDatos(result);
    });
  }

  openComPermanente(NoQueja, strQueja, flujo) {
    this.Token();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '1000px';
    dialogConfig.width = '1200px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Juridico(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefCompPerm = this.dialog.open(ComunicacionPermanenteComponent, dialogConfig);
    this.MtDialogRefCompPerm.afterClosed().subscribe(result => {
      this.ModalLogicComPerm(result);
    });
  }

  LoadServiceInfo() {
    this.Token();
    let frmmainloc = this.LoadInputInfo();
    let tempstr = '';
    this._maintableservice.getJuridico(frmmainloc).subscribe((Data) => {
      if (Data['reason'] == 'OK') {
        tempstr = Data['value'];
        console.log(Data);
        //console.log(tempstr);
        if (tempstr != null && tempstr != '') {
          this.FrmMainListFD = JSON.parse('[' + tempstr.slice(0, -1) + ']');
          if (this.FrmMainListFD.length >= 2000) {
            this.flaglimitError = true;
            this.SetSecTimerLimitError();
          }
        } else {
          this.FrmMainListFD = [];
        }
        this.rows = this.FrmMainListFD;
        this.data = this.rows;
        this.filteredData = this.rows;
        console.log(Data);
        this.flagDBError = false;
      } else {
        console.log('Rest Service Error');
        this.flagDBError = true;
        this.SetSecTimerDBError();
      }
    }, (error) => {
      console.log(error);
      this.flagDBError = true;
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

  SetSecTimerdesdegthasta() {
    const source = timer(5000);
    const subscribe = source.subscribe(val => this.flagdesdegthasta = false);
  }

  SetSecTimermissingdatep() {
    const source = timer(5000);
    const subscribe = source.subscribe(val => this.flagmissingdatep = false);
  }

  // filters results
  filterDatatable(event) {
    let val = event.target.value.toLowerCase();
    let keys = Object.keys(this.data[0]);
    let colsAmt = keys.length;
    let validkeys = [9, 14, 3, 4, 12, 15];
    this.rows = this.filteredData.filter(function (item) {
      for (let i = 0; i < colsAmt ; i++) {
        if (validkeys.indexOf(i) !== -1) {
          try {
            if (item[keys[i]].toString().toLowerCase().indexOf(val) !== -1 || !val) {
              return true;
            }
          }catch (e) {
            console.log('sin data');
          }
        }
      }
    });
  }

  onSelect({selected}) {
    console.log('Select Event', selected, this.selected);
  }

  singleSelectCheck = (row: any) => {
    return this.selected.indexOf(row) === -1;
  };

  clickStopper(event) {
    event.stopPropagation();
    return;
  }

  SetSecTimerDBError() {
    const source = timer(5000);
    const subscribe = source.subscribe(val => this.flagDBError = false);
  }

  SetSecTimerLimitError() {
    const source = timer(5000);
    const subscribe = source.subscribe(val => this.flaglimitError = false);
  }

  SetBtnClass(enabled: boolean, image) {
    if (enabled) {
      return 'btn btn-apgreen btn-image ' + image;
    } else {
      return 'btn btn-primary btn-image ' + image;
    }
  }

}

