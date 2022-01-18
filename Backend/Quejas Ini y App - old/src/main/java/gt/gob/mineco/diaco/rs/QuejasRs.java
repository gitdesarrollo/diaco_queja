package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.dao.ConsumidorRepository;
import gt.gob.mineco.diaco.dto.CalendarioDto;
import gt.gob.mineco.diaco.dto.DetalleCalendarioDto;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.service.QuejasServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/quejas")
@RequestScoped
public class QuejasRs {

    @Inject
    QuejasServiceImp quejasService;
    @Inject
    ConsumidorRepository consumidorDao;

    @GET
    @Path("/{noqueja}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getQuejasEstado(@PathParam(value = "noqueja") String noQueja) {
        ResponseRs response = new ResponseRs();
        List<DiacoQueja> quejas = quejasService.getAllQuejas(noQueja);
        response.setValue(quejas);
        response.setCode(0L);
        response.setReason("OK");
        response.setTotal(quejas.size());
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs createQueja(DiacoQueja queja) {
        ResponseRs response = new ResponseRs();
        queja.setCompleto30Datos("1");
        DiacoConsumidor id = this.consumidorDao.findById(queja.getIdConsumidor());
        queja.setIdDiacoSede(id.getSedeDiacoCercana());
        queja.setIdMotivoQueja(id.getIdMotivoQueja());
        queja = quejasService.saveQuejadq(queja);
        response.setValue(queja);
        return response;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseRs upload(@FormDataParam("document") InputStream is,
            @FormDataParam("document") FormDataContentDisposition fileDetails) {
        String uploadedFileLocation = fileDetails.getFileName();
        writeToFile(is, uploadedFileLocation);

        return null;
    }

    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            File salida = new File(uploadedFileLocation);
            System.out.println("Archivo subido en: " + salida.getAbsolutePath());
            out = new FileOutputStream(salida);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que sirve para consultar el detalle del calendario:
     * <p>
     * <ul>
     * <li>Audiencias</li>
     * <li>Conciliador/Usuario</li>
     * <li>Proveedor</li>
     * <li>Fecha de inicio</li>
     * <li>Color para diferenciar.</li>
     * </ul>
     *
     * @param pCalendarioDto Detalle de los parametros para realizar la
     * consulta.
     * @return Objeto con el detalle de audiencias.
     */
    @POST
    @Path("/calendario")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs consultarCalendario(CalendarioDto pCalendarioDto) {
        ResponseRs response = new ResponseRs();
        try {
            // Se procesa la informacion recibida del conciliador.
            List<DetalleCalendarioDto> detalleCalendario = quejasService.consultarDetalleCalendario(pCalendarioDto);
            // Respuesta.
            response.setReason("OK");
            response.setValue(detalleCalendario);
        } catch (Exception e) {
            // Cuando se detecta un error.
            response.setCode(Long.valueOf("1000"));
            response.setReason(e.getMessage()+" calendarioError");
        }
        return response;
    }
}
