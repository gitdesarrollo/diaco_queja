
export class FrmVerifConcP {
  NoQueja: number;
  FechaIngreso: Date;
  Motivo: string;
  Detalle: string;
  Solicito: string;
  NotasP: string;
  Razon: string;
  ResolucionP: string;
  Resolucioncode: number;
}

export class FrmMainFD {
	NoQueja: number;
	Fecha: Date;
	Proveedor: string;
	Consumidor: string;
}

export class FrmComPermanente {
	via: number;
	estatus: string;
	observaciones: string;
}

export class FrmBitacora {
	No: number;
	Fecha: Date;
	Bitacora: string;
	Consumidor: string;
}

export const LSTFKBITACORA: FrmBitacora[] = [
	{ No: 1, Fecha: new Date(2018, 1,15), Bitacora: 'Activos', Consumidor: 'Activos' },
	{ No: 2, Fecha: new Date(2018, 1,15), Bitacora: 'Revisión de conciliación virtual', Consumidor: 'Activos'  },
	{ No: 3, Fecha: new Date(2018, 1,15), Bitacora: 'Pendiente verificación de datos', Consumidor: 'Activos'  },
	{ No: 4, Fecha: new Date(2018, 1,15), Bitacora: 'Pendiente de comunicación permanente', Consumidor: 'Activos'  },
	{ No: 5, Fecha: new Date(2018, 1,15), Bitacora: 'Pendiente audiencia de conciliación', Consumidor: 'Activos'  },
	{ No: 6, Fecha: new Date(2018, 1,15), Bitacora: 'Pendiente audiencia de ofrecimientos de medio de prueba', Consumidor: 'Activos'  },
	{ No: 7, Fecha: new Date(2018, 1,15), Bitacora: 'Audiencia completada', Consumidor: 'Activos'  },
	{ No: 8, Fecha: new Date(2018, 1,15), Bitacora: 'Archivo', Consumidor: 'Activos'  },
	{ No: 9, Fecha: new Date(2018, 1,15), Bitacora: 'Finalizados (improcedencia, falta documentacion)', Consumidor: 'Activos'  },
	{ No: 10, Fecha: new Date(2018, 1,15), Bitacora: 'Enviados a juridico', Consumidor: 'Activos'  },
	{ No: 11, Fecha: new Date(2018, 1,15), Bitacora: 'Enviados a verificacion y vigilancia', Consumidor: 'Activos'  },
	{ No: 12, Fecha: new Date(2018, 1,15), Bitacora: 'Enviados a sedes', Consumidor: 'Activos'  },
	{ No: 13, Fecha: new Date(2018, 1,15), Bitacora: 'Todos (activos e inactivos)', Consumidor: 'Activos'  }
  ];

