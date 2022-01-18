import { Component, OnInit } from '@angular/core';
import { Paises } from '../models/paises';
import { PaisesService } from '../paises.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-paises',
  templateUrl: './cat-paises.component.html',
  styleUrls: ['./cat-paises.component.css']
})
export class CatPaisesComponent implements OnInit {
  
  

  paisArray: Paises[] = [ ];

   selectedPais: Paises = new Paises();
   auxiliar : string ;
   auxiliarId : number;
   pais : string;
   idPais : number;
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
if(this.idPais == 0) {
if(this.pais!=null) {
  if(this.validarRepetidos()) {
      this._paisesService.saveData(this.pais).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedPais.id_pais = this.paisArray.length+1;
        this.selectedPais.nombre_pais = this.pais;
        
         this.paisArray.push(this.selectedPais);
          this.selectedPais = new Paises();  
         this.GetPaisList();      
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });
    } else {
        alert('Este registro ya existe en la lista');
    }

      }
      else {
        alert('No puedes ingresar un registro en blanco');
      }
    }
    else {
      if(this.pais!=null) {
          this._paisesService.updateData(this.idPais,this.pais).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedPais = new Paises();  
             this.GetPaisList();    
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


openForEdit(pais: Paises) {
  this.selectedPais = pais;
}


setBeforeAuxiliar() {
 this.auxiliar = this.selectedPais.nombre_pais ;
 this.auxiliarId = this.selectedPais.id_pais;

}

setAfterAuxiliar() {
this.pais = this.selectedPais.nombre_pais;
this.idPais = this.selectedPais.id_pais;  
this.selectedPais.nombre_pais = this.auxiliar;
this.selectedPais.id_pais = this.auxiliarId;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._paisesService.deleteData(this.selectedPais.id_pais).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');  
          this.paisArray = this.paisArray.filter(x => x != this.selectedPais);
          this.selectedPais = new Paises();  
          this.GetPaisList();     
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
    for (var i = 0; i < this.paisArray.length; i++) {
      // use array[i] 
      console.log(this.paisArray[i].nombre_pais);
      if(this.paisArray[i].nombre_pais===this.pais) {
   
        return false;
      }
    
    }
    return true;
  }

GetPaisList(){
  let tempstr='';
  let arr = [];
  this.paisArray =[];
  this._paisesService.getData().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.paisArray);
       
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

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _paisesService:PaisesService,public dialogRef: MatDialogRef<CatPaisesComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetPaisList();
  }




}
