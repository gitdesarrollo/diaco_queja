import { Component, OnInit, Injectable } from '@angular/core';
import { SedeDiaco } from '../models/sede-diaco';
import { Flujo } from '../models/flujo';
import { SedeService } from '../shared/sede.service';
import{FlujoService} from '../shared/flujo.service';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { TipoCola } from '../models/tipo-cola';
import {CatTipoColaService} from '../shared/cat-tipo-cola.service'

@Component({
  selector: 'app-cat-tipo-cola',
  templateUrl: './cat-tipo-cola.component.html',
  styleUrls: ['./cat-tipo-cola.component.css']
})
export class CatTipoColaComponent implements OnInit {
  
  selectedTipoCola = new TipoCola();
  selectedFlujo = new Flujo();
  selectedSede = new SedeDiaco();
  tipocolaArray : TipoCola[] = [];
  sedeArray : SedeDiaco[]=[];
  flujoArray : Flujo[]=[];

  idTipoCola : number;
  idFlujo : number;
  nombre : string;
  ultimaAsignacion : number;
  tipo : string;
  idSede : number;


  idAuxiliar : number;
  idflujoAuxiliar : number;
  nombreAuxiliar : string;
  ultimaAsignacionAuxiliar : number;
  tipoAuxiliar : string;
  idSedeAuxiliar : number;

  loc_pagina=0;

  
  ngOnDestroy() {
   let localresp: FormResponse= new FormResponse();
  
   this.dialogRef.close(localresp);
 }	
 
    closeDialog() {
     this.loc_pagina=0;
     this.ngOnDestroy();
   }
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _sedeService:SedeService,private _tipoColaService:CatTipoColaService,private _flujoService:FlujoService, public dialogRef: MatDialogRef<CatTipoColaComponent>  ) { 
  }


  ngOnInit() {
    this.GetSedeList();
    this.GetFlujoList();
    this.GetTipoColaList();
    this.selectedTipoCola.nombre_flujo='Selecciona un flujo';
    this.selectedTipoCola.nombre_sede='Selecciona una sede';
    this.idSede=0;
  }

  openForEditSede(sede : SedeDiaco) {
    this.selectedSede = sede;
    this.selectedTipoCola.nombre_sede=sede.nombre_sede;
    this.selectedTipoCola.id_sede=sede.id_diaco_sede;
  }
  
  openForEditFlujo(flujo : Flujo) {
    this.selectedFlujo = flujo;
    this.selectedTipoCola.id_flujo=flujo.id;
    this.selectedTipoCola.nombre_flujo=flujo.flujo;
  }

  openForEdit(cola : TipoCola) {
    this.selectedTipoCola = cola;
    this.selectedTipoCola.id_flujo=cola.id_flujo;
    this.selectedTipoCola.id_sede=cola.id_sede;
    this.selectedTipoCola.nombre_sede=cola.nombre_sede;
    this.selectedTipoCola.nombre_flujo=cola.nombre_flujo;
  }



  addOrEdit() {
    let str="";
    if(this.idSede!=null){
      this.tipo="s";
    }
    else {
      this.tipo="f";
      this.idSede=0;
    }

   

if(this.idTipoCola == 0) {
if(this.nombre!=null) {
  
  if(this.validarRepetidos()) {

      this._tipoColaService.saveData(this.nombre,this.idFlujo,this.idTipoCola,this.tipo,this.idSede).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedTipoCola.id_tipo_cola = this.tipocolaArray.length+1;
        this.selectedTipoCola.nombre = this.nombre;
        this.selectedTipoCola.id_sede=this.idSede;
        this.selectedTipoCola.id_flujo=this.idFlujo;
        this.selectedTipoCola.tipo= this.tipo;
        this.selectedTipoCola.ultima_asignacion=this.ultimaAsignacion;
        
         this.tipocolaArray.push(this.selectedTipoCola);
          this.selectedTipoCola = new TipoCola();  
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
        
          this._tipoColaService.updateData(this.idTipoCola,this.nombre,this.idFlujo,0,this.tipo,this.idSede).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedTipoCola = new TipoCola();  
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


  GetSedeList(){
    let tempstr='';
    let arr = [];
    this.sedeArray =[];
    this._sedeService.getAll().subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.sedeArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
  
          console.log(this.sedeArray);
        }else{
          console.log('Lista de sedes no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }

  GetFlujoList(){
    let tempstr='';
    let arr = [];
    this.flujoArray =[];
    this._flujoService.getListFlujo().subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.flujoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
  
          console.log(this.flujoArray);
        }else{
          console.log('Lista de flujos no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }



GetTipoColaList(){
    let tempstr='';
    let arr = [];
    this.tipocolaArray =[];
    this._tipoColaService.GetListTipoCola().subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.tipocolaArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
  
          console.log(this.tipocolaArray);
        }else{
          console.log('Lista de tipos de cola no pudo ser obtenida.');
        }              
      }else{      
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }

  setBeforeAuxiliar() {
    this.nombreAuxiliar = this.selectedTipoCola.nombre;
    this.idAuxiliar = this.selectedTipoCola.id_tipo_cola;
    this.idflujoAuxiliar = this.selectedTipoCola.id_flujo;
    this.idSedeAuxiliar = this.selectedTipoCola.id_sede;
    this.ultimaAsignacionAuxiliar = this.selectedTipoCola.ultima_asignacion;
    this.tipoAuxiliar = this.selectedTipoCola.tipo;

   }
   
   setAfterAuxiliar() {
   this.nombre = this.selectedTipoCola.nombre;
   this.idTipoCola = this.selectedTipoCola.id_tipo_cola;  
   this.idFlujo = this.selectedTipoCola.id_flujo;
   this.idSede = this.selectedTipoCola.id_sede;
   this.ultimaAsignacion= this.selectedTipoCola.ultima_asignacion;
   this.tipo=this.selectedTipoCola.tipo;

   this.selectedTipoCola.nombre = this.nombreAuxiliar;
   this.selectedTipoCola.id_tipo_cola = this.idAuxiliar;
   this.selectedTipoCola.id_flujo = this.idflujoAuxiliar;
   this.selectedTipoCola.id_sede = this.idSedeAuxiliar;  
   this.selectedTipoCola.ultima_asignacion = this.ultimaAsignacionAuxiliar;
   this.selectedTipoCola.tipo = this.tipoAuxiliar;
   
   }
   


   delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
      this._tipoColaService.deleteData(this.selectedTipoCola.id_tipo_cola).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');  
            this.tipocolaArray = this.tipocolaArray.filter(x => x != this.selectedTipoCola);
            this.selectedTipoCola = new TipoCola();  
            this.GetTipoColaList();     
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
      for (var i = 0; i < this.tipocolaArray.length; i++) {
        // use array[i] 
        console.log(this.tipocolaArray[i].nombre);
        if(this.tipocolaArray[i].nombre===this.nombre) {
     
          return false;
        }
      
      }
      return true;
    }
  

}
