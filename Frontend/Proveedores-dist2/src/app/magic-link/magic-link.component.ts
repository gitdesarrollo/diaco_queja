import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { EmailService } from '../shared/email.service';
import { ActivatedRoute } from "@angular/router";
import { ConcvirtService } from '../resolver-conc-virt/concvirt.service';
import { timer } from 'rxjs';

@Component({
  selector: 'app-magic-link',
  templateUrl: './magic-link.component.html',
  styleUrls: ['./magic-link.component.css']
})
export class MagicLinkComponent implements OnInit {
	lst_mlink;mlink;LocalForm;
	flagInfoError:boolean;
	flagLinkExpirado: boolean;
	flagConfirmacionConsumidor: boolean;
	flagformvisible;
	flagEditable:boolean;
	flagResetPW:boolean;
	loaderror:boolean;
	
  constructor(private router: Router, private _emailService: EmailService, private route: ActivatedRoute, private _concvirtService: ConcvirtService) { 
	this.flagInfoError=false;
	this.flagLinkExpirado=false;
	this.flagConfirmacionConsumidor=false;
	this.flagEditable=false;
	this.loaderror=false;
	this.flagResetPW=false;
	this.flagformvisible=0;
  }

  ngOnInit() {
	    console.log(this.route.snapshot);
	    this.mlink=this.route.snapshot.paramMap.get("id");
		console.log('a:'+this.mlink);
		this.GetLink();
		this.SetSecTimerForm();
  }

  validacionformato(){
	  if( this.mlink.trim().length !=20 )
		  return false;
	  return true;
  }

  GetLink(){	  
		let tempstr=null;
		if(this.validacionformato()){
			this._emailService.getMLink(this.mlink).subscribe((Data)=>{
				if(Data['reason'] == 'OK'){
					tempstr=Data['value'];
					if(tempstr != null && tempstr !='')	{
						console.log(tempstr);
						this.lst_mlink=JSON.parse('['+tempstr.slice(0, -1) +']');
						this.flagInfoError=false;
						if(this.lst_mlink[0]['direccion'] != 'interno')
							window.location.href = this.lst_mlink[0]['direccion']+'/'+this.mlink;
						else
							this.InternalOperation(this.lst_mlink[0]['id_fuente'],this.mlink);
					}else{
						console.log('x2');
						this.lst_mlink=[];
						console.log('List magic link vacia.');
						this.flagformvisible++;
						this.flagLinkExpirado=true;
					}
					console.log('c:'+this.lst_mlink);
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
				}
			},(error)=>{
				console.log(error);
				this.flagInfoError=true;
			});
		}else{
			this.flagformvisible++;
			this.flagLinkExpirado=true;
		}
	}

	InternalOperation(id_fuente, magic_link){
		console.log(id_fuente, magic_link);
		if(id_fuente == 3){ // respuesta si, no verificacion conciliacion virtual (portal proveedor)
			let respuesta=this.route.snapshot.paramMap.get("param1");
			console.log('aqui:');
			console.log(respuesta);
			//guardar respuesta_con en diaco_verif_conciliacion_previa, actualizar magic link y deshabilitarlo
			this.SaveConfirmacionConsumidor(respuesta, magic_link);
			//actualizar conciliacion virtual en atencion al consumidor para mostrar este campo.
		}else if(id_fuente == 4){//link para reiniciar password y enviarlo por correo
			this.SaveResetPassword(magic_link);
		}
	}
	
	SaveConfirmacionConsumidor(respuesta, magic_link){
		let tempstr='';
		this._concvirtService.saveConfirmConsumidor(respuesta, magic_link).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.LocalForm=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					console.log(this.LocalForm);
					this.flagformvisible++;
					this.flagConfirmacionConsumidor=true;
				}
			}else{
				this.flagInfoError=true;
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
		});
	}

	SaveResetPassword(magic_link){
		let tempstr='';
		this._emailService.ResetPassword(magic_link).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.LocalForm=JSON.parse('['+tempstr.replace(/¬/g, '"').slice(0, -1) +']');
					console.log(this.LocalForm);
					this.flagformvisible++;
					this.flagResetPW=true;
				}
			}else{
				this.flagInfoError=true;
			}
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
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
