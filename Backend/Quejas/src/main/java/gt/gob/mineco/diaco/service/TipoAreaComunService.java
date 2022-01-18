package gt.gob.mineco.diaco.service;

import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.model.TipoReg_ActaConciliacion;
import gt.gob.mineco.diaco.model.TipoReg_ActaIncomparecencia;
import gt.gob.mineco.diaco.model.TipoReg_ComPerm;
import gt.gob.mineco.diaco.util.FormAudiencia;
import gt.gob.mineco.diaco.util.FormBitacora;
import gt.gob.mineco.diaco.util.FormBusqueda;
import gt.gob.mineco.diaco.util.FormComConsumidor;
import gt.gob.mineco.diaco.util.FormConcVirtInt;
import gt.gob.mineco.diaco.util.FormConfColas;
import gt.gob.mineco.diaco.util.FormConfGeneral;
import gt.gob.mineco.diaco.util.FormConfUsuario;
import gt.gob.mineco.diaco.util.FormConsumidor;
import gt.gob.mineco.diaco.util.FormMovimExp;
import gt.gob.mineco.diaco.util.FormParamGeneral;
import gt.gob.mineco.diaco.util.FormProveedor;
import gt.gob.mineco.diaco.util.FormQuejaFinalizada;
import gt.gob.mineco.diaco.util.FormRegistro;
import gt.gob.mineco.diaco.util.FormResAudiencia;
import gt.gob.mineco.diaco.util.FormSimple;
import gt.gob.mineco.diaco.util.FormSucursal;
import gt.gob.mineco.diaco.util.FormUsuarioSrch;
import gt.gob.mineco.diaco.util.FormVerifConcVirt;
import gt.gob.mineco.diaco.util.FormViewMainQueja1;
import gt.gob.mineco.diaco.util.FormViewMainQueja2;
import gt.gob.mineco.diaco.util.FormVisitaCampo;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.util.FormEstadoResolver;
import java.util.Date;

public interface TipoAreaComunService {
    //Queja

    public ResponseRs listTiposQueja(String token);

    public ResponseRs listTiposQueja(Integer id, String Type, String token);

    public ResponseRs getTipoQueja(Integer id, String token);

    public ResponseRs updateTipoQueja(TipoQueja tipoQueja);

    public ResponseRs DocumentarTipoQueja(FormSimple formSimple);

    public ResponseRs getTipoQuejaCodigo(String codigoqueja, String token);
    //Telefono

    public ResponseRs listTelefono(Integer id, String Type, String token);
    //Email

    public ResponseRs listEmail(Integer id, String Type, String token);
    //Consumidor

    public ResponseRs listTiposConsumidor(String token);

    public ResponseRs listTiposConsumidor(Integer id, String token);

    public ResponseRs CustomFilterConsumidor(FormBusqueda formBusqueda);
    //Bitacora

    public ResponseRs listTiposBitacora(Integer idqueja, Integer idflujo, String token);

    public ResponseRs getTipoBitacora(double id, String token);

    public ResponseRs addTipoBitacora(FormBitacora formBitacora);

    public ResponseRs updateTipoBitacora(FormBitacora formBitacora);

    public ResponseRs deleteTipoBitacora(FormBitacora formBitacora);

    public void BitacoraAutomatica(Integer idqueja, Integer idflujo, Integer usuario, String bitacora, String consumidor, Integer auto_fuente) throws Exception;
    //Catalogo

    public ResponseRs listCatalogo(String tabla, String token);
    //Comunicacion Consumidor

    public ResponseRs listTiposComConsumidor(Integer noqueja, String token, Integer flujo);

    public ResponseRs getTipoComConsumidor(Integer idquejapaso, String token, Integer flujo);

    public ResponseRs addTipoComConsumidor(FormComConsumidor formComConsumidor);
    //Paso Queja

    public ResponseRs listTiposPasoQueja(Integer idqueja, String token);

    public ResponseRs getTipoPasoQueja(double id, String token);

    public ResponseRs getLastPasoQuejabyType(Integer id_queja, Integer id_tipo_registro, String token);

    public ResponseRs addTipoPasoQueja(TipoPasoQueja tipoPasoQueja);
    //View Main Queja

    public ResponseRs listViewMainQueja(String token);
    //public ResponseRs listViewMainQuejaCustomFilter(FormViewMainQueja2 formViewMainQueja);

    public ResponseRs listQuejaCustomFilter1(FormViewMainQueja1 formViewMainQueja);

    public ResponseRs listQuejaCustomFilter2(FormViewMainQueja2 formViewMainQueja);

    public ResponseRs findTipoQuejaCustomFilterBusqueda(FormBusqueda formBusqueda);

    public ResponseRs listQuejaCustomFilterSP(FormViewMainQueja1 formViewMainQueja);

    public ResponseRs listQuejaCustomFilterVyV(FormViewMainQueja1 formViewMainQueja);

    public ResponseRs listQuejaCustomFilterJuridico(FormViewMainQueja1 formViewMainQueja);
    //Departamento

    public ResponseRs listDepartamento(String token);
    //Municipio

    public ResponseRs listMunicipio(String token);

    public ResponseRs listMunicipio(Integer id_departamento, String token);
    //Verificacion Conciliacion Virtual

    public ResponseRs listTiposVerifConcVirt(Integer noqueja, String token);

    public ResponseRs getTipoVerifConcVirt(Integer id, String token);

    public ResponseRs addTipoVerifConcVirt(FormVerifConcVirt formVerifConcVirt);

    public ResponseRs setTipoVerifConcVirtResolucion(String respuesta, String mlink);
    //Proveedor

    public ResponseRs listTiposProveedorSP(String token);

    public ResponseRs listTiposProveedornoneSP(String token);

    public ResponseRs listTiposProveedorCPoSP(String token);

    public ResponseRs listTiposProveedor(Integer id, String token);

    public ResponseRs CustomFilterProveedor(FormBusqueda formBusqueda);
    //Registro

    public ResponseRs listTiposRegistro(String token);

    public ResponseRs listTiposRegistro(Integer id, String token);
    //public String getNextCorrelativoRegistro(Integer id, String token) throws Exception;
    //Queja Registro

    public ResponseRs listQuejaRegistro(Integer id, String token);

    public ResponseRs saveQuejaRegistro(Integer idqueja, TipoReg_ComPerm tipoReg_ComPerm, Integer tipo_registro, String token) throws Exception;
    //Registro: Comunicación Permanente

    public ResponseRs getReg_ComPermanente(Integer id, String token);

    public ResponseRs getReg_ComPermanentexIdQueja(Integer idqueja, String token);

    public ResponseRs saveReg_ComPermanente(FormRegistro formReg_ComPerm);
    
    public ResponseRs getUltimaAceptacion(FormRegistro formReg_ComPerm);
    //sede

    public ResponseRs listSedeExcOne(Integer id, String token);

    public ResponseRs listSedeAll(String token);
    //Registro: Ficha Queja

    public ResponseRs getReg_FichaQuejaxIdQueja(Integer id, String token);

    public ResponseRs saveReg_FichaQueja(FormRegistro formReg_FichaQueja);
    //Audiencia

    public ResponseRs getAudienciaxIdQueja(Integer idqueja, Integer no_audiencia, String token);

    public ResponseRs saveAudiencia(FormAudiencia formReg_ComPerm);
    //Resultado Audiencia

    public ResponseRs listResultadoAudiencia(Integer no_audiencia, String token);
    //Registro: Cedula Citacion Consumidor

    public ResponseRs getReg_CedCitacionCon(Integer id_audiencia, String token);

    public ResponseRs save2Registros2Notificaciones(FormRegistro formRegistro);

    public ResponseRs get2Registros2Notificaciones(Integer id_audiencia, String token);
    //Registro: Cedula Citacion Proveedor

    public ResponseRs getReg_CedCitacionPro(Integer id_audiencia, String token);
    //Registro: Cedula Notificacion Consumidor

    public ResponseRs getReg_CedNotificacionCon(Integer id_audiencia, String token);
    //Registro: Cedula Notificacion Proveedor

    public ResponseRs getReg_CedNotificacionPro(Integer id_audiencia, String token);
    //motivo poa

    public ResponseRs listMotivoPOA(String token);
    //motivo queja finalizada

    public ResponseRs listMotivoQuejaFinalizada(String tipo, String token);

    public ResponseRs listMotivoQuejaFinMotivo(Integer padre, String token);
    //queja finalizada

    public ResponseRs getQuejaFinalizada(Integer id_queja, String token);

    public ResponseRs saveQuejaFinalizada(FormQuejaFinalizada formQuejaFinalizada);

    public ResponseRs saveQuejaFinalizadaSP(FormQuejaFinalizada formQuejaFinalizada);

    public ResponseRs saveQuejaFinalizadaVyV(FormQuejaFinalizada formQuejaFinalizada);

    public ResponseRs checkArchivo(Integer id_queja, String token);
    //visita de campo

    public ResponseRs listVisitaCampo(Integer idqueja, Integer id_flujo, String token);

    public ResponseRs saveVisitaCampo(FormVisitaCampo formVisitaCampo);

    public ResponseRs deleteVisitaCampo(FormVisitaCampo formVisitaCampo);
    //Usuario

    public ResponseRs listUsuario(String token, Integer id_flujo);

    public ResponseRs getUsuario(Integer id, String token);

    public ResponseRs EmailPassword(String email);
    //Registro: Resolucion visita de campo

    public ResponseRs getReg_ResVisitaCampo(Integer idqueja, String token);

    public ResponseRs saveReg_ResVisitaCampo(FormRegistro formRegistro);
    //Departamento Interno

    public ResponseRs listDeptoInternoMovHacia(String token);

    public ResponseRs listDeptoInternoMovHaciaVyV(String token);

    public ResponseRs listDeptoInternoMovHaciaJur(String token);

    public ResponseRs listDeptoInternoMovHaciaSinJefe(String token);
    //Queja Simple

    public ResponseRs listQuejaSimpleAct(String token);

    public ResponseRs listQuejaSimpleArchivo(String token);

    public ResponseRs listQuejaSimpleJuridico(String token);

    public ResponseRs listQuejaSimpleVyV(String token);

    public ResponseRs listQuejaSimpleTodo(String token);

    public ResponseRs listQuejaSimplePendAsig(String token);

    public ResponseRs listVyVQuejaSimpleArchivo(String token);

    public ResponseRs listVyVQuejaSimpleJuridico(String token);

    public ResponseRs listVyVQuejaSimpleAtCon(String token);

    public ResponseRs listVyVQuejaSimplePendAsig(String token);

    public ResponseRs listJurQuejaSimpleArchivo(String token);

    public ResponseRs listJurQuejaSimpleVerificacion(String token);

    public ResponseRs listJurQuejaSimpleAtCon(String token);

    public ResponseRs listJurQuejaSimplePendAsig(String token);
    //Movimiento Expediente

    public ResponseRs getMovimExpxIdQueja(Integer idqueja, String token);

    public ResponseRs getMovimExpTop5(String token, Integer id_dpto_interno);

    public ResponseRs savePreMovimExp(FormMovimExp formulario);

    public ResponseRs savePreMovimExpVyV(FormMovimExp formulario);

    public ResponseRs savePreMovimExpJur(FormMovimExp formulario);

    public ResponseRs saveMovimExp(FormMovimExp formulario);

    public ResponseRs saveMovimExpInd(FormMovimExp formulario);

    public ResponseRs saveMovimExpIndVyV(FormMovimExp formulario);

    public ResponseRs saveMovimExpIndJur(FormMovimExp formulario);
    //Movimiento Expediente Detalle

    public ResponseRs getMovimExpDetallexidMov(Integer id, String token);
    //Registro: Movimiento Expediente

    public ResponseRs getReg_MovExpxId(Integer id, String token);
    //Registro: Movimiento Expediente

    public ResponseRs getConcVirtInt(Integer idqueja, String token);

    public ResponseRs saveConcVirtInt(FormConcVirtInt formulario);
    //Estado Queja

    public ResponseRs getEstadosQueja(String token);
    //Verificacion Datos Interaccion

    public ResponseRs getVerifDatosInt(Integer idqueja, String token);

    public ResponseRs saveVerifDatosInt(FormConcVirtInt formulario);

    public ResponseRs VerifDatosFlujoGuia(Integer idqueja, Integer id_flujo, String token);
    //Magic Link

    public ResponseRs getMagicLink(String id);
    //Registro: Formulario Queja

    public ResponseRs getReg_FormularioQxIdQueja(Integer id, String token);

    public ResponseRs saveReg_FormularioQ(FormRegistro formRegistro);
    //Confirmar Accion

    public ResponseRs getConfirmarAccion(Integer idusuario, String token);

    public ResponseRs UpdateConfirmarAccion(FormSimple formSimple);

    public ResponseRs savePubConfirmarAccionAtCon(Integer id_queja);

    public ResponseRs saveReg_ResArchivoConciliacion(FormRegistro frmReg);

    public ResponseRs getReg_ResArchivoConcilacion(Integer idqueja, String token);

    public ResponseRs saveReg_ResArchUnAudConciliacion(FormRegistro PfrmReg);

    public ResponseRs getReg_ResArchivoUnAudConcilacion(Integer idqueja, String token);
    //Tipo Cola

    public ResponseRs getAllTipoColaSedes(String token);

    public ResponseRs getAllTipoColaxUsuario(String token, Integer idusuario);

    public ResponseRs getAllAsignaColaxTipo(String token, Integer idcola);

    public ResponseRs saveAsignaCola(FormConfColas formulario);

    public ResponseRs saveAsignaColaNew(FormConfColas formulario);

    public ResponseRs delAsignaCola(FormConfColas formulario);

    public ResponseRs getEspecialxFlujo(String token, Integer id_flujo);
    //tarea programada

    public Void SaveTareaProgramada(Integer id_proceso, Date ejecucion, String resultado);

    public Void RuteoQuejas140();

    public Void ReenvioCorreos();

    public Boolean savePrivConfirmarAccion140(Integer id_queja) throws Exception;

    public Void EmailQuejasExpiradas();

    public Void EmailAlertaQuejasExpiradas();
    //reset clave

    public ResponseRs SaveEmailNewPassword(String linkcode);
    //validador registros

    public ResponseRs ValidadorRegistros(Integer id_queja, String token);
    //flujo guia

    public boolean saveTiposFlujoGuia(Integer idqueja, Integer col) throws Exception;
    //reasignar queja

    public ResponseRs ReasignarQuejaSave(FormSimple formSimple);
    //configuracion: parametros generales
    
    public ResponseRs saveEstadoResolver(FormEstadoResolver formestadoresolver);
    //form para estado de resolver
    public ResponseRs getParamGenerales(String token);

    public ResponseRs saveParamGenerales(FormParamGeneral formParamGeneral);
    //configuracion usuario

    public ResponseRs getUsuarioCustomFilter(FormUsuarioSrch formUsuarioSrch);

    public ResponseRs getUsuarioCustomFilterConcPrev(FormUsuarioSrch formUsuarioSrch);

    public ResponseRs saveConfUsuario(FormConfUsuario formConfUsuario);

    public ResponseRs saveConfUsuarioPP(FormConfUsuario formConfUsuario);

    public ResponseRs getUsuarioConfById(Integer idusuario, String token);

    public ResponseRs getUsuarioAsignaCola(Integer id_tipo_cola, Integer id_flujo, String token);

    public ResponseRs getUsuarioConfPPById(Integer idusuario, String token);
    //cambiar password

    public ResponseRs SetNewPassword(FormSimple formulario);
    //catalogo puestos

    public ResponseRs getPuestos(String token);
    //expiracion, configuracion general
    // se agregan metodos para catalogos       

    public ResponseRs saveDepartamento(String nombreDepartamento, String token);

    public ResponseRs updateDepartamento(int codigoDepartamento, String nombreDepartamento, String token);

    public ResponseRs deleteDepartamento(int codigoDepartamento, String token);

    public ResponseRs listDeptoInterno(String token);

    public ResponseRs saveDepartamentoInterno(String nombreDepartamento, String token);

    public ResponseRs updateDepartamentoInterno(int codigoDepartamento, String nombreDepartamento, String token);

    public ResponseRs deleteDepartamentoInterno(int codigoDepartamento, String token);

    public ResponseRs saveMunicipio(int codigoDepto, String token, String nombreMunicipio, String usuario);

    public ResponseRs updateMunicipio(int codigoDepto, String token, int codigoMunicipio, String nombreMunicipio, String usuario);

    public ResponseRs deleteMunicipio(int codigoMunicipio, String token, String usuario);

    public ResponseRs savePais(String nombrePais, String token);

    public ResponseRs updatePais(Integer idPais, String nombrePais, String token);

    public ResponseRs deletePais(Integer idPais, String token);

    public ResponseRs listPaises(String token);

    public ResponseRs saveSede(String nombreSede, Integer CodigoMunicipio,
            String direccionAvenida, String direccionCalle, String direccionZona, String direccionDetalle, String avenidaOCalle, String usuario, String token);

    public ResponseRs updateSede(Integer idSede, String nombreSede, Integer codigoMunicipio,
            String direccionAvenida, String direccionCalle, String direccionZona, String direccionDetalle, String avenidaOCalle, String usuario, String token);

    public ResponseRs deleteSede(Integer idSede, String usuario, String token);

    public ResponseRs listDiaInhabil(String token, Integer id_departamento);

    public ResponseRs saveDiaInhabil(String token, Integer id_departamento, Integer dia, Integer mes, String descripcion, String usuario);

    public ResponseRs updateDiaInhabil(Integer id_dia_inhabil, String token, Integer id_departamento, Integer dia, Integer mes, String descripcion, String usuario);

    public ResponseRs deleteDiaInhabil(String token, Integer id_dia_inhabil, String usuario);

    public ResponseRs getListMotivoQueja(String token);

    public ResponseRs saveMotivoQueja(String motivo, String usuario, String token);

    public ResponseRs updateMotivoQueja(Integer idMotivoQueja, String motivo, String usuario, String operacion, String token);

    public ResponseRs getMunicipio(Integer codigoMunicipio, String token);

    public ResponseRs getDepartamento(Integer codigoDepartamento, String token);

    public ResponseRs saveCatProveedor(FormProveedor p);

    public ResponseRs updateCatProveedor(FormProveedor p);

    public ResponseRs deleteCatProveedor(Integer id, String usuario, String token);

    public ResponseRs listTiposProveedorComun(String token);

    public ResponseRs listTiposActividadEconomica(String token);

    public ResponseRs listTipoComercio(Integer idActividadEconomica, String token);

    public ResponseRs findProveedorByNit(String nit, String token);

    public ResponseRs findProveedorById(Integer idProveedor, String token);

    public ResponseRs saveConsumidor(FormConsumidor formConsumidor);

    public ResponseRs getConfiguracionGeneral(String token);

    public ResponseRs saveConfiguracionGeneral(FormConfGeneral formConfGeneral);
    //proveedor aprobar

    public ResponseRs GetProveedorAprobar(String token, String estado);

    public ResponseRs UpdProveedorAprobar(String token, Integer id, String estado, String razon, Integer usuario_operacion);

    public ResponseRs ProveedorAprobarxNitProv(String nit);
    //registros varios

    public ResponseRs saveReg_ResVerificacionVigVisitaCampo(FormRegistro formRegistro);

    public ResponseRs getReg_ResVisitaCampoVerifVig(Integer idqueja, String token);

    public ResponseRs save2Registros2NotificacionesJuridico(FormRegistro formRegistro);

    public ResponseRs get2Reg2NotiJuridico(Integer id_audiencia, String token);
    //portal links

    public ResponseRs getPortalLinks();
    //resultado audiencia

    public ResponseRs GetAllResAudiencia(Integer id_queja, String token);

    public ResponseRs GetResAudiencia(Integer id, String token);

    public ResponseRs saveResAudiencia(FormResAudiencia formResAudiencia);

    public ResponseRs updResAudiencia(FormResAudiencia formResAudiencia);

    public ResponseRs delResAudiencia(FormResAudiencia formResAudiencia);
    //resolucion final

    public ResponseRs GetCorrelativoResFinal(String token);
    //resolucion final

    public ResponseRs GetAllResFinal(Integer id_queja, String token);

    public ResponseRs GetResFinal(Integer id, String token);

    public ResponseRs saveResFinal(FormSimple formResAudiencia);

    public ResponseRs updResFinal(FormSimple formResAudiencia);

    public ResponseRs delResFinal(FormSimple formResAudiencia);

    public TipoReg_ActaConciliacion saveReg_ActaConciliacion(FormRegistro PfrmReg);

    public TipoReg_ActaIncomparecencia saveReg_ActaIncomparecencia(FormRegistro PfrmReg);
    //vyv asigna cola inicial

    public ResponseRs GetxidquejaVyvColaInicial(Integer id_queja, String token);

    public ResponseRs saveVyvColaInicial(FormSimple formulario);
    //qr path

    public ResponseRs GetQRpath();

    public ResponseRs listPuestos(String token);

    public ResponseRs savePuesto(Integer id, String nombre, String usuario, String token, String operacion);

    public ResponseRs listTipoCola(String token);

    public ResponseRs saveTipoCola(Integer idTipoCola, Integer idFlujo, String nombre,
            String tipo, Integer idSede, Integer usuario, String operacion, String token);

    public ResponseRs listDepartamentoForDiaInhabil(String token);

    public ResponseRs listTipoFlujo(String token);

    public ResponseRs listTipoEtnia(String token);

    public ResponseRs findConsumidorByNit(String nit, String token);
    
    public ResponseRs findConsumidorByDpi(String dpi, String token);

    public ResponseRs deleteConsumidor(Integer idConsumidor, String usuario, String token);

    public ResponseRs updateConsumidor(FormConsumidor f);

    public ResponseRs saveTipoTipoConsumidor(Integer idTipoTipoConsumidor, String nombre, String token, String usuario, String operacion);

    public ResponseRs listTipoTipoConsumidor(String token);

    public ResponseRs listTipoAreaInterna(String token);

    public ResponseRs saveTipoAreaInterna(Integer id, String nombre, String token, String usuario, String operacion);

    public ResponseRs listTipoMotivoQuejaFinalizada(String token);

    public ResponseRs saveTipoMotivoQuejaFinalizada(Integer id, String nombre, String tipo, Integer padre,
            String token, String usuario, String operacion);

    public ResponseRs saveActividadEconomica(Integer id, String nombreActividad, String usuario, String operacion, String token);

    public ResponseRs saveTipoComercio(Integer id, Integer idActividad, String nombre, String usuario, String operacion, String token);

    public ResponseRs listTipoComercioAll(String token);

    public ResponseRs saveReg_ActaJuridico(FormRegistro PfrmReg);

    public ResponseRs getReg_actaJuridico(Integer idqueja, String token);

    public ResponseRs listTipoSucursal(Integer idProveedor, String token);

    public ResponseRs saveTipoSucursal(FormSucursal s, String operacion);

    public ResponseRs deleteTipoSucursal(Integer id, String usuario, String token);

    public ResponseRs listProveedorAll(String token);

    /**
     * Metodo que sirve para crear al usuario proveedor.
     * <p>
     * Con la informacion basica del proveedor se genera un usuario y password
     * en el portal de proveedores.
     * <p>
     * Por defecto se crean usuarios de tipo Administradores.
     *
     * @param pProveedor DTO con la informacion del proveedor.
     * @param pUsuarioOperacion Usuario de la operacion.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    public ResponseRs crearUsuarioProveedor(FormConfUsuario pProveedor,
            Integer pUsuarioOperacion);
    //__________________________________________________________________________

    /**
     * Metodo que sirve para aprobar la solicitud de creacion del proveedor.
     * <p>
     * Cambia el estado del proveedor previamente almacenado de "T" = Temporal,
     * hacia "A" = Activo.
     * <p>
     * Activa la conciliacion previa.
     *
     * @param pToken Token de seguridad.
     * @param pEstado Estado del usuario.
     * @param pId Identificador del usuario.
     * @param pUsuarioOperacion Usuario que realiza la operacion.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    public ResponseRs aprobarSolicitudProveedor(String pToken, String pEstado,
            Integer pId, Integer pUsuarioOperacion);

    //__________________________________________________________________________
    /**
     * Metodo que sirve para procesar la aprobacion de la solicitud del usuario.
     * <p>
     * Ejecuta un proceso que realiza:
     * <ul>
     * <li>aprobarSolicitudProveedor
     * {@link TipoAreaComunService#aprobarSolicitudProveedor}</li>
     * <li>crearUsuarioProveedor
     * {@link TipoAreaComunService#crearUsuarioProveedor}</li>
     * </ul>
     *
     * @param pToken Token de seguridad.
     * @param pEstado Estado del usuario.
     * @param pIdUsuario Identificador del usuario.
     * @param pUsuarioOperacion Usuario que realiza la operacion.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    public ResponseRs procesarAprobacionUsuario(String pToken, String pEstado,
            Integer pIdUsuario, Integer pUsuarioOperacion);
    //__________________________________________________________________________
}
