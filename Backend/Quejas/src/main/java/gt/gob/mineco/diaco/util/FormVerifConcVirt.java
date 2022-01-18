package gt.gob.mineco.diaco.util;

import java.io.Serializable;

public class FormVerifConcVirt implements Serializable {
        private Integer id_verif_conciliacion_previa;
        private Integer id_queja;
        private String queja_resuelta;
	private String razon_prop_provee_rechazada;
        private Integer id_tipo_registro;
	private String notas;
        private Integer resolucion;
        private Integer respuesta_diaco;
        
        private Integer id_estado_operado;
        private String descripcion;
        private Integer usuario_operacion;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private String descripcion_consumidor;
        private Integer id_flujo;
        private String token;
        
        public FormVerifConcVirt(){
        }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_verif_conciliacion_previa() {
        return id_verif_conciliacion_previa;
    }

    public void setId_verif_conciliacion_previa(Integer id_verif_conciliacion_previa) {
        this.id_verif_conciliacion_previa = id_verif_conciliacion_previa;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public String getQueja_resuelta() {
        return queja_resuelta;
    }

    public void setQueja_resuelta(String queja_resuelta) {
        this.queja_resuelta = queja_resuelta;
    }

    public String getRazon_prop_provee_rechazada() {
        return razon_prop_provee_rechazada;
    }

    public void setRazon_prop_provee_rechazada(String razon_prop_provee_rechazada) {
        this.razon_prop_provee_rechazada = razon_prop_provee_rechazada;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getResolucion() {
        return resolucion;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
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

    public Integer getRespuesta_diaco() {
        return respuesta_diaco;
    }

    public void setRespuesta_diaco(Integer respuesta_diaco) {
        this.respuesta_diaco = respuesta_diaco;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }
        
    
}
