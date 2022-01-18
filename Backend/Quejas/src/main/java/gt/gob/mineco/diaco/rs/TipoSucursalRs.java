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
import gt.gob.mineco.diaco.util.FormConsumidor;
import gt.gob.mineco.diaco.util.FormSucursal;
import javax.ws.rs.POST;

@Path("/tipos-sucursal")
@RequestScoped
public class TipoSucursalRs {
    
@Inject
private TipoAreaComunService tipoService;
  
    @GET
        @Path("/find/{idProveedor}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoSucursalList( 
                @PathParam("idProveedor") Integer id,
                @PathParam("token") String token){
		return tipoService.listTipoSucursal(id,token);
	}
    
        
        @POST
         @Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveSucursal(FormSucursal formSucursal){
		return tipoService.saveTipoSucursal(formSucursal,"INSERT");
	}
        
         @POST
         @Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateSucursal(FormSucursal formSucursal){
		return tipoService.saveTipoSucursal(formSucursal,"UPDATE");
	}
        
         @POST
         @Path("/delete/{id}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteSucursal(
        @PathParam("id") Integer id,
         @PathParam("usuario") String usuario,
          @PathParam("token") String token
        ){
		return tipoService.deleteTipoSucursal(id, usuario, token);
	}
        
        
}
