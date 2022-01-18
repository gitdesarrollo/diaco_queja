
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
import javax.ws.rs.POST;

@Path("/tipos-motivo-queja")
@RequestScoped
public class TipoMotivoQuejaRs {
    @Inject
	private TipoAreaComunService tipoMotivoQuejaService;
	
        @GET
        @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListMotivoQueja(@PathParam("token") String token){
		return tipoMotivoQuejaService.getListMotivoQueja(token);
	}
     
        @POST
        @Path("/save/{motivo}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveMotivoQueja(@PathParam("motivo") String motivo,@PathParam("usuario") String usuario,
                @PathParam("token") String token){
		return tipoMotivoQuejaService.saveMotivoQueja(motivo, usuario, token);
	}
        
        @POST
        @Path("/update/{idMotivoQueja}/{motivo}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateMotivoQueja(@PathParam("idMotivoQueja") Integer idMotivoQueja,@PathParam("motivo") String motivo,
                @PathParam("usuario") String usuario,@PathParam("token") String token){
		return tipoMotivoQuejaService.updateMotivoQueja(idMotivoQueja, motivo, usuario,"UPDATE", token);
	}
    
        @POST
        @Path("/delete/{idMotivoQueja}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteMotivoQueja(@PathParam("idMotivoQueja") Integer idMotivoQueja, 
                @PathParam("usuario") String usuario,
                @PathParam("token") String token){
            return tipoMotivoQuejaService.updateMotivoQueja(idMotivoQueja,"HET", usuario,"DELETE", token);
	}
}


