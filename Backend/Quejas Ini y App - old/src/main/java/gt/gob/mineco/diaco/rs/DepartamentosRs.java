package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.Departamento;
import gt.gob.mineco.diaco.model.Municipio;
import gt.gob.mineco.diaco.service.DepartamentosServiceImp;
import gt.gob.mineco.diaco.service.MunicipiosServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/departamentos")
@RequestScoped
public class DepartamentosRs {
	@Inject
	DepartamentosServiceImp departamentosService;
	
	@Inject
	MunicipiosServiceImp municipiosService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getDepartamentos() {
		ResponseRs response = new ResponseRs();
		try {
            List<Departamento> departamentos = departamentosService.getAll();
            response.setValue(departamentos);
            response.setCode(0L);
            response.setReason("OK");
            response.setTotal(departamentos.size());
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("ERROR");
            response.setTotal(0);
        }
		return response;
	}
	
	@GET
	@Path("/{id}/municipios")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getMunicipios(@PathParam(value="id") Integer codigoDepartamento) {
		ResponseRs response = new ResponseRs();
		try {
            List<Municipio> municipios = municipiosService.getByDepartamento(codigoDepartamento);
            response.setValue(municipios);
            response.setCode(0L);
            response.setReason("OK");
            response.setTotal(municipios.size());
        } catch (Exception e) {
            response.setCode(1L);
            response.setReason("ERROR");
            response.setTotal(0);
        };
		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseRs createDepartamento(Departamento departamento) {
		ResponseRs response = new ResponseRs();
		departamento = departamentosService.saveDepartamento(departamento);
		response.setValue(departamento);
		return response;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseRs deleteDepartamento(Departamento departamento) {
		ResponseRs response = new ResponseRs();
		departamento = departamentosService.deleteDepartamento(departamento);
		response.setValue(departamento);
		return response;
	}
	
	
}
