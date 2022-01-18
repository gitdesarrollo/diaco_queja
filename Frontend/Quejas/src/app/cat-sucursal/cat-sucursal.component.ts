import { Component, OnInit } from '@angular/core';
import { Departamento } from '../models/departamento';
import { Sucursal } from '../models/sucursal';
import { Municipio } from '../models/municipio';
import {Proveedor} from '../models/proveedor';
import { SucursalService } from '../shared/sucursal.service';
import {DepartamentoService} from '../departamento.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const";
import { Inject } from '@angular/core';
import { MunicipioService } from '../shared/municipio.service';
import {ProveedorService} from '../shared/proveedor.service';
@Component({
  selector: 'app-cat-sucursal',
  templateUrl: './cat-sucursal.component.html',
  styleUrls: ['./cat-sucursal.component.css']
})
export class CatSucursalComponent implements OnInit {

  sucursalArray : Sucursal[] = [];
 departamentoArray : Departamento[] = [];
 municipioArray : Municipio[] = [];
 proveedorArray : Proveedor[] = [];
 selectedSucursal : Sucursal = new Sucursal();
 selectedDepartamento : Departamento = new Departamento();


 auxiliar : string;
 auxiliarId : number;

 id : number;
 direccion : string;
telefono : string;
zona : string;
 loc_pagina=0;

 
 ngOnDestroy() {
  let localresp: FormResponse= new FormResponse();
 
  this.dialogRef.close(localresp);
}	

   closeDialog() {
    this.loc_pagina=0;
    this.ngOnDestroy();
  }


 constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _departamentoService:DepartamentoService,private _municipioService:MunicipioService, private _sucursalService:SucursalService,private _proveedorService:ProveedorService,public dialogRef: MatDialogRef<CatSucursalComponent>  ) { }

 ngOnInit() {
   this.GetDepartamentoList();
   this.GetMunicipioList(1);
   this.GetProveedorList();
   this.selectedSucursal.nombre_proveedor="Selecciona un proveedor";
   this.selectedSucursal.nombre_departamento="Selecciona un departamento";
   this.selectedSucursal.nombre_municipio="Selecciona un municipio";
   
 }


 openForEdit(sucursal: Sucursal) {
   this.selectedSucursal = sucursal;
 }

 
openForEditDepartamento(departamento: Departamento) {
 this.selectedDepartamento = departamento;
 this.selectedSucursal.codigo_departamento=departamento.codigo_departamento;
 this.selectedSucursal.nombre_departamento=departamento.nombre_departamento;
 this.GetMunicipioList(departamento.codigo_departamento);
}

openForEditMunicipio(municipio : Municipio) {
this.selectedSucursal.codigo_municipio=municipio.codigo_municipio;
this.selectedSucursal.nombre_municipio=municipio.nombre_municipio;
}
openForEditProveedor(proveedor: Proveedor ) {
  this.selectedSucursal.id_proveedor = proveedor.id_proveedor;
  this.selectedSucursal.nombre_proveedor = proveedor.nombre;
  this.GetList(proveedor.id_proveedor);
  
}


setBeforeAuxiliar() {
 this.auxiliar = this.selectedSucursal.direccion;
 this.auxiliarId = this.selectedSucursal.id_sucursal;

}

setAfterAuxiliar() {
this.direccion = this.selectedSucursal.direccion;
this.id = this.selectedSucursal.id_sucursal;  
this.selectedSucursal.direccion = this.auxiliar;
this.selectedSucursal.id_sucursal = this.auxiliarId;
}


 delete() {
   if(confirm('¿Estas seguro de eliminar el registro?')) {
     this._sucursalService.deleteData(this.selectedSucursal.id_sucursal).subscribe((retvalue)=>{
       if(retvalue["reason"] == 'OK'){
           console.log('Rest service success');          
           this.sucursalArray = this.sucursalArray.filter(x => x != this.selectedSucursal);
           this.selectedSucursal = new Sucursal();       
       }else{
         console.log('Rest service response ERROR.');
       }		
     },(error)=>{
       console.log(error);
     });    
   }
    
 }

 addOrEdit() { 
 if(this.selectedSucursal.id_sucursal == 0) {
     if(this.selectedSucursal.direccion!=null ){
    
       
     this._sucursalService.saveData(this.selectedSucursal.id_proveedor, 
      this.selectedSucursal.codigo_departamento,this.selectedSucursal.codigo_municipio, 
      this.selectedSucursal.direccion,
      this.selectedSucursal.zona ,this.selectedSucursal.telefono).subscribe((retvalue)=>{
       if(retvalue["reason"] == 'OK'){
       console.log('Rest service success');           
         this.selectedSucursal.id_sucursal = this.sucursalArray.length+1;
         this.sucursalArray.push(this.selectedSucursal);
         this.selectedSucursal = new Sucursal();      
         this.ngOnInit();
       }else{
         console.log('Rest service response ERROR.');
       }		
     },(error)=>{
       console.log(error);
   
     });
   

       } // if validacion de datos obligatorios
       else {
             alert('Los datos: direccion deben ser ingresados');
       }
   }
   else {
     if(this.selectedSucursal.direccion!=null ){ 
         this._sucursalService.updateData(this.selectedSucursal.id_sucursal,this.selectedSucursal.id_proveedor, 
          this.selectedSucursal.codigo_departamento,this.selectedSucursal.codigo_municipio, 
          this.selectedSucursal.direccion,
          this.selectedSucursal.zona ,this.selectedSucursal.telefono).subscribe((retvalue)=>{
         if(retvalue["reason"] == 'OK'){
           console.log('Rest service success');     
            this.selectedSucursal = new Sucursal();   
            this.ngOnInit();  
         }else{
           console.log('Rest service response ERROR.');
     }		
   },(error)=>{
     console.log(error);
               });
           

             } else  {
                 alert ('Los datos: direccion deben ser ingresados');
             } 

     
   }
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
 


 GetProveedorList(){
  let tempstr='';
  let arr = [];
  this.departamentoArray =[];
  this._proveedorService.getList().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.proveedorArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
       // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.proveedorArray);
      }else{
        console.log('Lista de proveedores no pudo ser obtenida.');
      }              
    }else{      
      console.log('Rest service response ERROR.');
    }		
  },(error)=>{
    console.log(error);

  });
}


 GetMunicipioList(codigo_departamento){
  let tempstr='';
  let arr = [];
  this.municipioArray =[];
  this._municipioService.getData(codigo_departamento).subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.municipioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
       // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.municipioArray);
      }else{
        console.log('Lista de municipio no pudo ser obtenida.');
      }              
    }else{      
      console.log('Rest service response ERROR.');
    }		
  },(error)=>{
    console.log(error);

  });
}

GetList(id_proveedor) {
 let tempstr='';
   let arr = [];
   this.sucursalArray =[];
   this._sucursalService.find(id_proveedor).subscribe((retvalue)=>{
     if(retvalue["reason"] == 'OK'){
       tempstr=retvalue['value'];
       if(tempstr != null)	{
         this.sucursalArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
         console.log(this.sucursalArray);
       }else{
         console.log('Lista de sucursales no pudo ser obtenida.');
       }              
     }else{      
       console.log('Rest service response ERROR.');
     }		
   },(error)=>{
     console.log(error);
 
   });
}



}
