package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormReg_ComPerm implements Serializable {
        private Integer id_queja;
        private Integer creado_por;
        private String token;
                
        public FormReg_ComPerm(){
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

    public Integer getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(Integer creado_por) {
        this.creado_por = creado_por;
    }

        
        
}
