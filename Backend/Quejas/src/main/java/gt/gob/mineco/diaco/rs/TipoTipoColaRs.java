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

@Path("/tipos-cola")
@RequestScoped
public class TipoTipoColaRs {

	@Inject
	private TipoAreaComunService tipoService;
	
	@GET
        @Path("{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs getListTipoCola(@PathParam("token") String token){
		return tipoService.listTipoCola(token);
                
	}
        
        
        @GET
        @Path("/find/{id}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs findTipoCola(@PathParam("token") String token,
                @PathParam("id") Integer id
                ){
		return tipoService.listDeptoInterno(token);
                
	}
        
        @POST
        @Path("/save/{nombre}/{idFlujo}/{ultimaAsignacion}/{tipo}/{idSede}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs saveTipoCola(@PathParam("token") String token,            
                @PathParam("nombre") String nombre,
                @PathParam("idFlujo") Integer idFlujo,
                @PathParam("tipo") String tipo,
                @PathParam("idSede") Integer idSede,
                @PathParam("usuario") Integer idUsuario
                ){
          
		return tipoService.saveTipoCola(0, idFlujo, nombre, tipo, idSede, idUsuario,"INSERT", token);
	}
        
        @POST
        @Path("/update/{idTipoCola}/{nombre}/{idFlujo}/{tipo}/{idSede}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs updateTipoCola(
                @PathParam("idTipoCola") Integer idTipoCola,            
                @PathParam("token") String token,            
                @PathParam("nombre") String nombre,
                @PathParam("idFlujo") Integer idFlujo,
                @PathParam("tipo") String tipo,
                @PathParam("idSede") Integer idSede,
                @PathParam("usuario") Integer idUsuario
                ){
        return tipoService.saveTipoCola(idTipoCola, idFlujo, nombre, tipo, idSede, idUsuario,"UPDATE", token);

	}
        
        @POST
        @Path("/delete/{idTipoCola}/{usuario}/{token}")      
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseRs deleteTipoCola(
                @PathParam("idTipoCola") Integer idTipoCola,            
                @PathParam("token") String token,            
                @PathParam("usuario") Integer idUsuario
                ){
        return tipoService.saveTipoCola(idTipoCola, 0,"", "", 0, idUsuario,"DELETE", token);

	}
        
        
}
