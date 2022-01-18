package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.dto.ConsumidorDto;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.service.ConsumidoresServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.util.List;

@Path("/consumidores")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ConsumidoresRs {

    @Inject
    ConsumidoresServiceImp consumidoresService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consumidor")
    public ResponseRs createConsumidor(DiacoConsumidor consumidor) {
        ResponseRs response = new ResponseRs();
        consumidor = consumidoresService.saveConsumidor(consumidor);
        response.setValue(consumidor);
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ins_not_elect")
    public ResponseRs updateConsumidor(DiacoConsumidor consumidor) {
        ResponseRs response = new ResponseRs();
        consumidor = consumidoresService.updateConsumidor(consumidor);
        response.setValue(consumidor);
        return response;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs createConsumidorDto(ConsumidorDto pconsumidor) {
        ResponseRs response = new ResponseRs();
        pconsumidor = consumidoresService.saveConsumidorDto(pconsumidor);
        response.setValue(pconsumidor);
        return response;
    }

    @GET
    public ResponseRs getConsumidores(@QueryParam(value = "docid") String documentoIdentificacion) {
        ResponseRs response = new ResponseRs();
        DiacoConsumidor consumidor = consumidoresService.getConsumidorByDocumentoIdentificacion(documentoIdentificacion);
        System.out.println("consumidor: "+consumidor);
        if (consumidor != null) {
            TipoTelefono tel = consumidoresService.findTelefonoById(consumidor.getIdConsumidor());
            TipoEmail correo = consumidoresService.findCorreoById(consumidor.getIdConsumidor());
            if (tel != null) {
                consumidor.setTelefono(tel.getTelefono());
            }
            if (correo != null) {
                consumidor.setCorreoElectronico1(correo.getCorreo_electronico());
            }
        }
        response.setValue(consumidor);
        return response;
    }

    @GET
    @Path("/consumidor")
    public ResponseRs getConsumidoreId(@QueryParam(value = "id") Integer id) {
        ResponseRs response = new ResponseRs();
        try {
        DiacoConsumidor consumidor = consumidoresService.findConsumidorById(id);
        if (consumidor != null) {
            List<TipoTelefono> tel = consumidoresService.findTelefonosById(consumidor.getIdConsumidor());
            List<TipoEmail> correo = consumidoresService.findCorreosById(consumidor.getIdConsumidor());
            for (TipoTelefono t : tel) {
                switch (t.getTipocatalogo().getIdCatalogo()) {
                    case 3:
                        consumidor.setTelefonoCelular(t.getTelefono()); //Personal
                        break;
                    case 4:
                        consumidor.setTelefonoDomicilio(t.getTelefono()); //Domicilio
                        break;
                    case 5:
                        consumidor.setTelefonoReferencia(t.getTelefono()); //Referencia
                        break;

                }
            }
            if (correo != null) {
                Integer i = 1;
                for (TipoEmail c : correo) {
                    switch (i) {
                        case 1:
                            consumidor.setEmail(c.getCorreo_electronico());
                            i++;
                            break;
                        case 2:
                            consumidor.setEmail2(c.getCorreo_electronico());
                            i++;
                            break;
                    }
                }
            }
        }
        response.setValue(consumidor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    ///////////////////////////////////
    @GET
    @Path("/documento")
    public ResponseRs getConsumidoresDocto(@QueryParam(value = "docid") String documentoIdentificacion) {
        ResponseRs response = new ResponseRs();
        ConsumidorDto consumidor = consumidoresService.getConsumidorByDocumentoIdentificacion("documento", documentoIdentificacion);
        response.setValue(consumidor);
        return response;
    }

    @GET
    @Path("/nit")
    public ResponseRs getConsumidoresNit(@QueryParam(value = "docid") String documentoIdentificacion) {
        ResponseRs response = new ResponseRs();
        ConsumidorDto consumidor = consumidoresService.getConsumidorByDocumentoIdentificacion("nit", documentoIdentificacion);
        response.setValue(consumidor);
        return response;
    }

}
