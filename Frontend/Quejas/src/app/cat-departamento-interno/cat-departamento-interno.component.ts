
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";
import { DepartamentoInterno } from '../models/departamento-interno';
import { Component, OnInit } from '@angular/core';
import { DepartamentoInternoService } from '../departamento-interno.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-cat-departamento-interno',
  templateUrl: './cat-departamento-interno.component.html',
  styleUrls: ['./cat-departamento-interno.component.css']
})
export class CatDepartamentoInternoComponent implements OnInit {
  
  nombreDepto : string;
  idDepto : number;
   auxiliar : string;
   auxiliarId : number;
    departamentoArray: DepartamentoInterno[] = [
    ];
  
     selectedDepartamento: DepartamentoInterno = new DepartamentoInterno();
  
     loc_pagina=0;

  
     ngOnDestroy() {
      let localresp: FormResponse= new FormResponse();
     
      this.dialogRef.close(localresp);
    }	
    
       closeDialog() {
        this.loc_pagina=0;
        this.ngOnDestroy();
      }
    
setAfterAuxiliar() {
  this.idDepto= this.selectedDepartamento.id_departamento_interno;
  this.nombreDepto= this.selectedDepartamento.nombre;
  this.selectedDepartamento.nombre = this.auxiliar;
  this.selectedDepartamento.id_departamento_interno = this.auxiliarId;
}

setBeforeAuxiliar() {
  this.auxiliar = this.selectedDepartamento.nombre; 
  this.auxiliarId = this.selectedDepartamento.id_departamento_interno;
}

     addOrEdit() {
      let str="";
      str = this.selectedDepartamento.id_departamento_interno+"a"
  if(this.idDepto == 0) {
  if(this.nombreDepto!=null) {
   if(this.validarRepetidos()) {
        this._departamentoService.saveData(this.nombreDepto).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');     
          
        this.selectedDepartamento.id_departamento_interno = this.departamentoArray.length+1;
        this.selectedDepartamento.nombre = this.nombreDepto;
        this.departamentoArray.push(this.selectedDepartamento);
        this.selectedDepartamento = new DepartamentoInterno();
        this.GetDepartamentoList();     
          }else{
            console.log('Rest service response ERROR.');
          }		
        },(error)=>{
          console.log(error);
      
        });
      }
  else  {
    alert('Este registro ya existe en la lista');
  }

        }
        else {
          alert('No puedes ingresar un registro en blanco');
        }
      }
      else {
        if(this.nombreDepto!=null) {
            this._departamentoService.updateData(this.idDepto,this.nombreDepto).subscribe((retvalue)=>{
            if(retvalue["reason"] == 'OK'){
              console.log('Rest service success');
              this.selectedDepartamento = new DepartamentoInterno();
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
  
  
  openForEdit(departamento: DepartamentoInterno) {
    this.selectedDepartamento = departamento;
  }
  
  delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
      this._departamentoService.deleteData(this.selectedDepartamento.id_departamento_interno).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');     
        this.departamentoArray = this.departamentoArray.filter(x => x != this.selectedDepartamento);
        this.selectedDepartamento = new DepartamentoInterno();
        
        this.GetDepartamentoList();
          
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
      });
  
     }
     
  
  }


  validarRepetidos() {
    console.log('entro a funcion');
      for (var i = 0; i < this.departamentoArray.length; i++) {
        // use array[i] 
        console.log(this.departamentoArray[i].nombre);
        if(this.departamentoArray[i].nombre===this.nombreDepto) {
     
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
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _departamentoService:DepartamentoInternoService,public dialogRef: MatDialogRef<CatDepartamentoInternoComponent> ) { 
  
    
     }
  
  
    ngOnInit() {
     this.GetDepartamentoList();
    }
  
    
}
