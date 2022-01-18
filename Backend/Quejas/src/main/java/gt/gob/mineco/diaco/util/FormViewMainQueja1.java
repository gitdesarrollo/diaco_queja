package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormViewMainQueja1 implements Serializable {
        private Integer estado;
        private String fecha_desde;
        private String fecha_hasta;
        private Integer usuario;
        private String no_queja;
        private String token;
        
        public FormViewMainQueja1(){
        }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNo_queja() {
        return no_queja;
    }

    public void setNo_queja(String no_queja) {
        this.no_queja = no_queja;
    }
        
    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(String fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public String getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(String fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }        
           
}
