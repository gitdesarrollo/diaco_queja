import { Component, OnInit } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { QuejaService } from '../shared/queja.service'
import { InteraccionService } from '../interaccion-usuario/interaccion.service';
import {MAT_DIALOG_DATA,MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { FrmDetalle_Telefono, FrmDetalle_Email, FrmDetalle_Queja, FormResponse } from '../conc-virt-const'
import { FileReadUploadComponent }  from '../file-read-upload/file-read-upload.component'

@Component({
  selector: 'app-detalle-conc-virt',
  templateUrl: './detalle-conc-virt.component.html',
  styleUrls: ['./detalle-conc-virt.component.css']
})
export class DetalleConcVirtComponent implements OnInit {
  flagInfoError: boolean;
  flaglink: boolean;
  flagIntList: boolean;
  flagIntUsuario: boolean;
  frmdetalle_queja :FrmDetalle_Queja;
  frmdetalle_email :FrmDetalle_Email[];
  frmdetalle_telefono :FrmDetalle_Telefono[];
  ListInteraccion;linkdescription;
  flagformvisible;lst_queja;
  flagEditable:boolean;
  loaderror:boolean;
  
  constructor(private _quejaService:QuejaService, @Inject(MAT_DIALOG_DATA) public data: any,  public dialogRef: MatDialogRef<DetalleConcVirtComponent>, private _interaccionService:InteraccionService) { 
		this.flagInfoError=false;
		this.flaglink=false;
		this.flagIntList=false;
		this.flagIntUsuario=false;
		this.linkdescription='Ver Detalles';
		this.flagEditable=false;
		this.loaderror=false;
		this.flagformvisible=0;
  }

  ngOnInit() {
		//this.GetDetalle();
		this.QuejaCheck();
		this.frmdetalle_queja=new FrmDetalle_Queja();
		this.GetInteraccionList();
		this.SetSecTimerForm();
  }

	QuejaCheck(){
		let tempstr='';
		this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					this.setDetalle(this.lst_queja);
					//verificar estado para permitir edicion en formulario
					if(this.lst_queja[0]['id_estado_queja']>=401 && this.lst_queja[0]['id_estado_queja']<=401)
						this.flagEditable=true;
					else
						this.flagEditable=false;
				}else{
					console.log('Información de queja no pudo ser obtenida.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}				
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});	
	}

  DetallesInteraccion(){
	  if(this.flagIntList){
		  this.flagIntList=false;
		  this.linkdescription='Ver Detalles';
	  }else{
		  this.flagIntList=true;
		  this.linkdescription='Ocultar Detalles';
	  }
  }
/*
	GetDetalle(){
	  this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var todo = JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				console.log(todo);
				this.setDetalle(todo);
				this.flagformvisible++;
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.SetDetalleBlank();
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }
*/
  GetInteraccionList(){
	  let tempvalue=null;
	  this._interaccionService.getData(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempvalue=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				console.log(tempvalue);
				//if(tempvalue!=null || tempvalue !=''){
				if(tempvalue.length!=0){	
					this.ListInteraccion=tempvalue;
					console.log(this.ListInteraccion);
					this.flagIntUsuario=true;
				}else{
					this.ListInteraccion=[];
					this.flagIntUsuario=false;
				}
				this.flagformvisible++;
				this.SetSecTimerVarLoad();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }

	setDetalle(data){
		if(typeof data["0"] === 'undefined'){	
			this.SetDetalleBlank();
		}else{
			this.frmdetalle_queja.id_queja=data["0"].id_queja;
			this.frmdetalle_queja.quejanumero=data["0"].quejanumero;
			this.frmdetalle_queja.motivo=data["0"].motivo;
			this.frmdetalle_queja.detalle_queja=data["0"].detalle_queja;
			this.frmdetalle_queja.fecha_queja=data["0"].fecha_queja;
			this.frmdetalle_queja.solicita_que=data["0"].solicita_que;	
			this.frmdetalle_queja.id_consumidor=data["0"].id_consumidor;	
			this.frmdetalle_queja.id_proveedor=data["0"].id_proveedor;
			this.frmdetalle_queja.tipotelefono= data["0"].tipotelefono;	
			this.frmdetalle_queja.tipoemail= data["0"].tipoemail;
			this.frmdetalle_queja.nombre_consumidor= data["0"].nombre_consumidor;			
		}
		console.log(this.frmdetalle_queja);
	}

	SetDetalleBlank(){
		this.frmdetalle_queja.id_queja=null;
		this.frmdetalle_queja.motivo=null;
		this.frmdetalle_queja.detalle_queja=null;
		this.frmdetalle_queja.fecha_queja=null;
		this.frmdetalle_queja.solicita_que=null;
		this.frmdetalle_queja.id_consumidor=null;
		this.frmdetalle_queja.id_proveedor=null;
		this.frmdetalle_queja.nombre_consumidor=null;
		this.frmdetalle_queja.tipotelefono=[];
		this.frmdetalle_queja.tipoemail=[];
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerVarLoad(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flaglink=true);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<2){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	CloseandGo(){
		let localresp:FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		this.dialogRef.close(localresp);
	}
  
	closeDialog() {
		this.dialogRef.close();
	}

	closeMoveDialog(){
		this.dialogRef.close(this.data.NoQueja);
	}

}
