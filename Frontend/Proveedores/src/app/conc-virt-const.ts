//export const BASE_URL_REST='http://localhost:8080/diaco-int-web/rs/';
export const BASE_URL_REST='http://desagestorquejas.minfin.gob.gt/diaco-int-web/rs/';
export const BASE_URL_REST_FILE=BASE_URL_REST;

export class BaseCmbClass {
  id: number;
  nombre: string;
}

export class CmbResolucion {
  id_catalogo: number;
  descripcion_catalogo: string;
}

export class FrmMainConcVirt {
  departamento: number;
  municipio: number;
  estado: number;
  id_proveedor: number;
  SelectedFDesde: Date;
  SelectedFHasta: Date;
}

export const LSTFILTRO_ESTADOCONCVIRT: BaseCmbClass[] = [
	{ id: 401, nombre: 'Activo' },
	{ id: 402, nombre: 'Resuelto Localmente' },
	{ id: 999, nombre: 'Pendiente validar por DIACO' },
	{ id: 108, nombre: 'Proceso Completado por DIACO' }
];

export const LSTFILTRO_RESOLUCION: BaseCmbClass[] = [
	{ id: 1, nombre: 'No Aplica' },
	{ id: 2, nombre: 'Resuelto' },
	{ id: 3, nombre: 'Sin Resolución' }
];

export class FrmBitacora {
	id: number;
	correlativo: number;
	No: number;
	Fecha: Date;
	Bitacora: string;
	Consumidor: string;
}

export class FrmQueja {
  id_queja: number;
  fecha_queja: Date;
  id_estado_queja: number;
  motivo: string;
  detalle_queja: string;
  solicita_que: string;
  nombre_consumidor: string;
  tipotelefono:FrmDetalle_Telefono[];
  tipoemail:FrmDetalle_Email[];
}

export class FrmDetalle_Queja {
  id_queja: number;
  motivo: string;
  detalle_queja: string;
  fecha_queja: Date;
  solicita_que: string;
  id_consumidor: string;
  id_proveedor: string;
  nombre_consumidor: string;
  respuesta_con: string;
  quejanumero: string;
  tipotelefono:FrmDetalle_Telefono[];
  tipoemail:FrmDetalle_Email[];
}

export class FrmDetalle_Telefono {
  id_telefono: number;
  telefono: string;
  tipo_telefono: number;
}

export class FrmDetalle_Email {
  id_email: number;
  correo_electronico: string;
}

export class List_Images {
  id_imagen_queja: number;
  id_tipo_imagen: number;
  imagen: string;
  id_queja: number;
}

export class FrmDepartamento {
  codigo_departamento: number;
  nombre_departamento: string;
}

export class FrmMunicipio {
  codigo_municipio: number;
  codigo_departamento: number;
  nombre_departamento: string;
}

export class FrmProveedor {
  id_proveedor: number;
  nombre: string;
  direccion: string;
  nit: string;
  codigo_departamento: number;
  codigo_municipio: number;
}

export class FrmInteraccion {
  id: number;
  fecha: Date;
  pregunta: string;
  respuesta: string;
  id_queja:number;
  estado: string;
  cambiar_archivos: boolean;
  cambiar_datos: boolean;
}

export class FormResponse {
	NoQueja: number;
	Editable: boolean;
	quejanumero: string;
}
