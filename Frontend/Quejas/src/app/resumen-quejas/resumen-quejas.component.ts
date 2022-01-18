import { Component, OnInit } from '@angular/core';
import {FlatTreeControl} from '@angular/cdk/tree';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';
import { MainTableService } from '../tabla-main-at-con/main-table.service'
import { QuejaService } from '../shared/queja.service'
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';

	interface FoodNode {
	  name: string;
	  children?: FoodNode[];
	}

	/** Flat node with expandable and level information */
	interface ExampleFlatNode {
	  expandable: boolean;
	  name: string;
	  level: number;
	}

	interface Estado{
		nombre_estado:String;
		nombre_flujo:String;
	}

@Component({
  selector: 'app-resumen-quejas',
  templateUrl: './resumen-quejas.component.html',
  styleUrls: ['./resumen-quejas.component.css'],
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
export class ResumenQuejasComponent implements OnInit {
	/*TREE_DATA: FoodNode[] = [
	  {
		name: 'Fruit',
		children: [
		  {name: 'Apple'},
		  {name: 'Banana'},
		  {name: 'Fruit loops'},
		]
	  }, {
		name: 'Vegetables',
		children: [
		  {
			name: 'Green',
			children: [
			  {name: 'Broccoli'},
			  {name: 'Brussel sprouts'},
			]
		  }, {
			name: 'Orange',
			children: [
			  {name: 'Pumpkins'},
			  {name: 'Carrots'},
			]
		  },
		]
	  },
	];*/
	TREE_DATA: FoodNode[]=[{name:'Resumen', children:[]}];
	
	lst_quejasimple; lst_estados; flagInfoError;
	
	treeControl = new FlatTreeControl<ExampleFlatNode>(
      node => node.level, node => node.expandable);

	private transformer = (node: FoodNode, level: number) => {
		return {
		  expandable: !!node.children && node.children.length > 0,
		  name: node.name,
		  level: level,
		};
	  }

	treeFlattener = new MatTreeFlattener(
      this.transformer, node => node.level, node => node.expandable, node => node.children);

	dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

	hasChild = (_: number, node: ExampleFlatNode) => node.expandable;

  constructor(public dialogRef: MatDialogRef<ResumenQuejasComponent>, private _mainTableService:MainTableService, private _quejaService:QuejaService) {
    this.dataSource.data = this.TREE_DATA;
  }

  ngOnInit() {
		this.flagInfoError=false;
		this.GetQuejaEstados();
		this.dataSource.data = this.TREE_DATA;
  }

	closeDialog() {
		this.dialogRef.close();
	}

	go(){
		console.log(this.treeControl);
		console.log(this.treeFlattener);
	}

	GetQuejaListSimple(){
		let tempstr='';
		this._mainTableService.getSimpleQuejaTodo().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_quejasimple=JSON.parse('['+tempstr.slice(0, -1) +']');
				}else{
					this.lst_quejasimple=[];
				}
				console.log(this.lst_quejasimple);
				this.flagInfoError=false;
				this.CargarResumen();
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

	GetQuejaEstados(){
		let tempstr='';
		this._quejaService.getEstadosQueja().subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.lst_estados=JSON.parse('['+tempstr.slice(0, -1) +']');
				}else{
					this.lst_estados=[];
				}
				console.log(this.lst_estados);
				this.flagInfoError=false;
				this.GetQuejaListSimple();
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

	SearchEstado(id){
		  if (id === null)
			  return '';
		  var found=null;
		  for (var me of this.lst_estados) {
			  if (me['id_estado_queja'] === id) { 
					found=me;
			  }
		  }	  
		  if (found != null){
			  return found;
		  }else{
			  return '';
		  }
	}

	CargarResumen(){
		let lastflujo:string='';
		let lastestado:string='';
		let queja:string;
		let thisflujo:string='';
		let thisestado:string='';
		let thiselemento=[]; let ramaflujo:FoodNode; let ramaestado:FoodNode; let hoja:FoodNode;
		let cnt_flujo=-1; let cnt_estado=-1; let cnt_hoja=0;
		
		//this.lst_quejasimple.forEach(function(element)=> {
		this.lst_quejasimple.forEach(element=> {
			queja=element['quejanumero'];
			thiselemento=this.SearchEstado(element['id_estado_queja']);
			//SearchEstado(101);
			thisflujo=thiselemento['id_flujo'];
			thisestado=thiselemento['id_estado_queja'];
			if(thisflujo != lastflujo){
				ramaflujo={name: ('Flujo: '+thiselemento['nombre_flujo']), children:[]};
				//if(lastflujo===''){
					this.TREE_DATA[0]['children'].push(ramaflujo);
					cnt_flujo++;
					cnt_estado=-1;
				//}
				this.dataSource.data = this.TREE_DATA;
			}
			lastflujo=thisflujo;
			if(thisestado != lastestado){
				ramaestado={name: thiselemento['descripcion'], children:[]};
				//if(lastestado===''){
					this.TREE_DATA[0]['children'][cnt_flujo]['children'].push(ramaestado);
					//this.TREE_DATA[0]['children'][cnt_flujo]['name']='Flujo: '+thiselemento['nombre_flujo']+' ('+this.TREE_DATA[0]['children'][cnt_flujo]['children'].length+')';
					cnt_estado++;
					cnt_hoja=0;
				//}
				this.dataSource.data = this.TREE_DATA;
			}
			lastestado=thisestado;
			hoja={name: queja};
			this.TREE_DATA[0]['children'][cnt_flujo]['children'][cnt_estado]['children'].push(hoja);
			this.TREE_DATA[0]['children'][cnt_flujo]['children'][cnt_estado]['name']=thiselemento['descripcion']+' ('+this.TREE_DATA[0]['children'][cnt_flujo]['children'][cnt_estado]['children'].length+')';
			this.dataSource.data = this.TREE_DATA;
			cnt_hoja++;
			
		});
		this.dataSource.data = this.TREE_DATA;
	}

	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}

}

