package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoSede;
import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.service.MunicipiosServiceImp;
import gt.gob.mineco.diaco.service.SedesServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/municipios")
@RequestScoped
public class MunicipiosRs {
	@Inject
	MunicipiosServiceImp municipiosService;
	
	@Inject
	SedesServiceImp sedesService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMunicipios() {
		ResponseRs response = new ResponseRs();
		try {
            List<Municipio> municipios = municipiosService.getAll();
            response.setValue(municipios);
            response.setCode(0L);
            response.setReason("OK");
            response.setTotal(municipios.size());
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("ERROR");
            response.setTotal(0);
        }
        return response;
	}
	
	@GET
	@Path("/{id}/sedes")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getSedes(@PathParam(value="id") Integer codigoMunicipio) {
		ResponseRs response = new ResponseRs();
		try {
            List<DiacoSede> sedes = sedesService.getByMunicipio(codigoMunicipio);
            response.setValue(sedes);
            response.setCode(0L);
            response.setReason("OK");
            response.setTotal(sedes.size());
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("ERROR");
            response.setTotal(0);
        }
		return response;
	}
}
