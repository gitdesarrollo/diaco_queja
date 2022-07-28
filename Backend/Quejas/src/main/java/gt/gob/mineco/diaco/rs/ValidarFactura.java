package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.service.ValidaFactura;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.util.ValidateFacData;

@Path("/validar")
@RequestScoped
public class ValidarFactura {

    @Inject
    ValidaFactura validateFac;
    

    @POST
    @Path("/factura")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs consultarFactura(ValidateFacData validateData){
        ResponseRs resp = new ResponseRs();
        Boolean resultado = validateFac.validarFacturaServ(validateData);
        resp.setValue(resultado);
        return resp;
    }
}
