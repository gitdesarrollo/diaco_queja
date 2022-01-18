/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class FormEstadoResolver implements Serializable {
        private Integer id_queja;
        private Integer estado_resolver;
        
        public FormEstadoResolver() {
        }

    /**
     * @return the id_queja
     */
    public Integer getId_queja() {
        return id_queja;
    }

    /**
     * @param id_queja the id_queja to set
     */
    public void setId_queja(Integer id_queja) {
        this.id_queja = id_queja;
    }

    /**
     * @return the estado_resolver
     */
    public Integer getEstado_resolver() {
        return estado_resolver;
    }

    /**
     * @param estado_resolver the estado_resolver to set
     */
    public void setEstado_resolver(Integer estado_resolver) {
        this.estado_resolver = estado_resolver;
    }
}