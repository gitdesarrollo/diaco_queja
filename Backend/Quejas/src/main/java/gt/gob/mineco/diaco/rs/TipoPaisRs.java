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
import javax.ws.rs.POST;

@Path("/tipos-paises")
@RequestScoped
public class TipoPaisRs {
    @Inject
	private TipoAreaComunService tipoPaisService;
	
        @GET
        @Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getPaises(@PathParam("token") String token){
		return tipoPaisService.listPaises(token);
	}
     
        @POST
        @Path("/save/{nombrePais}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs savePais(@PathParam("nombrePais") String nombre, @PathParam("token") String token){
		return tipoPaisService.savePais(nombre, token);
	}
    
        @POST
        @Path("/update/{idPais}/{nombrePais}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updatePais(@PathParam("idPais") Integer id,@PathParam("nombrePais") String nombre, @PathParam("token") String token){
		return tipoPaisService.updatePais(id, nombre, token);
	}
    
        @POST
        @Path("/delete/{idPais}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deletePais(@PathParam("idPais") Integer id, @PathParam("token") String token){
		return tipoPaisService.deletePais(id, token);
	}
}
