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


@Path("/tipos-sede")
@RequestScoped
public class TipoSedeRs {

	@Inject
	private TipoAreaComunService areaComunService;
	
	@GET
	@Path("/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getSede(@PathParam("id") Integer id, @PathParam("token") String token) {
		return areaComunService.listSedeExcOne(id,token);
	}
	
        @GET
	@Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getSede(@PathParam("token") String token) {
		return areaComunService.listSedeAll(token);
	}
        
        @GET
	@Path("/colasedes/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAllColaSede(@PathParam("token") String token) {
		return areaComunService.getAllTipoColaSedes(token);
	}
	
	       @POST
	@Path("/save/{nombreSede}/{codigoMunicipio}/{direccionAvenida}/{direccionCalle}/{direccionZona}/{direccionDetalle}/{avenidaOCalle}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveSede(@PathParam("token") String token,
                @PathParam("codigoMunicipio") Integer codigoMunicipio,
                @PathParam("nombreSede") String nombreSede,
                @PathParam("direccionAvenida") String direccionAvenida,
                @PathParam("direccionCalle") String direccionCalle,
                @PathParam("direccionZona") String direccionZona,
                @PathParam("avenidaOCalle")  String avenidaOCalle,
                @PathParam("direccionDetalle") String direccionDetalle,
                @PathParam("usuario") String usuario) {
		return areaComunService.saveSede(nombreSede, codigoMunicipio,
                        direccionAvenida, direccionCalle, direccionZona, direccionDetalle, avenidaOCalle,usuario, token);
	}
        
        
           @POST
	@Path("/update/{idDiacoSede}/{nombreSede}/{codigoMunicipio}/{direccionAvenida}/{direccionCalle}/{direccionZona}/{direccionDetalle}/{avenidaOCalle}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateSede(@PathParam("token") String token,
                @PathParam("idDiacoSede") Integer idDiacoSede,
                @PathParam("codigoMunicipio") Integer codigoMunicipio,
                @PathParam("nombreSede") String nombreSede,
                @PathParam("direccionAvenida") String direccionAvenida,
                @PathParam("direccionCalle") String direccionCalle,
                @PathParam("direccionZona") String direccionZona,
                @PathParam("direccionDetalle") String direccionDetalle,
                @PathParam("avenidaOCalle") String avenidaOCalle,
                @PathParam("usuario") String usuario) {
		return areaComunService.updateSede(idDiacoSede, nombreSede,
                        codigoMunicipio, direccionAvenida, direccionCalle,
                        direccionZona, direccionDetalle, avenidaOCalle,usuario, token);
	}
        
       @POST
	@Path("/delete/{idDiacoSede}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteSede(@PathParam("token") String token,
                @PathParam("idDiacoSede") Integer idDiacoSede,
                @PathParam("usuario") String usuario
               ) {
		return areaComunService.deleteSede(idDiacoSede, usuario,token);
	}   
    
        
        @GET
	@Path("/colasxusuario/{token}/{usuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAllColaSede(@PathParam("token") String token, @PathParam("usuario") Integer usuario) {
		return areaComunService.getAllTipoColaxUsuario(token, usuario);
	}
        
}
