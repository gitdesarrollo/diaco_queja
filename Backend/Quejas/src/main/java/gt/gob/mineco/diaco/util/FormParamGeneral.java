package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormParamGeneral implements Serializable {
        private double UMA;
        private String titulo;
        private String genero;
        private String nombre;
        private String token;
        private Integer usuario;
        
        public FormParamGeneral() {
        }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getUMA() {
        return UMA;
    }

    public void setUMA(double UMA) {
        this.UMA = UMA;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
}
