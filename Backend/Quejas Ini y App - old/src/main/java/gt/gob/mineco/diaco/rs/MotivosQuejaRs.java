package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoMotivoQueja;
import gt.gob.mineco.diaco.service.MotivosQuejaServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/motivos-queja")
@RequestScoped
public class MotivosQuejaRs {
	@Inject
	MotivosQuejaServiceImp motivosQuejaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMotivosQueja() {
		ResponseRs response = new ResponseRs();
		List<DiacoMotivoQueja> motivosQueja = motivosQuejaService.getAll();
		response.setValue(motivosQueja);
		return response;
	}
}
