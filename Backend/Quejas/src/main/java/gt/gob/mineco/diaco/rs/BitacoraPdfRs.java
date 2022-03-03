package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gt.gob.mineco.diaco.model.bitacoraPdf;
import gt.gob.mineco.diaco.service.bitacoraPdfService;
import gt.gob.mineco.diaco.util.FormBitacoraPdf;
import gt.gob.mineco.diaco.util.ResponseRs;

@Path("/bitacora-pdf")
@RequestScoped
public class BitacoraPdfRs {
    
    @Inject
    private bitacoraPdfService bitacoraPdfDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs addBitacora(FormBitacoraPdf formBitacoraPdf){
        return bitacoraPdfDao.addBitacoraPdf(formBitacoraPdf);
    }

    @GET
    @Path("/{queja}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getBitacoraByQueja(@PathParam("queja") String queja){
        return bitacoraPdfDao.listBitacoraByName(queja);
    }

    @GET
    @Path("/consulta/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long getBitacoraByNameQueja(@PathParam("nombre") String nombre){
        return bitacoraPdfDao.listBitacoraByQuejaCount(nombre);
    }
}
