import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-muestra-registro',
  templateUrl: './muestra-registro.component.html',
  styleUrls: ['./muestra-registro.component.css']
})
export class MuestraRegistroComponent implements OnInit {
  report_id;
  report_reg;
  
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
	  this.report_id = this.route.snapshot.paramMap.get("id")
	  this.report_reg = this.route.snapshot.paramMap.get("reg")
  }

}
