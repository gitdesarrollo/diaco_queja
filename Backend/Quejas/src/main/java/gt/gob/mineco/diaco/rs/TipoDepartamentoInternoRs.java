package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import gt.gob.mineco.diaco.model.TipoDepartamentoInterno;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import javax.ws.rs.POST;


@Path("/tipos-dpto-interno")
@RequestScoped
public class TipoDepartamentoInternoRs {

	@Inject
	private TipoAreaComunService tipoDptoService;
	
	@GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListDepartamentoInterno(@PathParam("token") String token){
		return tipoDptoService.listDeptoInterno(token);
                
	}
        
        @POST
        @Path("/save/{nombreDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveDepartamentoInterno(@PathParam("token") String token,            
                @PathParam("nombreDepartamento") String nombreDepartamento
                ){
         
          
		return tipoDptoService.saveDepartamentoInterno(nombreDepartamento,token);
	}
       
        @POST
        @Path("/update/{codigoDepartamento}/{nombreDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateDepartamentoInterno(@PathParam("token") String token,
                @PathParam("codigoDepartamento") int codigoDepartamento,
                @PathParam("nombreDepartamento") String nombreDepartamento
                )
        {
         
          return tipoDptoService.updateDepartamentoInterno(codigoDepartamento,nombreDepartamento,token);
	}
        
         @POST
        @Path("/delete/{codigoDepartamento}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteDepartamentoInterno(@PathParam("token") String token,
                @PathParam("codigoDepartamento") int codigoDepartamento          
                )
        {
          return tipoDptoService.deleteDepartamentoInterno(codigoDepartamento,token);
	}
         
        
	
}
