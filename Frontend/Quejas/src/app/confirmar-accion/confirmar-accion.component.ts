import { Component, OnInit } from '@angular/core';
import { ConfirmarAccionService } from './confirmar-accion.service';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-confirmar-accion',
  templateUrl: './confirmar-accion.component.html',
  styleUrls: ['./confirmar-accion.component.css'],
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
export class ConfirmarAccionComponent implements OnInit {
  flagInfoError:boolean;
  loaderror:boolean;
  tabledata:boolean;
  flagBoton:boolean;
  flagNoSelection:boolean;
  btnvisible:boolean;
  myForm: FormGroup;
  flagformvisible; confacc_list;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<ConfirmarAccionComponent>, private _confirmarAccionService: ConfirmarAccionService) { 
	this.flagInfoError=false;
	this.loaderror=false;
	this.tabledata=false;
	this.flagBoton=true;
	this.flagNoSelection=false;
	this.btnvisible=true;
	this.flagformvisible=0;
  }

  ngOnInit() {
	  this.myForm = new FormGroup({
		confacc_list: new FormArray([])
	  });
	  console.log(this.data);
	  this.SetSecTimerForm();
	  this.GetList();
  }

  private addCheckboxes() {
    this.myForm.get('confacc_list')['controls']=[];
    this.confacc_list.map((o, i) => {
      const control = new FormControl(false); 
      (this.myForm.controls.confacc_list as FormArray).push(control);
    });
	this.tabledata=true;
  }

  seemyform(){
	  console.log(this.myForm);
	  console.log(this.confacc_list.length);
  }

 onSubmit(){}

  GetList(){
	  this._confirmarAccionService.getAll(this.data.Usuario).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.confacc_list=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.addCheckboxes();
					console.log(this.confacc_list);
					this.btnvisible=true;
				}else{
					this.confacc_list='';
					this.tabledata=false;
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
		let loc_str=this.GenerarListQueja();
		if(loc_str.length >0){
			this.btnvisible=false;
			//this.BtnVisibleAfterxsec();
			this._confirmarAccionService.addData(this.data.Usuario, loc_str ).subscribe((retvalue)=>{
				console.log(retvalue);
				if(retvalue["reason"] == 'OK'){
					this.UpdateAfterxsec();	
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
		}else{
			this.flagNoSelection=true;
			this.SetTimerNoSelection();
		}
	}

	UpdateAfterxsec(){
		const source = timer(1000);
		const subscribe = source.subscribe(val => this.GetList());
	}
	BtnVisibleAfterxsec(){
		const source = timer(1000);
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
	SetTimerNoSelection(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagNoSelection=false );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	closeDialog() {
		this.dialogRef.close();
	}

	GenerarListQueja(){
		let str_queja_list='';
		let i=0;
		for (let thisitem of this.myForm.get('confacc_list')['controls']){
			if(thisitem.value){
				if(str_queja_list.length==0)
					str_queja_list=''+this.confacc_list[i].id_queja;
				else
					str_queja_list=str_queja_list+', '+this.confacc_list[i].id_queja;
			}
			i++;
		}
		console.log(str_queja_list.length);
		console.log(str_queja_list);
		return str_queja_list;
	}

}
