package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.model.TipoReg_ActaConciliacion;
import gt.gob.mineco.diaco.model.TipoReg_ActaIncomparecencia;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.service.TipoRegistrosQuejaService;
import gt.gob.mineco.diaco.util.FormRegistro;
import javax.ws.rs.core.Response;

@Path("/tipos-registros")
@RequestScoped
public class TipoRegistrosRs {

    @Inject
    private TipoAreaComunService RegistroService;
    
    @Inject
    private TipoRegistrosQuejaService tipoRegQuejaService;

    @GET
    @Path("/form_queja/{pIdqueja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoFormQueja(@PathParam("pIdqueja") Integer pIdqueja, @PathParam("token") String token) {
       // return RegistroService.getReg_ComPermanentexIdQueja(idqueja, token);
        return RegistroService.getReg_FormularioQxIdQueja(pIdqueja, token);
    }

    @POST
    @Path("/form_queja")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoFormQueja(FormRegistro pFormQueja) {
       // return RegistroService.saveReg_ComPermanente(formReg_ComPerm);
       return RegistroService.saveReg_FormularioQ(pFormQueja);
    }

    @GET
    @Path("/com_con/{idqueja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoComConsumidor(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token) {
        return RegistroService.getReg_ComPermanentexIdQueja(idqueja, token);
    }

    @POST
    @Path("/com_con")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoComConsumidor(FormRegistro formReg_ComPerm) {
        return RegistroService.saveReg_ComPermanente(formReg_ComPerm);
    }

    @GET
    @Path("/ficha_queja/{idqueja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoFichaQueja(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token) {
        return RegistroService.getReg_FichaQuejaxIdQueja(idqueja, token);
    }

    @POST
    @Path("/ficha_queja")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoFichaQueja(FormRegistro formReg_ComPerm) {
        return RegistroService.saveReg_FichaQueja(formReg_ComPerm);
    }

    @GET
    @Path("/ced_cit_con/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCedCitacionConsumidor(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.getReg_CedCitacionCon(id_audiencia, token);
    }

    @GET
    @Path("/ced_cit_pro/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCedCitacionProveedor(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.getReg_CedCitacionPro(id_audiencia, token);
    }

    @GET
    @Path("/ced_not_con/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCedNotificacionConsumidor(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.getReg_CedNotificacionCon(id_audiencia, token);
    }

    @GET
    @Path("/ced_not_pro/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCedNotificacionProveedor(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.getReg_CedNotificacionPro(id_audiencia, token);
    }

    @GET
    @Path("/ced_2cit_2not/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCed2Registros2Notificaciones(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.get2Registros2Notificaciones(id_audiencia, token);
    }

    @GET
    @Path("/ced_2cit_2not_juridico/{id_audiencia}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoCed2Reg2NotiJuridico(@PathParam("id_audiencia") Integer id_audiencia, @PathParam("token") String token) {
        return RegistroService.get2Reg2NotiJuridico(id_audiencia, token);
    }

    
    @POST
    @Path("/ced_2cit_2not")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoCed2Registros2Notificaciones(FormRegistro formRegistro) {
        return RegistroService.save2Registros2Notificaciones(formRegistro);
    }

    @POST
    @Path("/ced_2cit_2not_juridico")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoCed2Reg2NotiJuridico(FormRegistro formRegistro) {
        return RegistroService.save2Registros2NotificacionesJuridico(formRegistro);
    }
    
    @GET
    @Path("/res_visita_campo/{id_queja}/{id_flujo}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoResVisitaCampo(@PathParam("id_queja") Integer id_queja,@PathParam("id_flujo") Integer id_flujo, @PathParam("token") String token) {
       if(id_flujo==1)
        return RegistroService.getReg_ResVisitaCampo(id_queja, token);
       else  return RegistroService.getReg_ResVisitaCampoVerifVig(id_queja, token);
    }

    @POST
    @Path("/res_visita_campo")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoResVisitaCampo(FormRegistro formRegistro) {
        if(formRegistro.getId_flujo()==1)
        return RegistroService.saveReg_ResVisitaCampo(formRegistro);
        else return RegistroService.saveReg_ResVerificacionVigVisitaCampo(formRegistro);
        
    }
    
    
    @POST
    @Path("/res_archivo_conciliacion")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoResArchivoConciliacion(FormRegistro formRegistro) {
        return RegistroService.saveReg_ResArchivoConciliacion(formRegistro);
    }

    @GET
    @Path("/res_archivo_conciliacion/{id_queja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoResArchivoConciliacion(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
        return RegistroService.getReg_ResArchivoConcilacion(id_queja, token);
    }

    @POST
    @Path("/res_archivo_un_aud_conc")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addTipoResArchUnAudConciliacion(FormRegistro formRegistro) {
        return RegistroService.saveReg_ResArchUnAudConciliacion(formRegistro);
    }
    
     @GET
    @Path("/res_archivo_un_aud_conc/{id_queja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getTipoResArchUnAudConciliacion(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
        return RegistroService.getReg_ResArchivoUnAudConcilacion(id_queja, token);
    }
    
    @GET /*ACTA CONCILIACION*/
    @Path("/acta_conciliacion-at/{id_queja}/{id_usuario}/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getActaConciliacionAt(@PathParam("id_queja") Integer id_queja, @PathParam("id_usuario") Integer id_usuario,@PathParam("token") String token) {
        FormRegistro formRegistro=new FormRegistro();
        formRegistro.setCreado_por(id_usuario);
        formRegistro.setId_queja(id_queja);
        formRegistro.setToken(token);
        TipoReg_ActaConciliacion vTipoReg_ActaConciliacion =RegistroService.saveReg_ActaConciliacion(formRegistro);
        return tipoRegQuejaService.getActaConciliacioAtContr(vTipoReg_ActaConciliacion);
    }
    
     @GET /*ACTA INCOMPARECENCIA*/
    @Path("/acta_incomparecencia-at/{id_queja}/{id_usuario}/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getActIncomparecenciaAt(@PathParam("id_queja") Integer id_queja, @PathParam("id_usuario") Integer id_usuario,@PathParam("token") String token) {
         FormRegistro formRegistro=new FormRegistro();
        formRegistro.setCreado_por(id_usuario);
        formRegistro.setId_queja(id_queja);
        formRegistro.setToken(token);
       
        TipoReg_ActaIncomparecencia vTipoReg_ActaIncomparecencia= RegistroService.saveReg_ActaIncomparecencia(formRegistro);
        return tipoRegQuejaService.getActaIncomparecenciaAtContr(vTipoReg_ActaIncomparecencia);
    }
    
    
    @POST
    @Path("/acta-juridico-corr")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addGetActaJuridico(FormRegistro formReg_ActaJuridico) {
        return RegistroService.saveReg_ActaJuridico(formReg_ActaJuridico);
    }

     @GET
    @Path("/acta-juridico-corr/{id_queja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getActaJuridico(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
        return RegistroService.getReg_actaJuridico(id_queja, token);
    }
    
}
