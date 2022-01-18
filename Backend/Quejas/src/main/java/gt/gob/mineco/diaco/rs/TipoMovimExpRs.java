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
import gt.gob.mineco.diaco.util.FormAudiencia;
import gt.gob.mineco.diaco.util.FormMovimExp;
import javax.ws.rs.POST;


@Path("/tipos-movimexp")
@RequestScoped
public class TipoMovimExpRs {

	@Inject
	private TipoAreaComunService areaComunService;
	
	@GET
        @Path("/{idqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoMovimExp(@PathParam("idqueja") Integer idqueja, @PathParam("token") String token) {
		return areaComunService.getMovimExpxIdQueja(idqueja,token);
	}
        
        @GET
        @Path("/top5/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoMovimExp(@PathParam("token") String token) {
		return areaComunService.getMovimExpTop5(token,1);
	}
        @GET
        @Path("/top5VyV/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoMovimExpVyV(@PathParam("token") String token) {
		return areaComunService.getMovimExpTop5(token,3);
	}
        @GET
        @Path("/top5Jur/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoMovimExpJur(@PathParam("token") String token) {
		return areaComunService.getMovimExpTop5(token,2);
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoMovimExp(FormMovimExp formulario) {
		return areaComunService.saveMovimExp(formulario);
	}
        
        @POST
        @Path("/pre")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoPreMovimExp(FormMovimExp formulario) {
		return areaComunService.savePreMovimExp(formulario);
	}
        @POST
        @Path("/preVyV")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoPreMovimExpVyV(FormMovimExp formulario) {
		return areaComunService.savePreMovimExpVyV(formulario);
	}     
        @POST
        @Path("/preJur")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoPreMovimExpJur(FormMovimExp formulario) {
		return areaComunService.savePreMovimExpJur(formulario);
	}  
        
        @POST
        @Path("/ind")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoIndMovimExp(FormMovimExp formulario) {
		return areaComunService.saveMovimExpInd(formulario);
	}
        
        @POST
        @Path("/indVyV")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoIndMovimExpVyV(FormMovimExp formulario) {
		return areaComunService.saveMovimExpIndVyV(formulario);
	}
        
        @POST
        @Path("/indJur")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoIndMovimExpJur(FormMovimExp formulario) {
		return areaComunService.saveMovimExpIndJur(formulario);
	}
}
