package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormConfGeneral implements Serializable {
        private String token;
        private Integer dias_expirar_atcon;
        private Integer dias_alerta_atcon;
        private Integer dias_link_verif_datos;
        private Integer dias_expirar_jur;
        private Integer dias_alerta_jur;
        private Integer dias_expirar_vyv;
        private Integer dias_alerta_vyv;
        private Integer dias_expirar_sp;
        private Integer dias_alerta_sp;
        private Integer dias_expirar_pp;
        private Integer dias_link_conc_virt;
        private Integer dias_conf_queja_fin;
        private Integer correos_com_perm;
        private Integer correos_verif_datos;
        private Integer correos_audiencias;
        private Integer correos_archivar_sp;
        private Integer correos_conf_resp_pp;
        
        public FormConfGeneral() {
        }

    public Integer getCorreos_conf_resp_pp() {
        return correos_conf_resp_pp;
    }

    public void setCorreos_conf_resp_pp(Integer correos_conf_resp_pp) {
        this.correos_conf_resp_pp = correos_conf_resp_pp;
    }

    public Integer getDias_link_conc_virt() {
        return dias_link_conc_virt;
    }

    public void setDias_link_conc_virt(Integer dias_link_conc_virt) {
        this.dias_link_conc_virt = dias_link_conc_virt;
    }

    public Integer getDias_conf_queja_fin() {
        return dias_conf_queja_fin;
    }

    public void setDias_conf_queja_fin(Integer dias_conf_queja_fin) {
        this.dias_conf_queja_fin = dias_conf_queja_fin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getDias_expirar_atcon() {
        return dias_expirar_atcon;
    }

    public void setDias_expirar_atcon(Integer dias_expirar_atcon) {
        this.dias_expirar_atcon = dias_expirar_atcon;
    }

    public Integer getDias_alerta_atcon() {
        return dias_alerta_atcon;
    }

    public void setDias_alerta_atcon(Integer dias_alerta_atcon) {
        this.dias_alerta_atcon = dias_alerta_atcon;
    }

    public Integer getDias_link_verif_datos() {
        return dias_link_verif_datos;
    }

    public void setDias_link_verif_datos(Integer dias_link_verif_datos) {
        this.dias_link_verif_datos = dias_link_verif_datos;
    }

    public Integer getDias_expirar_jur() {
        return dias_expirar_jur;
    }

    public void setDias_expirar_jur(Integer dias_expirar_jur) {
        this.dias_expirar_jur = dias_expirar_jur;
    }

    public Integer getDias_alerta_jur() {
        return dias_alerta_jur;
    }

    public void setDias_alerta_jur(Integer dias_alerta_jur) {
        this.dias_alerta_jur = dias_alerta_jur;
    }

    public Integer getDias_expirar_vyv() {
        return dias_expirar_vyv;
    }

    public void setDias_expirar_vyv(Integer dias_expirar_vyv) {
        this.dias_expirar_vyv = dias_expirar_vyv;
    }

    public Integer getDias_alerta_vyv() {
        return dias_alerta_vyv;
    }

    public void setDias_alerta_vyv(Integer dias_alerta_vyv) {
        this.dias_alerta_vyv = dias_alerta_vyv;
    }

    public Integer getDias_expirar_sp() {
        return dias_expirar_sp;
    }

    public void setDias_expirar_sp(Integer dias_expirar_sp) {
        this.dias_expirar_sp = dias_expirar_sp;
    }

    public Integer getDias_alerta_sp() {
        return dias_alerta_sp;
    }

    public void setDias_alerta_sp(Integer dias_alerta_sp) {
        this.dias_alerta_sp = dias_alerta_sp;
    }

    public Integer getDias_expirar_pp() {
        return dias_expirar_pp;
    }

    public void setDias_expirar_pp(Integer dias_expirar_pp) {
        this.dias_expirar_pp = dias_expirar_pp;
    }

    public Integer getCorreos_com_perm() {
        return correos_com_perm;
    }

    public void setCorreos_com_perm(Integer correos_com_perm) {
        this.correos_com_perm = correos_com_perm;
    }

    public Integer getCorreos_verif_datos() {
        return correos_verif_datos;
    }

    public void setCorreos_verif_datos(Integer correos_verif_datos) {
        this.correos_verif_datos = correos_verif_datos;
    }

    public Integer getCorreos_audiencias() {
        return correos_audiencias;
    }

    public void setCorreos_audiencias(Integer correos_audiencias) {
        this.correos_audiencias = correos_audiencias;
    }

    public Integer getCorreos_archivar_sp() {
        return correos_archivar_sp;
    }

    public void setCorreos_archivar_sp(Integer correos_archivar_sp) {
        this.correos_archivar_sp = correos_archivar_sp;
    }

}
