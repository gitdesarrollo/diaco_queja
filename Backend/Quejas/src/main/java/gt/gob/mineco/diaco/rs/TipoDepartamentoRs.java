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


@Path("/tipos-dpto")
@RequestScoped
public class TipoDepartamentoRs {

	@Inject
	private TipoAreaComunService tipoDptoService;
	
	@GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDepartamentos(@PathParam("token") String token){
		return tipoDptoService.listDepartamento(token);
	}
        
        @GET
        @Path("/depa/{codigoDepartamento}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDepartamentos(
                @PathParam("codigoDepartamento") Integer codigoDepartamento,
                @PathParam("token") String token){
		return tipoDptoService.getDepartamento(codigoDepartamento,token);
	}
        
        
        @POST
        @Path("/save/{nombreDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveDepartamento(@PathParam("token") String token,            
                @PathParam("nombreDepartamento") String nombreDepartamento
                ){
         
          
		return tipoDptoService.saveDepartamento(nombreDepartamento,token);
	}
       
        @POST
        @Path("/update/{codigoDepartamento}/{nombreDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateDepartamento(@PathParam("token") String token,
                @PathParam("codigoDepartamento") int codigoDepartamento,
                @PathParam("nombreDepartamento") String nombreDepartamento
                )
        {
         
          return tipoDptoService.updateDepartamento(codigoDepartamento,nombreDepartamento,token);
	}
        
         @POST
        @Path("/delete/{codigoDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteDepartamento(@PathParam("token") String token,
                @PathParam("codigoDepartamento") int codigoDepartamento          
                )
        {
          return tipoDptoService.deleteDepartamento(codigoDepartamento,token);
	}
         
        
	
}
