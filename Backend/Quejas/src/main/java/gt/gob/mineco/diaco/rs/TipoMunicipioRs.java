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
import javax.ws.rs.POST;


@Path("/tipos-muni")
@RequestScoped
public class TipoMunicipioRs {

	@Inject
	private TipoAreaComunService tipoMuniService;
	
        @GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMunicipios(@PathParam("token") String token){
		return tipoMuniService.listMunicipio(token);
	}
        
	@GET
        @Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMunicipios(@PathParam("id") Integer id, @PathParam("token") String token){
		return tipoMuniService.listMunicipio(id,token);
	}
        
        @GET
        @Path("/muni/{idMunicipio}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMunicipio(@PathParam("idMunicipio") Integer id, @PathParam("token") String token){
		return tipoMuniService.getMunicipio(id,token);
	}
        
        
        
        @POST
        @Path("/save/{codigoDepartamento}/{nombreMunicipio}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveMunicipio(@PathParam("codigoDepartamento") Integer codigoDepto, @PathParam("token") String token,
                 @PathParam("nombreMunicipio") String nombreMunicipio,
                 @PathParam("usuario") String usuario
                ){
		return tipoMuniService.saveMunicipio(codigoDepto, token, nombreMunicipio,usuario);
	}
        
       @POST
        @Path("/update/{codigoDepartamento}/{codigoMunicipio}/{nombreMunicipio}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateMunicipio(@PathParam("codigoDepartamento") Integer codigoDepto, @PathParam("token") String token,
                @PathParam("codigoMunicipio") Integer codigoMunicipio,
                @PathParam("nombreMunicipio") String nombreMunicipio,
                @PathParam("usuario") String usuario
                ){
		return tipoMuniService.updateMunicipio(codigoDepto, token, codigoMunicipio, nombreMunicipio,usuario);
	}  
        @POST
        @Path("/delete/{codigoMunicipio}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteMunicipio(@PathParam("token") String token,
                @PathParam("codigoMunicipio") Integer codigoMunicipio,
                @PathParam("usuario") String usuario
                ){
		return tipoMuniService.deleteMunicipio(codigoMunicipio,token,usuario);
	}   
	
}
