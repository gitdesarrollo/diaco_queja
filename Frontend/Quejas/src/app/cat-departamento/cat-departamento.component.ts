import { Component, OnInit } from '@angular/core';
import { Departamento } from '../models/departamento';
import { DepartamentoService } from '../departamento.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const"

@Component({
  selector: 'app-cat-departamento',
  templateUrl: './cat-departamento.component.html',
  styleUrls: ['./cat-departamento.component.css']
})
export class CatDepartamentoComponent implements OnInit {
 
  auxiliar : string = "";
  auxiliarId : number =0;
  departamentoArray: Departamento[] = [];
  nombreDepto : string;
  idDepto : number;

   selectedDepartamento: Departamento = new Departamento();
   departamentoAuxiliar: Departamento = new Departamento();
  loc_pagina=0;

  
 ngOnDestroy() {
  let localresp: FormResponse= new FormResponse();
 
  this.dialogRef.close(localresp);
}	

   closeDialog() {
    this.loc_pagina=0;
    this.ngOnDestroy();
  }

   addOrEdit() {
    let str="";
    var key = true;
if(this.idDepto == 0) {
if(this.nombreDepto != null) {
    if(this.validarRepetidos()) {
      this._departamentoService.saveData(this.nombreDepto).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');        
        this.selectedDepartamento.codigo_departamento = this.departamentoArray.length+1;
        this.selectedDepartamento.nombre_departamento = this.nombreDepto;
        this.departamentoArray.push(this.selectedDepartamento);
        this.selectedDepartamento = new Departamento();
        
         this.GetDepartamentoList();
        
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });

    } //if validarRepetidos
    else {
      alert('Este registro ya existe en la lista');
    }

      }
      else {
        alert('No puedes ingresar un registro en blanco');
      }
    }
    else {
      if(this.nombreDepto != null) {
          this._departamentoService.updateData(this.idDepto,this.nombreDepto).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');           
            this.selectedDepartamento = new Departamento();
            this.GetDepartamentoList();      
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


openForEdit(departamento: Departamento) {
  this.selectedDepartamento = departamento;
}

setBeforeAuxiliar() {
  console.log('beforeAuxiliar '+this.selectedDepartamento.nombre_departamento);
  this.auxiliar=this.selectedDepartamento.nombre_departamento;
  this.auxiliarId=this.selectedDepartamento.codigo_departamento;
}

setAfterAuxiliar() {
  this.idDepto= this.selectedDepartamento.codigo_departamento;
  this.nombreDepto= this.selectedDepartamento.nombre_departamento;
  console.log('valor de auxiliar en setAfterAuxiliar'+this.auxiliar);
  this.selectedDepartamento.nombre_departamento = this.auxiliar;
  this.selectedDepartamento.codigo_departamento = this.auxiliarId;
  console.log(' setAfterAuxiliar '+this.auxiliar);  

 
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
  
    this._departamentoService.deleteData(this.selectedDepartamento.codigo_departamento).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');    
      this.departamentoArray = this.departamentoArray.filter(x => x != this.selectedDepartamento);  
      this.selectedDepartamento = new Departamento();
    this.GetDepartamentoList();   
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
    });

    
    
  }
   

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


 validarRepetidos() {
console.log('entro a funcion');
  for (var i = 0; i < this.departamentoArray.length; i++) {
    // use array[i] 
    console.log(this.departamentoArray[i].nombre_departamento);
    if(this.departamentoArray[i].nombre_departamento===this.nombreDepto) {
 
      return false;
    }
  
  }
  return true;
}

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _departamentoService:DepartamentoService,public dialogRef: MatDialogRef<CatDepartamentoComponent> ) { 


  
   }


  ngOnInit() {
   this.GetDepartamentoList();
  }

  

}
