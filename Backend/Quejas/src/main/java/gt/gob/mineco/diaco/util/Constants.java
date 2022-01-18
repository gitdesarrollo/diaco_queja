package gt.gob.mineco.diaco.util;

public class Constants {
	public static final Integer QUEJA_NUEVA = 401;
        public static final long SESSION_DURATION = 24 * 60 * 60 * 1000;
        //constantes de id's de tipos de registros en los catalogos-- tabla: diaco_cat_tipo_registro
        public static final int REG_TIPO_RESOLUCION_ARCHIVO_CONCILIACION = 16;
        public static final int REG_TIPO_RESOLUCION_ARCHIVO_UNICA_AUD_CONC=17;
        public static final int REG_TIPO_FORMULARIO_QUEJA=2;
        public static final int REG_TIPO_PROGRAMAR_AUDIENCIA_CONCILIACION=4;
        public static final int REG_TIPO_PROGRAMAR_AUDIENCIA_OFRECIMIENTO_MED_PRUEBAS=5;
        public static final int REG_TIPO_VISITA_CAMPO=8;
        public static final int REG_TIPO_COMUNICACION_PERMANENTE=3;
        public static final int REG_TIPO_MOVIMIENTO_EXPEDIENTE=9;
        public static final int REG_TIPO_VISITA_CAMPO_VERIFICACION_VIG=21;
        public static final int REG_TIPO_PROGRAMAR_AUDIENCIA_OFRECIMIENTO_MED_PRUEBAS_JURIDICO=27;
        public static final int REG_TIPO_JURIDICO_RESULTADO_AUDIENCIA=30;
        public static final int REG_TIPO_JURIDICO_RESULUCION_FINAL=31;
        public static final int REG_TIPO_CONCILIACION_AT=6;
        public static final int REG_TIPO_VYV_ASIGNA_QUEJA_INICIAL=32;
        
        //constantes de id's para los correlativos y prefijos --tabla: diaco_registros
        public static final int REG_DIACO_REGISTRO_RES_ARCHIVO_CONCILIACION=10;
        public static final int REG_DIACO_REGISTRO_RES_ARCHIVO_UNICA_AUD_CONC=11;
        public static final int REG_DIACO_REGISTRO_FORMULARIO_QUEJA=9;
        public static final int REG_DIACO_REGISTRO_CEDULA_CITACION_CONSUMIDOR=3;
        public static final int REG_DIACO_REGISTRO_CEDULA_CITACION_PROVEEDOR=4;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_CONSUMIDOR=5;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_PROVEEDOR=6;
        public static final int REG_DIACO_REGISTRO_VISITA_CAMPO=7;
        public static final int REG_DIACO_REGISTRO_COMUNICACION_PERMANENTE=1;
        public static final int REG_DIACO_REGISTRO_FICHA_QUEJA=2;
        public static final int REG_DIACO_REGISTRO_MOVIMIENTO_EXPEDIENTE=8;
        public static final int REG_DIACO_REGISTRO_VISITA_CAMPO_VERIFICACION_VIG=13;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_JURIDICO=14;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_CITACION_JURIDICO=15;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_CITACION_CORREO=16;
        public static final int REG_DIACO_REGISTRO_CEDULA_NOTIFICACION_RES_CORREO=17;
        public static final int REG_DIACO_REGISTRO_RESOLUCION_FINAL_ARCHIVO_JURIDICO=18;
        public static final int REG_DIACO_REGISTRO_DECLARACION_REBELDIA=22;
        public static final int REG_DIACO_REGISTRO_ACTA_CONCILIACION=23;
        public static final int REG_DIACO_REGISTRO_ACTA_INCOMPARECENCIA=24;
        public static final int REG_DIACO_REGISTRO_RESOLUCION_APERTURA_PRUEBA=25;
        public static final int REG_DIACO_REGISTRO_CEDULA_CITACION_CONSUMIDOR_JUR=27;
        public static final int REG_DIACO_REGISTRO_CEDULA_CITACION_PROVEEDOR_JUR=28;
        public static final int REG_DIACO_REGISTRO_RESOLUCION_FINAL=29;
        public static final int REG_DIACO_REGISTRO_ACTA_JURIDICO=30;
        
        //constantes tipos de correo
        public static final int REG_DIACO_FUENTE_EMAIL_CONC_VIRT_INTERACCIONES=1;
        public static final int REG_DIACO_FUENTE_EMAIL_VERIF_DATOS_INFO_EXTRA=2;
        public static final int REG_DIACO_FUENTE_EMAIL_CONFIRMACION_QUEJA_FINALIZADA_PORTAL_PROV=3;
        public static final int REG_DIACO_FUENTE_EMAIL_RESET_PASSWORD=4;
        public static final int REG_DIACO_FUENTE_EMAIL_NOTIFICACIONES_AUDIENCIA=5;
        public static final int REG_DIACO_FUENTE_EMAIL_FINALIZAR_SERV_PUB=6;
        public static final int REG_DIACO_FUENTE_EMAIL_COM_PERMANENTE=7;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_EXPIRADA_ATCON=8;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_EXPIRADA_JUR=9;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_EXPIRADA_VYV=10;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_EXPIRADA_SP=11;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_ALERTA_EXPIRAR_ATCON=12;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_ALERTA_EXPIRAR_JUR=13;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_ALERTA_EXPIRAR_VYV=14;
        public static final int REG_DIACO_FUENTE_EMAIL_QUEJA_ALERTA_EXPIRAR_SP=15;
        public static final int REG_DIACO_FUENTE_EMAIL_PROVEEDOR_APROBADO=16;
        
        //constantes perfiles de usuario
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_NORMAL=1;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_REVISOR=2;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_ADMINISTRADOR=3;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_VERIFICADOR=29;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_CONFIGURACION=17;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_COLAS=18;
        public static final int REG_DIACO_PERFIL_USUARIO_JUR_NORMAL=12;
        public static final int REG_DIACO_PERFIL_USUARIO_JUR_REVISOR=13;
        public static final int REG_DIACO_PERFIL_USUARIO_JUR_ADMINISTRADOR=14;
        public static final int REG_DIACO_PERFIL_USUARIO_JUR_CONFIGURACION=19;
        public static final int REG_DIACO_PERFIL_USUARIO_JUR_COLAS=20;
        public static final int REG_DIACO_PERFIL_USUARIO_VYV_NORMAL=9;
        public static final int REG_DIACO_PERFIL_USUARIO_VYV_REVISOR=10;
        public static final int REG_DIACO_PERFIL_USUARIO_VYV_ADMINISTRADOR=11;
        public static final int REG_DIACO_PERFIL_USUARIO_VYV_CONFIGURACION=21;
        public static final int REG_DIACO_PERFIL_USUARIO_VYV_COLAS=22;
        public static final int REG_DIACO_PERFIL_USUARIO_SP_NORMAL=6;
        public static final int REG_DIACO_PERFIL_USUARIO_SP_REVISOR=7;
        public static final int REG_DIACO_PERFIL_USUARIO_SP_ADMINISTRADOR=8;
        public static final int REG_DIACO_PERFIL_USUARIO_SP_CONFIGURACION=23;
        public static final int REG_DIACO_PERFIL_USUARIO_SP_COLAS=24;
        public static final int REG_DIACO_PERFIL_USUARIO_GENERALES_PARAMETROS=15;
        public static final int REG_DIACO_PERFIL_USUARIO_GENERALES_ADMONUSUARIOS=16;
        public static final int REG_DIACO_PERFIL_USUARIO_GENERALES_CONFIGURACION=25;
        public static final int REG_DIACO_PERFIL_USUARIO_GENERALES_APROBACION_PP=26;
        public static final int REG_DIACO_PERFIL_USUARIO_GENERALES_CATALOGOS=27;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_PRESENCIAL=28;
        public static final int REG_DIACO_PERFIL_USUARIO_ATCON_CALL_CENTER=30;
        public static final int REG_DIACO_PERFIL_USUARIO_PP_NORMAL=4;
        public static final int REG_DIACO_PERFIL_USUARIO_PP_ADMINISTRADOR=5;

        //constantes categoria de imagen
        public static final int REG_DIACO_CAT_IMAGEN_DPI=2;
        public static final int REG_DIACO_CAT_IMAGEN_FACTURA=3;
        public static final int REG_DIACO_CAT_IMAGEN_FORMULARIO_REGISTRO=14;
        public static final int REG_DIACO_CAT_IMAGEN_NOMBRAMIENTO_REP_LEGAL=15;
        public static final int REG_DIACO_CAT_IMAGEN_DPI_DUENO_REP_LEGAL=16;
        public static final int REG_DIACO_CAT_IMAGEN_PATENTE_SOCIEDAD_EMPRESA=17;
        
        //constante sede central
        public static final String SEDE_CENTRAL_PZ4_DIR="en el Centro de Atención de Quejas ubicado en la 6ta. Avenida 0-36, 3er nivel Centro Comercial Plaza zona 4"; 
        public static final String SEDE_CENTRAL_EDIF_REG_MERC_DIR="en el Centro de Atención de Quejas ubicado en el Edificio del Registro Mercantil, 3er nivel"; 
        public static final int REG_DIACO_SEDE_CENTRAL=1;
        
        //puestos
        public static final int REG_DIACO_PUESTO_JEFE_ATCON=1;
        public static final int REG_DIACO_PUESTO_JEFE_JUR=2;
        public static final int REG_DIACO_PUESTO_JEFE_VYV=3;
        
        //departamento interno
        public static final int REG_DIACO_DPTOINTERNO_JURIDICO=2;
        public static final int REG_DIACO_DPTOINTERNO_VERIFICACION=3;
        
}


