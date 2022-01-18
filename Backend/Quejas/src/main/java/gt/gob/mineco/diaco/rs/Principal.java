package gt.gob.mineco.diaco.rs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.List;
import gt.gob.mineco.diaco.util.CheckNull;
import gt.gob.mineco.diaco.model.TipoUsuario;
import gt.gob.mineco.diaco.model.TipoUsuarioPerfil;

public class Principal implements Serializable {

    public static final int LOGIN_SUCCESS = 1;
    public static final int USER_NOT_FOUND = 2;
    public static final int USER_HAS_NO_ROLES = 3;
    public static final int AUTHENTICATION_FAILED = 4;
    public static final int SERVICE_UNAVAILABLE = 5;
    public static final int FIRST_LOGIN = 6;
    public static final int LOGOUT_SUCCESS = 7;
    public static final int TOKEN_NOT_FOUND = 8;
    public static final int PASSWORD_INVALID_LENGTH = 9;
    public static final int PASSWORD_EQUALS_USER = 10;
    public static final int PASSWORD_COMMON = 11;
    public static final int PASSWORD_SUCCESS = 12;
    public static final int PASSWORD_REGEX = 13;
    public static final int USERNAME_SUCCESS = 14;
    public static final int USERNAME_REGEX = 15;
    public static final int USERNAME_INVALID_LENGTH = 16;
    public static final int CHANGE_PASSWORD_REQUIRED = 17;
    public static final int USER_BLOCKED = 18;
    public static final int PASSWORD_HISTORY = 19;
    public static final int USER_TEMPORARILY_DISABLED = 20;
    public static final int USER_DISABLED = 21;

    long userId;
    String username;
    String fullname;
    String password;
    String token;
    int code;
    //List<TipoPerfilesPuesto> roles;
    List<String> roles;
    TipoUsuario tipousuario;
    
    public Principal() {
    }

    public TipoUsuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }
    
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        try{
            JsonObject queja = new JsonObject();
            JsonArray arr_roles = new JsonArray();
            CheckNull checknull=new CheckNull();
            //queja.addProperty("userid",checknull.cknull(this.userId));
            queja.addProperty("username",checknull.cknull(this.username));
            queja.addProperty("password",checknull.cknull(this.password));
            queja.addProperty("token",checknull.cknull(this.token));
            queja.addProperty("code",checknull.cknull(this.code));
            if(tipousuario!=null){
                queja.addProperty("userid",tipousuario.getIdUsuario());
                queja.addProperty("id_proveedor",checknull.cknull(tipousuario.getId_proveedor()));
                for (TipoUsuarioPerfil loc : tipousuario.getTipoUsuarioPerfilList()) {
                    JsonObject rolitem = new JsonObject();                
                    if(loc.getTipoPerfilesPuesto().getTipoflujo()!=null){
                        rolitem.addProperty("id_flujo",loc.getTipoPerfilesPuesto().getTipoflujo().getId());
                        rolitem.addProperty("nombre_flujo",loc.getTipoPerfilesPuesto().getTipoflujo().getFlujo());
                    }else{
                        rolitem.addProperty("id_flujo",0);
                        rolitem.addProperty("nombre_flujo","");
                    }
                    rolitem.addProperty("perfil",loc.getTipoPerfilesPuesto().getPerfil());
                    rolitem.addProperty("id_puesto",loc.getTipoPerfilesPuesto().getIdPerfilPuesto());
                    arr_roles.add(rolitem);
                }
                queja.add("roles",arr_roles);
            }
            return queja.toString()+",";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
