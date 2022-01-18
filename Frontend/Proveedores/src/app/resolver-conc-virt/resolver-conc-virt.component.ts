import { Component, OnInit,ViewChild,ElementRef,AfterViewInit,OnDestroy  } from '@angular/core';
import { LSTFILTRO_RESOLUCION, BaseCmbClass, List_Images, CmbResolucion } from '../conc-virt-const';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FileManagerService } from '../shared/file-manager.service';
import { QuejaService } from '../shared/queja.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Inject } from '@angular/core';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { FileReadUploadComponent }  from '../file-read-upload/file-read-upload.component';
import { CatalogoService } from '../shared/catalogo.service'
import { ConcvirtService } from './concvirt.service'

@Component({
  selector: 'app-resolver-conc-virt',
  templateUrl: './resolver-conc-virt.component.html',
  styleUrls: ['./resolver-conc-virt.component.css'],
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
export class ResolverConcVirtComponent implements OnInit { 
  cmbResolucion:CmbResolucion[];
  flagDBError: boolean;
  flagUpdateInfo: boolean;
  flagNoInfo: boolean;
  flagSaveBtn: boolean;
  flagEditable:boolean;
  loaderror:boolean;
  myForm: FormGroup;
  NotasCtrl: FormControl;
  ResolucionCtrl: FormControl;
  LocalForm;flagformvisible;lst_queja;resolucion_str;
  DataSaved:boolean;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _catalogoservice:CatalogoService, private _concvirtservice:ConcvirtService, private _quejaService:QuejaService,  public dialogRef: MatDialogRef<ResolverConcVirtComponent>) { 
	  this.flagDBError=false;
	  this.DataSaved=false;
	  this.flagNoInfo=false;
	  this.flagSaveBtn=true;  
	  this.flagEditable=false;
	  this.loaderror=false;
	  this.flagformvisible=0;
	  this.resolucion_str='';
  }

  ngOnInit() {
	  this.NotasCtrl =  new FormControl('',Validators.required);
	  this.ResolucionCtrl =  new FormControl('',Validators.required);
	  this.myForm = new FormGroup({
		notas: this.NotasCtrl,
		resolucion: this.ResolucionCtrl,
	  });
	  console.log(this.data);
	  this.cmbResolucion=[];
	  this.LoadCmbRes();
	  this.LocalForm=[];
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
					this.flagDBError=true;
					this.SetSecTimerInfoError();
				}				
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});	
	}

   ngAfterViewInit(){
		this.GetFormData(this.data.NoQueja);		
   }


  LoadCmbRes(){
		let tempstr='';
		this._catalogoservice.getData('resolver_queja').subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmbResolucion=JSON.parse('['+tempstr.slice(0, -1) +']');
				}else{
					this.cmbResolucion=[];
				}
				this.flagformvisible++;
				this.flagDBError=false;
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
  }

  onSubmit() {
	  if (this.myForm.valid) {
		console.log(this.myForm.value);
		this.SaveData();
	  }
  }


  LoadFrmData(){
	console.log(this.LocalForm);
	if(typeof this.LocalForm["0"] === 'undefined'){
		this.NotasCtrl.setValue('');
	}else{
		this.NotasCtrl.setValue(this.LocalForm["0"].notas);
		this.ResolucionCtrl.setValue(this.LocalForm["0"].resolucion);
		this.resolucion_str=this.getResolucionDescripcion(+this.LocalForm["0"].resolucion);
	}
  }

	SetSecTimerUpdate(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagUpdateInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagDBError=false);
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
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	GetFormData(noqueja){
		let tempstr='';
		this._concvirtservice.getData(noqueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.LocalForm=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					console.log(this.LocalForm);
					this.flagNoInfo=false;
				}else{
					this.LocalForm=[];
					this.flagNoInfo=true;
				}
				console.log('noinfo: '+this.flagNoInfo);
				this.flagDBError=false;
				this.flagformvisible++;
				this.LoadFrmData();
			}else{
				this.flagDBError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerInfoError();
		});
  }
  
  SaveData(){
	  this.flagSaveBtn=false;
	  //this.SetSecTimerDissapButton();
	  this._concvirtservice.addData(this.NotasCtrl.value,this.ResolucionCtrl.value, this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue['reason'] == 'OK'){
				console.log(retvalue);
				this.flagUpdateInfo=true;
				this.SetSecTimerUpdate();
				this.DataSaved=true;
				this.flagNoInfo=false;
				this.SetSecTimerDissapButton();
			}else{
				console.log('Rest service response ERROR.');
				this.flagDBError=true;
				this.SetSecTimerDissapButton();
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			console.log(error);
			this.flagDBError=true;
			this.SetSecTimerDissapButton();
			this.SetSecTimerInfoError();
		});
  }

  ngOnDestroy() {
	this.dialogRef.close(this.DataSaved);
  }	

  closeDialog() {
		this.dialogRef.close(this.DataSaved);
	}

  getResolucionDescripcion(id){
	  if (id === null)
		  return '';
	  var found=null;
	  for (var me of this.cmbResolucion) {
		  if (me['id_catalogo'] === id) { 
				found=me['descripcion_catalogo'];
		  }
   	  }	  
	  if (found != null){
		  return found;
	  }else{
		  return '';
	  }
  }

}
