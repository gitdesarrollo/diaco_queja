package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormConfColas implements Serializable {
        private Integer usuario_asignado;
        private Integer id_tipo_cola;
        private Integer id_local;
        private String fecha;
        private Integer estado;
        private Integer usuario_operacion;
        private String token;
        private Integer operacion; // 1- cambio estado, 2- programacion reactivacion, 3- delete programacion y reactivar
        
        public FormConfColas() {
        }

    public Integer getId_tipo_cola() {
        return id_tipo_cola;
    }

    public void setId_tipo_cola(Integer id_tipo_cola) {
        this.id_tipo_cola = id_tipo_cola;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUsuario_asignado() {
        return usuario_asignado;
    }

    public void setUsuario_asignado(Integer usuario_asignado) {
        this.usuario_asignado = usuario_asignado;
    }

    public Integer getId_local() {
        return id_local;
    }

    public void setId_local(Integer id_local) {
        this.id_local = id_local;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getUsuario_operacion() {
        return usuario_operacion;
    }

    public void setUsuario_operacion(Integer usuario_operacion) {
        this.usuario_operacion = usuario_operacion;
    }


}
