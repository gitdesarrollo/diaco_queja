import { Component, OnInit, OnDestroy } from '@angular/core';
import { LSTCMB_PRIMERAAUDIENCIA, BaseCmbClass, FrmProgramaAudiencia, FrmAudiencia, CmbCatalogo, FrmDetalle_Queja, CmbResultadoAudiencia, Frm4Registros, FormResponse, MSG_REGISTRO_DESHABILITADO, data } from '../atencion-consumidor-const'
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { timer } from 'rxjs';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Inject } from '@angular/core';
import { AudienciaService } from '../shared/audiencia.service';
import { QuejaService } from '../shared/queja.service';
import { CatalogoService } from '../shared/catalogo.service';
import { RegistrosService } from '../shared/registros.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import { DatePipe } from '@angular/common';
import { SeguridadService } from "../shared/seguridad.service";
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import esLocale from '@fullcalendar/core/locales/es';
import swal from 'sweetalert2'
import * as moment from 'moment';

@Component({
  selector: 'app-segunda-audiencia',
  templateUrl: './segunda-audiencia.component.html',
  styleUrls: ['./segunda-audiencia.component.css'],
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
export class SegundaAudienciaComponent implements OnInit {
	ProgramarForm: FormGroup;
	FechaCtrl: FormControl;
	HoraCtrl: FormControl;
	CerrarForm: FormGroup;
	ResultadoCtrl: FormControl;
	list_filtro:BaseCmbClass[];
	flagInsertInfo:boolean;
	flagInfoError:boolean;
	lst_audiencia:FrmAudiencia[];
	lst_programado:FrmProgramaAudiencia[];
	lbl_dpto;lbl_muni;lbl_sede;id_audiencia;
	cmb_resultado:CmbResultadoAudiencia[];
	flag_Programado:boolean;
	flag_Resultado:boolean;
	flagRegError:boolean;
	lst_queja:FrmDetalle_Queja[];
	registros:Frm4Registros[];
    routerlink1;
	linkdescription1;
    routerlink2;
	linkdescription2;
    routerlink3;
	linkdescription3;
    routerlink4;
	linkdescription4;
	routerlink5;
	linkdescription5;
	routerlink6;
	linkdescription6;
	lbl_resultado;
	flagformvisible;
	flagEditable:boolean;
	loaderror:boolean;
	flagMainUpdate:boolean;
	flagTimeError:boolean;
	flagValidadorRegistros: boolean;
	flagBoton: boolean;
	flagBoton2: boolean;
	flagBoton3: boolean;
	msg_deshabilitado;
	loc_pagina;
	//variables del calendario
	events: data[];
	options: any;
	btnPrimeraAudiencia: boolean;
	btnSegundaAudiencia: boolean;
	tblPrimeraAudiencia: boolean;
	tblSegundaAudiencia: boolean;
	registrosJuridico: string[];
	
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private _audienciaService:AudienciaService, private _quejaService: QuejaService, private _catalogoService: CatalogoService, public dialogRef: MatDialogRef<SegundaAudienciaComponent>, private _registrosservice:RegistrosService, private datePipe: DatePipe, private _seguridadService:SeguridadService) { 
		this.flagInsertInfo=false;
		this.flagInfoError=false;
		this.flag_Programado=false;
		this.flag_Resultado=false;
		this.lbl_dpto='';
		this.lbl_muni='';
		this.lbl_sede='';
		this.id_audiencia=0;
		this.flagEditable=false;
		this.loaderror=false;
		this.flagformvisible=0;
		this.flagMainUpdate=false;
		this.flagTimeError=false;
		this.flagRegError=false;
		this.flagValidadorRegistros=false;
		this.flagBoton=true;
		this.flagBoton2=true;
		this.flagBoton3=true;
		this.msg_deshabilitado=MSG_REGISTRO_DESHABILITADO;
		this.loc_pagina=0;
		this.btnPrimeraAudiencia = false;
		this.btnSegundaAudiencia = false;
		this.tblPrimeraAudiencia = false;
		this.tblSegundaAudiencia = false;
  }

  ngOnInit() {
	this.list_filtro = LSTCMB_PRIMERAAUDIENCIA;
	this.FechaCtrl =  new FormControl('',Validators.required);
	this.HoraCtrl =  new FormControl('',Validators.required);
	this.ProgramarForm = new FormGroup({
		fecha: this.FechaCtrl,
		hora: this.HoraCtrl,
	});
	this.ResultadoCtrl =  new FormControl('',Validators.required);
	this.CerrarForm = new FormGroup({
		resultado: this.ResultadoCtrl,
	});
	this.registros=[];
	this.registrosJuridico = []
	this.Leer4Registros(true);
	this.LeerRegistrosJuridico(true);
	this.LoadCmb();
	this.GetAudienciaList();
	this.GetQuejaList();
	this.SetSecTimerForm();
	this.ValidadorRegistros();
	
		//calendario
		this.validarCalendario();

		this.options = {
			plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
			eventTimeFormat: { // like '14:30:00'
				hour: '2-digit',
				minute: '2-digit',
				meridiem: false
			  },
			locale: esLocale,
			defaultDate: new Date(),
			header: {
				left: 'prev,next',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
				// right: 'month,agendaWeek,agendaDay'
			},
			dateClick: (e) => {

			},
			eventClick: function (info) {
				swal.fire({
					icon: 'info',
					html:
						'<div style="text-align: left;">' +
						'No. Queja: <b>' + info.event.extendedProps.no_queja + '-' + info.event.extendedProps.año + '</b><br> ' +
						'Descripción de Queja: <b>' + info.event.extendedProps.description + '</b><br> ' +
						'Auxiliar: <b>' + info.event.extendedProps.user + '</b> <br>' +
						'Audiencia: <b>' + info.event.extendedProps.tipoAudiencia + '</b> <br>' +
						'</div>',
						title: '<strong>No. Queja <u>' + info.event.extendedProps.no_queja + '-' + info.event.extendedProps.año + '</u></strong>',
					showConfirmButton: true
					//timer: 1500
				})
			}
		}
		//fin calendario
  }

  	/**
	 * alruanoe
	 * 24/04/2020
	 * metodo para validar calendario a 60 dias a partir del dia actual
	 */
	validarCalendario() {
		var today = new Date(Date.now());
		var fin = moment(today).add(60, 'day').format('LLL');

		if(this.HoraCtrl.value!=null){
			var td = new Date(this.convertDate());
		}else{
			td=today;
		}
		
		if (today > td) {
			this.flagTimeError = true;
			this.SetSecTimerTimeError();
		} else {
			this._audienciaService.ValidarCalendario(this.data.Usuario, this.data.NoQueja, today, fin).subscribe((retvalue
			) => {
				if (retvalue["reason"] == 'OK') {
					this.events = retvalue['value'] as data[];

				} else {

				}

			}, (error) => {

			});

		}
	}


	LeerRegistrosJuridico(blank: boolean){
		let empty=false;
		this.registrosJuridico = []
		if(blank){
		}else{
			if(typeof this.registros["0"] === 'undefined')
				empty=true;
			if(!empty){
				if(this.registros[0]['cedula_citacion_jur_con_id'] != null){
					this.tblSegundaAudiencia = true
					this.registrosJuridico.push('MuestraRegistro/3/'+this.registros[0]['cedula_citacion_jur_con_id']);
					// this.linkdescription1=this.registros[0]['cedula_citacion_jur_con_codigo'];
				}
				if(this.registros[0]['cedula_citacion_jur_pro_id'] != null){
					this.registrosJuridico.push('MuestraRegistro/4/'+this.registros[0]['cedula_citacion_jur_pro_id']);
					// this.linkdescription2=this.registros[0]['cedula_citacion_jur_pro_codigo'];
				}

                if(this.registros[0]['cedula_notificacion_citacion_jur_con_id'] != null){
					this.registrosJuridico.push('MuestraRegistro/4/'+this.registros[0]['cedula_notificacion_citacion_jur_con_id']);
					// this.linkdescription3=this.registros[0]['cedula_notificacion_citacion_jur_con_codigo'];
				}
                if(this.registros[0]['cedula_notificacion_citacion_jur_pro_id'] != null){
					this.registrosJuridico.push('MuestraRegistro/4/'+this.registros[0]['cedula_notificacion_citacion_jur_pro_id']);
					// this.linkdescription4=this.registros[0]['cedula_notificacion_citacion_jur_pro_codigo'];
				}

				if(this.registros[0]['cedula_notificacion_re_correo_id'] != null){
					this.registrosJuridico.push('MuestraRegistro/5/'+this.registros[0]['cedula_notificacion_re_correo_id']);
					// this.linkdescription5=this.registros[0]['cedula_notificacion_re_correo_codigo'];
				}
				if(this.registros[0]['cedula_notificacion_cit_correo_id'] != null){
					this.registrosJuridico.push('MuestraRegistro/6/'+this.registros[0]['cedula_notificacion_cit_correo_id']);
					// this.linkdescription6=this.registros[0]['cedula_notificacion_cit_correo_codigo'];
				}
			}
			
		}
		// this.MostrarMensaje("array ", this.registrosJuridico )
		
	}

	getTotalAudiencia(){
		let temp = ''
		this._audienciaService.getTotalAudiencia(this.data.NoQueja).subscribe(response => {
			if (response['value']){
				this.lst_audiencia = JSON.parse('[' + response["value"].slice(0, -1) + ']');
				this.lst_audiencia.forEach(v => {
					if(Number(v.es_primera_seg_audiencia) === 1){
						this.btnPrimeraAudiencia = true
						// this.tblPrimeraAudiencia = true
					}
					
					if(Number(v.es_primera_seg_audiencia) == 2){
						this.btnSegundaAudiencia = true
						// this.tblSegundaAudiencia = true
					}
					
				})
			}
		}, (err) => {
		})
	}

  ValidadorRegistros(){
		let tempstr=null;
		this._seguridadService.ValidadorRegistros(this.data.NoQueja).subscribe((Data)=>{
			if(Data['reason'] == 'OK' || Data['reason'] == 'INVALID'){
				if(Data['reason'] == 'OK'){
					this.flagValidadorRegistros=true;
				}else{
					this.flagValidadorRegistros=false;
				}
				this.flagformvisible++;
			}else{
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

  Save_ReProgramarAudiencia(){
		if(confirm("¿Está seguro que desea reprogramar esta audiencia?, ya existe otra cita previamente creada.")) {
			this.SaveProgramarAudiencia();
		}
  }

  Save_Audiencia(){
		if(this.flag_Resultado){
			if(confirm("¿Está seguro que desea actualizar el resultado de esta audiencia?")) {
				this.SaveAudiencia();
			}
		}else{
			this.SaveAudiencia();
		}
  }	

  LoadFrmDataQueja(isBlank:boolean){
	  if(isBlank){
			this.lbl_dpto='';
			this.lbl_muni='';
			this.lbl_sede='';
	  }else{
			this.lbl_dpto=this.lst_queja[0].nombre_departamento;
			this.lbl_muni=this.lst_queja[0].nombre_municipio;
			this.lbl_sede=this.lst_queja[0].nombre_sede;
	  }
  }

  LoadCmb(){
		let tempstr='';
		this._catalogoService.getResultadoAudiencia(2).subscribe((Data)=>{
			if(Data['reason'] == 'OK'){
				tempstr=Data['value'];
				if(tempstr != null)	{
					this.cmb_resultado=JSON.parse('['+tempstr.slice(0, -1) +']');
					this.flagformvisible++;
					//this.MoverHaciaCtrl.setValue(this.cmb_hacia[0].id_catalogo);
				}else{
					this.cmb_resultado=[];
				}
				this.flagInfoError=false;
			}else{
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}
		},(error)=>{
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
  }

	GetQuejaList(){
	  let tempstr='';
	  this._quejaService.getDataQueja(this.data.NoQueja).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_queja=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.LoadFrmDataQueja(false);
					this.flagformvisible++;
					if(this._seguridadService.EditableporFlujo(this.data.Flujo,this.lst_queja[0]['id_estado_queja']))
						//si es rol 3 administrador, puede editar
						if(this.data.Rol == 3)
							this.flagEditable=true;
						else{
							//si es otro rol, revisar si la queja esta asignada a este usuario
							if(this.lst_queja[0]['usuario_asignado'] == this.data.Usuario)
								this.flagEditable=true;
							else
								this.flagEditable=false;
						}
					else
						this.flagEditable=false;
				}else{
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
					this.LoadFrmDataQueja(true);
				}				
			}else{
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
				this.LoadFrmDataQueja(true);
			}		
		},(error)=>{
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

	LoadFrmData(isBlank:boolean){	
		
		if(!isBlank){
			if(typeof this.lst_audiencia["0"] === 'undefined'){
				this.lst_programado=[];
			}else{
				this.id_audiencia=this.lst_audiencia["0"].id_audiencia;
				if(this.lst_audiencia["0"].ProgramaAudiencia == null){
					this.lst_programado=[];
				}else{
					this.lst_programado=this.lst_audiencia["0"].ProgramaAudiencia;
					
					//flag was here
					let lastindex=this.lst_programado.length-1;
					//ObjectsArr[ObjectsArr.length - 1]
					if(lastindex>-1){
						this.flag_Programado=true;

						this.tblPrimeraAudiencia = true
						this.tblSegundaAudiencia = true

						if(this.lst_programado[lastindex].id_resultado_audiencia != null && this.lst_programado[lastindex].id_resultado_audiencia != ''){
							this.ResultadoCtrl.setValue(this.lst_programado[lastindex].id_resultado_audiencia);
							this.flag_Resultado=true;
							this.lbl_resultado=this.Getresultado(this.ResultadoCtrl.value);
						}
					}
				}		
			}
		}else{
			this.lst_programado=[];
		}
		this.GetRegistro();
		this.GetRegistroJuridico()
	}

	GetAudienciaList(){
	  let tempstr='';
	  this._audienciaService.getData(this.data.NoQueja,1).subscribe((retvalue)=>{
			if(retvalue["reason"] == 'OK'){
				tempstr=retvalue['value'];
				if(tempstr != null)	{
					this.lst_audiencia=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
					this.LoadFrmData(false);
					this.getTotalAudiencia()
				}else{
					this.LoadFrmData(true);
					
				}				
				this.flagformvisible++;
			}else{
				this.LoadFrmData(true);
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			}		
		},(error)=>{
			this.flagInfoError=true;
			this.SetSecTimerInfoError();
		});
	}

	convertDate(){
		var date=this.FechaCtrl.value;
		var time=this.HoraCtrl.value;
		var hour, min;
		if(time.length ==5){
			hour=time.substring(0, 2);
			min=time.substring(3, 5);
		}else{	
			hour="00";
			min="00";
		}
		var temp=this.datePipe.transform(date,"yyyy-MM-dd")+'T'+hour+":"+min+":00";
		return temp;  
	}

	SaveProgramarAudiencia(){
		var today = new Date(Date.now());
		var td = new Date(this.convertDate());
		if(today > td){
			this.flagTimeError=true;
			this.SetSecTimerTimeError();
		}else{	
			if (this.ProgramarForm.valid) {
				this.flagBoton=false;

				
				/**
				 * 26.04.2020
				 * alruanoe
				 * Se agrega validación de fecha a programar
				 */
				var today = new Date(this.FechaCtrl.value + "T" + this.HoraCtrl.value);
				let resultado: boolean;

				resultado = true;
				var fin = moment(today).add(1, 'hour').format('LLL');
				var inicio = moment(today).add(0, 'hour').format('LLL');
				var td = new Date(this.convertDate());
				if (today > td) {
					this.flagTimeError = true;
					this.SetSecTimerTimeError();
				} else {
					

					this._audienciaService.ValidarFecha(this.data.Usuario, this.data.NoQueja, inicio, fin).subscribe((retvalue
					) => {

						if (retvalue["reason"] == 'OK') {
							let eventos = retvalue['value'] as data[];

							
							if (eventos == null) {
								//se programa la audiencia
								this._audienciaService.ProgramarAudiencia(this.FechaCtrl.value, this.HoraCtrl.value, this.data.NoQueja, 2,this.data.Flujo).subscribe((retvalue) => {
									if (retvalue["reason"] == 'OK') {
										this.SetSecTimerUpdateList();
										this.flagInsertInfo = true;
										this.SetSecTimerInsert();
										this.flagMainUpdate = true;
										this.ProgramarForm.reset();
										//se recargar el calendario
										this.validarCalendario();



									} else {
										this.flagInfoError = true;
										this.SetSecTimerInfoError();
									}
									this.flagBoton = true;
								}, (error) => {
									this.flagInfoError = true;
									this.flagBoton = true;
								});


							} else {

								if (eventos.length > 0) {
									swal.fire({
										icon: 'error',
										title: 'Error.',
										text: 'Ya existe una audiencia programada para esta fecha y hora.'
									});
									this.flagInfoError = true;
									this.flagBoton = true;


								} else {
									//se programa la audiencia
									this._audienciaService.ProgramarAudiencia(this.FechaCtrl.value, this.HoraCtrl.value, this.data.NoQueja,2,this.data.Flujo).subscribe((retvalue) => {
										if (retvalue["reason"] == 'OK') {
											this.SetSecTimerUpdateList();
											this.flagInsertInfo = true;
											this.SetSecTimerInsert();
											this.flagMainUpdate = true;
											this.ProgramarForm.reset();
											//se recargar el calendario
											this.validarCalendario();



										} else {
											this.flagInfoError = true;
											this.SetSecTimerInfoError();
										}
										this.flagBoton = true;
									}, (error) => {
										this.flagInfoError = true;
										this.flagBoton = true;
									});

								}
							}


						} else {

						}

					}, (error) => {

					});

				}
				
				/**
				 * 26.04.2020
				 * alruanoe
				 * Fin de validación de fecha a programar
				 */

			}
		}
	}

	SaveAudiencia(){
	  if (this.CerrarForm.valid) {	
			this.flagBoton3=false;
			this._audienciaService.Audiencia(this.ResultadoCtrl.value,this.data.NoQueja,2,this.data.Flujo).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					this.SetSecTimerUpdateList();
					this.flagInsertInfo=true;
					this.SetSecTimerInsert();
					this.flag_Resultado=true;
					this.flagMainUpdate=true;
				}else{
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}	
				this.flagBoton3=true;
			},(error)=>{
				this.flagInfoError=true;
				this.flagBoton3=true;
			});
	  }
	}

	Generar4Registros(){
		if(this.id_audiencia!=0){
			var approved=false;
			if(this.linkdescription1 != ''){
				if(confirm("¿Está seguro que quiere actualizar este registro con la información mas recienteX?")) {
					approved=true;
				}
			}else{
				approved=true;
			}
			if(approved){
				this.flagBoton2=false;
				this._registrosservice.add_4RegistrosJuridico(this.data.NoQueja, this.id_audiencia).subscribe((retvalue)=>{
					if(retvalue["reason"] == 'OK'){
						//this.SetSecTimerUpdateList();
						this.flagRegError=false;
						this.GetRegistro();
					}else{
						this.flagRegError=true;
						this.SetSecTimerRegistro();
					}				
					this.flagBoton2=true;
				},(error)=>{
					this.flagRegError=true;
					this.flagBoton2=true;
					this.SetSecTimerRegistro();
				});  
			}
		}
	}

	generarRegistros(){

		this.flagBoton2 = false;
		this._registrosservice.add_4Registros(this.data.NoQueja, this.id_audiencia).subscribe((retvalue) => {
			if (retvalue["reason"] == 'OK') {
				this.flagRegError = false;
				this.GetRegistro();
				this.tblPrimeraAudiencia = true
			} else {
				this.flagRegError = true;
				this.SetSecTimerRegistro();
			}
			this.flagBoton2 = true;
		}, (error) => {
			this.flagRegError = true;
			this.flagBoton2 = true;
			this.SetSecTimerRegistro();
		});
	}

	GetRegistro(){
		if(this.id_audiencia!=0){
			this._registrosservice.read_4Registros(this.id_audiencia).subscribe((retvalue) => {
			// this._registrosservice.read_4RegistrosJuridico(this.id_audiencia).subscribe((retvalue)=>{
				if(retvalue["reason"] == 'OK'){
					var tempstr=retvalue['value'];
					if(tempstr != null)	{
						this.registros=JSON.parse('['+retvalue["value"].slice(0, -1) +']');
						this.Leer4Registros(false);
					}else{
						this.Leer4Registros(true);
					}
				}else{
					this.flagInfoError=true;
					this.SetSecTimerInfoError();
				}		
			},(error)=>{
				this.flagInfoError=true;
				this.SetSecTimerInfoError();
			});
		}
	}

	GetRegistroJuridico() {
		if (this.id_audiencia != 0) {
			this._registrosservice.read_4RegistrosJuridico(this.id_audiencia).subscribe((retvalue) => {
				if (retvalue["reason"] == 'OK') {
					var tempstr = retvalue['value'];
					if (tempstr != null) {
						this.registros = JSON.parse('[' + retvalue["value"].slice(0, -1) + ']');
						this.LeerRegistrosJuridico(false);
					} else {
						this.LeerRegistrosJuridico(true);
					}
				} else {
					this.flagInfoError = true;
					this.SetSecTimerInfoError();
				}
			}, (error) => {
				this.flagInfoError = true;
				this.SetSecTimerInfoError();
			});
		}
	}


	Leer4Registros(blank: boolean) {
		let empty = false;
		if (blank) {
			this.routerlink1 = '';
			this.linkdescription1 = '';
			this.routerlink2 = '';
			this.linkdescription2 = '';
			this.routerlink3 = '';
			this.linkdescription3 = '';
			this.routerlink4 = '';
			this.linkdescription4 = '';
		} else {
			if (typeof this.registros["0"] === 'undefined')
				empty = true;
			if (!empty) {
				if (this.registros[0]['cedula_citacion_con_id'] != null) {
					this.tblPrimeraAudiencia = true
					this.routerlink1 = 'MuestraRegistro/3/' + this.registros[0]['cedula_citacion_con_id'];
					this.linkdescription1 += this.registros[0]['cedula_citacion_con_codigo'];
				}
				if (this.registros[0]['cedula_citacion_pro_id'] != null) {
					this.routerlink2 = 'MuestraRegistro/4/' + this.registros[0]['cedula_citacion_pro_id'];
					this.linkdescription2 += this.registros[0]['cedula_citacion_pro_codigo'];
				}
				if (this.registros[0]['cedula_notificacion_con_id'] != null) {
					this.routerlink3 = 'MuestraRegistro/5/' + this.registros[0]['cedula_notificacion_con_id'];
					this.linkdescription3 += this.registros[0]['cedula_notificacion_con_codigo'];
				}
				if (this.registros[0]['cedula_notificacion_pro_id'] != null) {
					this.routerlink4 = 'MuestraRegistro/6/' + this.registros[0]['cedula_notificacion_pro_id'];
					this.linkdescription4 += this.registros[0]['cedula_notificacion_pro_codigo'];
				}
			}

		}
	}

	


	// Leer4Registros(blank:boolean ){
	// 	let empty=false;
	// 	if(blank){
	// 		this.routerlink1='';
	// 		this.linkdescription1='';
	// 		this.routerlink2='';
	// 		this.linkdescription2='';
	// 		this.routerlink3='';
	// 		this.linkdescription3='';
	// 		this.routerlink4='';
	// 		this.linkdescription4='';
	// 		this.routerlink5='';
	// 		this.linkdescription5='';
	// 		this.routerlink6='';
	// 		this.linkdescription6='';
	// 	}else{
	// 		if(typeof this.registros["0"] === 'undefined')
	// 			empty=true;
	// 		if(!empty){
	// 			if(this.registros[0]['cedula_citacion_jur_con_id'] != null){
	// 				this.routerlink1='MuestraRegistro/3/'+this.registros[0]['cedula_citacion_jur_con_id'];
	// 				this.linkdescription1=this.registros[0]['cedula_citacion_jur_con_codigo'];
	// 			}
	// 			if(this.registros[0]['cedula_citacion_jur_pro_id'] != null){
	// 				this.routerlink2='MuestraRegistro/4/'+this.registros[0]['cedula_citacion_jur_pro_id'];
	// 				this.linkdescription2=this.registros[0]['cedula_citacion_jur_pro_codigo'];
	// 			}

    //             if(this.registros[0]['cedula_notificacion_citacion_jur_con_id'] != null){
	// 				this.routerlink3='MuestraRegistro/4/'+this.registros[0]['cedula_notificacion_citacion_jur_con_id'];
	// 				this.linkdescription3=this.registros[0]['cedula_notificacion_citacion_jur_con_codigo'];
	// 			}
    //             if(this.registros[0]['cedula_notificacion_citacion_jur_pro_id'] != null){
	// 				this.routerlink4='MuestraRegistro/4/'+this.registros[0]['cedula_notificacion_citacion_jur_pro_id'];
	// 				this.linkdescription4=this.registros[0]['cedula_notificacion_citacion_jur_pro_codigo'];
	// 			}

	// 			if(this.registros[0]['cedula_notificacion_re_correo_id'] != null){
	// 				this.routerlink5='MuestraRegistro/5/'+this.registros[0]['cedula_notificacion_re_correo_id'];
	// 				this.linkdescription5=this.registros[0]['cedula_notificacion_re_correo_codigo'];
	// 			}
	// 			if(this.registros[0]['cedula_notificacion_cit_correo_id'] != null){
	// 				this.routerlink6='MuestraRegistro/6/'+this.registros[0]['cedula_notificacion_cit_correo_id'];
	// 				this.linkdescription6=this.registros[0]['cedula_notificacion_cit_correo_codigo'];
	// 			}
	// 		}
			
	// 	}
	// }

	SetSecTimerInsert(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInsertInfo=false);
	}
	SetSecTimerInfoError(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagInfoError=false);
	}
	SetSecTimerUpdateList(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.GetAudienciaList());
	}
	SetSecTimerUpdateRegistro(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => { this.GetRegistro(); this.GetRegistroJuridico()});
	}
	SetSecTimerTimeError(){
		const source = timer(3000);
		const subscribe = source.subscribe(val => this.flagTimeError=false);
	}
	SetSecTimerRegistro(){
		const source = timer(5000);
		const subscribe = source.subscribe(val => this.flagRegError=false);
	}
	SetSecTimerForm(){
		const source = timer(15000);
		const subscribe = source.subscribe(val => this.TimerForm() );
	}
	TimerForm(){
		if(this.flagformvisible<3){
			this.loaderror=true;	
			this.flagformvisible=-1;
		}
	}
	
	closeDialog(){
		this.loc_pagina=0;
		this.ngOnDestroy();
	}

	ngOnDestroy() {
		let localresp: FormResponse= new FormResponse();
		localresp.NoQueja = this.data.NoQueja;
		localresp.quejanumero = this.data.quejanumero;
		localresp.pagina = this.loc_pagina;
		localresp.changes = this.flagMainUpdate;
		this.dialogRef.close(localresp);
	}	

	Getresultado(idd:number){
  	  var id=+idd;
	  if (id === null)
		  return null;
	  var found=null;
	  for (var me of this.cmb_resultado) {
		  if (me['id_resultado_audiencia'] === id) { 
				found=me['descripcion'];
		  }
   	  }	  
	  if (found != null){
		  return found;
	  }else{
		  return null;
	  }
	}
	
	openCedulaCitaCons() {
		this._registrosservice.getCedulaCitaCons(this.registros[0]['id_audiencia']).subscribe((Data) => {
			this._registrosservice.FileDownLoadChoose(Data, 1);
		}, (error) => {
			this.SetSecTimerInfoError();
		});
	}


	openCedulaNotifiCons() {
		this._registrosservice.getCedulaNotiCons(this.registros[0]['id_audiencia']).subscribe((Data) => {
			this._registrosservice.FileDownLoadChoose(Data, 1);
		}, (error) => {
			this.SetSecTimerInfoError();
		});
	}

	openCedulaNotifiProv() {
		this._registrosservice.getCedulaNotiProv(this.registros[0]['id_audiencia']).subscribe((Data) => {
			this._registrosservice.FileDownLoadChoose(Data, 1);
		}, (error) => {
			this.SetSecTimerInfoError();
		});
	}
	openCedulaCitaProv() {
		this._registrosservice.getCedulaCitaProv(this.registros[0]['id_audiencia']).subscribe((Data) => {
			this._registrosservice.FileDownLoadChoose(Data, 1);
		}, (error) => {
			this.SetSecTimerInfoError();
		});
	}


	openCedulaCitaConJur(){
		this._registrosservice.openCedulaCitaConJur(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}

	openCedulaCitaProJur(){
		this._registrosservice.openCedulaCitaProJur(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}

	openCedulaNotiProJur(){
		this._registrosservice.openCedulaNotiProJur(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}

	openCedulaNotiConJur(){
		this._registrosservice.openCedulaNotiConJur(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}

	openCedulaCitaNotiCitCorreo(){
		this._registrosservice.openCedulaCitaNotiCitCorreo(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}

	openCedulaCitaNotiResCorreo(){
		this._registrosservice.openCedulaCitaNotiResCorreo(this.registros[0]['id_audiencia']).subscribe((Data)=>{
			this._registrosservice.FileDownLoadChoose(Data,1);
		},(error)=>{
			this.SetSecTimerInfoError();
		});
	}
	
	closeMoveDialog(pagina) {
		this.loc_pagina= pagina;
		this.ngOnDestroy();
	}

}
