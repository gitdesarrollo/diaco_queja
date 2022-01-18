package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.text.SimpleDateFormat;
import gt.gob.mineco.diaco.model.TipoCatalogo;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormUsuarioSrch;


@Path("/tipos-usuario")
@RequestScoped
public class TipoUsuarioRs {

	@Inject
	private TipoAreaComunService tipoService;
	
	@GET
        @Path("/usuario_xflujo/{id_flujo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioxFlujo(@PathParam("id_flujo") Integer id_flujo, @PathParam("token") String token) {
		return tipoService.listUsuario(token, id_flujo);
	}
	
        @GET
	@Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuario(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoService.getUsuario(id,token);
	}
        
        @GET
	@Path("/usuario_conf/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioConf(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoService.getUsuarioConfById(id,token);
	}
        
        @GET
	@Path("/usuario_confpp/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioConfPP(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoService.getUsuarioConfPPById(id,token);
	}
        
        @GET
	@Path("/asignacola/{id}/{id_flujo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioAsignaCola(@PathParam("id") Integer id, @PathParam("id_flujo") Integer id_flujo, @PathParam("token") String token) {
		return tipoService.getUsuarioAsignaCola(id,id_flujo,token);
	}
        
        @POST
	@Path("/customfilter")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioCustomFilter(FormUsuarioSrch formUsuarioSrch) {
		return tipoService.getUsuarioCustomFilter(formUsuarioSrch);
	}
        
        @POST
	@Path("/customfilterconcprev")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getUsuarioCustomFilterConcPrev(FormUsuarioSrch formUsuarioSrch) {
		return tipoService.getUsuarioCustomFilterConcPrev(formUsuarioSrch);
	}
}
