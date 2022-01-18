import { Component, OnInit } from '@angular/core';
import { ConfiguracionService } from '../shared/configuracion.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-param-generales',
  templateUrl: './param-generales.component.html',
  styleUrls: ['./param-generales.component.css'],
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
export class ParamGeneralesComponent implements OnInit {
	flagInfoError:boolean;
	loaderror:boolean;
	tabledata:boolean;
	flagBoton:boolean;
	flagSaved:boolean;
	btnvisible:boolean;
	flagChange:boolean;
	myForm: FormGroup;
	tituloCtrl: FormControl;
	generoCtrl: FormControl;
	nombreCtrl: FormControl;
	umaCtrl: FormControl;
	flagformvisible; list_conf;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ParamGeneralesComponent>, private _configuracionService: ConfiguracionService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.flagSaved=false;
	this.btnvisible=true;
	this.flagChange=false;
	this.flagformvisible=0;
  }

  ngOnInit() {
	  this.tituloCtrl =  new FormControl('',Validators.required);
	  this.generoCtrl =  new FormControl('',Validators.required);
	  this.nombreCtrl =  new FormControl('',Validators.required);
	  this.umaCtrl =  new FormControl('',Validators.required);
	  
	  this.myForm = new FormGroup({
		titulo: this.tituloCtrl,
		genero: this.generoCtrl,
		nombre: this.nombreCtrl,
		uma: this.umaCtrl,
	  });
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.GetList();
	  //this.generoCtrl.setValue('Masculino');
  }

  FormUpdate(){
	  this.flagChange=true;
  }

 onSubmit(){}

  GetList(){
	  this._configuracionService.getParamGeneral().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.list_conf=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.list_conf);
					this.tituloCtrl.setValue(this.list_conf[0]['titulo_ministerio']);
					this.generoCtrl.setValue(this.list_conf[0]['genero_ministro']);
					this.nombreCtrl.setValue(this.list_conf[0]['nombre_ministro']);
					this.umaCtrl.setValue(this.list_conf[0]['uma']);
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

	GuardarConfirmar(){
		this.btnvisible=false;
		//this.BtnVisibleAfterxsec();
		this._configuracionService.saveParamGeneral(this.umaCtrl.value, this.tituloCtrl.value, this.generoCtrl.value, this.nombreCtrl.value ).subscribe((retvalue)=>{
			console.log(retvalue);
			if(retvalue["reason"] == 'OK'){
				this.flagChange=false;
				this.flagSaved=true;	
				this.SetTimerSaved();
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
			this.BtnVisibleAfterxsec();		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
			this.BtnVisibleAfterxsec();
		});  
	}

	BtnVisibleAfterxsec(){
		const source = timer(500);
		const subscribe = source.subscribe(val => this.btnvisible=true);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	SetTimerSaved(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagSaved=false );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	txtTrim(pos){
		switch(pos){
			case 1:
				let vartitulo=this.tituloCtrl.value;
				this.tituloCtrl.setValue(vartitulo.substring(0, 14));
				break;
			case 2:
				let varnombre=this.nombreCtrl.value;
				this.nombreCtrl.setValue(varnombre.substring(0, 99));
				break;
		}
		this.FormUpdate();
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

	FormatMonto(){
		this.umaCtrl.setValue(this.decimals(this.umaCtrl.value));
		this.FormUpdate();
	}	

	decimals(cadena) {
		if(cadena ==null || cadena ==''){
			return '0.00';
		}else{
			let conv=Math.round(cadena * 100) / 100;
			return conv.toFixed(2);
		}
	}

}
