import { Component, OnInit } from '@angular/core';
import { Departamento } from '../models/departamento';
import { Municipio } from '../models/municipio';
import { MunicipioService } from '../shared/municipio.service';
import {DepartamentoService} from '../departamento.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-municipio',
  templateUrl: './cat-municipio.component.html',
  styleUrls: ['./cat-municipio.component.css']
})
export class CatMunicipioComponent implements OnInit {


  auxiliar : string;
  auxiliarId : number;
  auxiliarId2 : number;

  nombreMunicipio : string;
  codigoMunicipio : number;
  codigoDepartamento: number;

  municipioArray : Municipio[] = [ ];
  departamentoArray : Departamento[] = [];
  selectedMunicipio: Municipio = new Municipio();
  selectedDepartamento: Departamento = new Departamento();

  loc_pagina=0;

  
  ngOnDestroy() {
   let localresp: FormResponse= new FormResponse();
  
   this.dialogRef.close(localresp);
 }	
 
    closeDialog() {
     this.loc_pagina=0;
     this.ngOnDestroy();
   }


constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _departamentoService:DepartamentoService, private _municipioService:MunicipioService,public dialogRef: MatDialogRef<CatMunicipioComponent>  ) { 

  
}


ngOnInit() {
  this.GetDepartamentoList();
 }


 setAfterAuxiliar() {
  this.codigoMunicipio= this.selectedMunicipio.codigo_municipio;
  this.codigoDepartamento = this.selectedMunicipio.codigo_departamento;
  this.nombreMunicipio= this.selectedMunicipio.nombre_municipio;

  this.selectedMunicipio.nombre_municipio = this.auxiliar;
  this.selectedMunicipio.codigo_municipio = this.auxiliarId;
  this.selectedMunicipio.codigo_departamento=this.auxiliarId2;
}

setBeforeAuxiliar() {
  this.auxiliar = this.selectedMunicipio.nombre_municipio; 
  this.auxiliarId = this.selectedMunicipio.codigo_municipio;
  this.auxiliarId2 = this.selectedMunicipio.codigo_departamento;
}



 openForEditDepartamento(departamento: Departamento) {
   this.selectedDepartamento = departamento;
   console.log('holaLog');
   this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);
 }

 openForEdit(municipio: Municipio) {
  this.selectedMunicipio = municipio;
}

 delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._municipioService.deleteData(this.selectedMunicipio.codigo_municipio).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');    
       this.municipioArray = this.municipioArray.filter(x => x != this.selectedMunicipio);
       this.selectedMunicipio = new Municipio();    
       this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);     
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
    });

  }
   

}


addOrEdit() {
  let str="";

if(this.codigoMunicipio == 0) {
if(this.nombreMunicipio!=null) {
  if(this.validarRepetidos()) {
    this._municipioService.saveData(this.selectedDepartamento.codigo_departamento,this.nombreMunicipio).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');
        this.selectedMunicipio.codigo_municipio = this.municipioArray.length+1;
        this.selectedMunicipio.nombre_municipio = this.nombreMunicipio;
        this.selectedMunicipio.codigo_departamento=this.selectedDepartamento.codigo_departamento;

        this.municipioArray.push(this.selectedMunicipio);
        this.selectedMunicipio = new Municipio();     
        this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);     
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }
  else {
    alert('Este registro ya se encuentra en la lista');
  }


    }
    else {
      alert('No puedes ingresar un registro en blanco');
    }
  }
  else {
    if(this.nombreMunicipio!=null) {
        this._municipioService.updateData(this.selectedDepartamento.codigo_departamento,this.codigoMunicipio,this.nombreMunicipio).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');    
          this.selectedMunicipio = new Municipio();
          this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);     
        }else{
          console.log('Rest service response ERROR.');
    }		
  },(error)=>{
    console.log(error);
              });

 
    }
    else {
      alert('No puedes modificar un registro por uno en blanco');
    }
  }

  

 }


 validarRepetidos() {
  console.log('entro a funcion');
    for (var i = 0; i < this.municipioArray.length; i++) {
      // use array[i] 
      if(this.municipioArray[i].nombre_municipio===this.nombreMunicipio) {
   
        return false;
      }
    
    }
    return true;
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
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
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


}
