import { Component, OnInit } from '@angular/core';
import { Departamento } from '../models/departamento';
import { DiaInhabil } from '../models/dia-inhabil';
import { Municipio } from '../models/municipio';
import { DiainhabilService } from '../shared/diainhabil.service';
import {DepartamentoService} from '../departamento.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";
import { Inject } from '@angular/core';
@Component({
  selector: 'app-cat-dia-inhabil',
  templateUrl: './cat-dia-inhabil.component.html',
  styleUrls: ['./cat-dia-inhabil.component.css']
})
export class CatDiaInhabilComponent implements OnInit {

   diainhabilArray : DiaInhabil[] = [];
  departamentoArray : Departamento[] = [];
  diaArray : number[]=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
  mesArray : number[] = [1,2,3,4,5,6,7,8,9,10,11,12];
  selectedDiainhabil : DiaInhabil = new DiaInhabil();
  selectedDepartamento : Departamento = new Departamento();

  auxiliar : string;
  auxiliarId : number;

  idDiaInhabil : number;
  descripcion : string;

  loc_pagina=0;

  
  ngOnDestroy() {
   let localresp: FormResponse= new FormResponse();
  
   this.dialogRef.close(localresp);
 }	
 
    closeDialog() {
     this.loc_pagina=0;
     this.ngOnDestroy();
   }


getMesList() {
  this.mesArray=[1,2,3,4,5,6,7,8,9,10,11,12];
}

getDiaList() {
if(this.selectedDiainhabil.mes==2){
    this.diaArray=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28];
}
else if(this.selectedDiainhabil.mes==1 || this.selectedDiainhabil.mes==3 || 
  this.selectedDiainhabil.mes==5 || this.selectedDiainhabil.mes==7 || this.selectedDiainhabil.mes==8 || 
  this.selectedDiainhabil.mes==10 || this.selectedDiainhabil.mes==12 ) {
  this.diaArray=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
}
else {
  this.diaArray=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
}

}

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _departamentoService:DepartamentoService, private _diainhabilService:DiainhabilService,public dialogRef: MatDialogRef<CatDiaInhabilComponent>  ) { }

  ngOnInit() {
    this.GetDepartamentoList();
    this.GetDiaInhabilList(1);
    this.getMesList();
    this.selectedDiainhabil.mes=1;
    this.selectedDiainhabil.dia=1;
  }


  openForEdit(diainhabil: DiaInhabil) {
    this.selectedDiainhabil = diainhabil;
  }

  
openForEditDepartamento(departamento: Departamento) {
  this.selectedDepartamento = departamento;
  this.GetDiaInhabilList(this.selectedDepartamento.codigo_departamento);
}

openForEditMes(mes : number) {
  this.selectedDiainhabil.mes=mes;
  this.getDiaList();
}

openForEditDia(dia : number) {
this.selectedDiainhabil.dia=dia;

}



setBeforeAuxiliar() {
  this.auxiliar = this.selectedDiainhabil.descripcion;
  this.auxiliarId = this.selectedDiainhabil.id_dia_inhabil;
 
 }
 
 setAfterAuxiliar() {
 this.descripcion = this.selectedDiainhabil.descripcion;
 this.idDiaInhabil = this.selectedDiainhabil.id_dia_inhabil;  
 this.selectedDiainhabil.descripcion = this.auxiliar;
 this.selectedDiainhabil.id_dia_inhabil = this.auxiliarId;
 }
 

  delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
      this._diainhabilService.deleteData(this.selectedDiainhabil.id_dia_inhabil).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');          
            this.diainhabilArray = this.diainhabilArray.filter(x => x != this.selectedDiainhabil);
            this.selectedDiainhabil = new DiaInhabil();       
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
      });    
    }
     
  }

  addOrEdit() { 
  if(this.selectedDiainhabil.id_dia_inhabil == 0) {
      if(this.descripcion!=null && this.selectedDiainhabil.dia!=null && this.selectedDiainhabil.mes!=null){
      if(isNaN(this.selectedDiainhabil.dia)==false && isNaN(this.selectedDiainhabil.mes)==false) {  
        if(this.validarRepetidos()) {
      this._diainhabilService.saveData(this.descripcion,this.selectedDiainhabil.mes,this.selectedDiainhabil.dia,this.selectedDepartamento.codigo_departamento).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');           
          this.selectedDiainhabil.id_dia_inhabil = this.diainhabilArray.length+1;
          this.diainhabilArray.push(this.selectedDiainhabil);
          this.selectedDiainhabil = new DiaInhabil();      
          this.ngOnInit();
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });
    }// if validar repetidos
      else {
          alert('Ya existe un registro con esta descripcion en la lista ');
      }

    } // if validacion numerica de mes y anio
    else {
            alert('Los datos dia y mes deben ingresarse en formato numerico dd y MM');
    }

        } // if validacion de datos obligatorios
        else {
              alert('Los datos: descripcion, dia, y mes deben ser ingresados');
        }
    }
    else {
      if(this.descripcion!=null && this.selectedDiainhabil.dia!=null && this.selectedDiainhabil.mes!=null){ 
        if(isNaN(this.selectedDiainhabil.dia)==false && isNaN(this.selectedDiainhabil.mes)==false) { 
          this._diainhabilService.updateData(this.idDiaInhabil,this.descripcion,this.selectedDiainhabil.mes,this.selectedDiainhabil.dia,this.selectedDepartamento.codigo_departamento).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');     
             this.selectedDiainhabil = new DiaInhabil();   
             this.ngOnInit();  
          }else{
            console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
                });
              } else {
                alert('Los datos dia y mes deben ingresarse en formato numerico');
              }

              } else  {
                  alert ('Los datos: descripcion, dia, y mes deben ser ingresados');
              } 

      
    }
   }
  
  
   validarRepetidos() {
    console.log('entro a funcion');
    for (var i = 0; i < this.diainhabilArray.length; i++) {
      // use array[i] 
      console.log(this.diainhabilArray[i].descripcion);
      if(this.diainhabilArray[i].descripcion===this.selectedDiainhabil.descripcion) {
   
        return false;
      }
    
    }
    return true;
    }

    
  GetDepartamentoList(){
    let tempstr='';
    let arr = [];
    this.departamentoArray =[];
    this._diainhabilService.getListDepartamentos().subscribe((retvalue)=>{
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

GetDiaInhabilList(codigo_departamento) {
  let tempstr='';
    let arr = [];
    this.diainhabilArray =[];
    this._diainhabilService.getData(codigo_departamento).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.diainhabilArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
          console.log(this.diainhabilArray);
        }else{
          console.log('Lista de dias inhabiles no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
}



}
