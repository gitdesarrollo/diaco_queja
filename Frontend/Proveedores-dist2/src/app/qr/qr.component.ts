import { Component, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { ConfiguracionService } from "../shared/configuracion.service";
import { Inject } from '@angular/core';

@Component({
  selector: 'app-qr',
  templateUrl: './qr.component.html',
  styleUrls: ['./qr.component.css']
})
export class QrComponent implements OnInit {
	str_qrpath; lst_path;
	flagInfoError:boolean;	

	constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<QrComponent>, private _configuracionService:ConfiguracionService) { 
		this.str_qrpath='';
		this.flagInfoError=false;
	}

	ngOnInit() {
		this.GetQrpath();
	}

	closeDialog(){
		this.dialogRef.close();
	}

	GetQrpath(){
		this._configuracionService.getQR().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.lst_path=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.str_qrpath=this.lst_path[0]['path']+this.data['Nit'];
					console.log(this.lst_path, this.str_qrpath);			
				}
			}else{
				console.log('Rest service response ERROR.');
				this.flagInfoError=true;
			}		
		},(error)=>{
			console.log(error);
			this.flagInfoError=true;
		});
	}

}
