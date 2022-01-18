package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormBusqueda;
import gt.gob.mineco.diaco.util.FormProveedor;
import gt.gob.mineco.diaco.util.ResponseRs;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tipos-proveedor")
@RequestScoped
public class TipoProveedorRs {

    @Inject
    private TipoAreaComunService tipoProveedorService;

    /*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getProveedor(@PathParam("token") String token){
		return tipoProveedorService.listTiposProveedor(token);
	}*/
    @GET
    @Path("/{id}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedor(@PathParam("id") Integer id, @PathParam("token") String token) {
        return tipoProveedorService.listTiposProveedor(id, token);
    }

    @GET
    @Path("/serv_pub/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedorSP(@PathParam("token") String token) {
        return tipoProveedorService.listTiposProveedorSP(token);
    }

    @GET
    @Path("/find/{nit}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs findProveedorByNit(@PathParam("token") String token,
            @PathParam("nit") String nit
    ) {
        return tipoProveedorService.findProveedorByNit(nit, token);
    }

    @GET
    @Path("/none_serv_pub/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedornoneSP(@PathParam("token") String token) {
        return tipoProveedorService.listTiposProveedornoneSP(token);
    }

    @GET
    @Path("/cp_sp/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedorCPoSP(@PathParam("token") String token) {
        return tipoProveedorService.listTiposProveedorCPoSP(token);
    }

    @POST
    @Path("/customf")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs CustomFilterProveedor(FormBusqueda formBusqueda) {
        return tipoProveedorService.CustomFilterProveedor(formBusqueda);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs saveCatProveedor(FormProveedor p) {
        return tipoProveedorService.saveCatProveedor(p);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs updateCatProveedor(FormProveedor p) {
        return tipoProveedorService.updateCatProveedor(p);
    }

    @POST
    @Path("/delete/{idProveedor}/{usuario}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs deleteCatProveedor(@PathParam("idProveedor") Integer idProveedor,
            @PathParam("usuario") String usuario,
            @PathParam("token") String token) {
        return tipoProveedorService.deleteCatProveedor(idProveedor, usuario, token);
    }

    @GET
    @Path("/prov_aprobar/{token}/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getProveedorAprobar(@PathParam("token") String token, @PathParam("estado") String estado) {
        return tipoProveedorService.GetProveedorAprobar(token, estado);
    }

    @GET
    @Path("/prov_aprobar/{token}/{estado}/{id}/{usuario_operacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs saveProveedorAprobar(@PathParam("token") String token, @PathParam("estado") String estado, @PathParam("id") Integer id, @PathParam("usuario_operacion") Integer usuario_operacion) {
        return tipoProveedorService.UpdProveedorAprobar(token, id, estado, "", usuario_operacion);
    }

    @GET
    @Path("/prov_aprobar_rechazo/{token}/{razon}/{id}/{usuario_operacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs rechazoProveedorAprobar(@PathParam("token") String token, @PathParam("razon") String razon, @PathParam("id") Integer id, @PathParam("usuario_operacion") Integer usuario_operacion) {
        return tipoProveedorService.UpdProveedorAprobar(token, id, "R", razon, usuario_operacion);
    }

    @GET
    @Path("/prov_aprobar_nitprov/{nit}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs rechazoProveedorAprobar(@PathParam("nit") String nit) {
        return tipoProveedorService.ProveedorAprobarxNitProv(nit);
    }

    @GET
    @Path("/all/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getListAll(@PathParam("token") String token) {
        return tipoProveedorService.listProveedorAll(token);
    }

    /**
     * Metodo que sirve para aprobar la solicitud de creacion de un Proveedor.
     * Al aprobar esta solicitud se ejecutan los siguientes procesos:
     * <ul>
     * <li>Actualizacion de proveedor</li>
     * <li>Creacion del usuario del portal para el proveedor</li>
     * <li>Envio de notificacion.</li>
     * </ul>
     *
     * @param pToken Token de seguridad.
     * @param pEstado Estado del usuario.
     * @param pId Identificador del usuario.
     * @param pUsuarioOperacion Usuario que realiza la operacion.
     * @return Respuesta del tipo {@link ResponseRs}.
     */
    @GET
    @Path("/prov_aprobar2/{token}/{estado}/{id}/{usuario_operacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs aprobarProcesoProveedor(@PathParam("token") String pToken,
            @PathParam("estado") String pEstado, @PathParam("id") Integer pId,
            @PathParam("usuario_operacion") Integer pUsuarioOperacion) {

//        return tipoProveedorService.UpdProveedorAprobar(pToken, pId, pEstado,
//                "", pUsuarioOperacion);
        return tipoProveedorService.procesarAprobacionUsuario(pToken,
                pEstado, pId, pUsuarioOperacion);
    }

}
