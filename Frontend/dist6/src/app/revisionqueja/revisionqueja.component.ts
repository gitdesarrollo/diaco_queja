import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { Subscription, Subject,Observable, of } from 'rxjs';
import { Router, ActivatedRoute, Params  } from '@angular/router';
import { RevisionQuejasService } from '../shared/revisionquejas.service';
import { RevisionQueja } from '../shared/revisionqueja.model';



import {MatDialog, MatDialogConfig} from '@angular/material';

import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { ResponseRs } from '../shared/response-rs.model';
import {isUndefined} from 'util';

@Component({
  selector: 'app-revisionqueja',
  templateUrl: './revisionqueja.component.html',
  styleUrls: ['./revisionqueja.component.scss']
})
export class RevisionquejaComponent implements OnInit {
  quejas: RevisionQueja[]; 
  revisionQuejaForm: FormGroup;
  quejaOperationSubscription: Subscription;
	
	success: boolean = false;
	successFile : boolean = true;
	pnoQueja: string = "";
	

	//operation = new Subject<Quejaini>();

  //constructor() { }
   constructor(private router: Router,
   private dialog: MatDialog,
   private revisionQuejasService: RevisionQuejasService
	) { 
	}

	ngOnDestroy() {
	}

  ngOnInit() {
    this.initrevisionQuejaForm();
  }
  
  initrevisionQuejaForm() {
    this.revisionQuejaForm = new FormGroup({
      'noQueja': new FormControl('',Validators.required),
    });
  }
  
  public onSubmit() {
	  if (this.revisionQuejaForm.valid) {
		  this.pnoQueja = this.revisionQuejaForm.value.noQueja;
			  this.onDocumentoChanged();
			  this.revisionQuejasService.fetchData(this.revisionQuejaForm.value.noQueja).subscribe(
				  
			  (response) => {
				  //console.log(response);
				if (response.value) {
				  // inicializar formulario con valores predeterminados
				  this.quejas = response.value;
				   //console.log(response.value);
					//console.log(this.quejas);
			  }}
			);
	  }
  }

  onDismissClicked() {
	this.success = false;
	this.initrevisionQuejaForm();
  }

	 

  
	public onDocumentoChanged() {
		this.quejas = null;
  }
  
public openCustomDialog() {
    //console.log("this.customDialogForm.value");
	this.success = false;
  }  

}
