import { Component, OnInit } from '@angular/core';
import { Area } from '../models/area';
import { CatAreaService } from '../shared/cat-area.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";


@Component({
  selector: 'app-cat-area',
  templateUrl: './cat-area.component.html',
  styleUrls: ['./cat-area.component.css']
})
export class CatAreaComponent implements OnInit {

 

  areaArray: Area[] = [ ];

   selectedArea: Area = new Area();
   auxiliar : string ;
   auxiliarId : number;
   nombre : string;
   idArea : number;
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
if(this.idArea == 0) {
if(this.nombre!=null) {
  if(this.validarRepetidos()) {
      this._areaService.saveData(this.nombre).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success'); 
        this.selectedArea.id_area = this.areaArray.length+1;
        this.selectedArea.nombre = this.nombre;
        
         this.areaArray.push(this.selectedArea);
          this.selectedArea = new Area();  
         this.GetAreaList();      
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
          this._areaService.updateData(this.idArea,this.nombre).subscribe((retvalue)=>{
          if(retvalue["reason"] == 'OK'){
            console.log('Rest service success');   
             this.selectedArea = new Area();  
             this.GetAreaList();    
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


openForEdit(area: Area) {
  this.selectedArea = area;
}


setBeforeAuxiliar() {
 this.auxiliar = this.selectedArea.nombre ;
 this.auxiliarId = this.selectedArea.id_area;

}

setAfterAuxiliar() {
this.nombre = this.selectedArea.nombre;
this.idArea = this.selectedArea.id_area;  
this.selectedArea.nombre = this.auxiliar;
this.selectedArea.id_area = this.auxiliarId;
}

delete() {
  if(confirm('¿Estas seguro de eliminar el registro?')) {
    this._areaService.deleteData(this.selectedArea.id_area).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
          console.log('Rest service success');  
          this.areaArray = this.areaArray.filter(x => x != this.selectedArea);
          this.selectedArea = new Area();  
          this.GetAreaList();     
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
    for (var i = 0; i < this.areaArray.length; i++) {
      // use array[i] 
      console.log(this.areaArray[i].nombre);
      if(this.areaArray[i].nombre===this.nombre) {
   
        return false;
      }
    
    }
    return true;
  }

GetAreaList(){
  let tempstr='';
  let arr = [];
  this.areaArray =[];
  this._areaService.getListArea().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.areaArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.areaArray);
       
      }else{
      
        console.log('Lista de area interna no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}

constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _areaService:CatAreaService,public dialogRef: MatDialogRef<CatAreaComponent>  ) { 

  
   }


  ngOnInit() {
   this.GetAreaList();
  }


}
