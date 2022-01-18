import { Component, OnInit } from '@angular/core';
import { Motivoqueja } from '../models/motivoqueja';
import { MotivoquejaService } from '../shared/motivoqueja.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-motivo-queja',
  templateUrl: './cat-motivo-queja.component.html',
  styleUrls: ['./cat-motivo-queja.component.css']
})
export class CatMotivoQuejaComponent implements OnInit {

  auxiliar : string ;
  auxiliarId : number;
  idMotivo : number;
  motivo : string;

  motivoArray: Motivoqueja[] = [
  ];

   selectedMotivo: Motivoqueja = new Motivoqueja();


   loc_pagina=0;

  
   ngOnDestroy() {
    let localresp: FormResponse= new FormResponse();
   
    this.dialogRef.close(localresp);
  }	
  
     closeDialog() {
      this.loc_pagina=0;
      this.ngOnDestroy();
    }

   setBeforeAuxiliar() {
    console.log('beforeAuxiliar '+this.selectedMotivo.motivo);
    this.auxiliar=this.selectedMotivo.motivo;
    this.auxiliarId=this.selectedMotivo.id_motivo_queja;
  }
  
  setAfterAuxiliar() {
    this.idMotivo= this.selectedMotivo.id_motivo_queja;
    this.motivo= this.selectedMotivo.motivo;
    
    this.selectedMotivo.motivo = this.auxiliar;
    this.selectedMotivo.id_motivo_queja = this.auxiliarId;
   
  }

   addOrEdit() {
    let str="";
    str = this.selectedMotivo.motivo+"a"
if(this.idMotivo == 0) {
if(this.motivo!=null) {
    if(this.validarRepetidos()) {
      this._motivoService.saveData(this.motivo).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');     
        this.selectedMotivo.id_motivo_queja = this.motivoArray.length+1;
        this.selectedMotivo.motivo = this.motivo;
        this.motivoArray.push(this.selectedMotivo);
        this.selectedMotivo = new Motivoqueja();
        this.GetMotivoList();    
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });
    } // if validar repetidos
     else {
        alert('Este registro ya existe en la lista ');
    }
      } // if motivo not null
      else {
        alert('No puedes ingresar un registro en blanco');
      }
    }
    else {
      if(this.motivo!=null) {
          this._motivoService.updateData(this.idMotivo,this.motivo).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');  
            this.selectedMotivo = new Motivoqueja();
            this.GetMotivoList();       
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


openForEdit(motivo: Motivoqueja) {
  this.selectedMotivo = motivo;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._motivoService.deleteData(this.selectedMotivo.id_motivo_queja).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');   
        this.motivoArray = this.motivoArray.filter(x => x != this.selectedMotivo);
        this.selectedMotivo = new Motivoqueja();
        this.GetMotivoList();      
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
  for (var i = 0; i < this.motivoArray.length; i++) {
    // use array[i] 
    console.log(this.motivoArray[i].motivo);
    if(this.motivoArray[i].motivo===this.motivo) {
 
      return false;
    }
  
  }
  return true;
  }

GetMotivoList(){
  let tempstr='';
  let arr = [];
  this.motivoArray =[];
  this._motivoService.getData().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.motivoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.motivoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.motivoArray);
       
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

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _motivoService:MotivoquejaService,public dialogRef: MatDialogRef<CatMotivoQuejaComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetMotivoList();
  }


}
