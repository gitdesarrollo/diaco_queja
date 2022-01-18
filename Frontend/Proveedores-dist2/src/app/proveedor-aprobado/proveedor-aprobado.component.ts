import { Component, OnInit } from '@angular/core';
import { ConfiguracionService } from "../shared/configuracion.service";
import { ProveedorService } from "../main-conc-virtual/proveedor.service";
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-proveedor-aprobado',
  templateUrl: './proveedor-aprobado.component.html',
  styleUrls: ['./proveedor-aprobado.component.css']
})
export class ProveedorAprobadoComponent implements OnInit {
	proveedor_nombre; str_qrpath; lst_path; lst_proveedor;
	flagInfoError:boolean;
	flagLoaded:boolean;
	
  constructor(private _configuracionService:ConfiguracionService, private _proveedorService:ProveedorService,private route: ActivatedRoute) { 
	this.proveedor_nombre='';
	this.str_qrpath='';
	this.flagInfoError=false;
	this.flagLoaded=false;
  }

  ngOnInit() {
	  console.log(this.route.snapshot.paramMap.get("nit"));
	  this.getProvAprobarxNitProv(this.route.snapshot.paramMap.get("nit"));
  }
  
  GetQrpath(nit){
	    var tempstr='';
		this._configuracionService.getQR().subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.lst_path=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.str_qrpath=this.lst_path[0]['path']+nit;
					console.log(this.lst_path, this.str_qrpath);
					this.flagLoaded=true;					
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
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

	getProvAprobarxNitProv(NitProv){
		this._proveedorService.getProvAprobarxNitProv(NitProv).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				var tempstr=retvalue['value'];
				if(tempstr != null && tempstr != '')	{
					this.lst_proveedor=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.proveedor_nombre=this.lst_proveedor[0]['nombre'];
					this.GetQrpath(this.lst_proveedor[0]['nit']);
					console.log(this.lst_proveedor);			
				}else{
					console.log('Rest service response ERROR.');
					this.flagInfoError=true;
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
