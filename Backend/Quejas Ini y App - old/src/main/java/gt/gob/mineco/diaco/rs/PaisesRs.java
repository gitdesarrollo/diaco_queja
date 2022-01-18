package gt.gob.mineco.diaco.rs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoPais;
import gt.gob.mineco.diaco.service.PaisesServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/paises")
@RequestScoped
public class PaisesRs {

    @Inject
    PaisesServiceImp paisesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getPaises() {
        ResponseRs response = new ResponseRs();
        List<DiacoPais> paises = paisesService.getAll();
        response.setValue(paises);
        response.setCode(0L);
        response.setReason("OK");
        response.setTotal(paises.size());
        return response;
    }
}
