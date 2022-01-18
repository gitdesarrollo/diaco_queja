
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

@Path("/tipos-dia-inhabil")
@RequestScoped
public class TipoDiaInhabilRs {
    @Inject
    private TipoAreaComunService areaComunService;
	
   @GET
	@Path("/{id_departamento}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListDiaInhabil(@PathParam("token") String token,
                @PathParam("id_departamento") Integer id_departamento) {
		return areaComunService.listDiaInhabil(token, id_departamento);
	}
   
   @GET
	@Path("/list-depto/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListDepartamentoForDiaInhabil(@PathParam("token") String token) {
		return areaComunService.listDepartamentoForDiaInhabil(token);
	}
        
        
   @POST
	@Path("/save/{descripcion}/{mes}/{dia}/{id_departamento}/{usuario}/{token}/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveDiaInhabil(@PathParam("token") String token,
                @PathParam("id_departamento") Integer id_departamento,
                @PathParam("dia") Integer dia,
                @PathParam("mes") Integer mes,
                @PathParam("descripcion") String descripcion,
                @PathParam("usuario") String usuario
                ) {
		return areaComunService.saveDiaInhabil(token, id_departamento, dia, mes, descripcion,usuario);
	}
        
        
   @POST
	@Path("/update/{idDiaIn}/{descripcion}/{mes}/{dia}/{id_departamento}/{usuario}/{token}/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateDiaInhabil(@PathParam("token") String token,
                @PathParam("id_departamento") Integer id_departamento,
                @PathParam("dia") Integer dia,
                @PathParam("mes") Integer mes,
                @PathParam("descripcion") String descripcion,
                @PathParam("idDiaIn") Integer idDiaIn,
                @PathParam("usuario") String usuario
                ) {
		return areaComunService.updateDiaInhabil(idDiaIn, token, id_departamento, dia, mes, descripcion,usuario);
	}
        
     @POST
	@Path("/delete/{idDiaIn}/{usuario}/{token}/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteDiaInhabil(@PathParam("token") String token,
                @PathParam("idDiaIn") Integer idDiaIn,
        @PathParam("usuario") String usuario
        ) {
		return areaComunService.deleteDiaInhabil(token, idDiaIn,usuario);
	}     
        
        
}
