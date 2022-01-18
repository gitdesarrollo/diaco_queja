package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.text.SimpleDateFormat;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;


@Path("/tipos-telefono")
@RequestScoped
public class TipoTelefonoRs {

	@Inject
	private TipoAreaComunService tipoTelefonoService;
	
	@GET
        @Path("/{id}/{type}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTelefono(@PathParam("id") Integer id, @PathParam("type") String type, @PathParam("token") String token){
		return tipoTelefonoService.listTelefono(id, type,token);
	}
	
}
