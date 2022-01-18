package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormRegistro implements Serializable {
        private Integer id_queja;
        private Integer creado_por;
        private Integer id_comunicacion_consumidor;
        private Integer id_audiencia;
        private Integer id_flujo;
        private String token;
        
        public FormRegistro(){
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

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }

    /**
     * @return the id_flujo
     */
    public Integer getId_flujo() {
        return id_flujo;
    }

    /**
     * @param id_flujo the id_flujo to set
     */
    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    /**
     * @return the id_comunicacion_consumidor
     */
    public Integer getId_comunicacion_consumidor() {
        return id_comunicacion_consumidor;
    }

    /**
     * @param id_comunicacion_consumidor the id_comunicacion_consumidor to set
     */
    public void setId_comunicacion_consumidor(Integer id_comunicacion_consumidor) {
        this.id_comunicacion_consumidor = id_comunicacion_consumidor;
    }

        
        
}
