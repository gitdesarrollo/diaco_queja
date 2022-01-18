package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.text.SimpleDateFormat;
import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormBusqueda;
import gt.gob.mineco.diaco.util.FormConsumidor;


@Path("/tipos-consumidor")
@RequestScoped
public class TipoConsumidorRs {

	@Inject
	private TipoAreaComunService tipoConsumidorService;

       	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getConsumidor(@PathParam("token") String token){
		return tipoConsumidorService.listTiposConsumidor(token);
	}

	@GET
        @Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getConsumidor(@PathParam("id") Integer id, @PathParam("token") String token){
		return tipoConsumidorService.listTiposConsumidor(id,token);
	}
	
        
        @GET
        @Path("/find/{nit}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs findConsumidor(@PathParam("nit") String nit, @PathParam("token") String token){
		return tipoConsumidorService.findConsumidorByNit(nit, token);
	}
        
        @GET
        @Path("/findDpi/{dpi}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs findConsumidor1(@PathParam("dpi") String dpi, @PathParam("token") String token){
		return tipoConsumidorService.findConsumidorByDpi(dpi, token);
	}
        
        @POST
        @Path("/customf")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getConsumidor(FormBusqueda formBusqueda){
		return tipoConsumidorService.CustomFilterConsumidor(formBusqueda);
	}
        
        @POST
         @Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveConsumidor(FormConsumidor formConsumidor){
		return tipoConsumidorService.saveConsumidor(formConsumidor);
	}
         @POST
         @Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateConsumidor(FormConsumidor formConsumidor){
		return tipoConsumidorService.updateConsumidor(formConsumidor);
	}
        
         @POST
         @Path("/delete/{idConsumidor}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteConsumidor(@PathParam("token") String token,
                @PathParam("usuario") String usuario,
                @PathParam("idConsumidor") Integer idConsumidor){
		return tipoConsumidorService.deleteConsumidor(idConsumidor,usuario,token);
	}
        
}
