package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.dto.ConfirmaEmailDto;
import gt.gob.mineco.diaco.dto.ProveedorDto;
import gt.gob.mineco.diaco.model.DiacoEstablecimiento;
import gt.gob.mineco.diaco.model.DiacoProveedor;
import gt.gob.mineco.diaco.model.DiacoProveedorAprovar;
import gt.gob.mineco.diaco.model.TipoEmail;
import gt.gob.mineco.diaco.model.TipoTelefono;
import gt.gob.mineco.diaco.service.ProveedoresServiceImp;
import gt.gob.mineco.diaco.service.TipoImagenesQuejaServiceImp;
import gt.gob.mineco.diaco.util.FormCalificacionUsuario;
import gt.gob.mineco.diaco.util.ResponseRs;

import java.io.InputStream;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/proveedores")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ProveedoresRs {

    @Inject
    ProveedoresServiceImp proveedoresService;
    @Inject
    TipoImagenesQuejaServiceImp tipoImgQuejaService;

    @GET
    public ResponseRs getProveedores(@QueryParam(value = "nit") String nit) {
        ResponseRs response = new ResponseRs();
        DiacoProveedor proveedor = proveedoresService.getProveedorByNit(nit);
        response.setValue(proveedor);
        return response;
    }

    @GET
    @Path("/busca")
    public ResponseRs getFindProveedores(@QueryParam(value = "nit") String nit, @QueryParam(value = "nombre") String nombre) {
        ResponseRs response = new ResponseRs();
        List<ProveedorDto> proveedor = proveedoresService.getProveedorByNitNombre(nit, nombre);
        response.setValue(proveedor);
        return response;
    }

    @GET
    @Path("/id")
    public ResponseRs getProveedoresId(@QueryParam(value = "id") String id) {
        /*
        ResponseRs response = new ResponseRs();
        DiacoProveedor proveedor = proveedoresService.getProveedorById(Integer.parseInt(id));
        response.setValue(proveedor);
        return response;
         */
        ResponseRs response = new ResponseRs();
        DiacoProveedor proveedor = proveedoresService.getProveedorById(Integer.parseInt(id));
        if (proveedor != null) {
            List<TipoTelefono> tel = proveedoresService.findTelefonosById(proveedor.getIdProveedor());
            List<TipoEmail> correo = proveedoresService.findCorreosById(proveedor.getIdProveedor());
            for (TipoTelefono t : tel) {
                switch (t.getTipocatalogo().getIdCatalogo()) {
                    case 3:
                        proveedor.setTelefono1(t.getTelefono()); //Personal
                        break;
                    case 4:
                        proveedor.setTelefono2(t.getTelefono()); //Domicilio
                        break;
                    case 5:
                        proveedor.setTelefono3(t.getTelefono()); //Referencia
                        break;

                }
            }
            if (correo != null) {
                Integer i = 1;
                for (TipoEmail c : correo) {
                    switch (i) {
                        case 1:
                            proveedor.setEmail1(c.getCorreo_electronico());
                            i++;
                            break;
                        case 2:
                            proveedor.setEmail2(c.getCorreo_electronico());
                            i++;
                            break;
                    }
                }
            }
        }
        response.setValue(proveedor);
        return response;
    }

    @GET
    @Path("/proveedor")
    public ResponseRs getFindId(@QueryParam(value = "id") Integer id) {
        ResponseRs response = new ResponseRs();
        DiacoProveedor proveedor = proveedoresService.getProveedorById(id);
        if (proveedor != null) {
            List<TipoTelefono> tel = proveedoresService.findTelefonosById(proveedor.getIdProveedor());
            List<TipoEmail> correo = proveedoresService.findCorreosById(proveedor.getIdProveedor());
            for (TipoTelefono t : tel) {
                switch (t.getTipocatalogo().getIdCatalogo()) {
                    case 3:
                        proveedor.setTelefono1(t.getTelefono()); //Personal
                        break;
                    case 4:
                        proveedor.setTelefono2(t.getTelefono()); //Domicilio
                        break;
                    case 5:
                        proveedor.setTelefono3(t.getTelefono()); //Referencia
                        break;

                }
            }
            if (correo != null) {
                Integer i = 1;
                for (TipoEmail c : correo) {
                    switch (i) {
                        case 1:
                            proveedor.setEmail1(c.getCorreo_electronico());
                            i++;
                            break;
                        case 2:
                            proveedor.setEmail2(c.getCorreo_electronico());
                            i++;
                            break;
                    }
                }
            }
        }
        response.setValue(proveedor);
        return response;
    }

    @GET
    @Path("/aprobar")
    public ResponseRs getProveedorAprobar(@QueryParam(value = "nit") String nit) {
        ResponseRs response = new ResponseRs();
        DiacoProveedorAprovar proveedor = proveedoresService.getProveedorByNitProveedor(nit);
        response.setValue(proveedor);
        return response;
    }

    @GET
    @Path("/sucursales/{nit}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedorSucursal(@PathParam("nit") String nit) {
        try {
            ResponseRs response = new ResponseRs();
            List<DiacoEstablecimiento> proveedor = proveedoresService.getEstableciminientoByNitProveedor(nit);
            response.setValue(proveedor);
            response.setCode(1L);
            response.setTotal(proveedor.size());
            response.setReason("OK");
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/sucursales/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedorSucursalId(@PathParam("id") Integer id) {
        try {
            ResponseRs response = new ResponseRs();
            DiacoEstablecimiento proveedor = proveedoresService.getEstableciminientoById(id);
            response.setValue(proveedor);
            response.setCode(1L);
            response.setTotal(1);
            response.setReason("OK");
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @POST
    @Path("/sucursales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs setProveedorSucursal(DiacoEstablecimiento establecimiento) {
        try {
            ResponseRs response = new ResponseRs();
            DiacoEstablecimiento proveedor = proveedoresService.setEstableciminientoByNitProveedor(establecimiento);
            response.setValue(proveedor);
            response.setCode(1L);
            response.setTotal(1);
            response.setReason("OK");
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs createProveedor(DiacoProveedor proveedor) {
        ResponseRs response = new ResponseRs();
        proveedor = proveedoresService.saveProveedor(proveedor);
        response.setValue(proveedor);
        return response;
    }

    @POST
    @Path("/ins_not_elect")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs updateProveedor(DiacoProveedor proveedor) {
        ResponseRs response = new ResponseRs();
        proveedor = proveedoresService.updateProveedor(proveedor);
        response.setValue(proveedor);
        return response;
    }

    /*
    @POST
    @Path("/aprobar")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs createProveedorAprobar(DiacoProveedorAprovar proveedor) {
        ResponseRs response = new ResponseRs();
        proveedor = proveedoresService.saveProveedorAprobar(proveedor);
        response.setValue(proveedor);
        return response;
    }
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs upload(@FormDataParam("document") InputStream is,
            @FormDataParam("document") FormDataContentDisposition fileDetails,
            @QueryParam("id_proveedor") Integer id_proveedor,
            @QueryParam("id_categoria_imagen") Integer id_categoria_imagen,
            @DefaultValue("0") @QueryParam("correo") Integer pcorreo,
            @DefaultValue("0") @QueryParam("data") String ptoken
    ) {
        //ptoken identifica para este caso grabar en tabla diaco_imagen y si es consumidor = 1 o proveedor = 2
        System.out.println("Entrando a metodo para carga de Archivos JJ");
        Integer id_tipo_imagen = 3;
        if (ptoken.equals("0")) {
            System.out.println("idProveedor " + id_proveedor + ", tipoImange " + id_tipo_imagen + ", categoria " + id_categoria_imagen + ", flujo ");
            return tipoImgQuejaService.saveImagenPubProv(
                    is,
                    fileDetails,
                    id_proveedor,
                    id_tipo_imagen,
                    id_categoria_imagen
            //id_flujo
            );
        } else {
            System.out.println("token " + ptoken + ", tipoImange " + id_tipo_imagen + ", categoria " + id_categoria_imagen + ", flujo ");
            return tipoImgQuejaService.saveImagenPubProvCons(
                    is,
                    fileDetails,
                    id_proveedor,
                    id_tipo_imagen,
                    id_categoria_imagen,
                    ptoken
            //id_flujo
            );
        }
    }

    @POST
    @Path("/save_calificacion")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs saveCalificacionProveedor(FormCalificacionUsuario pCalificacion) {
        ResponseRs response = new ResponseRs();
        proveedoresService.saveCalificacionProveedor(pCalificacion);
        response.setReason("OK");
        return response;
    }

    /**
     * Metodo que sirve para guardar la informacion del Proveedor.
     * <p>
     * Cuando el proveedor ya existe lo actualiza. Cuando no existe lo crea. La
     * informacion es almacenada en Base de datos. Se le envia un correo
     * electronico de confirmacion al usuario.
     *
     * <p>
     * El estado con el que se almacena el proovedor es "T" = Temporal.
     *
     * @param pDtoProveedor Datos del proveedor.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    @POST
    @Path("/aprobar")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs procesarProveedor(DiacoProveedorAprovar pDtoProveedor) {
        ResponseRs response = new ResponseRs();
        try {
            // Se procesa la informacion recibida del Proveedor.
            pDtoProveedor = proveedoresService.procesarProveedor(pDtoProveedor);
            // Respuesta.
            response.setReason("OK");
            response.setValue(pDtoProveedor);
        } catch (Exception e) {
            // Cuando se detecta un error.
            response.setCode(Long.valueOf("1000"));
            response.setReason(e.getMessage());
        }
        return response;
    }

    /**
     * Metodo que sirve para confirmar a un proveedor.
     * <p>
     * Significa que un usuario ha recibido en su correo electronico el link de
     * confirmacion y lo ha utilizado. Esto genera que el estado del proveedor
     * sea cambiado hacia "P" = Pendiente.
     *
     * @param pLlaveDto DTO con la llave enviada por correo.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    @POST
    @Path("/confirmarproveedor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs confirmarProveedor(ConfirmaEmailDto pLlaveDto) {
        ResponseRs response = new ResponseRs();
        try {
            // Se procesa la informacion recibida del Proveedor.
            proveedoresService.confirmarProveedor(pLlaveDto.getLlave());
            // Respuesta.
            response.setReason("OK");
        } catch (Exception e) {
            // Cuando se detecta un error.
            response.setCode(Long.valueOf("2000"));
            response.setReason(e.getMessage());
        }
        return response;
    }
}
