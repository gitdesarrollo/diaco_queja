package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoTipoComercio;
import gt.gob.mineco.diaco.service.TiposComercioServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/tipos-comercio")
@RequestScoped
public class TiposComercioRs {
	@Inject
	TiposComercioServiceImp tiposComercioService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAll() {
		ResponseRs response = new ResponseRs();
		List<DiacoTipoComercio> tipos = tiposComercioService.getAll();
		response.setValue(tipos);
		return response;
	}
}
