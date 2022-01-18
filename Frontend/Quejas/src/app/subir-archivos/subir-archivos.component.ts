import { Component, OnInit, Input, AfterViewInit, OnDestroy } from '@angular/core';
import { QuejaService } from '../shared/queja.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { Inject } from '@angular/core';
import { FrmDetalle_Queja} from '../atencion-consumidor-const';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { SeguridadService } from "../shared/seguridad.service";

@Component({
  selector: 'app-subir-archivos',
  templateUrl: './subir-archivos.component.html',
  styleUrls: ['./subir-archivos.component.css'],
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
export class SubirArchivosComponent implements OnInit {

  lst_queja:FrmDetalle_Queja[];
  flagformvisible;
  flagEditable:boolean;
  loaderror:boolean;
  
  constructor(private _quejaService: QuejaService, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<SubirArchivosComponent>, private _seguridadService:SeguridadService) { 
		this.flagEditable=false;
		this.loaderror=false;
		this.flagformvisible=0;
  }

  ngOnInit() {		
		console.log(this.data);
		this.GetQuejaList(this.data.NoQueja);
		this.SetSecTimerForm();
  }
  
	closeDialog() {
		this.dialogRef.close();
	}

	GetQuejaList(idqueja){
	  let tempstr='';
	  this._quejaService.getDataQueja(idqueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					console.log(this.lst_queja);
					//verificar estado para permitir edicion en formulario
					if( this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']) )
						//si es rol 3 administrador, puede editar
						if(this.data.Rol == 3)
							this.flagEditable=true;
						else{
							//si es otro rol, revisar si la queja esta asignada a este usuario
							if(this.lst_queja[0]['usuario_asignado'] == this.data.Usuario)
								this.flagEditable=true;
							else
								this.flagEditable=false;
						}
					else
						this.flagEditable=false;
					this.flagformvisible++;
				}else{
					console.log('Información de queja no pudo ser obtenida.');
				}				
			}else{
				console.log('Rest service response ERROR.');
			}		
		},(error)=>{
			console.log(error);
		});
	}
	
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<1){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
}
