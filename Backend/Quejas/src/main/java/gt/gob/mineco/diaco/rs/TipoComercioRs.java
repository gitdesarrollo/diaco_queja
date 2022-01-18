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


@Path("/tipos-comercio")
@RequestScoped
public class TipoComercioRs {
    
@Inject
private TipoAreaComunService tipoComercioService;
  
    @GET
        @Path("/{idActividadEconomica}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoComercioList( 
                @PathParam("idActividadEconomica") Integer idActividadEconomica,
                @PathParam("token") String token){
		return tipoComercioService.listTipoComercio(idActividadEconomica,token);
	}
        
 @GET
        @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getTipoComercioListU( 
                @PathParam("token") String token){
		return tipoComercioService.listTipoComercioAll(token);
	}
   
      @POST
        @Path("/save/{idActividad}/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveTipoComercio(@PathParam("idActividad") Integer idActividad,@PathParam("nombre") String nombre,@PathParam("usuario") String usuario ,@PathParam("token") String token){
		return tipoComercioService.saveTipoComercio(0, idActividad, nombre, usuario, "INSERT", token);
              
	}
    
        @POST
        @Path("/update/{id}/{idActividad}/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateTipoComercio(@PathParam("id") Integer id,@PathParam("idActividad") Integer idActividad,@PathParam("nombre") String nombre,@PathParam("usuario") String usuario ,@PathParam("token") String token){
		return tipoComercioService.saveTipoComercio(id, idActividad, nombre, usuario,"UPDATE", token);
	}
    
        @POST
        @Path("/delete/{id}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteTipoComercio(@PathParam("id") Integer id,@PathParam("usuario") String usuario, @PathParam("token") String token){
		return tipoComercioService.saveTipoComercio(id, 0, "", usuario,"DELETE", token);
	}             
        
        
}
