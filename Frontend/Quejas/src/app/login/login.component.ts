import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SeguridadService } from "../shared/seguridad.service";
import { SubmitFormService } from "../shared/submit-form.service";
import { CatalogoService } from "../shared/catalogo.service";
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { timer } from 'rxjs';
import { linkPanel } from '../atencion-consumidor-const'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('Logintxt') logintxt: any;	
  myForm: FormGroup;
  UsrNameCtrl: FormControl;
  PsswordCtrl: FormControl;
  myForm2: FormGroup;
  EmailCtrl: FormControl;
  flagDBError:boolean;
  flagErrorPassword:boolean;
  flagErrorEmail:boolean;
  flagemailsent:boolean;
  flagErrorAccess:boolean;
  flagActiveScreen; 
  portallinkgrid =[];
  flagPortalLinks:boolean;

  constructor(private router: Router, private _seguridadService:SeguridadService, private _submitFormService:SubmitFormService, private _catalogoService:CatalogoService) { 
		this.flagDBError=false;
		this.flagErrorPassword=false;
		this.flagErrorEmail=false;
		this.flagemailsent=false;
		this.flagErrorAccess=false;
		this.flagPortalLinks=false;
		this.flagActiveScreen=1;
	}
	
	ngOnInit() {
		// const currentUser = JSON.parse(localStorage.getItem('tokenDiaco'));
		// console.log("curren ",currentUser)
		// if (currentUser) {
		// 	this.router.navigate(['/mainAtCon']);
		// }
	  this.portallinkgrid=[]
	  this.UsrNameCtrl =  new FormControl('',Validators.required);
	  this.PsswordCtrl =  new FormControl('',Validators.required);
	  this.myForm = new FormGroup({
		usrname: this.UsrNameCtrl,
		pssword: this.PsswordCtrl,
	  });
	  this.EmailCtrl =  new FormControl('',Validators.required);
	  this.myForm2 = new FormGroup({
		email: this.EmailCtrl,
	  });
	  this.SetSecTimerLoginTxt();
	  this.LoadLinksPortal();
  }

  GotoAS1(){
	  this.flagActiveScreen=1;
	//   console.log(this.flagActiveScreen);
  }

  GotoAS2(){
	  this.flagActiveScreen=2;
	//   console.log(this.flagActiveScreen);
  }

  ClaveCorreo(){
	  if(this.myForm2.valid){
		  this._seguridadService.EmailPassword(this.EmailCtrl.value).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					this.flagemailsent=true;
					this.SetSecTimerPasswordEmail();
				}else{
					// console.log('Rest service response ERROR.');
					this.flagErrorEmail=true;
					this.SetSecTimerPasswordError();
				}		
			},(error)=>{
				// console.log(error);
				this.flagErrorEmail=true;
				this.SetSecTimerPasswordError();
		  });
	  }
	//   console.log(this.flagActiveScreen);
  }

  SaveUserMove(frmsubmit){
	    let response=false;
	    //console.log(frmsubmit['code']);
		if(frmsubmit['code']=='1.0'){
			this.flagErrorPassword=false;
			response=this._submitFormService.SetInitial(frmsubmit);
			if(!response){
				this.flagErrorAccess=true;
				this.SetSecTimerAcceso();
			}
			//this.router.navigate(['/mainAtCon']);
		}else{
			this.flagErrorPassword=true;
		}
  }

  setToken(token){
	localStorage.setItem("tokenDiaco",JSON.stringify(token))
  }

	LogIn(){
		if (this.myForm.valid) {
		  let tempstr='';
		  let lst_login;
		  this._seguridadService.Login(this.UsrNameCtrl.value, this.PsswordCtrl.value).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					tempstr=retvalue['value'];
					if(tempstr != null)	{
						lst_login=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.setToken(lst_login[0])
						this.flagDBError=false;
						this.SaveUserMove(lst_login[0]);
					}else{
						// console.log('Rest service sin respuesta');
						this.flagDBError=true;
					}				
				}else{
					// console.log('Rest service response ERROR.');
					this.flagDBError=true;
				}		
			},(error)=>{
				// console.log(error);
				this.flagDBError=true;
			});
		}
	}

	LoadLinksPortal(){
		let tempstr='';
		this._catalogoService.getPortalLinks().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=JSON.parse('['+Data["value"].slice(0, -1) +']');
				
				if(tempstr != null && tempstr != '')	{
					for(var i=0;i<=7;i++){
						this.portallinkgrid.push({
							'link': tempstr[i]['link'],
							'nombre': tempstr[i]['nombre'],
							'activo': tempstr[i]['activo']
						});
					}
					
					this.flagPortalLinks=true;
				}
				
				this.flagDBError=false;
			}else{
				// console.log('Rest service response ERROR.');
				this.flagDBError=true;
			}
		},(error)=>{
			// console.log(error);
			this.flagDBError=true;
		});
	}	

	SetSecTimerPasswordError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagErrorEmail=false);
	}
	SetSecTimerPasswordEmail(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => {this.flagemailsent=false;this.GotoAS1();});
	}
	SetSecTimerAcceso(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => {this.flagErrorAccess=false;});
	}
	SetSecTimerLoginTxt(){
		const source = timer(1200);
		const subscribe = source.subscribe(val => this.logintxt.nativeElement.focus());
	}
}
