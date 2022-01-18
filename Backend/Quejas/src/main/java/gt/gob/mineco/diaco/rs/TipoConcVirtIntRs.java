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
import gt.gob.mineco.diaco.util.FormConcVirtInt;
import javax.ws.rs.POST;


@Path("/tipos-concvirtint")
@RequestScoped
public class TipoConcVirtIntRs {

	@Inject
	private TipoAreaComunService areaComunService;
	
	@GET
        @Path("/{idqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoComConsumidor(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token) {
		return areaComunService.getConcVirtInt(idqueja,token);
	}
        
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoComConsumidor(FormConcVirtInt formulario) {
		return areaComunService.saveConcVirtInt(formulario);
	}
	
}
