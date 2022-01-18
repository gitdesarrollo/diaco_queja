package gt.gob.mineco.diaco.rs;

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

import gt.gob.mineco.diaco.model.TipoPasoQueja;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;

@Path("/tipos-pasoqueja")
@RequestScoped
public class TipoPasoQuejaRs {

	@Inject
	private TipoAreaComunService tipoPasoQuejaService;
	
	@GET
        @Path("/{idqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoPasoQuejas(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token){
		return tipoPasoQuejaService.listTiposPasoQueja(idqueja,token);
	}
	
	@GET
	@Path("/single/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoPasoQueja(@PathParam("id") double id, @PathParam("token") String token) {
		return tipoPasoQuejaService.getTipoPasoQueja(id,token);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoPasoQueja(TipoPasoQueja tipoPasoQueja) {
		return tipoPasoQuejaService.addTipoPasoQueja(tipoPasoQueja);
	}
	
}
