package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoProveedorComun;
import gt.gob.mineco.diaco.service.ProveedoresComunesServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/proveedores-comunes")
@RequestScoped
public class ProveedoresComunesRs {
	@Inject
	ProveedoresComunesServiceImp proveedoresComunesService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAll() {
		ResponseRs response = new ResponseRs();
		List<DiacoProveedorComun> proveedores = proveedoresComunesService.getAll();
                System.out.println("proveedores = " + proveedores);
		response.setValue(proveedores);
		response.setCode(0L);
        response.setReason("OK");
        response.setTotal(proveedores.size());
        return response;
	}
}
