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
import gt.gob.mineco.diaco.model.TipoCatalogo;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormComConsumidor;
import gt.gob.mineco.diaco.util.FormQuejaFinalizada;
import javax.ws.rs.POST;


@Path("/tipos-finalizado")
@RequestScoped
public class TipoFinalizadoRs {

	@Inject
	private TipoAreaComunService tipoCatalogoService;
	
	@GET
	@Path("/poa/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMotivoPOA(@PathParam("token") String token) {
		return tipoCatalogoService.listMotivoPOA(token);
	}
	
        @GET
	@Path("/checkarchivo/{id_queja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs checkArchivo(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
		return tipoCatalogoService.checkArchivo(id_queja, token);
	}
        
        @GET
	@Path("/motivofinalizado/{tipo}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMotivoQuejaFinalizada(@PathParam("tipo") String tipo, @PathParam("token") String token) {
		return tipoCatalogoService.listMotivoQuejaFinalizada(tipo,token);
	}

        @GET
	@Path("/quejafinalizada/{id_queja}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getQuejaFinalizada(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
		return tipoCatalogoService.getQuejaFinalizada(id_queja,token);
	}

        @POST
        @Path("/quejafinalizada")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoQuejaFinalizada(FormQuejaFinalizada formQuejaFinalizada) {
		return tipoCatalogoService.saveQuejaFinalizada(formQuejaFinalizada);
	}
        
        @POST
        @Path("/quejafinalizadaSP")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoQuejaFinalizadaSP(FormQuejaFinalizada formQuejaFinalizada) {
		return tipoCatalogoService.saveQuejaFinalizadaSP(formQuejaFinalizada);
	}
        
        @POST
        @Path("/quejafinalizadaVyV")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs addTipoQuejaFinalizadaVyv(FormQuejaFinalizada formQuejaFinalizada) {
		return tipoCatalogoService.saveQuejaFinalizadaVyV(formQuejaFinalizada);
	}
        
        @GET
	@Path("/quejafinmotivo/{padre}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getQuejaFinMotivo(@PathParam("padre") Integer padre, @PathParam("token") String token) {
		return tipoCatalogoService.listMotivoQuejaFinMotivo(padre,token);
	}
        
}
