import { Component, OnInit } from '@angular/core';
import { Puesto } from '../models/puesto';
import { CatPuestoService } from '../shared/cat-puesto.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const"

@Component({
  selector: 'app-cat-puesto',
  templateUrl: './cat-puesto.component.html',
  styleUrls: ['./cat-puesto.component.css']
})
export class CatPuestoComponent implements OnInit {

  
  auxiliar : string = "";
  auxiliarId : number =0;
  puestoArray: Puesto[] = [];
  nombrePuesto : string;
  idPuesto : number;

   selectedPuesto: Puesto = new Puesto();
   puestoAuxiliar: Puesto = new Puesto();
  loc_pagina=0;

  
 ngOnDestroy() {
  let localresp: FormResponse= new FormResponse();
 
  this.dialogRef.close(localresp);
}	

   closeDialog() {
    this.loc_pagina=0;
    this.ngOnDestroy();
  }



  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _puestoService:CatPuestoService,public dialogRef: MatDialogRef<CatPuestoComponent> ) { 
  }


  addOrEdit() {
    let str="";
    var key = true;
if(this.idPuesto == 0) {
if(this.nombrePuesto != null) {
    if(this.validarRepetidos()) {
      this._puestoService.saveData(this.nombrePuesto).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');        
        this.selectedPuesto.id = this.puestoArray.length+1;
        this.selectedPuesto.nombre = this.nombrePuesto;
        this.puestoArray.push(this.selectedPuesto);
        this.selectedPuesto = new Puesto();
        
         this.GetPuestoList();
        
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
      if(this.nombrePuesto != null) {
          this._puestoService.updateData(this.idPuesto,this.nombrePuesto).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');           
            this.selectedPuesto = new Puesto();
            this.GetPuestoList();      
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


openForEdit(puesto: Puesto) {
  this.selectedPuesto = puesto;
}

setBeforeAuxiliar() {
  console.log('beforeAuxiliar '+this.selectedPuesto.nombre);
  this.auxiliar=this.selectedPuesto.nombre;
  this.auxiliarId=this.selectedPuesto.id;
}

setAfterAuxiliar() {
  this.idPuesto= this.selectedPuesto.id;
  this.nombrePuesto= this.selectedPuesto.nombre;
  console.log('valor de auxiliar en setAfterAuxiliar'+this.auxiliar);
  this.selectedPuesto.nombre = this.auxiliar;
  this.selectedPuesto.id = this.auxiliarId;
  console.log(' setAfterAuxiliar '+this.auxiliar);  

 
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
  
    this._puestoService.deleteData(this.selectedPuesto.id).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');    
      this.puestoArray = this.puestoArray.filter(x => x != this.selectedPuesto);  
      this.selectedPuesto = new Puesto();
    this.GetPuestoList();   
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
    });

    
    
  }
   

}

GetPuestoList(){
  let tempstr='';
  let arr = [];
  this.puestoArray =[];
  this._puestoService.getData().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.puestoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.puestoArray);
       
      }else{
      
        console.log('Lista de puestos no pudo ser obtenida.');
       
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
  for (var i = 0; i < this.puestoArray.length; i++) {
    // use array[i] 
    console.log(this.puestoArray[i].nombre);
    if(this.puestoArray[i].nombre===this.nombrePuesto) {
 
      return false;
    }
  
  }
  return true;
}



ngOnInit() {
  this.GetPuestoList();
 }

}
