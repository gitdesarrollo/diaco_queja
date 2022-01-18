


import { Component, OnInit } from '@angular/core';
import { TipoConsumidor } from '../models/tipo-consumidor';
import { TipoConsumidorService } from '../shared/tipo-consumidor.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-tipo-consumidor',
  templateUrl: './cat-tipo-consumidor.component.html',
  styleUrls: ['./cat-tipo-consumidor.component.css']
})

export class CatTipoConsumidorComponent implements OnInit {
  
  

  tipoArray: TipoConsumidor[] = [ ];

   selectedTipo: TipoConsumidor = new TipoConsumidor();
   auxiliar : string ;
   auxiliarId : number;
   nombre : string;
   idTipo : number;
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
if(this.idTipo == 0) {
if(this.nombre!=null) {
  if(this.validarRepetidos()) {
      this._tipoService.saveData(this.nombre).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedTipo.id_tipo_consumidor = this.tipoArray.length+1;
        this.selectedTipo.nombre_consumidor = this.nombre;
        
         this.tipoArray.push(this.selectedTipo);
          this.selectedTipo = new TipoConsumidor();  
         this.GetTipoList();      
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
      if(this.nombre!=null) {
          this._tipoService.updateData(this.idTipo,this.nombre).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedTipo = new TipoConsumidor();  
             this.GetTipoList();    
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


openForEdit(tipo: TipoConsumidor) {
  this.selectedTipo = tipo;
}


setBeforeAuxiliar() {
 this.auxiliar = this.selectedTipo.nombre_consumidor ;
 this.auxiliarId = this.selectedTipo.id_tipo_consumidor;

}

setAfterAuxiliar() {
this.nombre = this.selectedTipo.nombre_consumidor;
this.idTipo = this.selectedTipo.id_tipo_consumidor;  
this.selectedTipo.nombre_consumidor = this.auxiliar;
this.selectedTipo.id_tipo_consumidor = this.auxiliarId;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._tipoService.deleteData(this.selectedTipo.id_tipo_consumidor).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');  
          this.tipoArray = this.tipoArray.filter(x => x != this.selectedTipo);
          this.selectedTipo = new TipoConsumidor();  
          this.GetTipoList();     
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
    for (var i = 0; i < this.tipoArray.length; i++) {
      // use array[i] 
      console.log(this.tipoArray[i].nombre_consumidor);
      if(this.tipoArray[i].nombre_consumidor==this.nombre) {
   
        return false;
      }
    
    }
    return true;
  }

GetTipoList(){
  let tempstr='';
  let arr = [];
  this.tipoArray =[];
  this._tipoService.getListTipoConsumidor().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.tipoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.tipoArray);
       
      }else{
      
        console.log('Lista de tipos de consumidor no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _tipoService:TipoConsumidorService,public dialogRef: MatDialogRef<CatTipoConsumidorComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetTipoList();
  }




}
