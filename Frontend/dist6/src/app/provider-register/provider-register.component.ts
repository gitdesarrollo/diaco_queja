import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import swal from 'sweetalert2'
import { Proveedorpcv } from '../shared/proveedorpcv.model';

@Component({
  selector: 'app-provider-register',
  templateUrl: './provider-register.component.html',
  styleUrls: ['./provider-register.component.css']
})
export class ProviderRegisterComponent implements OnInit {

  formProveedor: FormGroup;
  catchError: String;
  disables: boolean;

  constructor() { }

  ngOnInit() {
    this.disables = false
    this.initialInformation(null)
  }

  findByNITtoSAT(){
    this.disables = !this.disables
    if(this.formProveedor.value.nit == "" || this.formProveedor.value.nit == undefined){
      this.catchError = "Nit invalido o Nit Vacio"
      this.Alerta("Error", this.catchError);
    }
  }

  initialInformation(prov: Proveedorpcv){
    this.formProveedor = new FormGroup({
      nit: new FormControl(prov ? prov.nit : '', Validators.required)
    })
  }

  Alerta(tipo, mensaje) {
		if (mensaje == undefined || mensaje == null) {
			if (tipo == 'ErrorCorreo') {
				swal.fire({
					icon: 'error',
					title: 'Error datos obtenidos.',
					text: 'No tiene configurado su correo electrónico en SAT, por favor actualice sus datos en Agencia Virtual de SAT. Gracias.',
				});

			}

			if (tipo == 'ErrorTelefono') {
				swal.fire({
					icon: 'error',
					title: 'Error datos obtenidos.',
					text: 'No tiene configurado su número telefónico en SAT, por favor actualice sus datos en Agencia Virtual de SAT. Gracias.',
				});

			}

			if (tipo == 'exito') {
				swal.fire({
					icon: 'success',
					title: 'Su solicitud ha sido registrada exitosamente.',
					showConfirmButton: false,
					timer: 1500
				})
			}

		} else {

			if (tipo == 'Error') {
				swal.fire({
					icon: 'error',
					title: 'Error.',
					text: mensaje,
				});

			}

			if (tipo == 'Advertencia') {
				swal.fire({
					icon: 'warning',
					title: 'Advertencia.',
					text: mensaje,

				});

			}
		}
	}

}
