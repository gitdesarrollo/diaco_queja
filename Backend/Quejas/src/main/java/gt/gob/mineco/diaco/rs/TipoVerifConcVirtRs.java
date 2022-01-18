package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormVerifConcVirt;

@Path("/tipos-verifconcvirt")
@RequestScoped
public class TipoVerifConcVirtRs {

	@Inject
	private TipoAreaComunService tipoAreaComunService;
	
        @GET
	@Path("/{idqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTiposVerifConcVirt(@PathParam("idqueja") Integer idquejapaso, @PathParam("token") String token) {
		return tipoAreaComunService.listTiposVerifConcVirt(idquejapaso,token);
	}
        
	@GET
	@Path("/single/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoVerifConcVirt(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoAreaComunService.getTipoVerifConcVirt(id,token);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoVerifConcVirt(FormVerifConcVirt formVerifConcVirt) {
		return tipoAreaComunService.addTipoVerifConcVirt(formVerifConcVirt);
	}
        
        @GET
	@Path("/conf_resolucion/{respuesta}/{magiclink}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs setTipoVerifConcVirtResolucion(@PathParam("respuesta") String respuesta, @PathParam("magiclink") String magiclink) {
		return tipoAreaComunService.setTipoVerifConcVirtResolucion(respuesta, magiclink);
	}
	
}
