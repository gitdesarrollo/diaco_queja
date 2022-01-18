import { Component, OnInit } from '@angular/core';
import { ActividadEconomica } from '../models/actividad-economica';
import { ActividadEconomicaService } from '../shared/actividad-economica.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";

@Component({
  selector: 'app-cat-actividad-economica',
  templateUrl: './cat-actividad-economica.component.html',
  styleUrls: ['./cat-actividad-economica.component.css']
})
export class CatActividadEconomicaComponent implements OnInit {

  

  actividadArray: ActividadEconomica[] = [ ];

   selectedActividad: ActividadEconomica = new ActividadEconomica();
   auxiliar : string ;
   auxiliarId : number;
   nombre : string;
   id : number;
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
if(this.id == 0) {
if(this.nombre!=null) {
  if(this.validarRepetidos()) {
      this._actividadService.saveData(this.nombre).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedActividad.id_actividad_economica = this.actividadArray.length+1;
        this.selectedActividad.nombre_actividad_economica = this.nombre;
        
         this.actividadArray.push(this.selectedActividad);
          this.selectedActividad = new ActividadEconomica();  
         this.GetList();      
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
          this._actividadService.updateData(this.id,this.nombre).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedActividad = new ActividadEconomica();  
             this.GetList();    
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


openForEdit(actividad: ActividadEconomica) {
  this.selectedActividad = actividad;
}


setBeforeAuxiliar() {
 this.auxiliar = this.selectedActividad.nombre_actividad_economica ;
 this.auxiliarId = this.selectedActividad.id_actividad_economica;

}

setAfterAuxiliar() {
this.nombre = this.selectedActividad.nombre_actividad_economica;
this.id = this.selectedActividad.id_actividad_economica;  
this.selectedActividad.nombre_actividad_economica = this.auxiliar;
this.selectedActividad.id_actividad_economica = this.auxiliarId;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._actividadService.deleteData(this.selectedActividad.id_actividad_economica).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');  
          this.actividadArray = this.actividadArray.filter(x => x != this.selectedActividad);
          this.selectedActividad = new ActividadEconomica();  
          this.GetList();     
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
    for (var i = 0; i < this.actividadArray.length; i++) {
      // use array[i] 
      console.log(this.actividadArray[i].nombre_actividad_economica);
      if(this.actividadArray[i].nombre_actividad_economica===this.nombre) {
   
        return false;
      }
    
    }
    return true;
  }

GetList(){
  let tempstr='';
  let arr = [];
  this.actividadArray =[];
  this._actividadService.getListActividadEconomica().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.actividadArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.actividadArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.actividadArray);
       
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

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _actividadService:ActividadEconomicaService,public dialogRef: MatDialogRef<CatActividadEconomicaComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetList();
  }




}
