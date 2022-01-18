package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormMovimExp implements Serializable {
        private Integer id_departamento_interno_hasta;
        private Integer id_sede_hasta;
        private String listado_quejas;
        private Integer id_queja_unica;
        private Integer id_proveedor;
        private Integer id_flujo;
        
        private Integer id_queja;
        private Date fecha_programada;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private Integer usuario;
        private String token;
        private String observaciones;
        
        public FormMovimExp() {
        }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_queja_unica() {
        return id_queja_unica;
    }

    public void setId_queja_unica(Integer id_queja_unica) {
        this.id_queja_unica = id_queja_unica;
    }

    public Integer getId_departamento_interno_hasta() {
        return id_departamento_interno_hasta;
    }

    public void setId_departamento_interno_hasta(Integer id_departamento_interno_hasta) {
        this.id_departamento_interno_hasta = id_departamento_interno_hasta;
    }

    public Integer getId_sede_hasta() {
        return id_sede_hasta;
    }

    public void setId_sede_hasta(Integer id_sede_hasta) {
        this.id_sede_hasta = id_sede_hasta;
    }

    public String getListado_quejas() {
        return listado_quejas;
    }

    public void setListado_quejas(String listado_quejas) {
        this.listado_quejas = listado_quejas;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Date getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(Date fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public Integer getId_sede_diaco_operacio() {
        return id_sede_diaco_operacio;
    }

    public void setId_sede_diaco_operacio(Integer id_sede_diaco_operacio) {
        this.id_sede_diaco_operacio = id_sede_diaco_operacio;
    }

    public Integer getId_departamento_interno() {
        return id_departamento_interno;
    }

    public void setId_departamento_interno(Integer id_departamento_interno) {
        this.id_departamento_interno = id_departamento_interno;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
        
        
        
}
