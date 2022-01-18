/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.rs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import gt.gob.mineco.diaco.util.ResponseRs;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.FormBusqueda;
import javax.ws.rs.POST;


@Path("/tipos-proveedor-comun")
@RequestScoped
public class TipoProveedorComunRs {
    
  @Inject
private TipoAreaComunService tipoProveedorService;
  
    @GET
        @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getProveedor( @PathParam("token") String token){
		return tipoProveedorService.listTiposProveedorComun(token);
	}
        
    @GET
        @Path("/findById/{id_proveedor}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListProveedorById( 
                @PathParam("id_proveedor") Integer id_proveedor,
                @PathParam("token") String token){
		return tipoProveedorService.findProveedorById(id_proveedor, token);
	}    

}
