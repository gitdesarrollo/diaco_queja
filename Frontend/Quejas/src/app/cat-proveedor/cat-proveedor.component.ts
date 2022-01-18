import { Component, OnInit } from '@angular/core';
import { Proveedor } from '../models/proveedor';
import { Municipio } from '../models/municipio';
import { Departamento } from '../models/departamento';
import { ProveedorService } from '../shared/proveedor.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { MunicipioService } from '../shared/municipio.service';
import {DepartamentoService} from '../departamento.service';
import { TipoComercioService } from '../shared/tipo-comercio.service';
import { ActividadEconomicaService } from '../shared/actividad-economica.service';
import { ProveedorComunService } from '../shared/proveedor-comun.service';
import { ActividadEconomica } from '../models/actividad-economica';
import {TipoComercio } from '../models/tipo-comercio';
import  { ProveedorComun } from '../models/proveedor-comun';
import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const"

@Component({
  selector: 'app-cat-proveedor',
  templateUrl: './cat-proveedor.component.html',
  styleUrls: ['./cat-proveedor.component.css']
})
export class CatProveedorComponent implements OnInit {

  calleArray : string[] = ['calle','avenida'];
  selectedCallecilla :string; 
  tipoProv : number; 
  selectedDepartamento = new Departamento();
  selectedMunicipio = new Municipio();
  selectedActividad = new ActividadEconomica();
  selectedComercio = new TipoComercio();
  selectedProveedorComun = new ProveedorComun();

  
  loc_pagina=0;
  notificacion : boolean;
  servicioPublico : boolean;
  calificacion : boolean;
  codigoMunicipio : number;
  nombreMunicipio : string;
  codigoDepartamento : number;
  nombreDepartamento: string;
  idActividadEconomica : number;
  actividadEconomica : string ;
  idTipoComercio : number;
  tipoComercio : string;
  habilitarNotificacion : string;
  conciliacion : boolean;



  proveedorComunArray : ProveedorComun[] = [];
  comercioArray : TipoComercio[] =[];
  actividadArray : ActividadEconomica[] = [];
  departamentoArray : Departamento[] = [];
  municipioArray : Municipio[] = [];
  selectedProveedor : Proveedor = new Proveedor();
  proveedorArray : Proveedor[] = [];
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _proveedorService:ProveedorService, 
  private _municipioService:MunicipioService, private _departamentoService:DepartamentoService,
  private _tipoComercioService:TipoComercioService,private _actividadEconomicaService:ActividadEconomicaService,
  private _proveedorComunService:ProveedorComunService ,
  public dialogRef: MatDialogRef<CatProveedorComponent>) { 
  }


 ngOnInit() {
  this.GetDepartamentoList();
  this.GetMunicipioList(1);
  this.GetActividadEconomicaList();
  this.GetTipoComercioList(1);
  this.GetProveedorComunList();
  this.selectedProveedor.tipo_proveedor=6;
  this.selectedProveedor.tipo_comercio="Seleccione el tipo de Comercio";
  this.selectedProveedor.nombre_actividad_economica="Seleccione la Actividad Economica";
  this.selectedProveedor.nombre_departamento="Seleccione el Departamento ";
  this.selectedProveedor.nombre_municipio="Seleccione el Municipio";
  this.selectedProveedor.nombre_tipo_proveedor="Seleccione el tipo Proveedor";
  this.selectedProveedor.callecilla="Seleccione calle o avenida";

  this.calificacion=false;
  this.servicioPublico=false;
  this.notificacion=false;
  this.conciliacion=false;
 }

 
 ngOnDestroy() {
  let localresp: FormResponse= new FormResponse();
 
  this.dialogRef.close(localresp);
}	


 addOrEdit() {

  let validacion : boolean;

if(this.notificacion) {
  console.log('seteo la notificacion 1 ');
    this.selectedProveedor.habilitado_notificacion_electronica="1";
}
else {
  this.selectedProveedor.habilitado_notificacion_electronica="0";
  console.log('seteo la notificacion 0 ');
}

if(this.conciliacion) {
  console.log('seteo la conciliacion 1 ');
    this.selectedProveedor.habilitado_conciliacion_previa="1";
}
else {
  this.selectedProveedor.habilitado_conciliacion_previa="0";
  console.log('seteo la conciliacion 0 ');
}

if(this.servicioPublico) {
    this.selectedProveedor.servicio_publico=true;
}
else {
    this.selectedProveedor.servicio_publico=false;
}
if(this.calificacion) {
  this.selectedProveedor.permite_calificacion=true;
}
else {
  this.selectedProveedor.permite_calificacion=false;
}
console.log('tipoProv es');
console.log(this.tipoProv);


if(this.tipoProv==6) {
  this.selectedProveedor.tipo_proveedor=this.tipoProv;
}
else if(this.tipoProv==7) {
  this.selectedProveedor.tipo_proveedor=this.tipoProv;
  this.selectedProveedor.nombre='';
}

if(this.selectedProveedor.primer_nombre==null) {
  this.selectedProveedor.primer_nombre=" ";
}

if(this.selectedProveedor.segundo_nombre==null) {
  this.selectedProveedor.segundo_nombre=" ";
}


if(this.selectedProveedor.tercer_nombre==null) {
  this.selectedProveedor.tercer_nombre=" ";
}

if(this.selectedProveedor.primer_apellido==null) {
  this.selectedProveedor.primer_apellido=" ";
}

if(this.selectedProveedor.segundo_apellido==null){
  this.selectedProveedor.segundo_apellido=" ";
}

if(this.selectedProveedor.apellido_casada==null){
  this.selectedProveedor.apellido_casada=" ";
}

if(this.selectedProveedor.direccion_zona!=null &&
  this.selectedProveedor.direccion!=null  && 
  this.selectedProveedor.codigo_departamento!=null && this.selectedProveedor.codigo_municipio!=null)

/* if(this.selectedProveedor.telefono==null && this.selectedProveedor.email!==null) {
  validacion=false;
  //alert('debe ingresar uno de estos dos datos para contacto: telefono o email');
  }//if de contacto
  else  */{
     console.log('tipo proveedor');
     console.log(this.selectedProveedor.tipo_proveedor);
      
        if(this.selectedProveedor.nit_proveedor!=null && this.selectedProveedor.id_actividad_economica!=null) {
              validacion=true;

          if(this.selectedProveedor.tipo_proveedor==7) {
                if(this.selectedProveedor.nombre!=null && 
                  this.selectedProveedor.nombre_empresa!=null && this.selectedProveedor.razon_social!=null){
                    validacion=true;
                  }
                else {
                     validacion=false;
                    alert('asegurese de haber ingresado los siguientes datos: nombre de empresa, razon social y nombre proveedor');
                 }
             } 


        }
        else {
              validacion=false;
              alert('asegurese de haber ingresado los siguientes datos: nit, actividad economica,nombre');
        }


        
  if(this.selectedProveedor.telefono!=null) {
    var isnum = /^\d+$/.test(this.selectedProveedor.telefono);
    if(isnum==false) {
      validacion=false;
          alert('el campo telefono debe de ser numerico');
    }
  }
  

  if(this.selectedProveedor.direccion_zona!=null) {
    var isnum = /^\d+$/.test(this.selectedProveedor.direccion_zona);
    if(isnum==false) {
      validacion=false;
          alert('el campo zona debe de ser numerico');
    }
  }
}

else {
  validacion=false;
  alert('Debe ingresar los siguientes datos: direccion, zona, departamento y municipio');
}

if(validacion)  {
   if(this.selectedProveedor.id_proveedor==0) {
      


      this._proveedorService.saveData(this.selectedProveedor.nombre,this.selectedProveedor.nit_proveedor,
      this.selectedProveedor.codigo_departamento,this.selectedProveedor.codigo_municipio,
      this.selectedProveedor.primer_nombre,this.selectedProveedor.segundo_nombre,this.selectedProveedor.tercer_nombre,
      this.selectedProveedor.primer_apellido,this.selectedProveedor.segundo_apellido,
      this.selectedProveedor.apellido_casada,this.selectedProveedor.direccion,
      this.selectedProveedor.direccion_zona,
      this.selectedProveedor.nombre_empresa,this.selectedProveedor.razon_social,
      this.selectedProveedor.email,
      this.tipoProv,this.selectedProveedor.telefono,
      this.selectedProveedor.id_actividad_economica,
      this.selectedProveedor.id_tipo_comercio,this.selectedProveedor.habilitado_notificacion_electronica,
      this.selectedProveedor.servicio_publico,this.selectedProveedor.habilitado_conciliacion_previa,
      this.selectedProveedor.permite_calificacion).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');        
    
      this.selectedProveedor= new Proveedor();
      this.selectedDepartamento= new Departamento();
      this.selectedMunicipio= new Municipio();
      this.selectedProveedorComun= new ProveedorComun();
      this.selectedActividad = new ActividadEconomica();
      this.selectedComercio = new TipoComercio();
      
     this.ngOnInit();
      
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    });
  }
  else {
    this._proveedorService.updateData(this.selectedProveedor.id_proveedor,this.selectedProveedor.nombre,this.selectedProveedor.nit_proveedor,
      this.selectedProveedor.codigo_departamento,this.selectedProveedor.codigo_municipio,
      this.selectedProveedor.primer_nombre,this.selectedProveedor.segundo_nombre,this.selectedProveedor.tercer_nombre,
      this.selectedProveedor.primer_apellido,this.selectedProveedor.segundo_apellido,
      this.selectedProveedor.apellido_casada,this.selectedProveedor.direccion,
      this.selectedProveedor.direccion_zona,
      this.selectedProveedor.nombre_empresa,this.selectedProveedor.razon_social,
      this.selectedProveedor.email,
      this.tipoProv,this.selectedProveedor.telefono,
      this.selectedProveedor.id_actividad_economica,
      this.selectedProveedor.id_tipo_comercio,
      this.selectedProveedor.habilitado_notificacion_electronica,
      this.selectedProveedor.servicio_publico,this.selectedProveedor.habilitado_conciliacion_previa,
      this.selectedProveedor.permite_calificacion).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
      console.log('Rest service success');        
 
      this.selectedProveedor= new Proveedor();
      this.selectedDepartamento= new Departamento();
      this.selectedMunicipio= new Municipio();
      this.selectedProveedorComun= new ProveedorComun();
      this.selectedActividad = new ActividadEconomica();
      this.selectedComercio = new TipoComercio();
      this.ngOnInit();
      
      }else{
        console.log('Rest service response ERROR.');
      }		
    },(error)=>{
      console.log(error);
  
    }); 
  }
}//validacion

 }

find() {

let tempstr='';
this._proveedorService.findByNitProveedor(this.selectedProveedor.nit_proveedor).subscribe((retvalue)=>{
  if(retvalue["reason"] == 'OK'){
    tempstr=retvalue['value'];
    if(tempstr != null)	{
     
      this.proveedorArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
      this.selectedProveedor=this.proveedorArray[0];
      this.codigoDepartamento= this.proveedorArray[0].codigo_departamento;
      this.codigoMunicipio=this.proveedorArray[0].codigo_municipio;
      this.nombreDepartamento=this.proveedorArray[0].nombre_departamento;
      this.nombreMunicipio=this.proveedorArray[0].nombre_municipio;
      this.actividadEconomica=this.proveedorArray[0].nombre_actividad_economica;
      this.tipoComercio=this.proveedorArray[0].tipo_comercio;
      this.idActividadEconomica=this.proveedorArray[0].id_actividad_economica;
      this.idTipoComercio=this.proveedorArray[0].id_tipo_comercio;
      this.tipoProv = this.proveedorArray[0].tipo_proveedor;
      this.selectedProveedor.tipo_proveedor= this.proveedorArray[0].tipo_proveedor;
      if(this.proveedorArray[0].habilitado_notificacion_electronica==="1") {
          this.notificacion=true;
      }
      else {
        this.notificacion = false;
      }
      if(this.proveedorArray[0].habilitado_conciliacion_previa==="1") {
        this.conciliacion=true;
    }
    else {
      this.conciliacion = false;
    }
console.log('servicio publico ');
console.log(this.proveedorArray[0].servicio_publico);
      if(this.proveedorArray[0].servicio_publico===true) {
        this.servicioPublico=true;
    }
    else {
      this.servicioPublico = false;
    }
    
    
    if(this.proveedorArray[0].permite_calificacion===true) {
      this.calificacion=true;
  }
  else {
    this.calificacion = false;
  }

      console.log(this.proveedorArray[0].servicio_publico);
     console.log(this.tipoProv);
      this.openForEditTipoProveedor(this.tipoProv);
   
      console.log(this.proveedorArray);
     
    }else{
    
      console.log('Proveedor no pudo ser obtenido.');
     
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
  this.proveedorComunArray =[];
  this._proveedorService.getListProveedorComun().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.proveedorComunArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
       // this.departamentoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.proveedorComunArray);
      }else{
        console.log('Lista de proveedores comunes no pudo ser obtenida.');
      }              
    }else{      
      console.log('Rest service response ERROR.');
    }		
  },(error)=>{
    console.log(error);

  });
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


GetMunicipioList(codigoDepartamento){
  let tempstr='';
  let arr = [];
  this.municipioArray =[];
  this._municipioService.getData(codigoDepartamento).subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.municipioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

        console.log(this.municipioArray);
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


GetActividadEconomicaList(){
  let tempstr='';
  let arr = [];
  this.actividadArray =[];
  this._actividadEconomicaService.getListActividadEconomica().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.actividadArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
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

GetTipoComercioList(idActividadEconomica){
  let tempstr='';
  let arr = [];
  this.comercioArray =[];
  this._tipoComercioService.getListTipoComercio(idActividadEconomica).subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.comercioArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.comercioArray);
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

GetProveedorComunList(){
  let tempstr='';
  let arr = [];
  this.proveedorComunArray =[];
  this._proveedorComunService.getListProveedorComun().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
        this.proveedorComunArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.proveedorComunArray);
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

closeDialog() {
  this.loc_pagina=0;
  this.ngOnDestroy();
}


openForEditTipoProveedor(id : number) {
    this.tipoProv=id;
    this.selectedProveedor.tipo_proveedor=id;

    if(this.selectedProveedor.tipo_proveedor == 6) {
        this.selectedProveedor.nombre_tipo_proveedor="Individual";
    }
    else {
      this.selectedProveedor.nombre_tipo_proveedor="Juridico";
    }
}

openForEditTipoComercio(comercio : TipoComercio) {
this.selectedComercio = comercio;
this.selectedProveedor.id_tipo_comercio=comercio.id_tipo_comercio;
this.selectedProveedor.tipo_comercio=comercio.tipo_comercio;

}

openForEditActividad(actividad : ActividadEconomica) {
  this.selectedActividad = actividad;
  this.selectedProveedor.id_actividad_economica=actividad.id_actividad_economica;
  this.selectedProveedor.nombre_actividad_economica=actividad.nombre_actividad_economica;

  this.GetTipoComercioList(actividad.id_actividad_economica);
}

openForEditDepartamento(departamento : Departamento) {
  this.selectedDepartamento = departamento;
  this.selectedProveedor.nombre_departamento = departamento.nombre_departamento;
  this.selectedProveedor.codigo_departamento = departamento.codigo_departamento;
  this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);

}

openForEditMunicipio(municipio : Municipio) {
  this.selectedMunicipio = municipio;
  this.selectedProveedor.codigo_municipio= municipio.codigo_municipio;
  this.selectedProveedor.nombre_municipio= municipio.nombre_municipio;
}

openForEditCalle(calle : string) {
  this.selectedCallecilla = calle;
  this.selectedProveedor.callecilla=calle;
}

openForEditProveedorComun(proveedorComun : ProveedorComun) {
  this.selectedProveedorComun=proveedorComun;
  this.findProveedorById(proveedorComun.id_proveedor);
  // aqui llamar findProveedorById
}


findProveedorById(idProveedor) {

  let tempstr='';
  this._proveedorService.findById(idProveedor).subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.proveedorArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        
      this.selectedProveedor=this.proveedorArray[0];
      this.codigoDepartamento= this.proveedorArray[0].codigo_departamento;
      this.codigoMunicipio=this.proveedorArray[0].codigo_municipio;
      this.nombreDepartamento=this.proveedorArray[0].nombre_departamento;
      this.nombreMunicipio=this.proveedorArray[0].nombre_municipio;
      this.actividadEconomica=this.proveedorArray[0].nombre_actividad_economica;
      this.tipoComercio=this.proveedorArray[0].tipo_comercio;
      this.idActividadEconomica=this.proveedorArray[0].id_actividad_economica;
      this.idTipoComercio=this.proveedorArray[0].id_tipo_comercio;
      this.tipoProv = this.proveedorArray[0].tipo_proveedor;
      if(this.proveedorArray[0].habilitado_notificacion_electronica==="1") {
          this.notificacion=true;
      }
      else {
        this.notificacion = false;
      }

      if(this.proveedorArray[0].habilitado_conciliacion_previa ==="1") {
        this.conciliacion=true;
    }
    else {
      this.conciliacion = false;
    }
      if(this.proveedorArray[0].servicio_publico===true) {
        this.servicioPublico=true;
    }
    else {
      this.servicioPublico = false;
    }


    if(this.proveedorArray[0].permite_calificacion===true) {
      this.calificacion=true;
  }
  else {
    this.calificacion = false;
  }

      console.log(this.proveedorArray[0].servicio_publico);
     
      this.openForEditTipoProveedor(this.selectedProveedor.tipo_proveedor);
     

      console.log(this.proveedorArray);
       
      }else{
      
        console.log('El proveedor no pudo ser obtenido.');
       
      }
            
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);
  
  });
  }
  

  delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
    
      this._proveedorService.deleteData(this.selectedProveedor.id_proveedor).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');    
        this.proveedorArray = this.proveedorArray.filter(x => x != this.selectedProveedor);  
        this.selectedProveedor = new Proveedor();
       
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
      }); 
    }
     
  
  }

}
