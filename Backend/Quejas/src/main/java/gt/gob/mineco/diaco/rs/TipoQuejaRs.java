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

import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormViewMainQueja2;
import gt.gob.mineco.diaco.util.FormSimple;
import gt.gob.mineco.diaco.util.FormEstadoResolver;

@Path("/tipos-queja")
@RequestScoped
public class TipoQuejaRs {

	@Inject
	private TipoAreaComunService tipoQuejaService;
	
        /*@GET
	@Path("/test1")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoTest1(){
		return tipoQuejaService.listTiposQueja(token);
	}*/
        
	@GET
	@Path("/{token}")
        @Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoQuejas(@PathParam("token") String token){
		return tipoQuejaService.listTiposQueja(token);
	}
	
        @GET
        @Path("/{id}/{type}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoQuejas(@PathParam("id") Integer id,@PathParam("type") String type, @PathParam("token") String token){
		return tipoQuejaService.listTiposQueja(id,type,token);
	}
        
	@GET
        @Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoQueja(@PathParam("id") Integer id, @PathParam("token") String token) {
		return tipoQuejaService.getTipoQueja(id,token);
	}

        @GET
        @Path("/searchcodigo/{codigoqueja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getbyCodigoQueja(@PathParam("codigoqueja") String codigoqueja, @PathParam("token") String token) {
		return tipoQuejaService.getTipoQuejaCodigo(codigoqueja,token);
	}
        
	@POST
        @Path("/documentar")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs SaveDocumentar(FormSimple formSimple) {
		return tipoQuejaService.DocumentarTipoQueja(formSimple);
	}

        @POST
        @Path("/reasignar")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs ReasignarQueja(FormSimple formSimple) {
		return tipoQuejaService.ReasignarQuejaSave(formSimple);
	}    
        
	@GET
        @Path("/estados/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoEstadoQueja(@PathParam("token") String token) {
		return tipoQuejaService.getEstadosQueja(token);
	}
        
        @POST
        @Path("/est_resolver")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveEstadoResolver(FormEstadoResolver formestadoresolver) {
		return tipoQuejaService.saveEstadoResolver(formestadoresolver);
	}  
        
}
