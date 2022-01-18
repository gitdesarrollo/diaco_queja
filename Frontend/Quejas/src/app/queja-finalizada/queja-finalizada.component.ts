import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-queja-finalizada',
  templateUrl: './queja-finalizada.component.html',
  styleUrls: ['./queja-finalizada.component.css']
})
export class QuejaFinalizadaComponent implements OnInit {
  @Input() formsent;
  FinoArchivo;lblTitulo;lblRazon;lblPOA;lblCategoria;lblDetalle;lblMonto;lblquejano;lblcomentario;lblconclusion;
  flagformvisible:boolean;

  constructor() { 
		this.flagformvisible=false;
  }

  ngOnInit() {
	  console.log(this.formsent);
	  this.FinoArchivo=0;
	  this.Selector();
  }
  
  Selector(){
	  if(this.formsent){
		if(this.formsent['id_motivo_queja_finalizada_poa']==''){
			this.FinoArchivo=1;
			this.lblTitulo='Queja Finalizada';
			this.lblPOA='';
			this.lblCategoria='';			
			this.lblMonto=0;
		}else{
			this.FinoArchivo=2;
			this.lblTitulo='Queja Archivada';
			this.lblPOA=this.formsent['motivo_queja_finalizada_poa'];
			this.lblCategoria=this.formsent['motivo_queja_finalizada_categoria'];
			this.lblMonto=+this.formsent['monto_recuperado'];
		}
		this.lblquejano=this.formsent['numeroqueja'];
		this.lblDetalle=this.formsent['detalles'];
		this.lblcomentario=this.formsent['comentario'];
		this.lblconclusion=this.formsent['conclusion'];
		this.lblRazon=this.formsent['motivo_queja_finalizada'];
	  }else{
			this.FinoArchivo=3;
	  }
	  this.flagformvisible=true;
  }

}
