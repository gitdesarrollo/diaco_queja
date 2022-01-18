package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormQuejaFinalizada implements Serializable {
        private Integer id_queja;
        private Integer razon;
        private Integer motivo_poa;
        private Integer categoria;
        private float monto=0;
        private String detalles;
        private String conclusion;
        private String comentario;
        private boolean archivo;
        
        private Integer usuario_operacion;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private String token;
        
        public FormQuejaFinalizada(){
        }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getRazon() {
        return razon;
    }

    public void setRazon(Integer razon) {
        this.razon = razon;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String descripcion) {
        this.detalles = descripcion;
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

    public Integer getMotivo_poa() {
        return motivo_poa;
    }

    public void setMotivo_poa(Integer motivo_poa) {
        this.motivo_poa = motivo_poa;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public boolean isArchivo() {
        return archivo;
    }

    public void setArchivo(boolean archivo) {
        this.archivo = archivo;
    }
            
    
}
