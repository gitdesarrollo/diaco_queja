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
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormRegistro;
import gt.gob.mineco.diaco.util.FormSimple;


@Path("/tipos-confaccion")
@RequestScoped
public class TipoConfirmarAccionRs {

	@Inject
	private TipoAreaComunService tipoService;
	
	@GET
        @Path("/{id_usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getConfAccion(@PathParam("id_usuario") Integer id_usuario, @PathParam("token") String token){
		return tipoService.getConfirmarAccion(id_usuario,token);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoComConsumidor(FormSimple formsimple) {
		return tipoService.UpdateConfirmarAccion(formsimple);
	}
        
        @POST
        @Path("/ultima_aceptacion")
        @Produces(MediaType.APPLICATION_JSON)
        public ResponseRs addTipoComConsumidor(FormRegistro formReg_ComPerm) {
            return tipoService.getUltimaAceptacion(formReg_ComPerm);
        }

//	@GET
//        @Path("/asignaAtCon/{id_queja}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ResponseRs getAsignaAtCon(@PathParam("id_queja") Integer id_queja){
//		return tipoService.savePubConfirmarAccionAtCon(id_queja);
//	}
        
//        @GET
//        @Path("/asignaAtCon/{id_queja}/{token}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ResponseRs getAsignaAtCon(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token){
//		return tipoService.savePubConfirmarAccionAtCon(id_queja);
//	}
//        
        @GET
        @Path("/asignaAtCon/{id_queja}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAsignaAtConTest(@PathParam("id_queja") Integer id_queja){
		return tipoService.savePubConfirmarAccionAtCon(id_queja);
	}
        
}
