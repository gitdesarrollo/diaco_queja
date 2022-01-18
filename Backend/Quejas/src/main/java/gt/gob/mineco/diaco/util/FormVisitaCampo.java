package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormVisitaCampo implements Serializable {
        private Integer id_visita_campo;
        private String fecha_visita;
	private String observaciones;
        private Integer id_tipo_registro;
	private Integer hecha_por;
        private Integer id_flujo;
        private Integer id_queja;
        private boolean edit;
        
        private Integer id_estado_operado;
        private String descripcion;
        private Integer usuario_operacion;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private String descripcion_consumidor;
        private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
        
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }
        
    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }
        
    public Integer getId_visita_campo() {
        return id_visita_campo;
    }

    public void setId_visita_campo(Integer id_visita_campo) {
        this.id_visita_campo = id_visita_campo;
    }

    public String getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public Integer getHecha_por() {
        return hecha_por;
    }

    public void setHecha_por(Integer hecha_por) {
        this.hecha_por = hecha_por;
    }

    public Integer getId_estado_operado() {
        return id_estado_operado;
    }

    public void setId_estado_operado(Integer id_estado_operado) {
        this.id_estado_operado = id_estado_operado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUsuario_operacion() {
        return usuario_operacion;
    }

    public void setUsuario_operacion(Integer usuario_operacion) {
        this.usuario_operacion = usuario_operacion;
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

    public String getDescripcion_consumidor() {
        return descripcion_consumidor;
    }

    public void setDescripcion_consumidor(String descripcion_consumidor) {
        this.descripcion_consumidor = descripcion_consumidor;
    }

        
        
}
