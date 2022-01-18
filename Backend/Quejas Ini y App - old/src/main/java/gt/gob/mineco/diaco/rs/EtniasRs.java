package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoEtnia;
import gt.gob.mineco.diaco.service.EtniasServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/etnias")
@RequestScoped
public class EtniasRs {
	@Inject
	EtniasServiceImp etniasService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getEtnias() {
		ResponseRs response = new ResponseRs();
		List<DiacoEtnia> etnias = etniasService.getAll();
		response.setValue(etnias);
		return response;
	}	
}
