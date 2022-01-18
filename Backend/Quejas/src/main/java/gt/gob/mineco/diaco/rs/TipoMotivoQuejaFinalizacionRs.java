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


@Path("/tipos-finalizacion")
@RequestScoped
public class TipoMotivoQuejaFinalizacionRs {
    
@Inject
private TipoAreaComunService tipoService;
  
    @GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getList( 
                @PathParam("token") String token){
		return tipoService.listTipoMotivoQuejaFinalizada(token);
	}
        
    @POST
        @Path("/save/{nombre}/{tipo}/{padre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getList(
                @PathParam("usuario") String usuario,
                @PathParam("nombre") String nombre,
                @PathParam("padre") Integer padre,
                @PathParam("tipo") String tipo,
                @PathParam("token") String token){
		return tipoService.saveTipoMotivoQuejaFinalizada(0, nombre, tipo, padre, token, usuario, "INSERT");
	}    
    
  @POST
        @Path("/update/{id}/{nombre}/{tipo}/{padre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getList(
                @PathParam("id") Integer id,
                @PathParam("usuario") String usuario,
                @PathParam("nombre") String nombre,
                @PathParam("padre") Integer padre,
                @PathParam("tipo") String tipo,
                @PathParam("token") String token){
		return tipoService.saveTipoMotivoQuejaFinalizada(id, nombre, tipo, padre, token, usuario, "UPDATE");
	}          
        
  @POST
        @Path("/delete/{id}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getList(
                @PathParam("id") Integer id,
                @PathParam("usuario") String usuario,
                @PathParam("token") String token){
		return tipoService.saveTipoMotivoQuejaFinalizada(id, "", "", 0, token, usuario, "DELETE");
	}          
        
}
