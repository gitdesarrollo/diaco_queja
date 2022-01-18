package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoSede;
import gt.gob.mineco.diaco.service.SedesServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/sedes")
@RequestScoped
public class SedesRs {
	@Inject
	SedesServiceImp sedesService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getSedes() {
		ResponseRs response = new ResponseRs();
		List<DiacoSede> sedes = sedesService.getAll();
		response.setValue(sedes);
		response.setCode(0L);
        response.setReason("OK");
        response.setTotal(sedes.size());
		return response;
	}
	
	/*
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseSegRs getSedes(@PathParam(value = "id") Integer codigoSede) {
        ResponseSegRs response = new ResponseSegRs();
        try {
            DiacoSede sede = sedesService.getBySedeId(codigoSede);
            response.setValue(sede);
            response.setCode(0L);
            response.setReason("OK");
            response.setTotal(1);
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("ERROR");
            response.setTotal(0);
        }
        return response;
    }*/
}
