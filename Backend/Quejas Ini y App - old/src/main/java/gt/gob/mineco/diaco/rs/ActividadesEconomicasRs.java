package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoActividadEconomica;
import gt.gob.mineco.diaco.model.DiacoTipoComercio;
import gt.gob.mineco.diaco.service.ActividadesEconomicasServiceImp;
import gt.gob.mineco.diaco.service.TiposComercioServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/actividades-economicas")
@RequestScoped
public class ActividadesEconomicasRs {
	@Inject
	ActividadesEconomicasServiceImp actividadesEconomicasService;
	
	@Inject
	TiposComercioServiceImp tiposComercioService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getAll() {
		ResponseRs response = new ResponseRs();
		List<DiacoActividadEconomica> actividades = actividadesEconomicasService.getAll();
		response.setValue(actividades);
		response.setCode(0L);
        response.setReason("OK");
        response.setTotal(actividades.size());
		return response;
	}
	
	@GET
	@Path("/{id}/tipos-comercio")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTiposComercio(@PathParam(value="id") Integer idActividadEconomica) {
		ResponseRs response = new ResponseRs();
		List<DiacoTipoComercio> tiposComercio = tiposComercioService.getByActividadEconomica(idActividadEconomica);
		response.setValue(tiposComercio);
		response.setCode(0L);
        response.setReason("OK");
        response.setTotal(tiposComercio.size());
		return response;
	}
}
