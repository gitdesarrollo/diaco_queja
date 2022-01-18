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
import gt.gob.mineco.diaco.util.FormVisitaCampo;

@Path("/tipos-visitacampo")
@RequestScoped
public class TipoVisitaCampoRs {

	@Inject
	private TipoAreaComunService tipoAreaComunService;
	
	@GET
        @Path("/{idqueja}/{idflujo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoVisitaCampo(@PathParam("idqueja") Integer idqueja, @PathParam("idflujo") Integer idflujo, @PathParam("token") String token){
		return tipoAreaComunService.listVisitaCampo(idqueja,idflujo,token);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveTipoVisitaCampo(FormVisitaCampo formVisitaCampo) {
		return tipoAreaComunService.saveVisitaCampo(formVisitaCampo);
	}
	
	@PUT
        @Path("/del")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteTipoVisitaCampo(FormVisitaCampo formVisitaCampo) {
		return tipoAreaComunService.deleteVisitaCampo(formVisitaCampo);
	}
}
