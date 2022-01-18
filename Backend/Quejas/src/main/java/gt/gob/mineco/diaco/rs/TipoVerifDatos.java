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

import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormConcVirtInt;

@Path("/tipos-verifdatos")
@RequestScoped
public class TipoVerifDatos {

	@Inject
	private TipoAreaComunService tipoareacomun;
	
	@GET
        @Path("/getInt/{idqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getVerifDatos(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token){
		return tipoareacomun.getVerifDatosInt(idqueja,token);
	}
	
	@POST
	@Path("/saveInt")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveVerifDatos(FormConcVirtInt formulario) {
		return tipoareacomun.saveVerifDatosInt(formulario);
	}
	
        @GET
        @Path("/FlujoGuia/{idqueja}/{id_flujo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updFlujoGuia(@PathParam("idqueja") Integer idqueja, @PathParam("id_flujo") Integer id_flujo, @PathParam("token") String token){
		return tipoareacomun.VerifDatosFlujoGuia(idqueja,id_flujo,token);
	}
        
}
