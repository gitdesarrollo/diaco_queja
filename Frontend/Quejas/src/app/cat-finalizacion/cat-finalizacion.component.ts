
import { Component, OnInit, Injectable } from '@angular/core';
import { FinalizacionQueja } from '../models/finalizacion-queja';
import{FinalizacionQuejaService} from '../shared/finalizacion-queja.service';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
@Component({
  selector: 'app-cat-finalizacion',
  templateUrl: './cat-finalizacion.component.html',
  styleUrls: ['./cat-finalizacion.component.css']
})



export class CatFinalizacionComponent implements OnInit {
  
  selectedFinalizacion = new FinalizacionQueja();

  finalizacionArray : FinalizacionQueja[] = [];
 

  idAuxiliar : number;
  nombreAuxiliar : string;
  tipoAuxiliar : string;
  padreAuxiliar : number;
  descripcion : string;
  id : number;
  tipo : string;
  padre : number;

  nombrePadre : string;

  loc_pagina=0;

  
  ngOnDestroy() {
   let localresp: FormResponse= new FormResponse();
  
   this.dialogRef.close(localresp);
 }	
 
    closeDialog() {
     this.loc_pagina=0;
     this.ngOnDestroy();
   }
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _finalService:FinalizacionQuejaService, public dialogRef: MatDialogRef<CatFinalizacionComponent>  ) { 
  }


  ngOnInit() {
    this.GetList();
    this.selectedFinalizacion.nombreTipo="selecciona tipo de motivo";
    this.selectedFinalizacion.nombrePadre="selecciona nombre opcion padre";
   
  }
  openForEdit(fin : FinalizacionQueja) {
    this.selectedFinalizacion = fin;
    this.selectedFinalizacion.id=fin.id;
    this.selectedFinalizacion.descripcion=fin.descripcion;
  }

  openForEditTipo(str : string) {
    this.selectedFinalizacion.tipo = str;
    if(str=="S") {
      this.selectedFinalizacion.nombreTipo = "S aqui";
    }
    else if(str=="F") {  
        this.selectedFinalizacion.nombreTipo = "F aqui"; 
    }
    else if(str=="C") {
             this.selectedFinalizacion.nombreTipo = "S aqui"; 
    }
    else  {
      this.selectedFinalizacion.nombreTipo = "otro aqui";
    }
    this.tipo= str;
  }
 
  openForEditPadre(f : FinalizacionQueja) {
    this.selectedFinalizacion.padre=f.id;
    this.padre= f.id;
    this.selectedFinalizacion.nombrePadre=f.descripcion;
    this.nombrePadre = f.descripcion;

  }




  addOrEdit() {
    let str="";
    if(this.padre==null) {
      this.padre=0;
    }
if(this.id == 0) {
  
if(this.descripcion!=null) {
  if(this.validarRepetidos()) {
    if( this.tipo!=null) {
      this._finalService.saveData(this.descripcion,this.tipo,this.padre).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedFinalizacion.id = this.finalizacionArray.length+1;
        this.selectedFinalizacion.descripcion = this.descripcion;
        this.selectedFinalizacion.tipo=this.tipo;
        this.selectedFinalizacion.padre=this.padre;
        
         this.finalizacionArray.push(this.selectedFinalizacion);
          this.selectedFinalizacion = new FinalizacionQueja();  
         this.ngOnInit();
         
         
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });

    }
    else {
      alert('Debe de ingresar el tipo de motivo de queja finalizada');
    }
    } else {
        alert('Este registro ya existe en la lista');
    }

      }
      else {
        alert('No puedes ingresar un registro en blanco');
      }
    }
    else {
      if(this.descripcion!=null) {
        
          this._finalService.updateData(this.id,this.descripcion,this.tipo,this.padre).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedFinalizacion = new FinalizacionQueja();  
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


  GetList(){
    let tempstr='';
    let arr = [];
    this.finalizacionArray =[];
    this._finalService.getList().subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
          this.finalizacionArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
  
          console.log(this.finalizacionArray);
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


  setBeforeAuxiliar() {
    this.nombreAuxiliar = this.selectedFinalizacion.descripcion;
    this.idAuxiliar = this.selectedFinalizacion.id;
    this.padreAuxiliar = this.selectedFinalizacion.padre;
    this.tipoAuxiliar = this.selectedFinalizacion.tipo;
    
   }
   
   setAfterAuxiliar() {
   this.descripcion = this.selectedFinalizacion.descripcion;
   this.id= this.selectedFinalizacion.id;  
   this.tipo = this.selectedFinalizacion.tipo;
   this.padre = this.selectedFinalizacion.padre;
   

   this.selectedFinalizacion.descripcion = this.nombreAuxiliar;
   this.selectedFinalizacion.id = this.idAuxiliar;
   this.selectedFinalizacion.tipo = this.tipoAuxiliar;
   this.selectedFinalizacion.padre = this.padreAuxiliar;  
   
   }
   


   delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
      this._finalService.deleteData(this.selectedFinalizacion.id).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');  
            this.finalizacionArray = this.finalizacionArray.filter(x => x != this.selectedFinalizacion);
            this.selectedFinalizacion = new FinalizacionQueja();  
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
      for (var i = 0; i < this.finalizacionArray.length; i++) {
        // use array[i] 
        console.log(this.finalizacionArray[i].descripcion);
        if(this.finalizacionArray[i].descripcion===this.descripcion) {
     
          return false;
        }
      
      }
      return true;
    }
  

}
