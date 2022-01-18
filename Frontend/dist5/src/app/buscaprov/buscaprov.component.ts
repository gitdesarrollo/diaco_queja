import {Component, ViewChild, OnInit} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { ApiUsuariosService } from './api-usuarios.service';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';

import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Proveedor } from '../shared/proveedor.model';



@Component({
  selector: 'app-buscaprov',
  templateUrl: './buscaprov.component.html',
  styleUrls: ['./buscaprov.component.css']
})
export class BuscaprovComponent implements OnInit {
  proveedorForm: FormGroup;	
  displayedColumns = ['Id', 'Nit', 'Nombre', 'Nombre Empresa','Razón Social', 'Seleccionar' ];
  dataSource: any;
  private proveedorToInsert: Proveedor;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  habilitado : string = "0";

  
  constructor(private dataService: ApiUsuariosService,  private dialogRef: MatDialogRef<BuscaprovComponent>) {
    //this.logService = new LogService(this.LOG_TAG);
  }



  ngOnInit() {  
	this.initProveedorForm();
  }  
  
    initProveedorForm() {
    this.proveedorForm = new FormGroup({
      'nombrep': new FormControl(''),
	  'nitProveedor': new FormControl('')
    });
	this.proveedorToInsert = new Proveedor();
    this.proveedorToInsert.idProveedor = 0;
    this.proveedorToInsert.nombre = "";
	this.proveedorToInsert.nitProveedor = "";
  this.proveedorToInsert.habilitadoConciliacionPrevia = "0";
  }

  RenderDataTable() {  
    this.dataService.getUsuarios()  
      .subscribe(  
      res => {  
        this.dataSource = new MatTableDataSource();  
        this.dataSource.data = res;  
        //console.log(this.dataSource.data);
        this.dataSource.sort = this.sort; 
        this.dataSource.paginator = this.paginator;          
      },  
      error => {  
        console.log('Se produjo un error mientras intentaba recuperar Usuarios!' + error);  
      });  
  }

  RenderDataTableProveedor() {  
   if (this.proveedorForm.value.nitProveedor == ""  && this.proveedorForm.value.nombrep == "") {
	this.dataSource = null;	
  } else {
    this.dataService.fetchDataProv(this.proveedorForm.value.nitProveedor,this.proveedorForm.value.nombrep)  
      .subscribe(  
      res => {  
        this.dataSource = new MatTableDataSource();  
        this.dataSource.data = res['value'];  
		//console.log(this.dataSource.data);
        this.dataSource.sort = this.sort; 
        this.dataSource.paginator = this.paginator;
      },  
      error => {  
        console.log('Se produjo un error mientras intentaba recuperar Usuarios!' + error);  
      }); 
  }	  
  }
  
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); 
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }
  
  seleccionar(row: any, valor: number, nombre: string, nit: string) {
	  this.proveedorToInsert.idProveedor = valor;
	this.proveedorToInsert.nombre = nombre;
	this.proveedorToInsert.nitProveedor = nit;
	//this.initProveedorForm();
	//this.dataSource = null;	
	console.info(this.proveedorToInsert.idProveedor);
	console.info(this.proveedorToInsert.nombre);
	
	}
	
	courseToInsert() {
	}
}







