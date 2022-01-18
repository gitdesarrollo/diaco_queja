package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.TipoBitacora;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormBitacora;

@Path("/tipos-bitacora")
@RequestScoped
public class TipoBitacoraRs {

	@Inject
	private TipoAreaComunService tipoBitacoraService;
	
	@GET
        @Path("/{idqueja}/{idflujo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoBitacoras(@PathParam("idqueja") Integer idqueja, @PathParam("idflujo") Integer idflujo, @PathParam("token") String token){
		return tipoBitacoraService.listTiposBitacora(idqueja,idflujo,token);
	}
	
	@GET
	@Path("/single/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoBitacora(@PathParam("id") double id, @PathParam("token") String token) {
		return tipoBitacoraService.getTipoBitacora(id,token);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoBitacora(FormBitacora formBitacora) {
		return tipoBitacoraService.addTipoBitacora(formBitacora);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateTipoBitacora(FormBitacora formBitacora) {
		return tipoBitacoraService.updateTipoBitacora(formBitacora);
	}
	
	@PUT
        @Path("/del")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteTipoBitacora(FormBitacora formBitacora) {
		return tipoBitacoraService.deleteTipoBitacora(formBitacora);
	}
}
