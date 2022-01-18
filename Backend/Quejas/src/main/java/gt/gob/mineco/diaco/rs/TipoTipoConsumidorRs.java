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
import gt.gob.mineco.diaco.model.TipoDepartamento;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import javax.ws.rs.POST;


@Path("/tipos-tipo-consumidor")
@RequestScoped
public class TipoTipoConsumidorRs {

	@Inject
	private TipoAreaComunService tipoService;
	
	@GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTiposConsumidor(@PathParam("token") String token){
		return tipoService.listTipoTipoConsumidor(token);
	}
        
     
        
        
        @POST
        @Path("/save/{nombre}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveTipoTipoConsumidor(@PathParam("token") String token,            
                @PathParam("nombre") String nombre,
                @PathParam("usuario") String usuario
                ){
         
		return tipoService.saveTipoTipoConsumidor(0, nombre, token, usuario,"INSERT");
	}
       
        @POST
        @Path("/update/{id}/{nombre}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateTipoTipoConsumidor(@PathParam("token") String token,
                @PathParam("id") int id,
                @PathParam("nombre") String nombre,
                @PathParam("usuario") String usuario
                )
        {
         
		return tipoService.saveTipoTipoConsumidor(id, nombre, token, usuario,"UPDATE");
	}
        
         @POST
        @Path("/delete/{id}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteTipoTipoConsumidor(@PathParam("token") String token,
                @PathParam("id") int id,
                @PathParam("usuario") String usuario
                )
        {
            
		return tipoService.saveTipoTipoConsumidor(id, "", token, usuario,"DELETE");
	}
         
        
	
}
