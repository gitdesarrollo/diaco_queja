import { Component, OnInit } from '@angular/core';
import {DepartamentoService} from './departamento.service';
import {Dpto} from './dpto'

@Component({
  selector: 'app-departamentotest',
  templateUrl: './departamentotest.component.html',
  styleUrls: ['./departamentotest.component.css']
})
export class DepartamentotestComponent implements OnInit {
  deptos:Dpto[];
  constructor(private _dptoService:DepartamentoService) { }

  ngOnInit() {
	var internal;
	this._dptoService.getUsers().subscribe((deptos)=>{
		this.deptos=JSON.parse('['+deptos.value.replace(/¬/g, '"').slice(0, -1) +']');
		//internal='['+deptos.value.replace(/¬/g, '"').slice(0, -1) +']';	
		//console.log(internal);
		//console.log(internal.replace(/¬/g, '"'));
	},(error)=>{
		console.log(error);
	});
  }

}
