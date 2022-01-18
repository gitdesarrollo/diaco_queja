package gt.gob.mineco.diaco.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import gt.gob.mineco.diaco.annotation.SecuredResource;
import gt.gob.mineco.diaco.util.JWTUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gt.gob.mineco.diaco.dao.SecurityManagerRepository;
import gt.gob.mineco.diaco.rs.RsResultado;
import javax.ws.rs.core.MediaType;

@SecuredResource
@Provider
public class SecuredResourceFilter implements ContainerRequestFilter {

    @Inject
    SecurityManagerRepository dao;

//    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String authToken = context.getHeaderString("Auth-Token");
        RsResultado result = new RsResultado();
        if (authToken == null || authToken.isEmpty()) {
            result.setCodigo(RsResultado.INVALID_TOKEN);
            result.setDescripcion("No exite token a validar.");
            context.abortWith(Response.ok(result, MediaType.APPLICATION_JSON).status(Response.Status.OK).build());
        } else {
            try {
                JWTUtils.verifyToken(authToken);
                // verificar si token no está en listado de tokens marcados como invalidos
                Map<String, Object> params = new HashMap<>();
                params.put("token", authToken);
                int tokenEncontrado = dao.getCountToken(params);
                if (tokenEncontrado > 0) {
                    result.setCodigo(RsResultado.INVALID_TOKEN);
                    result.setDescripcion("Sesion finalizada.");
                    context.abortWith(Response.ok(result, MediaType.APPLICATION_JSON).status(Response.Status.OK).build());
                }
            } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
                e.printStackTrace();
                result.setCodigo(RsResultado.INVALID_TOKEN);
                result.setDescripcion("Sesion finalizada. " + e.getMessage());
                context.abortWith(Response.ok(result, MediaType.APPLICATION_JSON).status(Response.Status.OK).build());
            }
        }
    }

}
