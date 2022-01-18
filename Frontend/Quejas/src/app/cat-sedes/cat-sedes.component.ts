import { Component, OnInit, Injectable } from '@angular/core';
import { SedeDiaco } from '../models/sede-diaco';
import { Municipio } from '../models/municipio';
import { Departamento } from '../models/departamento';
import { MunicipioService } from '../shared/municipio.service';
import {DepartamentoService} from '../departamento.service';
import { SedeService } from '../shared/sede.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-sedes',
  templateUrl: './cat-sedes.component.html',
  styleUrls: ['./cat-sedes.component.css']
})
export class CatSedesComponent implements OnInit {

   codigoMunicipio : number;
   nombreMunicipio : string;
  codigoDepartamento : number;          
  nombreDepartamento: string;

   auxiliarBusqueda : string;
   auxiliarBusquedaId : number;

  departamentoArray : Departamento[] = [];
  municipioArray : Municipio[] = [];
  sedeArray : SedeDiaco[] = [];
  calleArray : string[] = ['calle','avenida'];
  activa : string[] = ['si','no'];

  selectedDepartamento = new Departamento();
  selectedMunicipio = new Municipio();
  selectedSede = new SedeDiaco();
  selectedCallecilla :string;  
  loc_pagina=0;

  
  ngOnDestroy() {
   let localresp: FormResponse= new FormResponse();
  
   this.dialogRef.close(localresp);
 }	
 
    closeDialog() {
     this.loc_pagina=0;
     this.ngOnDestroy();
   }

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _sedeService:SedeService, private _municipioService:MunicipioService, private _departamentoService:DepartamentoService,public dialogRef: MatDialogRef<CatSedesComponent>  ) { 
  }

  ngOnInit() {
    this.GetSedeList();
    this.GetDepartamentoList();
    this.GetMunicipioList(1);
  }

openForEdit(sede : SedeDiaco) {
let tempstr='';

this.selectedSede = sede;

this.municipioArray =[];
    this._municipioService.getData(this.selectedSede.codigo_departamento).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.municipioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
          
          this.codigoDepartamento=this.departamentoArray[0].codigo_departamento;
          this.nombreDepartamento=this.departamentoArray[0].nombre_departamento;
          
          this.codigoMunicipio= this.municipioArray[0].codigo_municipio;
          this.nombreMunicipio= this.municipioArray[0].nombre_municipio;
          
          
          for (var i = 0; i < this.departamentoArray.length; i++) {
            // use array[i] 
            
            if(this.departamentoArray[i].codigo_departamento===this.selectedSede.codigo_departamento) {
              this.departamentoArray[i].codigo_departamento=this.codigoDepartamento;
              this.departamentoArray[i].nombre_departamento=this.nombreDepartamento;
              
            }
          
          }
          
          this.departamentoArray[0].codigo_departamento= this.selectedSede.codigo_departamento;
          this.departamentoArray[0].nombre_departamento= this.selectedSede.nombre_departamento;
          
          
          for (var i = 0; i < this.municipioArray.length; i++) {
            // use array[i] 
            
            if(this.municipioArray[i].codigo_municipio===this.selectedSede.codigo_municipio) {
                  this.municipioArray[i].codigo_municipio=this.codigoMunicipio;
                  this.municipioArray[i].nombre_municipio=this.nombreMunicipio;
            }
          
          }
          
          
          this.municipioArray[0].codigo_municipio=this.selectedSede.codigo_municipio;
          this.municipioArray[0].nombre_municipio=this.selectedSede.nombre_municipio;
          


        }else{
          console.log('Lista de departamentos no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
    });



/*
this.selectedDepartamento.codigo_departamento=this.selectedSede.codigo_departamento;
this.selectedDepartamento.nombre_departamento=this.selectedSede.nombre_departamento
this.selectedMunicipio.codigo_municipio=this.selectedSede.codigo_municipio;
this.selectedMunicipio.nombre_municipio=this.selectedSede.nombre_municipio;


this.codigoDepartamento=this.departamentoArray[0].codigo_departamento;
this.nombreDepartamento=this.departamentoArray[0].nombre_departamento;

this.codigoMunicipio= this.municipioArray[0].codigo_municipio;
this.nombreMunicipio= this.municipioArray[0].nombre_municipio;


for (var i = 0; i < this.departamentoArray.length; i++) {
  // use array[i] 
  
  if(this.departamentoArray[i].codigo_departamento===this.selectedSede.codigo_departamento) {
    this.departamentoArray[i].codigo_departamento=this.codigoDepartamento;
    this.departamentoArray[i].nombre_departamento=this.nombreDepartamento;
    
  }

}

this.departamentoArray[0].codigo_departamento= this.selectedSede.codigo_departamento;
this.departamentoArray[0].nombre_departamento= this.selectedSede.nombre_departamento;


for (var i = 0; i < this.municipioArray.length; i++) {
  // use array[i] 
  
  if(this.municipioArray[i].codigo_departamento===this.selectedSede.codigo_municipio) {
        this.municipioArray[i].codigo_municipio=this.codigoMunicipio;
        this.municipioArray[i].nombre_municipio=this.nombreMunicipio;
  }

}


this.municipioArray[0].codigo_municipio=this.selectedSede.codigo_municipio;
this.municipioArray[0].nombre_municipio=this.selectedSede.nombre_municipio;

*/


}


openForEditDepartamento(departamento : Departamento) {
  this.selectedDepartamento = departamento;
  this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);

}

openForEditMunicipio(municipio : Municipio) {
  this.selectedMunicipio = municipio;
  this.selectedSede.codigo_municipio=municipio.codigo_municipio;
}

openForEditCalle(calle : string) {
  this.selectedCallecilla = calle;
  this.selectedSede.callecilla=calle;
}



GetSedeList(){
  let tempstr='';
  let arr = [];
  this.sedeArray =[];
  this._sedeService.getListSede().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.sedeArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
       // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

        console.log(this.sedeArray);
      }else{
        console.log('Lista de sedes no pudo ser obtenida.');
      }              
    }else{      
      console.log('Rest service response ERROR.');
    }		
  },(error)=>{
    console.log(error);

  });
}


  GetDepartamentoList(){
    let tempstr='';
    let arr = [];
    this.departamentoArray =[];
    this._departamentoService.getData().subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
          console.log(this.departamentoArray);
        }else{
          console.log('Lista de departamentos no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }

  
  GetMunicipioList(codigoDepartamento){
    let tempstr='';
    let arr = [];
    this.municipioArray =[];
    this._municipioService.getData(codigoDepartamento).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.municipioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
          console.log(this.municipioArray);
        }else{
          console.log('Lista de departamentos no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
    });
  }

  delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
      this._sedeService.deleteData(this.selectedSede.id_diaco_sede).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');   
          this.sedeArray = this.sedeArray.filter(x => x != this.selectedSede);
          this.selectedSede = new SedeDiaco();
          this.GetSedeList();
          this.GetDepartamentoList();
          this.GetMunicipioList(1); 
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
      });
  
    }
  }


verificarRepetido() {

}

  addOrEdit() {
    
  if(this.selectedSede.id_diaco_sede == 0) {

  if(this.selectedSede.nombre_sede!=null && this.selectedSede.direccion_avenida!=null &&
      this.selectedSede.direccion_calle!=null && this.selectedSede.direccion_zona!=null) {
        if(this.selectedSede.direccion_detalle ==null) {
              this.selectedSede.direccion_detalle=" ";
        }

        this._sedeService.saveData(this.selectedSede.nombre_sede,
        this.selectedSede.codigo_municipio,
        this.selectedSede.direccion_avenida,
        this.selectedSede.direccion_calle,
        this.selectedSede.direccion_zona,
        this.selectedSede.direccion_detalle,
        this.selectedSede.callecilla).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');     
           /* this.selectedSede.id_diaco_sede = this.sedeArray.length+1;
            this.sedeArray.push(this.selectedSede);
           */
            this.selectedSede = new SedeDiaco();   
            this.GetSedeList();
            this.GetDepartamentoList();
            this.GetMunicipioList(1);
      
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });
  
      
         }
         else {
           alert('debe ingresar datos en los campos: nombre sede, direccion calle, direccion avenida, direccion zona');
         }
    }
    else {
      if(this.selectedSede.nombre_sede!=null && this.selectedSede.direccion_avenida!=null &&
        this.selectedSede.direccion_calle!=null && this.selectedSede.direccion_zona!=null) {
   
          this._sedeService.updateData(this.selectedSede.id_diaco_sede,
            this.selectedSede.nombre_sede,
            this.selectedSede.codigo_municipio,
            this.selectedSede.direccion_avenida,
            this.selectedSede.direccion_calle,
            this.selectedSede.direccion_zona,
            this.selectedSede.direccion_detalle,
            this.selectedSede.callecilla).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');  
              this.selectedSede = new SedeDiaco();    
              this.GetSedeList(); 
              this.GetDepartamentoList(); 
              this.GetMunicipioList(1);  
          }else{
            console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
                });
  
              }
              else {
                  alert('debe ingresar datos en los campos: nombre sede, direccion calle, direccion avenida, direccion zona');
              }
    }
  
    
  
   }
  
  

}
