package gt.gob.mineco.diaco.util;

import java.io.Serializable;
import java.util.Date;

public class FormSimple implements Serializable {
        private String listado_quejas;
        private Integer usuario;
        private String resp1;
        private String resp2;
        private String token;
        private Integer id_queja;
        private Integer id_flujo;
        private Integer valor;
        private Integer valor2;
        private float valor3;
        
        public FormSimple() {
        }

    public Integer getValor2() {
        return valor2;
    }

    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public Integer getId_queja() {
        return id_queja;
    }

    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getListado_quejas() {
        return listado_quejas;
    }

    public void setListado_quejas(String listado_quejas) {
        this.listado_quejas = listado_quejas;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the valor3
     */
    public float getValor3() {
        return valor3;
    }

    /**
     * @param valor3 the valor3 to set
     */
    public void setValor3(float valor3) {
        this.valor3 = valor3;
    }
        
}
