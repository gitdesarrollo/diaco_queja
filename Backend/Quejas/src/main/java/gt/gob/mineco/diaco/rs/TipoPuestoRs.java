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


 @Path("/tipos-puesto")
@RequestScoped
public class TipoPuestoRs {

	@Inject
	private TipoAreaComunService tipoPuestoService;
	
	@GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getPuestos(@PathParam("token") String token){
		return tipoPuestoService.listPuestos(token);
	}
        
        @POST
        @Path("/save/{puesto}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs savePuesto(
                @PathParam("puesto") String puesto,
                @PathParam("usuario") String usuario,
                @PathParam("token") String token){
		return tipoPuestoService.savePuesto(0, puesto, usuario,token, "INSERT");
	}
        
        @POST
        @Path("/update/{idPuesto}/{puesto}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updatePuesto(
                @PathParam("idPuesto") Integer idPuesto,
                @PathParam("puesto") String puesto,
                @PathParam("usuario") String usuario,
                @PathParam("token") String token){
		return tipoPuestoService.savePuesto(idPuesto, puesto, usuario,token, "UPDATE");
	}
        
        @POST
        @Path("/delete/{idPuesto}/{usuario}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deletePuesto(
                @PathParam("idPuesto") Integer idPuesto,
                @PathParam("usuario") String usuario,
                @PathParam("token") String token){
		return tipoPuestoService.savePuesto(idPuesto, " ", usuario,token, "DELETE");
	}
        
    
    
}
