package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormUsuarioSrch implements Serializable {
        private String usuario;
        private Integer flujo;
        private String nombre;
        private String token;
        private Integer proveedor;
        
        public FormUsuarioSrch() {
        }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getFlujo() {
        return flujo;
    }

    public void setFlujo(Integer flujo) {
        this.flujo = flujo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
