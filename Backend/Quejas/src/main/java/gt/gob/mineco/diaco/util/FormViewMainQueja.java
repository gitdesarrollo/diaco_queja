package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormViewMainQueja implements Serializable {
        private Integer estado;
        private String fecha_desde;
        private String fecha_hasta;
        private Integer id_departamento;
        private Integer id_municipio;
        private Integer id_proveedor;
        private Integer id_consumidor;
        private String numeroqueja;
        private Integer id_flujo;
        private Integer usuario;
        private String token;
        
        public FormViewMainQueja(){}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
        
    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }
        
    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
    }

    public String getNumeroqueja() {
        return numeroqueja;
    }

    public void setNumeroqueja(String numeroqueja) {
        this.numeroqueja = numeroqueja;
    }
        
    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }
        
        
        
}
