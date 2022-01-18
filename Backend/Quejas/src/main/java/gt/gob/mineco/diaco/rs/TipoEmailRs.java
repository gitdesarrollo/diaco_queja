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
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;


@Path("/tipos-email")
@RequestScoped
public class TipoEmailRs {

	@Inject
	private TipoAreaComunService tipoEmailService;
	
	@GET
        @Path("/{id}/{type}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getEmail(@PathParam("id") Integer id, @PathParam("type") String type, @PathParam("token") String token){
		return tipoEmailService.listEmail(id, type,token);
	}

	@GET
        @Path("/magiclink/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMagicLink(@PathParam("id") String id){
		return tipoEmailService.getMagicLink(id);
	}
        
        @GET
	@Path("/resetpw/{linkcode}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs ResetPW(@PathParam("linkcode") String linkcode) {
		return tipoEmailService.SaveEmailNewPassword(linkcode);
	}
        
}
