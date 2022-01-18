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
        

@Path("/tipos-actividad-economica")
@RequestScoped
public class TipoActividadEconomicaRs {
    
  @Inject
private TipoAreaComunService tipoActividadService;
  
    @GET
        @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListActividadEconomica( @PathParam("token") String token){
		return tipoActividadService.listTiposActividadEconomica(token);
	}

      @POST
        @Path("/save/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveActividad(@PathParam("nombre") String nombre,@PathParam("usuario") String usuario ,@PathParam("token") String token){
		return tipoActividadService.saveActividadEconomica(0, nombre, usuario,"INSERT", token);
	}
    
        @POST
        @Path("/update/{id}/{nombre}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateActividad(@PathParam("id") Integer id,@PathParam("nombre") String nombre,@PathParam("usuario") String usuario ,@PathParam("token") String token){
		return tipoActividadService.saveActividadEconomica(id, nombre, usuario,"UPDATE", token);
	}
    
        @POST
        @Path("/delete/{id}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteActividad(@PathParam("id") Integer id,@PathParam("usuario") String usuario, @PathParam("token") String token){
		return tipoActividadService.saveActividadEconomica(id," ",usuario,"DELETE", token);
	}     
}
