import {Component, OnInit, Input, ViewChild, ElementRef, OnDestroy, AfterViewInit} from '@angular/core';
import {FrmMainParam, FrmMainFD} from '../atencion-consumidor-const';
import {MatDialog, MatDialogRef, MatDialogConfig} from '@angular/material';
import {SubirArchivosComponent} from '../subir-archivos/subir-archivos.component';
import {BitacoraACComponent} from '../bitacora-ac/bitacora-ac.component';
import {ArchivarServPubComponent} from '../archivar-serv-pub/archivar-serv-pub.component';
import {MovimientoServPubComponent} from '../movimiento-serv-pub/movimiento-serv-pub.component';
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

declare function myclick(thisobject): any;

@Component({
  selector: 'app-tabla-main-serv-pub',
  templateUrl: './tabla-main-serv-pub.component.html',
  styleUrls: ['./tabla-main-serv-pub.component.css'],
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
export class TablaMainServPubComponent implements OnInit {
  @Input() inputfrmmain;
  //frmmain: FrmMainParam;
  FrmMainListFD: FrmMainFD[];
  MtDialogRefBit: MatDialogRef<BitacoraACComponent>;
  MtDialogRefMovExp: MatDialogRef<MovimientoServPubComponent>;
  MtDialogRefSubArch: MatDialogRef<SubirArchivosComponent>;
  MtDialogRefArchivar: MatDialogRef<ArchivarServPubComponent>;
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
      Rol: this._submitFormService.Get_Atencion_Consumidor(),
      Usuario: this._submitFormService.Get_userid(),
      Flujo: flujo
    };

    this.MtDialogRefBit = this.dialog.open(BitacoraACComponent, dialogConfig);

  }

  openMovimientoExpediente(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '600px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Atencion_Consumidor(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefMovExp = this.dialog.open(MovimientoServPubComponent, dialogConfig);
    this.MtDialogRefMovExp.afterClosed().subscribe(result => {
      this.ModalLogicMovimiento(result);
    });
  }

  openArchivarQueja(NoQueja, strQueja) {
    this.Token();
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.height = '600px';
    dialogConfig.width = '1000px';
    dialogConfig.data = {
      NoQueja: NoQueja,
      quejanumero: strQueja,
      Rol: this._submitFormService.Get_Atencion_Consumidor(),
      Usuario: this._submitFormService.Get_userid()
    };

    this.MtDialogRefArchivar = this.dialog.open(ArchivarServPubComponent, dialogConfig);
    /*this.MtDialogRefArchivar.afterClosed().subscribe(result => {
      this.ModalLogicMovimiento(result);
    });	*/
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

  ModalLogicMovimiento(FormResponse) {
    if (FormResponse.changes) {
      this.LoadServiceInfo();
    }
  }

  local_myclick(obje) {
    myclick(obje);
  }

  LoadServiceInfo() {
    this.Token();
    let frmmainloc = this.LoadInputInfo();
    let tempstr = '';
    this._maintableservice.getServPub(frmmainloc).subscribe((Data) => {
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

