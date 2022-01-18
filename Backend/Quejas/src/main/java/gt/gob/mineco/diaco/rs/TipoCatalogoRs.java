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
import gt.gob.mineco.diaco.model.TipoCatalogo;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;


@Path("/tipos-catalogo")
@RequestScoped
public class TipoCatalogoRs {

	@Inject
	private TipoAreaComunService tipoCatalogoService;
	
	@GET
	@Path("/{tabla}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getCatalogo(@PathParam("tabla") String tabla, @PathParam("token") String token) {
		return tipoCatalogoService.listCatalogo(tabla,token);
	}
	
        @GET
	@Path("/res_aud/{id_resultado}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getCatalogo(@PathParam("id_resultado") Integer id_resultado, @PathParam("token") String token) {
		return tipoCatalogoService.listResultadoAudiencia(id_resultado,token);
	}
        
        @GET
	@Path("/dpto_interno/mov_hacia/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDptoInternoMovHacia(@PathParam("token") String token) {
		return tipoCatalogoService.listDeptoInternoMovHacia(token);
	}
        
        @GET
	@Path("/dpto_interno/mov_haciaVyV/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDptoInternoMovHaciaVyV(@PathParam("token") String token) {
		return tipoCatalogoService.listDeptoInternoMovHaciaVyV(token);
	}
        
        @GET
	@Path("/dpto_interno/mov_haciaJur/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDptoInternoMovHaciaJur(@PathParam("token") String token) {
		return tipoCatalogoService.listDeptoInternoMovHaciaJur(token);
	}
        
        @GET
	@Path("/dpto_interno/mov_hacia_sinjefe/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDptoInternoMovHaciaSinJefe(@PathParam("token") String token) {
		return tipoCatalogoService.listDeptoInternoMovHaciaSinJefe(token);
	}
        
        @GET
	@Path("/puesto/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getPuesto(@PathParam("token") String token) {
		return tipoCatalogoService.getPuestos(token);
	}
        
        @GET
	@Path("/portallinks")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getPortalLinks() {
		return tipoCatalogoService.getPortalLinks();
	}
        
}
