package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormComConsumidor implements Serializable {
        private Integer id_comunicacion_consumidor;
        private Integer id_queja;
        private Integer id_flujo;
        private String estatus;
	private String observaciones;
        private Integer id_tipo_registro;
	private Integer via;
        
        private Integer id_estado_operado;
        private String descripcion;
        private Integer usuario_operacion;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private String descripcion_consumidor;

        private String token;

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
        
    public Integer getId_comunicacion_consumidor() {
        return id_comunicacion_consumidor;
    }

    public void setId_comunicacion_consumidor(Integer id_comunicacion_consumidor) {
        this.id_comunicacion_consumidor = id_comunicacion_consumidor;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    public Integer getVia() {
        return via;
    }

    public void setVia(Integer via) {
        this.via = via;
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
