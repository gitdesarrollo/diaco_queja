import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { ConfiguracionService } from "../shared/configuracion.service";

@Component({
  selector: 'app-cambiar-password',
  templateUrl: './cambiar-password.component.html',
  styleUrls: ['./cambiar-password.component.css'],
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
export class CambiarPasswordComponent implements OnInit {
	MyForm: FormGroup;
	claveanteriorCtrl:FormControl;
	clavenueva1Ctrl: FormControl;
	clavenueva2Ctrl: FormControl;
	clavesiguales:boolean;
	minimocumplido:boolean;
	flagBoton:boolean;
	flagInsert:boolean;
	flagInfoError:boolean;
	flagClave:boolean;
	
  constructor( public dialogRef: MatDialogRef<CambiarPasswordComponent>, private _configuracionService: ConfiguracionService) { 
	this.claveanteriorCtrl =  new FormControl('',Validators.required);
	this.clavenueva1Ctrl =  new FormControl('',Validators.required);
	this.clavenueva2Ctrl =  new FormControl('',Validators.required);
	this.MyForm = new FormGroup({
		claveanterior: this.claveanteriorCtrl,
		clavenueva1: this.clavenueva1Ctrl,
		clavenueva2: this.clavenueva2Ctrl,
	});
	this.clavesiguales=false;
	this.minimocumplido=false;
	this.flagBoton=true;
	this.flagInsert=false;
	this.flagInfoError=false;
	this.flagClave=false;
  }

  ngOnInit() {
	  
  }

  CheckClavesIguales(){
	  if(this.clavenueva1Ctrl.value != this.clavenueva2Ctrl.value){
			this.clavesiguales=false;
	  }else{
			this.clavesiguales=true;
	  }
	  var cn1=this.clavenueva1Ctrl.value;
	  var cn2=this.clavenueva2Ctrl.value;
	  if(cn1!=null && cn2!=null){
		  if(cn1.length >4 && cn2.length >4){
				this.minimocumplido=true;
		  }else{
				this.minimocumplido=false;
		  }
	  }else{
		  this.minimocumplido=false;
	  }
  }

	closeDialog(){
		this.dialogRef.close();
	}	

	onSubmit(){
		
	}

	Save(){
	  if (this.MyForm.valid) {	
			this.flagBoton=false;
			this._configuracionService.cambiaPW(this.clavenueva1Ctrl.value, this.claveanteriorCtrl.value).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					console.log(retvalue);
					this.flagInsert=true;
					this.SetSecTimerInsert();
					this.MyForm.reset();
				}else if(retvalue["reason"] == 'ERR_CLAVEVIEJA'){
					this.flagClave=true;
					this.SetSecTimerClave();
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}	
				this.flagBoton=true;
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.flagBoton=true;
			});
	  }
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsert=false);
	}
	SetSecTimerClave(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagClave=false);
	}
}
