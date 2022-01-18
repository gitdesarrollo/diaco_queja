import { Component, OnInit } from '@angular/core';
import { TipoComercio } from '../models/tipo-comercio';
import {ActividadEconomica}  from '../models/actividad-economica'
import { TipoComercioService } from '../shared/tipo-comercio.service';
import {ActividadEconomicaService} from '../shared/actividad-economica.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";



@Component({
  selector: 'app-cat-tipo-comercio',
  templateUrl: './cat-tipo-comercio.component.html',
  styleUrls: ['./cat-tipo-comercio.component.css']
})
export class CatTipoComercioComponent implements OnInit {

  

  comercioArray: TipoComercio[] = [ ];
  actividadArray: ActividadEconomica[] = [];

   selectedTipoComercio: TipoComercio = new TipoComercio();
   auxiliar : string ;
   auxiliarId : number;
   auxiliarId2 : number;
   nombre : string;
   id : number;
   idActividad : number;
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
      this._tipoComercioService.saveData(this.idActividad,this.nombre).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedTipoComercio.id_tipo_comercio = this.comercioArray.length+1;
        this.selectedTipoComercio.tipo_comercio = this.nombre;
        this.selectedTipoComercio.id_actividad_economica = this.idActividad;
        
         this.comercioArray.push(this.selectedTipoComercio);
          this.selectedTipoComercio = new TipoComercio();  
          this.ngOnInit();         
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
          this._tipoComercioService.updateData(this.id,this.idActividad,this.nombre).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedTipoComercio = new TipoComercio();  
             this.ngOnInit();    
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


   
GetListActividad(){
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


openForEdit(comercio: TipoComercio) {
  this.selectedTipoComercio = comercio;
  this.selectedTipoComercio.id_actividad_economica = comercio.id_actividad_economica;
  this.selectedTipoComercio.nombre_actividad_economica = comercio.nombre_actividad_economica;



}

openForEditActividad(actividad: ActividadEconomica) {
  this.selectedTipoComercio.id_actividad_economica = actividad.id_actividad_economica;
  this.selectedTipoComercio.nombre_actividad_economica= actividad.nombre_actividad_economica;
}

setBeforeAuxiliar() {
 this.auxiliar = this.selectedTipoComercio.tipo_comercio ;
 this.auxiliarId = this.selectedTipoComercio.id_tipo_comercio;
 this.auxiliarId2 = this.selectedTipoComercio.id_actividad_economica;

}

setAfterAuxiliar() {
this.nombre = this.selectedTipoComercio.tipo_comercio;
this.id = this.selectedTipoComercio.id_tipo_comercio;  
this.idActividad = this.selectedTipoComercio.id_actividad_economica;

this.selectedTipoComercio.tipo_comercio = this.auxiliar;
this.selectedTipoComercio.id_tipo_comercio = this.auxiliarId;
this.selectedTipoComercio.id_actividad_economica= this.auxiliarId2;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._tipoComercioService.deleteData(this.selectedTipoComercio.id_tipo_comercio).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');  
          this.comercioArray = this.comercioArray.filter(x => x != this.selectedTipoComercio);
          this.selectedTipoComercio = new TipoComercio();  
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
    for (var i = 0; i < this.comercioArray.length; i++) {
      // use array[i] 
      console.log(this.comercioArray[i].tipo_comercio);
      if(this.comercioArray[i].tipo_comercio===this.nombre) {
   
        return false;
      }
    
    }
    return true;
  }

GetList(){
  let tempstr='';
  let arr = [];
  this.comercioArray =[];
  this._tipoComercioService.getList().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.comercioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.comercioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.comercioArray);
       
      }else{
      
        console.log('Lista de tipo de comercio no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _tipoComercioService:TipoComercioService,private _actividadService: ActividadEconomicaService,public dialogRef: MatDialogRef<CatTipoComercioComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetList();
   this.GetListActividad();
   this.selectedTipoComercio.nombre_actividad_economica="Selecciona actividad";
  }




}
