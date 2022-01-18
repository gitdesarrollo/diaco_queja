import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material';

@Component({
  selector: 'app-guia-registros',
  templateUrl: './guia-registros.component.html',
  styleUrls: ['./guia-registros.component.css']
})
export class GuiaRegistrosComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<GuiaRegistrosComponent>) { }

  ngOnInit() {
  }

	closeDialog() {
		this.dialogRef.close();
	}

}
