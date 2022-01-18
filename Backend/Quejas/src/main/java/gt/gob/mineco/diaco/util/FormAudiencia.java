package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormAudiencia implements Serializable {
        private Integer id_audiencia;
        private String fecha_finalizada;
        private Integer id_resultado_audiencia;
        private Integer es_primera_seg_audiencia;
        private Integer id_tipo_registro;
        private String fecha_firma_direccion;
        private String aprobado_jefe_juridico;
        private String ced_cita_consumidor;
        private String ced_cita_proveedor;
        private String ced_notif_consumidor;
        private String ced_notif_proveedor;
        private Integer id_queja;
        private Integer id_flujo;
        private String fecha_programada;
        private String hora_programada;
        private Integer id_sede_diaco_operacio;
        private Integer id_departamento_interno;
        private Integer usuario;
        private String token;

        public FormAudiencia() {
        }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getFecha_finalizada() {
        return fecha_finalizada;
    }

    public void setFecha_finalizada(String fecha_finalizada) {
        this.fecha_finalizada = fecha_finalizada;
    }

    public String getFecha_firma_direccion() {
        return fecha_firma_direccion;
    }

    public void setFecha_firma_direccion(String fecha_firma_direccion) {
        this.fecha_firma_direccion = fecha_firma_direccion;
    }

    public String getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(String fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public String getHora_programada() {
        return hora_programada;
    }

    public void setHora_programada(String hora_programada) {
        this.hora_programada = hora_programada;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
        
    public Integer getId_audiencia() {
        return id_audiencia;
    }

    public void setId_audiencia(Integer id_audiencia) {
        this.id_audiencia = id_audiencia;
    }

    public Integer getId_resultado_audiencia() {
        return id_resultado_audiencia;
    }

    public void setId_resultado_audiencia(Integer id_resultado_audiencia) {
        this.id_resultado_audiencia = id_resultado_audiencia;
    }

    public Integer getEs_primera_seg_audiencia() {
        return es_primera_seg_audiencia;
    }

    public void setEs_primera_seg_audiencia(Integer es_primera_seg_audiencia) {
        this.es_primera_seg_audiencia = es_primera_seg_audiencia;
    }

    public Integer getId_tipo_registro() {
        return id_tipo_registro;
    }

    public void setId_tipo_registro(Integer id_tipo_registro) {
        this.id_tipo_registro = id_tipo_registro;
    }

    public String getAprobado_jefe_juridico() {
        return aprobado_jefe_juridico;
    }

    public void setAprobado_jefe_juridico(String aprobado_jefe_juridico) {
        this.aprobado_jefe_juridico = aprobado_jefe_juridico;
    }

    public String getCed_cita_consumidor() {
        return ced_cita_consumidor;
    }

    public void setCed_cita_consumidor(String ced_cita_consumidor) {
        this.ced_cita_consumidor = ced_cita_consumidor;
    }

    public String getCed_cita_proveedor() {
        return ced_cita_proveedor;
    }

    public void setCed_cita_proveedor(String ced_cita_proveedor) {
        this.ced_cita_proveedor = ced_cita_proveedor;
    }

    public String getCed_notif_consumidor() {
        return ced_notif_consumidor;
    }

    public void setCed_notif_consumidor(String ced_notif_consumidor) {
        this.ced_notif_consumidor = ced_notif_consumidor;
    }

    public String getCed_notif_proveedor() {
        return ced_notif_proveedor;
    }

    public void setCed_notif_proveedor(String ced_notif_proveedor) {
        this.ced_notif_proveedor = ced_notif_proveedor;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
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
