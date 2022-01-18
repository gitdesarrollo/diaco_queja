/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.ResponseRs;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/tipos-area")
@RequestScoped
public class TipoAreaInternaRs {
    @Inject
	private TipoAreaComunService tipoProveedorService;

       @GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getProveedor(@PathParam("token") String token){
		return tipoProveedorService.listTipoAreaInterna(token);
	}

    @POST
        @Path("/save/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveArea(@PathParam("nombre") String nombre,
               @PathParam("usuario") String usuario,
               @PathParam("token") String token){
		return tipoProveedorService.saveTipoAreaInterna(0, nombre, token, usuario, "INSERT");
	}
        
   
    @POST
        @Path("/update/{id}/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateArea(@PathParam("nombre") String nombre,
                @PathParam("id") Integer id,
               @PathParam("usuario") String usuario,
               @PathParam("token") String token){
		return tipoProveedorService.saveTipoAreaInterna(id, nombre, token, usuario, "UPDATE");
	}     
        
    @POST
        @Path("/delete/{id}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteArea(
                @PathParam("id") Integer id,
               @PathParam("usuario") String usuario,
               @PathParam("token") String token){
		return tipoProveedorService.saveTipoAreaInterna(id, "", token, usuario, "DELETE");
	}       
}
