import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FrmBitacora} from '../atencion-consumidor-const';
import { BitacoraService } from './bitacora.service';
import { QuejaService } from '../shared/queja.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import { Inject } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-bitacora-ac',
  templateUrl: './bitacora-ac.component.html',
  styleUrls: ['./bitacora-ac.component.css'],
  animations: [
		trigger('EnterLeave', [
	  state('flyIn', style({ transform: 'translateX(0)' })),
	  transition(':enter', [
		style({ transform: 'translateX(-100%)' }),
		animate('100ms 150ms ease-in')
	  ]),
	  transition(':leave', [
		animate('300ms ease-out', style({ transform: 'translateX(-100%)' }))
	  ])
	])]
})
export class BitacoraACComponent implements OnInit {
  ListBitacora:FrmBitacora[];
  //ListBitacora=LSTFKBITACORA;

  myForm: FormGroup;
  BitacoraCtrl: FormControl;
  ConsumidorCtrl: FormControl;
  flagInsertInfo: boolean;
  flagUpdateInfo: boolean;
  flagDeleteInfo: boolean;
  flagEdit: boolean;
  flagInfoError: boolean;
  flagCopyConsumidor: boolean;
  currentEditid;
  flagformvisible;lst_queja; usuarioid;
  flagEditable:boolean;
  loaderror:boolean;
  flagesAdmin:boolean;
  
  constructor(private _bitacoraService:BitacoraService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<BitacoraACComponent>, private _quejaService: QuejaService, private _seguridadService:SeguridadService) { 
	  this.flagInsertInfo = false;
	  this.flagUpdateInfo = false;
	  this.flagDeleteInfo = false;
	  this.flagInfoError = false;
	  this.flagEdit = false;
	  this.currentEditid = 0;
	  this.flagCopyConsumidor=true;
	  this.flagEditable=false;
	  this.loaderror=false;
	  this.flagformvisible=0;
	  this.usuarioid=0;
	  this.flagesAdmin=false;
  }
	  
	  /*ngAfterViewInit(){
		console.log('length: '+this.ListBitacora );
	  }*/

  ngOnInit() {	  
	  this.BitacoraCtrl =  new FormControl('',Validators.required);
	  this.ConsumidorCtrl =  new FormControl('');
	  
	  this.myForm = new FormGroup({
		bitacora: this.BitacoraCtrl,
		consumidor: this.ConsumidorCtrl,
	  });
	  
	  //console.log(this.data);
	  this.GetBitacoraList(this.data.NoQueja);
	  this.GetQuejaList();
	  this.SetSecTimerForm();
  }
  
  GetQuejaList(){
	  let tempstr='';
	  this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					this.flagformvisible++;
					//verificar estado para permitir edicion en formulario
					if(this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']))
						//si es rol 3 administrador, puede editar
						if(this.data.Rol == 3){
							this.flagEditable=true;
							this.flagesAdmin=true;
						}else{
							//si es otro rol, revisar si la queja esta asignada a este usuario
							this.usuarioid=this.data.Usuario;
							if(this.lst_queja[0]['usuario_asignado'] == this.data.Usuario){
								this.flagEditable=true;
							}else{
								this.flagEditable=false;
							}
						}
					else
						this.flagEditable=false;
				}else{
					console.log('Informaci??n de queja no pudo ser obtenida.');
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
  
  onSubmit() {
	  if (this.myForm.valid && !this.flagEdit) {
		//console.log(this.myForm.value);
		this.SaveBitacora(this.BitacoraCtrl.value,this.ConsumidorCtrl.value,this.data.NoQueja);
	  }
  }

    UpdateConsumidor(event: any){
	  if(this.flagCopyConsumidor)
		this.ConsumidorCtrl.setValue(this.BitacoraCtrl.value);
  }
   
  ConsumidorTouched(){
	this.flagCopyConsumidor=false;
  }	  

  CheckConsumidor(){
	  if(this.BitacoraCtrl.value == this.ConsumidorCtrl.value)
		  this.flagCopyConsumidor=true;
	  else
		  this.flagCopyConsumidor=false;
  }

  
  GetBitacoraList(idqueja){
	  this._bitacoraService.getData(idqueja,this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				this.ListBitacora=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				this.ResetID();
				console.log(this.ListBitacora);
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.flagformvisible++;	
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }
  
  SaveBitacora(bitacora, consumidor, no_queja){
	  this._bitacoraService.addData(bitacora,consumidor,no_queja,this.data.Flujo).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				console.log(retvalue);
				this.SetSecTimerUpdateList();
				this.flagInsertInfo=true;
				this.SetSecTimerInsert();
				this.myForm.reset();
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
  
  EnableEdit(id, bitacora, consumidor){
	  //console.log('length: '+this.ListBitacora.length );
	  this.flagEdit=true;
	  this.currentEditid=id;
	  this.BitacoraCtrl.setValue(bitacora);	
  	  this.ConsumidorCtrl.setValue(consumidor);	
  }
  
	CancelEdit(){
		this.flagEdit=false;
		this.currentEditid = 0;
		this.CleanForm();
	}
	
  DeleteBitacora(id){
		if(confirm("??Est?? seguro que quiere eliminar este elemento?")) {
			this._bitacoraService.deleteData(id,this.data.NoQueja,this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList();
					this.flagDeleteInfo=true;
					this.SetSecTimerDelete();
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
  }

  UpdateBitacora(){
	  if (this.myForm.valid) {	
			console.log('id actual update: '+this.currentEditid);
			this._bitacoraService.editData(this.currentEditid, this.BitacoraCtrl.value,this.ConsumidorCtrl.value,this.data.NoQueja,this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.SetSecTimerUpdateList();
					this.flagUpdateInfo=true;
					this.SetSecTimerUpdate();
					this.CancelEdit();
					this.myForm.reset();
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
  }

  ResetID(){
	for(let i=0; i<this.ListBitacora.length; i++){
		this.ListBitacora[i].correlativo = (this.ListBitacora.length-i); //use i instead of 0
	}  
  }

	SetSecTimerUpdateList(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.GetBitacoraList(this.data.NoQueja));
	}
	
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerUpdate(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdateInfo=false);
	}
	SetSecTimerDelete(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDeleteInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
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
	
	CleanForm(){
		this.BitacoraCtrl.setValue("");	
		this.ConsumidorCtrl.setValue("");	
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

}
