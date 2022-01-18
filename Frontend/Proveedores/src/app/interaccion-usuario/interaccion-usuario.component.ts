import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { InteraccionService } from './interaccion.service';
import { QuejaService } from '../shared/queja.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import { Inject } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { FrmInteraccion } from '../conc-virt-const'

@Component({
  selector: 'app-interaccion-usuario',
  templateUrl: './interaccion-usuario.component.html',
  styleUrls: ['./interaccion-usuario.component.css'],
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
export class InteraccionUsuarioComponent implements OnInit {
  ListInteraccion:FrmInteraccion[];
  myForm: FormGroup;
  mensajeCtrl: FormControl;
  flagInsertInfo: boolean;
  flagInfoError: boolean;
  flagEmailSent: boolean;
  flagEmailError: boolean;
  flagSaveBtn: boolean;
  flagformvisible;lst_queja;
  flagEditable:boolean;
  loaderror:boolean;
  
  chkboxlist = [
    { id: 0, name: 'Permite Agregar Archivos', ctrln:'permite_archivos' },
    { id: 1, name: 'Permite Cambiar Datos', ctrln:'permite_cambiodatos' }
  ];  
  constructor(private _interaccionService:InteraccionService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<InteraccionUsuarioComponent>, private _quejaService:QuejaService) { 
		  this.flagSaveBtn=true;
		  this.flagInsertInfo = false;
		  this.flagInfoError = false;
		  this.flagEmailSent = false;
		  this.flagEmailError = false;
		  this.flagEditable=false;
		  this.loaderror=false;
		  this.flagformvisible=0;
  }
	  
	  /*ngAfterViewInit(){
		console.log('length: '+this.ListBitacora );
	  }*/

  checkboxchange(i){
	  //this.myForm.get('chkboxlist')['controls'][0].setValue(true);	
		//this.myForm.get('chkboxlist')['controls'][1].setValue(true);		
		if(	!this.myForm.get('chkboxlist')['controls'][0].value && !this.myForm.get('chkboxlist')['controls'][1].value )
			this.myForm.get('chkboxlist')['controls'][i].setValue(true);
  }

  ngOnInit() {	  
	  this.mensajeCtrl =  new FormControl('',Validators.required);
	  
	  this.myForm = new FormGroup({
		mensaje: this.mensajeCtrl,
		chkboxlist: new FormArray([])
	  });
	  this.addCheckboxes();
	  //console.log(this.data);
	  this.GetInteraccionList();
	  
	  console.log(this.data);
	  this.QuejaCheck();
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
	
  private addCheckboxes() {
    this.chkboxlist.map((o, i) => {
      const control = new FormControl(true); 
      (this.myForm.controls.chkboxlist as FormArray).push(control);
    });
  }
		
  onSubmit() {
	  if (this.myForm.valid) {
		//console.log(this.myForm.value);
		this.SaveInteraccion();
	  }
  }

  GetInteraccionList(){
	  this._interaccionService.getData(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				let tempvalue=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
				if(tempvalue!=null){
					this.ListInteraccion=tempvalue;
					console.log(this.ListInteraccion);
				}else{
					this.ListInteraccion=[];
				}
				this.flagformvisible++;
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
  
  SaveInteraccion(){
	  this.flagSaveBtn=false;
	  //this.SetSecTimerDissapButton();
	  this._interaccionService.addData(this.mensajeCtrl.value, this.myForm.get('chkboxlist')['controls'][0].value, this.myForm.get('chkboxlist')['controls'][1].value, this.data.NoQueja ).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK' || retvalue["reason"] == 'EMAILFAIL'){
				console.log(retvalue);
				this.SetSecTimerUpdateList();
				this.flagInsertInfo=true;
				this.SetSecTimerInsert();
				this.myForm.reset();
				this.CleanForm();
				if(retvalue["reason"] == 'OK'){
					this.flagEmailSent=true;
					this.SetSecTimerMailOk();
				}else{
					this.flagEmailError=true;
					this.SetSecTimerMailError();
				}
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.SetSecTimerDissapButton();	
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerDissapButton();
			this.SetSecTimerInfoError();
		});
  }
  
	SetSecTimerUpdateList(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.GetInteraccionList());
	}
	
	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerMailOk(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagEmailSent=false);
	}
	SetSecTimerMailError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagEmailError=false);
	}
	SetSecTimerDissapButton(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.flagSaveBtn=true);
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
		this.mensajeCtrl.setValue("");
		this.myForm.get('chkboxlist')['controls'][0].setValue(true);	
		this.myForm.get('chkboxlist')['controls'][1].setValue(true);			
	}
	
	closeDialog() {
		this.dialogRef.close();
	}
	
  
}
