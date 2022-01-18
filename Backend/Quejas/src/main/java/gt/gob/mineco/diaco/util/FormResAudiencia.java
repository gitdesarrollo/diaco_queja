package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormResAudiencia implements Serializable {
        private Integer id;
        private Integer tipo;
        private String ccitacion;
        private Integer id_tipo_registro;
        private String fechanotificacion;
        private Integer id_queja;
        private Integer id_flujo;
        private String fecha;
        private String hora;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private Integer usuario_modifico;
        private String fecha_modifico;
        private String token;
        

        public FormResAudiencia() {
        }

    public Integer getUsuario_modifico() {
        return usuario_modifico;
    }

    public void setUsuario_modifico(Integer usuario_modifico) {
        this.usuario_modifico = usuario_modifico;
    }

    public String getFecha_modifico() {
        return fecha_modifico;
    }

    public void setFecha_modifico(String fecha_modifico) {
        this.fecha_modifico = fecha_modifico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getCcitacion() {
        return ccitacion;
    }

    public void setCcitacion(String ccitacion) {
        this.ccitacion = ccitacion;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public String getFechanotificacion() {
        return fechanotificacion;
    }

    public void setFechanotificacion(String fechanotificacion) {
        this.fechanotificacion = fechanotificacion;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

            

}
