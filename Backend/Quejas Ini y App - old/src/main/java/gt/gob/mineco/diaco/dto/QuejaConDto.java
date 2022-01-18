/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mps
 */
public class QuejaConDto {

    private Integer idQueja;
    private String resp;
    private String xqueja;
    private String xsolicitud;
    private String xubicacion;
    private String varchivos;
    private String vdatos;
    private String token;

    public Integer getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(Integer idQueja) {
        this.idQueja = idQueja;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getXqueja() {
        return xqueja;
    }

    public void setXqueja(String xqueja) {
        this.xqueja = xqueja;
    }

    public String getXsolicitud() {
        return xsolicitud;
    }

    public void setXsolicitud(String xsolicitud) {
        this.xsolicitud = xsolicitud;
    }

    public String getXubicacion() {
        return xubicacion;
    }

    public void setXubicacion(String xubicacion) {
        this.xubicacion = xubicacion;
    }

    public String getVarchivos() {
        return varchivos;
    }

    public void setVarchivos(String varchivos) {
        this.varchivos = varchivos;
    }

    public String getVdatos() {
        return vdatos;
    }

    public void setVdatos(String vdatos) {
        this.vdatos = vdatos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
    
}
