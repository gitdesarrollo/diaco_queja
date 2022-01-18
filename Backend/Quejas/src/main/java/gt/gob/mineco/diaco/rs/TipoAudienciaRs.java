package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormAudiencia;
import gt.gob.mineco.diaco.util.FormResAudiencia;
import gt.gob.mineco.diaco.util.FormSimple;
import javax.ws.rs.POST;


@Path("/tipos-audiencia")
@RequestScoped
public class TipoAudienciaRs {

	@Inject
	private TipoAreaComunService areaComunService;
	
	@GET
        @Path("/{idqueja}/{no_audiencia}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoComConsumidor(@PathParam("idqueja") Integer idqueja,@PathParam("no_audiencia") Integer no_audiencia,@PathParam("token") String token) {
		return areaComunService.getAudienciaxIdQueja(idqueja,no_audiencia,token);
	}
        
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs SaveAudiencia(FormAudiencia formAudiencia) {
		return areaComunService.saveAudiencia(formAudiencia);
	}
	
        //res audiencia
        @POST
        @Path("/res_audiencia/add")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveResAudiencia(FormResAudiencia formulario) {
		return areaComunService.saveResAudiencia(formulario);
	}
        
        @POST
        @Path("/res_audiencia/upd")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updResAudiencia(FormResAudiencia formulario) {
		return areaComunService.updResAudiencia(formulario);
	}
        
        @POST
        @Path("/res_audiencia/del")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs delResAudiencia(FormResAudiencia formulario) {
		return areaComunService.delResAudiencia(formulario);
	}
        
        @GET
        @Path("/res_audiencia/get/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getResAudiencia(@PathParam("id") Integer id, @PathParam("token") String token) {
		return areaComunService.GetResAudiencia(id, token);
	}
        
        @GET
        @Path("/res_audiencia/getall/{id_queja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getallResAudiencia(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
		return areaComunService.GetAllResAudiencia(id_queja, token);
	}
        
        //resolucion final
        
        @GET
        @Path("/res_final/getcorrelativo/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getallResAudiencia(@PathParam("token") String token) {
		return areaComunService.GetCorrelativoResFinal(token);
	}

        @POST
        @Path("/res_final/add")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveResFinal(FormSimple formulario) {
		return areaComunService.saveResFinal(formulario);
	}
        
        @POST
        @Path("/res_final/upd")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updResFinal(FormSimple formulario) {
		return areaComunService.updResFinal(formulario);
	}
        
        @POST
        @Path("/res_final/del")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs delResFinal(FormSimple formulario) {
		return areaComunService.delResFinal(formulario);
	}
        
        @GET
        @Path("/res_final/get/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getResFinal(@PathParam("id") Integer id, @PathParam("token") String token) {
		return areaComunService.GetResFinal(id, token);
	}
        
        @GET
        @Path("/res_final/getall/{id_queja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getallResFinal(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
		return areaComunService.GetAllResFinal(id_queja, token);
	}

        
}
