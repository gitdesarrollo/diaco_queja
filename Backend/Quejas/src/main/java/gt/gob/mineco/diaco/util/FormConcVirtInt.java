package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormConcVirtInt implements Serializable {
        private Integer id;
        private Date fecha;
        private String pregunta;
	private String respuesta_con;
        private String estado;
        private boolean cambiar_archivos;
        private boolean cambiar_datos;
        private Integer id_queja;
        private Integer id_departamento_interno;
        private Integer usuario;
        private boolean is_edit;
        private String token;
        
        public FormConcVirtInt() {
        }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIs_edit() {
        return is_edit;
    }

    public void setIs_edit(boolean is_edit) {
        this.is_edit = is_edit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta_con() {
        return respuesta_con;
    }

    public void setRespuesta_con(String respuesta_con) {
        this.respuesta_con = respuesta_con;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCambiar_archivos() {
        return cambiar_archivos;
    }

    public void setCambiar_archivos(boolean cambiar_archivos) {
        this.cambiar_archivos = cambiar_archivos;
    }

    public boolean isCambiar_datos() {
        return cambiar_datos;
    }

    public void setCambiar_datos(boolean cambiar_datos) {
        this.cambiar_datos = cambiar_datos;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
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
