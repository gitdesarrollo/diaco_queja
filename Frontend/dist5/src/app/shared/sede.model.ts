import { Municipio } from './municipio.model';

export class Sede {
  idDiacoSede: number;
	direccionSede: string;
	fechaAdicion: Date;
	fechaModificacion: Date;
	habilitado: string;
	nombreSede: string;
	usuarioAdiciono: string;
	usuarioModifico: string;
	municipio: Municipio;
}
