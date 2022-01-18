/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import java.io.Serializable;

/**
 *
 * @author julio
 */
public class CedulaNotificacionDto implements Serializable {
    
     private String esCP;
     private int idQueja;
     private String esResultadoResolucion;
     private int usuario;
     private int idResultadoResolucion;
     private String token;
     private String correlativo;

    /**
     * @return the esCP
     */
    public String getEsCP() {
        return esCP;
    }

    /**
     * @param esCP the esCP to set
     */
    public void setEsCP(String esCP) {
        this.esCP = esCP;
    }

    /**
     * @return the idQueja
     */
    public int getIdQueja() {
        return idQueja;
    }

    /**
     * @param idQueja the idQueja to set
     */
    public void setIdQueja(int idQueja) {
        this.idQueja = idQueja;
    }

    /**
     * @return the esResultadoResolucion
     */
    public String getEsResultadoResolucion() {
        return esResultadoResolucion;
    }

    /**
     * @param esResultadoResolucion the esResultadoResolucion to set
     */
    public void setEsResultadoResolucion(String esResultadoResolucion) {
        this.esResultadoResolucion = esResultadoResolucion;
    }

    /**
     * @return the usuario
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the idResultadoResolucion
     */
    public int getIdResultadoResolucion() {
        return idResultadoResolucion;
    }

    /**
     * @param idResultadoResolucion the idResultadoResolucion to set
     */
    public void setIdResultadoResolucion(int idResultadoResolucion) {
        this.idResultadoResolucion = idResultadoResolucion;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
    
}
