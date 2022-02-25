package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.service.ParametroGeneralService;
import gt.gob.mineco.diaco.util.ResponseRs;

/**
 *
 * @author jjolon
 */

 @Path("/parametros")
 @RequestScoped
public class Parametros {

    @Inject
    private ParametroGeneralService parametroService;

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ResponseRs getParametro(@PathParam("id") Integer id){
//        return parametroService.getParametroById(id);
//    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getParametroName(@PathParam("name") String name){
        return parametroService.getParametroByName(name);
    }

}
