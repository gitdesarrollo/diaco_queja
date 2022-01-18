/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import gt.gob.mineco.diaco.service.TipoRegistrosQuejaService;
import gt.gob.mineco.diaco.util.CedulaNotificacionDto;
import javax.ws.rs.QueryParam;

/**
 *
 * @author julio
 */
@Path("/registros-queja")
@RequestScoped
public class TipoRegistrosQuejaRs {

    @Inject
    private TipoRegistrosQuejaService tipoRegQuejaService;

    @GET /*DIACO-AQ-FO-02*/
    @Path("/form-queja/{id}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFormularioQueja(@PathParam("id") Integer id,@PathParam("pToken") String pToken) {
    //    return tipoRegQuejaService.getRegistroFichaQueja(idqueja);
        return tipoRegQuejaService.getFormularioQueja(id,pToken);
    }
 
     @GET
    //@Path("/com-perm/{idqueja}")
    @Path("/com-perm/{pIdRegistro}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getRegistroComunicacionPermanente(@PathParam("pIdRegistro") Integer pIdRegistro,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getRegistroComunicacionPermanente(pIdRegistro,pToken);
    }
  
    
     @GET
    //@Path("/com-perm/{idqueja}")
    @Path("/cedula-cita-cons/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaCitacionCons(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaCitacionCons(pId_audiencia,pToken);
    }
    
     @GET
    @Path("/cedula-cita-prov/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaCitacionProv(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaCitacionProv(pId_audiencia,pToken);
    }
      @GET
    @Path("/cedula-noti-cons/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotiCons(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionCons(pId_audiencia,pToken);
    }
    
    @GET
    @Path("/cedula-noti-prov/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotiProv(@PathParam("pId_audiencia") Integer idqueja,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionProv(idqueja,pToken);
    }
    
    
    
    @GET  
    @Path("/ficha-queja/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFichaQueja(@QueryParam("pIdQueja") Integer idqueja,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getFichaQueja(idqueja,pToken);
    }
    
    @GET
    @Path("/resolucion-archivo-visita-campo/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResArchivoVisitaCampo(@QueryParam("pIdQueja") Integer pIdQueja,@QueryParam("pFechaResolucion") String pFechaResolucion,@PathParam("pToken") String pToken ) {
        return tipoRegQuejaService.getArchivoVisitaCampo(pIdQueja, pFechaResolucion,pToken);
    }
    
    
    @GET
    @Path("/res-archivo-visita-campo_verif/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResArchivoVisitaCampo(@QueryParam("pIdQueja") Integer pIdQueja,@PathParam("pToken") String pToken ) {
        return tipoRegQuejaService.getArchivoVisitaCampoVerifVig(pIdQueja,pToken);
    }
    
     @GET
    @Path("/res_arch_conci/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResArchivoConciliacion(@QueryParam("pIdQueja") Integer pIdQueja,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getResArchivoConciliacion(pIdQueja,pToken);
    }
    
    @GET
    @Path("/res_arch_un_aud_con/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResArchivoUnicaAudiencia(@QueryParam("pIdQueja") Integer pIdQueja,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getResArchivoUnicaAudiencia(pIdQueja,pToken);
    }
    
    @GET
    @Path("/res_mov_expediente/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResMovExpediente(@QueryParam("pId") Integer pIdQueja,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getMovimientoExpediente(pIdQueja,pToken);
    }
    
    @GET
    @Path("/ced_noti_cit_con_jur/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotificacionCitacionConJuridico(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionCitacionJuridico(pId_audiencia,"C",pToken);
    }
    
    @GET
    @Path("/ced_noti_cit_pro_jur/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotificacionCitacionProJuridico(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionCitacionJuridico(pId_audiencia,"P",pToken);
    }
    
    @GET
    @Path("/ced_cit_con_jur/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCitacionConJuridico(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaCitacionConsJuridico(pId_audiencia,pToken);
    }
    
    
    @GET
    @Path("/ced_cit_pro_jur/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCitacionProJuridico(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaCitacionProJuridico(pId_audiencia,pToken);
    }
    
    
    @GET
    @Path("/res_ced_noti_res_jur/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotificacionResJuridico(@QueryParam("pIdQueja") Integer pIdQueja,@QueryParam("es_CP") String es_CP,@QueryParam("esResultadoResolucion") String esResultadoResolucion,@PathParam("pToken") String pToken) {
         CedulaNotificacionDto vpCedulaNotificacionDto=new CedulaNotificacionDto();
         vpCedulaNotificacionDto.setEsCP(es_CP);
         vpCedulaNotificacionDto.setEsResultadoResolucion(esResultadoResolucion);
         vpCedulaNotificacionDto.setIdQueja(pIdQueja);
         vpCedulaNotificacionDto.setToken(pToken);
         
        return tipoRegQuejaService.getCedulaNotificacionResolucionJuridico(vpCedulaNotificacionDto);
    }
    
    @GET
    @Path("/cedula-noti-cit-correo/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotificacionCitCorreo(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionCitacionCorreo(pId_audiencia,pToken);
    }
    
    @GET
    @Path("/cedula-noti-res-correo/{pId_audiencia}/{pToken}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCedulaNotificacionResCorreo(@PathParam("pId_audiencia") Integer pId_audiencia,@PathParam("pToken") String pToken) {
        return tipoRegQuejaService.getCedulaNotificacionResCorreo(pId_audiencia,pToken);
    }
    
    
    @GET
    @Path("/caratula/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCaratulaJuridico(@QueryParam("pIdQueja") Integer pIdQueja,@PathParam("token") String token) {
        return tipoRegQuejaService.getCaratulaJuridico(pIdQueja, token);
    }
    
    @GET
    @Path("/apertura-prueba/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getAperturaPrueba(@QueryParam("pIdResultAudiencia") Integer pIdResultAudiencia,@PathParam("token") String token) {
        return tipoRegQuejaService.getAperturaPrueba(pIdResultAudiencia, token);
    }
    
    @GET
    @Path("/declaracion-rebeldia/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getDeclaracionRebeldia(@QueryParam("pIdResultAudiencia") Integer pIdResultAudiencia,@PathParam("token") String token) {
        return tipoRegQuejaService.getDeclaracionRebeldia(pIdResultAudiencia, token);
    }
    
    @GET
    @Path("/resolucion-final/{token}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getResolucionFInal(@QueryParam("pIdResultAudiencia") Integer pIdResultAudiencia,@PathParam("token") String token) {
        return tipoRegQuejaService.getResolucionFinal(pIdResultAudiencia, token);
    }
    
}
