package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.dao.QuejaRepository;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import gt.gob.mineco.diaco.dto.DiacoQuejaIniDto;
import gt.gob.mineco.diaco.dto.QuejaConDto;
import gt.gob.mineco.diaco.model.DiacoConsumidor;
import gt.gob.mineco.diaco.model.DiacoQueja;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoImagenesQueja;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.model.TipoUsuario;
import gt.gob.mineco.diaco.service.QuejasServiceImp;
import gt.gob.mineco.diaco.service.SecurityManagerServiceImpl;
import gt.gob.mineco.diaco.service.TipoImagenesQuejaServiceImp;
import gt.gob.mineco.diaco.util.ResponseRs;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/quejasini")
@RequestScoped
public class QuejasRsIni {

    @Inject
    QuejasServiceImp quejasService;
    @Inject
    QuejaRepository quejaDao;
    @Inject
    TipoImagenesQuejaServiceImp tipoImgQuejaService;
    @Inject
    SecurityManagerServiceImpl securityService;

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getQuejas(DiacoQuejaIniDto quejaini) {
        ResponseRs response = new ResponseRs();
        try {
            List<DiacoQuejaIniDto> paises = quejasService.getquejas(quejaini);
            response.setValue(paises);
        } catch (Exception e) {
            System.out.println("Error en getQuejas " + e.getMessage());
        }
        return response;
    }*/
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getQuejasId(@PathParam(value = "id") Integer id) {
        ResponseRs response = new ResponseRs();
        DiacoQueja queja = quejasService.getquejasId(id);
        try {
            String tempFormat = new SimpleDateFormat("dd/MM/yyyy").format(queja.getFechaQueja());
            System.out.println("fecha: " + tempFormat);
            queja.setFechaFactura_(tempFormat);
            this.getarchivos(queja);
        } catch (Exception e) {
            System.out.println("Error en fecha: " + queja.getFechaFactura());
        }
        response.setValue(queja);
        return response;
    }
    ////////////////////////////////////////FACTURA////////////////////////
    @GET
    @Path("/factura")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getFacturas() {
        ResponseRs response = new ResponseRs();
        Integer facturas = 0;
        facturas = quejaDao.findMaxFromYear();
        try {


        } catch (Exception e) {
            System.out.println("Error en fecha: " );
        }
        //response.setValue();
        return response;
    }
    
    
    @GET
    public ResponseRs getFacturaProveedor(@QueryParam(value = "proveedor") Integer proveedor) {
        System.out.println("Ingresando a getFacturaProveedor jj"+ proveedor);
        ResponseRs response = new ResponseRs();
        
        try {
            response.setCode(0L);
            response.setReason("OK");
            response.setValue(quejaDao.findByProveedor(proveedor));
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(1L);
            response.setReason("ERROR getFacturaProveedor");
        }
        return response;

        /*if (queja != null) {
            TipoTelefono tel = consumidoresService.findTelefonoById(consumidor.getIdConsumidor());
            TipoEmail correo = consumidoresService.findCorreoById(consumidor.getIdConsumidor());
            if (tel != null) {
                consumidor.setTelefono(tel.getTelefono());
            }
            if (correo != null) {
                consumidor.setCorreoElectronico1(correo.getCorreo_electronico());
            }
        }*/
        
    }

    //////////////////////////////////////FINALIZA FACTURA/////////////////////////////////////////////
    
    @GET
    @Path("/noqueja/{anio}/{correlativo}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getQuejasAnioSec(@PathParam(value = "anio") Integer anio, @PathParam(value = "correlativo") Integer correlativo) {
        ResponseRs response = new ResponseRs();
        DiacoQueja queja = quejasService.getquejasAnioSec(anio, correlativo);
        this.getarchivos(queja);
        response.setValue(queja);
        return response;
    }

    @GET
    @Path("/secuencia")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getSecuencia() {
        ResponseRs response = new ResponseRs();
        Long sigSecuencia = quejasService.getSecuencia();
        response.setValue(sigSecuencia);
        return response;
    }

    @GET
    @Path("/secuencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getIdFuente(@PathParam(value = "id") Integer id) {
        ResponseRs response = new ResponseRs();
        DiacoQueja queja = quejasService.findBySecuencia(id);
        this.getarchivos(queja);
        response.setValue(queja);
        return response;
    }

    @GET
    @Path("/quejacon/{dato}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs getIdConsumidor(@PathParam(value = "dato") String dato, @PathParam(value = "id") String id,
            @DefaultValue("0") @QueryParam("queja") String pqueja) {
        ResponseRs response = new ResponseRs();
        if (dato.equals("interno") || dato.equals(("presencial"))) {
            if (pqueja.equals("0")) {
                response.setValue(null);
            } else {
                DiacoQueja queja = this.securityService.findTokenIntExt(dato, id, pqueja);
                response.setValue(queja);
                response.setCode(queja != null ? 1L : 0L);
                response.setTotal(queja != null ? 1 : 0);
                response.setReason(queja != null ? "OK" : "ERROR");
            }
        } else if (dato.equals("externo") || dato.equals("externo1") || dato.equals("pr")) {
            //externo -  busca si existe el link y esta activo, adicionalmente del dato de la queja
            //esterno1 -  elimina el link para que ya no sea utiliza
            DiacoQueja queja = this.securityService.findTokenIntExt(dato, id, "");
            try {
                String tempFormat = new SimpleDateFormat("dd/MM/yyyy").format(queja.getFechaQueja());
                //System.out.println("fecha: " + tempFormat);
                queja.setFechaFactura_(tempFormat);
                this.getarchivos(queja);
            } catch (Exception e) {
                System.out.println("Error en fecha: ");
            }
            response.setValue(queja);
            response.setCode(queja != null ? 1L : 0L);
            response.setTotal(queja != null ? 1 : 0);
            response.setReason(queja != null ? "OK" : "ERROR");
        } else if (dato.equals("0") || dato.equals("1")) {
            DiacoQuejaIniDto sigSecuencia = this.securityService.findTokenByConsumidor(dato, id);
            response.setValue(sigSecuencia);
            response.setCode(sigSecuencia != null ? 1L : 0L);
            response.setTotal(sigSecuencia != null ? 1 : 0);
            response.setReason(sigSecuencia != null ? "OK" : "ERROR");
        } else if (dato.equals("presencialI")) {
            TipoUsuario sigSecuencia = this.securityService.findTokenPresenccial(dato, id);
            response.setValue(sigSecuencia);
            response.setCode(sigSecuencia != null ? 1L : 0L);
            response.setTotal(sigSecuencia != null ? 1 : 0);
            response.setReason(sigSecuencia != null ? "OK" : "ERROR ");
        }
        return response;
    }

    @GET
    @Path("/archivos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs listArchivosQuejasId(@PathParam(value = "id") Integer id) {
        ResponseRs response = new ResponseRs();
        List<TipoImagenesQueja> datos = this.quejasService.listArchivos(id);
        response.setValue(datos);
        response.setCode(1L);
        response.setTotal(datos.size());
        response.setReason("OK");
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs createQueja(DiacoQuejaIniDto queja) {
        ResponseRs response = new ResponseRs();
        queja = quejasService.saveQuejaIni(queja);
        response.setValue(queja);
        //send email for notification
        if (queja.getConciliacion().equals("1")) {
            String token = this.securityService.createTokenByConsumidor(queja);
            System.out.println("Envio correo queja web" + this.quejasService.sendMail(queja, "subject", "1", token));
        } else {
            if (queja.getPresencial() != null) {
                System.out.println("No envio correo queja web se llenaran 40-30 datos");
                //    if (queja.getPresencial().equals("1")) {
                //        System.out.println("Envio correo queja web" + this.quejasService.sendMail(queja, "subject", "1", ""));
                //    }
            } else {
                String token = this.securityService.createTokenByConsumidor(queja);
                System.out.println("Envio correo queja web" + this.quejasService.sendMail(queja, "subject", "1", token));
            }
        }

        return response;
    }

    @POST
    @Path("/noupload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs createQuejaNoUpload(DiacoQuejaIniDto queja) {
        ResponseRs response = new ResponseRs();
        queja = quejasService.saveQuejaIni(queja);
        response.setValue(queja);
        if (queja.getConciliacion().equals("1")) {
            if (queja.getPresencial() != null) {
                //System.out.println("No envio correo queja web se llenaran 40-30 datos");
                //    if (queja.getPresencial().equals("1")) {
                System.out.println("Envio correo queja presencial" + this.quejasService.sendMail(queja, "subject", "2", ""));
                //    }
            } else {
                //send email for upload files
                String token = this.securityService.createTokenByConsumidor(queja);
                System.out.println("tocken  " + token);
                System.out.println("Envio correo queja int"
                        + this.quejasService.sendMail(queja, "subject", "3", token));
            }
        } else {
            if (queja.getPresencial() != null) {
                System.out.println("No envio correo queja web se llenaran 40-30 datos");
                //    if (queja.getPresencial().equals("1")) {
                //        System.out.println("Envio correo queja web" + this.quejasService.sendMail(queja, "subject", "1", ""));
                //    }
            } else {
                //send email for upload files
                String token = this.securityService.createTokenByConsumidor(queja);
                System.out.println("token  " + token);
                System.out.println("Envio correo queja int"
                        + this.quejasService.sendMail(queja, "subject", "3", token));
            }
        }

        return response;
    }

    @POST
    @Path("/internal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs updateQuejaUpload(DiacoQuejaIniDto queja) {
        ResponseRs response = new ResponseRs();
        queja = quejasService.saveQuejaIni(queja);
        response.setValue(queja);
        //send email for notification upload files
        System.out.println("Envio correo queja upload"
                + this.quejasService.sendMail(queja, "subject", "2", ""));
        return response;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs upload(@FormDataParam("document") InputStream is,
            @FormDataParam("document") FormDataContentDisposition fileDetails,
            @QueryParam("id_queja") Integer id_queja,
            @QueryParam("id_categoria_imagen") Integer id_categoria_imagen, //dpi o factura
            @DefaultValue("0") @QueryParam("correo") Integer pcorreo,
            @DefaultValue("0") @QueryParam("data") String ptoken
    ) {
        Integer id_tipo_imagen = 3;
        Integer id_flujo = 1;
        System.out.println("idqueja " + id_queja + ", tipoImange " + id_tipo_imagen + ", categoria " + id_categoria_imagen + ", flujo " + id_flujo);
        if (pcorreo > 0) {
            DiacoQuejaIniDto sigSecuencia = this.securityService.findTokenByConsumidor("0", ptoken);
            if (sigSecuencia != null) {
                this.quejasService.sendMail(sigSecuencia, "subject", "2", "");
            }
        }
        return tipoImgQuejaService.saveImagenPub(
                is,
                fileDetails,
                id_queja,
                id_tipo_imagen,
                id_categoria_imagen,
                id_flujo
        );
    }

    @GET
    @Path("/download/{id}/{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlantilla(@PathParam("id") Integer id,
            @PathParam("idcategoria") Integer idcategoria,
            @PathParam("token") String token) {
        return tipoImgQuejaService.getPlantilla(id, idcategoria, "queja");
    }

    private DiacoQueja getarchivos(DiacoQueja pq) {
        //busca si existen archivos
        return pq = this.quejasService.buscaArchivos(pq);
    }

    @POST
    @Path("/updatequejacon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs updateQuejaCon(QuejaConDto queja) {
        ResponseRs response = new ResponseRs();
        DiacoQueja qq = quejasService.updateQuejaCon(queja);
        response.setValue(qq);
        //send email for notification upload files
        //System.out.println("Envio correo queja upload" + this.quejasService.sendMail(qq, "subject", "2", ""));
        return response;
    }
}
