/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.rs;

import gt.gob.mineco.diaco.annotation.SecuredResource;
import gt.gob.mineco.diaco.dto.ChangePasswordDto;
import gt.gob.mineco.diaco.service.SecurityManagerServiceImpl;
import gt.gob.mineco.diaco.service.TipoAreaComunService;
import gt.gob.mineco.diaco.util.ResponseRs;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/users")
@RequestScoped
public class UsersRs {

    @Inject
    SecurityManagerServiceImpl security;

    @Inject
    private TipoAreaComunService tipoService;    
    
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //public RsResultado<Principal> login(Principal principal) {
    public ResponseRs login(Principal principal) {
        try{
            ResponseRs response = new ResponseRs();
            RsResultado<Principal> resultado = new RsResultado<Principal>();
            resultado.setCodigo(RsResultado.ERROR);
            principal = security.login(principal);
            if (principal.getCode() == Principal.LOGIN_SUCCESS) {
                resultado.setCodigo(RsResultado.SUCCESS);
                resultado.setDescripcion("Usuario ingreso con éxito.");
            } else {
                principal.setToken("");
                resultado.setDescripcion("Error en ingreso, favor verifique sus datos y permisos.");
            }
            principal.setPassword("");
            resultado.addDetalle(principal);
            response.setReason("OK");
            response.setValue(principal);
            return response;
            //return resultado;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Path("/logout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON)
    @SecuredResource()
    //public RsResultado<Principal> logout(@HeaderParam("Auth-Token") String token, Principal principal) {
    public ResponseRs logout(Principal principal) {
        ResponseRs response = new ResponseRs();
        RsResultado<Principal> resultado = new RsResultado<Principal>();
        resultado.setCodigo(RsResultado.SUCCESS);
        principal = security.logout(principal);
        resultado.addDetalle(principal);
        response.setValue(principal);
        response.setReason(principal.getCode()==Principal.LOGOUT_SUCCESS?"OK":"ERROR");
        //response.setValue(principal.getCode()==Principal.LOGOUT_SUCCESS?"LOGOUT_SUCCESS":"TOKEN_NOT_FOUND");
        return response;
    }

    @PUT
    @Path("/change")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @SecuredResource
    public ResponseRs changePassord(@HeaderParam("Auth-Token") String token, ChangePasswordDto changepass) {
        ResponseRs response = new ResponseRs();
        RsResultado result = new RsResultado();
        result.setDetalle(new ArrayList());
        ResultDetail resultDetail = new ResultDetail();
        resultDetail.setDetalle(new ArrayList());
        result.setResultDetail(resultDetail);
        if (security.changepassword(token, changepass)) {
            result.setCodigo(RsResultado.SUCCESS);
            result.setDescripcion("Cambio de clave con exito.");
        } else {
            result.setCodigo(RsResultado.BAD_PARAMETER);
            result.setDescripcion("Error en cambio de clave, favor verifique.");
        }
        //return result;

        response.setReason(result.getDescripcion());
        response.setTotal(1);
        //response.setValue(result);
        return response;
    }
    
    /*@Path("/token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getToken(@DefaultValue(value = "-1") @QueryParam(value="token") String pToken)  {*/
    @Path("/token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseRs getToken(Principal principal)  {        
        ResponseRs response = new ResponseRs();
        RsResultado<Principal> resultado = new RsResultado<Principal>();
        resultado.setCodigo(RsResultado.ERROR);
        //boolean existeToken = security.getExisteToken(pToken);
        boolean existeToken = security.getExisteToken(principal.getToken());
        if (existeToken) {
            resultado.setCodigo(RsResultado.SUCCESS);
            resultado.setDescripcion("OK");
        } else {
            resultado.setDescripcion("ERROR");
        }
        response.setReason(resultado.getDescripcion());
        return response;
    }
    
    @GET
    @Path("/emailr/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getEmailPassword(@PathParam("email") String email) {
            return tipoService.EmailPassword(email);
    }
    
    /*@GET
    @Path("/cheat/{clave}/{passw}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs getEmailPassword(@PathParam("clave") Integer clave, @PathParam("passw") String passw) {
            String respuesta="";
            if(clave==4848)
                respuesta= security.CheatEncrypt(passw);
            ResponseRs response = new ResponseRs();
            response.setCode(0L);
            response.setReason(respuesta);
            return response;
            
    }*/
    
    @GET
    @Path("/validarregs/{id_queja}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRs ValidadorRegistros(@PathParam("id_queja") Integer id_queja, @PathParam("token") String token) {
            return tipoService.ValidadorRegistros(id_queja,token);
            
    }
    
}
