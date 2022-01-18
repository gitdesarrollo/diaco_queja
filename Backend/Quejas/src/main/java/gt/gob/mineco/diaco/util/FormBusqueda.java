package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormBusqueda implements Serializable {
        private String fecha_desde;
        private String fecha_hasta;
        private String con_nombre;
        private String con_apellido;
        private String con_dpi;
        private String con_nit;
        private String pro_nombre;
        private String pro_nit;
        private String no_queja;
        private Integer id_proveedor;
        private Integer id_consumidor;
        private Integer id_flujo;
        private Integer id_usuario;
        private Integer estado;
        private String token;
        
        public FormBusqueda(){
        }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
        
    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }
        
    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Integer getId_consumidor() {
        return id_consumidor;
    }

    public void setId_consumidor(Integer id_consumidor) {
        this.id_consumidor = id_consumidor;
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

    public String getCon_nombre() {
        return con_nombre;
    }

    public void setCon_nombre(String con_nombre) {
        this.con_nombre = con_nombre;
    }

    public String getCon_apellido() {
        return con_apellido;
    }

    public void setCon_apellido(String con_apellido) {
        this.con_apellido = con_apellido;
    }

    public String getCon_dpi() {
        return con_dpi;
    }

    public void setCon_dpi(String con_dpi) {
        this.con_dpi = con_dpi;
    }

    public String getCon_nit() {
        return con_nit;
    }

    public void setCon_nit(String con_nit) {
        this.con_nit = con_nit;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public String getPro_nit() {
        return pro_nit;
    }

    public void setPro_nit(String pro_nit) {
        this.pro_nit = pro_nit;
    }

    public String getNo_queja() {
        return no_queja;
    }

    public void setNo_queja(String no_queja) {
        this.no_queja = no_queja;
    }
        
        
}
