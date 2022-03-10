package gt.gob.mineco.diaco.rs;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gt.gob.mineco.diaco.model.TipoConsumidor;
import gt.gob.mineco.diaco.model.TipoQueja;
import gt.gob.mineco.diaco.service.AlertasNotificacionService;
import gt.gob.mineco.diaco.util.FormConsumidorQuery;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/expiracion-queja")
@RequestScoped
public class NotificacionesAlertasRs {

    @Inject
    private AlertasNotificacionService notificacionesServices;


    @GET
    @Path("/listado")
    public ResponseRs getListadoQueja(){
        ResponseRs response = new ResponseRs();

        notificacionesServices.ExpiredNotificationAlert();

        response.setCode(200L);
        response.setValue("true");

       return response; 
        
    }

    @POST
    @Path("info-consumidor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getInfoConsumidor(FormConsumidorQuery queja){
        ResponseRs res = new ResponseRs();

        res.setCode(200L);
        res.setValue(notificacionesServices.getDataByQueja(queja));
        return res;   
    }

    
}
