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

import gt.gob.mineco.diaco.model.TipoComConsumidor;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormComConsumidor;

@Path("/tipos-comconsumidor")
@RequestScoped
public class TipoComConsumidorRs {

	@Inject
	private TipoAreaComunService tipoComConsumidorService;
	
        @GET
	@Path("/{idqueja}/{token}/{flujo}")//CUANDO SE ABRE EL BOTON DE COMUNICACION PERMANENTE DE MAIN CONSUMIDOR
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTiposComConsumidor(@PathParam("idqueja") Integer idquejapaso, @PathParam("token") String token, @PathParam("flujo") Integer flujo) {
		return tipoComConsumidorService.listTiposComConsumidor(idquejapaso, token, flujo);
	}
        
	@GET
	@Path("/single/{idquejapaso}/{token}/{flujo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoComConsumidor(@PathParam("idquejapaso") Integer idquejapaso, @PathParam("token") String token, @PathParam("flujo") Integer flujo) {
		return tipoComConsumidorService.getTipoComConsumidor(idquejapaso, token, flujo);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)// CUANDO SE GUARDA UNA COMUNICACION PERMANENTE
	public ResponseRs addTipoComConsumidor(FormComConsumidor formComConsumidor) {
		return tipoComConsumidorService.addTipoComConsumidor(formComConsumidor);
	}
	
}
