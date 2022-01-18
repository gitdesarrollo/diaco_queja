import { Component, OnInit } from '@angular/core';
import { Consumidor } from '../models/consumidor';
import { Municipio } from '../models/municipio';
import { Departamento } from '../models/departamento';
import {SedeDiaco} from '../models/sede-diaco';
import {Paises} from '../models/paises';
import {Etnia} from '../models/etnia';
import {Motivoqueja} from '../models/motivoqueja';

import { ConsumidorService } from '../shared/consumidor.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { MunicipioService } from '../shared/municipio.service';
import {DepartamentoService} from '../departamento.service';
import {SedeService} from '../shared/sede.service';
import {PaisesService} from '../paises.service';
import {EtniaService} from '../shared/etnia.service';
import {MotivoquejaService} from '../shared/motivoqueja.service';

import { CmbCatalogo, FrmRegComCon, FormResponse } from "../atencion-consumidor-const"


@Component({
  selector: 'app-cat-consumidor',
  templateUrl: './cat-consumidor.component.html',
  styleUrls: ['./cat-consumidor.component.css']
})
export class CatConsumidorComponent implements OnInit {

  selectedDepartamento = new Departamento();
  selectedMunicipio = new Municipio();
  selectedSede = new SedeDiaco();
  selectedPais = new Paises();
  selectedEtnia = new Etnia();
  selectedMotivo = new Motivoqueja();
  selectedConsumidor = new Consumidor();

  departamentoArray : Departamento[] = [];
  municipioArray : Municipio[] = [];
  sedeArray : SedeDiaco[]=[];
  paisArray : Paises[]=[];
  etniaArray : Etnia[]=[];
  motivoArray : Motivoqueja[]=[];
  consumidorArray : Consumidor[]=[];

  
  calleArray : string[] = ['calle','avenida'];
  selectedCallecilla :string; 
  nacionalidadArray : string[] = ['guatemalteco(a)','extranjero(a)'];
  tipoconsumidorArray : string[] = ['individual','juridico'];

  notificacion : boolean;
  autorizo : boolean;
  tipoCons : number;
  nacionalidad : number;

  loc_pagina=0;

  ngOnDestroy() {
    let localresp: FormResponse= new FormResponse();
   
    this.dialogRef.close(localresp);
  }	
  closeDialog() {
    this.loc_pagina=0;
    this.ngOnDestroy();
  }

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _consumidorService:ConsumidorService, 
  private _municipioService:MunicipioService, private _departamentoService:DepartamentoService,
private _sedeService:SedeService,private _paisService:PaisesService,private _etniaService:EtniaService,
private _motivoquejaService:MotivoquejaService,
  public dialogRef: MatDialogRef<CatConsumidorComponent>) { 
  }

  ngOnInit() {
    this.GetDepartamentoList();
    this.GetMunicipioList(1);
    this.GetSedeList();
    this.GetEtniaList();
    this.GetMotivoList();
    this.GetPaisList();
    this.autorizo=false;
    this.notificacion=false;
    this.selectedConsumidor.nombre_sede='Seleccione sede';
    this.selectedConsumidor.nombre_departamento = 'Seleccione departamento';
    this.selectedConsumidor.nombre_municipio = 'Seleccione municipio';
    this.selectedConsumidor.nombre_etnia = 'Seleccione Etnia';
    this.selectedConsumidor.nombre_pais = 'Seleccione pais';
    this.selectedConsumidor.nombre_motivo_queja= 'Seleccione motivo queja';
    this.selectedConsumidor.nombre_tipo_consumidor='Seleccione tipo de consumidor';
    this.selectedConsumidor.nombre_genero= 'Seleccione Género';
    this.selectedConsumidor.nombre_nacionalidad= 'Selecccione nacionalidad';

    
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

GetPaisList(){
  let tempstr='';
  let arr = [];
  this.paisArray =[];
  this._paisService.getData().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.paisArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.paisArray);
       
      }else{
      
        console.log('Lista de paises no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}


GetMotivoList(){
  let tempstr='';
  let arr = [];
  this.motivoArray =[];
  this._motivoquejaService.getData().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.motivoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.motivoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.motivoArray);
       
      }else{
      
        console.log('Lista de motivo de quejas no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}

GetEtniaList(){
  let tempstr='';
  let arr = [];
  this.etniaArray =[];
  this._etniaService.getListEtnia().subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.etniaArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');

       // this.motivoArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        console.log(this.etniaArray);
       
      }else{
      
        console.log('Lista de etnias no pudo ser obtenida.');
       
      }
    				
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);

  });
}

find() {

  let tempstr='';
  this._consumidorService.findByNit(this.selectedConsumidor.nit_consumidor).subscribe((retvalue)=>{
    if(retvalue["reason"] == 'OK'){
      tempstr=retvalue['value'];
      if(tempstr != null)	{
       
        this.consumidorArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
        this.selectedConsumidor=this.consumidorArray[0];

       this.openForEditNacionalidad(this.selectedConsumidor.nombre_nacionalidad);
       this.openForEditTipoConsumidor(this.selectedConsumidor.tipo_consumidor);
       
        if(this.consumidorArray[0].habilitado_notificacion_electronica==="1") {
            this.notificacion=true;
        }
        else {
          this.notificacion = false;
        }
  console.log('autorizo publicar ');
  console.log(this.consumidorArray[0].autorizoPublicar);
  
        if(this.consumidorArray[0].autorizoPublicar==="1") {
          this.autorizo=true;
      }
      else {
        this.autorizo = false;
      }
        console.log(this.consumidorArray[0].autorizoPublicar);
       
        
       
  
        console.log(this.consumidorArray);
       
      }else{
      
        console.log('Consumidor no pudo ser obtenido.');
       
      }
            
    }else{
     
      console.log('Rest service response ERROR.');
      
    }		
  },(error)=>{
    console.log(error);
  
  });
  }

  findByDpi(){

    let tempstr='';
    this._consumidorService.findByDpi(this.selectedConsumidor.documento_identificacion).subscribe((retvalue)=>{
      if(retvalue["reason"] == 'OK'){
        tempstr=retvalue['value'];
        if(tempstr != null)	{
         
          this.consumidorArray=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
          this.selectedConsumidor=this.consumidorArray[0];
  
         this.openForEditNacionalidad(this.selectedConsumidor.nombre_nacionalidad);
         this.openForEditTipoConsumidor(this.selectedConsumidor.tipo_consumidor);
         
          if(this.consumidorArray[0].habilitado_notificacion_electronica==="1") {
              this.notificacion=true;
          }
          else {
            this.notificacion = false;
          }
    console.log('autorizo publicar ');
    console.log(this.consumidorArray[0].autorizoPublicar);
    
          if(this.consumidorArray[0].autorizoPublicar==="1") {
            this.autorizo=true;
        }
        else {
          this.autorizo = false;
        }
          console.log(this.consumidorArray[0].autorizoPublicar);
         
          
         
    
          console.log(this.consumidorArray);
         
        }else{
        
          console.log('Consumidor no pudo ser obtenido.');
         
        }
              
      }else{
       
        console.log('Rest service response ERROR.');
        
      }		
    },(error)=>{
      console.log(error);
    
    });
    }

  
  
openForEditTipoConsumidor(id : number) {
  this.tipoCons=id;
  this.selectedConsumidor.tipo_consumidor=id;

  if(this.selectedConsumidor.tipo_consumidor == 6) {
      this.selectedConsumidor.nombre_tipo_consumidor="Individual";
  }
  else {
    this.selectedConsumidor.nombre_tipo_consumidor="Juridico";
  }
}

openForEditDepartamento(departamento : Departamento) {
  this.selectedDepartamento = departamento;
  this.selectedConsumidor.nombre_departamento = departamento.nombre_departamento;
  this.selectedConsumidor.codigo_departamento = departamento.codigo_departamento;
  this.GetMunicipioList(this.selectedDepartamento.codigo_departamento);

}
  

openForEditMunicipio(municipio : Municipio) {
  this.selectedMunicipio = municipio;
  this.selectedConsumidor.codigo_municipio= municipio.codigo_municipio;
  this.selectedConsumidor.nombre_municipio= municipio.nombre_municipio;
}

openForEditEtnia(etnia : Etnia) {
  this.selectedEtnia = etnia;
  this.selectedConsumidor.id_etnia= etnia.codigo_etnia;
  this.selectedConsumidor.nombre_etnia=etnia.descripcion;
}

openForEditPais(pais : Paises) {
  this.selectedPais=pais;
  this.selectedConsumidor.id_pais=pais.id_pais;
  this.selectedConsumidor.nombre_pais=pais.nombre_pais;
}

openForEditSede(sede : SedeDiaco) {
  this.selectedSede=sede;
  this.selectedConsumidor.sede_diaco_cercana=sede.id_diaco_sede;
  this.selectedConsumidor.nombre_sede=sede.nombre_sede;
}

openForEditMotivo(motivo : Motivoqueja) {
  this.selectedMotivo=motivo;
  this.selectedConsumidor.id_motivo_queja=motivo.id_motivo_queja;
  this.selectedConsumidor.nombre_motivo_queja= motivo.motivo;
}

openForEditGenero(idGenero : number) {

  if(idGenero==1) {
    this.selectedConsumidor.genero="M";
    this.selectedConsumidor.nombre_genero="Masculino";
  }
  else if (idGenero==2) {
    this.selectedConsumidor.genero="F";
    this.selectedConsumidor.nombre_genero="Femenino";
  }
 
}

openForEditNacionalidad(nacionalidadP : string) {
  
  this.selectedConsumidor.nombre_nacionalidad=nacionalidadP;

  if(nacionalidadP==='guatemalteco(a)') {
    this.nacionalidad=1;
    this.selectedConsumidor.nacionalidad=1;
  }
  else if(nacionalidadP==='extranjero(a)') {
    this.nacionalidad=2;
    this.selectedConsumidor.nacionalidad=2;
  }
}

openForEditCalle(calle : string) {
  this.selectedCallecilla = calle;
  this.selectedConsumidor.callecilla= calle;
}

addOrEdit() {

  let validacion : boolean;
  let mensaje : string;
if( this.selectedConsumidor.codigo_departamento!=null && this.selectedConsumidor.codigo_municipio!=null &&
  this.selectedConsumidor.direccion!=null && 
   this.selectedConsumidor.direccion_zona!=null && this.selectedConsumidor.sede_diaco_cercana!=null) {


   
   if(this.selectedConsumidor.correo_electronico1!=null || this.selectedConsumidor.telefono!=null)   { 
     
     
  
          if(this.selectedConsumidor.genero!=null && this.selectedConsumidor.id_etnia!=null && this.selectedConsumidor.nit_consumidor!=null 
             && this.selectedConsumidor.nombre1!=null && this.selectedConsumidor.apellido1!=null && this.selectedConsumidor.documento_identificacion!=null ) {
              validacion=true;

              if (this.tipoCons==7) {
                if(this.selectedConsumidor.nombre_empresa!=null && this.selectedConsumidor.razon_social!=null) {
                    validacion=true;
          }
        else {
                 validacion=false;
                 alert('debes de ingresar los siguientes datos: nombre de empresa, razon social');
          }
   }

          }
          else {
              validacion=false;
              alert('debes ingresar los siguientes datos: nit, dpi/pasaporte, etnia, genero, motivo de queja, primer nombre, primer apellido ');
      
               }

       
               if(this.selectedConsumidor.telefono!=null) {
                var isnum = /^\d+$/.test(this.selectedConsumidor.telefono);
                if(isnum==false) {
                  validacion=false;
                      alert('el campo telefono debe de ser numerico');
                }
              }
              
 

   }//if contacto
else {
  validacion=false;
  alert('debes ingresar al menos uno de estos campos: telefono personal o correo electronico 1');
}

if(this.selectedConsumidor.direccion_zona!=null) {
  var isnum = /^\d+$/.test(this.selectedConsumidor.direccion_zona);
  if(isnum==false) {
    validacion=false;
        alert('el campo zona debe de ser numerico');
  }
}


}
else {
  validacion=false;
  alert('debes de seleccionar departamento, municipio, sede cercana, y llenar al menos direccion y zona');
}





  if(this.notificacion) {
    console.log('seteo la notificacion 1 ');
      this.selectedConsumidor.habilitado_notificacion_electronica="1";
  }
  else {
    this.selectedConsumidor.habilitado_notificacion_electronica="0";
    console.log('seteo la notificacion 0 ');
  }
  
  if(this.autorizo) {
      this.selectedConsumidor.autorizoPublicar="1";
  }
  else {
      this.selectedConsumidor.autorizoPublicar="0";
  }
  



  if(validacion) {
    if(this.selectedConsumidor.nombre2==null){
      this.selectedConsumidor.nombre2=" ";
    }
    
    if(this.selectedConsumidor.nombre3==null){
      this.selectedConsumidor.nombre3=" ";
    }
    
    if(this.selectedConsumidor.apellido2==null){
      this.selectedConsumidor.apellido2=" ";
    }
    
    if(this.selectedConsumidor.apellido_casada==null){
      this.selectedConsumidor.apellido_casada=" ";
    }
    
    
    if(this.selectedConsumidor.correo_electronico2==null){
      this.selectedConsumidor.correo_electronico2=" ";
    }
    
    if(this.selectedConsumidor.domicilio==null){
      this.selectedConsumidor.domicilio=" ";
    }

    if(this.selectedConsumidor.id_consumidor==0) {
  
  
      this._consumidorService.saveData(
        this.nacionalidad,this.selectedConsumidor.nit_consumidor,this.selectedConsumidor.documento_identificacion,
        this.selectedConsumidor.genero,this.selectedConsumidor.direccion,
        this.selectedConsumidor.direccion_zona,
  this.selectedConsumidor.codigo_municipio, this.selectedConsumidor.codigo_departamento,this.selectedConsumidor.sede_diaco_cercana,
  this.selectedConsumidor.habilitado_notificacion_electronica,this.selectedConsumidor.nombre1,this.selectedConsumidor.nombre2,this.selectedConsumidor.nombre3,
  this.selectedConsumidor.apellido1,this.selectedConsumidor.apellido2, this.selectedConsumidor.apellido_casada,this.tipoCons,
  this.selectedConsumidor.tipo_documento,this.selectedConsumidor.id_pais,this.selectedConsumidor.id_etnia,
  this.selectedConsumidor.telefono,this.selectedConsumidor.domicilio,this.selectedConsumidor.referencia,this.selectedConsumidor.correo_electronico1,
  this.selectedConsumidor.correo_electronico2,this.selectedConsumidor.representante_legal,
	this.selectedConsumidor.nombre_empresa,this.selectedConsumidor.razon_social,this.selectedConsumidor.id_motivo_queja,
  this.selectedConsumidor.autorizoPublicar).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');        
      
        this.selectedConsumidor= new Consumidor();
        this.selectedDepartamento= new Departamento();
        this.selectedMunicipio= new Municipio();
        this.selectedEtnia= new Etnia();
        this.selectedPais = new Paises();
        this.selectedSede = new SedeDiaco();
        this.selectedMotivo = new Motivoqueja();
        
       this.ngOnInit();
        
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
    
      });
    }
    else {
      this._consumidorService.updateData(this.selectedConsumidor.id_consumidor, this.nacionalidad,this.selectedConsumidor.nit_consumidor,this.selectedConsumidor.documento_identificacion,
        this.selectedConsumidor.genero,this.selectedConsumidor.direccion,
        this.selectedConsumidor.direccion_zona,
  this.selectedConsumidor.codigo_municipio, this.selectedConsumidor.codigo_departamento,this.selectedConsumidor.sede_diaco_cercana,
  this.selectedConsumidor.habilitado_notificacion_electronica,this.selectedConsumidor.nombre1,this.selectedConsumidor.nombre2,this.selectedConsumidor.nombre3,
  this.selectedConsumidor.apellido1,this.selectedConsumidor.apellido2, this.selectedConsumidor.apellido_casada,this.tipoCons,
  this.selectedConsumidor.tipo_documento,this.selectedConsumidor.id_pais,this.selectedConsumidor.id_etnia,
  this.selectedConsumidor.telefono,this.selectedConsumidor.domicilio,this.selectedConsumidor.referencia,
  this.selectedConsumidor.correo_electronico1,this.selectedConsumidor.correo_electronico2,
  this.selectedConsumidor.representante_legal,
  this.selectedConsumidor.nombre_empresa,this.selectedConsumidor.razon_social,this.selectedConsumidor.id_motivo_queja,
  this.selectedConsumidor.autorizoPublicar).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');        
   
        this.selectedConsumidor= new Consumidor();
        this.selectedDepartamento= new Departamento();
        this.selectedMunicipio= new Municipio();
        this.selectedEtnia= new Etnia();
        this.selectedPais = new Paises();
        this.selectedSede = new SedeDiaco();
        this.selectedMotivo = new Motivoqueja();
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


   delete() {
    if(confirm('¿Estas seguro de eliminar el registro?')) {
    
      this._consumidorService.deleteData(this.selectedConsumidor.id_consumidor).subscribe((retvalue)=>{
        if(retvalue["reason"] == 'OK'){
        console.log('Rest service success');    
        this.consumidorArray = this.consumidorArray.filter(x => x != this.selectedConsumidor);  
        this.selectedConsumidor = new Consumidor();
       
        }else{
          console.log('Rest service response ERROR.');
        }		
      },(error)=>{
        console.log(error);
      }); 
    }
     
  
  }


}
